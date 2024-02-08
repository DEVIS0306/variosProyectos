package modeloController;

import alerta.SenaException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controlador para la pantalla principal de inicio de sesión.
 */
public class MainLoginController implements Initializable {

    @FXML
    private StackPane containerForm;

    private Label lblInicioTexto;

    private VBox signInForm, signUpForm;

    private boolean estadobtnIniciar = true;
    @FXML
    private GridPane stackPane;

    /**
     * Inicializa el controlador y carga los formularios de inicio de sesión y
     * registro.
     *
     * @param url la URL de la ubicación del FXML
     * @param rb el recurso de ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            signInForm = loadForm("/vistaFXML/LoginFormScene.fxml");
            containerForm.getChildren().addAll(signInForm);
            signInForm.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Carga y devuelve un formulario desde el archivo FXML especificado.
     *
     * @param url la URL del archivo FXML que se va a cargar
     * @return el formulario cargado como un objeto VBox
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    private VBox loadForm(String url) throws IOException {
        return (VBox) FXMLLoader.load(getClass().getResource(url));
    }


}
