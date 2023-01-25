import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Interface extends Application {
    Translate tz = new Translate();
    private double clickX;
    private double clickY;

    @Override
    public void start(Stage primaryStage) throws Exception{
        World world = new World ("./data/airport-codes_no_comma.csv");
        primaryStage.setTitle("Hello world");
        Earth earth = new Earth();
        Pane pane = new Pane(earth);
        Scene ihm = new Scene(pane, 800, 600,true);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(3000.0);
        camera.setFieldOfView(35);
        ihm.setCamera(camera);

        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                System.out.println("Clicked on : (" + event.getSceneX()+ ", "+ event.getSceneY()+")");
                clickX = event.getSceneX();
                clickY = event.getSceneY();
            }
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                tz.setZ((event.getSceneY() - clickY)*0.1);
                camera.getTransforms().add(tz);
            }
        });

        ihm.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton()== MouseButton.SECONDARY && event.getEventType()==MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();
                if (pickResult.getIntersectedNode() != null) {
                    Point2D point2d=pickResult.getIntersectedTexCoord();
                    //conversion long et lat
                    double latitude=2*Math.toDegrees(Math.atan(Math.exp((0.5-point2d.getY())/0.2678))-(Math.PI/4));
                    double longitude=360*(point2d.getX()-0.5);
                    Aeroport aeroproche = world.findNearestAirport(latitude, longitude);
                    System.out.println("Position : (" + longitude +", " +latitude);
                    System.out.println("aeroport le plus proche : " + aeroproche.toString());
                }
            }
        });

        primaryStage.setScene(ihm);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}