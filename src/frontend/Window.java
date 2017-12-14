package frontend;

import backend.Coordinate;
import backend.Sprite;
import backend.Velocity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Window extends Application {

    private final static int NUM_SNOWFLAKES = 10;

    private long prevTime;
    private Pane root;
    private Canvas canvas;
    private ArrayList<Sprite> spriteList;
    private GraphicsContext gc;

    private Coordinate dimensions;
    private Planet planet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        dimensions = new Coordinate(1000, 900);

        spriteList = new ArrayList<>();
        Sprite.setSpriteList(spriteList);

        root = new Pane();
        root.setPrefSize(dimensions.getX(), dimensions.getY());
        Scene scene = new Scene(root);

        canvas = new Canvas(dimensions.getX(), dimensions.getY());
        root.getChildren().add(canvas);

        planet = new Planet(new Velocity(0, 0), canvas);

        gc = canvas.getGraphicsContext2D();

        for (int i=0; i<NUM_SNOWFLAKES; i++) {
        }
        new Snowflake(planet, gc, new Coordinate(250, 250), 3, 20);
        new Snowflake(planet, gc, new Coordinate(200, 110), 3, 20);
        new Snowflake(planet, gc, new Coordinate(120, 350), 3, 20);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Recursion Drawing");
        primaryStage.show();

        prevTime = System.nanoTime();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long curTime) {
                onUpdate((curTime - prevTime) / 1E9);
                prevTime = curTime;
            }
        };
        timer.start();
    }

    private void onUpdate(double deltaTime) {

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);

        //System.out.println(1/deltaTime);
        for (Sprite sprite : spriteList) {
            sprite.update();
        }
    }
}
