package org.lostinbrittany.sparkjava.test;

import static spark.Spark.*;

public class HelloWorld {
	
	public static void main(String[] args) {
		get("/", (request, response) -> "Hello World");
	}
}