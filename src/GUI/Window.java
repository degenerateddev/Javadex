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
	
    JPanel openTeam = new JPanel();
    JPanel addToTeam = new JPanel();
	
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
	
	openPokédex = new OpenPokédexPanel(db);
	addToPokédex = new AddToPokédexPanel(db);
	
	this.setSize(800, 600);
	
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
            background.add(cards);
        }

        @Override
        public void show(View view) {
            cardLayout.show(background, view.name());
        }
    }
}
