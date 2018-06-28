package com.example.demo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebService
public class HelloService {

	@WebMethod
	public String sayHello(String message) {
		return "Hello ," + message;
	}

	public static void main(String[] args) {
		// create and publish an endPoint
		HelloService hello = new HelloService();
		Endpoint endPoint = Endpoint.publish("http://localhost:18080/helloService", hello);
	}

}
