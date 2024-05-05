package org.culturetripp.tonactiviter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Optional;

import static org.culturetripp.tonactiviter.getData.title;

public class ActiviterController implements Initializable {

    @FXML
    private Label Dispo_name;

    @FXML
    private Button add_btn;

    @FXML
    private DatePicker add_date;

    @FXML
    private TextField add_fname;

    @FXML
    private AnchorPane add_form;

    @FXML
    private ImageView add_imageview;

    @FXML
    private Button add_import;

    @FXML
    private TextField add_name;

    @FXML
    private TextField add_nameact;

    @FXML
    private Button addact_Ajouterbtn;

    @FXML
    private Button addact_Modifierbtn;

    @FXML
    private Button addact_Supprimerbtn;

    @FXML
    private Button addact_Viderbtn;

    @FXML
    private TextField addact_cherc;

    @FXML
    private TableColumn<AjoutData, Date> addact_col_date;

    @FXML
    private TableColumn<AjoutData, String> addact_col_fame;

    @FXML
    private TableColumn<AjoutData, String> addact_col_name;

    @FXML
    private TableColumn<AjoutData, String> addact_col_nameact;

    @FXML
    private TableView<AjoutData> addact_tableview;

    @FXML
    private Button client_btn;

    @FXML
    private TextField client_cherch;

    @FXML
    private TableColumn<?, ?> client_col_harriver;

    @FXML
    private TableColumn<?, ?> client_col_hdepart;

    @FXML
    private TableColumn<?, ?> client_col_nameact;

    @FXML
    private TableColumn<?, ?> client_col_ticket;

    @FXML
    private TableColumn<?, ?> client_col_date;

    @FXML
    private AnchorPane client_form;

    @FXML
    private Label client_harriver;

    @FXML
    private Label client_hdepart;

    @FXML
    private Label client_nameact;

    @FXML
    private Button client_suppbtn;

    @FXML
    private TableView<?> client_tableview;

    @FXML
    private Label client_ticket;

    @FXML
    private Button client_viderbtn;

    @FXML
    private Button close;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_totaldispo;

    @FXML
    private Label dashboard_totalreserve;

    @FXML
    private Label dashboard_totalrevenue;

    @FXML
    private Button dispo_btn;

    @FXML
    private TableColumn<AjoutData, Date> dispo_col_date;

    @FXML
    private TableColumn<AjoutData, String> dispo_col_name;

    @FXML
    private TableColumn<AjoutData, String> dispo_col_nameact;

    @FXML
    private Label dispo_date;

    @FXML
    private AnchorPane dispo_form;

    @FXML
    private ImageView dispo_imageview;

    @FXML
    private Label dispo_nameact;

    @FXML
    private Button dispo_payébtn;

    @FXML
    private Button dispo_reçubtn;

    @FXML
    private Button dispo_selectbtn;

    @FXML
    private Label dispo_spinner_totale;

    @FXML
    private Spinner<Integer> dispo_spinneradulte;

    @FXML
    private Label dispo_spinneradulte_prix;

    @FXML
    private Spinner<Integer> dispo_spinnerenfant;

    @FXML
    private Label dispo_spinnerenfant_prix;

    @FXML
    private TableView<AjoutData> dispo_tableview;

    @FXML
    private Label dispo_titre;

    @FXML
    private Button dispo_viderbtn;

    @FXML
    private AnchorPane top_form;

    @FXML
    private Button minimize;


    private Image image;

    private Connection connect ;
    private PreparedStatement prepare ;
    private Statement statement ;
    private ResultSet result;




    private double x= 0;
    private double y= 0;

