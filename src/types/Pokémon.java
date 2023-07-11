package types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DB;


public class Pokémon {
    public int id;
    public String name;
    public String description;
    public String image;
    public String sound;
    public List<Elements> elements;
    public List<Attacks> attacks;
    public int level;
    public int stage;		// current stage index (1 for first evolution stage)
    public int[] stages;	// db pokémon IDs as references
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
    
    public Pokémon() {
	
    }
    
    public Pokémon(int id, String name, String description, String image, String sound, int level, int health, List<Elements> elements, List<Attacks> attacks, int stage, int[] stages) {
	this.name = name;
	this.description = description;
	this.image = image;
	this.sound = sound;
	this.level = level;
	this.health = health;
	this.elements = elements;
	this.attacks = attacks;
	this.stages = stages;
	this.stage = stage;
    }
    
    @Override
    public String toString() {
	try {
	    return this.name + " of element " + this.elements.get(0) + " is level " + this.level + " and has " + this.health + " health";
	} catch (ArrayIndexOutOfBoundsException e) {
	    return this.name + " is level " + this.level + " and has " + this.health + " health";
	}
    }
}    