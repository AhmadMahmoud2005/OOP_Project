package project;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Gui {
    private Stage primaryStage;
    private TableView<TrafficLight> trafficLightTable;
    public static Scene mainScene;
    public static ArrayList<Owner> owners = Owner.owners;
    public static NotificationSystem notificationSystem;
    public LoginPage loginPage;
    public String css;

    public Gui(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.notificationSystem = new NotificationSystem();
        notificationSystem.checkNotification();
        this.loginPage = new LoginPage(primaryStage);
        this.css = getClass().getResource("global-style.css").toExternalForm();
    }

    public void start () {
        // Main Menu Layout
        VBox mainMenu = new VBox(10);
        Label welcomeLabel = new Label("WELCOME TO OUR PROJECT");
        Button ownerButton = new Button("Owner");
        Button adminButton = new Button("Admin");
        Button trafficOfficerButton = new Button("Traffic Officer");
        Button loadTrafficLightsButton = new Button("Load Traffic Lights");

        mainMenu.getChildren().addAll(welcomeLabel, ownerButton, adminButton, trafficOfficerButton, loadTrafficLightsButton);
        mainScene = new Scene(mainMenu, 600, 500);

        // Admin Menu Layout
        VBox adminMenu = new VBox(10);
        Label adminLabel = new Label("Admin Menu");
        Button addTrafficLightButton = new Button("Add Traffic Lights");
        Button updateTrafficButton = new Button("Update Traffic Lights");
        Button deleteTrafficButton = new Button("Delete Traffic Lights");
        Button viewViolationsAdminButton = new Button("View Violations");
        Button generateReportButton = new Button("Generate Reports");
        Button adminBackButton = new Button("Back to Main Menu");

        adminMenu.getChildren().addAll(adminLabel, addTrafficLightButton, updateTrafficButton, deleteTrafficButton, viewViolationsAdminButton, generateReportButton, adminBackButton);
        Scene adminScene = new Scene(adminMenu, 600, 500);

        // Traffic Officer Menu Layout
        VBox officerMenu = new VBox(10);
        Label officerLabel = new Label("Traffic Officer Menu");
        Button recordViolationsButton = new Button("Record Violations");
        Button viewAllViolationsButton = new Button("View All Violations");
        Button sendNotificationButton = new Button("Send Notification to Owner");
        Button officerBackButton = new Button("Back to Main Menu");

        officerMenu.getChildren().addAll(officerLabel, recordViolationsButton, viewAllViolationsButton, sendNotificationButton, officerBackButton);
        Scene officerScene = new Scene(officerMenu, 600, 500);

        // Load Traffic Lights Layout
        VBox loadTrafficLightsMenu = new VBox(10);
        Label loadLabel = new Label("Traffic Lights");
        this.trafficLightTable = new TableView<>();
        this.makeTrafficLightTable();
        Button loadBackButton = new Button("Back to Main Menu");

        loadTrafficLightsMenu.getChildren().addAll(loadLabel, trafficLightTable, loadBackButton);
        Scene loadTrafficLightsScene = new Scene(loadTrafficLightsMenu, 600, 500);

        ArrayList<VBox> pages = new ArrayList<>(Arrays.asList(mainMenu, adminMenu, officerMenu));
        this.centralizePages(pages);

        // Main Menu Button Actions
        ownerButton.setOnAction(e -> primaryStage.setScene(loginPage.getScene()));
        adminButton.setOnAction(e -> primaryStage.setScene(adminScene));
        trafficOfficerButton.setOnAction(e -> primaryStage.setScene(officerScene));

        loadTrafficLightsButton.setOnAction(e -> {
            makeTrafficLightTable(); // Ensure table is populated
            loadTrafficLightsMenu.getChildren().setAll(loadLabel, trafficLightTable, loadBackButton); // Replace children
            primaryStage.setScene(loadTrafficLightsScene); // Reuse existing scene
        });

        // Admin functionalities
        addTrafficLightButton.setOnAction(e -> primaryStage.setScene(addTL(adminScene)));
        updateTrafficButton.setOnAction(e -> primaryStage.setScene(updateTL(adminScene)));
        deleteTrafficButton.setOnAction(e -> primaryStage.setScene(deleteTL(adminScene)));
        generateReportButton.setOnAction(e -> primaryStage.setScene(renderReport(adminScene)));

        // Officer functionalities
        recordViolationsButton.setOnAction(e -> primaryStage.setScene(renderRecordViolations(officerScene)));
        sendNotificationButton.setOnAction(e -> primaryStage.setScene(renderSendNotification(officerScene)));

        // Viewing Violations
        viewAllViolationsButton.setOnAction(e -> primaryStage.setScene(renderAllViolations(officerScene)));
        viewViolationsAdminButton.setOnAction(e -> primaryStage.setScene(renderViolations(adminScene)));

        // Back buttons
        adminBackButton.setOnAction(e -> primaryStage.setScene(mainScene));
        officerBackButton.setOnAction(e -> primaryStage.setScene(mainScene));
        loadBackButton.setOnAction(e -> primaryStage.setScene(mainScene));

        // Styling All Scenes
        mainScene.getStylesheets().add(css);
        adminScene.getStylesheets().add(css);
        officerScene.getStylesheets().add(css);
        loadTrafficLightsScene.getStylesheets().add(css);

        // Set up Stage
        primaryStage.setTitle("Traffic Management System");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void centralizePages(ArrayList<VBox> pages) {
        for (VBox page : pages) {
            page.setAlignment(Pos.CENTER);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void makeTrafficLightRow(String id, String location, String status, String duration) {
        trafficLightTable = new TableView<>();
        TableColumn<TrafficLight, String> idColumn = new TableColumn<>(id);
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());

        TableColumn<TrafficLight, String> locationColumn = new TableColumn<>(location);
        locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());

        TableColumn<TrafficLight, String> statusColumn = new TableColumn<>(status);
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        TableColumn<TrafficLight, String> durationColumn = new TableColumn<>(duration);
        durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());

        trafficLightTable.getColumns().addAll(idColumn, locationColumn, statusColumn, durationColumn);
        trafficLightTable.getItems().addAll(Admin.arr);
    }

    private void makeTrafficLightTable() {
        makeTrafficLightRow("ID", "Location", "Status", "Duration");
    }

    private Scene addTL(Scene back) {
        Label idLabel = new Label("Traffic Light ID:");
        TextField idField = new TextField();

        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();

        Label statusLabel = new Label("Status:");
        TextField statusField = new TextField();

        Label durationLabel = new Label("Duration:");
        TextField durationField = new TextField();

        // Button to add traffic light
        Button addButton = new Button("Add Traffic Light");

        // Action for Add Button
        addButton.setOnAction(e -> {
            // Get input values
            String id = idField.getText().trim();
            String location = locationField.getText().trim();
            String status = statusField.getText().trim();
            String duration = durationField.getText().trim();

            // Validate inputs
            if (id.isEmpty() || location.isEmpty() || status.isEmpty() || duration.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Missing Fields");
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
                return;
            }

            // Create and add TrafficLight
            TrafficLight trafficLight = new TrafficLight(id, location, status, duration);
            Admin.arr.add(trafficLight);

            // Clear input fields
            idField.clear();
            locationField.clear();
            statusField.clear();
            durationField.clear();
        });
        GridPane formLayout = new GridPane();
        formLayout.setHgap(10);
        formLayout.setVgap(10);
        formLayout.addRow(0, idLabel, idField);
        formLayout.addRow(1, locationLabel, locationField);
        formLayout.addRow(2, statusLabel, statusField);
        formLayout.addRow(3, durationLabel, durationField);
        formLayout.add(addButton, 1, 4);

        Button backButton = new Button("Back");

        // Main layout
        VBox mainLayout = new VBox(10, formLayout, backButton);
        mainLayout.setPrefWidth(600);
        mainLayout.setPadding(new Insets(20));

        backButton.setOnAction(e -> primaryStage.setScene(back));

        // Scene
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(css);
        return scene;
    }

    private Scene updateTL(Scene back) {
        // UI Components
        Label idLabel = new Label("Enter Traffic Light ID to Update:");
        TextField idField = new TextField();

        Label newIdLabel = new Label("New ID:");
        TextField newIdField = new TextField();

        Label statusLabel = new Label("New Status:");
        TextField statusField = new TextField();

        Label durationLabel = new Label("New Duration:");
        TextField durationField = new TextField();

        Label locationLabel = new Label("New Location:");
        TextField locationField = new TextField();

        Button updateButton = new Button("Update Traffic Light");
        Label resultLabel = new Label();

        Button backButton = new Button("Back");

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(idLabel, 0, 0);
        gridPane.add(idField, 1, 0);

        gridPane.add(newIdLabel, 0, 1);
        gridPane.add(newIdField, 1, 1);

        gridPane.add(statusLabel, 0, 2);
        gridPane.add(statusField, 1, 2);

        gridPane.add(durationLabel, 0, 3);
        gridPane.add(durationField, 1, 3);

        gridPane.add(locationLabel, 0, 4);
        gridPane.add(locationField, 1, 4);

        gridPane.add(updateButton, 1, 5);
        gridPane.add(resultLabel, 1, 6);

        // Event Handling
        updateButton.setOnAction(e -> {
            String id = idField.getText();
            String newId = newIdField.getText();
            String newStatus = statusField.getText();
            String newDuration = durationField.getText();
            String newLocation = locationField.getText();

            int index = -1;

            // Find the traffic light to update
            for (int i = 0; i < Admin.arr.size(); i++) {
                if (Admin.arr.get(i).getId().equals(id)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                resultLabel.setText("Traffic Light ID not found.");
            } else {
                // Update the traffic light
                TrafficLight updatedTrafficLight = new TrafficLight(newId, newLocation, newStatus, newDuration);
                Admin.arr.set(index, updatedTrafficLight);
                resultLabel.setText("Traffic Light updated successfully!");
            }

            // Clear the fields
            idField.clear();
            newIdField.clear();
            statusField.clear();
            durationField.clear();
            locationField.clear();
        });

        VBox mainLayout = new VBox(10, gridPane, backButton);
        mainLayout.setPrefWidth(600);
        mainLayout.setPadding(new Insets(20));

        backButton.setOnAction(e -> primaryStage.setScene(back));

        // Scene and Stage Setup
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(css);
        return scene;
    }

    private Scene deleteTL(Scene back) {
        Label idLabel = new Label("Enter Traffic Light ID to Delete:");
        TextField idField = new TextField();
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Back");
        Label resultLabel = new Label();

        GridPane deleteMenu = new GridPane();
        deleteMenu.setPadding(new Insets(20));
        deleteMenu.setHgap(10);
        deleteMenu.setVgap(10);

        deleteMenu.add(idLabel, 0, 0);
        deleteMenu.add(idField, 1, 0);
        deleteMenu.add(deleteButton, 1, 1);
        deleteMenu.add(resultLabel, 1, 2);
        deleteMenu.add(backButton, 1, 3);

        VBox mainLayout = new VBox(10, deleteMenu, backButton);
        mainLayout.setPrefWidth(600);
        mainLayout.setPadding(new Insets(20));

        // Event Handling for Delete Button
        deleteButton.setOnAction(e -> {
            String id = idField.getText();
            boolean found = false;

            for (int i = 0; i < Admin.arr.size(); i++) {
                if (Admin.arr.get(i).getId().equals(id)) {
                    Admin.arr.remove(i);
                    found = true;
                    break;
                }
            }

            if (found) {
                resultLabel.setText("Traffic Light deleted successfully!");
            } else {
                resultLabel.setText("Traffic Light ID not found.");
            }

            idField.clear();
        });

        // Event Handling for Back Button
        backButton.setOnAction(e -> primaryStage.setScene(back));

        // Set Main Scene
        Scene scene = new Scene(mainLayout);
        scene.getStylesheets().add(css);
        return scene;
    }

    private Scene renderReport(Scene back) {
        // Labels to display results
        Label highestDensityZoneLabel = new Label("The highest density zone ID: ");
        Label mostFrequentViolationLabel = new Label("The most frequent violation type: ");

        // Button to trigger computation
        Button computeButton = new Button("Compute Results");
        Button backButton = new Button("Back");

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(120));
        layout.getChildren().addAll(highestDensityZoneLabel, mostFrequentViolationLabel, computeButton, backButton);
        layout.setAlignment(Pos.CENTER);

        // Event Handling for Compute Button
        computeButton.setOnAction(e -> {
            try {
                // Getting the report results
                var result = Admin.generateReport();

                // Validate if result is not null
                if (result == null) {
                    highestDensityZoneLabel.setText("Error: No report data available.");
                    mostFrequentViolationLabel.setText("Error: No report data available.");
                } else {
                    // Extract the values from the result map entry
                    String mostFrequentZone = result.getKey();
                    String mostFrequentViolationType = result.getValue();

                    // Ensure neither value is null
                    if (mostFrequentZone == null || mostFrequentViolationType == null) {
                        highestDensityZoneLabel.setText("Error: Data not available.");
                        mostFrequentViolationLabel.setText("Error: Data not available.");
                    } else {
                        // Update the labels with the report data
                        highestDensityZoneLabel.setText("The highest density zone ID: " + mostFrequentZone);
                        mostFrequentViolationLabel.setText("The most frequent violation type: " + mostFrequentViolationType);
                    }
                }
            } catch (Exception ex) {
                // Catch any exceptions and display a generic error
                highestDensityZoneLabel.setText("Error generating report.");
                mostFrequentViolationLabel.setText("Error generating report.");
                ex.printStackTrace();  // For debugging purposes
            }
        });
        backButton.setOnAction(e -> primaryStage.setScene(back));

        // Scene and Stage Setup
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(css);
        return scene;
    }

    private Scene renderViolations(Scene back) {
        // Create RadioButtons
        RadioButton vehicleRadioButton = new RadioButton("View by Officer");
        RadioButton zoneRadioButton = new RadioButton("View by Zone");

        // Group the RadioButtons to ensure only one can be selected
        ToggleGroup toggleGroup = new ToggleGroup();
        vehicleRadioButton.setToggleGroup(toggleGroup);
        zoneRadioButton.setToggleGroup(toggleGroup);

        // Create TextField for ID input
        TextField idInput = new TextField();
        idInput.setPromptText("Enter ID (Officer or Zone)");

        // Create Button for submission
        Button submitButton = new Button("View Violations");
        Button loadBackButton = new Button("Back");

        // Create TextArea to display results
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setPromptText("Violations will appear here...");

        // Event Handling
        submitButton.setOnAction(e -> {
            resultArea.clear(); // Clear previous results

            if (toggleGroup.getSelectedToggle() == null) {
                resultArea.setText("Please select either 'Officer' or 'Zone'.");
                return;
            }

            String idText = idInput.getText();
            if (idText.isEmpty()) {
                resultArea.setText("Please enter a valid ID.");
                return;
            }

            try {
                int id = Integer.parseInt(idText); // Parse ID as integer
                boolean violationFound = false;
                if (vehicleRadioButton.isSelected()) {
                    resultArea.setText("Violations by TrafficOfficer ID (" + id + "):\n");
                    for (TrafficViolation v : TrafficOfficer.violationArr) {
                        if (v.getTrafficOfficerId() == id) {
                            resultArea.appendText(v.toString() + "\n");
                            violationFound = true;
                            break;
                        }
                    }
                } else if (zoneRadioButton.isSelected()) {
                    resultArea.setText("Violations by Zone ID (" + id + "):\n");
                    for (TrafficViolation v : TrafficOfficer.violationArr) {
                        if (v.getZoneId() == id) { // Assuming getZoneId exists
                            resultArea.appendText(v.toString() + "\n");
                            violationFound = true;
                            break;
                        }
                    }
                }

                if (!violationFound) {
                    resultArea.appendText("No violations found for this ID.");
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid ID format. Please enter a number.");
            }
        });

        // Layout
        VBox layout = new VBox(10, vehicleRadioButton, zoneRadioButton, idInput, submitButton, resultArea, loadBackButton);
        layout.setPadding(new Insets(15));
        layout.setPrefWidth(400);

        // Scene

        // Define the button's action
        loadBackButton.setOnAction(event -> {
            primaryStage.setScene(back);  // Update the state when the button is pressed
        });

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(css);
        return scene;
    }

    private Scene renderAllViolations(Scene back) {
        // Create TableView
        TableView<TrafficViolation> tableView = new TableView<>();

        // Create columns
        TableColumn<TrafficViolation, Integer> violationIdColumn = new TableColumn<>("Violation ID");
        violationIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getViolationId()).asObject());

        TableColumn<TrafficViolation, Integer> vehicleIdColumn = new TableColumn<>("Vehicle ID");
        vehicleIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVehicleId()).asObject());

        TableColumn<TrafficViolation, Integer> officerIdColumn = new TableColumn<>("Officer ID");
        officerIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTrafficOfficerId()).asObject());

        TableColumn<TrafficViolation, String> violationTypeColumn = new TableColumn<>("Violation Type");
        violationTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getViolationType()));

        TableColumn<TrafficViolation, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

        TableColumn<TrafficViolation, Double> fineAmountColumn = new TableColumn<>("Fine Amount");
        fineAmountColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getFineAmount()).asObject());

        TableColumn<TrafficViolation, Integer> zoneIdColumn = new TableColumn<>("Zone ID");
        zoneIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getZoneId()).asObject());

        // Add columns to the TableView
        tableView.getColumns().addAll(violationIdColumn, vehicleIdColumn, officerIdColumn, violationTypeColumn, dateColumn, fineAmountColumn, zoneIdColumn);

        // Set data in the table
        tableView.getItems().setAll(TrafficOfficer.violationArr);

        Button loadBackButton = new Button("Back");
        // Layout
        VBox layout = new VBox(10, tableView, loadBackButton);
        layout.setPrefWidth(800);

        // Define the button's action
        loadBackButton.setOnAction(event -> {
            primaryStage.setScene(back);  // Update the state when the button is pressed
        });

        Scene scene = new Scene(layout);
        scene.getStylesheets().add(css);
        return scene;
    }

    private Scene renderRecordViolations(Scene back) {
        // Create labels and input fields
        Label violationIdLabel = new Label("Violation ID:");
        TextField violationIdField = new TextField();

        Label vehicleIdLabel = new Label("Vehicle ID:");
        TextField vehicleIdField = new TextField();

        Label zoneIdLabel = new Label("Zone ID:");
        TextField zoneIdField = new TextField();

        Label officerIdLabel = new Label("Traffic Officer ID:");
        TextField officerIdField = new TextField();

        Label violationTypeLabel = new Label("Violation Type:");
        TextField violationTypeField = new TextField();

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();

        Label dateLabel = new Label("Date:");
        TextField dateField = new TextField();

        // Button to add the violation
        Button addButton = new Button("Add Violation");
        Button backButton = new Button("Back");

        // Label for feedback
        Label feedbackLabel = new Label();

        // Layout setup
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(violationIdLabel, 0, 0);
        gridPane.add(violationIdField, 1, 0);

        gridPane.add(vehicleIdLabel, 0, 1);
        gridPane.add(vehicleIdField, 1, 1);

        gridPane.add(zoneIdLabel, 0, 2);
        gridPane.add(zoneIdField, 1, 2);

        gridPane.add(officerIdLabel, 0, 3);
        gridPane.add(officerIdField, 1, 3);

        gridPane.add(violationTypeLabel, 0, 4);
        gridPane.add(violationTypeField, 1, 4);

        gridPane.add(amountLabel, 0, 5);
        gridPane.add(amountField, 1, 5);

        gridPane.add(dateLabel, 0, 6);
        gridPane.add(dateField, 1, 6);

        gridPane.add(addButton, 1, 7);
        gridPane.add(feedbackLabel, 1, 8);

        // Button action
        addButton.setOnAction(e -> {
            try {
                // Parse input values
                int violationId = Integer.parseInt(violationIdField.getText());
                int vehicleId = Integer.parseInt(vehicleIdField.getText());
                int zoneId = Integer.parseInt(zoneIdField.getText());
                int officerId = Integer.parseInt(officerIdField.getText());
                String violationType = violationTypeField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();

                // Create and add the violation
                TrafficViolation violation = new TrafficViolation(
                        violationId, vehicleId, zoneId, officerId, violationType, date, amount);
                TrafficOfficer.violationArr.add(violation);

                // Clear fields and provide feedback
                violationIdField.clear();
                vehicleIdField.clear();
                zoneIdField.clear();
                officerIdField.clear();
                violationTypeField.clear();
                amountField.clear();
                dateField.clear();
                feedbackLabel.setText("Violation added successfully!");
            } catch (Exception ex) {
                feedbackLabel.setText("Error: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10);
        layout.setPrefWidth(600);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(gridPane, backButton);

        backButton.setOnAction(event -> primaryStage.setScene(back));

        // Scene setup
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(css);
        return scene;
    }

    private Scene renderSendNotification(Scene back) {
        Label vehicleIdLabel = new Label("Vehicle ID:");
        TextField vehicleIdField = new TextField();
        Button sendButton = new Button("Send Notification");
        Label feedbackLabel = new Label();
        Button backButton = new Button("Back");

        sendButton.setOnAction(e -> {
            try {
                int vehicleId = Integer.parseInt(vehicleIdField.getText());
                notificationSystem.createAndSendNotification(vehicleId);
                feedbackLabel.setText("Notification sent successfully!");
            } catch (NumberFormatException ex) {
                System.err.println("Invalid Vehicle ID format!");
            }
        });

        backButton.setOnAction(e -> primaryStage.setScene(back));

        VBox vbox = new VBox(10, vehicleIdLabel, vehicleIdField, sendButton, feedbackLabel, backButton);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 600, 500);
        scene.getStylesheets().add(css);
        return scene;
    }
}

class LoginPage {
    private Scene scene;
    private String css = getClass().getResource("global-style.css").toExternalForm();

    public LoginPage(Stage primaryStage) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        Button ownerBackButton = new Button("Back to Main Menu");
        Label feedbackLabel = new Label();

        // Login action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean found = false;

            for (Owner owner : Gui.owners) {
                if (owner.name.equals(username) && owner.getPassword().equals(password)) {
                    found = true;
                    System.out.println("Login successful!");
                    LogoutPage logoutPage = new LogoutPage(primaryStage, owner);
                    primaryStage.setScene(logoutPage.getScene());
                    break;
                }
            }

            if (!found) {
                feedbackLabel.setText("Invalid username or password!");
            }
        });

        // Navigate to Sign Up page
        signUpButton.setOnAction(e -> {
            SignUpPage signUpPage = new SignUpPage(primaryStage, Gui.owners);
            primaryStage.setScene(signUpPage.getScene());
        });

        // Navigate to Main Menu
        ownerBackButton.setOnAction(e -> primaryStage.setScene(Gui.mainScene));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 0, 2);
        grid.add(signUpButton, 1, 2);
        grid.add(ownerBackButton, 0, 3);
        grid.add(feedbackLabel, 0, 4);

        scene = new Scene(grid, 600, 500);
        scene.getStylesheets().add(css);
    }

    public Scene getScene() {
        return scene;
    }
}

