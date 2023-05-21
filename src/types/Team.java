package types;

public class Team {
    private final int id;
    public final String name;
    public final Pokémon members;
    
    public Team(int id, String name, Pokémon members) {
	this.id = id;
	this.name = name;
	this.members = members;
    }
}
