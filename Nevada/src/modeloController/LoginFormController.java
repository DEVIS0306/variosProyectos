package modeloController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modeloDTO.InsertarRolesDTO;
import modeloDTO.RegistrarAdminDAOImpl;
import modeloDTO.RegistroAdminDTO;
import modeloDTO.RegistroAdminModel;
import modeloDTO.RegistroAdminModelImpl;
import modeloDTO.globalModelImpl;
import utilities.ControllerGeneralModel;
import modeloDTO.SessionManager;

/**
 *
 * Controlador de la ventana de inicio de sesión (LoginForm). Extiende la clase
 * globalModelImpl e implementa la interfaz Initializable. Es responsable de
 * controlar la lógica de la ventana de inicio de sesión y sus componentes.
 */
public class LoginFormController extends globalModelImpl implements Initializable {

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtUserSignIn, txtPasswordSignInMask;

    @FXML
    private PasswordField txtPasswordSignIn;

    @FXML
    private CheckBox checkViewPassSignIn;

    @FXML
    private CheckBox btnLimpiar;
    @FXML
    private RadioButton checkViewPassSignIn1;

    @FXML
    private Button btnVerContrasenia;
    @FXML
    private Button btnSignIn;
    private Boolean verContrasenia = true;
    private Boolean botonAceptar = false;
    private String obtenerConstrasenia = "";
    public Stage stage;
    private RegistroAdminModel registroAdminModel;

    /**
     * Limpia los campos de texto de la ventana de inicio de sesión.
     */
    public void limpiarCampos() {
        txtPasswordSignIn.setText("");
        txtPasswordSignInMask.setText("");
        txtUserSignIn.setText("");
    }

    /**
     * Maneja el evento de tecla presionada. Consume el carácter espaciado para
     * evitar su entrada.
     *
     * @param e el evento KeyEvent
     */
    @FXML
    public void eventKey(KeyEvent e) {

        String c = e.getCharacter();

        if (c.equalsIgnoreCase(" ")) {
            e.consume();
        }

    }

    /**
     * Maneja el evento de acción.
     *
     * @param e el evento ActionEvent
     */
    @FXML
    public void actionEvent(ActionEvent e) {

        Object evt = e.getSource();

    }

    /**
     * Inicializa el controlador de la clase.
     *
     * @param url la URL de la ubicación del FXML
     * @param rb el recurso de ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registroAdminModel = new RegistroAdminModelImpl(new RegistrarAdminDAOImpl());
        txtPasswordSignInMask.setVisible(false);

    }

    /**
     * Muestra u oculta la contraseña en el campo de texto.
     *
     * @return la contraseña visible
     */
    @FXML
    public String verContrasenia() {
        if (verContrasenia == true) {
            txtPasswordSignInMask.setVisible(true);
            txtPasswordSignInMask.setText(txtPasswordSignIn.getText());
            obtenerConstrasenia = txtPasswordSignInMask.getText();
            txtPasswordSignIn.setVisible(false);
            verContrasenia = false;
            URL linkNuevo = getClass().getResource("/iconos/VerContrasenia.png");
            Image imagenNuevo = new Image(linkNuevo.toString(), 18, 18, false, true);

            btnVerContrasenia.setGraphic((new ImageView(imagenNuevo)));
            return obtenerConstrasenia;
        } else if (verContrasenia == false) {
            txtPasswordSignIn.setVisible(true);
            txtPasswordSignIn.setText(txtPasswordSignInMask.getText());
            obtenerConstrasenia = txtPasswordSignIn.getText();
            txtPasswordSignInMask.setVisible(false);
            verContrasenia = true;
            URL linkNuevo = getClass().getResource("/iconos/OcultarContrasenia.png");
            Image imagenNuevo = new Image(linkNuevo.toString(), 18, 18, false, true);
            btnVerContrasenia.setGraphic((new ImageView(imagenNuevo)));
            return obtenerConstrasenia;
        }
        return obtenerConstrasenia;
    }

    /**
     * Obtiene la contraseña visible dependiendo del estado actual.
     *
     * @return la contraseña visible
     */
    public String verContraseniaVisible() {
        if (verContrasenia == true) {
            obtenerConstrasenia = txtPasswordSignIn.getText();
            txtPasswordSignInMask.setText(obtenerConstrasenia);
        } else if (verContrasenia == false) {
            obtenerConstrasenia = txtPasswordSignInMask.getText();
            txtPasswordSignIn.setText(obtenerConstrasenia);
        }
        if (txtPasswordSignIn.getText().equals("")) {
            obtenerConstrasenia = "";
        }

        return obtenerConstrasenia;
    }

    /**
     * Inicia el proceso de inicio de sesión. Verifica las credenciales
     * ingresadas y muestra la ventana principal en caso de éxito.
     *
     * @throws Exception si ocurre un error durante el inicio de sesión
     */
    @FXML
    public void iniciarSesion() throws Exception {
        verContraseniaVisible();
        if (txtUserSignIn.getText().length() == 0) {
            lblMensaje.setText("INGRESE USUARIO");
        } else if (obtenerConstrasenia.equals("")) {
            lblMensaje.setText("INGRESE CONTRASEÑA");

        } else {
            try {

                if (RegistrarAdminDAOImpl.verificarUsuario(this.txtUserSignIn.getText()).equals(this.txtUserSignIn.getText())) {
                    String existe = RegistrarAdminDAOImpl.verificaPassword(txtUserSignIn.getText());
                    existe = existe.replaceAll("\\s+", "");
                    if (existe.equals(obtenerConstrasenia)) {
                        Parent root = null;
                        try {
                            List<RegistroAdminDTO> usuarioSelecionado = registroAdminModel.listarUsuario(txtUserSignIn.getText());
                            SessionManager.setAttribute("usuarioSelecionado", usuarioSelecionado);
                            String url = "/vistaFXML/MainMenuScene.fxml";
                            root = FXMLLoader.load(getClass().getResource(url));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.getIcons().add(new javafx.scene.image.Image("/img/JW_Logo.svg.png"));
                            stage.setTitle("Congregación nevada");
                            stage.setScene(scene);
                            stage.show();

                            Stage myStage = (Stage) this.btnSignIn.getScene().getWindow();
                            myStage.close();

                        } catch (Exception ex) {

                        }
                    } else {
                        lblMensaje.setText("¡LA CONTRASEÑA NO ES CORRECTA!");
                    }
                } else {
                    lblMensaje.setText("¡EL USUARIO NO EXISTE!");
                    this.txtUserSignIn.requestFocus();

                }
            } catch (Exception ex) {
                Logger.getLogger(LoginFormController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
