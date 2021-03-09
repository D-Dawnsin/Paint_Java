/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pain.t;

import java.util.Stack;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author dawso
 */
public class DCanvas {
    
    protected Canvas canvas;
    protected GraphicsContext gc;
    private Pane dp;
    protected final int CANVAS_WIDTH = 1750;
    protected final int CANVAS_HEIGHT = 950;
    private double mouseClickX, mouseClickY;
    private double xClick, startY, startX, yClick ;
    protected int choice;
    protected Stack<Image> actionsU = new Stack();
    protected Stack<Image> actionsR = new Stack();
    protected Stack<Image> mainPicture = new Stack();
    private double zI;
    protected ScrollPane canvasScroll;
    private ImageView imgView;
    private PixelReader pr;
    private Line line;
    private Rectangle rect;
    private Circle circ;
    private Ellipse oval;
    private Polygon poly;
    private Rectangle cropRect;
    private Rectangle selectRect;
    private ImageView cropImage;
    private Image image;
    private int cropWidth, cropHeight;
    protected int x = 0;
    private TextArea textA = new TextArea();
    protected int polygonSides = 0;
    private ColorPicker colorPicker = new ColorPicker();
    
    
    /**
     * This is the constructor for the DCanvas class that creates the canvas
     * and handles all mouse events
     * @param x
     * @param y 
     */
    public DCanvas (double x, double y)
    {
        canvas = new Canvas(x, y);  //Creates a canvas
        //creates a graphic context with the canvas inside
        gc = canvas.getGraphicsContext2D();  
        
        dp = new Pane(canvas);  //puts a drawing pain on the canvas
        
        canvasScroll = new ScrollPane();
        //Scrollers only show when the window squeezes the photo
        canvasScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);  
        canvasScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        canvasScroll.setContent(dp);  //Adds the canvas toa scrollpane
        
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_WIDTH); 
        
        //adds screenshot of canvas after edit to stack of images
        addToStack(screenshot());  
        
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
            new EventHandler<MouseEvent>()
            {
                /*This method makes it so that when one presses their mouse, the
                canvas will remember where the mouse was first pressed (event x)*/ 
                public void handle(MouseEvent event) 
                /*and follow the mouse until it stops (event y) and then makes
                        this path a stroke on the canvas*/
                {
                            
                    /*Depending on what button the user chooses then the number 
                    for choice will change to the number assosiated with the 
                    tool and go through the switch statements when they press 
                    their mouse*/
                    switch (choice)  
                    {
                        case 0:  //pencil
                        {
                            gc.beginPath();
                            gc.moveTo(event.getX(), event.getY());
                            //Gets the color chosen by the user for the stroke
                            gc.setStroke(colorPicker.getValue());  
                            gc.stroke();
                            break;
                        }

                        case 1:  //line
                        {
                            //creates line object
                            line = new Line(event.getX(), event.getY(), 
                                    event.getX(), event.getY());  
                            line.setStroke(colorPicker.getValue());
                            line.setStrokeWidth(gc.getLineWidth());
                            line.setFill(colorPicker.getValue());
                            dp.getChildren().add(line);//adds line to drawing pane
                            gc.beginPath();
                            //sets starting point of line 
                            gc.moveTo(event.getX(), event.getY());  
                            //sets color of stroke to the users choice from the color picker
                            gc.setStroke(colorPicker.getValue()); 
                            //sets the width of line to the width chose under the width button
                            gc.setLineWidth(gc.getLineWidth());  
                            break;
                        }

                        case 2:  //rectangle
                        {
                            rect = new Rectangle(event.getX() , event.getY() 
                                    , 0 , 0);  //creates rectangle
                            rect.setStroke(colorPicker.getValue());
                            rect.setStrokeWidth(gc.getLineWidth());
                            rect.setFill(colorPicker.getValue());
                            //adds rectangle to drawing pane
                            dp.getChildren().add(rect);  
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX();  
                            //variables to keep track of first click y
                            startY = event.getY(); 
                            //sets starting point of line 
                            gc.moveTo(event.getX(), event.getY());  
                            //Gets the color chosen by the user for the stroke
                            gc.setStroke(colorPicker.getValue());  
                            /*sets the width of line to the width chose under 
                            the width button*/
                            gc.setLineWidth(gc.getLineWidth());  
                            break;
                        }

                        case 3:  //square
                        {
                            rect = new Rectangle(event.getX() , event.getY() , 
                                    0 , 0);  //creates rectangle
                            rect.setStroke(colorPicker.getValue());
                            rect.setStrokeWidth(gc.getLineWidth());
                            rect.setFill(colorPicker.getValue());
                             //adds rectangle to drawing pane
                            dp.getChildren().add(rect); 
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX(); 
                            //variables to keep track of first click y
                            startY = event.getY();  
                            //sets starting point of line
                            gc.moveTo(event.getX(), event.getY()); 
                            //Gets the color chosen by the user for the stroke
                            gc.setStroke(colorPicker.getValue());  
                            /*sets the width of line to the width chose under 
                            the width button*/
                            gc.setLineWidth(gc.getLineWidth());  
                           break;
                        }

                        case 4:  //circle
                        {
                            startX = event.getX();
                            startY = event.getY();
                            //creates circle object
                            circ = new Circle(startX, startY, 0);  
                            circ.setStroke(colorPicker.getValue());
                            circ.setStrokeWidth(gc.getLineWidth());
                            circ.setFill(colorPicker.getValue());
                            //adds line to drawing pane
                            dp.getChildren().add(circ);  
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX();
                            //variables to keep track of first click y
                            startY = event.getY();  
                            //sets starting point of line
                            gc.moveTo(event.getX(), event.getY());
                            //Gets the color chosen by the user for the stroke
                            gc.setStroke(colorPicker.getValue());
                            /*sets the width of line to the width chose under 
                            the width button*/ 
                            gc.setLineWidth(gc.getLineWidth());  
                            break;  //gc.arc()
                        }

                        case 5:  //ellipse
                        {
                            oval = new Ellipse(event.getX() , event.getY(), 
                                    0 , 0);  //creates ellipse
                            oval.setStroke(colorPicker.getValue());
                            oval.setStrokeWidth(gc.getLineWidth());
                            oval.setFill(colorPicker.getValue());
                            //adds oval to drawing pane
                            dp.getChildren().add(oval);  
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX(); 
                            //variables to keep track of first click y
                            startY = event.getY();
                            //sets starting point of line
                            gc.moveTo(event.getX(), event.getY());
                            //Gets the color chosen by the user for the stroke
                            gc.setStroke(colorPicker.getValue());  
                            /*sets the width of line to the width chose under 
                            the width button*/ 
                            gc.setLineWidth(gc.getLineWidth()); 
                            break;  //gc.setStrokeOval()
                        }

                        case 6:  //polygon
                        {
                            poly = new Polygon();
                            dp.getChildren().add(poly);
                            startX = event.getX();
                            startY = event.getY();
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX();
                            //variables to keep track of first click y
                            startY = event.getY(); 
                            //sets starting point of line
                            gc.moveTo(event.getX(), event.getY());
                            //Gets the color chosen by the user for the stroke
                            gc.setStroke(colorPicker.getValue());
                            /*sets the width of line to the width chose under 
                            the width button*/
                            gc.setLineWidth(gc.getLineWidth());  

                            break;
                        }

                        case 7:  //round rectangle
                        {
                            rect = new Rectangle(event.getX() , event.getY(), 
                                    0 , 0);  //creates rectangle
                            rect.setStroke(colorPicker.getValue());
                            rect.setStrokeWidth(gc.getLineWidth());
                            rect.setFill(colorPicker.getValue());
                            //adds rectangle to drawing pane
                            dp.getChildren().add(rect);  
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX();
                            //variables to keep track of first click y
                            startY = event.getY();  
                            //sets starting point of line
                            gc.moveTo(event.getX(), event.getY());  
                            //Gets the color chosen by the user for the stroke
                            gc.setStroke(colorPicker.getValue()); 
                            /*sets the width of line to the width chose under 
                            the width button*/
                            gc.setLineWidth(gc.getLineWidth()); 
                            break;
                        }

                        case 8:  //dropper
                        {
                            image = screenshot();
                            pr = image.getPixelReader();
                            int x = (int) event.getX();
                            int y = (int) event.getY();
                            Color c = pr.getColor(x , y);
                            colorPicker.setValue(c);
                            break;
                        }

                        case 9:  //eraser
                        {
                            gc.beginPath();
                            gc.moveTo(event.getX(), event.getY());
                            gc.setStroke(Color.WHITE);  //Makes color equal color of canvas
                            gc.stroke();
                            break;
                        }

                        case 10:  //Stops all tools from working
                        {
                            break;
                        }

                        case 11:  //text tool
                        {
                            gc.setFont(Font.font("Verdana", 100));
                            gc.setFill(colorPicker.getValue());
                            /*sets color of stroke to the users choice from the 
                            color picker*/
                            gc.setStroke(colorPicker.getValue());  
                            gc.strokeText(textA.getText(), event.getX(),
                                    event.getY());
                            gc.fillText(textA.getText() , event.getX(),
                                    event.getY());
                            break;
                        }

                        case 12:  //cropping
                        {
                            cropRect = new Rectangle(event.getX() , event.getY(),
                                    0 , 0);  //creates rectangle
                            cropRect.setStroke(Color.BLACK);
                            cropRect.setFill(Color.TRANSPARENT);
                            cropRect.setStrokeWidth(gc.getLineWidth());
                            //adds rectangle to drawing pane
                            dp.getChildren().add(cropRect);  
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX();  
                            //variables to keep track of first click y
                            startY = event.getY();  
                            break;
                        }

                         case 13:  //moving cropped image
                        {
                            gc.beginPath();
                            Paint tempColor = gc.getFill();
                            gc.setFill(Color.WHITE);
                            gc.fillRect(cropRect.getX(), cropRect.getY(), 
                                    cropRect.getWidth(), cropRect.getHeight());
                            gc.setFill(tempColor);
                            gc.closePath();

                            Image selectedImage = mainPicture.peek();
                            PixelReader pr = selectedImage.getPixelReader();
                            WritableImage newImage = new WritableImage(pr, 
                                    (int) cropRect.getX(), (int) cropRect.getY(),
                                    (int) cropRect.getWidth(), 
                                    (int) cropRect.getHeight());
                            
                            cropImage = new ImageView(newImage);
                            cropImage.setX(event.getX());
                            cropImage.setY(event.getY());
                            dp.getChildren().add(cropImage);
                            break;
                        }

                        case 14:  //cropping for stamp
                        {
                            cropRect = new Rectangle(event.getX() ,
                                    event.getY() , 0 , 0);  //creates rectangle
                            cropRect.setStroke(Color.BLACK);
                            cropRect.setFill(Color.TRANSPARENT);
                            cropRect.setStrokeWidth(gc.getLineWidth());
                            //adds rectangle to drawing pane
                            dp.getChildren().add(cropRect);  
                            gc.beginPath();
                            //variables to keep track of first click x
                            startX = event.getX(); 
                            //variables to keep track of first click y
                            startY = event.getY();  
                            break;
                        }

                        case 15:  //moving cropped stamp image
                        {                             
                            startX = event.getX();
                            startY = event.getY(); 
                            Image selectedImage = mainPicture.peek();
                            PixelReader pr = selectedImage.getPixelReader();
                            WritableImage newImage = new WritableImage(pr, 
                                    (int) cropRect.getX(), (int) cropRect.getY(),
                                    cropWidth, cropHeight);
                            
                            cropImage = new ImageView(newImage);
                            cropImage.setX(event.getX());
                            cropImage.setY(event.getY());
                            dp.getChildren().add(cropImage);
                            break;
                        }
                    }

                }
            });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
            new EventHandler<MouseEvent>()
            {
                /*This method draws the line over the path made in the above 
                method but fills it in so that the user can see it*/
                public void handle(MouseEvent event)  
                {
                    switch (choice)
                    {
                        case 0:  //pencil
                        {
                            //creats stroke with the drag
                            gc.lineTo(event.getX(), event.getY());
                            //gets color user has chosen
                            gc.setStroke(colorPicker.getValue());  
                            gc.stroke();  //makes drag stroke
                            break;
                        }

                        case 1:  //line
                        {
                            //follows the mouse for user to draw shape on a pane 
                            line.setEndX(event.getX());  
                            line.setEndY(event.getY());
                            break;
                        }

                        case 2:  //rectagle
                        {
                            //follows the mouse for user to draw shape on a pane
                            rect.setWidth(event.getX() - startX);  
                            rect.setHeight(event.getY() - startY);
                            break;
                        }

                        case 3:  //square
                        {
                            //follows the mouse for user to draw shape on a pane
                            rect.setWidth(event.getY() - startY);  
                            rect.setHeight(event.getY() - startY);
                            break;
                        }

                        case 4:  //circle
                        {
                            double radius = (event.getY() - startY)/2;
                            //follows the mouse for user to draw shape on a pane
                            circ.setCenterX(startX + radius);  
                            circ.setCenterY(startY + radius);
                            circ.setRadius(radius);
                            break;
                        }

                        case 5:  //elipse
                        {
                            double radiusX = (event.getX() -startX)/2;
                            double radiusY = (event.getY() -startY)/2;  
                            oval.setRadiusX((event.getX() -startX)/2);
                            oval.setRadiusY((event.getY() -startY)/2);
                            //follows the mouse for user to draw shape on a pane
                            oval.setCenterX(startX - radiusX);  
                            oval.setCenterY(startY - radiusY);
                            break;
                        }

                        case 6:  //polygon
                        {
                            poly.getPoints().addAll(event.getX(), event.getY());
                            final double angleStep = Math.PI * 2 / polygonSides;
                            double radius = Math.sqrt(((event.getX()-startX)*
                                    (event.getX()-startX)) + 
                                    ((event.getY()-startY)*
                                            (event.getY()-startY)));
                            
                            double angle = Math.atan2(event.getY()-startY, 
                                    event.getX()-startX);

                            for (int i = 0; i < polygonSides; i++, 
                                    angle += angleStep) 
                            {
                                poly.getPoints().addAll(Math.cos(angle) * 
                                        radius + startX, Math.sin(angle) * 
                                                radius + startY);
                            }

                            poly.setStroke(colorPicker.getValue());
                            poly.setStrokeWidth(gc.getLineWidth());
                            poly.setFill(colorPicker.getValue());

                            break;
                        }

                        case 7:  //round rectangle
                        {
                            //follows the mouse for user to draw shape on a pane
                            rect.setWidth(event.getX() - startX);  
                            rect.setHeight(event.getY() - startY);
                            rect.setArcHeight(50);
                            rect.setArcWidth(50);
                            break;
                        }

                        case 8:  //dropper
                        {
                            break;
                        }

                        case 9:  //eraser
                        {
                            //creats stroke with the drag
                            gc.lineTo(event.getX(), event.getY());
                            //makes color pathing white (same color as coanvas
                            gc.setStroke(Color.WHITE);  
                            gc.stroke();  //makes drag stroke
                            break;
                        }

                        case 10:  //Stops all tools from working
                        {
                            break;
                        }

                        case 11:  //text tool
                        {
                            break;
                        }

                        case 12:  //cropping
                        {
                            //follows the mouse for user to draw shape on a pane
                            cropRect.setWidth(event.getX() - startX);  
                            cropRect.setHeight(event.getY() - startY);
                            break;
                        }

                         case 13:  //moving cropped image
                        { 
                            cropImage.setX(event.getX());
                            cropImage.setY(event.getY());
                            break;
                        }

                        case 14:  //cropping for stamp
                        {
                            //follows the mouse for user to draw shape on a pane
                            cropRect.setWidth(event.getX() - startX);  
                            cropRect.setHeight(event.getY() - startY);
                            break;
                        }

                        case 15:  //moving cropped stamp
                        {
                            cropImage.setX(event.getX());
                            cropImage.setY(event.getY());
                            break;
                        }

                    }

                }
            });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, 
            new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent event) 
                {  
                    switch (choice)
                    {
                        case 0:  //pencil
                        {
                            gc.closePath();
                            //pencilT.setSelected(false);  //Diselects button
                            break;
                        }

                        case 1:  //line
                        {
                            dp.getChildren().remove(line);
                            /*makes a stroke between the first mouse press and 
                            the mouse release*/
                            gc.lineTo(event.getX(), event.getY());  
                            gc.stroke();  //Makes the stroke between the points
                            gc.closePath();
                            break;
                        }

                        case 2:  //rectangle
                        {
                            dp.getChildren().remove(rect);
                            //sets fill color to colorpicker
                            gc.setFill(colorPicker.getValue()); 
                            //creates a rectangle on the canvas
                            gc.rect(startX , startY, event.getX() - startX, 
                                    event.getY() - startY);  
                            gc.stroke();
                            gc.fill();  //fills the rectangle shape
                            gc.closePath();
                            break;
                        }

                        case 3:  //square
                        {
                            dp.getChildren().remove(rect);
                            //sets fill color to colorpicker
                            gc.setFill(colorPicker.getValue());
                            
                            /*creates a square on canvas by taking the 
                            difference between y-value of the unclick and the 
                            y-value click and uses thpse as height and width*/
                            gc.rect(startX , startY, event.getY()-startY, 
                                    event.getY()-startY);  
                            gc.stroke();
                            gc.fill();  //fills the square shape
                            gc.closePath();
                            break;
                        }

                        case 4:  //cricle
                        {
                            dp.getChildren().remove(circ);
                            //sets fill color to colorpicker
                            gc.setFill(colorPicker.getValue());  
                            
                            /*creates cirlce by making an oval and treating it 
                            the same way I treated the square made from a rectangle*/
                            gc.strokeOval(startX, startY, event.getY()-startY, 
                                    event.getY()-startY);  
                            
                            //fills the entire circle with users color choice
                            gc.fillOval(startX, startY, event.getY()-startY, 
                                    event.getY()-startY);  
                            gc.stroke();
                            gc.closePath();
                            break;
                        }

                        case 5:  //elipse
                        {
                            dp.getChildren().remove(oval);
                            gc.setFill(colorPicker.getValue());  //sets fill color to colorpicker
                            
                            /*creates oval by using orignal click of mouse and 
                            subtracting the difference between startx and endx 
                            as width and difference between startY and endY as 
                            height*/
                            gc.strokeOval(startX, startY, event.getX() - startX,
                                    event.getY()-startY); 
                            
                            //fils the entire oval with users color choice
                            gc.fillOval(startX, startY, event.getX()-startX,
                                    event.getY()-startY);  
                            gc.stroke();
                            gc.closePath();
                            break;
                        }

                        case 6:  //polygon
                        {
                            dp.getChildren().remove(poly);
                            //sets fill color to colorpicker
                            gc.setFill(colorPicker.getValue());  
                            gc.stroke();
                            gc.fill();  //fills the square shape
                            gc.closePath();
                            drawPolygon(canvas, polygonSides, startX, startY, 
                                    event.getX(), event.getY());//creates the polygon
                            //polygon.setSelected(false);  //Diselects button
                            //random.setSelected(false);  //Diselects button
                            break;
                        }

                        case 7:  //round rectangle
                        {
                            dp.getChildren().remove(rect);
                            //sets fill color to colorpicker
                            gc.setFill(colorPicker.getValue()); 
                            
                            //creates a round rectangle on the canvas
                            gc.strokeRoundRect(startX , startY, event.getX() - 
                                    startX, event.getY() - startY, 50, 50 );  
                            
                            //fills te round rectangle with color
                            gc.fillRoundRect(startX , startY, event.getX() - 
                                    startX, event.getY() - startY, 50, 50 );  
                            gc.stroke();
                            gc.fill();  //fills the rectangle shape
                            gc.closePath();
                            //roundR.setSelected(false);  //Diselects button
                            //random.setSelected(false);  //Diselects button
                            break;
                        }

                        case 8:  //dropper
                        {
                            //dropper.setSelected(false);  //Diselects button
                            break;
                        }

                        case 9:  //eraser
                        {
                            gc.closePath();  //closes the eraser path
                            //eraser.setSelected(false);  //Diselects button
                            break;
                        }

                        case 10:  //Stops all tools from working
                        {
                            break;
                        }

                        case 11:  //text tool
                        {
                            //text.setSelected(false);  //Diselects button
                            break;
                        }

                        case 12:  //cropping
                        {
                            int width = (int)(event.getX() - startX);
                            int height = (int)(event.getY() - startY);
                            dp.getChildren().remove(cropRect);
                            gc.closePath();
                            choice = 13;
                            break;
                        }

                         case 13:  //moving cropped image
                        {
                            dp.getChildren().remove(cropImage);
                            gc.drawImage(cropImage.getImage(), event.getX(), 
                                    event.getY(), cropRect.getWidth(), 
                                    cropRect.getHeight());
                            choice = 10;
                            break;
                        }

                        case 14:  //cropping
                        {
                            cropWidth = (int)(event.getX() - startX);
                            cropHeight = (int)(event.getY() - startY);
                            dp.getChildren().remove(cropRect);
                            gc.closePath();
                            choice = 15;
                            break;
                        }

                        case 15:  //stamping the picture
                        {
                            dp.getChildren().remove(cropImage);
                            gc.drawImage(cropImage.getImage(), event.getX(), 
                                    event.getY(), cropRect.getWidth(), 
                                    cropRect.getHeight());
                            choice = 10;
                            break;
                        }
                    }
                    if(choice != 13  && choice != 15)
                        //Sets choice to 10 so that the mouse stops doing anything
                        choice = 10;
                    //adds screenshot of canvas after edit to stack of images
                    addToStack(screenshot());  
                }
        });
    }
    
    /**
     * Pushes an image onto a stack
     * @param i 
     */
    public void addToStack(Image i)
    {
        actionsU.push(i);
    }
    
    /**
      * Creates a Image that consists of the canvas (a snapshot of the canvas)
      * @return Image
      */
    public Image screenshot()
    {
         //makes image size of canvas
        WritableImage tempImage = new WritableImage(CANVAS_WIDTH, CANVAS_HEIGHT); 
        /*makes the tempImage that was only as big as canvas now equal to a 
        screenshot of the current canvas*/
        canvas.snapshot(null, tempImage);  
        ImageView imageView = new ImageView(tempImage);  
        return imageView.getImage();  
    }
    
    /**
     * Sets the ColorPicker Value to the value chosen in the SuperMenu class
     * @param cp 
     */
    public void setColorPicker(ColorPicker cp)
    {
        colorPicker = cp;
    }
    
    /**
     * Sets the TextArea to the TextArea from the SuperMenu class
     * @param ta 
     */
    public void setTextArea(TextArea ta)
    {
        textA = ta;
    }
    
     /**
     * Takes the number of sides user dictates and makes the arrays that size +1
     * the first paired point is the user's first mouse click
     * Then the method performs math (based off of the user's first click and 
     * mouse release) to create the other paired points that represent the 
     * vertices of the polygon once both arrays are filled with the paired 
     * points then a polygon is drawn onto the canvas
     * @param canvas
     * @param numPoints
     * @param xClickPoint
     * @param yClickPoint
     * @param secondClickX
     * @param secondClickY
     */
     public void drawPolygon(Canvas canvas, int numPoints, double xClickPoint, 
             double yClickPoint, double secondClickX, double secondClickY){
        double[] xPoints = new double[numPoints+1];
        double[] yPoints = new double[numPoints+1];
       
        xPoints[0] = secondClickX;
        yPoints[0] = secondClickY;
       
        final double angleStep = Math.PI * 2 / numPoints;
        
        double radius = Math.sqrt(((secondClickX-xClickPoint)*
                (secondClickX-xClickPoint)) + ((secondClickY-yClickPoint)*
                        (secondClickY-yClickPoint)));
        
        double angle = Math.atan2(secondClickY-yClickPoint, 
                secondClickX-xClickPoint);
       
        for (int i = 0; i < numPoints+1; i++, angle += angleStep) {
            xPoints[i] = Math.cos(angle) * radius + xClickPoint;
            yPoints[i] = Math.sin(angle) * radius + yClickPoint;
        }
       
        gc.fillPolygon(xPoints, yPoints, numPoints+1);

       
        gc.strokePolygon(xPoints, yPoints, numPoints+1);
    }  //creates the real polygon that is on the canvas
}
