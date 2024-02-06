package com.registro.registro;

import com.registro.registro.BaseDeDatos.Trabajo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.registro.registro.Main.primaryStage;
import static com.registro.registro.MostrarController.dataGlobal;

public class NotificacionesController implements Initializable {

    @FXML
    private Label lbl1;
    @FXML
    private Label lbl1Fecha;
    @FXML
    private Label lbl1Count;
    @FXML
    private Label lbl1Trabajo;
    @FXML
    private Button btn1VerMas;

    @FXML
    private Label lbl2;
    @FXML
    private Label lbl2Fecha;
    @FXML
    private Label lbl2Count;
    @FXML
    private Label lbl2Trabajo;
    @FXML
    private Button btn2VerMas;

    @FXML
    private Label lbl3;
    @FXML
    private Label lbl3Fecha;
    @FXML
    private Label lbl3Count;
    @FXML
    private Label lbl3Trabajo;
    @FXML
    private Button btn3VerMas;

    @FXML
    private Label lbl4;
    @FXML
    private Label lbl4Fecha;
    @FXML
    private Label lbl4Count;
    @FXML
    private Label lbl4Trabajo;
    @FXML
    private Button btn4VerMas;

    @FXML
    private Label lbl5;
    @FXML
    private Label lbl5Fecha;
    @FXML
    private Label lbl5Count;
    @FXML
    private Label lbl5Trabajo;
    @FXML
    private Button btn5VerMas;

    @FXML
    private Label lbl6;
    @FXML
    private Label lbl6Fecha;
    @FXML
    private Label lbl6Count;
    @FXML
    private Label lbl6Trabajo;
    @FXML
    private Button btn6VerMas;

    @FXML
    private Label lbl7;
    @FXML
    private Label lbl7Fecha;
    @FXML
    private Label lbl7Count;
    @FXML
    private Label lbl7Trabajo;
    @FXML
    private Button btn7VerMas;

    //Variables para guardar los trabajos de cada dia

    ObservableList<Trabajo> data1 = FXCollections.observableArrayList();
    ObservableList<Trabajo> data2 = FXCollections.observableArrayList();
    ObservableList<Trabajo> data3 = FXCollections.observableArrayList();
    ObservableList<Trabajo> data4 = FXCollections.observableArrayList();
    ObservableList<Trabajo> data5 = FXCollections.observableArrayList();
    ObservableList<Trabajo> data6 = FXCollections.observableArrayList();
    ObservableList<Trabajo> data7 = FXCollections.observableArrayList();

    //Variable estatica que servira para usar el ObservableList en la ventana ver-mas
    public static ObservableList<Trabajo> dataVerMas = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl1Count.setVisible(false);
        lbl2Count.setVisible(false);
        lbl3Count.setVisible(false);
        lbl4Count.setVisible(false);
        lbl5Count.setVisible(false);
        lbl6Count.setVisible(false);
        lbl7Count.setVisible(false);

        btn1VerMas.setVisible(false);
        btn2VerMas.setVisible(false);
        btn3VerMas.setVisible(false);
        btn4VerMas.setVisible(false);
        btn5VerMas.setVisible(false);
        btn6VerMas.setVisible(false);
        btn7VerMas.setVisible(false);