class SignUpPage {
    private Scene scene;
    private String css = getClass().getResource("global-style.css").toExternalForm();

    public SignUpPage(Stage primaryStage, ArrayList<Owner> owners) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label contactLabel = new Label("Contact Info:");
        TextField contactField = new TextField();

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();

        Button signUpButton = new Button("Sign Up");
        Button backButton = new Button("Back");

        // Sign Up action
        signUpButton.setOnAction(e -> {
            String name = usernameField.getText();
            int id = Integer.parseInt(idField.getText());
            String email = emailField.getText();
            String password = passwordField.getText();
            String contact = contactField.getText();
            String address = addressField.getText();

            Owner owner = new Owner(id, name, email, contact, address, password);
            Vehicle car = new Vehicle(owner.id * 10, "Car", "ABC-1234", owner.id);
            owner.arr.add(car);

            owners.add(owner);
            System.out.println("Sign Up successful!");
            primaryStage.setScene(new LoginPage(primaryStage).getScene());
        });

        // Navigate back to Login page
        backButton.setOnAction(e -> primaryStage.setScene(new LoginPage(primaryStage).getScene()));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(contactLabel, 0, 4);
        grid.add(contactField, 1, 4);
        grid.add(addressLabel, 0, 5);
        grid.add(addressField, 1, 5);
        grid.add(signUpButton, 0, 6);
        grid.add(backButton, 1, 6);

