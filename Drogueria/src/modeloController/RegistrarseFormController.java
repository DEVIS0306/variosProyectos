/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloController;

import alerta.AlertBoxes;
import alerta.SenaException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeloDTO.InsertarRolesDTO;
import modeloDTO.RegistrarAdminDAOImpl;
import modeloDTO.RegistroAdminDTO;
import modeloDTO.RegistroAdminModel;
import modeloDTO.RegistroAdminModelImpl;
import modeloDTO.globalModel;
import modeloDTO.globalModelImpl;
import static modeloDTO.globalModelImpl.validarContrasenia;
import static modeloDTO.globalModelImpl.validateEmail;
import otros.ComboBoxItem;

/**
 * Controlador para la interfaz de registro de usuario. Extiende la clase
 * globalModelImpl e implementa la interfaz Initializable.
 */
public class RegistrarseFormController extends globalModelImpl implements Initializable {

    private VBox panelFormSignUp;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNumeroDocumento;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtNombreUsuario;

    @FXML
    private TextField txtDireccion;

    @FXML
    private PasswordField txtContraseniaOculta;

    @FXML
    private TextField txtContraseniaVisible;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVercontrasenia;

    @FXML
    private ComboBox<ComboBoxItem> comboDocumento;

    private globalModel globalModel;
    private RegistroAdminModel registroAdminModel;
    private static final int MAXIMO_NUMERO_TELEFONO = 10;

    private Boolean verContrasenia = true;
    private ArrayList<InsertarRolesDTO> admin;

    /**
     * Inicializa el controlador después de que se haya cargado la interfaz.
     *
     * @param url la ubicación utilizada para resolver rutas relativas de
     * recursos
     * @param rb el paquete de recursos utilizado para localizar recursos
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limiteTextField(txtTelefono, MAXIMO_NUMERO_TELEFONO);
        validarSoloNumeros(txtTelefono, MAXIMO_NUMERO_TELEFONO);
        validarSoloLetras(txtNombre);
        validarSoloLetras(txtApellido);
        validarSoloLetrasNumeros(txtNumeroDocumento);
        txtContraseniaVisible.setVisible(false);
        globalModel = new globalModelImpl();
        registroAdminModel = new RegistroAdminModelImpl(new RegistrarAdminDAOImpl());

        try {
            List<ComboBoxItem> listaTipoDocumento = globalModel.listaTipoDocumento();
            setComboBoxItems(comboDocumento, listaTipoDocumento);
            comboDocumento.getSelectionModel().selectFirst();
        } catch (Exception ex) {
            //  Logger.getLogger(RegistroAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene los datos del formulario de registro y los asigna al objeto
     * RegistroAdminDTO.
     *
     * @param registroAdminDTO el objeto RegistroAdminDTO para almacenar los
     * datos del formulario
     */
    private void obtenerDatosFormulario(RegistroAdminDTO registroAdminDTO) {
        registroAdminDTO.setIdRol(1);
        registroAdminDTO.setNombre(txtNombre.getText());
        registroAdminDTO.setApellido(txtApellido.getText());
        registroAdminDTO.setTipoDocumento(comboDocumento.getSelectionModel().getSelectedItem().toString());
        registroAdminDTO.setNumeroDocumento(txtNumeroDocumento.getText());
        registroAdminDTO.setCorreo(txtCorreo.getText());
        registroAdminDTO.setTelefono(txtTelefono.getText());
        registroAdminDTO.setDireccion(txtDireccion.getText());
        registroAdminDTO.setEstado(1);
        if (verContrasenia == true) {
            registroAdminDTO.setContrasenia(txtContraseniaOculta.getText());

        } else if (verContrasenia == false) {
            registroAdminDTO.setContrasenia(txtContraseniaVisible.getText());
        }

    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón de registro.
     */
    @FXML
    void aceptarVentana() {
        Parent root = null;
        try {
            validarCamposFormulario();
            RegistroAdminDTO registroAdminDTO = new RegistroAdminDTO();
            obtenerDatosFormulario(registroAdminDTO);
            registroAdminModel.insertarUsuario(registroAdminDTO);
            AlertBoxes.infoAlert("USUARIO", "EL USUARIO SE REGISTRO EXITOSA MENTE", "");
            String url = "/vistaFXML/MainLoginScene.fxml";
            root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new javafx.scene.image.Image("/img/sena.jpg"));
            stage.setTitle("Sistema de Ventas");
            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
            myStage.close();

        } catch (Exception ex) {

        }
    }

