package org.lostinbrittany.sparkjava.beers;

import static spark.Spark.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.lostinbrittany.sparkjava.beers.model.Beer;
import org.lostinbrittany.sparkjava.tools.BeerInitialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class BeersAPI {

	public static Logger logger = LoggerFactory.getLogger(BeersAPI.class);

	private static Connection conn;

	private static void initDB() {

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:mem:d1");

			BeerInitialize.initBeerDb(conn);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		}
	}

	private static void closeDb() {

		try {
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {

		initDB();

		staticFileLocation("/static"); // Static files

		get("/BeerList", (request, response) -> {
			List<Beer> beerList = Beer.getBeers(conn);
			Gson gson = new Gson();
			String json = gson.toJson(beerList);
			return json;
		});

	}
}