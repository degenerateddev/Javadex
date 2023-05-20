package types;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;

enum Elements {
    WATER,
    FIRE,
    EARTH
}

enum Attacks {
    TACKLE
}

public class Pokémon {
    private final int id;
    public final String name;
    public final String description;
    public final String image;
    public final String sound;
    public final Elements element;
    public final Attacks[] attacks;
    public final int stage;
    public final Pokémon[] stages;
    public int health = 100;
    
    public Pokémon(int id, String name, String description, String image, String sound, int health, Elements element, Attacks[] attacks, int stage, Pokémon[] stages) {
	//this.id = getID();
	this.id = id;
	this.name = name;
	this.description = description;
	this.image = image;
	this.sound = sound;
	this.health = health;
	this.element = element;
	this.attacks = attacks;
	this.stages = stages;
	this.stage = stage;
    }
    
    private int getID() {
	DB db = new DB();
	int length;
	
	try {
	    length = db.getAll().getFetchSize();
	    return length;
	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
	return 0;
    }
}    