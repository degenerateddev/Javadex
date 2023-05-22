package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import database.DB;

public class OpenTeamPanel extends JPanel {
    DB db;
    
    OpenTeamPanel(DB db) {
	this.setOpaque(false);
	
	JButton teamButton = new JButton("Open Team");
	this.add(teamButton);
    }
}
