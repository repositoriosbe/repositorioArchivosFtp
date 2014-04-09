/*
 * 
 */
package cl.bluex.datos;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import cl.bluex.entidades.Configuracion;
import cl.bluex.entidades.Oxml;
import cl.bluex.entidades.ParametrosGenerales;

/**
 * The Class AccionesBDImpl.
 */
public class AccionesBDImpl implements Acciones {

    /** The Constant LOGGER. */
    static final Logger LOGGER = Logger.getLogger(AccionesBDImpl.class);

    /** The _user name. */
    private String _userName = Configuracion.getInstance().getProperty(Configuracion.DATABASE_USER);;

    /** The _password. */
    private String _password = Configuracion.getInstance().getProperty(Configuracion.DATABASE_PSWD);;

    /** The _connection string. */
    private String _connectionString = Configuracion.getInstance().getProperty(Configuracion.CONNECTION_URL);;

    /** The connection. */
    private Connection connection;

    /** The inserta oxml. */
    private final String INSERTA_OXML = "{ ? = call PKG_GENERAL2.INSERTA_OXML(?,?,?,?,?,?,?,?,?,?,?) }";
    
    /** The consulta pgen. */
    private final String CONSULTA_PGEN = "{ call PKG_GENERAL2.GET_PARAMETROS_PDT_FTP(?) }";
    
    
    /**
     * Instantiates a new acciones bd impl.
     */
    public AccionesBDImpl() {
//         conectar();
    }

    /**
     * Instantiates a new acciones bd impl.
     * @param _userName the _user name
     * @param _password the _password
     */
    public AccionesBDImpl(final String _userName, final String _password) {
        this();
        this._userName = _userName;
        this._password = _password;
    }