    /**
     * Muestra u oculta la contraseña visible según el estado actual.
     */
    @FXML
    public void verContrasenia() {
        if (verContrasenia == true) {
            txtContraseniaVisible.setText(txtContraseniaOculta.getText());
            txtContraseniaVisible.setVisible(true);
            txtContraseniaOculta.setVisible(false);
            verContrasenia = false;
            URL linkNuevo = getClass().getResource("/iconos/VerContrasenia.png");
            Image imagenNuevo = new Image(linkNuevo.toString(), 18, 18, false, true);

            btnVercontrasenia.setGraphic((new ImageView(imagenNuevo)));

        } else if (verContrasenia == false) {
            txtContraseniaOculta.setText(txtContraseniaVisible.getText());
            txtContraseniaVisible.setVisible(false);
            txtContraseniaOculta.setVisible(true);
            verContrasenia = true;
            URL linkNuevo = getClass().getResource("/iconos/OcultarContrasenia.png");
            Image imagenNuevo = new Image(linkNuevo.toString(), 18, 18, false, true);
            btnVercontrasenia.setGraphic((new ImageView(imagenNuevo)));
        }
    }

    /**
     * Valida los campos del formulario de registro.
     *
     * @throws SenaException si los campos del formulario no son válidos
     * @throws Exception si ocurre un error durante la validación del formulario
     */
    public void validarCamposFormulario() throws SenaException, Exception {
        String mensaje = "";
        String peticion = "Favor de ingresar un valor";
        Boolean validarCorreo = validateEmail(txtCorreo.getText());
        Boolean validarContrasenia = validarContrasenia(txtContraseniaVisible.getText());
        Boolean validarContraseniaO = validarContrasenia(txtContraseniaOculta.getText());
        if (txtNombre.getText().equals("")) {
            txtNombre.requestFocus();
            mensaje = "El campo nombre es obligatorio";
        } else if (txtApellido.getText().equals("")) {
            mensaje = "El campo Apellido es obligatorio";
        } else if (txtNumeroDocumento.getText().equals("")) {
            mensaje = "El campo numero es obligatorio";
        } else if (txtCorreo.getText().equals("")) {
            mensaje = "El campo correo es obligatorio";
        } else if (RegistrarAdminDAOImpl.verificarUsuario(this.txtCorreo.getText()).equals(this.txtCorreo.getText())) {
            mensaje = "El usuario ya existe";
            peticion = "Ingrese un correo diferente";
        } else if (validarCorreo == false) {
            mensaje = "El Correo es incorrecto";
            peticion = "Favor de ingresar un correo correcto";
        } else if (txtTelefono.getText().equals("")) {
            mensaje = "El campo telefono es obligatorio";
        } else if (txtDireccion.getText().equals("")) {
            mensaje = "El campo direccion es obligatorio";
        } else if (txtContraseniaVisible.getText().equals("") && txtContraseniaOculta.getText().equals("")) {
            mensaje = "El campo contraseña es obligatorio";
        } else if (validarContrasenia == false && validarContraseniaO == false) {
            mensaje = "La contraseña tiene que tener mas de 8 digitos";
            peticion = "Favor de ingresar una contraseña valida";
        } else {
            return;
        }
        AlertBoxes.infoAlert(
                "Info", mensaje, peticion);
        throw new SenaException("prueba") {

        };

    }

}
