package cl.bluex.entidades.eventos;

/**
 * The Class HeadWebServicesVO.
 */
public class HeadWebServicesVO {

    /** The fecha h. */
    protected String fechaH;

    /** The id sesion. */
    protected Long idSesion;

    /** The ip pdt. */
    protected String ipPDT;

    /** The no serie pdt. */
    protected String noSeriePdt;

    // elementos del HEAD (generico)
    /** The operacion. */
    protected int operacion;

    /** The posta. */
    protected String posta;

    /** The prioridad msj. */
    protected String prioridadMsj;

    /** The ruta. */
    protected String ruta;

    /** The tipo msje. */
    protected int tipoMsje;

    /**
     * Instantiates a new head web services vo.
     */
    public HeadWebServicesVO() {

    }

    /**
     * Instantiates a new head web services vo.
     * @param operacion the operacion
     * @param idSesion2 the id sesion2
     * @param noSeriePdt the no serie pdt
     * @param ipPDT the ip pdt
     * @param tipoMsje the tipo msje
     * @param prioridadMsj the prioridad msj
     * @param fechaH the fecha h
     * @param posta the posta
     * @param ruta the ruta
     */
    public HeadWebServicesVO(final int operacion, final Long idSesion2, final String noSeriePdt, final String ipPDT,
            final int tipoMsje, final String prioridadMsj, final String fechaH, final String posta, final String ruta) {
        super();
        this.operacion = operacion;
        this.idSesion = idSesion2;
        this.noSeriePdt = noSeriePdt;
        this.ipPDT = ipPDT;
        this.tipoMsje = tipoMsje;
        this.prioridadMsj = prioridadMsj;
        this.fechaH = fechaH;
        this.posta = posta;
        this.ruta = ruta;
    }

    /**
     * Gets the fecha h.
     * @return the fechaH
     */
    public final String getFechaH() {
        return this.fechaH;
    }

    /**
     * Gets the id sesion.
     * @return the idSesion
     */
    public final Long getIdSesion() {
        return this.idSesion;
    }

    /**
     * Gets the ip pdt.
     * @return the ipPDT
     */
    public final String getIpPDT() {
        return this.ipPDT;
    }

    /**
     * Gets the no serie pdt.
     * @return the noSeriePdt
     */
    public final String getNoSeriePdt() {
        return this.noSeriePdt;
    }

    /**
     * Gets the operacion.
     * @return the operacion
     */
    public int getOperacion() {
        return this.operacion;
    }

    /**
     * Gets the posta.
     * @return the posta
     */
    public String getPosta() {
        return this.posta;
    }

    /**
     * Gets the prioridad msj.
     * @return the prioridadMsj
     */
    public final String getPrioridadMsj() {
        return this.prioridadMsj;
    }

    /**
     * Gets the ruta.
     * @return the ruta
     */
    public final String getRuta() {
        return this.ruta;
    }

    /**
     * Gets the tipo msje.
     * @return the tipoMsje
     */
    public final int getTipoMsje() {
        return this.tipoMsje;
    }

    /**
     * Sets the fecha h.
     * @param fechaH the fechaH to set
     */
    public final void setFechaH(final String fechaH) {
        this.fechaH = fechaH;
    }

    /**
     * Sets the id sesion.
     * @param idSesion the idSesion to set
     */
    public final void setIdSesion(final Long idSesion) {
        this.idSesion = idSesion;
    }

    /**
     * Sets the ip pdt.
     * @param ipPDT the ipPDT to set
     */
    public final void setIpPDT(final String ipPDT) {
        this.ipPDT = ipPDT;
    }

    /**
     * Sets the no serie pdt.
     * @param noSeriePdt the noSeriePdt to set
     */
    public final void setNoSeriePdt(final String noSeriePdt) {
        this.noSeriePdt = noSeriePdt;
    }

    /**
     * Sets the operacion.
     * @param operacion the operacion to set
     */
    public void setOperacion(final int operacion) {
        this.operacion = operacion;
    }

    /**
     * Sets the posta.
     * @param posta the posta to set
     */
    public void setPosta(final String posta) {
        this.posta = posta;
    }

    /**
     * Sets the prioridad msj.
     * @param prioridadMsj the prioridadMsj to set
     */
    public final void setPrioridadMsj(final String prioridadMsj) {
        this.prioridadMsj = prioridadMsj;
    }

    /**
     * Sets the ruta.
     * @param ruta the ruta to set
     */
    public final void setRuta(final String ruta) {
        this.ruta = ruta;
    }

    /**
     * Sets the tipo msje.
     * @param tipoMsje the tipoMsje to set
     */
    public final void setTipoMsje(final int tipoMsje) {
        this.tipoMsje = tipoMsje;
    }
}
