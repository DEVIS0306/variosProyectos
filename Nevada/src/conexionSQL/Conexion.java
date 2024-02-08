/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para gestionar la conexión a la base de datos.
 *
 * Esta clase proporciona métodos para establecer una conexión con una base de
 * datos MySQL y obtener una instancia de conexión que se puede utilizar para
 * interactuar con la base de datos.
 */
public class Conexion {

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return La conexión establecida con la base de datos.
     * @throws SQLException si ocurre un error al establecer la conexión.
     * @throws ClassNotFoundException si no se encuentra el controlador JDBC
     * para MySQL.
     */
    public static Connection getConexion() throws SQLException, ClassNotFoundException {
        // Configuración de la URL, usuario y contraseña de la base de datos
        String url = "jdbc:mysql://localhost:3312/nevada";
        String user = "root";
        String password = "";
        // Carga el controlador JDBC de MySQL
        Class.forName("com.mysql.jdbc.Driver");
        try {
            // Establece la conexión con la base de datos
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a la base de datos: " + url);
            System.out.println("Usuario conectado: " + user);
            return con;
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de datos: " + e.getMessage());
            return null;
        }

    }

    //CONEXION workbench
    /*
           import java.sql.Connection;
           import java.sql.DriverManager;
           import java.sql.SQLException;
    
    
    
    String url = "jdbc:mysql://localhost:3306/nombre_base_de_datos";
    String username = "nombre_usuario";
    String password = "contraseña";

    try {
    Connection connection = DriverManager.getConnection(url, username, password);
  
    connection.close();
} catch (SQLException e) {
    e.printStackTrace();
}

     */
}
