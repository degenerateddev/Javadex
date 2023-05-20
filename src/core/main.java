package core;

import javax.swing.SwingUtilities;

import GUI.Window;

public class main {
    
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
	    
	    @Override
	    public void run() {
		new Window();
	    }
	});
    }
}
