/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloController;

import alerta.AlertBoxes;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import modeloDTO.ClienteDTO;
import modeloDTO.RegistrarClienteDAOImpl;
import modeloDTO.RegistroClienteModel;
import modeloDTO.RegistroClienteModelImpl;
import modeloDTO.SessionManager;
import modeloDTO.globalModel;
import modeloDTO.globalModelImpl;
import otros.BaseController;
import otros.ComboBoxItem;

/**
 * Controlador para la escena de clientes definida en un archivo FXML.
 *
 * Este controlador maneja la lógica y la interacción de la escena de clientes,
 * que incluye funcionalidades relacionadas con la gestión de clientes. Extiende
 * la clase BaseController para heredar comportamientos comunes. Implementa la
 * interfaz Initializable para proporcionar un método de inicialización.
 *
 */
public class DatosPersonalesSceneController extends BaseController implements Initializable {

    @FXML
    private TextField txtBuscarDocumento;
    @FXML
    private TextField txtFiltrarNombre;
    @FXML
    private Button botonRegistrar;
    @FXML
    private TableView<ClienteDTO> tablaUsuario;
    @FXML
    private TableColumn<Number, Number> colId;
    @FXML
    private TableColumn<ClienteDTO, String> colTipoDocumento;
    @FXML
    private TableColumn<ClienteDTO, String> colNombre;
    @FXML
    private TableColumn<ClienteDTO, String> colDireccion;
    @FXML
    private TableColumn<ClienteDTO, String> colTelefono;
    @FXML
    private Button botonCerrar;
    private globalModel globalModel;
    private RegistroClienteModel registroClienteModel;
    private ObservableList<ClienteDTO> data = FXCollections.observableArrayList();
    private ObservableList<ClienteDTO> filtroPersona = FXCollections.observableArrayList();
    private ObservableList<ClienteDTO> filtroDocumento = FXCollections.observableArrayList();
    private Predicate<ClienteDTO> predicateCombinado = dataPredicate -> true;
    private FilteredList<ClienteDTO> listaDataFiltrada = new FilteredList<>(data);

