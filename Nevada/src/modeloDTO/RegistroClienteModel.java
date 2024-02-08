/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public interface RegistroClienteModel {

    /**
     * Inserta un nuevo usuario en el sistema utilizando los datos
     * proporcionados en un objeto ClienteDTO.
     *
     * @param clienteDTO El objeto ClienteDTO que contiene los datos del usuario
     * a insertar.
     * @throws Exception Si ocurre algún error durante la operación de
     * inserción.
     */
    void insertarUsuario(ClienteDTO clienteDTO) throws Exception;

    /**
     * Edita los datos de un usuario existente en el sistema utilizando los
     * datos proporcionados en un objeto ClienteDTO.
     *
     * @param clienteDTO El objeto ClienteDTO que contiene los nuevos datos del
     * usuario a editar.
     * @throws Exception Si ocurre algún error durante la operación de edición.
     */
    void editarUsuario(ClienteDTO clienteDTO) throws Exception;

    /**
     * Obtiene una lista de todos los usuarios registrados en el sistema.
     *
     * @return Una lista de objetos ClienteDTO que representan los usuarios
     * registrados.
     * @throws Exception Si ocurre algún error durante la operación de obtención
     * de la lista de usuarios.
     */
    List<ClienteDTO> listarUsuario() throws Exception;

    /**
     * Elimina un usuario del sistema utilizando su identificador.
     *
     * @param id El identificador del usuario a eliminar.
     * @throws Exception Si ocurre algún error durante la operación de
     * eliminación.
     */
    void eliminarCliente(int id) throws Exception;

    /**
     * Valida si existen usuarios en el sistema con el número de cédula
     * especificado.
     *
     * @param cc El número de cédula a validar.
     * @return La cantidad de usuarios encontrados con el número de cédula
     * especificado.
     * @throws Exception Si ocurre algún error durante la operación de
     * validación.
     */
    public int validarExisteUsuarios(String cc) throws Exception;
    
    public String obtenerNumeroDocomento(String documentoCompleto);
    
    
    
}
