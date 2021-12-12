/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TableView<tabla> tvtabla;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnombre;
    @FXML
    private TextField tfapellido;
    @FXML
    private TextField tfedad;
    @FXML
    private TableColumn<tabla, Integer> clmid;
    @FXML
    private TableColumn<tabla,String> clmnombre;
    @FXML
    private TableColumn<tabla,String> clmapellido;
    @FXML
    private TableColumn<tabla,Integer> clmedad;
    @FXML
    private Button tnagregar;
    @FXML
    private Button btnactualizar;
    @FXML
    private Button btneliminar;
    private Object FXCollection;
    private Object TablaList;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public Connection getConnection(){
       Connection conn;
       try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crud");
           
            return conn;
       } catch (Exception e) {
           System.out.println("error: "+e.getMessage());
           return null;
       }
        
   }
    public ObservableList<tabla> gettablaList(){
        ObservableList<tabla> TablaList= FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query="SELECT*FROM Tabla";
        Statement st; 
        ResultSet rs;
        try {
            st=conn.createStatement();
            rs=st.executeQuery(query);
            tabla Tabla;
            while (rs.next()) {
                Tabla = new tabla(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellido"),rs.getInt("edad"));
                TablaList.add(Tabla);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TablaList;
        
     
    }
    public void Showtabla(){
        ObservableList<tabla> list=gettablaList();
        clmid.setCellValueFactory(new PropertyValueFactory<tabla,Integer>("id"));
        clmnombre.setCellValueFactory(new PropertyValueFactory<tabla,String>("nombre"));
        clmapellido.setCellValueFactory(new PropertyValueFactory<tabla,String>("apellido"));
        clmedad.setCellValueFactory(new PropertyValueFactory<tabla,Integer>("edad"));
    }

    
}
