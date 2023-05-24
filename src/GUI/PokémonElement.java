package GUI;

import javax.swing.JComponent;

import types.Pokémon;

public class PokémonElement extends JComponent {
    Pokémon pokémon;
    
    PokémonElement(Pokémon pokémon) {
	this.pokémon = pokémon;
    }
}
