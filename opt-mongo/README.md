# Optional Step 1 

# Step-06 : Mongo Beers

Let's say you already have your beers in a MongoDB database. Now we are going to replace our local JSON files with calls to MongoDB.

> In order to do this step you need to have your beer data in a MongoDB database.
> How to do it is outside the scope of this tutorial, but if you only want to do a quicktest, you could:
>
> - Install MongoDB (see http://mongodb.com/)
> - Start the MongoDB daemon (usually with the command `mongod`)
> - Use `mongoimport` command line tool to import the detailed JSON datafiles
>
>    ```
>      mongoimport --db test --collection beers beers/AffligemBlond.json
>      mongoimport --db test --collection beers beers/AffligemDubbel.json
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

## Connecting to Mongo

Now in our `BeersAPI` file we create methods to open and close a connection:

```java
private static MongoClient mongoClient;
private static MongoDatabase database;

private static void initDB() {
  try {
    mongoClient = new MongoClient();		
    database = mongoClient.getDatabase("beers");		
    collection = database.getCollection("beers");
  } catch (Exception e) {
    System.out.println(e.getMessage());
    logger.error(e.getMessage(), e);
  }
}

private static void closeDb() {

  try {
    mongoClient.close();
  } catch (Exception e) {
    System.out.println(e.getMessage());
    logger.error(e.getMessage(), e);
  }
}
```

And in the `main()` method, we call `initDB()`:

```java
	public static void main(String[] args) {
    initDB();
    [...]
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

# Call the DAO from the API

