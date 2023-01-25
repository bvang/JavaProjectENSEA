import javafx.animation.AnimationTimer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;

public class Earth extends Group {

    private Rotate ry = new Rotate();
    public Sphere getEarth() {
        return sph;
    }
    private Sphere sph;
    private ArrayList<Sphere> yellowSphere;

    public Earth() {
        this.ry = ry;
        this.sph = sph;
        yellowSphere = new ArrayList<Sphere>();
        sph = new Sphere(300);
        this.getChildren().add(sph);
        this.getTransforms().add(ry);
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image("file:./data/earth_lights_4800.png"));
        sph.setMaterial(material);
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long time) {
                //System.out.println("Valeur de time : " + time);
                ry.setAngle(time/50000000);
                ry.setAxis(new Point3D(0,1,0));
            }
        };
        animation.start();
    }
}
