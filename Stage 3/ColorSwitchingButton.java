package org.example.timezoneviewer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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

  private boolean isRed = false;

  public ColorSwitchingButton(VBox layout, VBox layoutSearchAndResult, Background backgroundPink, UIComponents components) {

    //Make sure to add final, so they cannot be changed somehow.
    final ToggleButton switchButton = components.getSwitchButton();
    final Label team = components.getTeamLabel();
    final Label countryTime = components.getCountryTimeLabel();
    final Label date = components.getDateLabel();
    final Label resultDisplay = components.getResultDisplayLabel();
    final Button fetchButton = components.getFetchButton();

    Background backgroundBlack = new Background(new BackgroundFill(
            Color.rgb(55, 55, 55), null, null));

    switchButton.setTextFill(defaultText);
    switchButton.setStyle("-fx-background-color: transparent;");

    switchButton.setOnAction(event -> {
      isRed = !isRed;
      if (isRed) {
        applyColorScheme(switchButton, layout, layoutSearchAndResult, team, countryTime,
                date, resultDisplay, fetchButton, backgroundBlack, darkModeTextColor,
                baseButtonColorDark, hoverButtonColorDark, lightModeText);
      }
      else {
        applyColorScheme(switchButton, layout, layoutSearchAndResult, team, countryTime,
                date, resultDisplay, fetchButton, backgroundPink, defaultText,
                baseButtonColorLight, hoverButtonColorLight, darkModeText);
      }
    });
  }

  private void applyColorScheme(ToggleButton switchButton, VBox layout, VBox layoutSearchAndResult,
                                Label team, Label countryTime, Label date, Label resultDisplay,
                                Button fetchButton, Background background, Color textColor,
                                String baseButtonColor, String hoverButtonColor, String buttonText) {

    switchButton.setTextFill(textColor);
    switchButton.setBackground(background);
    switchButton.setText(buttonText);

    updateGUIColors(layout, layoutSearchAndResult, team, countryTime, date, resultDisplay, background, textColor);

    fetchButton.setStyle("-fx-background-color: " + baseButtonColor + "; -fx-text-fill: white;");
    setFetchButtonHoverEffect(fetchButton, hoverButtonColor, baseButtonColor);
  }

  //This class is for updating specifically UI components colours
  private void updateGUIColors(VBox layout, VBox layoutSearchAndResult,
                               Label team, Label countryTime, Label date, Label resultDisplay,
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

  private void setFetchButtonHoverEffect(Button fetchButton, String hoverColor, String baseColor) {
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