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
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        Color textColor = Color.BLACK;

        //create all of our GUI components
        Label team = UIComponents.createTeamLabel(background, textColor);
        Label countryTime = UIComponents.createCountryTimeLabel(background, font, textColor);
        TextField countryInput = UIComponents.createCountryInput(inputFont);
        Button fetchButton = UIComponents.createFetchButton(labelFont);
        Label date = UIComponents.createDateLabel(background, textColor);
        Label resultDisplay = UIComponents.createResultDisplayLabel(font, background, textColor);
        ToggleButton switchButton = UIComponents.createSwitchButton();

        //Whenever the button is pressed we take everything from the input box and try to get api result from it.
        fetchButton.setOnAction(e -> fetchTimeData(countryInput, resultDisplay, countryTime, date));

        //Align our components in the GUI
        HBox teamName = new HBox(495, switchButton, team);
        teamName.setAlignment(Pos.TOP_CENTER);
        teamName.setPadding(new Insets(5, 10, 5, 10));

        HBox searchBar = new HBox(15, countryInput, fetchButton);
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

        new ColorSwitchingButton(switchButton, layout, layoutSearchAndResult, team, countryTime,
                date, resultDisplay, fetchButton, background);

        //Make it so enter acts the same way as pressing the button
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fetchTimeData(countryInput, resultDisplay, countryTime, date);
            }
        });

        updateTime(countryTime, resultDisplay);

        primaryStage.setTitle("Time Zone Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchTimeData(TextField countryInput, Label resultDisplay, Label countryTime, Label date)  {
        countryName = countryInput.getText(); //take text from the input box
        String result = ApiClient.getTimeZoneData(countryName); //fetch data from api
        if (result.contains("datetime")) {
            int startIndex = result.indexOf("datetime") + 11; // Find the index where the datetime value starts
            int endIndex = result.indexOf("+"); // Find the index where the timezone offset starts
            String datetime = result.substring(startIndex, endIndex); // Extract the datetime string
            String countryDate = datetime.split("T")[0];
            String time = datetime.split("T")[1].split("\\.")[0]; //extract only hours/minutes/seconds

            resultDisplay.setText(time); //display time
            String capitalName = countryName.split("/")[1]; //get the Capital name
            capitalName = capitalName.toLowerCase(); //Make the whole user input to lower case
            //Make it so only the first letter is capitalized
            String capital = capitalName.substring(0,1).toUpperCase() + capitalName.substring(1);
            date.setText(capital + " Date: " + countryDate);
            countryTime.setText(capital + " time:"); //update from local time to user's input country
            countryInput.setText(""); //clear the search bar
        }
        else{
            countryInput.setText("Error incorrect Timezone");
        }
    }
    private void updateTime(Label countryTime, Label resultDisplay) {
        Timeline timeline;
        timeline = new Timeline(
              new KeyFrame(Duration.seconds(1), e -> {
                  if (!countryTime.getText().equals("Local time: ")) {
                      String timeString = resultDisplay.getText();
                      LocalTime time = LocalTime.parse(timeString);
                      time = time.plusSeconds(1);
                      String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                      resultDisplay.setText(formattedTime); // Update resultDisplay with the new time
                  }
                  else{
                      //If the api has not been used, yet we update the local time
                      String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                      resultDisplay.setText(currentTime);
                  }
              })
         );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}