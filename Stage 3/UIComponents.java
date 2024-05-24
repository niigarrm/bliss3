package org.example.timezoneviewer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UIComponents {

  private Label dateLabel;
  private TextField countryInput;
  private Button fetchButton;
  private ToggleButton switchButton;
  private Label resultDisplayLabel;
  private Label countryTimeLabel;
  private Label teamLabel;
  public UIComponents(Background background, Color textColor, Font inputFont, Font font ){
    initializeDateLabel(background, textColor);
    initializeCountryInput(inputFont);
    initializeFetchButton(inputFont);
    initializeSwitchButton();
    initializeCountryTimeLabel(background, font, textColor);
    initializeTeamLabel(background, textColor);
    initializeResultDisplayLabel(font, background, textColor);
  }

  public Label getDateLabel() {
    return dateLabel;
  }

  public TextField getCountryInput() {
    return countryInput;
  }

  public Button getFetchButton() {
    return fetchButton;
  }

  public ToggleButton getSwitchButton() {
    return switchButton;
  }

  public  Label getResultDisplayLabel() {
    return resultDisplayLabel;
  }

  public Label getCountryTimeLabel() {
    return countryTimeLabel;
  }

  public Label getTeamLabel() {
    return teamLabel;
  }

  // Private static methods to initialize UI components
  private void initializeDateLabel(Background background, Color textColor) {
    String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    dateLabel = new Label("Date: " + localDate);
    dateLabel.setFont(new Font("Impact", 26));
    dateLabel.setTextFill(textColor);
    dateLabel.setBackground(background);
  }

  private void initializeCountryInput(Font inputFont) {
    countryInput = new TextField();
    countryInput.setPrefSize(250, 36);
    countryInput.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: #333333;");
    countryInput.setFont(inputFont);
  }

  private void initializeFetchButton(Font font) {
    fetchButton = new Button("Get Time");
    fetchButton.setStyle("-fx-background-color: #DB7093; -fx-text-fill: white;");
    fetchButton.setFont(font);
    setFetchButtonHoverEffect(fetchButton);
  }

  private void initializeSwitchButton() {
    switchButton = new ToggleButton("Dark mode");
    switchButton.setFont(new Font("Impact", 18));
  }

  private void initializeResultDisplayLabel(Font font, Background background, Color textColor) {
    String localTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    resultDisplayLabel = new Label(localTime);
    resultDisplayLabel.setBackground(background);
    resultDisplayLabel.setTextFill(textColor);
    resultDisplayLabel.setFont(font);
  }

  private void initializeCountryTimeLabel(Background background, Font font, Color textColor) {
    countryTimeLabel = new Label("Local time: ");
    countryTimeLabel.setBackground(background);
    countryTimeLabel.setTextFill(textColor);
    countryTimeLabel.setFont(font);
  }

  private void initializeTeamLabel(Background background, Color textColor) {
    teamLabel = new Label("Made by team: Bliss3");
    teamLabel.setBackground(background);
    teamLabel.setTextFill(textColor);
    teamLabel.setFont(new Font("Impact", 18));
  }

  // Private static method for setting fetch button hover effect
  private void setFetchButtonHoverEffect(Button fetchButton) {
    fetchButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        // Mouse entered button
        fetchButton.setStyle("-fx-background-color: #DD7E9E; -fx-text-fill: white;");
        fetchButton.setScaleX(1.15); // Increase size by 15% horizontally
        fetchButton.setScaleY(1.15); // Increase size by 15% vertically
      }
      else {
        // Mouse exited button
        fetchButton.setStyle("-fx-background-color: #DB7093; -fx-text-fill: white;");
        fetchButton.setScaleX(1.0); // Reset size
        fetchButton.setScaleY(1.0); // Reset size
      }
    });
  }
}