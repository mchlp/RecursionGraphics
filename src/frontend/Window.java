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
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Window extends Application {

    private final static int NUM_SNOWFLAKES = 20;

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

        for (int i = 0; i < NUM_SNOWFLAKES; i++) {
            double xPos = (Math.random() * dimensions.getX());
            double yPos = (Math.random() * dimensions.getY());
            int level = 3;
            int len = (int) (Math.random()*10 + 20);
            new Snowflake(planet, gc, new Coordinate(xPos, yPos), level, len);
        }

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

        gc.setFill(Color.rgb(46, 14, 73));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.LIGHTGREY);
        gc.fillOval(-(dimensions.getX()*(1.5-1))/2, dimensions.getY() - 50/2 - 50, dimensions.getX()*1.5, 100);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);

        planet.update(deltaTime);

        //System.out.println(1/deltaTime);
        for (Sprite sprite : spriteList) {
            sprite.update(deltaTime);
        }
    }
}
