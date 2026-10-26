package csc372.module3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * MenuApp shows a window with a menu bar of four options: print the date and time in the
 * text box, save the text box to log.txt, change the background to a random green hue,
 * and exit the program.
 */
public class MenuApp extends Application {

    private static final DateTimeFormatter DATE_TIME =
            DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy 'at' h:mm:ss a");
    private static final Path LOG_FILE = Path.of("log.txt");

    // Picked once per execution, so the green option shows the same hue every time it is used.
    private final String greenHue = toHex(randomGreen(new Random()));

    private final TextArea textBox = new TextArea();
    private final Label statusLabel = new Label("Choose an option from the Menu.");
    private final BorderPane root = new BorderPane();

    @Override
    public void start(Stage stage) {
        MenuItem dateTimeItem = new MenuItem("Print Date and Time");
        MenuItem saveItem = new MenuItem("Save Text Box to log.txt");
        MenuItem greenItem = new MenuItem("Change Background to Green " + greenHue);
        MenuItem exitItem = new MenuItem("Exit");

        dateTimeItem.setOnAction(event -> printDateTime());
        saveItem.setOnAction(event -> saveTextBox());
        greenItem.setOnAction(event -> changeBackground());
        exitItem.setOnAction(event -> exitProgram());

        Menu menu = new Menu("Menu");
        menu.getItems().addAll(dateTimeItem, saveItem, greenItem, exitItem);

        textBox.setPromptText("Menu output appears here. You can also type in this box.");
        textBox.setWrapText(true);
        statusLabel.setStyle("-fx-background-color: white; -fx-padding: 4 8;");

        root.setTop(new MenuBar(menu));
        root.setCenter(textBox);
        root.setBottom(statusLabel);
        BorderPane.setMargin(textBox, new Insets(20));
        BorderPane.setMargin(statusLabel, new Insets(0, 20, 15, 20));

        stage.setTitle("CSC372 Module 3 - Menu Application");
        stage.setScene(new Scene(root, 640, 420));
        stage.show();
        System.out.println("Program started. Green hue for this run: " + greenHue);
    }

    /** Menu option 1: adds the current date and time to the text box. */
    private void printDateTime() {
        String now = LocalDateTime.now().format(DATE_TIME);
        textBox.appendText(now + "\n");
        report("Printed the date and time: " + now);
    }

    /** Menu option 2: writes the text box contents to log.txt. */
    private void saveTextBox() {
        String text = textBox.getText();
        if (text.isBlank()) {
            report("Nothing to save. The text box is empty.");
            return;
        }
        try {
            Files.writeString(LOG_FILE, text);
            report("Saved the text box to " + LOG_FILE.toAbsolutePath());
        } catch (IOException e) {
            report("Could not write log.txt: " + e.getMessage());
        }
    }

    /** Menu option 3: sets the window background to this run's random green hue. */
    private void changeBackground() {
        root.setStyle("-fx-background-color: " + greenHue + ";");
        report("Background changed to green hue " + greenHue);
    }

    /** Menu option 4: closes the program. */
    private void exitProgram() {
        System.out.println("Exiting the program.");
        Platform.exit();
    }

    private void report(String message) {
        statusLabel.setText(message);
        System.out.println(message);
    }

    /** Returns a random green: hue 100-140 degrees with varied saturation and brightness. */
    private static Color randomGreen(Random random) {
        double hue = 100 + random.nextDouble() * 40;
        double saturation = 0.5 + random.nextDouble() * 0.5;
        double brightness = 0.4 + random.nextDouble() * 0.5;
        return Color.hsb(hue, saturation, brightness);
    }

    /** Converts a color to an HTML hex code such as #3CB371. */
    private static String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                Math.round(color.getRed() * 255),
                Math.round(color.getGreen() * 255),
                Math.round(color.getBlue() * 255));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
