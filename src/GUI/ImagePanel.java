package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class ImagePanel extends JComponent {
    BufferedImage image;
    
    public ImagePanel() {
	File bgimage = null;
	
	File directory = new File("src/bin/");
	
	if (directory.isDirectory()) {
	    File[] files = directory.listFiles();
	    
	    if (files != null && files.length > 0) {
		Random rng = new Random();
		File randomFile = files[rng.nextInt(files.length)];
		bgimage = randomFile;
		
	    } else {
		System.out.println("No images!");
	    }
	} else {
	    System.out.println("Not a directory!");
	}
	
	try {
	    image = ImageIO.read(bgimage);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
//    @Override
//    protected void paintComponent(Graphics g) {
//	super.paintComponent(g);
//	
//	double ratio = Math.min(
//                (double) getWidth() / image.getWidth(),
//                (double) getHeight() / image.getHeight()
//        );
//        int scaledWidth = (int) (image.getWidth() * ratio);
//        int scaledHeight = (int) (image.getHeight() * ratio);
//        
//        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
//	
//	g.drawImage(scaledImage, 0, 0, this);
//    }
//    
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(image.getWidth(), image.getHeight());
//    }
}
