/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import java.util.Objects;

/**
 * @author user
 */
public class ComboBoxItem {

    private String valor;
    private String etiqueta;

    public ComboBoxItem() {

    }

    public ComboBoxItem(String valor, String etiqueta) {
        this.valor = valor;
        this.etiqueta = etiqueta;
    }

    /**
     * @return the value
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the value to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the label
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the label to set
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * Retorna la etiqueta del combobox
     *
     * @return
     */
    @Override
    public String toString() {
        return getEtiqueta();
    }

    /**
     * Método para retornar el valor de la etiqueta en int, utilizado para el sort de tarifas model
     *
     * @return
     */
    public int toInt() {
        return Integer.parseInt(getEtiqueta());
    }

    /**
     * Metodo equals para la comparacion de objetos
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComboBoxItem other = (ComboBoxItem) obj;
        if (!Objects.equals(this.valor, other.valor)) {
            return false;
        }
        if (!Objects.equals(this.etiqueta, other.etiqueta)) {
            return false;
        }
        return true;
    }

    /**
     * metodo hashCode
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.valor);
        hash = 89 * hash + Objects.hashCode(this.etiqueta);
        return hash;
    }

}
