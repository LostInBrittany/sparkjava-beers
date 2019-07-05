package org.lostinbrittany.beers.dao;

import org.bson.Document;
import org.lostinbrittany.beers.model.Beer;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class BeerDAO {

	public static Gson gson = new Gson();
	

	public static String getBeer(String id) {

		MongoClient mongoClient = new MongoClient();
		
		MongoDatabase database = mongoClient.getDatabase("beers");
		
		MongoCollection<Document> beers = database.getCollection("beers");
		
		Document beerDocument = beers.find(Filters.eq("id", id)).first();
		
		Beer beer = gson.fromJson(beerDocument.toJson(), Beer.class);
		
		return beer.toJSONDetail();
	}
	
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