package GUI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import database.DB;

/* 
 * 	Layout und Ereignisbehandlung in AWT, grafische
 *	Komponenten in Swing.
 */

public class Window extends JFrame {
    DB db;
    
    public Window() {
	addWindowListener(new WindowAdapter() {
	    public void windowClosing (WindowEvent e) {
		db.disconnect();
		dispose();
	    }
	});
	
	db = new DB();
	
	this.setSize(800, 600);
	//this.setLayout(new CardLayout());
	
	Navbar bar = new Navbar();
	this.setJMenuBar(bar);
	
	this.setContentPane(new ImagePanel());
	
	JPanel cards = new JPanel(new CardLayout());
	
	JPanel openPokédexPanel = new JPanel();
	JPanel addToPokédexPanel = new JPanel();
	JPanel editPokédexPanel = new JPanel();
	JPanel removeFromPokédexPanel = new JPanel();
	
	JPanel openTeam = new JPanel();
	JPanel addToTeam = new JPanel();
	JPanel removeFromTeamPanel = new JPanel();
	
	JPanel exportPokédex = new JPanel();
	JPanel importPokédex = new JPanel();
	
	final String ADD_TO_POKÉDEX_PANEL = "Test";
	cards.add(addToPokédexPanel, ADD_TO_POKÉDEX_PANEL);
	
	
	this.setVisible(true);
    }
}
