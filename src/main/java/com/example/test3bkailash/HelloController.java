package com.example.test3bkailash;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class HelloController implements Initializable  {
    public TableView tableView;
    public TableColumn customerName;
    public TableColumn mobileNumber;
    public TableColumn pizzaSize;
    public TableColumn numToppings;
    public TableColumn totalBill;
    public TextField xcustomerName;
    public TextField xmobileNumber;
    public TextField xpizzaSize;
    public TextField xnumToppings;
    @FXML
    private Label welcomeText;

    private ObservableList<pizzaorder> list = FXCollections.observableArrayList();


    @FXML
    public void Create(ActionEvent actionEvent) {
        String customerName = xcustomerName.getText();
        int mobileNumber = Integer.parseInt(xmobileNumber.getText());
        String pizzaSize = xpizzaSize.getText().toUpperCase();
        int numToppings = Integer.parseInt(xnumToppings.getText());
        int totalBill = calculateTotalBill(pizzaSize, numToppings);

        String jdbcUrl = "jdbc:mysql://localhost:3306/test3bkailash";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO `pizzaorder` (`customerName`, `mobileNumber`, `pizzaSize`, `numToppings`, `totalBill`) VALUES ('" + customerName + "', '" + mobileNumber + "', '" + pizzaSize + "', '" + numToppings + "', '" + totalBill + "')";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Read(ActionEvent actionEvent) {
        String customerName = xcustomerName.getText();
        String jdbcUrl = "jdbc:mysql://localhost:3306/test3bkailash";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM pizzaorder WHERE customerName='" + customerName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String pizzaSize = resultSet.getString("pizzaSize");
                int mobileNumber = resultSet.getInt("mobileNumber");
                int numToppings = resultSet.getInt("numToppings");
                int totalBill = resultSet.getInt("totalBill");

                xmobileNumber.setText(String.valueOf(mobileNumber));
                xpizzaSize.setText(pizzaSize);
                xnumToppings.setText(String.valueOf(numToppings));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Update(ActionEvent actionEvent) {
        String customerName = xcustomerName.getText();
        int mobileNumber = Integer.parseInt(xmobileNumber.getText());
        String pizzaSize = xpizzaSize.getText().toUpperCase();
        int numToppings = Integer.parseInt(xnumToppings.getText());
        int totalBill = calculateTotalBill(pizzaSize, numToppings);

        String jdbcUrl = "jdbc:mysql://localhost:3306/test3bkailash";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "UPDATE `pizzaorder` SET `mobileNumber`='" + mobileNumber + "', `pizzaSize`='" + pizzaSize + "', `numToppings`='" + numToppings + "', `totalBill`='" + totalBill + "' WHERE `customerName`='" + customerName + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Delete(ActionEvent actionEvent) {
        String customerName = xcustomerName.getText();

        String jdbcUrl = "jdbc:mysql://localhost:3306/test3bkailash";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "DELETE FROM `pizzaorder` WHERE `customerName`='" + customerName + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Load(ActionEvent actionEvent) {
        populateTable();
    }

    private void populateTable() {
        list.clear();

        String jdbcUrl = "jdbc:mysql://localhost:3306/test3bkailash";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM pizzaorder";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String customerName = resultSet.getString("customerName");
                int mobileNumber = resultSet.getInt("mobileNumber");
                String pizzaSize = resultSet.getString("pizzaSize");
                int numToppings = resultSet.getInt("numToppings");
                int totalBill = resultSet.getInt("totalBill");
                list.add(new pizzaorder(customerName, mobileNumber, pizzaSize, numToppings, totalBill));
            }
            tableView.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static int calculateTotalBill(String size, int toppings) {
        int basePrice = 0;
        switch (size) {
            case "XL": basePrice = 1500; break;
            case "L": basePrice = 1200; break;
            case "M": basePrice = 1000; break;
            case "S": basePrice = 800; break;
            default: throw new IllegalArgumentException("Invalid pizza size: " + size);
        }
        int toppingsPrice = toppings * 150;
        int totalPrice = basePrice + toppingsPrice;
        return (int) (totalPrice * 1.15);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        pizzaSize.setCellValueFactory(new PropertyValueFactory<>("pizzaSize"));
        numToppings.setCellValueFactory(new PropertyValueFactory<>("numToppings"));
        totalBill.setCellValueFactory(new PropertyValueFactory<>("totalBill"));
        tableView.setItems(list);

    }
}