        // Utilizar un DateTimeFormatter para obtener el nombre del día
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault());

        for (int i = 0;i <= 6;i++){

            int count = 0;
            ArrayList<String> trabajos = new ArrayList<>();
            trabajos.add("Sin trabajos");

            ObservableList<Trabajo> data = FXCollections.observableArrayList();

            for (Trabajo trabajo: dataGlobal){
                if (trabajo.getFecha_mantenimientoSF().equals(LocalDate.now().plusDays(i))){
                    if (count == 0){
                        trabajos.clear();
                        data.clear();
                    }
                    count++;
                    trabajos.add(trabajo.getNombre_trabajo());

                    data.add(trabajo);

                }
            }

            switch (i) {
                case 0 -> {
                    lbl1.setText(LocalDate.now().plusDays(i).format(formatter));
                    lbl1Fecha.setText(LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    lbl1Count.setText(String.valueOf(count));
                    lbl1Trabajo.setText(trabajos.get(0));
                    if (count > 0) {
                        btn1VerMas.setVisible(true);
                        lbl1Count.setVisible(true);
                        data1 = data;
                    }
                }
                case 1 -> {
                    lbl2.setText(LocalDate.now().plusDays(i).format(formatter));
                    lbl2Fecha.setText(LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    lbl2Count.setText(String.valueOf(count));
                    lbl2Trabajo.setText(trabajos.get(0));
                    if (count > 0) {
                        btn2VerMas.setVisible(true);
                        lbl2Count.setVisible(true);
                        data2 = data;
                    }
                }
                case 2 -> {
                    lbl3.setText(LocalDate.now().plusDays(i).format(formatter));
                    lbl3Fecha.setText(LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    lbl3Count.setText(String.valueOf(count));
                    lbl3Trabajo.setText(trabajos.get(0));
                    if (count > 0) {
                        btn3VerMas.setVisible(true);
                        lbl3Count.setVisible(true);
                        data3 = data;
                    }
                }
                case 3 -> {
                    lbl4.setText(LocalDate.now().plusDays(i).format(formatter));
                    lbl4Fecha.setText(LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    lbl4Count.setText(String.valueOf(count));
                    lbl4Trabajo.setText(trabajos.get(0));
                    if (count > 0) {
                        btn4VerMas.setVisible(true);
                        lbl4Count.setVisible(true);
                        data4 = data;
                    }
                }
                case 4 -> {
                    lbl5.setText(LocalDate.now().plusDays(i).format(formatter));
                    lbl5Fecha.setText(LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    lbl5Count.setText(String.valueOf(count));
                    lbl5Trabajo.setText(trabajos.get(0));
                    if (count > 0) {
                        btn5VerMas.setVisible(true);
                        lbl5Count.setVisible(true);
                        data5 = data;
                    }
                }
                case 5 -> {
                    lbl6.setText(LocalDate.now().plusDays(i).format(formatter));
                    lbl6Fecha.setText(LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    lbl6Count.setText(String.valueOf(count));
                    lbl6Trabajo.setText(trabajos.get(0));
                    if (count > 0) {
                        btn6VerMas.setVisible(true);
                        lbl6Count.setVisible(true);
                        data6 = data;
                    }
                }
                case 6 -> {
                    lbl7.setText(LocalDate.now().plusDays(i).format(formatter));
                    lbl7Fecha.setText(LocalDate.now().plusDays(i).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    lbl7Count.setText(String.valueOf(count));
                    lbl7Trabajo.setText(trabajos.get(0));
                    if (count > 0) {
                        btn7VerMas.setVisible(true);
                        lbl7Count.setVisible(true);
                        data7 = data;
                    }
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    @FXML
    private void boton1() throws IOException {
        verMas(data1);
    }
    @FXML
    private void boton2() throws IOException {
        verMas(data2);
    }
    @FXML
    private void boton3() throws IOException {
        verMas(data3);
    }
    @FXML
    private void boton4() throws IOException {
        verMas(data4);
    }
    @FXML
    private void boton5() throws IOException {
        verMas(data5);
    }
    @FXML
    private void boton6() throws IOException {
        verMas(data6);
    }
    @FXML
    private void boton7() throws IOException {
        verMas(data7);
    }


    @FXML
    private void verMas(ObservableList<Trabajo> d) throws IOException {

        //Asignar los datos del dia elegido
        dataVerMas.clear();
        dataVerMas = d;


        // Cargar el archivo FXML de la ventana externa
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ventana-ver-mas.fxml"));
        Parent root = loader.load();

        // Crear una nueva escena y asignarla a la ventana externa
        Scene scene = new Scene(root);
        Stage ventanaExterna = new Stage();
        ventanaExterna.initModality(Modality.APPLICATION_MODAL);
        ventanaExterna.initOwner(primaryStage);

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/icono.png")));
        ventanaExterna.getIcons().add(icon);
        ventanaExterna.setTitle("Ver mas");
        ventanaExterna.setScene(scene);

        // Mostrar la ventana externa
        ventanaExterna.showAndWait();
    }
}