        scene = new Scene(grid, 600, 500);
        scene.getStylesheets().add(css);
    }

    public Scene getScene() {
        return scene;
    }
}

class LogoutPage {
    private Scene scene;
    private Owner owner;
    private Stage primaryStage;
    private String css = getClass().getResource("global-style.css").toExternalForm();

    public LogoutPage(Stage primaryStage, Owner owner) {
        this.owner = owner;
        this.primaryStage = primaryStage;
        Label welcomeLabel = new Label("Welcome, " + owner.name + "!");
        Button viewViolationsButton = new Button("View Notifications");
        Button payButton = new Button("Pay Your Bills");
        Button checkButton = new Button("Check Vehicles");
        Button logoutButton = new Button("Logout");

        // Action for "View Notifications"
        viewViolationsButton.setOnAction(e -> {
            System.out.println("Displaying notifications for " + owner.name + ".");
            renderViewNotifications();
        });

        // Action for "Pay"
        payButton.setOnAction(e -> {
            System.out.println("Processing payment for " + owner.name + ".");
            renderPayment();
        });

        // Action for "Check"
        checkButton.setOnAction(e -> {
            System.out.println("Checking details for " + owner.name + ".");
            renderCheck();
        });

        // Logout action
        logoutButton.setOnAction(e -> {
            System.out.println("Logged out successfully!");
            primaryStage.setScene(new LoginPage(primaryStage).getScene());
        });

        // Layout
        VBox vbox = new VBox(15, welcomeLabel, viewViolationsButton, payButton, checkButton, logoutButton);
        vbox.setAlignment(Pos.CENTER);

        scene = new Scene(vbox, 600, 500);
        scene.getStylesheets().add(css);
    }

