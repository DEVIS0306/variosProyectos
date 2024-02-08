/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import otros.ComboBoxItem;

/**
 * Implementación de la interfaz globalModel que proporciona funcionalidades
 * comunes. Esta clase también implementa la interfaz Initializable para
 * inicializar componentes.
 */
public class globalModelImpl implements globalModel, Initializable {

    @FXML
    private StackPane containerForm;
    @FXML
    private GridPane stackPane;

    private VBox panelFormSignUp;

    private ResourceBundle bundle;
    public Stage stage;

    /**
     * Obtiene una lista de elementos para poblar un ComboBox con los tipos de
     * documento.
     *
     * @return Lista de ComboBoxItem con los tipos de documento.
     * @throws Exception Si ocurre algún error durante la obtención de la lista.
     */
    @Override
    public List<ComboBoxItem> listaTipoDocumento() throws Exception {
        List<String> tipoDocumento = new ArrayList<>();
        List<String> diminutivotipoDocumento = new ArrayList<>();
        List<ComboBoxItem> listaDocumento = new ArrayList<>();
        tipoDocumento.add("CEDULA DE CIUDADANIA");
        tipoDocumento.add("NIT");
        tipoDocumento.add("CEDULA EXTRANJERIA");
        tipoDocumento.add("PASAPORTE");
        tipoDocumento.add("TARJETA DE IDENTIDAD");
        tipoDocumento.add("REGISTRO CIVIL");
        tipoDocumento.add("CARNÉ DIPLOMATICO");
        tipoDocumento.add("SALVOCONDUCTO DE PERMANENCIA");
        tipoDocumento.add("PERMISO ESPECIAL DE PERMANENCIA");
        tipoDocumento.add("PERMISO POR PROTECCIÓN TEMPORAL");
        diminutivotipoDocumento.add("CC");
        diminutivotipoDocumento.add("NI");
        diminutivotipoDocumento.add("CE");
        diminutivotipoDocumento.add("PA");
        diminutivotipoDocumento.add("TI");
        diminutivotipoDocumento.add("RC");
        diminutivotipoDocumento.add("CD");
        diminutivotipoDocumento.add("SC");
        diminutivotipoDocumento.add("PE");
        diminutivotipoDocumento.add("PT");

        for (int i = 0; i < tipoDocumento.size(); i++) {
            ComboBoxItem ListaTipoDocumento = new ComboBoxItem();
            ListaTipoDocumento.setValor(diminutivotipoDocumento.get(i));
            ListaTipoDocumento.setEtiqueta(tipoDocumento.get(i));
            listaDocumento.add(ListaTipoDocumento);
        }
        listaDocumento.sort(Comparator.comparing(ComboBoxItem::getEtiqueta));
        return listaDocumento;
    }

    /**
     * Retorna el documento completo a partir de su representación abreviada.
     *
     * @param documento La representación abreviada del documento.
     * @return El documento completo.
     */
    public String documentoCompleto(String documento) {
        if (documento.endsWith("CC")) {
            documento = "CEDULA DE CIUDADANIA";
        } else if (documento.endsWith("NI")) {
            documento = "NIT";
        } else if (documento.endsWith("CE")) {
            documento = "CEDULA EXTRANJERIA";
        } else if (documento.endsWith("PA")) {
            documento = "PASAPORTE";
        } else if (documento.endsWith("TI")) {
            documento = "TARJETA DE IDENTIDAD";
        } else if (documento.endsWith("RC")) {
            documento = "REGISTRO CIVIL";
        } else if (documento.endsWith("CD")) {
            documento = "CARNÉ DIPLOMATICO";
        } else if (documento.endsWith("SC")) {
            documento = "SALVOCONDUCTO DE PERMANENCIA";
        } else if (documento.endsWith("PE")) {
            documento = "PERMISO ESPECIAL DE PERMANENCIA";
        } else if (documento.endsWith("PT")) {
            documento = "PERMISO POR PROTECCIÓN TEMPORAL";
        }
        return documento;
    }

