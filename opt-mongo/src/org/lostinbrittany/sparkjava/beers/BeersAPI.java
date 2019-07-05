package org.lostinbrittany.sparkjava.beers;

import static spark.Spark.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.lostinbrittany.sparkjava.dao.BeerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class BeersAPI {

	public static Logger logger = LoggerFactory.getLogger(BeersAPI.class);

	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> beers;
	
	public static void main(String[] args) {

		staticFileLocation("/static"); // Static files

		get("/BeerList", (request, response) -> {
			return BeerDAO.getBeerList();
		});
		
		get("/Beer/:beerId",(request, response) -> {
			return BeerDAO.getBeer(request.params(":beerId"));
		});

	}
}