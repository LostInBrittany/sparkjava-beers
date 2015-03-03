# Step 04 - Using a SQL DB #

## Our initialization is ugly ##

Initializing the beers in the `Beer` class is not a nice way to do it, it's even one of the worse ways. In this step we are going to replace that procedure with a SQL database. If you already have a SQL database (MySQL, MariaDB...) in your computer, you can use it. If not, we are explaining here an alternative way using an embedded java database: [h2](http://www.h2database.com/).

Let's begin by adding the H2 dependency to our `build.gradle` and then executing `gradle eclipse`:


		dependencies {
		 	compile group: 'com.sparkjava', name: 'spark-core', version: '2.1'
		 	compile group: 'com.google.code.gson', name: 'gson', version: '2.3.1'
		 	compile group: 'com.h2database', name: 'h2', version: '1.4.185'
		}
		
We are going to use [h2](http://www.h2database.com/) as an *in-memory* database, i.e. a database where the data isn't written on disk, it remains in memory and when the application is shut down all the data is lost. With this configuration, we need a way to initialize the database at each restart of the application, we are creating an utility `BeerInitialize` class with a `initBeerDb` method:

		public static void initBeerDb() {
			logger.info("Ready to init DB");
			
			 try
		        {
		            Class.forName("org.h2.Driver");
		            Connection con = DriverManager.getConnection("jdbc:h2:mem:d1");
		            Statement stmt = con.createStatement();
		            stmt.executeUpdate( "CREATE TABLE beers ( id varchar(50), name varchar(100), image varchar(100), description varchar(1000), alcohol decimal(3,1))" );	 
		
		            logger.info("Table created");
		            //prepared statement
					PreparedStatement prep = con.prepareStatement("INSERT INTO beers (id, name, image, description, alcohol) VALUES (?,?,?,?,?)");
		 
					
		            for (Beer beer: BeerInitialize.getBeers()) {
		            	prep.setString(1, beer.getId());
		            	prep.setString(2, beer.getName());
		            	prep.setString(3, beer.getImg());
		            	prep.setString(4, beer.getDescription());
						prep.setDouble(5, beer.getAlcohol());
		 
						//batch insert
						prep.addBatch();
		            	
		            }
					con.setAutoCommit(false);
					prep.executeBatch();
					con.setAutoCommit(true);
		            
				    logger.info("Ready to query");
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
		            logger.error(e.getMessage(), e);
		        }
		}	 	
		
		
		
## So now we have a database, let\'s use it ##		
		
We are going to modify the loading of the beer list to use the database. We must change the `getBeers()` method in the `Beer` class to
make it do a SQL `SELECT` request to recover the full list of beers:



		public class Beer {
		
			/*
			 ...
			 */
		
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