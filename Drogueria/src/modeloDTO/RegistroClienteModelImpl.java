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
public class RegistroClienteModelImpl implements RegistroClienteModel {

    private final RegistrarClienteDAO registrarClienteDAO;

    /**
     * Constructor de la clase RegistroClienteModelImpl.
     *
     * @param registrarClienteDAO El objeto RegistrarClienteDAO que se utilizará
     * para realizar las operaciones de registro de clientes.
     */
    public RegistroClienteModelImpl(RegistrarClienteDAO registrarClienteDAO) {
        this.registrarClienteDAO = registrarClienteDAO;
    }

    /**
     * Inserta un nuevo usuario en el sistema utilizando los datos
     * proporcionados en un objeto ClienteDTO.
     *
     * @param clienteDTO El objeto ClienteDTO que contiene los datos del usuario
     * a insertar.
     * @throws Exception Si ocurre algún error durante la operación de
     * inserción.
     */
    @Override
    public void insertarUsuario(ClienteDTO clienteDTO) throws Exception {
        try {
            registrarClienteDAO.insertarUsuario(clienteDTO);
        } catch (Exception ex) {
            System.out.println("Error al registrar un clientes");
        }
    }

    /**
     * Edita los datos de un usuario existente en el sistema utilizando los
     * datos proporcionados en un objeto ClienteDTO.
     *
     * @param clienteDTO El objeto ClienteDTO que contiene los nuevos datos del
     * usuario a editar.
     * @throws Exception Si ocurre algún error durante la operación de edición.
     */
    @Override
    public void editarUsuario(ClienteDTO clienteDTO) throws Exception {
        try {
            registrarClienteDAO.editarUsuario(clienteDTO);
        } catch (Exception ex) {
            System.out.println("Error al editar un cliente");
        }
    }

    /**
     * Obtiene una lista de todos los usuarios registrados en el sistema.
     *
     * @return Una lista de objetos ClienteDTO que representan los usuarios
     * registrados.
     * @throws Exception Si ocurre algún error durante la operación de obtención
     * de la lista de usuarios.
     */
    @Override
    public List<ClienteDTO> listarUsuario() throws Exception {
        List<ClienteDTO> listaUsuarioSelecionado;
        try {
            listaUsuarioSelecionado = registrarClienteDAO.obtenerListaUsuario();
            return listaUsuarioSelecionado;
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return null;
    }

    /**
     * Elimina un usuario del sistema utilizando su identificador.
     *
     * @param id El identificador del usuario a eliminar.
     * @throws Exception Si ocurre algún error durante la operación de
     * eliminación.
     */
    @Override
    public void eliminarCliente(int id) throws Exception {
        try {
            registrarClienteDAO.eliminarCliente(id);

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

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
    @Override
    public int validarExisteUsuarios(String cc) {
        int respuesta = 1;
        try {
            respuesta = registrarClienteDAO.validarExisteUsuario(cc);
            return respuesta;
        } catch (Exception ex) {
            System.out.println("Error en el metodo validarExisteUsuario");
        }
        return respuesta = 1;
    }

    @Override
    public String obtenerNumeroDocomento(String documentoCompleto) {
        if (documentoCompleto.length() > 3) {
            return documentoCompleto.substring(3);
        } else {
            return "";
        }
    }
}
