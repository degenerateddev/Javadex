package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import database.DB;

public class ExportPokédexPanel extends JPanel {
    DB db;
    
    ExportPokédexPanel(DB db) {
	JButton exportButton = new JButton("Export");
	this.add(exportButton);
    }
}
