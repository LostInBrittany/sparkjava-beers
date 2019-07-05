# Optional Step - Using a SQL DB 

## Our initialization is ugly 

Initializing the beers in the `Beer` class is not a nice way to do it, it's even one of the worse ways. In this step we are going to replace that procedure with a SQL database. If you already have a SQL database (MySQL, MariaDB...) in your computer, you can use it. If not, we are explaining here an alternative way using an embedded java database: [h2](http://www.h2database.com/).

Let's begin by adding the H2 dependency to our `build.gradle` and then executing `gradle eclipse`:


		dependencies {
		 	compile group: 'com.sparkjava', name: 'spark-core', version: '2.7.2'
			compile group: 'org.slf4j', name: 'slf4j-simple', version: '1.6.1'	
		 	compile group: 'com.google.code.gson', name: 'gson', version: '2.8.5'
		 	compile group: 'com.h2database', name: 'h2', version: '1.4.197'
		}
		
We are going to use [h2](http://www.h2database.com/) as an *in-memory* database, i.e. a database where the data isn't written on disk, it remains in memory and when the application is shut down all the data is lost. With this configuration, we need a way to initialize the database at each restart of the application, we are creating an utility `BeerInitialize` class with a `initBeerDb` method:

`BeersAPI.java`


		package org.lostinbrittany.sparkjava.beers;
		
		/*
		 ...
		 */
		
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
				
				/*
				 ...
				 */
		
			}
		}




`BeerInitialize.java`:


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
		
		
		
## So now we have a database, let's use it 		
		
We are going to modify the loading of the beer list to use the database. We must change the `getBeers()` method in the `Beer` class to
make it do a SQL `SELECT` request to recover the full list of beers:


`Beer.java`:

		public class Beer {
		
			/*
			 ...
			 */
		
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