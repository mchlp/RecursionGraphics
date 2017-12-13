import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Application {

    private final static int SIDE_LENGTH = 16;

    private long prevTime;
    private Pane root;
    private Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        root = new Pane();
        root.setPrefSize(600, 600);

        canvas = new Canvas(600,600);
        root.getChildren().clear();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
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

    private void draw() {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);
        drawSnowflake(300, 300, 20, gc, 3);
        drawSnowflake(350, 450, 20, gc, 3);
        drawSnowflake(430, 120, 20, gc, 3);
    }

    private void drawSnowflake(double x, double y, double len, GraphicsContext gc, int level) {
        if (level == 0) {
            return;
        }
        int add = 60;
        int angle = -30;
        for (int i=0; i<6; i++) {
            double xOffset = Math.cos(Math.toRadians(angle)) * len;
            double yOffset = Math.sin(Math.toRadians(angle)) * len;
            gc.strokeLine(x, y, x+xOffset, y+yOffset);
            drawSnowflake(x+xOffset, y+yOffset, len/3, gc, level-1);
            angle += add;
        }
    }


    private void onUpdate(double deltaTime) {
        System.out.println(1/deltaTime);
        draw();
    }
}
