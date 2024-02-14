package com.registro.registro;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.registro.registro.Main.loginStage;
import static com.registro.registro.Main.primaryStage;
import static com.registro.registro.Notificaciones.EnviarCorreo.destinatario;
import static com.registro.registro.RecuperarCorreo.hora;
import static com.registro.registro.RecuperarCorreo.minutos;

public class LoginController implements Initializable {

    @FXML
    private TextField correoField;

    @FXML
    private ComboBox<String> horaComboBox;

    @FXML
    private Spinner<Integer> minutosSpinner;

    @FXML
    private Text messageText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 24; i++) {
            horaComboBox.getItems().add(String.format("%02d", i));
        }
        horaComboBox.setValue("00");

        correoField.setText(destinatario);
        horaComboBox.setValue(String.valueOf(hora));
        minutosSpinner.getValueFactory().setValue(minutos);
    }

    @FXML
    private void guardarLogin() throws IOException {
        if (isValidEmail(correoField.getText())){
            destinatario = correoField.getText();
            hora = Integer.parseInt(horaComboBox.getValue());
            RecuperarCorreo.minutos = minutosSpinner.getValue();
            loginStage.close();
            Main.cargarInicio(primaryStage);

            RecuperarCorreo.guardar(correoField.getText(), Integer.parseInt(horaComboBox.getValue()),
                                    minutosSpinner.getValue());
        } else {
            messageText.setText("Correo no valido");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
        return email.matches(emailRegex);
    }
}
