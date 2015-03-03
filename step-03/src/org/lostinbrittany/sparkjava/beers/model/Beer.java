package org.lostinbrittany.sparkjava.beers.model;

import java.util.ArrayList;
import java.util.List;

public class Beer {

	/*
	 * Each beer was defined by
	 * 
	 * { "alcohol": 6.8, "description":
	 * "Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable."
	 * , "id": "AffligemBlond", "img": "static/img/AffligemBlond.jpg", "name":
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

		Beer beer;

		beer = new Beer();
		beer.setAlcohol(6.8);
		beer.setDescription("Affligem Blonde, the classic clear blonde abbey ale, with a gentle roundness and 6.8% alcohol. Low on bitterness, it is eminently drinkable.");
		beer.setId("AffligemBlond");
		beer.setImg("static/img/AffligemBlond.jpg");
		beer.setName("Affligem Blond");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(6.8);
		beer.setDescription("A reddish-brown abbey ale brewed with dark malts. The secondary fermentation gives a fruity aroma and a unique spicy character with a distinctive aftertaste. Secondary fermentation in the bottle.");
		beer.setId("AffligemDubbel");
		beer.setImg("static/img/AffligemDubbel.jpg");
		beer.setName("Affligem Dubbel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(8.5);
		beer.setDescription("The king of the abbey beers. It is amber-gold and pours with a deep head and original aroma, delivering a complex, full bodied flavour. Pure enjoyment! Secondary fermentation in the bottle.");
		beer.setId("AffligemTripel");
		beer.setImg("static/img/AffligemTripel.jpg");
		beer.setName("Affligem Tripel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(7.5);
		beer.setDescription("Trappistes Rochefort 6 Belgian Beer.");
		beer.setId("TrappistesRochefort6");
		beer.setImg("static/img/TrappistesRochefort6.jpg");
		beer.setName("Rochefort 6");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(9.2);
		beer.setDescription("A dry but rich flavoured beer with complex fruity and spicy flavours.");
		beer.setId("TrappistesRochefort8");
		beer.setImg("static/img/TrappistesRochefort8.jpg");
		beer.setName("Rochefort 8");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(11.3);
		beer.setDescription("The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.");
		beer.setId("TrappistesRochefort10");
		beer.setImg("static/img/TrappistesRochefort10.jpg");
		beer.setName("Rochefort 10");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(6.7);
		beer.setDescription("This name became a reference. This beer is mostly pointed out with its product name: a Paterke. This Paterke is a dark, chestnut coloured beer with a high fermentation (6.7%) and a full taste");
		beer.setId("StBernardusPater6");
		beer.setImg("static/img/StBernardusPater6.jpg");
		beer.setName("St Bernardus Pater 6");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(8.0);
		beer.setDescription("This beer, with high fermentation, has a pale amber colour and a flowery, fruity taste with a harmonious balance between sweet and sour. This beer has a thick and vivid froth and strikes its balanced taste with a delicate bitterness.");
		beer.setId("StBernardusTripel");
		beer.setImg("static/img/StBernardusTripel.jpg");
		beer.setName("St Bernardus Tripel");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(10.5);
		beer.setDescription("The top product from the Rochefort Trappist brewery. Dark colour, full and very impressive taste. Strong plum, raisin, and black currant palate, with ascending notes of vinousness and other complexities.");
		beer.setId("StBernardusAbt12");
		beer.setImg("static/img/StBernardusAbt12.jpg");
		beer.setName("St Bernardus Abt 12");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(7);
		beer.setDescription("This Trappist beer possesses a beautiful coppery colour that makes it particularly attractive. Topped with a creamy head, it gives off a slight fruity apricot smell from the fermentation. The aroma felt in the mouth is a balance confirming the fruit nuances revealed to the sense of smell. This traditional Belgian beer is best savoured at cellar temperature ");
		beer.setId("ChimayRed");
		beer.setImg("static/img/ChimayRed.jpg");
		beer.setName("Chimay Rouge");
		list.add(beer);

		beer = new Beer();
		beer.setAlcohol(10.5);
		beer.setDescription("Chimay Triple, with its typical golden colour, its slightly hazy appearance and its fine head is especially characterised by its aroma which results from an agreeable combination of fresh hops and yeast. The beers flavour, as sensed in the mouth, comes from the smell of hops: above all it is the fruity notes of muscat and raisins that give this beer a particularly attractive aroma. The aroma complements the touch of bitterness. There is no acidity, but an after-bitterness which melts in the mouth. This top fermented Trappist beer, refermented in the bottle, is not pasteurised.");
		beer.setId("ChimayTriple");
		beer.setImg("static/img/ChimayTriple.jpg");
		beer.setName("Chimay Tripel");
		list.add(beer);

		return list;
	}
}
