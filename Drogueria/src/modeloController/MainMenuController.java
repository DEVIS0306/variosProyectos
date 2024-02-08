/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloController;

import alerta.AlertBoxes;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modeloDTO.RegistroAdminDTO;
import otros.BaseController;
import modeloDTO.SessionManager;

/**
 * Controlador para la interfaz de menú principal.
 * Extiende la clase BaseController e implementa la interfaz Initializable.
 */
public class MainMenuController extends BaseController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane gridPrincipal;
    @FXML
    private TreeView<String> verticalMenu;
    @FXML
    private Button botonSalir;
    private HashMap<String, String> mapaMenu = new HashMap<>();
    private List<RegistroAdminDTO> listaUsuarioActual;
    private final int administrador = 1;
    private final int empleado = 2;
    private final int usuario = 3;

     /**
     * Inicializa el controlador después de que se haya cargado la interfaz.
     *
     * @param url    la ubicación utilizada para resolver rutas relativas de recursos
     * @param bundle el paquete de recursos utilizado para localizar recursos
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        super.setBundle(bundle);
        SessionManager.setAttribute("gridPrincipal", gridPrincipal);
        SessionManager.setAttribute("bodyContainer", gridPane);
        llenarMapaMenu();

        listaUsuarioActual = (List<RegistroAdminDTO>) SessionManager.getAttribute("usuarioSelecionado");

        /**
         * Creamos el menú lateral
         */
        TreeItem<String> rootItem = new TreeItem<>("root");
        if (listaUsuarioActual.get(0).getIdRol() == administrador) {

            TreeItem<String> menuItem0 = new TreeItem<>(("Usuario"));
            TreeItem<String> menuItem1 = new TreeItem<>(("Ventas"));

            TreeItem<String> Usuario
                    = new TreeItem<>(("Clientes"));

            TreeItem<String> Actividad
                    = new TreeItem<>(("Ventas"));

            menuItem0.getChildren().add(Usuario);
            menuItem1.getChildren().add(Actividad);

            rootItem.getChildren().add(menuItem0);
            rootItem.getChildren().add(menuItem1);
        } else if (listaUsuarioActual.get(0).getIdRol() == usuario) {
            TreeItem<String> menuItem0 = new TreeItem<>(("Mi Cuenta"));

            TreeItem<String> Cuenta
                    = new TreeItem<>(("Perfil"));

            menuItem0.getChildren().add(Cuenta);

            rootItem.getChildren().add(menuItem0);
        }

        verticalMenu.setRoot(rootItem);
        verticalMenu.setShowRoot(false);
        cargarVentanaClientes();

    }

    /**
     * Método que se ejecuta cuando se selecciona un elemento del menú lateral.
     *
     * @param ev el evento de ratón que desencadena la acción
     * @throws Exception si ocurre un error durante la validación del menú
     */
    public void leerMenu(MouseEvent ev) throws Exception {
        SessionManager.removeAttribute("Clientes");
        try {
            validarMenu();
        } catch (Exception ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     /**
     * Valida el elemento de menú seleccionado y carga la ventana correspondiente.
     *
     * @throws Exception si ocurre un error durante la validación del menú
     */
    private void validarMenu() throws Exception {

        TreeItem<String> item = verticalMenu.getSelectionModel().getSelectedItem();

        if (item == null) {
            return;
        }
        String itemMenu = item.getValue();
        if (itemMenu.equals(("Clientes"))) {
            cargarVentana("/vistaFXML/ClientesScene.fxml",
                    "bodyContainer");

        }else if(itemMenu.equals("Ventas")){
            cargarVentana("/vistaFXML/InicioCategoriaScene.fxml",
                    "bodyContainer");
        }

        item.setExpanded(!item.isExpanded());

    }

     /**
     * Llena el mapa de menú con los nombres de los elementos y sus ubicaciones correspondientes.
     */
    private void llenarMapaMenu() {
        mapaMenu.put(("Clientes"),
                "vistaFXML//ClientesScene");
        mapaMenu.put(("Ventas"),
                "vistaFXML//InicioCategoriaScene");
    }

     /**
     * Maneja el evento de salida del sistema.
     */
    @FXML
    public void salir() {
        Parent root = null;
        try {
            String confirmar = AlertBoxes.confirmAlert("Confirmacion", "Esta seguro que desea salir?", "");
            if (confirmar.equals("si")) {

                String url = "/vistaFXML/MainLoginScene.fxml";
                root = FXMLLoader.load(getClass().getResource(url));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.getIcons().add(new javafx.scene.image.Image("/img/sena.jpg"));
                stage.setTitle("Sistema de Drogueria");
                stage.setScene(scene);
                stage.show();

                Stage myStage = (Stage) this.botonSalir.getScene().getWindow();
                SessionManager.removeAttribute("usuarioSelecionado");
                SessionManager.removeAttribute("campoEditar");
                SessionManager.removeAttribute("categoriaEditar");
                myStage.close();
            }

        } catch (Exception ex) {

        }
    }

}
