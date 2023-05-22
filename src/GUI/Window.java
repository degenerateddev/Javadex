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
    
    JPanel openPokédex;
    JPanel addToPokédex;
	
    JPanel openTeam;
    JPanel addToTeam;
	
    JPanel exportPokédex;
    JPanel importPokédex;
    
    public Window() {
	addWindowListener(new WindowAdapter() {
	    public void windowClosing (WindowEvent e) {
		db.disconnectDB();
		dispose();
	    }
	});
	
	db = new DB();
	
	openPokédex = new OpenPokédexPanel(db);
	addToPokédex = new AddToPokédexPanel(db);
	openTeam = new OpenTeamPanel(db);
	addToTeam = new AddToTeamPanel(db);
	exportPokédex = new ExportPokédexPanel(db);
	importPokédex = new ImportPokédexPanel();
	
	this.setSize(800, 600);
	this.setResizable(false);
	
	this.add(new MainPane());
	this.pack();
	this.setLocationRelativeTo(null);
	
	Navbar bar = new Navbar(cards, this);
	this.setJMenuBar(bar);
	
	this.setVisible(true);
    }
    
    public void switchPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, panelName);
    }
    
    public interface Navigatable {
	enum View {
	    MENU, CONTENT;
	}
	public void show(View view);
    }
    
    public class MainPane extends JPanel implements Navigatable {
	private BackgroundImage background;
	private CardLayout cardLayout;
	
        public MainPane() {
            setLayout(new BorderLayout());

            background = new BackgroundImage();
            background.setLayout(new CardLayout());
            this.add(background);
            
            cards = new JPanel(new CardLayout());
            cards.setOpaque(false);
	
            cards.add(openPokédex, "openPokedex");
            cards.add(addToPokédex, "addToPokedex");
            cards.add(openTeam, "openTeam");
            cards.add(addToTeam, "addToTeam");
            cards.add(exportPokédex, "exportPokedex");
            cards.add(importPokédex, "importPokedex");
            background.add(cards);
        }

        @Override
        public void show(View view) {
            cardLayout.show(background, view.name());
        }
    }
}