    /**
     * Valida que el texto de un TextField contenga únicamente caracteres
     * numéricos y cumpla con una longitud máxima.
     *
     * @param texto El TextField a validar.
     * @param longitudMaxima La longitud máxima permitida para el texto.
     */
    public void validarSoloNumeros(TextField texto, int longitudMaxima) {
        texto.textProperty().addListener((obs, oldValue, newValue) -> {
            if (texto.getText().length() > longitudMaxima) {
                String substring = texto.getText().substring(0, longitudMaxima);
                texto.setText(substring);
            }
            if (!texto.getText().matches("\\d+") && !texto.getText().equals("")) {
                String s = texto.getText().substring(0, texto.getLength() - 1);
                texto.setText(s);
            }
            texto.setText(texto.getText().replaceAll("^0+(?=\\d+$)", ""));
        });
    }

    /**
     * Limita la longitud del texto en un TextField.
     *
     * @param texto El TextField al que se aplicará la limitación.
     * @param limite La longitud máxima permitida para el texto.
     */
    protected static void limiteTextField(final TextField texto, final int limite) {
        texto.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> obs,
                    final String oldValue, final String newValue) {
                if (texto.getText().length() > limite) {
                    String s = texto.getText().substring(0, limite);
                    texto.setText(s);
                }
            }

        });
    }

    /**
     * Valida que un TextField contenga solo letras y números, convirtiendo el
     * texto a mayúsculas.
     *
     * @param texto El TextField a validar.
     */
    protected void validarSoloLetrasNumeros(TextField texto) {
        texto.setTextFormatter(new TextFormatter<>((change) -> {
            if (change.getText().matches("^[A-Za-z0-9 ]+$")) {
                change.setText(change.getText().toUpperCase());
                return change;
            } else {
                change.setText("");
                change.setText(change.getText().toUpperCase());
                return change;
            }
        }));
    }

    /**
     * Valida que un TextField contenga solo letras, convirtiendo el texto a
     * mayúsculas.
     *
     * @param texto El TextField a validar.
     */
    protected void validarSoloLetras(TextField texto) {
        texto.setTextFormatter(new TextFormatter<>((change) -> {
            if (change.getText().matches("^[A-Za-z]+$")) {
                change.setText(change.getText().toUpperCase());
                return change;
            } else {
                change.setText("");
                // change.setText(change.getText().toUpperCase());
                return change;
            }
        }));
    }

    /**
     * Valida que un TextField contenga solo caracteres permitidos, convirtiendo
     * el texto a mayúsculas.
     *
     * @param texto El TextField a validar.
     */
    protected void validarCaracteres(TextField texto) {
        texto.setTextFormatter(new TextFormatter<>((change) -> {
            if (!change.getText().matches("^[ÑñA-Za-z0-9 ,.\"%&)(;-]+$")) {
                change.setText("");
            }
            change.setText(change.getText().toUpperCase(bundle.getLocale()));
            return change;
        }));

    }

    /**
     * Obtiene el tamaño de un ArrayList incrementado en 1.
     *
     * @param list El ArrayList del que se desea obtener el tamaño incrementado.
     * @return El tamaño del ArrayList incrementado en 1.
     */
    public static int enumSizeExcepcion(ArrayList<String> list) {
        return list.size() + 1;
    }

    /**
     * Convierte los elementos de un ArrayList en una cadena de texto separada
     * por saltos de línea.
     *
     * @param datos El ArrayList del que se obtendrá la cadena de texto.
     * @return La cadena de texto resultante.
     */
    public static String toString(ArrayList<String> datos) {
        String text = "";
        for (int i = 0; i < datos.size(); i++) {
            text = text + datos.get(i) + "\n";
        }
        return text;
    }

    /**
     * Valida si una dirección de correo electrónico es válida.
     *
     * @param email La dirección de correo electrónico a validar.
     * @return true si la dirección de correo es válida, de lo contrario false.
     */
    public static boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    /**
     * Valida si una contraseña cumple con los requisitos mínimos.
     *
     * @param contrasenia La contraseña a validar.
     * @return true si la contraseña cumple con los requisitos mínimos, de lo
     * contrario false.
     */
    public static boolean validarContrasenia(String contrasenia) {
        boolean respuesta = false;
        if (contrasenia.length() < 8) {
            respuesta = false;
            return respuesta;
        }
        respuesta = true;
        return respuesta;
    }

    /**
     * Método de inicialización de la interfaz. No soportado actualmente.
     *
     * @param location La ubicación del recurso.
     * @param resources Los recursos utilizados.
     * @throws UnsupportedOperationException Si el método no está soportado.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Carga y devuelve un formulario desde una URL en un contenedor VBox.
     *
     * @param url La URL del formulario a cargar.
     * @return El VBox que contiene el formulario cargado.
     * @throws IOException Si ocurre un error al cargar el formulario.
     */
    private VBox loadForm(String url) throws IOException {
        return (VBox) FXMLLoader.load(getClass().getResource(url));
    }

    /**
     * Selecciona un valor en un ComboBox basado en su etiqueta o valor.
     *
     * @param comboBox El ComboBox en el que se realizará la selección.
     * @param value El valor o etiqueta a seleccionar.
     */
    protected void selectComboBoxValue(ComboBox<ComboBoxItem> comboBox, String value) {
        if (value != null) {
            for (ComboBoxItem item : comboBox.getItems()) {
                if (value.equals(item.getValor()) || value.equals(item.getEtiqueta())) {
                    comboBox.getSelectionModel().select(item);
                    break;
                }
            }
        }
    }

    /**
     * Establece los elementos de un ComboBox a partir de una lista de
     * ComboBoxItem.
     *
     * @param comboBox El ComboBox al que se agregarán los elementos.
     * @param items La lista de ComboBoxItem a agregar.
     */
    protected void setComboBoxItems(ComboBox<ComboBoxItem> comboBox, List<ComboBoxItem> items) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll(items);
    }

    /**
     * Obtiene una lista de elementos para poblar un ComboBox con los estados.
     *
     * @return Lista de ComboBoxItem con los estados.
     * @throws Exception Si ocurre algún error durante la obtención de la lista.
     */
    @Override
    public List<ComboBoxItem> listaEstado() throws Exception {
        List<String> tipoEstado = new ArrayList<>();
        List<String> diminutivotipoEstado = new ArrayList<>();
        List<ComboBoxItem> listaEstado = new ArrayList<>();
        tipoEstado.add("ACTIVO");
        tipoEstado.add("DESACTIVO");
        diminutivotipoEstado.add("1");
        diminutivotipoEstado.add("0");
        for (int i = 0; i < tipoEstado.size(); i++) {
            ComboBoxItem ListaTipoDocumento = new ComboBoxItem();
            ListaTipoDocumento.setValor(diminutivotipoEstado.get(i));
            ListaTipoDocumento.setEtiqueta(tipoEstado.get(i));
            listaEstado.add(ListaTipoDocumento);
        }
        listaEstado.sort(Comparator.comparing(ComboBoxItem::getEtiqueta));
        return listaEstado;
    }

    /**
     * Ajusta el tamaño de las columnas de una tabla para que se ajusten al
     * contenido de sus celdas y al encabezado.
     *
     * @param tabla La tabla a la que se ajustarán las columnas.
     */
    public void ajustarAnchorColumnasTablaUnicoHeader(TableView<?> tabla) {
        tabla.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        tabla.getColumns().stream().forEach((columna) -> {
            modificarAnchorColumnas(tabla, columna);
        });
    }

    /**
     * Modifica el ancho de una columna de tabla para que se ajuste al contenido
     * de sus celdas y al encabezado.
     *
     * @param tabla La tabla a la que pertenece la columna.
     * @param columna La columna que se modificará.
     */
    public void modificarAnchorColumnas(TableView<?> tabla, TableColumn columna) {
        //Obtenemos mínimo tamaño será el header
        Text t = new Text(columna.getText());
        double widthMaximo = t.getLayoutBounds().getWidth();

        for (int i = 0; i < tabla.getItems().size(); i++) {
            if (columna.getCellData(i) != null) {
                t = new Text(columna.getCellData(i).toString());
                double nuevoWidth = t.getLayoutBounds().getWidth();
                //cambiamos el tamaño maximo si la data es mayor que el header
                if (nuevoWidth > widthMaximo) {
                    widthMaximo = nuevoWidth;
                }
            }
        }
        columna.setPrefWidth(widthMaximo + 50.0d);
    }

    /**
     * Cierra la ventana asociada al control proporcionado.
     *
     * @param control El control cuya ventana se cerrará.
     */
    protected void cerrarVentana(Control control) {

        if (control != null && control.getScene().getWindow() instanceof Stage) {
            ((Stage) control.getScene().getWindow()).close();
            /**
             * elimina el efecto de shadow al grid principal al cerrar una modal
             */
            GridPane gridPrincipal = (GridPane) SessionManager.getAttribute("gridPrincipal");
            Objects.requireNonNull(gridPrincipal).setEffect(new ColorAdjust(0, 0, 0, 0));
        } else {

        }

    }
}
