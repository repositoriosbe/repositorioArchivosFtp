package cl.bluex.negocio;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

import cl.bluex.entidades.Constantes;

// TODO: Auto-generated Javadoc
/**
 * The Class Utilidad.
 */
public class Utilidad {

    /** The Constant LOGGER. */
    static final Logger LOGGER = Logger.getLogger(Utilidad.class);

    /**
     * Validate date.
     * @param date the date
     * @return true, if successful
     */
    public boolean validateDate(String date) {
        date = this.getSoloNum(date); // Quitar posibles caracteres no munericos
        boolean resultado = false;
        if (date.length() == 14) {
            final String dateAnio = date.substring(0, 4);
            final String dateMes = date.substring(4, 6);
            final String dateDia = date.substring(6, 8);
            final String dateString = dateDia + "/" + dateMes + "/" + dateAnio;
            final String dateHoras = date.substring(8);

            final String DatePattern = "^(?:(31)(\\D)(0?[13578]|1[02])\\2|(29|30)"
                    + "(\\D)(0?[13-9]|1[0-2])\\5|(0?[1-9]|1\\d|2[0-8])(\\D)"
                    + "(0?[1-9]|1[0-2])\\8)((?:1[6-9]|[2-9]\\d)?\\d{2})$|^(29)"
                    + "(\\D)(0?2)\\12((?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]"
                    + "|[13579][26])|(?:16|[2468][048]|[3579][26])00)$";
            if (dateString.matches(DatePattern)) {
                resultado = true;
            } else {
                return false;
            }

            if (Integer.valueOf(dateHoras.substring(0, 2)).intValue() < 24
                    && Integer.valueOf(dateHoras.substring(0, 2)).intValue() >= 0) {
                resultado = true;
            } else {
                return false;
            }
            if (Integer.valueOf(dateHoras.substring(2, 4)).intValue() < 60
                    && Integer.valueOf(dateHoras.substring(2, 4)).intValue() >= 0) {
                resultado = true;
            } else {
                return false;
            }
            if (Integer.valueOf(dateHoras.substring(4, 6)).intValue() < 60
                    && Integer.valueOf(dateHoras.substring(4, 6)).intValue() >= 0) {
                resultado = true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        return resultado;
    }

    /**
     * Gets the properties.
     * @param sProperty the s property
     * @param key the key
     * @return the properties
     */
    public String getProperties(final String sProperty, final String key) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle(sProperty);

        try {
            return resourceBundle.getString(key);

        } catch (final MissingResourceException e) {
            LOGGER.error(Constantes.VERSION + " Error : " + e.getMessage());
            return null;
        }
    }

    /**
     * Gets the solo num.
     * @param strChars the str chars
     * @return the solo num
     */
    public final String getSoloNum(final String strChars) {

        String strNums = Constantes.VACIO;

        try {
            // limpieza de caracteres especiales
            final Pattern patron = Pattern.compile("[^0-9]+");
            final Matcher matcher = patron.matcher(strChars);
            // Obtiene sï¿½lo numeros
            strNums = matcher.replaceAll(Constantes.VACIO);
        } catch (final PatternSyntaxException e) {
            LOGGER.error(Constantes.VERSION + " Error: " + e.getMessage());
        }
        return strNums;
    }

    /**
     * Gets the string date formatted.
     * @param date the date
     * @return the string date formatted
     */
    public final String getStringDateFormatted(String date) {
        date = this.getSoloNum(date); // Quitar posibles caracteres no munericos
        final String dateAnio = date.substring(0, 4);
        final String dateMes = date.substring(4, 6);
        final String dateDia = date.substring(6, 8);
        final String dateHoras = date.substring(8);

        final String hora = dateHoras.substring(0, 2);
        final String minutos = dateHoras.substring(2, 4);
        final String segundos = dateHoras.substring(4, 6);

        final String dateString = dateAnio + "-" + dateMes + "-" + dateDia + " " + hora + ":" + minutos + ":"
                + segundos;

        return dateString;

    }
    
    
    /**
     * Gets the fecha hora.
     *
     * @return the fecha hora
     */
    public static String getFechaHora(){
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        return(dia + "-" + (mes+1) + "-" + año + "  " + hora + ":" + minuto + ":" +  segundo);
    }

}
