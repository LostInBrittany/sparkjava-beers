# Sparkjava-Beers 

## A server-side companion to the [angular-beers](https://github.com/LostInBrittany/angular-beers) project

The [angular-beers](https://github.com/LostInBrittany/angular-beers) project is a small [AngularJS](http://angularjs.org) tutorial that can be used on its own. But IMHO it is a pity to do only the client-side and mocking the server API with plain files. So here we have a companion project where we are going to do the server-side of [angular-beers](https://github.com/LostInBrittany/angular-beers) using [Spark](http://sparkjava.com/), tiny [Sinatra](http://www.sinatrarb.com/) inspired framework for creating web applications in Java 8 with minimal effort.

## What are the objectives of this tutorial

Follow this tutorial to learn to build APIs in Java quickly an easily, without all the pain of the classic JEE stack. You will use the [Spark](http://sparkjava.com/) framework, with some drops of SQL (with [h2](http://www.h2database.com/)) and NoSQL databases (with [MongoDB](http://mongodb.com), work in progress).

## What do I need to use this tutorial

You will need a web-browser and your favorite Java IDE (I can suggest [IntelliJ IDEA](https://www.jetbrains.com/idea/)  or [Eclipse](http://eclipse.org), but any other will do it). You will also need the [Gradle](http://gradle.org) build tool.

## How is the tutorial organized ##

As many computers used for the course haven't Git, we have structurated the project to allow a Git-less use. The `src` directory is the sources directory of the project, the working version of the code. The tutorial is divided in steps, each one in its own directory:

1. [Empty project](./step-01/)
1. [Installing Spark](./step-02/)
1. [Using the `Beer` class](./step-03/)
1. [Serving static resources](./step-04/)

And two optional possibilities:

- [Using a SQL DB 01](./opt-sql-01/)  and [Using a SQL DB 02](./opt-sql-01/)
- [Using MongoDB](./opt-mongo/)

In each step directory you have a README file that explain the objective of the step, that you will do in the working directory `app`. If you have problems or if you get lost, you also have the solution of each step in the step directories. 