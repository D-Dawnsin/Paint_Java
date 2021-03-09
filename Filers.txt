/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pain.t;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author dawso
 */
public class Filers {
    
    private File selectedFile;
    private File savFile;
    private Image image;
    private DCanvas Dcanvas;
    private Stage mainStage;
    private SuperMenu superMenu;
    private final int CLOSING_WINDOW_WIDTH = 350;
    private final int CLOSING_WINDOW_HEIGHTH = 60;
    private final int CANVAS_WIDTH = 1750;
    private final int CANVAS_HEIGHT = 950;
    private double TIMER_WINDOW_HEIGHT = 150;
    private double TIMER_WINDOW_WIDTH = 450;
    
    
    /**
     * This is the constructor of the Filers class that instantiates a DCanvas
     * and Stage object in order to help perform file oriented tasks
     * @param canvas
     * @param stage 
     */
    public Filers(DCanvas canvas, Stage stage)
    {
        Dcanvas = canvas;
        
        mainStage = stage;
    }
    
    
    /**
     * Brings up a window that asks the user if they wish to see the autosave
     * timer or not see the timer
     */
    public void timer()
    {
        Button yes = new Button("Yes");
        Button no = new Button("No");

        //creates Label to ask user question on timer
        Label question = new Label("Would you like an automated save timer?");  

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(question, 1, 0);  //adds label to grid
        grid.add(yes, 0, 1);
        grid.add(no, 2, 1);

        Scene timerScene = new Scene(grid, TIMER_WINDOW_WIDTH, 
                TIMER_WINDOW_HEIGHT);  //adds the grid to the window

        Stage timerWindow = new Stage();  //creates the new window (stage)

        timerWindow.setTitle("Timer");
        timerWindow.setScene(timerScene);
        timerWindow.show();

        yes.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                
                timerWindow.close();
            }
        });

        no.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                
                timerWindow.close();
            }
        });
    }
    
     /**
     * Method saves the canvas to wherever the user originally saved the 
     * canvas when they "Saved As"
     * If the save file is null then it won't save
     * if save file is not null then the method makes a writable image and 
     * makes it a snapshot of the canvas and saves that over the former 
     * destination where the user originally saved canvas first 
     */
    public void save()
    {
        if(savFile != null)
        {
            try   /*no fileshooser, therefore it uses the address of where they
                first saved their canvas and just reuses that address here 
                without asking the user if they want to make a new address*/
            {
                //Constructs an empty image the size of the canvas
                WritableImage writableImage = new WritableImage(CANVAS_WIDTH, 
                        CANVAS_HEIGHT);  
                /*Takes a snapshot of the canvas and everything on it, 
                and applies it to the writableimage*/
                Dcanvas.canvas.snapshot(null, writableImage);
                //Renders the writableimage
                RenderedImage renderedImage = 
                        SwingFXUtils.fromFXImage(writableImage, null);  
                ImageIO.write(renderedImage, "png", savFile); //Saves the render
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(PainT.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
            
        }
    }
    
    /**
     * Brings up a window to ask user if they prefer to continue and exit
     * or save once again in the same spot they originally saved and then
     * close the program
     */
    public void exit()
    {
        Button continueB = new Button("Yes, continue");
        Button secondSave = new Button("save");

        HBox thirdLayout = new HBox();
        thirdLayout.setPadding(new Insets(15, 12, 15, 12));
        thirdLayout.setSpacing(150);  //The spacing between the buttons
        thirdLayout.getChildren().add(continueB);
        thirdLayout.getChildren().add(secondSave);

        continueB.setAlignment(Pos.CENTER);  //Allignment of words in button
        secondSave.setAlignment(Pos.CENTER);

        Scene thirdScene = new Scene(thirdLayout, CLOSING_WINDOW_WIDTH,
                CLOSING_WINDOW_HEIGHTH);

        Stage closingWindow = new Stage();  //creates the new window (stage)
        closingWindow.setTitle("Are you sure?");
        closingWindow.setScene(thirdScene);

        closingWindow.show();

        secondSave.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                if(savFile != null)
                {   
                    try   /*no filechooser, therefore it uses the address of 
                        where they first saved their canvas and just reuses 
                        that address here without asking the user if they want
                        to make a new address*/
                    {
                        //Constructs an empty image the size of the canvas
                        WritableImage writableImage = 
                                new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT);
                        /*Takes a snapshot of the canvas and everything on it, 
                        and applies it to the writableimage*/
                        Dcanvas.canvas.snapshot(null, writableImage); 
                        //Renders the writableimage
                        RenderedImage renderedImage = 
                                SwingFXUtils.fromFXImage(writableImage, null);
                        //Saves the render
                        ImageIO.write(renderedImage, "png", savFile);  
                        closingWindow.close();  //closes closing window
                        mainStage.close();  //closes window after save
                    }

                    catch (IOException ex) 
                    {
                        Logger.getLogger(PainT.class.getName()).
                                log(Level.SEVERE, null, ex);
                    }
                    closingWindow.close();  //closes closing window
                    mainStage.close();  //closes the program
                }
            }
        });


        continueB.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                closingWindow.close();  //closes closing window
                mainStage.close();  //closes the program
            }
        });
    }
    
    
    /**
     * Allows the user to save the state of their canvas wherever in their
     * computer they wish
     */
    public void saveAs()
    {
        FileChooser fileChooser2 = new FileChooser();
        //Allows user to filter between png's and jpeg's
        fileChooser2.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("TIF File", "*.tif"),
        new FileChooser.ExtensionFilter("JPG File", "*.jpg"),
        new FileChooser.ExtensionFilter("PNG File", "*png"));  

        savFile = fileChooser2.showSaveDialog(mainStage);

        if(savFile != null)
        {
            //timer.run();
            try 
            {
                //Constructs an empty image the size of the canvas
                WritableImage writableImage = new WritableImage(CANVAS_WIDTH, 
                        CANVAS_HEIGHT);  
                /*Takes a snapshot of the canvas and everything on it, and 
                applies it to the writableimage*/
                Dcanvas.canvas.snapshot(null, writableImage);
                //Renders the writableimage
                RenderedImage renderedImage = 
                        SwingFXUtils.fromFXImage(writableImage, null);  
                ImageIO.write(renderedImage, "png", savFile); //Saves the render
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(PainT.class.getName()).
                        log(Level.SEVERE, null, ex);
            } 
        }
        System.out.println("Started auto save");  //starts the thread
    }
    
    /**
     * Changes the width and height of the canvas to the width and height of 
     * the picture the user chooses
     * @param width
     * @param height 
     */
    public void ChangeCanvas(double width , double height)
    {
        Dcanvas.canvas.setHeight(height);
        Dcanvas.canvas.setWidth(width);
    }
    
    
    /**
     * Opens a dialogue box that allows the user to choose a photo file from
     * their computer
     */
    public void Open()
    {
        FileChooser fileChooser = new FileChooser();//allows user to choose file
        //Allows user to filter between png's, jpeg's, and .tif's
        fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("TIF File", "*.tif"),
        new FileChooser.ExtensionFilter("JPG File", "*.jpg"),
        new FileChooser.ExtensionFilter("PNG File", "*png"));  

        selectedFile = fileChooser.showOpenDialog(mainStage);
        try 
        {
            //takes the address of file and assigns it to the "input" variable
            FileInputStream input = new FileInputStream(selectedFile);
            /*next lines of code grab the image from the "input" address and 
            then allows for the ability to shows it with "imageView"*/
            image = new Image(input);   

            double x = image.getWidth();
            double y = image.getHeight();


            /*Sends the dimensions of the photo to a method that will change the
            canvas dimensions to the dimensions of the picture*/
            ChangeCanvas(x , y); 
            /*This makes it so that the picture is as big as the variables 
            defined above*/
            Dcanvas.gc.drawImage(image, 0, 0, x, y); 
            //adds screenshot of canvas after edit to stack of images
            Dcanvas.addToStack(Dcanvas.screenshot());  
            addToMainStack(Dcanvas.screenshot());
        } 

        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PainT.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
    * Pushes an image onto a stack
    * @param i 
    */
    public void addToMainStack(Image i)
    {
      Dcanvas.mainPicture.push(i);
    }
    

    
}
