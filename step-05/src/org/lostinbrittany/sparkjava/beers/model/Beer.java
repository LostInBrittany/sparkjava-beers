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

	
	public static List<Beer> getBeers() {

		ArrayList<Beer> list = new ArrayList<Beer>();
		
		 try
	        {
	            Class.forName("org.h2.Driver");
	            Connection con = DriverManager.getConnection("jdbc:h2:mem:d1");
	            Statement stmt = con.createStatement();
	            
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
	            con.close();
	        }
	        catch( Exception e )
	        {
	            System.out.println( e.getMessage() );
	        }  
		 return list;
	}

	
	
	
}