    public Scene getScene() {
        return scene;
    }

    public void renderViewNotifications() {
        VBox notificationsBox = new VBox(10);
        notificationsBox.setAlignment(Pos.CENTER);

        // Display each notification
        for (int i = 0; i < owner.notifications.size(); i++) {
            Label notificationLabel = new Label(owner.notifications.get(i));
            notificationsBox.getChildren().add(notificationLabel);

            // Add a separator after each notification except the last one
            if (i < owner.notifications.size() - 1) {
                Separator horizontalLine = new Separator();
                horizontalLine.setPrefWidth(300);
                horizontalLine.setMinHeight(10); // Minimum height
                horizontalLine.setPrefHeight(10); // Preferred height
                horizontalLine.setMaxHeight(10); // Maximum height
                notificationsBox.getChildren().add(horizontalLine);
            }
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> primaryStage.setScene(scene));

        VBox notificationPage = new VBox(10, notificationsBox, backButton);
        notificationPage.setAlignment(Pos.CENTER);
        Scene notificationsScene = new Scene(notificationPage, 600, 500);
        notificationsScene.getStylesheets().add(css);
        primaryStage.setScene(notificationsScene);
    }

    public void renderPayment() {
        Label idLabel = new Label("Enter Violation ID:");
        TextField idField = new TextField();

        Label amountLabel = new Label("Enter Fine Amount:");
        TextField amountField = new TextField();

        Button payButton = new Button("Pay Violation");
        Label resultLabel = new Label();
        Button loadBackButton = new Button("Back");

        // GridPane layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(idLabel, 0, 0);
        grid.add(idField, 1, 0);
        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(payButton, 1, 2);
        grid.add(resultLabel, 1, 3);
        grid.add(loadBackButton, 1, 4);

        // Button action
        payButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                double amount = Double.parseDouble(amountField.getText());
                boolean violationFound = false;

                for (int i = 0; i < TrafficOfficer.violationArr.size(); i++) {
                    var violation = TrafficOfficer.violationArr.get(i);
                    if (violation.getViolationId() == id && violation.getFineAmount() == amount) {
                        TrafficOfficer.violationArr.remove(i);
                        violationFound = true;
                        resultLabel.setText("Violation paid successfully!");
                        break;
                    }
                }

                if (!violationFound) {
                    resultLabel.setText("No matching violation found.");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter valid numbers.");
            }
        });
        loadBackButton.setOnAction(e -> {
            primaryStage.setScene(scene);
        });

        // Setting up the stage
        Scene currScene = new Scene(grid, 600, 500);
        currScene.getStylesheets().add(css);
        primaryStage.setTitle("Pay Violation");
        primaryStage.setScene(currScene);
    }

    public void renderCheck() {
        TableView<Vehicle> vehicleTable = new TableView<>();
        ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList(owner.arr);

        TableColumn<Vehicle, Integer> idColumn = new TableColumn<>("Vehicle ID");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getVehicleId()));

        TableColumn<Vehicle, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getType()));

        TableColumn<Vehicle, String> licensePlateColumn = new TableColumn<>("License Plate");
        licensePlateColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getLicensePlate()));

        vehicleTable.getColumns().addAll(idColumn, typeColumn, licensePlateColumn);
        vehicleTable.setItems(vehicleList);

        ListView<String> violationListView = new ListView<>();
        Button loadViolationsButton = new Button("Show Violations");
        Button loadBackButton = new Button("Back");

        // Layout
        VBox rightPane = new VBox(10, new Label("Violations:"), violationListView);
        rightPane.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(vehicleTable);
        root.setRight(rightPane);
        root.setBottom(loadViolationsButton);
        root.setTop(loadBackButton);

        // Event: Show violations for selected vehicle
        vehicleTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int selectedVehicleId = newSelection.getVehicleId();
                ObservableList<String> violations = FXCollections.observableArrayList();

                for (var violation : TrafficOfficer.violationArr) {
                    if (violation.getVehicleId() == selectedVehicleId) {
                        violations.add(violation.toString());
                    }
                }

                if (violations.isEmpty()) {
                    violations.add("No violations for this vehicle.");
                }

                violationListView.setItems(violations);
            }
        });

        loadBackButton.setOnAction(e -> {primaryStage.setScene(scene);});

        // Show scene
        Scene currScene = new Scene(root, 800, 500);
        currScene.getStylesheets().add(css);
        primaryStage.setTitle("Owner Vehicles and Violations");
        primaryStage.setScene(currScene);
    }
}