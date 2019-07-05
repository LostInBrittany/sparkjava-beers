# Step 03 - Using the `Beer` class

## Let's create the beer list 

We are going to take the beer list from the [angular-beers](https://github.com/LostInBrittany/angular-beers) project and serve it from the Spark server. 
To do it, we begin by creating a `Beer` class that keeps the same information than the `Beer` JSON in JavaScript side.


		package org.lostinbrittany.sparkjava.test.model;
		
		public class Beer {
			
			/*
			 ...
			 */
			
			private String name;
			private String id;
			private String img;
			private String description;
			private double alcohol;
				
			/*
			 ...
			 */
		}
		
And then, quite manually for now, we create our *BeerList* with a static method:



			public static List<Beer> getBeers() {
				ArrayList<Beer> list = new ArrayList<Beer>();
		
				Beer beer;
		
				beer = new Beer();
				beer.setAlcohol(6.8);
				beer.setDescription("Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.");
				beer.setId("AffligemBlond");
				beer.setImg("beers/img/AffligemBlond.jpg");
				beer.setName("Affligem Blond");
				list.add(beer);
				
				/*
			 	 ...
			 	 */
			 	 
			 	 return list;
			 }
   		
   		
## We want an API so we need JSON 

So now we have a nice Java list of beers. In order to send it to the client in our Spark API, we need to transform it in JSON (REST APIs speak JSON). To do it, we need a JSON library like [GSON](https://code.google.com/p/google-gson/). 

Let's begin by adding the GSON dependency to the `build.gradle` and actualize the dependencies by running `gradle eclipse`:

		dependencies {
		 	compile group: 'com.sparkjava', name: 'spark-core', version: '2.7.2'
			compile group: 'org.slf4j', name: 'slf4j-simple', version: '1.6.1'	
		 	compile group: 'com.google.code.gson', name: 'gson', version: '2.8.5'
		}   		
		
Now we can use GSON to serialize the beer list into JSON:

		List<Beer> beerList = Beer.getBeers();
       Gson gson = new Gson();
       String json = gson.toJson(beerList); 		
       

## Answering to the REST request 

And now we want our API to listen to the */BeerList* path and send back the JSON corresponding to the beer list. We create a `BeersAPI` class to pilot this feature:

		package org.lostinbrittany.sparkjava.test;
		
		import static spark.Spark.get;
		import java.util.List;
		import org.lostinbrittany.sparkjava.test.model.Beer;
		import com.google.gson.Gson;
		
		
		public class BeersAPI {
		
		    public static void main(String[] args) {
		        get("/BeerList", (request, response) -> {
		        	List<Beer> beerList = Beer.getBeers();
		        	Gson gson = new Gson();
		        	String json = gson.toJson(beerList);  
		        	return json;
		        });
		    }
		}      
		
		
