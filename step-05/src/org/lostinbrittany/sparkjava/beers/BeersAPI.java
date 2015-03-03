package org.lostinbrittany.sparkjava.beers;

import static spark.Spark.*;

import java.util.List;

import org.lostinbrittany.sparkjava.beers.model.Beer;
import org.lostinbrittany.sparkjava.tools.BeerInitialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


public class BeersAPI {
	
	public static Logger logger = LoggerFactory.getLogger(BeersAPI.class);
	
    public static void main(String[] args) {


	    
    	BeerInitialize.initBeerDb();
    	
        staticFileLocation("/static"); // Static files
        
        get("/BeerList", (request, response) -> {
        	List<Beer> beerList = Beer.getBeers();
        	Gson gson = new Gson();
        	String json = gson.toJson(beerList);  
        	return json;
        });
        
    }
}