# CSC372 Module 3 Critical Thinking – Menu Application

JavaFX application with a top menu bar of four options:

1. **Print Date and Time** – adds the current date and time to the text box
2. **Save Text Box to log.txt** – writes the text box contents to `log.txt`
3. **Change Background to Green #xxxxxx** – sets the window background to a random green hue chosen once per run (the hue is shown in the menu item)
4. **Exit** – closes the program

## Files

| File | Purpose |
|------|---------|
| `src/main/java/csc372/module3/MenuApp.java` | JavaFX `Application` with `MenuBar`, `Menu`, `MenuItem`, `TextArea`, and event handlers |
| `src/main/java/module-info.java` | Module declaration (`requires javafx.controls`) |
| `pom.xml` | Maven build with the OpenJFX 17 dependency |

## Run

In IntelliJ IDEA, open the folder (Maven loads JavaFX) and run `MenuApp`, or from a terminal:

```
mvn javafx:run
```

Built for Java 17.