    /**
     * Instantiates a new acciones bd impl.
     * @param _userName the _user name
     * @param _password the _password
     * @param _connectionString the _connection string
     */
    public AccionesBDImpl(final String _userName, final String _password, final String _connectionString) {
        this();
        this._userName = _userName;
        this._password = _password;
        this._connectionString = _connectionString;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cl.imagemaker.negocio.Acciones#conectar()
     */
    @Override
    public boolean conectar() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(Configuracion.getInstance().getProperty(Configuracion.DATABASE_DRIVER));
                connection = DriverManager.getConnection(getConnectionString(), getUserName(), getPassword());
            }
            return true;
        } catch (final ClassNotFoundException e) {
            LOGGER.error("[conectar] Clase no encontrada" + e.getMessage());
        } catch (final SQLException e) {
            LOGGER.error("[conectar] Error SQL : " + e.getMessage());
        } catch (final Exception e) {
            LOGGER.error("[conectar] Error No controlado: " + e.getMessage());
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cl.imagemaker.negocio.Acciones#desconectar()
     */
    @Override
    public boolean desconectar() {
        try {
            connection.close();
            return connection.isClosed();
        } catch (final SQLException e) {
            LOGGER.error("[desconectar] Error SQL: " + e.getMessage());
        } catch (final Exception e) {
            LOGGER.error("[desconectar] Error No controlado: " + e.getMessage());
        }
        return false;
    }

    /**
     * Ejecutar sp.
     * @param empresa the empresa
     * @return the result set
     */
    public ResultSet buscaParametros(final String empresa, final int intTipoAplicacion) {
        try {
            if (!connection.isClosed()) {
                CallableStatement cs = null;
                cs = this.connection.prepareCall("SELECT * " + " FROM pgen a" + " WHERE a.empr_cdg = " + empresa
                        + " AND a.pgen_nmr LIKE 'FTP%'" + " AND a.aplc_cdg = " + intTipoAplicacion);
                return cs.executeQuery();
            }
        } catch (final SQLException e) {
            LOGGER.error("[buscaParametros] Error SQL: " + e.getMessage());
        } catch (final Exception e) {
            LOGGER.error("[buscaParametros] Error No controlado: " + e.getMessage());
        } 
        return null;
    }

    
    public ArrayList<ParametrosGenerales> buscaParametrosGenerales() {
        try {
            if (!connection.isClosed()) {
            	
            	ResultSet rs = null;
                CallableStatement cst = null;
                ArrayList<ParametrosGenerales> arrPgen = new ArrayList<ParametrosGenerales>();
                
                ParametrosGenerales pgen = null;
                
                // Llamada al procedimiento almacenado
                cst = connection.prepareCall(CONSULTA_PGEN);
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
                while (rs.next()) {                  
                	  pgen = new ParametrosGenerales();
                	  pgen.setEmpresa(rs.getString(1));
                	  pgen.setIpServidor(rs.getString(2));
                	  pgen.setUsuario(rs.getString(3));
                	  pgen.setClave(rs.getString(4));
                	  pgen.setDirErrorLocal(rs.getString(5));
                	  pgen.setDirDescargas(rs.getString(6));
                	  pgen.setDirBackupLocal(rs.getString(7));
                	  pgen.setDirProcLocal(rs.getString(8));
                	  pgen.setDirBackupFtp(rs.getString(9));
                	  pgen.setDirErrorFtp(rs.getString(10));
                	  
                	  arrPgen.add(pgen);
                }
                
               return arrPgen;
                
            }
        } catch (final SQLException e) {
            LOGGER.error("[buscaParametros] Error SQL: " + e.getMessage());
        } catch (final Exception e) {
            LOGGER.error("[buscaParametros] Error No controlado: " + e.getMessage());
        } 
        return null;
    }
    
    /**
     * Ejecutar sp.
     * @param empresa the empresa
     * @return the result set
     */
    public boolean setEjecucion(final boolean ejecuta) {
        
        boolean respuesta=false;
        try {
            if (!connection.isClosed()) {
                CallableStatement cs = null;
                cs = this.connection.prepareCall("UPDATE....");
                cs.executeQuery();
                connection.setAutoCommit(true);
                connection.commit();
                respuesta = true;
            }
        } catch (final SQLException e) {
            LOGGER.error("[setEjecucion] Error SQL: " + e.getMessage());
        } catch (final Exception e) {
            LOGGER.error("[setEjecucion] Error No controlado: " + e.getMessage());
        } 
        return respuesta;
    }    
    
    
    /**
     * Insertar.
     * @param oxml the oxml
     * @return true, if successful
     */
    public boolean insertarOxml(final Oxml oxml) {
        boolean vFlag = false;
        try {
            if (connection == null)
                this.conectar();

            if (!connection.isClosed()) {
                if (connection.getAutoCommit())
                    connection.setAutoCommit(false);
                int intContador = 0;
                final CallableStatement preparedStatement = connection.prepareCall(INSERTA_OXML);
                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
                preparedStatement.setLong(++intContador, oxml.getOxmloperacion());
                preparedStatement.setLong(++intContador, oxml.getOxmlid_sesion());
                preparedStatement.setString(++intContador, "NUEVO");
                preparedStatement.setString(++intContador, oxml.getOxmlprso_cdg_crea());
                preparedStatement.setString(++intContador, oxml.getOxmlprso_cdg_edita());
                preparedStatement.setLong(++intContador, oxml.getOxmlprioridad());
                preparedStatement.setString(++intContador, oxml.getPstacdg());
                preparedStatement.setLong(++intContador, oxml.getTpngcdg());
                preparedStatement.setString(++intContador, oxml.getOfcncdg());
                preparedStatement.setString(++intContador, oxml.getOxmlcourier());
                preparedStatement.setCharacterStream(++intContador, new StringReader(oxml.getOxmlxml()), oxml
                        .getOxmlxml().length());
                preparedStatement.executeUpdate();
                if (preparedStatement.getLong(1) == 1) {
                	vFlag = true;
                }
                connection.setAutoCommit(true);
                connection.commit();
            }
        } catch (final SQLException e) {
            LOGGER.error("Error SQL: " + e.getMessage());
            vFlag = false;
            rollback();
        } catch (final Exception e) {
            LOGGER.error(e.getStackTrace());
            LOGGER.error("Error No controlado: " + e.getMessage());
            vFlag = false;
            rollback();
        }
        return !vFlag;
    }

    
    
    /**
     * Rollback.
     */
    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("[rollback] Error SQL: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("[rollback] Error No controlado: " + e.getMessage());
        }
    }

    /**
     * Gets the user name.
     * @return the user name
     */
    public String getUserName() {
        return _userName;
    }

    /**
     * Sets the user name.
     * @param _userName the new user name
     */
    public void setUserName(final String _userName) {
        this._userName = _userName;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Sets the sassword.
     * @param _password the new sassword
     */
    public void setSassword(final String _password) {
        this._password = _password;
    }

    /**
     * Gets the _connection string.
     * @return the _connection string
     */
    public String getConnectionString() {
        return _connectionString;
    }

    /**
     * Sets the _connection string.
     * @param _connectionString the new _connection string
     */
    public void setConnectionString(final String _connectionString) {
        this._connectionString = _connectionString;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
