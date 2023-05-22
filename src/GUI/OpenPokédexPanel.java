package GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class OpenPokédexPanel extends JPanel {
    
    OpenPokédexPanel() {
	// tried doing transparent background but made no difference
	this.setOpaque(false);
	//this.setBackground(new Color(255, 255, 255, 0));
	
	JButton pokémonButton = new JButton("Pokémon");
	this.add(pokémonButton);
    }
}
