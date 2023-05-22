package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AddToPokédexPanel extends JPanel {
    
    AddToPokédexPanel() {
	JButton pokémonButton = new JButton("Pokémon");
	this.add(pokémonButton);
    }
}
