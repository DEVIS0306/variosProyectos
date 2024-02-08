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
public class RegistrarAdminDAOImpl implements RegistrarAdminDAO {

    static ResultSet res;

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
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO usuario (idrol, nombre, apellido, tipo_documento, num_documento, direccion, telefono, email, estado, password) VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, registroAdminDTO.getIdRol());
            ps.setString(2, registroAdminDTO.getNombre());
            ps.setString(3, registroAdminDTO.getApellido());
            ps.setString(4, registroAdminDTO.getTipoDocumento());
            ps.setString(5, registroAdminDTO.getNumeroDocumento());
            ps.setString(6, registroAdminDTO.getDireccion());
            ps.setString(7, registroAdminDTO.getTelefono());
            ps.setString(8, registroAdminDTO.getCorreo());
            ps.setInt(9, registroAdminDTO.getEstado());
            ps.setString(10, registroAdminDTO.getContrasenia());
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
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
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE usuario set idrol =?, nombre =?, tipo_documento =?, num_documento =?, direccion =?, telefono =?, email =?, estado =?, apellido =?, password =? WHERE idusuario = '" + registroAdminDTO.getId() + "'");
            ps.setInt(1, registroAdminDTO.getIdRol());
            ps.setString(2, registroAdminDTO.getNombre());
            ps.setString(3, registroAdminDTO.getApellido());
            ps.setString(4, registroAdminDTO.getTipoDocumento());
            ps.setString(5, registroAdminDTO.getNumeroDocumento());
            ps.setString(6, registroAdminDTO.getDireccion());
            ps.setString(7, registroAdminDTO.getTelefono());
            ps.setString(8, registroAdminDTO.getCorreo());
            ps.setInt(9, registroAdminDTO.getEstado());
            ps.setString(10, registroAdminDTO.getContrasenia());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
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
    public void insertarRoles(ArrayList<InsertarRolesDTO> administrador) throws Exception {
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO rol (nombre, descripcion, estado) VALUES (?,?,?)");

            for (int i = 0; i < administrador.size(); i++) {
                ps.setString(1, administrador.get(i).getNombre());
                ps.setString(2, administrador.get(i).getDescripcion());
                ps.setInt(3, administrador.get(i).getEstado());
                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Registro guardado rol");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
    }

    /**
     * Valida la existencia de un usuario.
     *
     * @return Un entero que representa la existencia del usuario. 1 si existe,
     * 0 si no existe.
     */
    @Override
    public int validarExisteUsuario() throws Exception {
        /* int respuesta = 0;
        Connection con = Conexion.getConexion();
        res = SQL.Conexion.Consulta"SELECT * FROM rol");
        while (res.next()) {
            respuesta = respuesta + 1;
        }
        return respuesta;*/
        return 1;

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
     * Obtiene una lista de usuarios que coinciden con el nombre de usuario
     * especificado.
     *
     * @param usuario El nombre de usuario a buscar.
     * @return Una lista de objetos RegistroAdminDTO que representan los
     * usuarios encontrados.
     * @throws Exception Si ocurre algún error durante la búsqueda.
     */
    @Override
    public List<RegistroAdminDTO> obtenerListaUsuario(String usuario) throws Exception {
        List<RegistroAdminDTO> listaUsuarioSelecionado = new ArrayList<>();
        Connection con = Conexion.getConexion();
        String SQL = "SELECT *FROM usuario WHERE email = '" + usuario + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                RegistroAdminDTO r = new RegistroAdminDTO();
                r.setId(rs.getInt(1));
                r.setIdRol(rs.getInt(2));
                r.setNombre(rs.getString(3));
                r.setApellido(rs.getString(4));
                r.setTipoDocumento(rs.getString(5));
                r.setNumeroDocumento(rs.getString(6));
                r.setDireccion(rs.getString(7));
                r.setTelefono(rs.getString(8));
                r.setCorreo(rs.getString(9));
                r.setEstado(rs.getInt(10));

                r.setContrasenia(rs.getString(11));
                listaUsuarioSelecionado.add(r);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
        return listaUsuarioSelecionado;
    }
}
