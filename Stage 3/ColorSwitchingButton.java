package org.example.timezoneviewer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorSwitchingButton {

  private final String darkModeText = "Dark mode";
  private final String lightModeText = "Light mode";
  private final String baseButtonColorDark = "#483D8B";
  private final String hoverButtonColorDark = "#605CA7";
  private final String baseButtonColorLight = "#D4748E";
  private final String hoverButtonColorLight = "#DD7E9E";
  private final Color defaultText = Color.BLACK;
  private final Color darkModeTextColor = Color.RED;

  private final ToggleButton switchButton;
  private final Label team;
  private final Label countryTime;
  private final Label date;
  private final Label resultDisplay;
  private final Button fetchButton;

  private boolean isRed = false;

  public ColorSwitchingButton(VBox layout, VBox layoutSearchAndResult, Background backgroundPink, UIComponents components) {

    //Make sure to add final, so they cannot be changed somehow.
    switchButton = components.getSwitchButton();
    team = components.getTeamLabel();
    countryTime = components.getCountryTimeLabel();
    date = components.getDateLabel();
    resultDisplay = components.getResultDisplayLabel();
    fetchButton = components.getFetchButton();

    //Make sure enter cannot trigger our button
    switchButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
      if (event.getCode() == KeyCode.ENTER) {
        event.consume(); // Consume the enter key event
      }
    });

    Background backgroundBlack = new Background(new BackgroundFill(
            Color.rgb(55, 55, 55), null, null));

    switchButton.setTextFill(defaultText);
    switchButton.setStyle("-fx-background-color: transparent;");

    switchButton.setOnAction(event -> {
      isRed = !isRed;
      if (isRed) {
        applyColorScheme(layout, layoutSearchAndResult, backgroundBlack, darkModeTextColor,
                baseButtonColorDark, hoverButtonColorDark, lightModeText);
      }
      else {
        applyColorScheme(layout, layoutSearchAndResult, backgroundPink, defaultText,
                baseButtonColorLight, hoverButtonColorLight, darkModeText);
      }
    });
  }

  private void applyColorScheme(VBox layout, VBox layoutSearchAndResult, Background background, Color textColor,
                                String baseButtonColor, String hoverButtonColor, String buttonText) {

    switchButton.setTextFill(textColor);
    switchButton.setBackground(background);
    switchButton.setText(buttonText);

    updateGUIColors(layout, layoutSearchAndResult, background, textColor);

    fetchButton.setStyle("-fx-background-color: " + baseButtonColor + "; -fx-text-fill: white;");
    setFetchButtonHoverEffect(hoverButtonColor, baseButtonColor);
  }

  //This class is for updating specifically UI components colours
  private void updateGUIColors(VBox layout, VBox layoutSearchAndResult,
                               Background background, Color textColor) {

    layout.setBackground(background);
    layoutSearchAndResult.setBackground(background);

    team.setTextFill(textColor);
    team.setBackground(background);

    countryTime.setTextFill(textColor);
    countryTime.setBackground(background);

    date.setTextFill(textColor);
    date.setBackground(background);

    resultDisplay.setTextFill(textColor);
    resultDisplay.setBackground(background);
  }

  private void setFetchButtonHoverEffect(String hoverColor, String baseColor) {
    fetchButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        fetchButton.setStyle("-fx-background-color: " + hoverColor + "; -fx-text-fill: white;");
        fetchButton.setScaleX(1.15); // Increase size by 15% horizontally
        fetchButton.setScaleY(1.15); // Increase size by 15% vertically
      }
      else {
        fetchButton.setStyle("-fx-background-color: " + baseColor + "; -fx-text-fill: white;");
        fetchButton.setScaleX(1.0); // Reset size
        fetchButton.setScaleY(1.0); // Reset size
      }
    });
  }
}