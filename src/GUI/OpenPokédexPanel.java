package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import database.DB;

public class OpenPokédexPanel extends JPanel {
    DB db;
    
    OpenPokédexPanel(DB db) {
	this.setOpaque(false);
	
	JButton pokémonButton = new JButton("Open Pokédex");
	this.add(pokémonButton);
    }
}
