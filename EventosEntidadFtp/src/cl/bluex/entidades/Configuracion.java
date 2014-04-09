/*
 * 
 */
package cl.bluex.entidades;

import java.io.IOException;
import java.util.Properties;

/**
 * The Class Configuracion.
 */
public class Configuracion {

    /** The properties. */
    Properties properties = null;

    /** Configuration file name. */
    public final static String CONFIG_FILE_NAME = "db.properties";

    /** Data base name. */
    public final static String CONNECTION_URL = "connectionUrl";

    /** Data base user. */
    public final static String DATABASE_USER = "dbuserName";

    /** Data base password. */
    public final static String DATABASE_PSWD = "dbPassword";

    /** The Constant DATABASE_DRIVER. */
    public final static String DATABASE_DRIVER = "driverDB";
    
    /**
     * Instantiates a new configuracion.
     */
    public Configuracion() {
        this.properties = new Properties();
        try {
            properties.load(Configuracion.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
            
        } catch (final IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Gets the single instance of Configuracion.
     * @return single instance of Configuracion
     */
    public static Configuracion getInstance() {
        return ConfigurationHolder.INSTANCE;
    }

    /**
     * The Class ConfigurationHolder.
     */
    private static class ConfigurationHolder {

        /** The Constant INSTANCE. */
        private static final Configuracion INSTANCE = new Configuracion();
    }

    /**
     * Gets the property.
     * @param key the key
     * @return the property
     */
    public String getProperty(final String key) {
        return this.properties.getProperty(key);
    }// getProperty

}
