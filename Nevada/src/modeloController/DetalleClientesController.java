/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloController;

import alerta.AlertBoxes;
import alerta.SenaException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modeloDTO.ClienteDTO;
import modeloDTO.RegistrarClienteDAOImpl;
import modeloDTO.RegistroClienteModel;
import modeloDTO.RegistroClienteModelImpl;
import modeloDTO.globalModel;
import modeloDTO.globalModelImpl;
import otros.ComboBoxItem;
import modeloDTO.SessionManager;

/**
 * Controlador de la vista DetalleClientesScene.fxml.
 *
 * Esta clase implementa la interfaz Initializable para proporcionar un método
 * de inicialización. Extiende la clase globalModelImpl para heredar su
 * funcionalidad relacionada con el modelo global.
 *
 * Esta clase se encarga de controlar la lógica de la vista
 * DetalleClientesScene.fxml, incluyendo el manejo de eventos, la interacción
 * con el modelo de datos y la actualización de la interfaz gráfica.
 *
 * Se debe implementar el método initialize() de la interfaz Initializable para
 * realizar las tareas de inicialización necesarias al cargar la vista.
 */
public class DetalleClientesController extends globalModelImpl implements Initializable {

    @FXML
    private ComboBox<ComboBoxItem> comboTipoDocumento;
    @FXML
    private TextField textoDocumento;
    @FXML
    private TextField textoNombre;
    @FXML
    private TextField textoDireccion;
    @FXML
    private TextField textoTelefono;
    @FXML
    private Button botonRegistrar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Label labelCerrar;
    private globalModel globalModel;
    private RegistroClienteModel registroClienteModel;
    private ClienteDTO clienteActual;

    /**
     *
     * Inicializa el controlador al cargar la vista asociada.
     *
     * Realiza tareas de inicialización, como la creación de instancias de
     * modelos y la carga del formulario.
     *
     * @param url la URL de la ubicación del archivo FXML, no utilizada en este
     * método
     *
     * @param rb el ResourceBundle que se utiliza para localizar los archivos de
     * recursos, no utilizado en este método
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        globalModel = new globalModelImpl();
        registroClienteModel = new RegistroClienteModelImpl(new RegistrarClienteDAOImpl());
        clienteActual = (ClienteDTO) SessionManager.getAttribute("clientesEditar");
        try {
            cargarFormulario();
        } catch (Exception ex) {

        }
    }

    /**
     *
     * Carga el formulario de detalle de clientes.
     *
     * Realiza tareas de inicialización y configuración de los componentes del
     * formulario, como la carga de datos iniciales,
     *
     * la validación de campos de texto y la selección de elementos en un combo
     * box.
     *
     * Si hay un cliente actualmente seleccionado en la sesión, carga sus datos
     * en los campos correspondientes y deshabilita
     *
     * la edición del tipo de documento y número de documento.
     *
     * @throws Exception si ocurre algún error durante la carga del formulario
     */
    private void cargarFormulario() throws Exception {

        try {
            List<ComboBoxItem> listaTipoDocumento = globalModel.listaTipoDocumento();
            setComboBoxItems(comboTipoDocumento, listaTipoDocumento);
            validarSoloNumeros(textoTelefono, 12);
            validarSoloLetrasNumeros(textoNombre);
            validarSoloLetrasNumeros(textoDocumento);
            validarSoloLetrasNumeros(textoDireccion);

            if (SessionManager.containsAttribute("clientesEditar")) {
                textoNombre.setText(clienteActual.getNombre());
                textoDireccion.setText(clienteActual.getDireccion());
                textoTelefono.setText(clienteActual.getTelefono());
                String numeroDocumento  = registroClienteModel.obtenerNumeroDocomento(clienteActual.getNumeroDocumento());
                textoDocumento.setText(numeroDocumento);
                String elementoSelecionado = seleccionarComboEditar(clienteActual.getDescripcionComoboBox());
                selectComboBoxValue(comboTipoDocumento, elementoSelecionado);

                comboTipoDocumento.setDisable(true);
                textoDocumento.setDisable(true);
            } else {
                comboTipoDocumento.getSelectionModel().selectFirst();
            }
        } catch (Exception ex) {

        }
    }

