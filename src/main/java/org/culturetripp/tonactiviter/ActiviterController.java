package org.culturetripp.tonactiviter;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.itextpdf.layout.Document;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.event.ActionEvent;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Optional;

import static org.culturetripp.tonactiviter.getData.date;
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
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private ImageView image5;

    @FXML
    private ImageView image6;

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

        String uri = getData.path;

        // Vérifier si le chemin d'accès est null ou vide
        if (uri == null || uri.isEmpty()) {
            // Afficher un message d'erreur et sortir de la méthode
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs et importer une image");
            alert.showAndWait();
            return;
        }

        // Remplacer les caractères "\\" dans le chemin d'accès
        uri = uri.replace("\\", "\\\\");

        String sql1 = "SELECT * FROM ajoutactiviter WHERE nom= '"
                +add_name.getText()+"'";

        connect = database.connectDb();

        Alert alert;
        try {
            // Exécuter la requête sql1 pour vérifier si le nom existe déjà
            statement = connect.createStatement();
            result = statement.executeQuery(sql1);

            if (result.next()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText(add_name.getText() + " a déjà été ajouté");
                alert.showAndWait();
            } else {
                if (add_name.getText().isEmpty() || add_nameact.getText().isEmpty() || add_fname.getText().isEmpty() || add_date.getValue() == null) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Veuillez remplir tous les champs");
                    alert.showAndWait();
                } else {
                    String sql = "INSERT INTO ajoutactiviter (nom, prenom, NomActiviter, image, date) VALUES (?, ?, ?, ?, ?)";
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, add_name.getText());
                    prepare.setString(2, add_fname.getText());
                    prepare.setString(3, add_nameact.getText());
                    prepare.setString(4, uri);
                    prepare.setString(5, String.valueOf(add_date.getValue()));
                    prepare.execute();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajouté avec succès");
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
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("error");
                alert.setHeaderText(null);
                alert.setContentText("Please select the activity first");
                alert.showAndWait();

            }else {
                statement.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
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

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("please select the activity first a delete");
                alert.showAndWait();


            }else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("are you sure do you want delete "+add_name.getText() + "?");


                Optional<ButtonType> result = alert.showAndWait();

                if(ButtonType.OK == result.get()){

                    statement.executeUpdate(sql);

                    showAjoutActiviterList();
                    clearAjoutActiviterList();

                    alert = new Alert(AlertType.INFORMATION);
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

    public void rechercherAjoutActiviter() {
        FilteredList<AjoutData> filter = new FilteredList<>(listAjoutActiviter, e -> true);

        addact_cherc.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateActData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String keychercher = newValue.toLowerCase();

                if (predicateActData.getPrenom().toLowerCase().contains(keychercher)) {
                    return true;
                } else if (predicateActData.getNom().toLowerCase().contains(keychercher)) {
                    return true;
                } else if (predicateActData.getNomActiviter().toLowerCase().contains(keychercher)) {
                    return true;
                } else if (predicateActData.getDate().toString().contains(keychercher)) {
                    return true;
                }

                return false;
            });
        });

        SortedList<AjoutData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(addact_tableview.comparatorProperty());
        addact_tableview.setItems(sortData);
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

    public void PDF() {
        ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
        String sql = "SELECT type, total, date FROM client WHERE client_id = ?";
        LocalDate date = LocalDate.now();

        // Avant l'exécution de la requête SQL
        try (Connection connect = database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sql)) {
            int customerID = 1;
            prepare.setInt(1, customerID);

            // Après avoir exécuté la requête SQL
            try (ResultSet resultSet = prepare.executeQuery()) {
                float totalAmount = 0;
                while (resultSet.next()) {
                    String type = resultSet.getString("type");
                    float total = resultSet.getFloat("total");
                    totalAmount += total;
                    // Ajoutez ici d'autres informations à récupérer si nécessaire
                }
                // Ajoutez le montant total récupéré à votre liste de transactions
                Transaction totalTransaction = new Transaction("Total", 1, totalAmount);
                transactionList.add(totalTransaction);

                // Après avoir récupéré les données de la base de données
                try (PdfWriter writer = new PdfWriter("receipt.pdf");
                     PdfDocument pdf = new PdfDocument(writer);
                     Document document = new Document(pdf)) {
                    addHeader(document);
                    addCustomerInfo(document, date, customerID);
                    addTransactionDetails(document, transactionList);
                    addTotalAmount(document, totalAmount); // Ajoutez le montant total ici
                    addThankYouMessage(document);
                    System.out.println("PDF file generated successfully.");
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private float totalAmount;
    {
// Code pour ajouter le montant total à votre liste de transactions
        this.totalAmount = totalAmount; // Affectation du montant total à la variable de classe
    }

    private void addTotalAmount(Document document, float totalAmount) {
        Paragraph totalAmountParagraph = new Paragraph("Total Amount Paid: TND " + String.format("%.2f", totalAmount))
                .setFontSize(12);
        document.add(totalAmountParagraph);
    }




    private void addHeader(Document document) {
        Paragraph header = new Paragraph("Receipt")
                .setFontSize(24)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(header);
    }

    private void addCustomerInfo(Document document, LocalDate date, int customerID) {
        Paragraph customerInfo = new Paragraph("Date: " + date.toString() + "\nCustomer ID: " + customerID)
                .setFontSize(12);
        document.add(customerInfo);
    }

    private void addTransactionDetails(Document document, ObservableList<Transaction> transactionList) {
        for (Transaction transaction : transactionList) {
            Paragraph transactionDetails = new Paragraph("Product: " + transaction.getProductName() +
                    ", Quantity: " + transaction.getQuantity() +
                    ", Price: " + String.format("%.2f", transaction.getPrice()))
                    .setFontSize(12);
            document.add(transactionDetails);
        }
    }

    private void addTotalAmount(Document document, ObservableList<Transaction> transactionList) {
        float totalAmount = calculateTotalAmount(transactionList);
        Paragraph totalAmountParagraph = new Paragraph("Total Amount: TND " + String.format("%.2f", totalAmount))
                .setFontSize(12);
        document.add(totalAmountParagraph);
    }

    private void addThankYouMessage(Document document) {
        Paragraph thankYouParagraph = new Paragraph("Thank you for your purchase!")
                .setFontSize(12);
        document.add(thankYouParagraph);
    }

    private float calculateTotalAmount(ObservableList<Transaction> transactionList) {
        float totalAmount = 0;
        for (Transaction transaction : transactionList) {
            totalAmount += transaction.getQuantity() * transaction.getPrice();
        }
        return totalAmount;
    }






    public void payer() {
        String sql = "INSERT INTO client (type, total, date) VALUES (?, ?, ?)";
        connect = database.connectDb();
        String type = "";

        if (price1 > 0 && price2 == 0) {
            type = "Adulte";
        } else if (price2 > 0 && price1 == 0) {
            type = "Enfant";
        } else if (price2 > 0 && price1 > 0) {
            type = "Adulte & Enfant";
        }

        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        try {
            // Appliquer la réduction de 20% si plus de 5 personnes achètent
            float totalWithDiscount = total;
            if (qty1 + qty2 > 5) {
                totalWithDiscount *= 0.8;
            }

            PreparedStatement prepare = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            prepare.setString(1, type);
            prepare.setFloat(2, totalWithDiscount);
            prepare.setDate(3, setDate);


            // Vérifier si l'image et le titre sont sélectionnés
            if (dispo_imageview.getImage() == null || dispo_titre.getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the activity and enter the title.");
                alert.showAndWait();
            } else {
                // Exécuter la requête SQL pour insérer les détails d'achat
                prepare.executeUpdate();

                // Afficher un message d'alerte pour indiquer le succès de l'achat
                Alert successAlert = new Alert(AlertType.INFORMATION);
                successAlert.setTitle("Information Message");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Successfully purchased!");
                successAlert.showAndWait();

                // Récupérer l'identifiant généré par la base de données
                ResultSet generatedKeys = prepare.getGeneratedKeys();
                int id = 0;
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                // Insérer les détails de l'achat dans la table client_info
                String sql2 = "INSERT INTO client_info (client_id, type, total,title) VALUES (?, ?, ?, ?)";
                PreparedStatement prepare2 = connect.prepareStatement(sql2);
                prepare2.setInt(1, id);
                prepare2.setString(2, type);
                prepare2.setFloat(3, totalWithDiscount);

                prepare2.setString(4, dispo_titre.getText());
                prepare2.executeUpdate();

                clearbuyticketinfo(); // Effacer les informations d'achat
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the activity first");
            alert.showAndWait();

        } else {
            // Obtenir le nom de l'activité sélectionnée
            String nomActivite = dispo_nameact.getText();

            // Définir le texte du titre sur le nom de l'activité
            dispo_titre.setText(nomActivite);

            // Charger l'image correspondant à l'activité sélectionnée
            String uri = "file:" + getData.path;
            Image image = new Image(uri);

            // Définir l'image de l'activité dans votre ImageView
            dispo_imageview.setImage(image);

            // Effacer les autres labels
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

