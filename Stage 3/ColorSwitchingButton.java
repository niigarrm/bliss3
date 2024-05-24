package org.example.timezoneviewer;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorSwitchingButton {

  private boolean isRed = false;

  public ColorSwitchingButton(ToggleButton switchButton, VBox layout, VBox layoutSearchAndResult,
                              Label team, Label countryTime, Label date, Label resultDisplay,
                              Button fetchButton, Background backgroundPink) {

    Background backgroundBlack = new Background(new BackgroundFill(
            Color.rgb(55, 55, 55), null, null));

    Color defaultText = Color.BLACK;

    switchButton.setTextFill(defaultText);
    switchButton.setStyle("-fx-background-color: transparent;");

    switchButton.setOnAction(event -> {
      // When we press the button we change the value of isRed, so it will switch to a different color.
      isRed = !isRed;

      Color textColor;
      Background background;
      if (isRed) {
        background = backgroundBlack;
        textColor = Color.RED;
        switchColour(switchButton, layout, layoutSearchAndResult, team, countryTime,
                date, resultDisplay, fetchButton, background, textColor, "#483D8B", "#605CA7");
        switchButton.setText("Light mode");
      } else {
        background = backgroundPink;
        switchColour(switchButton, layout, layoutSearchAndResult, team, countryTime,
                date, resultDisplay, fetchButton, background, defaultText, "#D4748E", "#DD7E9E");
        switchButton.setText("Dark mode");
      }
    });
  }

  private void switchColour(ToggleButton switchButton, VBox layout, VBox layoutSearchAndResult,
                            Label team, Label countryTime, Label date, Label resultDisplay,
                            Button fetchButton, Background background, Color textColor,
                            String baseButtonColor, String hoverButtonColor) {

    switchButton.setTextFill(textColor);
    switchButton.setBackground(background);

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

    // Update fetchButton base color
    fetchButton.setStyle("-fx-background-color: " + baseButtonColor + "; -fx-text-fill: white;");
    // Update fetchButton hover effect colors
    setFetchButtonHoverEffect(fetchButton, hoverButtonColor, baseButtonColor);
  }

  private void setFetchButtonHoverEffect(Button fetchButton, String hoverColor, String baseColor) {
    fetchButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        // Mouse entered button area
        fetchButton.setStyle("-fx-background-color: " + hoverColor + "; -fx-text-fill: white;");
        fetchButton.setScaleX(1.15); // Increase size by 15% horizontally
        fetchButton.setScaleY(1.15); // Increase size by 15% vertically
      } else {
        // Mouse exited button area
        fetchButton.setStyle("-fx-background-color: " + baseColor + "; -fx-text-fill: white;");
        fetchButton.setScaleX(1.0); // Reset size
        fetchButton.setScaleY(1.0); // Reset size
      }
    });
  }
}