    /**
     * Inicializa el controlador después de que se haya cargado la escena.
     *
     * Este método se invoca automáticamente después de cargar la escena y se
     * puede utilizar para realizar tareas de inicialización adicionales
     * necesarias para el funcionamiento del controlador.
     *
     * @param url La ubicación utilizada para resolver rutas relativas.
     * @param rb Los recursos utilizados para localizar los objetos específicos
     * del controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.setBundle(bundle);
        globalModel = new globalModelImpl();
        registroClienteModel = new RegistroClienteModelImpl(new RegistrarClienteDAOImpl());
        try {
            List<ComboBoxItem> listaTipoDocumento = globalModel.listaTipoDocumento();
            txtBuscarDocumento.textProperty().addListener((observable, oldValue, newValue)
                    -> actualizarPredicateCombinado());
            txtFiltrarNombre.textProperty().addListener((observable, oldValue, newValue)
                    -> actualizarPredicateCombinado());
            cargarTablaClientes();
            convertirAMayusculas(txtBuscarDocumento);
            convertirAMayusculas(txtFiltrarNombre);
        } catch (Exception ex) {

        }
    }

    /**
     * Carga los datos de los clientes en la tabla de usuarios.
     *
     * Recupera la lista de clientes utilizando el modelo de registro de
     * clientes. Luego, limpia los datos existentes en la tabla y agrega los
     * nuevos datos. Configura las propiedades de las columnas de la tabla y
     * establece las celdas de la columna "id" como un índice incremental.
     * Finalmente, establece los datos en la tabla, ajusta el redimensionamiento
     * de columnas y realiza otros ajustes necesarios.
     *
     * @throws Exception Si ocurre un error al obtener la lista de clientes.
     */
    public void cargarTablaClientes() throws Exception {
        List<ClienteDTO> listaClientes = registroClienteModel.listarUsuario();
        data.clear();
        filtroPersona.clear();
        if (!listaClientes.isEmpty()) {
            data.addAll(listaClientes);
        }

       
        colTipoDocumento.setCellValueFactory(new PropertyValueFactory<>("numeroDocumento"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        Callback<TableColumn<ClienteDTO, String>, TableCell<ClienteDTO, String>> cellFactory = (param) -> {

            final TableCell<ClienteDTO, String> cell = new TableCell<ClienteDTO, String>() {

                @Override
                protected void updateItem(String valor, boolean empty) {
                    super.updateItem(valor, empty);
                    if (empty || valor.equals("null")) {
                        setText(null);
                    }
                }
            };
            return cell;
        };

        colId.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(
                tablaUsuario.getItems().indexOf(cellData.getValue()) + 1));
        tablaUsuario.setItems(data);
        tablaUsuario.setColumnResizePolicy((param) -> true);
        //ajustarAnchorColumnasTablaUnicoHeader(tablaUsuario);

    }

    /**
     * Maneja el evento de registrar un cliente.
     *
     * Abre una ventana modal para mostrar el formulario de registro de cliente.
     * Cuando se cierra la ventana modal, se actualiza la tabla de clientes.
     *
     * @throws Exception Si ocurre un error al cargar los datos en la tabla de
     * clientes.
     */
    @FXML
    private void registrarCliente() throws Exception {
        Stage stage = iniciarModalConEfectoGris(
                "/vistaFXML/DetalleClientesScene.fxml");
        stage.setOnHiding((WindowEvent windowEvent) -> {
            try {
                cargarTablaClientes();
            } catch (Exception ex) {
                ex.getStackTrace();
            }
        });
    }

    /**
     * Maneja el evento de editar un cliente.
     *
     * Obtiene el cliente seleccionado de la tabla de clientes. Si no se ha
     * seleccionado ningún cliente, muestra una alerta informativa y finaliza el
     * método. Si se ha seleccionado un cliente, guarda el cliente en el
     * atributo de sesión "clientesEditar", y abre una ventana modal para
     * mostrar el formulario de edición de cliente. Cuando se cierra la ventana
     * modal, se actualiza la tabla de clientes.
     *
     * @throws Exception Si ocurre un error al cargar los datos en la tabla de
     * clientes.
     */
    @FXML
    private void editarCliente() throws Exception {
        ClienteDTO clienteSeleccionado = tablaUsuario.getSelectionModel().getSelectedItem();
        if (null == clienteSeleccionado) {
            AlertBoxes.infoAlert("Informacion", "Debe Selecionar un Cliente", null);
            return;
        } else {
            try {
                SessionManager.setAttribute("clientesEditar", clienteSeleccionado);
                Stage stage = iniciarModalConEfectoGris(
                        "/vistaFXML/DetalleClientesScene.fxml");
                stage.setOnHiding((WindowEvent windowEvent) -> {
                    try {
                        cargarTablaClientes();
                    } catch (Exception ex) {
                        ex.getStackTrace();
                    }
                });
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(null, "error");
            }

        }
    }

    /**
     * Maneja el evento de eliminar un cliente.
     *
     * Obtiene el cliente seleccionado de la tabla de clientes. Si no se ha
     * seleccionado ningún cliente, muestra una alerta informativa y finaliza el
     * método. Si se ha seleccionado un cliente, muestra una ventana de
     * confirmación para confirmar la eliminación del cliente. Si el usuario
     * confirma la eliminación, se obtiene el ID del cliente y se llama al
     * método de eliminar cliente del modelo. Luego, se muestra una alerta
     * informativa de éxito y se actualiza la tabla de clientes.
     *
     * Si ocurre un error durante el proceso de eliminación del cliente, se
     * captura y muestra el stack trace del error.
     */
    @FXML
    void eliminarCliente() {
        ClienteDTO clienteSeleccionado = tablaUsuario.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            String eliminar = AlertBoxes.confirmAlert("Informacion", "Desea Elimar el cliente con nombre " + clienteSeleccionado.getNombre() + "?", "");
            if (eliminar.equals("si")) {

                try {
                    int id = clienteSeleccionado.getId();
                    registroClienteModel.eliminarCliente(id);
                    AlertBoxes.infoAlert("CLIENTE", "EL CLIENTE SE ELIMINO EXITOSA MENTE", "");
                    cargarTablaClientes();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        } else {
            AlertBoxes.infoAlert("CLIENTE", "DEBES SELECIONAR EL CLIENTE QUE VAS A ELIMINAR", "");
        }
    }

    /**
     * Actualiza el predicado combinado utilizado para filtrar los datos en la
     * tabla de cotizantes. El predicado se construye combinando cuatro
     * predicados separados, cada uno verificando una condición diferente basada
     * en la entrada del usuario en campos de texto y comboBox combinados.
     */
    private void actualizarPredicateCombinado() {
        Predicate<ClienteDTO> predicate1 = clienteDTO
                -> clienteDTO.getNumeroDocumento().contains(txtBuscarDocumento.getText());

        Predicate<ClienteDTO> predicate2 = clienteDTO
                -> clienteDTO.getNombre().contains(txtFiltrarNombre.getText());

        predicateCombinado = predicate1.and(predicate2);
        listaDataFiltrada.setPredicate(predicateCombinado);
        tablaUsuario.setItems(listaDataFiltrada);
    }
    /**
     * Limpia los text field del filtro
     */
    @FXML
    void limpiarCampoFiltro(){
        txtBuscarDocumento.setText("");
        txtFiltrarNombre.setText("");
    }

}
