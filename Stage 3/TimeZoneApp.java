package org.example.timezoneviewer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeZoneApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create the fonts and background
        Font font = new Font("Impact", 52);
        Font inputFont = Font.font("Segoe UI", 18);
        Background background = new Background(new BackgroundFill(Color.PINK, null, null));
        Color textColor = Color.BLACK;

        UIComponents components = new UIComponents(background, textColor, inputFont, font);

        // Get all of our GUI components
        Label team = components.getTeamLabel();
        Label countryTime = components.getCountryTimeLabel();
        Button fetchButton = components.getFetchButton();
        Label date = components.getDateLabel();
        Label resultDisplay = components.getResultDisplayLabel();
        ToggleButton switchButton = components.getSwitchButton();
        ComboBox<String> continentCityDropdown = components.getContinentCityDropdown();

        // Whenever the button is pressed we take the selected value from the dropdown and try to get API result from it.
        fetchButton.setOnAction(e -> fetchTimeData(continentCityDropdown, resultDisplay, countryTime, date));

        // Align our components in the GUI
        HBox teamName = new HBox(495, switchButton, team);
        teamName.setAlignment(Pos.TOP_CENTER);
        teamName.setPadding(new Insets(5, 10, 5, 10));

        HBox searchBar = new HBox(15, continentCityDropdown, fetchButton);
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

        // Initialize so that pressing the color switch button actually switches the color
        new ColorSwitchingButton(layout, layoutSearchAndResult, background, components);

        // Make it so enter acts the same way as pressing the button
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                fetchTimeData(continentCityDropdown, resultDisplay, countryTime, date);
            }
        });

        updateTime(countryTime, resultDisplay);

        primaryStage.setTitle("Time Zone Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchTimeData(ComboBox<String> continentCityDropdown, Label resultDisplay, Label countryTime, Label date) {
        String countryName = continentCityDropdown.getValue(); // Take selected value from the dropdown
        String result = ApiClient.getTimeZoneData(countryName); // Fetch data from API
        if (result.contains("datetime")) {
            int startIndex = result.indexOf("datetime") + 11; // Find the index where the datetime value starts
            int endIndex = result.indexOf("+"); // Find the index where the timezone offset starts
            String datetime = result.substring(startIndex, endIndex); // Extract the datetime string
            String countryDate = datetime.split("T")[0];
            String time = datetime.split("T")[1].split("\\.")[0]; // Extract only hours/minutes/seconds

            resultDisplay.setText(time); // Display time
            String capitalName = countryName.split("/")[1]; // Get the Capital name
            capitalName = capitalName.toLowerCase(); // Make the whole user input to lower case
            // Make it so only the first letter is capitalized
            String capital = capitalName.substring(0, 1).toUpperCase() + capitalName.substring(1);
            date.setText(capital + " Date: " + countryDate);
            countryTime.setText(capital + " time:"); // Update from local time to user's input country
        } else {
            resultDisplay.setText("Error: incorrect Timezone");
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
                    } else {
                        // If the API has not been used yet, we update the local time
                        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        resultDisplay.setText(currentTime);
                    }
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
