# Optional Step: Mongo Beers

Let's say you already have your beers in a MongoDB database. Now we are going to replace our local JSON files with calls to MongoDB.

> In order to do this step you need to have your beer data in a MongoDB database.
> How to do it is outside the scope of this tutorial, but if you only want to do a quicktest, you could:
>
> - Install MongoDB (see http://mongodb.com/)
> - Start the MongoDB daemon (usually with the command `mongod`)
> - Use `mongoimport` command line tool to import the detailed JSON datafiles
>
>    ```
>      mongoimport --db beers --collection beers beers/AffligemBlond.json
>      mongoimport --db beers --collection beers beers/AffligemDubbel.json
>      ...
>   ```  

## Adding the MongoDB driver dependency

For this step, we are going to use the official Mongo DB Java driver by adding it as a dependency to our `build.gradle`:

```groovy
// https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver
compile group: 'org.mongodb', name: 'mongo-java-driver', version: '3.10.2'
```

Don't forget to refresh your Gradle dependencies in Eclipse to make it recover
the new dependencies from the  repository.

## Enriching `Beer` to easy JSON marshalling

GSON allow using annotations to mark object properties for, for example, conditional rendering.

Let's add a `@Expose` annotation to the fields included in the beer list but not to those only in details:

```java
	@Expose private String name;
	@Expose private String id;
	@Expose private String img;
	@Expose private String description;
	@Expose private double alcohol;

	private String availability;
	private String brewery;
	private String label;
	private String serving;
	private String style;
```

Now we can add two methods to serialize the object into JSON, one to serialize the object only with the exposed properties and another with all the properties:

```java
public static final Gson gson = new Gson();

public String toJSONDetail() {
	return gson.toJson(this);		
}

public String toJSON() {
	final GsonBuilder builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		final Gson gsonShort = builder.create();
	
	return gsonShort.toJson(this);
}
```

## Create a `BeerDAO`

We are going to create a DAO to deal with the Database.

Our DAO will have two methods, `getBeer` to get info on one beer, and `getBeerList` to get the full list of beers:

Let's begin with `getBeer`:

```java

	public static String getBeer(String id) {

		MongoClient mongoClient = new MongoClient();
		
		MongoDatabase database = mongoClient.getDatabase("beers");
		
		MongoCollection<Document> beers = database.getCollection("beers");
		
		Document beerDocument = beers.find(Filters.eq("id", id)).first();
		
		Beer beer = gson.fromJson(beerDocument.toJson(), Beer.class);
		
		return beer.toJSONDetail();
	}
```

And now `getBeerList`:

```java
	
	public static String getBeerList() {

		MongoClient mongoClient = new MongoClient();
		
		MongoDatabase database = mongoClient.getDatabase("beers");
		
		MongoCollection<Document> beers = database.getCollection("beers");
				
		/*
		   {
		    "alcohol": 6.8,
		    "description": "Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.",
		    "id": "AffligemBlond",
		    "img": "/img/AffligemBlond.jpg",
		    "name": "Affligem Blond"
		  },
		 */
		StringBuilder sb = new StringBuilder("[");
		for (Document beerDocument : beers.find()) {
			Beer beer = gson.fromJson(beerDocument.toJson(), Beer.class);			
			sb.append(beer.toJSON()).append(",");		
		}
		sb.deleteCharAt(sb.length()-1).append("]");
		return sb.toString();
	}
}
```

## Call the DAO from the API

On the `BeersAPI` change the route methods to call the DAO:

```java
		get("/BeerList", (request, response) -> {
			return BeerDAO.getBeerList();
		});
		
		get("/Beer/:beerId",(request, response) -> {
			return BeerDAO.getBeer(request.params(":beerId"));
		});

```
