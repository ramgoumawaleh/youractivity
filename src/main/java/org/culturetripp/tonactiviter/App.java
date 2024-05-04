
package org.culturetripp.tonactiviter;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application {
    private double x=0;
    private double y=0;



    @Override
    public void start(Stage stage) throws Exception {
        Parent parent  = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        Scene scene = new Scene(parent);

        parent.setOnMousePressed((MouseEvent event) ->{

            x=event.getSceneX();
            y=event.getSceneY();
        });
        parent.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });



        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
