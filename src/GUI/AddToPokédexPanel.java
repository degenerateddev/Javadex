package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import database.DB;

public class AddToPokédexPanel extends JPanel {
    DB db;
    
    AddToPokédexPanel(DB db) {
	this.setOpaque(false);
	
	JButton pokémonButton = new JButton("Add");
	this.add(pokémonButton);
    }
}
