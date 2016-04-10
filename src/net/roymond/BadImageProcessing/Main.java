package net.roymond.BadImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main{

    static int delta = 20;
    static String dirOfFile;

    public static void exportFile(Graphics2D graphic, BufferedImage image){
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

    public static void processImage(BufferedImage img){
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



    public static void main(String[] args) {

        //Getting the directory
        try {
            dirOfFile = args[0];
            delta = Integer.parseInt(args[1]);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("FAILED!");
            dirOfFile = "";

        }

        //Open an image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(dirOfFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (img != null){
            processImage(img);
        }

    }
}
