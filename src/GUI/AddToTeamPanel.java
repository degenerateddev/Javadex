package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import database.DB;

public class AddToTeamPanel extends JPanel {
    DB db;
    
    AddToTeamPanel(DB db) {
	this.setOpaque(false);
	
	JButton pokémonButton = new JButton("Add to Team");
	this.add(pokémonButton);
    }
}