    /**
     *
     * Método invocado al presionar el botón "Aceptar" en la ventana de detalle
     * de clientes. Realiza las siguientes acciones: Valida los campos del
     * formulario. Crea un objeto ClienteDTO y obtiene los datos del formulario.
     * Si hay un cliente actualmente seleccionado en la sesión, se realiza la
     * edición del cliente. Si no hay un cliente actualmente seleccionado en la
     * sesión, se valida si ya existe un cliente con el mismo tipo de documento
     * y número de documento. Cierra la ventana de detalle de clientes.
     *
     * @throws Exception si ocurre algún error durante la ejecución del método
     */
    @FXML
    void aceptarVentana() {

        try {
            validarCamposFormulario();
            ClienteDTO clienteDTO = new ClienteDTO();
            obtenerDatosFormulario(clienteDTO);
            if (SessionManager.containsAttribute("clientesEditar")) {
                registroClienteModel.editarUsuario(clienteDTO);
                AlertBoxes.infoAlert("CLIENTE", "EL CLIENTE SE EDITO EXITOSA MENTE", "");
                SessionManager.removeAttribute("clientesEditar");

            } else {

                if (registroClienteModel.validarExisteUsuarios(clienteDTO.getTipoDocumento() + " " + clienteDTO.getNumeroDocumento()) != 0) {
                    AlertBoxes.infoAlert("CLIENTE", "EL CLIENTE YA EXISTE", "");
                } else {
                    registroClienteModel.insertarUsuario(clienteDTO);
                    AlertBoxes.infoAlert("CLIENTE", "EL CLIENTE SE REGISTRO EXITOSA MENTE", "");
                }

            }
            cerrarVentana(labelCerrar);

        } catch (Exception ex) {

        }
    }

    /**
     *
     * Método utilizado para seleccionar un elemento en el ComboBox de tipo de
     * documento al editar un cliente. Realiza las siguientes acciones: Obtiene
     * la descripción del tipo de documento seleccionado. Extrae los primeros
     * dos caracteres y la descripción restante. Busca el elemento
     * correspondiente en la lista de elementos del ComboBox y lo selecciona.
     * Devuelve la representación en cadena del elemento seleccionado.
     *
     * @param seleccion la descripción del tipo de documento seleccionado
     * @return la representación en cadena del elemento seleccionado en el
     * ComboBox
     */
    private String seleccionarComboEditar(String seleccion) {
        // Obtener la descripción del tipo de documento
        String descripcion = seleccion;
        String primerosDosCaracteres = descripcion.substring(0, 2);
        String descripcionSinPrimerosTresCaracteres = descripcion.substring(3);

        // Establecer el valor del campo de texto del documento
        ObservableList<ComboBoxItem> items = comboTipoDocumento.getItems();
        primerosDosCaracteres = globalModel.documentoCompleto(primerosDosCaracteres);
        String respuesta = "";

        for (ComboBoxItem item : items) {
            if (item.getEtiqueta().equals(primerosDosCaracteres)) {
                comboTipoDocumento.setValue(item);
                respuesta = item.toString();
                break;
            }
        }
        return respuesta;

    }

    /**
     *
     * Obtiene los datos del formulario y los asigna a un objeto ClienteDTO.
     * Realiza las siguientes acciones: Verifica si hay un cliente en edición y
     * asigna su ID correspondiente al objeto ClienteDTO. Obtiene el elemento
     * seleccionado en el ComboBox de tipo de documento y asigna su valor al
     * objeto ClienteDTO. Asigna los valores de los campos de texto del
     * formulario al objeto ClienteDTO.
     *
     * @param clienteDTO el objeto ClienteDTO en el que se asignarán los datos
     * del formulario
     */
    private void obtenerDatosFormulario(ClienteDTO clienteDTO) {
        if (SessionManager.containsAttribute("clientesEditar")) {
            clienteDTO.setId(clienteActual.getId());
        }
        ComboBoxItem itemSeleccionado = comboTipoDocumento.getSelectionModel().getSelectedItem();
        String diminutivo = itemSeleccionado.getValor();
        clienteDTO.setTipoDocumento(diminutivo);
        clienteDTO.setNumeroDocumento(textoDocumento.getText());
        clienteDTO.setNombre(textoNombre.getText());
        clienteDTO.setDireccion(textoDireccion.getText());
        clienteDTO.setTelefono(textoTelefono.getText());
    }

    /**
     *
     * Valida los campos del formulario para asegurarse de que estén completos.
     * Lanza una excepción SenaException si algún campo obligatorio está vacío.
     *
     * @throws SenaException si algún campo obligatorio está vacío
     */
    public void validarCamposFormulario() throws SenaException {
        String mensaje = "";
        String peticion = "Favor de ingresar un valor";
        if (textoDocumento.getText().equals("")) {
            textoDocumento.requestFocus();
            mensaje = "El campo Documento es obligatorio";
        } else if (textoNombre.getText().equals("")) {
            textoNombre.requestFocus();
            mensaje = "El campo nombre es obligatorio";
        } else {
            return;
        }
        AlertBoxes.infoAlert(
                "Info", mensaje, peticion);
        throw new SenaException("error") {

        };
    }

    /**
     *
     * Cancela la ventana actual. Muestra una alerta de confirmación y cierra la
     * ventana si el usuario confirma. Si se cancela, se elimina el atributo
     * "clientesEditar" de SessionManager.
     */
    @FXML
    public void cancelarVentana() {
        String confirmar = AlertBoxes.confirmAlert("Confirmacion", "Esta seguro que desea salir?", "Los cambio hecho en esta pantalla se perderan");
        if (confirmar.equals("si")) {
            SessionManager.removeAttribute("clientesEditar");
            cerrarVentana(labelCerrar);
        }
    }

}
