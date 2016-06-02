package net.roymond.BadImageProcessing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by gero on 4/13/2016.
 */
public class ImageProcessor {

    private int delta;
    private String inputFilePath;
    private BufferedImage sourceImage;
    private String exportDir;
    private Graphics2D graphic;

    public void exportFile(){
        long currentTime = System.currentTimeMillis();
        String fileName = "bad_" + String.valueOf(currentTime) + ".png";
        File outputFile = new File(exportDir + "\\" + fileName);

        graphic.drawImage(sourceImage, null, 0, 0);
        try {
            ImageIO.write(sourceImage, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadImage(){
        sourceImage = null;
        try {
            sourceImage = ImageIO.read(new File(inputFilePath));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean processImage(){
        int imgWidth = sourceImage.getWidth();
        int imgHeight = sourceImage.getHeight();
        int changeX = delta / 2;
        int changeY = delta / 2;
        int radius = delta / 2;
        int pixelColor;
        int posX = 0;
        int posY = 0;
        graphic = sourceImage.createGraphics();
        while (posY < imgHeight) {
            while (posX < imgWidth) {
                pixelColor = sourceImage.getRGB(posX, posY);
                graphic.setColor(new Color(pixelColor));
                graphic.fillOval(posX, posY, radius, radius);
                posX = posX + changeX;
            }
            posY = posY + changeY;
            posX = 0;
        }
        return true;
    }


    public ImageProcessor(String inputFilePath, int delta, String exportDirectory){
        /*This is the constructor for the Image Processor class.

        @param inputFilePath - The path to the input image
        @param delta - This variable is used to generate the level of processing
        @param exportDirectory - This variable is where the new image will be placed.
         */
        this.inputFilePath = inputFilePath;
        this.delta = delta;
        this.exportDir = exportDirectory;
    }

}
