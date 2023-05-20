package GUI;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Navbar extends JMenuBar{
    
    public Navbar() {
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
    }
}
