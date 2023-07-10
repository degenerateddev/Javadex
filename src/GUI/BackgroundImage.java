package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class BackgroundImage extends JComponent {
    BufferedImage image = null;
    
    public BackgroundImage() {
	File bgimage = null;
	
	File directory = new File("static/");
	
	if (directory.isDirectory()) {
	    File[] files = directory.listFiles();
	    
	    if (files != null && files.length > 0) {
		Random rng = new Random();
		File randomFile = files[rng.nextInt(files.length)];
		bgimage = randomFile;
		
	    } else {
		System.out.println("No images!");
		return;
	    }
	} else {
	    System.out.println("Not a directory!");
	    return;
	}
	
	try {
	    image = ImageIO.read(bgimage);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            //return new Dimension(image.getWidth(), image.getHeight());
        }
        return new Dimension(800, 600);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	if (image != null) {
	    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
    }
}
