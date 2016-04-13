package net.roymond.BadImageProcessing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by gero on 4/13/2016.
 */
public class SetupWindow {
    private JButton browseButton;
    private JPanel Setup_Window;
    private JPanel titlePanel;
    private JPanel OptionsPanel;
    private JTextField inputImagePath;
    private JSlider deltaSlider;
    private JTextField exportDirPath;
    private JButton exportDirButton;
    private JButton exportButton;
    private JLabel errorText;

    String imagePath = "";
    String exportPath = "";
    int desiredDelta;
    boolean status;

    public SetupWindow(){

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "PNG and JPG Files", "png", "jpg");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int returnVal = chooser.showOpenDialog(Setup_Window);
                if(returnVal == JFileChooser.APPROVE_OPTION) {

                    imagePath = chooser.getSelectedFile().getAbsolutePath();

                    inputImagePath.setText(imagePath);

                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getName());
                }
            }
        });

        exportDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser exportDirChooser = new JFileChooser();
                exportDirChooser.setCurrentDirectory(new File("."));
                exportDirChooser.setDialogTitle("Select an export directory");
                exportDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                exportDirChooser.setAcceptAllFileFilterUsed(false);
                int returnVal = exportDirChooser.showOpenDialog(Setup_Window);
                if(returnVal == JFileChooser.APPROVE_OPTION) {

                    exportPath = exportDirChooser.getSelectedFile().getAbsolutePath();
                    exportDirPath.setText(exportPath);

                    System.out.println("You chose to open this file: " +
                            exportDirChooser.getSelectedFile().getName());
                }
            }
        });

        deltaSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                desiredDelta = deltaSlider.getValue();
            }
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Check to see if the input file is null
                if (imagePath.equals("") && inputImagePath.getText().equals("")){
                    inputImagePath.setText("ERROR: No Input File Specified");
                } else if (imagePath.equals("")){
                    imagePath = inputImagePath.getText();
                }

                desiredDelta = deltaSlider.getValue();

                try {
                    ImageProcessor imageProcessor = new ImageProcessor(imagePath, desiredDelta, exportPath);
                    imageProcessor.loadImage();
                    imageProcessor.processImage();
                    imageProcessor.exportFile();
                } catch (Exception exc){
                    errorText.setText("An error has occurred");
                }

            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("The S***y Image Processor");
        frame.setContentPane(new SetupWindow().Setup_Window);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

}
