/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alerta;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author user
 */
public class SenaException extends Exception{

    private static final String RUTA_MENSAJE_EXCEPCION
            = "alerta.SenaException";
    private static final String PREFIJO_MENSAJE_EXCEPCION = "sistema.ventas.";

    private String codigo;

    public SenaException() {
    }

    public SenaException(String mensaje) {
        super(mensaje);
    }

    public SenaException(String codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }

    public static SenaException
            getLocalizedException(String codigo, String mensaje, Locale locales, Object... args) {
        ResourceBundle bundle = ResourceBundle.getBundle(RUTA_MENSAJE_EXCEPCION, locales);
        if (codigo == null || codigo.trim().isEmpty()) {
            codigo = "01001";
        }
        String claveMensaje = PREFIJO_MENSAJE_EXCEPCION.concat(codigo);
        if (!bundle.containsKey(claveMensaje)) {
            codigo = "01001";
            claveMensaje = PREFIJO_MENSAJE_EXCEPCION.concat(codigo);
        }
        Object[] argumentosMensaje = new Object[]{codigo, mensaje};
        if (args != null && args.length > 0) {
            int argumentosTamano = args.length + 2;
            int argumentosIndice = 0;
            argumentosMensaje = new Object[argumentosTamano];
            argumentosMensaje[argumentosIndice] = codigo;
            argumentosIndice++;
            argumentosMensaje[argumentosIndice] = mensaje;
            argumentosIndice++;
            for (Object arg : args) {
                argumentosMensaje[argumentosIndice] = arg;
                argumentosIndice++;
            }
        }
        mensaje = MessageFormat.format(
                bundle.getString(claveMensaje),
                argumentosMensaje
        );
        return new SenaException(codigo, mensaje);
    }
}
