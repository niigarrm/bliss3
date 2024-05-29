package org.example.timezoneviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    // Define the UI components
    Background background = new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY));
    Color textColor = Color.BLACK;
    Font inputFont = new Font("Arial", 14);
    Font labelFont = new Font("Arial", 16);

    UIComponents uiComponents = new UIComponents(background, textColor, inputFont, labelFont);

    // Create a layout and add UI components to it
    VBox root = new VBox(10); // VBox with 10px spacing between elements
    root.setPadding(new Insets(15)); // Padding around the VBox
    root.getChildren().addAll(
            uiComponents.getDateLabel(),
            uiComponents.getCountryInput(),
            uiComponents.getFetchButton(),
            uiComponents.getSwitchButton(),
            uiComponents.getContinentCityDropdown(), // Add the ComboBox here
            uiComponents.getCountryTimeLabel(),
            uiComponents.getResultDisplayLabel(),
            uiComponents.getTeamLabel()
    );

    // Set up the scene and stage
    Scene scene = new Scene(root, 400, 600);
    primaryStage.setTitle("Time Zone Viewer");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
