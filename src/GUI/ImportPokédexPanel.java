package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ImportPokédexPanel extends JPanel {
    
    ImportPokédexPanel() {
	this.setOpaque(false);
	
	JButton importButton = new JButton("Import");
	this.add(importButton);
    }
}
