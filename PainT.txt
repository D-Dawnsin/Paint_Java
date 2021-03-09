package pain.t;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author dawso
 */
public class PainT extends Application {

    private final int CANVAS_WIDTH = 1750;
    private final int CANVAS_HEIGHT = 950;
    private ScrollPane scrollPane; 
    private VBox root;
    
    final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);

    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Microsoft Pain(t)");

        //DCanvas object
        DCanvas dynamicCanvas = new DCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);  
        
        //Filer Object
        Filers filer = new Filers(dynamicCanvas, primaryStage);  
        
        //SuperMenu Object
        SuperMenu superMenu = new SuperMenu(dynamicCanvas, filer);  

        dynamicCanvas.setColorPicker(superMenu.colorPicker);
        dynamicCanvas.setTextArea(superMenu.textA);
        
        //creates my timer thread object
        Clock timer = new Clock(filer, superMenu.timer);  
        
        //adds both menubar and toolbar to window
        root = new VBox(superMenu.menuBar, superMenu.toolBar);  
        
        scrollPane = new ScrollPane();  //Creates ScrollPane
        //Scrollers only show when the window squeezes the photo
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);  
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        
        //adds scrollPane to main root (vbox)
        root.getChildren().add(dynamicCanvas.canvasScroll);  

        scrollPane.setContent(root);  //Adds the root (Window) to the scrollPane

        Scene scene = new Scene(scrollPane, 
                dynamicCanvas.gc.getCanvas().getWidth(),
                dynamicCanvas.gc.getCanvas().getHeight());  
       
        
        primaryStage.setScene(scene);
        
        primaryStage.show();  
    }
    
    /**
     * launches the program 
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


