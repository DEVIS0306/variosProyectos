/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

import conexionSQL.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class RegistrarClienteDAOImpl extends Conexion implements RegistrarClienteDAO {

    static ResultSet res;

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
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO clientes (cc, nombre, direccion, telefono) VALUES (?,?,?,?)");
            ps.setString(1, clienteDTO.getTipoDocumento().concat(" ").concat(clienteDTO.getNumeroDocumento()));
            ps.setString(2, clienteDTO.getNombre());
            ps.setString(3, clienteDTO.getDireccion());
            ps.setString(4, clienteDTO.getTelefono());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
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
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE clientes set nombre =?, direccion =?, telefono =? WHERE id_cliente = '" + clienteDTO.getId() + "'");
            ps.setString(1, clienteDTO.getNombre());
            ps.setString(2, clienteDTO.getDireccion());
            ps.setString(3, clienteDTO.getTelefono());

            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
    }

    /**
     * Verifica la existencia de un usuario en la base de datos utilizando el
     * correo electrónico proporcionado.
     *
     * @param usuario El correo electrónico del usuario a verificar.
     * @return El correo electrónico del usuario si se encuentra en la base de
     * datos, o una cadena vacía si no se encuentra.
     * @throws Exception Si ocurre algún error durante la operación de
     * verificación.
     */
    public static String verificarUsuario(String usuario) throws Exception {
        String existe = "";
        Connection con = Conexion.getConexion();
        String SQL = "SELECT email FROM usuario WHERE email = '" + usuario + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getString(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
        return existe;
    }

    /**
     * Obtiene la contraseña de un usuario en la base de datos utilizando el
     * correo electrónico proporcionado.
     *
     * @param usuario El correo electrónico del usuario del cual se desea
     * obtener la contraseña.
     * @return La contraseña del usuario si se encuentra en la base de datos, o
     * una cadena vacía si no se encuentra.
     * @throws Exception Si ocurre algún error durante la operación de obtención
     * de la contraseña.
     */
    public static String verificaPassword(String usuario) throws Exception {
        String existe = "";
        Connection con = Conexion.getConexion();
        String SQL = "SELECT password FROM usuario WHERE email = '" + usuario + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getString(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
        return existe;
    }

    /**
     * Busca un usuario en la base de datos utilizando el correo electrónico
     * proporcionado.
     *
     * @param usuario El correo electrónico del usuario a buscar.
     * @return El correo electrónico del usuario si se encuentra en la base de
     * datos, o una cadena vacía si no se encuentra.
     * @throws Exception Si ocurre algún error durante la operación de búsqueda.
     */
    public static String listarUsuario(String usuario) throws Exception {
        String existe = "";
        Connection con = Conexion.getConexion();
        String SQL = "SELECT email FROM usuario WHERE email = '" + usuario + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                existe = rs.getString(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
        return existe;
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
    public List<ClienteDTO> obtenerListaUsuario() throws Exception {
        List<ClienteDTO> listaUsuarioSelecionado = new ArrayList<>();
        Connection con = Conexion.getConexion();
        String SQL = "SELECT *FROM clientes";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setId(rs.getInt(1));
                c.setDescripcionComoboBox(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setTelefono(rs.getString(5));
                c.setNumeroDocumento(rs.getString(2));
                c.setTipoDocumento(rs.getString(2));
                listaUsuarioSelecionado.add(c);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
        return listaUsuarioSelecionado;
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
        Connection con = Conexion.getConexion();
        String SQL = "DELETE FROM clientes WHERE id_cliente = ?";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setInt(1, id);
        ps.executeUpdate();

    }

    /**
     * Elimina un usuario del sistema utilizando su identificador.
     *
     * @param id El identificador del usuario a eliminar.
     * @throws Exception Si ocurre algún error durante la operación de
     * eliminación.
     */
    @Override
    public int validarExisteUsuario(String cc) throws Exception {
        Connection con = Conexion.getConexion();
        String SQL = "SELECT COUNT(*) AS count FROM clientes WHERE cc = ?";
        PreparedStatement ps = con.prepareStatement(SQL);
        ps.setString(1, cc);
        ResultSet rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        return count;

    }
}
