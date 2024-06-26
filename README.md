# bliss3
## Digital Clock Project
Our project aims to develop a Java application that allows users to view the current time and time zone of any country by simply typing in it’s name. This project uses Java programming language and utilizes an external API to fetch data. The application will also feature a graphical user interface (GUI) to enhance user experience and interaction.

## How to use
1. Download and input the files from 'Stage3' directory to your javafx project.
2. The structure should look like this:
```
project-name
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── your-package-name
│   │   │       ├── Main.java
│   │   │       ├── ColorSwitchingButton.java
│   │   │       ├── TimeZoneOptions.java
│   │   │       ├── UIComponents.java
│   │   │       ├── ApiClient.java
│   │   │       └── TimeZoneApp.java
│   │   └── resources
│   │       └── Options.txt
```
4. Run the project. Make sure the txt file is in correct place otherwise the code will not work.
5. Choose the timezone from the list.
6. Press the button or enter on your keyboard to see the timezone Date and Time update live.
7. Whenever you want to close the project hit the X button on the window or stop the project in your IDE.

## Installing guide: 
1. First you will need to download javafx sdk for your specific machine from here: https://gluonhq.com/products/javafx/  
2. Extract sdk file.
3. Create a new javafx project.
4. Add javafx sdk to the project. Go to the left top corner and press file/project structure/+/java/find your sdk file/ok/apply/ok
5. In case you are having trouble setting up javafx on intellij refer to the following video for reference: https://www.youtube.com/watch?v=Ope4icw6bVk 

### Key Components:
1.	API Integration: Use an API that provides accurate and up-to-date information about time zones worldwide. The API fetches time zone data based on country names which the user provides.
2.	Data Retrieval: Implement functionality to fetch time zone data from the API based on user input (country name). Process the retrieved data to extract the current time and time zone information from the string result by the API.
3.	Graphical User Interface (GUI): Design and develop a user-friendly GUI using Java. The GUI should include input fields for users to enter the country name and display areas to show the current time and time zone information.
4.	Input Validation: Implement input validation mechanisms to ensure that the user-provided country name is valid and exists in the API database. If the country does not exist provide the user with an error message.
5.	Real-time Updates: Incorporate functionality to update the displayed time and time zone information in real-time. Use timers or other suitable mechanisms to periodically refresh the data fetched from the API.

### Project Workflow:
1.	User Input: The user launches the application and is presented with a GUI interface containing an input field to enter the country name. By default the local time should also be displayed 
2.	API: Upon entering the country name and triggering the search action, the application sends a request to the external API to retrieve time zone data for the specified country. If a country does not exist, then an error message informing the user is displayed.
3.	Data Processing: The application receives the response from the API and processes the data to extract the current time and time zone information.
4.	GUI Update: The extracted time and time zone information are displayed on the GUI interface in designated display areas.
5.	Real-time Updates: The application continuously updates the displayed time and time zone information at regular intervals.


