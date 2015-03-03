# Step 04 - Serving static resources

## We want to use Spark to serve the beer images 

Spark can also serve the static resources (images, HTML, CSS, JS...) of your application. To do it, you can assign a folder in the classpath serving static files with the `staticFileLocation` method. Note that the public directory name is not included in the URL. A file `/static/css/style.css` is made available as `http://{host}:{port}/css/style.css`

		staticFileLocation("/static"); // Static files
		
In your project you should put the `static` directory inside `src`.		
		
You can also assign an external folder (not in the classpath) serving static files with the externalStaticFileLocation method.

		externalStaticFileLocation("/var/www/public"); // Static files

So now we can put our beer images inside `src/static/img/` and serve them with URLs like `{host}:{port}/img/AffligemTripel.jpg`.		 


## Now we can make it serve also our HTML, CSS & JS, can't we? 

Yes you can! Take a stable version of the [angular-beers](https://github.com/LostInBrittany/angular-beers) project (step 10 for example) and put it inside the static directory. Test it to see if it works... and it doesn't!

First thing to change, the beer list location. In the original [angular-beers](https://github.com/LostInBrittany/angular-beers) project we looked for a `beers/beers.json` file, now the have the nice `/BeerList` API endpoint. So you need to change the `BeerListCtrl` controller to ask for the right endpoint.

If you do it;, you will see the [angular-beers](https://github.com/LostInBrittany/angular-beers) running as a static resource on Spark.
		