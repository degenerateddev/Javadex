package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
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
    
    public DB() {
	try {
	    connection = DriverManager.getConnection(dbUrl, username, password);
	    System.out.println("Database connection established...");
	    
	    try (ResultSet resultSet = connection.getMetaData().getTables(null, null, "pokedex", null)) {
		if (resultSet.next()) {
		    System.out.println("Table already exists.");
	            return;
	        } else {
	            setup("pokedex");
	        }
		
	    } catch (SQLException e) {
		System.out.println("Failed to check if the table exists.");
	        e.printStackTrace();
	        return;
	    }
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
    
    private void setup(String table) {
	if (table.equals("pokedex")) {
	    String sql = "CREATE TABLE pokemon (" +
		    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
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
	    
	    
	}
    }
    
    public void disconnect() {
	try {
	    connection.close();
	} catch (SQLException e) {
	    e.printStackTrace();
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
    
    public static ResultSet getAll() {
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
    
    public static void add(Pokémon pokémon) {
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
    
    public static void remove(Pokémon pokémon) {
	String sql = "DELETE FROM pokedex WHERE id=";
    }
}
