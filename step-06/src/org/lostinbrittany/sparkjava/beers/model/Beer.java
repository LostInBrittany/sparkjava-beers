package org.lostinbrittany.sparkjava.beers.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
 

public class Beer {

	/*
	 * Each beer was defined by
	 * 
	 * { "alcohol": 6.8, "description":
	 * "Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable."
	 * , "id": "AffligemBlond", "img": "img/AffligemBlond.jpg", "name":
	 * "Affligem Blond" }
	 */

	private String name;
	private String id;
	private String img;
	private String description;
	private double alcohol;
	
	

	private String availability;
	private String brewery;
	private String label;
	private String serving;
	private String style;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(double alcohol) {
		this.alcohol = alcohol;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getBrewery() {
		return brewery;
	}

	public void setBrewery(String brewery) {
		this.brewery = brewery;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getServing() {
		return serving;
	}

	public void setServing(String serving) {
		this.serving = serving;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public static Beer getBeer(String beerId, Connection conn) {
		Beer beer = null;
		 try
	        {
	            Statement stmt = conn.createStatement();
	            
	          //query to database
				try {

					ResultSet rs = stmt.executeQuery("SELECT * FROM beers WHERE id='"+beerId+"'");
					while (rs.next()) {
	 					beer = new Beer();
						beer.setId(rs.getString(1));
						beer.setName(rs.getString(2));
						beer.setImg(rs.getString(3));
						beer.setDescription(rs.getString(4));
						beer.setAlcohol(rs.getDouble(5));
						beer.setAvailability(rs.getString(6));
						beer.setBrewery(rs.getString(7));
						beer.setLabel(rs.getString(8));
						beer.setServing(rs.getString(9));
						beer.setStyle(rs.getString(10));

			        	Gson gson = new Gson();
			        	System.out.println(gson.toJson(beer));  
			        					
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

	            stmt.close();
	        }
	        catch( Exception e )
	        {
	            System.out.println( e.getMessage() );
	        }  
		return beer;
	}
	
	public static List<Beer> getBeers(Connection conn) {

		ArrayList<Beer> list = new ArrayList<Beer>();
		
		 try
	        {
	            Statement stmt = conn.createStatement();
	            
	          //query to database
				try {

					Beer beer;
					ResultSet rs = stmt.executeQuery("SELECT * FROM beers");
					while (rs.next()) {
	 

						beer = new Beer();
						beer.setId(rs.getString(1));
						beer.setName(rs.getString(2));
						beer.setImg(rs.getString(3));
						beer.setDescription(rs.getString(4));
						beer.setAlcohol(rs.getDouble(5));

			        	Gson gson = new Gson();
			        	System.out.println(gson.toJson(beer));  
			        	
						list.add(beer);					
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

	            stmt.close();
	        }
	        catch( Exception e )
	        {
	            System.out.println( e.getMessage() );
	        }  
		 return list;
	}

	
	
	
}
