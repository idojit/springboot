package com.example.demo;

import com.example.demo.webservice.HelloService;
import com.example.demo.webservice.HelloServiceService;

public class HelloClient {

	public static void main(String[] args) {
		HelloServiceService helloServiceService = new HelloServiceService();
		HelloService helloService = helloServiceService.getHelloServicePort();
		System.out.println(helloService.sayHello("你好"));

	}

}
