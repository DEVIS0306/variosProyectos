/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

import java.util.List;
import javafx.scene.control.TextField;
import otros.ComboBoxItem;

/**
 * Interfaz que define métodos comunes utilizados en el modelo global de la
 * aplicación.
 */
public interface globalModel {

    /**
     * Obtiene una lista de elementos para poblar un ComboBox con los tipos de
     * documento.
     *
     * @return Lista de ComboBoxItem con los tipos de documento.
     * @throws Exception Si ocurre algún error durante la obtención de la lista.
     */
    List<ComboBoxItem> listaTipoDocumento() throws Exception;

    /**
     * Obtiene una lista de elementos para poblar un ComboBox con los estados.
     *
     * @return Lista de ComboBoxItem con los estados.
     * @throws Exception Si ocurre algún error durante la obtención de la lista.
     */
    List<ComboBoxItem> listaEstado() throws Exception;

    /**
     * Valida que el texto de un TextField contenga únicamente caracteres
     * numéricos y cumpla con una longitud máxima.
     *
     * @param texto El TextField a validar.
     * @param longitudMaxima La longitud máxima permitida para el texto.
     */
    void validarSoloNumeros(TextField texto, int longitudMaxima);

    /**
     * Retorna el documento completo a partir de su representación abreviada.
     *
     * @param documento La representación abreviada del documento.
     * @return El documento completo.
     */
    String documentoCompleto(String documento);

}
