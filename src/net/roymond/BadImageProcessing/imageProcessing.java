package net.roymond.BadImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by gero on 4/13/2016.
 */
public class imageProcessing {

    private int delta;
    private String dir;

    public void exportFile(Graphics2D graphic, BufferedImage image){
        long currentTime = System.currentTimeMillis();
        String fileName = "shitty_" + String.valueOf(currentTime) + ".png";
        File outputFile = new File(fileName);
        graphic.drawImage(image, null, 0, 0);
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processImage(BufferedImage img){
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        Graphics2D graphic = img.createGraphics();
        int changeX = delta/2;
        int changeY = delta/2;
        int radius = delta/2;
        int pixelColor;
        int posX = 0;
        int posY = 0;

        while(posY < imgHeight){
            while(posX < imgWidth){
                pixelColor = img.getRGB(posX,posY);
                graphic.setColor(new Color(pixelColor));
                graphic.fillOval(posX,posY,radius,radius);
                posX = posX + changeX;
            }
            posY = posY + changeY;
            posX = 0;
        }
        exportFile(graphic,img);
    }


    public imageProcessing(String directory,  int delta){
        this.dir = directory;
        this.delta = delta;
    }

}
