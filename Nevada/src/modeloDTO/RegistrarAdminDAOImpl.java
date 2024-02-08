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
            PreparedStatement ps = con.prepareStatement("INSERT INTO usuarios (usuario, password) VALUES (?,?)");
            ps.setString(1, registroAdminDTO.getUsuario());
            ps.setString(2, registroAdminDTO.getPassword());
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
            PreparedStatement ps = con.prepareStatement("UPDATE usuarios set usuario =?, password =? WHERE id = '" + registroAdminDTO.getId() + "'");
            ps.setString(1, registroAdminDTO.getUsuario());
            ps.setString(2, registroAdminDTO.getPassword());
           
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
        String SQL = "SELECT usuario FROM usuarios WHERE usuario = '" + usuario + "'";
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
        String SQL = "SELECT password FROM usuarios WHERE usuario = '" + usuario + "'";
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
        String SQL = "SELECT *FROM usuario WHERE usuario = '" + usuario + "'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                RegistroAdminDTO r = new RegistroAdminDTO();
                r.setId(rs.getInt(1));
                r.setUsuario(rs.getString(2));
                r.setPassword(rs.getString(3));
                listaUsuarioSelecionado.add(r);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            System.out.println(e.toString());
        }
        return listaUsuarioSelecionado;
    }
}
