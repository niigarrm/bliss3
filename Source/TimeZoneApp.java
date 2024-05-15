package org.example.timezoneviewer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneApp extends Application {

    private String countryName;
    @Override
    public void start(Stage primaryStage) {
        /*
        The following lines until fetch button are for creating our fields that will be displayed in our GUI.
        We set their text position, text size, font, size and background colour.
         */
        Font font = new Font("Impact", 60);
        Label team = new Label("Made by team: Bliss3");
        Font fontForTeam = new Font("Impact", 18);
        team.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        team.setFont(fontForTeam);

        Label countryTime = new Label("Local time: ");
        countryTime.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        countryTime.setFont(font);
        countryTime.setAlignment(Pos.CENTER);

        TextField countryInput = new TextField();
        countryInput.setPrefSize(250, 15);

        Button fetchButton = new Button("Get Time");
        fetchButton.hoverProperty();

        String localTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Label resultDisplay = new Label(localTime);
        resultDisplay.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
        resultDisplay.setFont(font);
        resultDisplay.setAlignment(Pos.CENTER);

        //Whenever the button is pressed we take everything from the input box and try to get api result from it.
        fetchButton.setOnAction(e -> {
            fetchTimeData(countryInput, resultDisplay, countryTime);
        });

        HBox teamName = new HBox(20, team);
        teamName.setAlignment(Pos.TOP_RIGHT);
        teamName.setPadding(new Insets(5));

        HBox searchBar = new HBox(20, countryInput, fetchButton);
        searchBar.setAlignment(Pos.TOP_CENTER);

        VBox resultBox = new VBox(10, countryTime, resultDisplay);
        resultBox.setAlignment(Pos.CENTER);

        VBox layoutSearchAndResult = new VBox(135, searchBar, resultBox);
        layoutSearchAndResult.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

        VBox layout = new VBox(5, teamName, layoutSearchAndResult);
        layout.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

        Scene scene = new Scene(layout, 780, 500);

        updateTime(countryTime, resultDisplay);

        primaryStage.setTitle("Time Zone Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchTimeData(TextField countryInput, Label resultDisplay, Label countryTime) {
        countryName = countryInput.getText(); //take text from the input box
        String result = ApiClient.getTimeZoneData(countryName); //fecth data from api
        int startIndex = result.indexOf("datetime") + 11; // Find the index where the datetime value starts
        int endIndex = result.indexOf("+"); // Find the index where the timezone offset starts
        String datetime = result.substring(startIndex, endIndex); // Extract the datetime string
        String time = datetime.split("T")[1].split("\\.")[0]; //extract only hours/minutes/seconds

        resultDisplay.setText(time); //display time
        countryTime.setText(countryName + " time:"); //update from local time to user's input country
    }

    private void updateTime(Label countryTime, Label resultDisplay) {
        Timeline timeline;
        timeline = new Timeline(
              new KeyFrame(Duration.seconds(1), e -> {
                  if (!countryTime.getText().equals("Local time: ")) {
                      String result = ApiClient.getTimeZoneData(countryName);
                      if (result.contains("datetime")) {
                          int startIndex = result.indexOf("datetime") + 11;
                          int endIndex = result.indexOf("+");
                          String datetime = result.substring(startIndex, endIndex);
                          String time = datetime.split("T")[1].split("\\.")[0];

                          resultDisplay.setText(time);
                          countryTime.setText(countryName + " time:");
                     }
                  }
                  else{
                      String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                      resultDisplay.setText(currentTime);
                  }
              })
         );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}