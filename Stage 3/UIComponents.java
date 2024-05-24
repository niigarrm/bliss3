package org.example.timezoneviewer;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UIComponents {

  public static Label createDateLabel(Background background, Color textColor) {
    String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    Label date = new Label("Date: " + localDate);
    Font dateFont = new Font("Impact", 26);
    date.setFont(dateFont);
    date.setTextFill(textColor);
    date.setBackground(background);
    return date;
  }

  public static TextField createCountryInput(Font inputFont) {
    TextField countryInput = new TextField();
    countryInput.setPrefSize(250, 36);
    countryInput.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: #333333;");
    countryInput.setFont(inputFont);
    return countryInput;
  }

  public static Button createFetchButton(Font font) {
    Button fetchButton = new Button("Get Time");
    fetchButton.setStyle("-fx-background-color: #DB7093; -fx-text-fill: white;");
    fetchButton.setFont(font);
    setFetchButtonHoverEffect(fetchButton);
    return fetchButton;
  }

  public static ToggleButton createSwitchButton() {
    ToggleButton switchButton = new ToggleButton("Dark mode");
    Font fontForButton = new Font("Impact", 18);
    switchButton.setFont(fontForButton);
    return switchButton;
  }

  public static Label createResultDisplayLabel(Font font, Background background, Color textColor) {
    String localTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    Label resultDisplay = new Label(localTime);
    resultDisplay.setBackground(background);
    resultDisplay.setTextFill(textColor);
    resultDisplay.setFont(font);
    return resultDisplay;
  }

  public static Label createCountryTimeLabel(Background background, Font font, Color textColor) {
    Label countryTime = new Label("Local time: ");
    countryTime.setBackground(background);
    countryTime.setTextFill(textColor);
    countryTime.setFont(font);
    return countryTime;
  }

  public static Label createTeamLabel(Background background, Color textColor) {
    Label team = new Label("Made by team: Bliss3");
    Font fontForTeam = new Font("Impact", 18);
    team.setBackground(background);
    team.setTextFill(textColor);
    team.setFont(fontForTeam);
    return team;
  }

  private static void setFetchButtonHoverEffect(Button fetchButton) {
    fetchButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        // Mouse entered button
        fetchButton.setStyle("-fx-background-color: #DD7E9E; -fx-text-fill: white;");
        fetchButton.setScaleX(1.15); // Increase size by 15% horizontally
        fetchButton.setScaleY(1.15); // Increase size by 15% vertically
      } else {
        // Mouse exited button
        fetchButton.setStyle("-fx-background-color: #DB7093; -fx-text-fill: white;");
        fetchButton.setScaleX(1.0); // Reset size
        fetchButton.setScaleY(1.0); // Reset size
      }
    });
  }
}
