package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import database.DB;
import types.Pokémon;
import types.Pokémon.Elements;

public class AddToPokédexPanel extends JPanel {
    DB db = new DB();
    Pokémon pokémon = new Pokémon();
    ResultSet pokémonList = db.getAllDB();
    
    AddToPokédexPanel(DB db) {
        this.setLayout(new GridBagLayout());
        this.setOpaque(false);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        nameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(nameLabel, constraints);
        JTextField name = new JTextField();
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        name.setColumns(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(name, constraints);
        
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        descriptionLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(descriptionLabel, constraints);
        JTextArea description = new JTextArea();
        description.setFont(new Font("Arial", Font.PLAIN, 15));
        description.setRows(4);
        JScrollPane descriptionScrollPane = new JScrollPane(description);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(descriptionScrollPane, constraints);
        
        JLabel healthLabel = new JLabel("Health:");
        healthLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        healthLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(healthLabel, constraints);
        JTextField health = new JTextField();
        health.setFont(new Font("Arial", Font.PLAIN, 15));
        health.setColumns(10);
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(health, constraints);
        
        JLabel elementsLabel = new JLabel("Elements:");
        elementsLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        elementsLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(elementsLabel, constraints);
        JList<Pokémon.Elements> elementsList = new JList<>(Pokémon.Elements.values());
        elementsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        elementsList.setVisibleRowCount(4);
        JScrollPane elementsScrollPane = new JScrollPane(elementsList);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(elementsScrollPane , constraints);
        
        JLabel attacksLabel = new JLabel("Attacks:");
        attacksLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        attacksLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(attacksLabel, constraints);
        JList<Pokémon.Attacks> attacksList = new JList<>(Pokémon.Attacks.values());
        attacksList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        attacksList.setVisibleRowCount(4);
        JScrollPane attacksScrollPane = new JScrollPane(attacksList);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(attacksScrollPane, constraints);
        
        JLabel stageLabel = new JLabel("Stage:");
        stageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        stageLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(stageLabel, constraints);
        JTextField stage = new JTextField();
        stage.setFont(new Font("Arial", Font.PLAIN, 15));
        stage.setColumns(10);
        constraints.gridx = 1;
        constraints.gridy = 5;
        this.add(stage, constraints);
        
        JLabel linkedStagesLabel = new JLabel("Linked Stages:");
        linkedStagesLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        linkedStagesLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.add(linkedStagesLabel, constraints);
        JList<Integer> linkedStagesChoice = new JList<Integer>();
        constraints.gridx = 1;
        constraints.gridy = 6;
        this.add(linkedStagesChoice, constraints);
        
        JButton chooseImageButton = new JButton("Select image");
        constraints.gridx = 0;
        constraints.gridy = 7;
        this.add(chooseImageButton, constraints);
        
        JButton chooseSoundButton = new JButton("Select sound");
        constraints.gridx = 1;
        constraints.gridy = 7;
        this.add(chooseSoundButton, constraints);
        
        JButton submitButton = new JButton("Add to Pokédex");
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 2;
        this.add(submitButton, constraints);
        
        chooseImageButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooseImage = new JFileChooser("Image file");
                int result = chooseImage.showOpenDialog(null);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    File image = new File(chooseImage.getSelectedFile().getAbsolutePath());
                    System.out.println(image);
                }
            }
        });
        
        chooseSoundButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooseSound = new JFileChooser("Sound file");
                int result = chooseSound.showOpenDialog(null);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    File sound = new File(chooseSound.getSelectedFile().getAbsolutePath());
                    System.out.println(sound);
                }
            }
        });
        
        submitButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
        	pokémon.name = name.getText();
        	pokémon.description = description.getText();
        	pokémon.health = Integer.parseInt(health.getText());
        	pokémon.stage = Integer.parseInt(stage.getText());
        	
        	List<Pokémon.Elements> selectedElements = elementsList.getSelectedValuesList();
        	pokémon.elements = selectedElements;
        	List<Pokémon.Attacks> selectedAttacks = attacksList.getSelectedValuesList();
        	pokémon.attacks = selectedAttacks;
        	System.out.println(pokémon);
            }
        });
    }
}