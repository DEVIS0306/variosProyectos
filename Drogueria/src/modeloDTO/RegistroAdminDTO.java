/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDTO;

/**
 * Representa un objeto RegistroAdminDTO que contiene información relacionada a
 * un administrador.
 */
public class RegistroAdminDTO {

    private String descripcionComoboBox;
    private int consecutivoNumero;
    private int id;
    private int idRol;
    private String idRolNombre;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private String correo;
    private String telefono;
    private String direccion;
    private String contrasenia;
    private int estado;
    public Object SelectedItem;

    /**
     * Constructor de la clase RegistroAdminDTO que acepta la descripción del
     * combo box y el tipo de documento.
     *
     * @param descripcionComoboBox La descripción del combo box asociada al
     * administrador.
     * @param tipoDocumento El tipo de documento asociado al administrador.
     */
    public RegistroAdminDTO(String descripcionComoboBox, String tipoDocumento) {
        this.descripcionComoboBox = descripcionComoboBox;
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Constructor por defecto de la clase RegistroAdminDTO.
     */
    public RegistroAdminDTO() {
    }

    /**
     * Obtiene el ID del administrador.
     *
     * @return El ID del administrador.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del administrador.
     *
     * @param id El ID del administrador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del administrador.
     *
     * @return El nombre del administrador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del administrador.
     *
     * @param nombre El nombre del administrador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del administrador.
     *
     * @return El apellido del administrador.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del administrador.
     *
     * @param apellido El apellido del administrador.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el tipo de documento asociado al administrador.
     *
     * @return El tipo de documento asociado al administrador.
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Establece el tipo de documento asociado al administrador.
     *
     * @param tipoDocumento El tipo de documento asociado al administrador.
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Obtiene el número de documento del administrador.
     *
     * @return El número de documento del administrador.
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Establece el número de documento del administrador.
     *
     * @param numeroDocumento El número de documento del administrador.
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * Obtiene el correo del usuario.
     *
     * @return El correo del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del usuario.
     *
     * @param correo El correo del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el teléfono del usuario.
     *
     * @return El teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del usuario.
     *
     * @param telefono El teléfono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección del usuario.
     *
     * @return La dirección del usuario.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del usuario.
     *
     * @param direccion La dirección del usuario.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasenia La contraseña del usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * Obtiene la descripción del combo box.
     *
     * @return La descripción del combo box.
     */
    public String getDescripcionComoboBox() {
        return descripcionComoboBox;
    }

    /**
     * Establece la descripción del combo box.
     *
     * @param descripcionComoboBox La descripción del combo box.
     */
    public void setDescripcionComoboBox(String descripcionComoboBox) {
        this.descripcionComoboBox = descripcionComoboBox;
    }

    /**
     * Obtiene el consecutivo del número.
     *
     * @return El consecutivo del número.
     */
    public int getConsecutivoNumero() {
        return consecutivoNumero;
    }

    /**
     * Establece el consecutivo del número.
     *
     * @param consecutivoNumero El consecutivo del número.
     */
    public void setConsecutivoNumero(int consecutivoNumero) {
        this.consecutivoNumero = consecutivoNumero;
    }

    /**
     * Obtiene el item seleccionado.
     *
     * @return El item seleccionado.
     */
    public Object getSelectedItem() {
        return SelectedItem;
    }

    /**
     * Establece el item seleccionado.
     *
     * @param SelectedItem El item seleccionado.
     */
    public void setSelectedItem(Object SelectedItem) {
        this.SelectedItem = SelectedItem;
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
     * Obtiene el estado.
     *
     * @return El estado.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * Establece el estado.
     *
     * @param estado El estado.
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el nombre del ID del rol.
     *
     * @return El nombre del ID del rol.
     */
    public String getIdRolNombre() {
        return idRolNombre;
    }

    /**
     * Establece el nombre del ID del rol.
     *
     * @param idRolNombre El nombre del ID del rol.
     */
    public void setIdRolNombre(String idRolNombre) {
        this.idRolNombre = idRolNombre;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto.
     *
     * @return La representación en forma de cadena del objeto.
     */
    @Override
    public String toString() {
        return getTipoDocumento();
    }

}
