package GUI;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import database.DB;

/* 
 * 	Layout und Ereignisbehandlung in AWT, grafische
 *	Komponenten in Swing.
 */

public class Window extends JFrame {
    DB db;
    JPanel cards;
    JLayeredPane layeredPane;
    
    JPanel openPokédex = new OpenPokédexPanel();
    JPanel addToPokédexPanel = new JPanel();
    JPanel editPokédexPanel = new JPanel();
    JPanel removeFromPokédexPanel = new JPanel();
	
    JPanel openTeam = new JPanel();
    JPanel addToTeam = new JPanel();
    JPanel removeFromTeamPanel = new JPanel();
	
    JPanel exportPokédex = new JPanel();
    JPanel importPokédex = new JPanel();
    
    public Window() {
	addWindowListener(new WindowAdapter() {
	    public void windowClosing (WindowEvent e) {
		db.disconnect();
		dispose();
	    }
	});
	
	db = new DB();
	
	this.setSize(800, 600);
	
//	layeredPane = new JLayeredPane();
//	layeredPane.setLayout(new BorderLayout());
//	this.setContentPane(layeredPane);
	
	BackgroundImage background = new BackgroundImage();
//	layeredPane.add(background, Integer.valueOf(0));
	this.setLayout(new BorderLayout());
	this.setContentPane(background);
	
	cards = new JPanel(new CardLayout());
	cards.setOpaque(false);
	background.add(cards);
//	layeredPane.add(cards, Integer.valueOf(1));
	
	cards.add(openPokédex, "openPokedex");
	//this.getContentPane().add(cards);
	
	Navbar bar = new Navbar(cards, this);
	this.setJMenuBar(bar);
	
	this.setVisible(true);
    }
    
    public void switchPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, panelName);
        System.out.println(panelName);
    }
}
