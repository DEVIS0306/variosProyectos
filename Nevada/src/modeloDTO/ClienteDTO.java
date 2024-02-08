/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

/**
 * Representa un objeto ClienteDTO que contiene información relacionada a un
 * cliente.
 */
public class ClienteDTO {

    private int id;
    private String descripcionComoboBox;
    private String tipoDocumento;
    private String nombre;
    private String direccion;
    private String telefono;
    private String numeroDocumento;

    /**
     * Constructor de la clase ClienteDTO que acepta la descripción del combo
     * box y el tipo de documento.
     *
     * @param descripcionComoboBox La descripción del combo box asociada al
     * cliente.
     * @param tipoDocumento El tipo de documento asociado al cliente.
     */
    public ClienteDTO(String descripcionComoboBox, String tipoDocumento) {
        this.descripcionComoboBox = descripcionComoboBox;
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Constructor por defecto de la clase ClienteDTO.
     */
    public ClienteDTO() {
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id El ID del cliente.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la descripción del combo box asociada al cliente.
     *
     * @return La descripción del combo box asociada al cliente.
     */
    public String getDescripcionComoboBox() {
        return descripcionComoboBox;
    }

    /**
     * Establece la descripción del combo box asociada al cliente.
     *
     * @param descripcionComoboBox La descripción del combo box asociada al
     * cliente.
     */
    public void setDescripcionComoboBox(String descripcionComoboBox) {
        this.descripcionComoboBox = descripcionComoboBox;
    }

    /**
     * Obtiene el tipo de documento asociado al cliente.
     *
     * @return El tipo de documento asociado al cliente.
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento asociado al cliente.
     *
     * @param tipoDocumento El tipo de documento asociado al cliente.
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre El nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del cliente.
     *
     * @return La dirección del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del cliente.
     *
     * @param direccion La dirección del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el teléfono del cliente.
     *
     * @return El teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     *
     * @param telefono El teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el numero documento del cliente.
     *
     * @return El numero documento del cliente.
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Establece el numero documento del cliente.
     *
     * @param numeroDocumento El numero documento del cliente.
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

}
