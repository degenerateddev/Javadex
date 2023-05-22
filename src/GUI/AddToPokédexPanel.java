package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import database.DB;
import types.Pokémon;

public class AddToPokédexPanel extends JPanel {
    DB db;
    Pokémon pokémon;
    
    AddToPokédexPanel(DB db) {
	this.setLayout(new GridBagLayout());
	this.setOpaque(false);
	
	GridBagConstraints constraints = new GridBagConstraints();
	
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setForeground(Color.WHITE);
        this.add(nameLabel, constraints);
	JTextField name = new JTextField();
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setColumns(20);
        this.add(name, constraints);
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel.setForeground(Color.WHITE);
        this.add(descriptionLabel, constraints);
	JTextField description = new JTextField();
	description.setFont(new Font("Arial", Font.PLAIN, 15));
        description.setColumns(40);
        this.add(description, constraints);
        
        JLabel elementsLabel = new JLabel("Elements:");
        elementsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        elementsLabel.setForeground(Color.WHITE);
        this.add(elementsLabel);
        JComboBox<Pokémon.Elements> elementsChoice = new JComboBox<Pokémon.Elements>(Pokémon.Elements.values());
        this.add(elementsChoice, constraints);
        
        JLabel attacksLabel = new JLabel("Attacks:");
        attacksLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        attacksLabel.setForeground(Color.WHITE);
        this.add(attacksLabel);
        JComboBox<Pokémon.Attacks> attacksChoice = new JComboBox<Pokémon.Attacks>(Pokémon.Attacks.values());
        this.add(attacksChoice, constraints);
        
        JButton chooseImageButton = new JButton("Select image");
        this.add(chooseImageButton, constraints);
        
        JButton chooseSoundButton = new JButton("Select sound");
        this.add(chooseSoundButton, constraints);
	
	JButton submitButton = new JButton("Add to Pokédex");
	this.add(submitButton, constraints);
	
	chooseImageButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooseImage = new JFileChooser("Image");
		int result = chooseImage.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    File image = new File(chooseImage.getSelectedFile().getAbsolutePath());
		    System.out.println(image);
		}
	    }
	});
	
    }
}
