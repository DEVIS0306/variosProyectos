/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz RegistroAdminModel que proporciona
 * funcionalidad para el registro de administradores.
 */
public class RegistroAdminModelImpl implements RegistroAdminModel {

    private final RegistrarAdminDAO registrarAdminDAO;

    /**
     * Constructor de la clase RegistroAdminModelImpl.
     *
     * @param registrarAdminDAO Objeto RegistrarAdminDAO que se utiliza para
     * acceder a los datos del registro de administradores.
     */
    public RegistroAdminModelImpl(RegistrarAdminDAO registrarAdminDAO) {
        this.registrarAdminDAO = registrarAdminDAO;
    }

    /**
     * Inserta un usuario en la base de datos.
     *
     * @param registroAdminDTO Objeto RegistroAdminDTO que contiene los datos
     * del usuario a insertar.
     * @throws Exception Si ocurre algún error durante la inserción.
     */
    @Override
    public void insertarUsuario(RegistroAdminDTO registroAdminDTO) throws Exception {
        try {
            registrarAdminDAO.insertarUsuario(registroAdminDTO);
        } catch (Exception ex) {
            System.out.println("Error en RegistroAdminModelImpl");
        }
    }

    /**
     * Edita un usuario existente en la base de datos.
     *
     * @param registroAdminDTO Objeto RegistroAdminDTO que contiene los nuevos
     * datos del usuario a editar.
     * @throws Exception Si ocurre algún error durante la edición.
     */
    @Override
    public void editarUsuario(RegistroAdminDTO registroAdminDTO) throws Exception {
        try {
            registrarAdminDAO.editarUsuario(registroAdminDTO);
        } catch (Exception ex) {
            System.out.println("Error en RegistroAdminModelImpl");
        }
    }

    /**
     * Inserta una lista de roles de administrador en la base de datos.
     *
     * @param administrador Lista de objetos InsertarRolesDTO que contienen los
     * datos de los roles a insertar.
     * @throws Exception Si ocurre algún error durante la inserción.
     */
    @Override
    public void insertarRol(ArrayList<InsertarRolesDTO> administrador) throws Exception {
        try {
            registrarAdminDAO.insertarRoles(administrador);
        } catch (Exception ex) {
            System.out.println("Error al insertar roles");
        }
    }

    /**
     * Valida la existencia de un usuario.
     *
     * @return Un entero que representa la existencia del usuario. 1 si existe,
     * 0 si no existe.
     */
    @Override
    public int validarExisteUsuarios() {
        int respuesta = 1;
        try {
            respuesta = registrarAdminDAO.validarExisteUsuario();
            return respuesta;
        } catch (Exception ex) {
            System.out.println("Error al insertar roles");
        }
        return respuesta = 1;
    }

    /**
     * Obtiene una lista de usuarios que coinciden con el nombre de usuario
     * especificado.
     *
     * @param usuario El nombre de usuario a buscar.
     * @return Una lista de objetos RegistroAdminDTO que representan los
     * usuarios encontrados.
     * @throws Exception Si ocurre algún error durante la búsqueda.
     */
    @Override
    public List<RegistroAdminDTO> listarUsuario(String usuario) throws Exception {
        List<RegistroAdminDTO> listaUsuarioSelecionado;
        try {
            listaUsuarioSelecionado = registrarAdminDAO.obtenerListaUsuario(usuario);
            return listaUsuarioSelecionado;
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return null;
    }

}
