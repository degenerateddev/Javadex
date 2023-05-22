package GUI;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Navbar extends JMenuBar{
    JPanel cards;
    Window window;
    
    public Navbar(JPanel cards, Window window) {
	this.cards = cards;
	this.window = window;
	
	JMenu pokedex = new JMenu("Pokédex");
	JMenuItem openPokedex = new JMenuItem("Open");
	JMenuItem addToPokedex = new JMenuItem("Add");
	JMenuItem editPokedex = new JMenuItem("Edit");
	JMenuItem removeFromPokedex = new JMenuItem("Remove");
	pokedex.add(openPokedex);
	pokedex.add(addToPokedex);
	pokedex.add(editPokedex);
	pokedex.add(removeFromPokedex);
	
	JMenu team = new JMenu("Team");
	JMenuItem openTeam = new JMenuItem("Open");
	JMenuItem addToTeam = new JMenuItem("Add");
	JMenuItem removeFromTeam = new JMenuItem("Remove");
	team.add(openTeam);
	team.add(addToTeam);
	team.add(removeFromTeam);
	
	JMenu export = new JMenu("Export");
	JMenuItem exportPokedex = new JMenuItem("Export");
	JMenuItem importPokedex = new JMenuItem("Import");
	export.add(exportPokedex);
	export.add(importPokedex);
	
	this.add(pokedex);
	this.add(team);
	this.add(export);
	
	// Events
	openPokedex.addActionListener(e -> window.switchPanel("openPokedex"));
	addToPokedex.addActionListener(e -> window.switchPanel("addToPokedex"));
	editPokedex.addActionListener(e -> window.switchPanel("editPokedex"));
	removeFromPokedex.addActionListener(e -> window.switchPanel("removeFromPokedex"));
	
	openTeam.addActionListener(e -> window.switchPanel("openPokedex"));
	addToTeam.addActionListener(e -> window.switchPanel("openPokedex"));
	removeFromTeam.addActionListener(e -> window.switchPanel("openPokedex"));
	
	exportPokedex.addActionListener(e -> window.switchPanel("exportPokedex"));
	importPokedex.addActionListener(e -> window.switchPanel("importPokedex"));
    }
}
