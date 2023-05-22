package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import types.Pokémon;

public class DB {
    String db = readEnv("POSTGRES_DB");
    String username = readEnv("POSTGRES_USER");
    String password = readEnv("POSTGRES_PASSWORD");
    
    String dbUrl = "jdbc:postgresql://0.0.0.0:1337/" + db;
    
    static Connection connection;
    boolean connected = false;
    
    public File fallbackFile = new File("data/fallbackFile.txt");
    
    public DB() {
	try {
	    connection = DriverManager.getConnection(dbUrl, username, password);
	    System.out.println("Database connection established...");
	    
	    DatabaseMetaData metadata = connection.getMetaData();
	    ResultSet pokedexResults = metadata.getTables(null, null, "pokedex", null);
	    if (!pokedexResults.next()) {
		System.out.println("Pokedex table exists. Skipping...");
		
	    } else {
		System.out.println("Creating pokedex table...");
		setupDB("pokedex");
	    }
	    
	    ResultSet teamsResults = metadata.getTables(null, null, "teams", null);
	    if (!teamsResults.next()) {
		System.out.println("Teams table exists. Skipping...");
		
	    } else {
		System.out.println("Creating teams table...");
		setupDB("teams");
	    }
	    
	    connected = true;
	    
	} catch (SQLException e) {
	    System.out.println("Could not find running PostgreSQL database...");
	    System.out.println("Using fallback text file");
	    
	    try {
		if (fallbackFile.createNewFile()) {
		    System.out.println("Fallback file created");
		} else {
		    System.out.println("Fallback file found");
		}
		
	    } catch (IOException e1) {
		e1.printStackTrace();
		System.exit(0);
	    }
	}
    }
    
    /*
     * Database setup and base methods
     */
    
    private void setupDB(String table) {
	if (table.equals("pokedex")) {
	    String sql = "CREATE TABLE pokemon (" +
		    "id SERIAL PRIMARY KEY," +
		    "name VARCHAR(255) NOT NULL," +
		    "description VARCHAR(255) NOT NULL," +
		    "image VARCHAR(255) NOT NULL," +
		    "sound VARCHAR(255) NOT NULL," +
		    "health INT," +
		    "element VARCHAR(255) NOT NULL," +
		    "stage INT," +
		    "FOREIGN KEY (stage) REFERENCES pokemon(id)" +
		    ")";
	    
	    try (Statement statement = connection.createStatement()) {
		statement.executeUpdate(sql);
		System.out.println("Pokemon table created successfully!");
		
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    
	} else if (table.equals("teams")) {
	    String sql = "CREATE TABLE team (" +
		    "id SERIAL PRIMARY KEY," +
		    "name VARCHAR(255) NOT NULL," +
		    "pokemon_id INT," +
		    "FOREIGN KEY (pokemon_id) REFERENCES pokemon(id)" +
		    ")";
	    
	    try (Statement statement = connection.createStatement()) {
		statement.executeUpdate(sql);
		System.out.println("Pokemon table created successfully!");
		
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    
	}
    }
    
    public void disconnectDB() {
	if (connected) {
	    try {
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }
    
    private String readEnv(String query) {
	String result = "";
	BufferedReader reader;
	
	try {
	    reader = new BufferedReader(new FileReader(".env"));
	    String line = reader.readLine();
	    
	    while (line != null) {
		String[] data = line.split("=");
		String key = data[0];
		String value = data[1];
		
		if (key.equals(query)) {
		    reader.close();
		    
		    return value;
		}
		
		line = reader.readLine();
	    }
	    
	    reader.close();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	return result;
    }
    
    /*
     * Database CRUD operations
     */
    
    public static ResultSet getAllDB() {
	String sql = "SELECT * FROM pokedex";
	
	try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    ResultSet data = statement.executeQuery(sql);
	    System.out.println(data);
	    return data;
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return null;
    }
    
    public static void addDB(Pokémon pokémon) {
	String sql = "INSERT INTO pokedex (name, description, image, sound) VALUES (?, ?, ?)";
	String name = pokémon.name;
	String description = pokémon.description;
	String image = pokémon.image;
	String sound = pokémon.sound;
	
	try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setString(1, name);
	    statement.setString(2, description);
	    statement.setString(3, image);
	    statement.setString(4, sound);
	    int rowsAffected = statement.executeUpdate();
	    System.out.println("Changed " + rowsAffected + " row(s)");
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    public static void removeDB(Pokémon pokémon) {
	String sql = "DELETE FROM pokedex WHERE id=" + pokémon.id;
    }
    
    /*
     * Fallback file methods
     */
    
    public static void getAllFile(Pokémon pokémon) {
	
    }
    
    public static void addFile(Pokémon pokémon) {
	
    }
    
    public static void removeFile(Pokémon pokémon) {
	
    }
    
    /*
     * Data exporting
     */
    
    public static void exportDB() {
	
    }
    
    public static void exportFile() {
	
    }
}
