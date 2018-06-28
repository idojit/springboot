package com.example.p2p;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 外网端服务，穿透中继
 * 
 * @author zz
 *
 */
public class Server {
	public static List<ServerThread> connections = new ArrayList<ServerThread>();

	public static void main(String[] args) {
		try {
			// 1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = null;
			// 记录客户端的数量
			int count = 0;
			System.out.println("***服务器即将启动，等待客户端的连接***");
			// 循环监听等待客户端的连接
			while (true) {
				// 调用accept()方法开始监听，等待客户端的连接
				socket = serverSocket.accept();
				// 创建一个新的线程
				ServerThread serverThread = new ServerThread(socket);
				// 启动线程
				serverThread.start();

				connections.add(serverThread);

				count++;// 统计客户端的数量
				System.out.println("客户端的数量：" + count);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


/*
假设现在有以下3台机器：

外网机器，IP：121.56.21.85 ， 以下简称“主机A”

处在内网1下的机器，外网IP：106.116.5.45 ，内网IP：192.168.1.10， 以下简称“主机1”

处在内网2下的机器，外网IP：104.128.52.6 ，内网IP：192.168.0.11，以下简称“主机2”

很显然内网的两台机器不能直接连接，我们现在要实现的是借助外网机器，让两台内网机器进行tcp直连通讯。



实现过程如下：

1、主机A启动服务端程序，监听端口8888，接受TCP请求。

2、启动主机1的客户端程序，连接主机A的8888端口，建立TCP连接。

3、启动主机2的客户端程序，连接主机A的8888端口，建立TCP连接。

4、主机2发送一个命令告诉主机A，我要求与其他设备进行连接，请求协助进行穿透。

5、主机A接收到主机2的命令之后，会返回主机1的外网地址和端口给主机2，同时把主机2的外网地址和端口发送给主机1。

6、主机1和主机2在收到主机A的信息之后，同时异步发起对对方的连接。

7、在与对方发起连接之后，监听本地与主机A连接的端口（也可以在发起连接之前），（由于不同的操作系统对tcp的实现不尽相同，有的操作系统会在连接发送之后，把对方的连接当作是回应，即发出SYN之后，把对方发来的SYN当作是本次SYN的ACK，这种情况就不需要监听也可建立连接，本文的代码所在测试环境就不需要监听，测试环境为：服务器centos 7.3， 内网1 win10，内网2 win10和centos7.2都测试过）。

8、主机1和主机2成功连上，可以关闭主机A的服务，主机1和主机2的连接依然会持续生效，不关闭就形成了一个3方直连的拓扑网状结构网络。
 */
