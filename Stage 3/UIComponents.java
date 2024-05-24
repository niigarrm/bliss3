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

  //everything is static, so we don't need to initialize UIComponents and can use them directly.
  private static Label dateLabel;
  private static TextField countryInput;
  private static Button fetchButton;
  private static ToggleButton switchButton;
  private static Label resultDisplayLabel;
  private static Label countryTimeLabel;
  private static Label teamLabel;

  // Public static factory methods to create UI components
  public static Label createDateLabel(Background background, Color textColor) {
    initializeDateLabel(background, textColor);
    return dateLabel;
  }

  public static TextField createCountryInput(Font inputFont) {
    initializeCountryInput(inputFont);
    return countryInput;
  }

  public static Button createFetchButton(Font font) {
    initializeFetchButton(font);
    return fetchButton;
  }

  public static ToggleButton createSwitchButton() {
    initializeSwitchButton();
    return switchButton;
  }

  public static Label createResultDisplayLabel(Font font, Background background, Color textColor) {
    initializeResultDisplayLabel(font, background, textColor);
    return resultDisplayLabel;
  }

  public static Label createCountryTimeLabel(Background background, Font font, Color textColor) {
    initializeCountryTimeLabel(background, font, textColor);
    return countryTimeLabel;
  }

  public static Label createTeamLabel(Background background, Color textColor) {
    initializeTeamLabel(background, textColor);
    return teamLabel;
  }

  public static Label getDateLabel() {
    return dateLabel;
  }

  public static TextField getCountryInput() {
    return countryInput;
  }

  public static Button getFetchButton() {
    return fetchButton;
  }

  public static ToggleButton getSwitchButton() {
    return switchButton;
  }

  public static Label getResultDisplayLabel() {
    return resultDisplayLabel;
  }

  public static Label getCountryTimeLabel() {
    return countryTimeLabel;
  }

  public static Label getTeamLabel() {
    return teamLabel;
  }

  // Private static methods to initialize UI components
  private static void initializeDateLabel(Background background, Color textColor) {
    String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    dateLabel = new Label("Date: " + localDate);
    dateLabel.setFont(new Font("Impact", 26));
    dateLabel.setTextFill(textColor);
    dateLabel.setBackground(background);
  }

  private static void initializeCountryInput(Font inputFont) {
    countryInput = new TextField();
    countryInput.setPrefSize(250, 36);
    countryInput.setStyle("-fx-background-color: #f0f0f0; -fx-text-fill: #333333;");
    countryInput.setFont(inputFont);
  }

  private static void initializeFetchButton(Font font) {
    fetchButton = new Button("Get Time");
    fetchButton.setStyle("-fx-background-color: #DB7093; -fx-text-fill: white;");
    fetchButton.setFont(font);
    setFetchButtonHoverEffect(fetchButton);
  }

  private static void initializeSwitchButton() {
    switchButton = new ToggleButton("Dark mode");
    switchButton.setFont(new Font("Impact", 18));
  }

  private static void initializeResultDisplayLabel(Font font, Background background, Color textColor) {
    String localTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    resultDisplayLabel = new Label(localTime);
    resultDisplayLabel.setBackground(background);
    resultDisplayLabel.setTextFill(textColor);
    resultDisplayLabel.setFont(font);
  }

  private static void initializeCountryTimeLabel(Background background, Font font, Color textColor) {
    countryTimeLabel = new Label("Local time: ");
    countryTimeLabel.setBackground(background);
    countryTimeLabel.setTextFill(textColor);
    countryTimeLabel.setFont(font);
  }

  private static void initializeTeamLabel(Background background, Color textColor) {
    teamLabel = new Label("Made by team: Bliss3");
    teamLabel.setBackground(background);
    teamLabel.setTextFill(textColor);
    teamLabel.setFont(new Font("Impact", 18));
  }

  // Private static method for setting fetch button hover effect
  private static void setFetchButtonHoverEffect(Button fetchButton) {
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