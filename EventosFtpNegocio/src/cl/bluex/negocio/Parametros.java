package cl.bluex.negocio;

import java.util.HashMap;
import java.util.List;

import cl.bluex.datos.Parametro;
import cl.bluex.entidades.ParametrosGenerales;
import cl.bluex.entidades.Pgen;

// TODO: Auto-generated Javadoc
/**
 * The Class Parametros.
 */
public class Parametros {

    /** The pgen. */
    private Pgen pgen;

    /** The int aplicacion. */
    private int intAplicacion;

	/**
	 * Instantiates a new parametros.
	 */
    public Parametros(){
        super();
    }
    
    /**
	 * Instantiates a new parametros.
	 * 
	 * @param empresa
	 *            the empresa
	 * @param intAplicacion
	 *            the int aplicacion
	 */
    public Parametros(final Long empresa, final int intAplicacion) {
        if (pgen == null)
            pgen = new Pgen("FTP%", intAplicacion, empresa);
    }

    /**
     * Busca parametros.
     * @return the hash map
     */
//    public HashMap<String, Object> buscaParametros() {
    public List<ParametrosGenerales>  buscaParametros() {
        return new Parametro(pgen).buscaParametros();
    }

    /**
     * Gets the pgen.
     * @return the pgen
     */
    public Pgen getPgen() {
        return pgen;
    }

    /**
     * Sets the pgen.
     * @param pgen the new pgen
     */
    public void setPgen(final Pgen pgen) {
        this.pgen = pgen;
    }

    /**
     * Gets the int aplicacion.
     * @return the int aplicacion
     */
    public int getIntAplicacion() {
        return intAplicacion;
    }

    /**
     * Sets the int aplicacion.
     * @param intAplicacion the new int aplicacion
     */
    public void setIntAplicacion(final int intAplicacion) {
        this.intAplicacion = intAplicacion;
    }

}
