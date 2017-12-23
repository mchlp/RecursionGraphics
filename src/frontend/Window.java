/*
 * Michael Pu
 * RecursionGraphics - Window
 * December 22, 2017
 */

package frontend;

import backend.Coordinate;
import backend.Sprite;
import backend.Velocity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Window extends Application {

    private static final State DEFAULT_STATE = State.NORMAL;

    private long prevTime;
    private Stage primaryStage;
    private Pane root;
    private Canvas canvas;
    private ArrayList<Sprite> spriteList;
    private GraphicsContext gc;

    private Planet planet;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();

        spriteList = new ArrayList<>();
        Sprite.setSpriteList(spriteList);

        root = new Pane();
        root.setPrefSize(screen.getWidth(), screen.getHeight());
        Scene scene = new Scene(root);

        canvas = new Canvas(screen.getWidth(), screen.getHeight());
        root.getChildren().add(canvas);

        planet = new Planet(canvas, DEFAULT_STATE);

        Sky sky = new Sky(planet);
        Ground ground = new Ground(planet, Color.LIGHTGREY);

        Text text = new Text();
        root.getChildren().add(text);
        Clock clock = new Clock(sky, text);

        gc = canvas.getGraphicsContext2D();

        int numSnowflake = (int) (screen.getWidth()*screen.getHeight()*DEFAULT_STATE.getSnowflakePerPixel());

        // point snowflakes
        for (int i=0; i<numSnowflake; i++) {
            double xPos = (Math.random());
            double yPos = (Math.random());
            double size = Math.random()*3+5;
            new SnowflakePoint(planet, new Coordinate(xPos, yPos), size);
        }


        // large snowflakes
        /*
        for (int i = 0; i < NUM_SNOWFLAKES; i++) {
            double xPos = (Math.random() * dimensions.getX());
            double yPos = (Math.random() * dimensions.getY());
            int level = 3;
            int len = (int) (Math.random()*10 + 10);
            new Snowflake(planet, new Coordinate(xPos, yPos), level, len);
        }*/


        primaryStage.setScene(scene);
        primaryStage.setTitle("Snowing...");
        primaryStage.setMaximized(true);
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

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateDimensions();
            }
        });

        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                updateDimensions();
            }
        });
    }

    private void updateDimensions() {
        Rectangle2D screen = new Rectangle2D(primaryStage.getX(), primaryStage.getY(), primaryStage.getWidth(), primaryStage.getHeight());
        planet.setDimesions(screen);
        canvas.setWidth(screen.getWidth());
        canvas.setHeight(screen.getHeight());
    }

    private void onUpdate(double deltaTime) {

        planet.update(deltaTime);

        //System.out.println(1/deltaTime);
        for (Sprite sprite : spriteList) {
            sprite.update(deltaTime);
        }
    }
}
