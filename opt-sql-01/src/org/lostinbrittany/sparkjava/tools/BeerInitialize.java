package org.lostinbrittany.sparkjava.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.lostinbrittany.sparkjava.beers.model.Beer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class BeerInitialize {

	public static Logger logger = LoggerFactory.getLogger(BeerInitialize.class);

	public static void initBeerDb(Connection conn) {
		logger.info("Ready to init DB");

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE beers ( id varchar(50), name varchar(100), image varchar(100), description varchar(1000), alcohol decimal(3,1))");

			logger.info("Table created");
			// prepared statement
			PreparedStatement prep = conn
					.prepareStatement("INSERT INTO beers (id, name, image, description, alcohol) VALUES (?,?,?,?,?)");

			for (Beer beer : BeerInitialize.getBeers()) {
				prep.setString(1, beer.getId());
				prep.setString(2, beer.getName());
				prep.setString(3, beer.getImg());
				prep.setString(4, beer.getDescription());
				prep.setDouble(5, beer.getAlcohol());

				// batch insert
				prep.addBatch();

			}
			conn.setAutoCommit(false);
			prep.executeBatch();
			conn.setAutoCommit(true);

			logger.info("Ready to query");
			// query to database
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

				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			logger.error(e.getMessage(), e);
		}
	}

	private static List<Beer> getBeers() {
		ArrayList<Beer> list = new ArrayList<Beer>();

		Beer beer;

		beer = new Beer();
		beer.setAlcohol(6.8);
		beer.setDescription("Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.");
		beer.setId("AffligemBlond");
		beer.setImg("img/AffligemBlond.jpg");
		beer.setName("Affligem Blond");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(6.8);
		beer.setDescription("A reddish-brown abbey ale brewed with dark malts. The secondary fermentation gives a fruity aroma and a unique spicy character with a distinctive aftertaste. Secondary fermentation in the bottle.");
		beer.setId("AffligemDubbel");
		beer.setImg("img/AffligemDubbel.jpg");
		beer.setName("Affligem Dubbel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(8.5);
		beer.setDescription("The king of the abbey beers. It is amber-gold and pours with a deep head and original aroma, delivering a complex, full bodied flavour. Pure enjoyment! Secondary fermentation in the bottle.");
		beer.setId("AffligemTripel");
		beer.setImg("img/AffligemTripel.jpg");
		beer.setName("Affligem Tripel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(7.5);
		beer.setDescription("Trappistes Rochefort 6 Belgian Beer.");
		beer.setId("TrappistesRochefort6");
		beer.setImg("img/TrappistesRochefort6.jpg");
		beer.setName("Rochefort 6");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(9.2);
		beer.setDescription("A dry but rich flavoured beer with complex fruity and spicy flavours.");
		beer.setId("TrappistesRochefort8");
		beer.setImg("img/TrappistesRochefort8.jpg");
		beer.setName("Rochefort 8");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(11.3);
		beer.setDescription("The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.");
		beer.setId("TrappistesRochefort10");
		beer.setImg("img/TrappistesRochefort10.jpg");
		beer.setName("Rochefort 10");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(6.7);
		beer.setDescription("This name became a reference. This beer is mostly pointed out with its product name: a Paterke. This Paterke is a dark, chestnut coloured beer with a high fermentation (6.7%) and a full taste");
		beer.setId("StBernardusPater6");
		beer.setImg("img/StBernardusPater6.jpg");
		beer.setName("St Bernardus Pater 6");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(8.0);
		beer.setDescription("This beer, with high fermentation, has a pale amber colour and a flowery, fruity taste with a harmonious balance between sweet and sour. This beer has a thick and vivid froth and strikes its balanced taste with a delicate bitterness.");
		beer.setId("StBernardusTripel");
		beer.setImg("img/StBernardusTripel.jpg");
		beer.setName("St Bernardus Tripel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(10.5);
		beer.setDescription("The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.");
		beer.setId("StBernardusAbt12");
		beer.setImg("img/StBernardusAbt12.jpg");
		beer.setName("St Bernardus Abt 12");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(7);
		beer.setDescription("This Trappist beer possesses a beautiful coppery colour that makes it particularly attractive. Topped with a creamy head, it gives off a slight fruity apricot smell from the fermentation. The aroma felt in the mouth is a balance confirming the fruit nuances revealed to the sense of smell. This traditional Belgian beer is best savoured at cellar temperature ");
		beer.setId("ChimayRed");
		beer.setImg("img/ChimayRed.jpg");
		beer.setName("Chimay Rouge");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(10.5);
		beer.setDescription("Chimay Triple, with its typical golden colour, its slightly hazy appearance and its fine head is especially characterised by its aroma which results from an agreeable combination of fresh hops and yeast. The beers flavour, as sensed in the mouth, comes from the smell of hops: above all it is the fruity notes of muscat and raisins that give this beer a particularly attractive aroma. The aroma complements the touch of bitterness. There is no acidity, but an after-bitterness which melts in the mouth. This top fermented Trappist beer, refermented in the bottle, is not pasteurised.");
		beer.setId("ChimayTriple");
		beer.setImg("img/ChimayTriple.jpg");
		beer.setName("Chimay Tripel");
		list.add(beer);

		return list;
	}

}
