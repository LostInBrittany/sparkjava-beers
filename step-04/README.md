# Step 04 - Serving static resources #

## We want to use Spark to serve the beer images ##

Spark can also serve the static resources (images, HTML, CSS, JS...) of your application. To do it, you can assign a folder in the classpath serving static files with the `staticFileLocation` method. Note that the public directory name is not included in the URL. A file `/static/css/style.css` is made available as `http://{host}:{port}/css/style.css`

		staticFileLocation("/static"); // Static files
		
In your project you should put the `static` directory inside `src`.		
		
You can also assign an external folder (not in the classpath) serving static files with the externalStaticFileLocation method.

		externalStaticFileLocation("/var/www/public"); // Static files

So now we can put our beer images inside `src/static/img/` and serve them with URLs like `{host}:{port}/img/AffligemTripel.jpg`.		 


		