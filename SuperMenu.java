/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pain.t;

import java.util.Random;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author dawso
 */
public class SuperMenu {
    
    private final double NOTES_WINDOW_WIDTH = 1000;
    private final double NOTES_WINDOW_HEIGHT = 750;
    private final double TOOLS_WINDOW_WIDTH = 750;
    private final double TOOLS_WINDOW_HEIGHT = 550;
    protected Label timer;
    private ToggleGroup group;
    private KeyCombination keyS;
    private KeyCombination keyO;
    private KeyCombination keySa;
    protected ColorPicker colorPicker = new ColorPicker(Color.DODGERBLUE);
    protected MenuBar menuBar;
    protected ToolBar toolBar;
    private DCanvas Dcanvas;
    private Log log;
    private final int POLY_WINDOW_WIDTH = 450;
    private final int POLY_WINDOW_HEIGHT = 150;
    private int polygonSides;
    protected TextArea textA;
    private final int NEW_WINDOW_WIDTH = 400;
    private final int NEW_WINDOW_HEIGHTH = 150;
    private Filers filers;
    private Thread thread;
    private double TIMER_WINDOW_HEIGHT = 150;
    private double TIMER_WINDOW_WIDTH = 450;
    
    
    /**
     * This is the Constructor of the SuperMenu class that creates all of the 
     * menus, menu items, buttons, toggle buttons, and dictates the actions
     * when the any of the items or buttons are clicked
     * @param canvas
     * @param filer 
     */
    public SuperMenu(DCanvas canvas, Filers filer)
    {
        Dcanvas = canvas;
        
        filers = filer;
        
        timer = new Label();
        
        Label time = new Label();
        Clock timer = new Clock(filer, time);
        thread = new Thread(timer);
        time.textProperty().bind(timer.getTime());
        
        menuBar = new MenuBar();
        //Menu is the button to click for drop down in menubar
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");//These are all menubar options for program
        Menu view = new Menu("View");
        Menu imageMen = new Menu("Image");
        Menu help = new Menu("Help");
        Menu clockLabel = new Menu("", time);
        
        //adds the menu's to the menubar
        menuBar.getMenus().addAll(file, edit, view, imageMen, help, clockLabel); 
        
        //adds the about tab to the help menuitem
        MenuItem about = new MenuItem("About");  
        //Adds the tab to access Release Notes
        MenuItem release = new MenuItem("Release Notes");  
        MenuItem tools = new MenuItem("About Tools"); //Adds the about tools tab 
        help.getItems().addAll(about, release, tools);  
        
        MenuItem crop = new MenuItem("Crop");  //creates crop tab
        MenuItem stamp = new MenuItem("Stamp");  //creates stamp tab
        edit.getItems().addAll(crop, stamp);  //adds crop and stamp under edit
        
        
        MenuItem zoomI = new MenuItem("Zoom In");  //creates MenuItem zoom in
        MenuItem zoomO = new MenuItem("Zoom Out");  //creates MenuItem zoom out
        edit.getItems().addAll(zoomI, zoomO);//adds zoom in and zoom Out to edit
        
        //creates a menu item, which are the menu options under the menu
        MenuItem save = new MenuItem("Save");  
        MenuItem saveAs = new MenuItem("Save As..");
        MenuItem open = new MenuItem("Open..");
        MenuItem exit = new MenuItem("Exit..");
        MenuItem clock = new MenuItem("Timer");
        //adds the menu items to the menu
        file.getItems().addAll(open, saveAs, save, clock, exit);
        
        
        open.setAccelerator(new KeyCodeCombination(KeyCode.O,
                KeyCombination.CONTROL_DOWN));  /*creates keyboard shortcut for 
        Open = Ctrl + O*/
        
        //creates keyboard shortcut for Save = Crtl + S
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, 
                KeyCombination.CONTROL_DOWN));  
        saveAs.setAccelerator(new KeyCodeCombination(KeyCode.A,
                KeyCombination.CONTROL_DOWN));  /*creates keyboard shortcut for 
        Save As = Ctrl + A*/
        exit.setAccelerator(new KeyCodeCombination(KeyCode.E,
                KeyCombination.CONTROL_DOWN));  /*creates keyboard shortcut for 
        Exit = Ctrl + E*/
        
        
        toolBar = new ToolBar();  //creats toolbar 
        //Creates the menuButton Brush Width
        MenuButton width = new MenuButton("Brush Width"); 
        //creates the width options for under the BrushWidth button
        MenuItem onePx = new MenuItem("1 Px");  
        MenuItem twoPx = new MenuItem("2 Px");
        MenuItem fourPx = new MenuItem("4 Px");
        MenuItem eightPx = new MenuItem("8 Px");
        MenuItem twelvePx = new MenuItem("12 Px");
        //Adds the width choices under the toolbar menuitem 
        width.getItems().addAll(onePx, twoPx, fourPx, eightPx, twelvePx);  
        
        
        Button undo = new Button("Undo");  //makes undo button
        Button redo = new Button("Redo");  //makes the redo button
        
        //makes the random button
        ToggleButton random = new ToggleButton("Random");  
        
        //Makes the Pencil Tool Button
        ToggleButton pencilT = new ToggleButton("Pencil Tool");  
        
        //Creates dropper tool
        ToggleButton dropper = new ToggleButton("Dropper"); 
        
        //Makes all of the Shape options, Toggle Buttons
        ToggleButton rectangle = new ToggleButton("Rectangle");  
        ToggleButton square = new ToggleButton("Square");
        ToggleButton polygon = new ToggleButton("Polygon");
        ToggleButton circle = new ToggleButton("Circle");
        ToggleButton elipse = new ToggleButton("Elipse");
        ToggleButton roundR = new ToggleButton("Round Rectangle");  
        
        //Creates the eraser tool
        ToggleButton eraser = new ToggleButton("Eraser");  
        
        //Makes the line tool button a toggle button
        ToggleButton lineT = new ToggleButton("Line Tool");  
        
        //Makes Text Button
        ToggleButton text = new ToggleButton("Text");  
        
        //creates the status bar Label
        Label statusBar = new Label("Tool Selected");  
        
        textA = new TextArea();  //Makes Text Area
        textA.setWrapText(true);
        textA.setPrefHeight(50);  //Changes the width of textArea
        textA.setPrefWidth(105);  //Changes the height of textArea
        

         //adds all of the items to the toolbar
        toolBar.getItems().addAll(colorPicker, new Separator(), undo, redo, 
        new Separator(), dropper, new Separator(), eraser, pencilT, 
        lineT, new Separator(),width, new Separator(), rectangle, 
        roundR, square, polygon, circle, elipse, new Separator(), 
        random, new Separator(),text, textA, new Separator(), statusBar); 
        
        
        /*Makes a toggle group so that when one button is pressed the other 
        buttons arent also pressed*/
        group = new ToggleGroup();  
        pencilT.setToggleGroup(group);  //Adds the pencil Tool to the group
        lineT.setToggleGroup(group);  //adds the Line tool to the group
        rectangle.setToggleGroup(group);  //adds rectangle to the group
        square.setToggleGroup(group);  //adds square to the group
        circle.setToggleGroup(group);  //adds rectangle to the group
        elipse.setToggleGroup(group);  //adds rectangle to the group
        dropper.setToggleGroup(group);  //adds dropper to the group
        eraser.setToggleGroup(group);  //adds eraser to the group
        polygon.setToggleGroup(group);  //adds polygon to the group
        roundR.setToggleGroup(group);  //adds round rectangle to the group
        text.setToggleGroup(group);  //adds text tool to the group
        random.setToggleGroup(group);  //adds random to the group
        
        
        
        ////////////////////////////////////////////////////////////////////////
        
        /*When user clicks brush width the drawing size on canvas is changed to 
        this width*/
        onePx.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                Dcanvas.gc.setLineWidth(1);  //sets width of strokes to 1 px
            }
        });
        
        twoPx.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Dcanvas.gc.setLineWidth(2);  //sets width of strokes to 2 px
            }
        });
        
        fourPx.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Dcanvas.gc.setLineWidth(4);  //sets width of strokes to 4 px
            }
        });
        
        eightPx.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Dcanvas.gc.setLineWidth(8);  //sets width of strokes to 8 px
            }
        });
        
        twelvePx.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Dcanvas.gc.setLineWidth(12);  //sets width of strokes to 12 px
            }
        });
        
        ////////////////////////////////////////////////////////////////////////
        
        //This allows the user to choose when they want to use the Pencil Tool 
        pencilT.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 0;  
                statusBar.setText("Pencil Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Line Tool 
        lineT.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 1; 
                statusBar.setText("Line Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Rectangle Tool 
        rectangle.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 2; 
                statusBar.setText("Rectangle Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Square Tool 
        square.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e)
            {
                 /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 3;  
                statusBar.setText("Square Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Circle Tool 
        circle.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 4;  
                statusBar.setText("Circle Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Elipse Tool 
        elipse.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 5;  
                statusBar.setText("Elipse Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Dropper Tool
        dropper.setOnAction(new EventHandler<ActionEvent>()   
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 8;  
                statusBar.setText("Dropper Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Eraser Tool
        eraser.setOnAction(new EventHandler<ActionEvent>()   
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 9;
                statusBar.setText("Eraser Tool");
            }
        });
      
        //This allows the user to choose when they want to use the Polygon Tool
        polygon.setOnAction(new EventHandler<ActionEvent>()   
        {
            public void handle(ActionEvent e)
            {
                polyWindow();
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 6;
                statusBar.setText("Polygon Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Round Rectangle Tool
        roundR.setOnAction(new EventHandler<ActionEvent>()   
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 7;  
                statusBar.setText("Round Rect. Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Text Tool 
        text.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 11;  
                statusBar.setText("Text Tool");
            }
        });
        
        //This allows the user to choose when they want to use the Crop Function 
        crop.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 12;  
                statusBar.setText("Cropping Tool");
            }
        });
        
        //This allows the user to choose when they want to use the stamp function
        stamp.setOnAction(new EventHandler<ActionEvent>()   
        {
            public void handle(ActionEvent e)
            {
                /*when user chooses their tool it changes the int variable to 
                this number*/
                Dcanvas.choice = 14;  
                statusBar.setText("Stamp Tool");
            }
        });
        
        //This allows the user to draw a random shape
        random.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                Random ran = new Random();  //Makes radnom number generator

                Dcanvas.x = ran.nextInt(7)+1;  //makes number from 1 - 7
                
                statusBar.setText("Random, Who Knows...");
                
                //if the number is anything but the polygon, then the tool is called
                if(Dcanvas.x != 6)  
                Dcanvas.choice = Dcanvas.x;
                
                /*if the number correlates with the polygon then it will call 
                the polygon window method*/
                else  
                {
                  polyWindow();  
                }
            }
        });
        
        ///////////////////////////////////////////////////////////////////////
        
        undo.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                //if stack of images is not empty
                if (Dcanvas.actionsU.isEmpty() == false)  
                {
                    Image tempImage = canvas.actionsU.pop();
                    /*pushes the popped off image from the undo stack to the 
                    redo stack*/
                    Dcanvas.actionsR.push(tempImage);  
                    
                    if(Dcanvas.actionsU.isEmpty() == false)
                    {
                        /*displays the second most recent screenshot of the 
                        canvas on the canvas*/
                        Dcanvas.gc.drawImage(Dcanvas.actionsU.peek(), 0, 0);  
                    }
                }
                
                else
                    System.out.println("Can't undo anymore");
            }
        });
        
        redo.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                if(Dcanvas.actionsR.isEmpty() == false)
                    {
                        /*displays the most recent screenshot of the canvas on 
                        the canvas from the redo stack*/
                        Dcanvas.gc.drawImage(Dcanvas.actionsR.peek(), 0, 0);  
                        
                            //if stack of images is not empty
                            if (Dcanvas.actionsR.isEmpty() == false)  
                                {
                                    //gets rid of most recent snapshot of screen
                                    Image tempImage = canvas.actionsR.pop(); 
                                    /*pushes the popped off image from the redo
                                    stack back onto the undo Stack in order to 
                                    alternate back an forth*/
                                    Dcanvas.actionsU.push(tempImage);  
                                }
                    }
                else
                    System.out.println("Can't redo anymore");
            }
        });
        
        ////////////////////////////////////////////////////////////////////////
        
        zoomI.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                ZoomIn();
            }
        });
        
         zoomO.setOnAction(new EventHandler<ActionEvent>() 
        {
            public void handle(ActionEvent e)
            {
                ZoomOut();
            }
        });
         
         ///////////////////////////////////////////////////////////////////////
         
         about.setOnAction(new EventHandler<ActionEvent>()
        {
             public void handle(ActionEvent e)
             {
                Button shut = new Button("Close");
                
                //Information pertaining to the program
                Label label = new Label("This is the most current release"
                        + " of Pain(t)" + "\n" + "Thanks for supporting the "
                        + "program!");  
                label.setAlignment(Pos.TOP_CENTER);
                
                //toggling the dimensions and everything in Grid Pane
                GridPane secondaryLayout = new GridPane();  
                secondaryLayout.setAlignment(Pos.CENTER);
                //sets positioning of button and label
                secondaryLayout.add(shut, 1, 2);  
                secondaryLayout.add(label, 1 , 1);
                secondaryLayout.setHgap(10);
                secondaryLayout.setVgap(10);
                secondaryLayout.setPadding(new Insets(25, 25, 25, 25));
                
                
                Scene secondScene = new Scene(secondaryLayout, NEW_WINDOW_WIDTH,
                        NEW_WINDOW_HEIGHTH);
                
                Stage newWindow = new Stage();  //creates the new window (stage)
                newWindow.setTitle("About Pain(t)");
                newWindow.setScene(secondScene);
                
                //sets dimensions of where the window will pop up
                newWindow.setX(NEW_WINDOW_WIDTH);  
                newWindow.setY(NEW_WINDOW_HEIGHTH);
                
                newWindow.show();
                
                shut.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent e)
                    {
                        newWindow.close();  //closes the window
                    }
                });
                
            }
        });
        
        release.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                Label notes = new Label("Release Notes:\n" +
                "\n" +
                "Version Final (11/7/19)\n" +
                "\n" +
                "New Features:\n" +
                "-Ability to see and not see the auto save timer\n" +
                "\n" +
                "Known Issues:\n" +
                "-Window is not always the same size as the scene so sometimes "
                        + "the bars get cut off\n" +
                "\n" +
                "Upcoming:\n" +
                "All done\n" +
                "\n" +
                "\n" +
                "Version 0.00006 (10/14/19)\n" +
                "\n" +
                "New Features:\n" +
                "-A Random Tool that draws any of the shapes randomly\n" +
                "-A Label to inform the user what tool they have currently "
                        + "selected\n" +
                "-Logging how long a tool is used for\n" +
                "\n" +
                "\n" +
                "Known Issues:\n" +
                "-The window is not always the same size as the scene so the "
                        + "bars get cut off sometimes\n" +
                "-The Logging is still messed up so it does not write into the"
                        + " text file perfectly\n" +
                "\n" +
                "Upcoming:\n" +
                "-No Upcoming new features\n" +
                "\n" +
                "Version 0.00005 (10/7/19)\n" +
                "\n" +
                "New Features:\n" +
                "-Timed autosave\n" +
                "-Saves images in the three different files\n" +
                "\n" +
                "\n" +
                "Known Issues:\n" +
                "-The window is not always the same size as the scene so the"
                        + " bars get cut off sometimes\n" +
                "-Zoom does not let user still move picture around\n" +
                "\n" +
                "Upcoming:\n" +
                "-adding a random shp drawing button\n" +
                "-adding a stamp tool\n" +
                "-showsing what tool is in use with a label\n" +
                "-and attempting to document how long each tool is chosen\n" +
                "\n" +
                "\n" +
                "Version 0.00004 (9/27/19)\n" +
                "\n" +
                "New Features:\n" +
                "-Allows user to crop image\n" +
                "-Now user can redo an change to canvas\n" +
                "-User can add text to canvas\n" +
                "-User can specify how many sides they want a polygon to have "
                        + "and draw it\n" +
                "-Can no draw a round rectangle\n" +
                "-User can see what they are drawing on canvas \n" +
                "-Can no erase markings on canvas\n" +
                "\n" +
                "\n" +
                "Known Issues:\n" +
                "-The window is not always the same size as the scene so the "
                        + "bars get cut off sometimes\n" +
                "-Zoom does not let user still move picture around\n" +
                "\n" +
                "Upcoming:\n" +
                "-Solidfy zooms\n" +
                "-Record when user opens and saves files\n" +
                "\n" +
                "\n" +
                "Version 0.00003 (9/20/19)\n" +
                "\n" +
                "New Features:\n" +
                "-Draws straight lines, rectangles, circles, squares, and "
                        + "elispes\n" +
                "-Can undo any changes to canvas\n" +
                "-Can use dropper tool to get any color from strokes or"
                        + " pictures\n" +
                "-Keyboard shortcuts for save, save as, exit, and open\n" +
                "\n" +
                "\n" +
                "Known Issues:\n" +
                "-No known issues as of now\n" +
                "-The window is not always the same size as the scene so the "
                        + "bars get cut off sometimes\n" +
                "-Zoom does not let user still move picture around and cant "
                        + "zoom out\n" +
                "\n" +
                "Upcoming:\n" +
                "-Adjusting the scene size to the size of the window\n" +
                "-Redo button\n" +
                "-Allows user to add text to Canvas\n" +
                "-Solidify zooms\n" +
                "\n" +
                "\n" +
                "Version 0.00002 (9/12/19)\n" +
                "\n" +
                "New Features:\n" +
                "-Pop up window asking it user wishes to exit the program"
                        + " without saving again\n" +
                "-Scroll bars for when he picture is bigger then the window\n" +
                "-An About tab that talks about the program\n" +
                "-One can draw on the canvas\n" +
                "-one can also choose the color and width of their brush\n" +
                "\n" +
                "Known Issues:\n" +
                "-No known issues as of now\n" +
                "-The window is not always the same size as the scene so the "
                        + "bars get cut off sometimes\n" +
                "\n" +
                "Upcoming:\n" +
                "-Adjusting the scene size to the size of the window\n" +
                "\n" +
                "\n" +
                "Version 0.00001 (9/3/19)\n" +
                "\n" +
                "New Features:\n" +
                "-Opening a png or jpeg from one's computer\n" +
                "-Saving one's canvas in the window to their computer\n" +
                "-Closing nicely (ADA complient)\n" +
                "\n" +
                "Known Issues:\n" +
                "-No known issues as of now\n" +
                "\n" +
                "Upcoming:\n" +
                "-Adding a brush system for user to draw on their canvas\n" +
                "-Allowing user to adjust placement/size of picture");
                
                HBox thirdLayout = new HBox();
                thirdLayout.setPadding(new Insets(15, 12, 15, 12));
                thirdLayout.setSpacing(150);  //The spacing between the buttons
                thirdLayout.getChildren().add(notes);
                
                notes.setAlignment(Pos.CENTER);//places the label in middle of hBoc
                
                ScrollPane releaseScroll = new ScrollPane();//Creates ScrollPane
                //Scrollers only show when the window squeezes the photo
                releaseScroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);  
                releaseScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                releaseScroll.setContent(thirdLayout);  //adds hbox to scrollpane
                
                
                
                Scene releaseScene = new Scene(releaseScroll, NOTES_WINDOW_WIDTH
                        , NOTES_WINDOW_HEIGHT);
                
                Stage releaseWindow = new Stage();//creates the new window (stage)
                releaseWindow.setTitle("Rlease Notes");
                releaseWindow.setScene(releaseScene);
                
                releaseWindow.show();
            }
        });
        
        tools.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                Label notes = new Label("Color Picker: Allows user to pick "
                        + "designated color"
                        + "\n"
                        + "\n"
                        + "Pencil Tool: Allows User to Free hand Draw"
                        + "\n"
                        + "\n"
                        + "Line Tool: Allows user to draw straight lines"
                        + "\n"
                        + "\n"
                        + "Rectangle Tool: Allows user to draw a rectangle"
                        + "\n"
                        + "\n"
                        + "Square Tool: Allows user to draw a square"
                        + "\n"
                        + "\n"
                        + "Circle Tool: Allows user to draw a circle"
                        + "\n"
                        + "\n"
                        + "Ellipse Tool: Allows user to draw an oval"
                        + "\n"
                        + "\n"
                        + "Dropper Tool: Allows user to grab whatever color they "
                        + "clicked"
                        + "\n"
                        + "\n"
                        + "Eraser Tool: Allows user to erase anything they draw"
                        + "\n"
                        + "\n"
                        + "Text Tool: Allows user to put whatever text they type "
                        + "in wherever they want on canvas"
                        + "\n"
                        + "\n"
                        + "Redo: Redoes any change to canvas the user makes"
                        + "\n"
                        + "\n"
                        + "Undo: Undoes any changes to the canvas the user makes");
                
                HBox thirdLayout = new HBox();
                thirdLayout.setPadding(new Insets(15, 12, 15, 12));
                thirdLayout.setSpacing(150);  //The spacing between the buttons
                thirdLayout.getChildren().add(notes);
                
                notes.setAlignment(Pos.CENTER);//places the label in middle of hBoc
                
                ScrollPane toolsScroll = new ScrollPane();  //Creates ScrollPane
                //Scrollers only show when the window squeezes the photo
                toolsScroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);  
                toolsScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
                toolsScroll.setContent(thirdLayout);  //adds hbox to scrollpane
                
                
                
                Scene toolsScene = new Scene(toolsScroll, TOOLS_WINDOW_WIDTH, 
                        TOOLS_WINDOW_HEIGHT);
                
                Stage releaseWindow = new Stage();  //creates the new window (stage)
                releaseWindow.setTitle("Tools");
                releaseWindow.setScene(toolsScene);
                
                releaseWindow.show();
            }
        });
        
        /*When "open" is clicked, the FileChooser will allow the user to choose
        a file off of their computer*/
        open.setOnAction(new EventHandler<ActionEvent>()  
        {
             public void handle(ActionEvent e)
             {
                filers.Open();  //Method that opens
             }
        });
        
        save.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
               filers.save();  //Method that saves the canvas
            }
        });
        
        saveAs.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                filer.saveAs();
                thread.start();
            }
        });
        
         exit.setOnAction(new EventHandler<ActionEvent>()
        {
             public void handle(ActionEvent e)
             {
                filer.exit();
                thread.stop();
             }
        });
         
        clock.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                Button yes = new Button("Yes");
                Button no = new Button("No");

                Label question = new Label("Would you like to see the auto save"
                    + " timer?");  //creates Label to ask user question on timer

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

                Stage timerWindow = new Stage();//creates the new window (stage)

                timerWindow.setTitle("Timer");
                timerWindow.setScene(timerScene);
                timerWindow.show();

                yes.setOnAction(new EventHandler<ActionEvent>()  
                {
                    public void handle(ActionEvent e)
                    {
                        clockLabel.setDisable(false);
                        timerWindow.close();
                    }
                });

                no.setOnAction(new EventHandler<ActionEvent>()  
                {
                    public void handle(ActionEvent e)
                    {
                        clockLabel.setDisable(true);
                        timerWindow.close();
                    }
                });
            }
        });
    }
    
    
    /**
     * Creates a window for the user to enter the amount of sides they want on 
     * the polygon
     */
    public void polyWindow()
    {
        Button enter = new Button("Enter");
        //creates Label for textField       
        Label sides = new Label("Number of Sides: "); 
              
        //creates textfield for user to input their number
        TextField input = new TextField();  
        input.setPromptText("Number of Sides");  //adds gint for text field
                
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
                
        grid.add(sides, 0, 1);  //adds label to grid
        grid.add(input, 1, 1);  //adds textfield to grid
        grid.add(enter, 0, 2);  //adds button to grid
           
        //adds the grid to the window
       Scene polyScene = new Scene(grid, POLY_WINDOW_WIDTH, POLY_WINDOW_HEIGHT); 
                
        Stage polyWindow = new Stage();  //creates the new window (stage)
                
        polyWindow.setTitle("Polygon Sides");
        polyWindow.setScene(polyScene);
        polyWindow.show();
                
        enter.setOnAction(new EventHandler<ActionEvent>()  
        {
            public void handle(ActionEvent e)
            {
                //sets universal int variable polygon sides to users input
                polygonSides = Integer.parseInt(input.getText());  
                Dcanvas.polygonSides = polygonSides;
                polyWindow.close();
            }
        });
                
        Dcanvas.choice = 6;  //when user chooses their tool it changes the int 
        //variable to this number
    }
    
     /**
     * Zooms into the canvas by multiplying the canvas dimensions
     */
    public void ZoomIn()
    {
        Dcanvas.canvas.setWidth(Dcanvas.canvas.getWidth() * 2);
        Dcanvas.canvas.setHeight(Dcanvas.canvas.getHeight() * 2);
        //dp.setPrefSize(canvas.getWidth() + 100 , canvas.getHeight()+100);
        Dcanvas.gc.drawImage(Dcanvas.actionsU.peek(), 0, 0, 
                Dcanvas.canvas.getWidth(), Dcanvas.canvas.getHeight());
        Dcanvas.actionsR.clear(); 
    }
      
    /**
     * Zooms out of the canvas by dividing the canvas dimensions
     */
    public void ZoomOut()
    {
        Dcanvas.canvas.setWidth(Dcanvas.canvas.getWidth() / 2);
        Dcanvas.canvas.setHeight(Dcanvas.canvas.getHeight() / 2);
        //dp.setPrefSize(canvas.getWidth() + 100 , canvas.getHeight()+100);
        Dcanvas.gc.drawImage(Dcanvas.actionsU.peek(), 0, 0, 
                Dcanvas.canvas.getWidth(), Dcanvas.canvas.getHeight());
        Dcanvas.actionsR.clear();   
    }
    
}
