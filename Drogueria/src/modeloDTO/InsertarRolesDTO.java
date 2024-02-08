/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

/**
 * Representa un objeto InsertarRolesDTO que contiene información relacionada a
 * un rol.
 */
public class InsertarRolesDTO {

    private int idRol;
    private String nombre;
    private String descripcion;
    private int estado;

    /**
     * Constructor de la clase InsertarRolesDTO que acepta el nombre, la
     * descripción y el estado del rol.
     *
     * @param nombre El nombre del rol.
     * @param descripcion La descripción del rol.
     * @param estado El estado del rol.
     */
    public InsertarRolesDTO(String nombre, String descripcion, int estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     * Constructor por defecto de la clase InsertarRolesDTO.
     */
    public InsertarRolesDTO() {
    }

    /**
     * Obtiene el ID del rol.
     *
     * @return El ID del rol.
     */
    public int getIdRol() {
        return idRol;
    }

    /**
     * Establece el ID del rol.
     *
     * @param idRol El ID del rol.
     */
    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    /**
     * Obtiene el nombre del rol.
     *
     * @return El nombre del rol.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del rol.
     *
     * @param nombre El nombre del rol.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del rol.
     *
     * @return La descripción del rol.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del rol.
     *
     * @param descripcion La descripción del rol.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el estado del rol.
     *
     * @return El estado del rol.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Establece el estado del rol.
     *
     * @param estado El estado del rol.
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

}
