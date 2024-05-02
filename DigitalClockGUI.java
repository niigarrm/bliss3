import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DigitalClockGUI extends JFrame {
  private JLabel timeLabel;
  private JTextField countryTextField;
  private Timer timer;

  public DigitalClockGUI() {
    setTitle("Digital Clock");
    setSize(720, 580); //Predifined openning size
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes the program close if you exit it.

    // Create input field for country and change text colour
    JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    inputPanel.setBackground(Color.BLACK);
    JLabel countryLabel = new JLabel("Enter Country:");
    countryLabel.setForeground(Color.RED);
    countryLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    countryTextField = new JTextField(20);
    countryTextField.setBackground(Color.white);
    inputPanel.add(countryLabel);
    inputPanel.add(countryTextField);
    add(inputPanel, BorderLayout.NORTH); //add the inputPanel for text on the top of the window

    // Create time label
    timeLabel = new JLabel();
    timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    timeLabel.setFont(new Font("Arial", Font.PLAIN, 100));
    timeLabel.setForeground(Color.RED); // Set text color to white
    timeLabel.setBackground(Color.BLACK); //set background colour
    timeLabel.setOpaque(true);
    add(timeLabel, BorderLayout.CENTER);

    // Start the timer to update time
    timer = new Timer(1000, e -> updateTime(null));
    timer.start();
  }

  private void updateTime(String timeZone) {
    Calendar calendar = Calendar.getInstance(); //get the local time
    if (timeZone != null) {
      calendar.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String timeString = sdf.format(calendar.getTime());

    timeLabel.setText(timeString);
  }

  public static void main(String[] args) {
    //update the time and make sure the new time is visible
    SwingUtilities.invokeLater(() -> new DigitalClockGUI().setVisible(true));
  }
}
