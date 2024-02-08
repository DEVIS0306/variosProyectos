/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase utilitaria para gestionar la sesión de usuario en la aplicación.
 */
public final class SessionManager {

    /**
     * Constructor privado para evitar la creación de instancias de la clase.
     */
    private SessionManager() {
    }

    private static volatile Map<String, Object> session;

    /**
     * Establece un atributo en la sesión de usuario.
     *
     * @param name  Nombre del atributo.
     * @param value Valor del atributo.
     */
    public static void setAttribute(String name, Object value) {
        if (session == null) {
            session = new HashMap<>();
        }
        session.put(name, value);
    }

    /**
     * Obtiene el valor de un atributo de la sesión de usuario.
     *
     * @param name Nombre del atributo.
     * @return El valor del atributo, o null si no se encuentra en la sesión.
     */
    public static Object getAttribute(String name) {
        if (session.containsKey(name)) {
            return session.get(name);
        }
        return null;
    }

    /**
     * Verifica si un atributo está presente en la sesión de usuario.
     *
     * @param name Nombre del atributo.
     * @return true si el atributo está presente, false de lo contrario.
     */
    public static boolean containsAttribute(String name) {
        return session != null && session.containsKey(name);
    }

    /**
     * Elimina un atributo de la sesión de usuario.
     *
     * @param name Nombre del atributo a eliminar.
     */
    public static void removeAttribute(String name) {
        if (containsAttribute(name)) {
            session.remove(name);
        }
    }

    /**
     * Destruye la sesión de usuario, eliminando todos los atributos y liberando recursos.
     */
    public static void destroy() {
        if (session != null) {
            session.clear();
            session = null;
        }
    }

}
