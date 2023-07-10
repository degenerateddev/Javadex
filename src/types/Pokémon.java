package types;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DB;


public class Pokémon {
    public final String name;
    public final String description;
    public final String image;
    public final String sound;
    public final Elements[] elements;
    public final Attacks[] attacks;
    public final int stage;	// current stage index (0 for first evolution stage)
    public final int[] stages;	// db pokémon IDs as references
    public int health = 100;
    
    public enum Elements {
	WATER,
	FIRE,
	ROCK,
    DARK,
    DRAGON,
    STEEL,
    FAIRY,
    GHOST,
    PSYCHIC,
    NORMAL,
    FIGHTING,
    POISON,
    GROUND,
    ELECTRIC,
    ICE,
    BUG,
    FLYING,
    GRASS
    }

    public enum Attacks {
	TACKLE
    }
    
    public Pokémon(String name, String description, String image, String sound, int health, Elements[] elements, Attacks[] attacks, int stage, int[] stages) {
	//this.id = getID();
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