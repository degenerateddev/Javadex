package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import types.Pokémon;
import types.Pokémon.Attacks;
import types.Pokémon.Elements;
import utils.Conversion;

public class DB {
    String db = readEnv("SQLITE_DB");
    String username = readEnv("SQLITE_USER");
    String password = readEnv("SQLITE_PASSWORD");
    
    String dbUrl = "jdbc:sqlite:" + db;
    
    static Connection connection;
    
    private boolean connected;
    
    public DB() {
	try {
	    connection = DriverManager.getConnection(dbUrl, username, password);
	    System.out.println("Database connection established...");
	    connected = true;
	    
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
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
    }
    
    /*
     * Database setup and base methods
     */
    
    private void setupDB(String table) {
	if (table.equals("pokedex")) {
	    String sql = "CREATE TABLE IF NOT EXISTS pokemon (" +
		    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
		    "name TEXT NOT NULL," +
		    "description TEXT NOT NULL," +
		    "image TEXT NOT NULL," +
		    "sound TEXT NOT NULL," +
		    "level INTEGER," +
		    "health INTEGER," +
		    "elements TEXT NOT NULL," +
		    "attacks, TEXT NOT NULL," +
		    "stage INTEGER," +
		    "stages TEXT" +	// comma separated list of IDs to pokemons
		    ")";
	    
	    try (Statement statement = connection.createStatement()) {
	    	statement.executeUpdate(sql);
	    	System.out.println("Pokemon table created successfully!");
		
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    
	} else if (table.equals("teams")) {
	    String sql = "CREATE TABLE IF NOT EXISTS team (" +
		    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
		    "name TEXT NOT NULL," +
		    "member_id INTEGER," +
		    "FOREIGN KEY (member_id) REFERENCES pokemon(id)" +
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
		System.out.println("Could not find .env file!");
	    return "";
	}
	
	return result;
    }
    
    /*
     * Database CRUD operations
     */
    
    public ResultSet getAllDB() {
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
    
    public ResultSet getAllTeamsDB() {
	String sql = "SELECT * FROM teams";
	
	try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    ResultSet data = statement.executeQuery(sql);
	    System.out.println(data);
	    return data;
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return null;
    }
    
    public ResultSet getPokémon(int id) {
	String sql = "SELECT * FROM pokedex WHERE id=?";
	
	try (PreparedStatement statement = connection.prepareStatement(sql)) {
	    statement.setInt(1, id);
	    
	    ResultSet data = statement.executeQuery(sql);
	    System.out.println(data);
	    return data;
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return null;
    }
    
    public void addDB(Pokémon pokémon) {
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
    
    public void removeDB(int ID) {
	String sql = "DELETE FROM pokedex WHERE id=" + Integer.toString(ID);
    }
    
    /*
     * Fallback file methods
     */
    
    /*public static ArrayList<Pokémon> getAllFile() {
	ArrayList<Pokémon> allPokémon = new ArrayList<Pokémon>();
	BufferedReader reader;
	
	try {
	    reader = new BufferedReader(new FileReader(fallbackFile));
	    String line = reader.readLine();
	    
	    while (line != null) {
		//"Pikachu#Pikapikadescription#/home/user/image.png#/home/user/sound.wav#100#[WATER, EARTH]#[TACKLE, TESTATTACK]#0#[1337, 69]"
		String[] data = line.split("#");
		
		String name = data[0];
		String description = data[1];
		String image = data[2];
		String sound = data[3];
		int health = Integer.parseInt(data[4]);
		
		Pokémon.Elements[] elements = {};
		String[] convertedElements = Conversion.stringToArray(data[5]);
		for (int i = 0; i < convertedElements.length; i++) {
		    elements[i] = Pokémon.Elements.valueOf(convertedElements[i]);
		}
		
		Pokémon.Attacks[] attacks = {};
		String[] convertedAttacks = Conversion.stringToArray(data[5]);
		for (int i = 0; i < convertedAttacks.length; i++) {
		    attacks[i] = Pokémon.Attacks.valueOf(convertedAttacks[i]);
		}
		
		int stage = Integer.parseInt(data[7]);
		
		int[] stages = {};
		String[] convertedStages = Conversion.stringToArray(data[8]);
		for (int i = 0; i < convertedAttacks.length; i++) {
		    stages[i] = Integer.parseInt(convertedStages[i]);
		}
		
		Pokémon pokémon = new Pokémon(name, description, image, sound, health, elements, attacks, stage, stages);
		allPokémon.add(pokémon);
		
		line = reader.readLine();
	    }
	    
	    reader.close();
	    
	    return allPokémon;
	    
	} catch (IOException e) {
	    e.printStackTrace();
	    
	    return allPokémon;
	}
    }*/
    
    /*
     * Data exporting
     */
    
    public void exportDB() {
	
    }
}
