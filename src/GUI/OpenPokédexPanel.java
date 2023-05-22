package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import database.DB;

public class OpenPokédexPanel extends JPanel {
    DB db;
    
    OpenPokédexPanel(DB db) {
	this.setOpaque(false);
	
	JButton pokémonButton = new JButton("Open");
	this.add(pokémonButton);
    }
}
