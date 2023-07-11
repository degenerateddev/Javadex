package types;

public class Team {
    private int id;
    public String name;
    public Pokémon[] members;
    
    public Team() {
	
    }
    
    public Team(int id, String name, Pokémon[] members) {
	this.id = id;
	this.name = name;
	this.members = members;
    }
}
