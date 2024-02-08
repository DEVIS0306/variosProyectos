package utilities;

import java.util.ArrayList;

/**
 * Clase ControllerGeneralModel.
 * 
 * Esta clase representa el modelo del controlador general.
 * Contiene métodos y funcionalidades comunes utilizadas por los controladores.
 */
public class ControllerGeneralModel {

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

}
