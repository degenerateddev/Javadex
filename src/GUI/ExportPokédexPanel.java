package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import database.DB;

public class ExportPokédexPanel extends JPanel {
    DB db;
    
    ExportPokédexPanel(DB db) {
	this.setOpaque(false);
	
	JButton exportButton = new JButton("Export");
	this.add(exportButton);
	
	exportButton.addActionListener(new ActionListener() {
	    
	    @Override
	    public void actionPerformed(ActionEvent e) {
		JFileChooser chooseImage = new JFileChooser("File");
		int result = chooseImage.showSaveDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    File file = new File(chooseImage.getSelectedFile().getAbsolutePath());
		    System.out.println(file);
		}
	    }
	});
    }
}
