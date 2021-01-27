# Step 02 - Installing Spark

We add Spark as a dependency for Gradle. To do it we need to define in the `build.gradle` file a *respository* (a source storing location when we will look for dependencies) and the dependency information for Spark:

```groovy
repositories {
	mavenCentral()
}

dependencies {
	compile group: 'com.sparkjava', name: 'spark-core', version: '2.7.2'
	compile group: 'org.slf4j', name: 'slf4j-simple', version: '1.6.1'		
}
```

Then we can create a new class called `HelloWorld`  and add the following code to it:

```java
package org.lostinbrittany.sparkjava.test;


import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class HelloWorld {

	public static void main(String[] args) {
		get("/", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return "Hello World!!";
			}
		});
	}

}
```

This code:

* Imports the required classes from the Spark library.
* Creates a new class called `HelloWorld` and defines a main method.
* Defines a route that tells Spark that when an HTTP GET request is made to *‘/’*, return *“Hello World”*. You use Spark’s `get()` method to define the mapping from the URL to the callback.

To see the application in action, run the main program using your IDE. The application will start the embedded Jetty server at http://0.0.0.0:4567. When you open this link in your web browser, you will see *“Hello World!!”*.

Take advantage of Java 8 lambda expressions to make your code more concise and clean. Spark is a modern Java web framework that takes advantage of Java 8 features.	

```java
package org.lostinbrittany.sparkjava.test;

import static spark.Spark.*;

public class HelloWorld {
	
	public static void main(String[] args) {
		get("/", (request, response) -> "Hello World");
	}
}
```