    public ObservableList<AjoutData> ajoutActiviterList(){

        ObservableList<AjoutData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM ajoutactiviter ";
        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AjoutData ajtD;

            while(result.next()){
                ajtD = new AjoutData(result.getString("nom"), result.getString("prenom"), result.getString("NomActiviter"), result.getString("image"),result.getDate("date") );
                listData.add(ajtD);
            }

        }catch (Exception e){e.printStackTrace();}
        return listData;
    }

    public ObservableList<AjoutData> listAjoutActiviter;
    public void showAjoutActiviterList(){
        listAjoutActiviter = ajoutActiviterList();

        addact_col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        addact_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        addact_col_fame.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        addact_col_nameact.setCellValueFactory(new PropertyValueFactory<>("NomActiviter"));
        addact_tableview.setItems(listAjoutActiviter);

    }



    public void importImage(){
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.png","*jpg","*jpeg"));


        Stage stage = (Stage) add_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if(file != null){
            image = new Image(file.toURI().toString(),166,148,false,true);
            add_imageview.setImage(image);

            getData.path= file.getAbsolutePath();

        }
    }





    public void selectAjoutActiviter(){
        AjoutData ajtD = addact_tableview.getSelectionModel().getSelectedItem();
        int num = addact_tableview.getSelectionModel().getSelectedIndex();
        if((num -1) < -1){
            return;
        }

        getData.path= ajtD.getImage();

        add_name.setText(ajtD.getNom());
        add_fname.setText(ajtD.getPrenom());
        add_nameact.setText(ajtD.getNomActiviter());

        String getDate = String.valueOf(ajtD.getDate());


        String uri = "file:" + ajtD.getImage();

        image = new Image(uri,166,148,false,true);
        add_imageview.setImage(image);
    }



    public void insertAjoutActiviter() {
        String sql1 = "SELECT * FROM ajoutactiviter WHERE nom= '"
                +add_name.getText()+"'";

        connect = database.connectDb();

        Alert alert;
        try {
            // Exécuter la requête sql1 pour vérifier si le nom existe déjà
            statement = connect.createStatement();
            result = statement.executeQuery(sql1);


            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(add_name.getText() + " was already added");
                alert.showAndWait();
            } else {
                if (add_name.getText().isEmpty() || add_nameact.getText().isEmpty() || add_fname.getText().isEmpty() || image == null || add_date.getValue() == null) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields and import an image");
                    alert.showAndWait();
                } else {
                    String sql = "INSERT INTO ajoutactiviter (nom, prenom, NomActiviter, image, date) VALUES (?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, add_name.getText());
                    prepare.setString(2, add_fname.getText());
                    prepare.setString(3, add_nameact.getText());
                    prepare.setString(4, image.getUrl()); // Utilisation de l'URL de l'ima
                    prepare.setString(5, String.valueOf(add_date.getValue()));
                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added");
                    alert.showAndWait();

                    clearAjoutActiviterList();
                    showAjoutActiviterList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void updateAjoutActiviter(){

        String uri = getData.path;
        uri = uri.replace("\\","\\\\");

        String sql = "UPDATE ajoutactiviter SET nom = '"+ add_name.getText()
                + "', prenom = '" + add_fname.getText()
                +"', NomActiviter = '" +add_nameact.getText()
                +"', image = '" + uri
                +"', date = '" + add_date.getValue()+"'";

        connect = database.connectDb();

        try {
            statement = connect.createStatement();

            Alert alert;

            if(add_name.getText().isEmpty() ||
                    add_nameact.getText().isEmpty() ||
                    add_fname.getText().isEmpty() ||
                    add_nameact.getText().isEmpty() ||
                    add_imageview.getImage() == null ||
                    add_date.getValue() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(null);
                alert.setContentText("Please select the activity first");
                alert.showAndWait();

            }else {
                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("information message");
                alert.setHeaderText(null);
                alert.setContentText("successfully update" + add_name.getText());
                alert.showAndWait();

                showAjoutActiviterList();

                clearAjoutActiviterList();
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void deleteAjoutActiviter(){
        String sql = "DELETE FROM ajoutactiviter WHERE nom = '" + add_name.getText()+"'";
        connect = database.connectDb();

        try {

            statement = connect.createStatement();
            Alert alert;
            if(add_name.getText().isEmpty() ||
                    add_nameact.getText().isEmpty() ||
                    add_fname.getText().isEmpty() ||
                    add_nameact.getText().isEmpty() ||
                    add_imageview.getImage() == null ||
                    add_date.getValue() == null){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("please select the activity first a delete");
                alert.showAndWait();


            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("are you sure do you want delete "+add_name.getText() + "?");


                Optional<ButtonType> result = alert.showAndWait();

                if(ButtonType.OK == result.get()){

                    statement.executeUpdate(sql);

                    showAjoutActiviterList();
                    clearAjoutActiviterList();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully delete" );
                    alert.showAndWait();

                }else {
                    return;
                }

            }




        }catch (Exception e){e.printStackTrace();}

    }

    public  void rechercherAjoutActiviter(){

    }



    public void clearAjoutActiviterList (){
        add_name.setText("");
        add_fname.setText("");
        add_nameact.setText("");
        add_imageview.setImage(null);
        add_date.setValue(null);

    }

    private SpinnerValueFactory <Integer> spinner1;
    private SpinnerValueFactory <Integer> spinner2;

    private float price1= 0;
    private float price2= 0;
    private float total = 0;
    private int qty1 = 0;
    private int qty2 = 0;




    public void payer(){

        String sql = "INSERT INTO client (type,total,date) VALUES(???)";

        connect = database.connectDb();
        String type ="";

        if(price1 > 0 && price2 ==0){

            type = "Adulte";
        }else if(price2 > 0 && price1 ==0){
            type ="Enfant";

        }else if(price2 >0 && price1 >0){
            type = "Adulte & Enfant";
        }


        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1,type);
            prepare.setString(2,String.valueOf(total));
            prepare.setString(3,String.valueOf(setDate));

            Alert alert;

            if(dispo_imageview.getImage() == null || dispo_titre.getText().isEmpty())

            {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eror Message");
                alert.setHeaderText(null);
                alert.setContentText("please select the activity first");
                alert.showAndWait();

            }  else if(price1 == 0 && price2 ==0){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eror Message");
                alert.setHeaderText("null");
                alert.setContentText("please indicate the quantity of ticket you want to buy");
                alert.showAndWait();


            }else{
                prepare.executeUpdate();

                alert = new Alert ( Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("successfuly buy");
                alert.showAndWait();

                String sql1= "SELECT * FROM client ";

                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();

                int num = 0;

                while (result.next()){

                    num = result.getInt("id");

                }
                String sql2 = "INSER INTO client_info (client_id,type,totale,title) VALUES(?,?,?,?)";

                prepare = connect.prepareStatement(sql2);
                prepare.setString(1,String.valueOf(num));
                prepare.setString(2,type);
                prepare.setString(3,dispo_titre.getText());
                prepare.setFloat(4,total);

                prepare.executeUpdate();
                clearbuyticketinfo();
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void clearbuyticketinfo(){
        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);

        dispo_spinneradulte.setValueFactory(spinner1);
        dispo_spinnerenfant.setValueFactory(spinner2);

        dispo_spinneradulte_prix.setText("0.0Dt");
        dispo_spinnerenfant_prix.setText("0.0Dt");
        dispo_spinner_totale.setText("0.0Dt");

        dispo_imageview.setImage(null);
        dispo_titre.setText("");


    }


    public void showSpinnerValue(){

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);

        dispo_spinneradulte.setValueFactory(spinner1);
        dispo_spinnerenfant.setValueFactory(spinner2);
    }

    public void getSpinnerValue(){

        qty1 = dispo_spinneradulte.getValue();
        qty2 = dispo_spinnerenfant.getValue();

        price1 = (qty1 * 40);
        price2 = (qty2 * 30);

        total = (price1 + price2);

        dispo_spinneradulte_prix.setText(String.valueOf(price1)+"Dt");
        dispo_spinnerenfant_prix.setText(String.valueOf(price2)+"Dt");

        dispo_spinner_totale.setText(String.valueOf(total)+"Dt");

    }


    public ObservableList<AjoutData>DispoActiviterList(){
        ObservableList<AjoutData> listDpAct = FXCollections.observableArrayList();

        String sql = "SELECT * FROM ajoutactiviter ORDER BY nom";

        connect = database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AjoutData ajtD;

            while(result.next()){

                ajtD= new AjoutData(result.getString("nom")
                , result.getString("prenom")
                        ,result.getString("NomActiviter")
                        , result.getString("image")
                        ,result.getDate("date") );

                listDpAct.add(ajtD);


            }

        }catch (Exception e){e.printStackTrace();}
        return listDpAct;
    }

    private ObservableList<AjoutData> DispoActiviterList;

    public void showDispoActiviterList(){

        DispoActiviterList = DispoActiviterList();

        dispo_col_name.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        dispo_col_nameact.setCellValueFactory(new PropertyValueFactory<>("nomActiviter"));
        dispo_col_date.setCellValueFactory(new PropertyValueFactory<>("Date"));

        dispo_tableview.setItems(DispoActiviterList);


    }
    public void selectDispoActiviter() {

        AjoutData ajtD = dispo_tableview.getSelectionModel().getSelectedItem();
        int num = dispo_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        Dispo_name.setText(ajtD.getNom());
        dispo_nameact.setText(ajtD.getNomActiviter());
        dispo_date.setText(String.valueOf(ajtD.getDate()));

        getData.path=ajtD.getImage();
        title = ajtD.getTitle();

    }

    public void selectDispoAct(){

        Alert alert;
        if(Dispo_name.getText().isEmpty()
        || dispo_nameact.getText().isEmpty()
        || dispo_date.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("please select the activity first ");
            alert.showAndWait();


        }else {

            String uri = "file:" + getData.path;
            image = new Image(uri, 177, 150, false, true);


            dispo_titre.setText(title);

            Dispo_name.setText("");
            dispo_nameact.setText("");
            dispo_date.setText("");


        }

    }



    public void close() {
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage) top_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void switchForm(ActionEvent event) {
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            add_form.setVisible(false);
            dispo_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color: #ae2d3c");
            add_btn.setStyle("-fx-background-color: transparent");
            dispo_btn.setStyle("-fx-background-color: transparent");

        }else if(event.getSource() == add_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(true);
            dispo_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color: transparent");
            add_btn.setStyle("-fx-background-color: #ae2d3c");
            dispo_btn.setStyle("-fx-background-color: transparent");




        }else if(event.getSource() == dispo_btn){
            dashboard_form.setVisible(false);
            add_form.setVisible(false);
            dispo_form.setVisible(true);

            dashboard_btn.setStyle("-fx-background-color: transparent");
            add_btn.setStyle("-fx-background-color: transparent");
            dispo_btn.setStyle("-fx-background-color: #ae2d3c");

            showDispoActiviterList();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAjoutActiviterList();
        showDispoActiviterList();
        showSpinnerValue();

    }

}

