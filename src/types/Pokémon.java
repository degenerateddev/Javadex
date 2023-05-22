package types;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;


public class Pokémon {
    public final int id;
    public final String name;
    public final String description;
    public final String image;
    public final String sound;
    public final Elements[] elements;
    public final Attacks[] attacks;
    public final int stage;
    public final Pokémon[] stages;
    public int health = 100;
    
    public enum Elements {
	WATER,
	FIRE,
	EARTH
    }

    public enum Attacks {
	TACKLE
    }
    
    public Pokémon(int id, String name, String description, String image, String sound, int health, Elements elements[], Attacks[] attacks, int stage, Pokémon[] stages) {
	//this.id = getID();
	this.id = id;
	this.name = name;
	this.description = description;
	this.image = image;
	this.sound = sound;
	this.health = health;
	this.elements = elements;
	this.attacks = attacks;
	this.stages = stages;
	this.stage = stage;
    }
    
//    private int getID() {
//	DB db = new DB();
//	int length;
//	
//	try {
//	    length = db.getAllDB().getFetchSize();
//	    return length;
//	    
//	} catch (SQLException e) {
//	    e.printStackTrace();
//	}
//	
//	return 0;
//    }
}    