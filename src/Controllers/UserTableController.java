package Controllers;

import com.jfoenix.controls.JFXButton;


import Models.User;
import dbManager.DBHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class UserTableController {
	static private DBHandler  dbHandler;
	static private Connection connection;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton createBtn;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> idCol;

    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, String> passwordCol;
    
    ObservableList<User> userList;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException, IllegalStateException {
    	dbHandler = new DBHandler();
 		connection= dbHandler.getDbConnection();
 		idCol.setCellValueFactory(new PropertyValueFactory<User,String>("UserId"));
 		nameCol.setCellValueFactory(new PropertyValueFactory<User,String>("Name"));
 		emailCol.setCellValueFactory(new PropertyValueFactory<User,String>("Email")); 		
 		passwordCol.setCellValueFactory(new PropertyValueFactory<User,String>("Password"));
 		userList = getUserData();
 		userTable.setItems(userList);
 		createBtn.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		createUser();
        	}
        });
    }
	private void createUser() {
		createBtn.getScene().getWindow().hide();
    
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/Views/Sample.fxml"));
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
    
    public ObservableList<User> getUserData(){
    	ObservableList <User> list= FXCollections.observableArrayList();
    	try {
    		PreparedStatement ps=connection.prepareCall("select * from users");
    		ResultSet rs= ps.executeQuery();
    		while(rs.next()) {
    			list.add(new User(rs.getString("UserId"),rs.getString("Name"),rs.getString("Email"),rs.getString("Password") ));
    		}
    				
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    
}
