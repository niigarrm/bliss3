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
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneApp extends Application {

    private String countryName;
    @Override
    public void start(Stage primaryStage) {

        //create the fonts and background
        Font font = new Font("Impact", 52);
        Font inputFont = Font.font("Segoe UI", 18);
        Font labelFont = Font.font("Segoe UI", FontWeight.BOLD, 18);
        Background background = new Background(new BackgroundFill(Color.PINK, null, null));

        //create all of our GUI components
        Label team = createTeamLabel(background);
        Label countryTime = createCountryTimeLabel(background, font);
        TextField countryInput = createCountryInput(inputFont);
        Button fetchButton = createFetchButton(labelFont);
        Label date = createDateLabel(background);
        Label resultDisplay = createResultDisplayLabel(font, background);

        //Whenever the button is pressed we take everything from the input box and try to get api result from it.
        fetchButton.setOnAction(e -> fetchTimeData(countryInput, resultDisplay, countryTime, date));

        //Align our components in the GUI
        HBox teamName = new HBox(20, team);
        teamName.setAlignment(Pos.TOP_RIGHT);
        teamName.setPadding(new Insets(10));

        HBox searchBar = new HBox(20, countryInput, fetchButton);
        searchBar.setAlignment(Pos.TOP_CENTER);

        HBox dateTime = new HBox(5, date);
        dateTime.setAlignment(Pos.BASELINE_RIGHT);
        dateTime.setPadding(new Insets(0, 60, 0, 60));

        VBox resultBox = new VBox(10, dateTime, countryTime, resultDisplay);
        resultBox.setAlignment(Pos.CENTER);

        VBox layoutSearchAndResult = new VBox(95, searchBar, resultBox);
        layoutSearchAndResult.setBackground(background);

        VBox layout = new VBox(5, teamName, layoutSearchAndResult);
        layout.setBackground(background);

        Scene scene = new Scene(layout, 780, 500);

        updateTime(countryTime, resultDisplay);

        primaryStage.setTitle("Time Zone Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label createDateLabel(Background background) {
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Label date = new Label("Date: " + localDate);
        Font dateFont = new Font("Impact", 26);
        date.setFont(dateFont);
        date.setBackground(background);
        return date;
    }

    private TextField createCountryInput(Font inputFont) {
        TextField countryInput = new TextField();
        countryInput.setPrefSize(250, 36);
        countryInput.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: #333333;");
        countryInput.setFont(inputFont);
        return countryInput;
    }

    private Button createFetchButton(Font font) {
        Button fetchButton = new Button("Get Time");
        fetchButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        fetchButton.setFont(font);
        setFetchButtonHoverEffect(fetchButton);
        return fetchButton;
    }

    private Label createResultDisplayLabel(Font font, Background background) {
        String localTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        Label resultDisplay = new Label(localTime);
        resultDisplay.setBackground(background);
        resultDisplay.setFont(font);
        return resultDisplay;
    }

    private Label createCountryTimeLabel(Background background, Font font) {
        Label countryTime = new Label("Local time: ");
        countryTime.setBackground(background);
        countryTime.setFont(font);
        return countryTime;
    }

    private Label createTeamLabel(Background background) {
        Label team = new Label("Made by team: Bliss3");
        Font fontForTeam = new Font("Impact", 18);
        team.setBackground(background);
        team.setFont(fontForTeam);
        return team;
    }

    private void setFetchButtonHoverEffect(Button fetchButton) {
        fetchButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // Mouse entered button
                fetchButton.setStyle("-fx-background-color: #66BB6A; -fx-text-fill: white;");
                fetchButton.setScaleX(1.15); // Increase size by 15% horizontally
                fetchButton.setScaleY(1.15); // Increase size by 15% verticall
            } else {
                // Mouse exited button
                fetchButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
                fetchButton.setScaleX(1.0); // Reset size
                fetchButton.setScaleY(1.0); // Reset size
            }
        });
    }

    private void fetchTimeData(TextField countryInput, Label resultDisplay, Label countryTime, Label date) {
        countryName = countryInput.getText(); //take text from the input box
        String result = ApiClient.getTimeZoneData(countryName); //fecth data from api
        int startIndex = result.indexOf("datetime") + 11; // Find the index where the datetime value starts
        int endIndex = result.indexOf("+"); // Find the index where the timezone offset starts
        String datetime = result.substring(startIndex, endIndex); // Extract the datetime string
        String countryDate = datetime.split("T")[0];
        String time = datetime.split("T")[1].split("\\.")[0]; //extract only hours/minutes/seconds

        resultDisplay.setText(time); //display time
        date.setText(countryName + " Date: " + countryDate);
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

                          resultDisplay.setText(time); //display time
                          countryTime.setText(countryName + " time:");
                     }
                  }
                  else{
                      //If the api has not been used yet we update the local time
                      String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                      resultDisplay.setText(currentTime);
                  }
              })
         );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}