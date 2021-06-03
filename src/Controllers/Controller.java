package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import dbManager.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Controller {
	static private DBHandler  dbHandler;
	static private Connection connection;
	static private PreparedStatement preparedStatement;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton createBtn;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
    	dbHandler = new DBHandler();
		connection= dbHandler.getDbConnection();
		
		createBtn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				try {
					createAccount();
					goBack();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

    }
	private void goBack() {
		createBtn.getScene().getWindow().hide();
    
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/Views/UserTable.fxml"));
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Parent root = loader.getRoot();
    		Stage stage= new Stage();
    		stage.setScene(new Scene(root));
    		stage.show();
    		

    }
    
    public  void createAccount() throws SQLException{
    	String query = " insert into users (Name, Email,Password)"+ "values (?,?,?)";  
    	preparedStatement= connection.prepareStatement(query);
    	String val=nameField.getText().trim();
    	String val1=emailField.getText().trim();
    	String val2=passwordField.getText().trim();
    	
		preparedStatement.setString(1, val);
		preparedStatement.setString(2, val1);
		preparedStatement.setString(3, val2);
		preparedStatement.executeUpdate();
		System.out.println("Data sucessfully added to database");
    }
}
