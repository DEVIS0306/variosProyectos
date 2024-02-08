/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaz que define los métodos para el modelo de registro de administrador.
 */
public interface RegistroAdminModel {

    /**
     * Inserta un usuario en la base de datos.
     *
     * @param registroAdminDTO Objeto RegistroAdminDTO que contiene los datos
     * del usuario a insertar.
     * @throws Exception Si ocurre algún error durante la inserción.
     */
    void insertarUsuario(RegistroAdminDTO registroAdminDTO) throws Exception;

    /**
     * Edita un usuario existente en la base de datos.
     *
     * @param registroAdminDTO Objeto RegistroAdminDTO que contiene los nuevos
     * datos del usuario a editar.
     * @throws Exception Si ocurre algún error durante la edición.
     */
    void editarUsuario(RegistroAdminDTO registroAdminDTO) throws Exception;

    /**
     * Inserta una lista de roles de administrador en la base de datos.
     *
     * @param administrador Lista de objetos InsertarRolesDTO que contienen los
     * datos de los roles a insertar.
     * @throws Exception Si ocurre algún error durante la inserción.
     */
    void insertarRol(ArrayList<InsertarRolesDTO> administrador) throws Exception;

    /**
     * Valida la existencia de un usuario.
     *
     * @return Un entero que representa la existencia del usuario. 1 si existe,
     * 0 si no existe.
     * @throws Exception Si ocurre algún error durante la validación.
     */
    public int validarExisteUsuarios() throws Exception;

    /**
     * Obtiene una lista de usuarios que coinciden con el nombre de usuario
     * especificado.
     *
     * @param usuario El nombre de usuario a buscar.
     * @return Una lista de objetos RegistroAdminDTO que representan los
     * usuarios encontrados.
     * @throws Exception Si ocurre algún error durante la búsqueda.
     */
    List<RegistroAdminDTO> listarUsuario(String usuario) throws Exception;

}
