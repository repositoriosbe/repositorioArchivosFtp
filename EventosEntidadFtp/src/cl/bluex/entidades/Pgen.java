package cl.bluex.entidades;

// TODO: Auto-generated Javadoc
/**
 * The Class Pgen.
 */
public class Pgen {

    /** The str nombre parametro. */
    private String strNombreParametro;

    /** The empresa. */
    private Long empresa;

    /** The pgen nmr. */
    private String pgenNmr;

    /** The pgen valor1. */
    private String pgenValor1;

    /** The int tipo aplicacion. */
    private int intTipoAplicacion;

    /**
     * Instantiates a new pgen.
     */
    public Pgen() {

    }

    /**
     * Instantiates a new pgen.
     * @param empresa the empresa
     */
    public Pgen(Long empresa) {
        super();
        this.empresa = empresa;
    }

    /**
	 * Instantiates a new pgen.
	 * 
	 * @param strNombreParametro
	 *            the str nombre parametro
	 * @param intTipoAplicacion
	 *            the int tipo aplicacion
	 * @param empresa
	 *            the empresa
	 */
    public Pgen(String strNombreParametro, int intTipoAplicacion, Long empresa) {
        super();
        this.strNombreParametro = strNombreParametro;
        this.empresa = empresa;
        this.intTipoAplicacion = intTipoAplicacion;
    }

    /**
     * Gets the str nombre parametro.
     * @return the str nombre parametro
     */
    public String getStrNombreParametro() {
        return strNombreParametro;
    }

    /**
     * Sets the str nombre parametro.
     * @param strNombreParametro the new str nombre parametro
     */
    public void setStrNombreParametro(String strNombreParametro) {
        this.strNombreParametro = strNombreParametro;
    }

    /**
     * Gets the empresa.
     * @return the empresa
     */
    public Long getEmpresa() {
        return empresa;
    }

    /**
     * Sets the empresa.
     * @param empresa the new empresa
     */
    public void setEmpresa(Long empresa) {
        this.empresa = empresa;
    }

    /**
     * Gets the pgen nmr.
     * @return the pgen nmr
     */
    public String getPgenNmr() {
        return pgenNmr;
    }

    /**
     * Sets the pgen nmr.
     * @param pgenNmr the new pgen nmr
     */
    public void setPgenNmr(String pgenNmr) {
        this.pgenNmr = pgenNmr;
    }

    /**
     * Gets the pgen valor1.
     * @return the pgen valor1
     */
    public String getPgenValor1() {
        return pgenValor1;
    }

    /**
     * Sets the pgen valor1.
     * @param pgenValor1 the new pgen valor1
     */
    public void setPgenValor1(String pgenValor1) {
        this.pgenValor1 = pgenValor1;
    }

    /**
     * Gets the int tipo aplicacion.
     * @return the int tipo aplicacion
     */
    public int getIntTipoAplicacion() {
        return intTipoAplicacion;
    }

    /**
     * Sets the int tipo aplicacion.
     * @param intTipoAplicacion the new int tipo aplicacion
     */
    public void setIntTipoAplicacion(int intTipoAplicacion) {
        this.intTipoAplicacion = intTipoAplicacion;
    }

}
