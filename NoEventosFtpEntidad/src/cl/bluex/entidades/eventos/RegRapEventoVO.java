package cl.bluex.entidades.eventos;

// TODO: Auto-generated Javadoc
/**
 * The Class RegRapEventoVO.
 * @author Iran Vasquez, Federico D. Arias
 */
public class RegRapEventoVO extends HeadWebServicesVO {

    /** The bdga cdg. */
    private String bdgaCdg; // BDGA_CDG;// nvarchar(10)

    /** The bdga cdg destino. */
    private String bdgaCdgDestino;// nvarchar(10)

    /** The bdga cdg destino eb. */
    private String bdgaCdgDestinoEb;// BDGA_CDG_DESTINO_EB;// nvarchar(10)

    /** The bdga cdg eb. */
    private String bdgaCdgEb;// BDGA_CDG_EB;// nvarchar(10)

    /** The bdga cdg ed. */
    private String bdgaCdgEd;// BDGA_CDG_ED;// nvarchar(10)

    /** The bdga cdg md. */
    private String bdgaCdgMd;// BDGA_CDG_MD;// nvarchar(10)

    /** The bdga tpo. */
    private String bdgaTpo;// BDGA_TPO;// nvarchar(2)

    /** The bdga tpo destino. */
    private String bdgaTpoDestino;// nvarchar(2)

    /** The bdga tpo destino eb. */
    private String bdgaTpoDestinoEb;// BDGA_TPO_DESTINO_EB;// nvarchar(2)

    /** The bdga tpo eb. */
    private String bdgaTpoEb;// BDGA_TPO_EB;// nvarchar(2)

    /** The bdga tpo ed. */
    private String bdgaTpoEd;// BDGA_TPO_ED;// nvarchar(2)

    /** The bdga tpo md. */
    private String bdgaTpoMd;// BDGA_TPO_MD;// nvarchar(2)

    /** The desc error. */
    private String descError;

    /** The evbg observacion. */
    private String evbgObservacion;// EVBG_OBSERVACION;// nvarchar(50)

    // EVENTO_BODEGA
    /** The evbg seq. */
    private long evbgSeq;// EVBG_SEQ;// numeric(14)
    // EVENTO_DOCUMENTO
    /** The evdc documento. */
    private long evdcDocumento;// EVDC_DOCUMENTO;// numeric(14)

    /** The evdc observacion. */
    private String evdcObservacion;// EVDC_OBSERVACION;// nvarchar(100)

    /** The evdc peso. */
    private double evdcPeso;// EVDC_PESO;// numeric(5)

    /** The evdc piezas. */
    private int evdcPiezas;// EVDC_PIEZAS;// numeric(5)

    /** The evdc rsva. */
    private int evdcRsva;// EVDC_RSVA;// int

    /** The evdc seq. */
    private long evdcSeq;// EVDC_SEQ;// numeric(14)

    /** The evto cdg. */
    private String evtoCdg;// EVTO_CDG;// nvarchar(3)

    /** The evto cdg eb. */
    private String evtoCdgEb;

    /** The evto cdg ed. */
    private String evtoCdgEd;

    /** The evto swt exc evt. */
    private String evtoSwtExcEvt;// EVTO_SWT_EXC_EVT;// nvarchar(2)

    /** The evto swt exc evt ed. */
    private String evtoSwtExcEvtEd;// EVTO_SWT_EXC_EVT_ED;// nvarchar(2)

    /** The evto swt exc evt md. */
    private String evtoSwtExcEvtMd;// EVTO_SWT_EXC_EVT_MD;// nvarchar(2)

    /** The mvbg observacion. */
    private String mvbgObservacion;// MVBG_OBSERVACION;// nvarchar(50)

    // Datos Registro
    // MOVIMIENTO_BODEGA
    /** The mvbg seq. */
    private long mvbgSeq;// MVBG_SEQ;//numeric(2,2)

    // MOVIMIENTO_DOCUMENTO
    /** The mvdc documento. */
    private long mvdcDocumento;// MVDC_DOCUMENTO;// numeric(14)

    /** The mvdc observacion. */
    private String mvdcObservacion;// MVDC_OBSERVACION;// nvarchar(100)

    /** The mvdc peso. */
    private double mvdcPeso;// MVDC_PESO;// numeric(14,2)

    /** The mvdc piezas. */
    private int mvdcPiezas;// MVDC_PIEZAS;// numeric(5)

    /** The mvdc rsva. */
    private int mvdcRsva;// MVDC_RSVA;// int

    /** The mvdc seq. */
    private long mvdcSeq;// MVDC_SEQ;// numeric(14)

    /** The ofcn cdg. */
    private String ofcnCdg;// OFCN_CDG;// nvarchar(5)

    /** The ofcn cdg destino. */
    private String ofcnCdgDestino;// nvarchar(5)

    /** The ofcn cdg destino eb. */
    private String ofcnCdgDestinoEb;// OFCN_CDG_DESTINO_EB;// nvarchar(5)

    // private String ofcnCdgEb;//OFCN_CDG_EB;// nvarchar(5)
    /** The ofcn cdg eb. */
    private String ofcnCdgEb;

    /** The ofcn cdg ed. */
    private String ofcnCdgEd;// OFCN_CDG_ED;// nvarchar(5)

    /** The ofcn cdg md. */
    private String ofcnCdgMd;// OFCN_CDG_MD;// nvarchar(5)

    /** The operacion. */
    private int operacion;

    /** The prso cdg. */
    private String prsoCdg;// PRSO_CDG;// nvarchar(20)

    /** The prso cdg eb. */
    private String prsoCdgEb;// PRSO_CDG_EB;// nvarchar(20)

    /** The prso cdg ed. */
    private String prsoCdgEd;// PRSO_CDG_ED;// nvarchar(20)

    /** The prso cdg md. */
    private String prsoCdgMd;// PRSO_CDG_MD;// nvarchar(20)

    // Tipo evento
    /** The tpo evt. */
    private int tpoEvt;

    /** The udmd cdg. */
    private String udmdCdg;// UDMD_CDG;// nvarchar(5)

    /** The udmd cdg eb. */
    private String udmdCdgEb;// UDMD_CDG_EB;// nvarchar(5)

    /** The udmd cdg ed. */
    private String udmdCdgEd;// UDMD_CDG_ED;// nvarchar(5)

    /** The udmd cdg md. */
    private String udmdCdgMd;// UDMD_CDG_MD ;//nvarchar(5)

    /** The empresa. */
    private Long empresa;

    /**
     * Instantiates a new reg rap evento vo.
     */
    public RegRapEventoVO() {
        super();

        this.tpoEvt = 1;
    }

    /**
     * Instantiates a new reg rap evento vo.
     * @param operacion the operacion
     * @param idSesion the id sesion
     * @param noSeriePdt the no serie pdt
     * @param ipPDT the ip pdt
     * @param tipoMsje the tipo msje
     * @param prioridadMsj the prioridad msj
     * @param fechaH the fecha h
     * @param base the base
     * @param ruta the ruta
     */
    public RegRapEventoVO(final int operacion, final Long idSesion, final String noSeriePdt, final String ipPDT,
            final int tipoMsje, final String prioridadMsj, final String fechaH, final String base, final String ruta) {
        super(operacion, idSesion, noSeriePdt, ipPDT, tipoMsje, prioridadMsj, fechaH, base, ruta);

    }

    /**
     * Gets the bdga cdg.
     * @return the bdgaCdg
     */
    public final String getBdgaCdg() {
        return this.bdgaCdg;
    }

    /**
     * Gets the bdga cdg destino.
     * @return the bdgaCdgDestino
     */
    public final String getBdgaCdgDestino() {
        return this.bdgaCdgDestino;
    }

    /**
     * Gets the bdga cdg destino eb.
     * @return the bdgaCdgDestinoEb
     */
    public final String getBdgaCdgDestinoEb() {
        return this.bdgaCdgDestinoEb;
    }

    /**
     * Gets the bdga cdg eb.
     * @return the bdgaCdgEb
     */
    public final String getBdgaCdgEb() {
        return this.bdgaCdgEb;
    }

    /**
     * Gets the bdga cdg ed.
     * @return the bdgaCdgEd
     */
    public final String getBdgaCdgEd() {
        return this.bdgaCdgEd;
    }

    /**
     * Gets the bdga cdg md.
     * @return the bdgaCdgMd
     */
    public final String getBdgaCdgMd() {
        return this.bdgaCdgMd;
    }

    /**
     * Gets the bdga tpo.
     * @return the bdgaTpo
     */
    public final String getBdgaTpo() {
        return this.bdgaTpo;
    }

    /**
     * Gets the bdga tpo destino.
     * @return the bdgaTpoDestino
     */
    public final String getBdgaTpoDestino() {
        return this.bdgaTpoDestino;
    }

    /**
     * Gets the bdga tpo destino eb.
     * @return the bdgTpoDestinoEb
     */
    public final String getBdgaTpoDestinoEb() {
        return this.bdgaTpoDestinoEb;
    }

    /**
     * Gets the bdga tpo eb.
     * @return the bdgaTpoEb
     */
    public final String getBdgaTpoEb() {
        return this.bdgaTpoEb;
    }

    /**
     * Gets the bdga tpo ed.
     * @return the bdgaTpoEd
     */
    public final String getBdgaTpoEd() {
        return this.bdgaTpoEd;
    }

    /**
     * Gets the bdga tpo md.
     * @return the bdgaTpoMd
     */
    public final String getBdgaTpoMd() {
        return this.bdgaTpoMd;
    }

    /**
     * Gets the desc error.
     * @return the descError
     */
    public final String getDescError() {
        return this.descError;
    }

    /**
     * Gets the evbg observacion.
     * @return the evbgOnservacion
     */
    public final String getEvbgObservacion() {
        return this.evbgObservacion;
    }

    /**
     * Gets the evbg seq.
     * @return the evbgSeq
     */
    public final long getEvbgSeq() {
        return this.evbgSeq;
    }

    /**
     * Gets the evdc documento.
     * @return the evdcDocumento
     */
    public final long getEvdcDocumento() {
        return this.evdcDocumento;
    }

    /**
     * Gets the evdc observacion.
     * @return the evdcObservacion
     */
    public final String getEvdcObservacion() {
        return this.evdcObservacion;
    }

    /**
     * Gets the evdc peso.
     * @return the evdcPeso
     */
    public final double getEvdcPeso() {
        return this.evdcPeso;
    }

    /**
     * Gets the evdc piezas.
     * @return the evdcPiezas
     */
    public final int getEvdcPiezas() {
        return this.evdcPiezas;
    }

    /**
     * Gets the evdc rsva.
     * @return the evdcRsva
     */
    public final int getEvdcRsva() {
        return this.evdcRsva;
    }

    /**
     * Gets the evdc seq.
     * @return the evdcSeq
     */
    public final long getEvdcSeq() {
        return this.evdcSeq;
    }

    /**
     * Gets the evto cdg.
     * @return the evtoCgd
     */
    public final String getEvtoCdg() {
        return this.evtoCdg;
    }

    /**
     * Gets the evto cdg eb.
     * @return the evtoCdgEb
     */
    public final String getEvtoCdgEb() {
        return this.evtoCdgEb;
    }

    /**
     * Gets the evto cdg ed.
     * @return the evtoCdgEd
     */
    public final String getEvtoCdgEd() {
        return this.evtoCdgEd;
    }

    /**
     * Gets the evto swt exc evt.
     * @return the evtoSwtExcEvt
     */
    public final String getEvtoSwtExcEvt() {
        return this.evtoSwtExcEvt;
    }

    /**
     * Gets the evto swt exc evt ed.
     * @return the evtoSwtExcEvtEd
     */
    public final String getEvtoSwtExcEvtEd() {
        return this.evtoSwtExcEvtEd;
    }

    /**
     * Gets the evto swt exc evt md.
     * @return the evtoSwtExcEvtMd
     */
    public final String getEvtoSwtExcEvtMd() {
        return this.evtoSwtExcEvtMd;
    }

    /**
     * Gets the mvbg observacion.
     * @return the mvbgObservacion
     */
    public final String getMvbgObservacion() {
        return this.mvbgObservacion;
    }

    /**
     * Gets the mvbg seq.
     * @return the mvbgSeq
     */
    public final long getMvbgSeq() {
        return this.mvbgSeq;
    }

    /**
     * Gets the mvdc documento.
     * @return the mvdcDocumento
     */
    public final long getMvdcDocumento() {
        return this.mvdcDocumento;
    }

    /**
     * Gets the mvdc observacion.
     * @return the mvdcObservacion
     */
    public final String getMvdcObservacion() {
        return this.mvdcObservacion;
    }

    /**
     * Gets the mvdc peso.
     * @return the mvdcPeso
     */
    public final double getMvdcPeso() {
        return this.mvdcPeso;
    }

    /**
     * Gets the mvdc piezas.
     * @return the mvdcPiezas
     */
    public final int getMvdcPiezas() {
        return this.mvdcPiezas;
    }

    /**
     * Gets the mvdc rsva.
     * @return the mvdcRsva
     */
    public final int getMvdcRsva() {
        return this.mvdcRsva;
    }

    /**
     * Gets the mvdc seq.
     * @return the mvdcSeq
     */
    public final long getMvdcSeq() {
        return this.mvdcSeq;
    }

    /**
     * Gets the ofcn cdg.
     * @return the ofcnCdg
     */
    public final String getOfcnCdg() {
        return this.ofcnCdg;
    }

    /**
     * Gets the ofcn cdg destino.
     * @return the ofcnCdgDestino
     */
    public final String getOfcnCdgDestino() {
        return this.ofcnCdgDestino;
    }

    /**
     * Gets the ofcn cdg destino eb.
     * @return the ofcnCdgDestinoEb
     */
    public final String getOfcnCdgDestinoEb() {
        return this.ofcnCdgDestinoEb;
    }

    /**
     * Gets the ofcn cdg eb.
     * @return the ofcnCdgEb
     */
    public final String getOfcnCdgEb() {
        return this.ofcnCdgEb;
    }

    /**
     * Gets the ofcn cdg ed.
     * @return the ofcnCdgEd
     */
    public final String getOfcnCdgEd() {
        return this.ofcnCdgEd;
    }

    /**
     * Gets the ofcn cdg md.
     * @return the ofcnCdgMd
     */
    public final String getOfcnCdgMd() {
        return this.ofcnCdgMd;
    }

    /**
     * Gets the operacion.
     * @return the operacion
     */
    @Override
    public final int getOperacion() {
        return this.operacion;
    }

    /**
     * Gets the prso cdg.
     * @return the prsoCdg
     */
    public final String getPrsoCdg() {
        return this.prsoCdg;
    }

    /**
     * Gets the prso cdg eb.
     * @return the prsoCdgEb
     */
    public final String getPrsoCdgEb() {
        return this.prsoCdgEb;
    }

    /**
     * Gets the prso cdg ed.
     * @return the prsoCdgEd
     */
    public final String getPrsoCdgEd() {
        return this.prsoCdgEd;
    }

    /**
     * Gets the prso cdg md.
     * @return the prsoCsgMd
     */
    public final String getPrsoCdgMd() {
        return this.prsoCdgMd;
    }

    /**
     * Gets the tpo evt.
     * @return the tpoEvt
     */
    public final int getTpoEvt() {
        return this.tpoEvt;
    }

    /**
     * Gets the udmd cdg.
     * @return the udmdCdg
     */
    public final String getUdmdCdg() {
        return this.udmdCdg;
    }

    /**
     * Gets the udmd cdg eb.
     * @return the udmdEb
     */
    public final String getUdmdCdgEb() {
        return this.udmdCdgEb;
    }

    /**
     * Gets the udmd cdg ed.
     * @return the udmdCdgEd
     */
    public final String getUdmdCdgEd() {
        return this.udmdCdgEd;
    }

    /**
     * Gets the udmd cdg md.
     * @return the udmdCdgMd
     */
    public final String getUdmdCdgMd() {
        return this.udmdCdgMd;
    }

    /**
     * Sets the bdga cdg.
     * @param bdgaCdg the bdgaCdg to set
     */
    public final void setBdgaCdg(final String bdgaCdg) {
        this.bdgaCdg = bdgaCdg;
    }

    /**
     * Sets the bdga cdg destino.
     * @param bdgaCdgDestino the bdgaCdgDestino to set
     */
    public final void setBdgaCdgDestino(final String bdgaCdgDestino) {
        this.bdgaCdgDestino = bdgaCdgDestino;
    }

    /**
     * Sets the bdga cdg destino eb.
     * @param bdgaCdgDestinoEb the bdgaCdgDestinoEb to set
     */
    public final void setBdgaCdgDestinoEb(final String bdgaCdgDestinoEb) {
        this.bdgaCdgDestinoEb = bdgaCdgDestinoEb;
    }

    /**
     * Sets the bdga cdg eb.
     * @param bdgaCdgEb the bdgaCdgEb to set
     */
    public final void setBdgaCdgEb(final String bdgaCdgEb) {
        this.bdgaCdgEb = bdgaCdgEb;
    }

    /**
     * Sets the bdga cdg ed.
     * @param bdgaCdgEd the bdgaCdgEd to set
     */
    public final void setBdgaCdgEd(final String bdgaCdgEd) {
        this.bdgaCdgEd = bdgaCdgEd;
    }

    /**
     * Sets the bdga cdg md.
     * @param bdgaCdgMd the bdgaCdgMd to set
     */
    public final void setBdgaCdgMd(final String bdgaCdgMd) {
        this.bdgaCdgMd = bdgaCdgMd;
    }

    /**
     * Sets the bdga tpo.
     * @param bdgaTpo the bdgaTpo to set
     */
    public final void setBdgaTpo(final String bdgaTpo) {
        this.bdgaTpo = bdgaTpo;
    }

    /**
     * Sets the bdga tpo destino.
     * @param bdgaTpoDestino the bdgaTpoDestino to set
     */
    public final void setBdgaTpoDestino(final String bdgaTpoDestino) {
        this.bdgaTpoDestino = bdgaTpoDestino;
    }

    /**
     * Sets the bdga tpo destino eb.
     * @param bdgaTpoDestinoEb the new bdga tpo destino eb
     */
    public final void setBdgaTpoDestinoEb(final String bdgaTpoDestinoEb) {
        this.bdgaTpoDestinoEb = bdgaTpoDestinoEb;
    }

    /**
     * Sets the bdga tpo eb.
     * @param bdgaTpoEb the bdgaTpoEb to set
     */
    public final void setBdgaTpoEb(final String bdgaTpoEb) {
        this.bdgaTpoEb = bdgaTpoEb;
    }

    /**
     * Sets the bdga tpo ed.
     * @param bdgaTpoEd the bdgaTpoEd to set
     */
    public final void setBdgaTpoEd(final String bdgaTpoEd) {
        this.bdgaTpoEd = bdgaTpoEd;
    }

    /**
     * Sets the bdga tpo md.
     * @param bdgaTpoMd the bdgaTpoMd to set
     */
    public final void setBdgaTpoMd(final String bdgaTpoMd) {
        this.bdgaTpoMd = bdgaTpoMd;
    }

    /**
     * Sets the desc error.
     * @param descError the descError to set
     */
    public final void setDescError(final String descError) {
        this.descError = descError;
    }

    /**
     * Sets the evbg observacion.
     * @param evbgObservacion the new evbg observacion
     */
    public final void setEvbgObservacion(final String evbgObservacion) {
        this.evbgObservacion = evbgObservacion;
    }

    /**
     * Sets the evbg seq.
     * @param evbgSeq the evbgSeq to set
     */
    public final void setEvbgSeq(final long evbgSeq) {
        this.evbgSeq = evbgSeq;
    }

    /**
     * Sets the evdc documento.
     * @param evdcDocumento the evdcDocumento to set
     */
    public final void setEvdcDocumento(final long evdcDocumento) {
        this.evdcDocumento = evdcDocumento;
    }

    /**
     * Sets the evdc observacion.
     * @param evdcObservacion the evdcObservacion to set
     */
    public final void setEvdcObservacion(final String evdcObservacion) {
        this.evdcObservacion = evdcObservacion;
    }

    /**
     * Sets the evdc peso.
     * @param evdcPeso the evdcPeso to set
     */
    public final void setEvdcPeso(final double evdcPeso) {
        this.evdcPeso = evdcPeso;
    }

    /**
     * Sets the evdc piezas.
     * @param evdcPiezas the evdcPiezas to set
     */
    public final void setEvdcPiezas(final int evdcPiezas) {
        this.evdcPiezas = evdcPiezas;
    }

    /**
     * Sets the evdc rsva.
     * @param evdcRsva the evdcRsva to set
     */
    public final void setEvdcRsva(final int evdcRsva) {
        this.evdcRsva = evdcRsva;
    }

    /**
     * Sets the evdc seq.
     * @param evdcSeq the evdcSeq to set
     */
    public final void setEvdcSeq(final long evdcSeq) {
        this.evdcSeq = evdcSeq;
    }

    /**
     * Sets the evto cdg.
     * @param evtoCdg the new evto cdg
     */
    public final void setEvtoCdg(final String evtoCdg) {
        this.evtoCdg = evtoCdg;
    }

    /**
     * Sets the evto cdg eb.
     * @param evtoCdgEb the evtoCdgEb to set
     */
    public final void setEvtoCdgEb(final String evtoCdgEb) {
        this.evtoCdgEb = evtoCdgEb;
    }

    /**
     * Sets the evto cdg ed.
     * @param evtoCdgEd the evtoCdgEd to set
     */
    public final void setEvtoCdgEd(final String evtoCdgEd) {
        this.evtoCdgEd = evtoCdgEd;
    }

    /**
     * Sets the evto swt exc evt.
     * @param evtoSwtExcEvt the evtoSwtExcEvt to set
     */
    public final void setEvtoSwtExcEvt(final String evtoSwtExcEvt) {
        this.evtoSwtExcEvt = evtoSwtExcEvt;
    }

    /**
     * Sets the evto swt exc evt ed.
     * @param evtoSwtExcEvtEd the evtoSwtExcEvtEd to set
     */
    public final void setEvtoSwtExcEvtEd(final String evtoSwtExcEvtEd) {
        this.evtoSwtExcEvtEd = evtoSwtExcEvtEd;
    }

    /**
     * Sets the evto swt exc evt md.
     * @param evtoSwtExcEvtMd the evtoSwtExcEvtMd to set
     */
    public final void setEvtoSwtExcEvtMd(final String evtoSwtExcEvtMd) {
        this.evtoSwtExcEvtMd = evtoSwtExcEvtMd;
    }

    /**
     * Sets the mvbg observacion.
     * @param mvbgObservacion the mvbgObservacion to set
     */
    public final void setMvbgObservacion(final String mvbgObservacion) {
        this.mvbgObservacion = mvbgObservacion;
    }

    /**
     * Sets the mvbg seq.
     * @param mvbgSeq the mvbgSeq to set
     */
    public final void setMvbgSeq(final long mvbgSeq) {
        this.mvbgSeq = mvbgSeq;
    }

    /**
     * Sets the mvdc documento.
     * @param mvdcDocumento the mvdcDocumento to set
     */
    public final void setMvdcDocumento(final long mvdcDocumento) {
        this.mvdcDocumento = mvdcDocumento;
    }

    /**
     * Sets the mvdc observacion.
     * @param mvdcObservacion the mvdcObservacion to set
     */
    public final void setMvdcObservacion(final String mvdcObservacion) {
        this.mvdcObservacion = mvdcObservacion;
    }

    /**
     * Sets the mvdc peso.
     * @param mvdcPeso the mvdcPeso to set
     */
    public final void setMvdcPeso(final double mvdcPeso) {
        this.mvdcPeso = mvdcPeso;
    }

    /**
     * Sets the mvdc piezas.
     * @param mvdcPiezas the mvdcPiezas to set
     */
    public final void setMvdcPiezas(final int mvdcPiezas) {
        this.mvdcPiezas = mvdcPiezas;
    }

    /**
     * Sets the mvdc rsva.
     * @param mvdcRsva the mvdcRsva to set
     */
    public final void setMvdcRsva(final int mvdcRsva) {
        this.mvdcRsva = mvdcRsva;
    }

    /**
     * Sets the mvdc seq.
     * @param mvdcSeq the mvdcSeq to set
     */
    public final void setMvdcSeq(final long mvdcSeq) {
        this.mvdcSeq = mvdcSeq;
    }

    /**
     * Sets the ofcn cdg.
     * @param ofcnCdg the ofcnCdg to set
     */
    public final void setOfcnCdg(final String ofcnCdg) {
        this.ofcnCdg = ofcnCdg;
    }

    /**
     * Sets the ofcn cdg destino.
     * @param ofcnCdgDestino the ofcnCdgDestino to set
     */
    public final void setOfcnCdgDestino(final String ofcnCdgDestino) {
        this.ofcnCdgDestino = ofcnCdgDestino;
    }

    /**
     * Sets the ofcn cdg destino eb.
     * @param ofcnCdgDestinoEb the ofcnCdgDestinoEb to set
     */
    public final void setOfcnCdgDestinoEb(final String ofcnCdgDestinoEb) {
        this.ofcnCdgDestinoEb = ofcnCdgDestinoEb;
    }

    /**
     * Sets the ofcn cdg eb.
     * @param ofcnCdgEb the ofcnCdgEb to set
     */
    public final void setOfcnCdgEb(final String ofcnCdgEb) {
        this.ofcnCdgEb = ofcnCdgEb;
    }

    /**
     * Sets the ofcn cdg ed.
     * @param ofcnCdgEd the ofcnCdgEd to set
     */
    public final void setOfcnCdgEd(final String ofcnCdgEd) {
        this.ofcnCdgEd = ofcnCdgEd;
    }

    /**
     * Sets the ofcn cdg md.
     * @param ofcnCdgMd the ofcnCdgMd to set
     */
    public final void setOfcnCdgMd(final String ofcnCdgMd) {
        this.ofcnCdgMd = ofcnCdgMd;
    }

    /**
     * Sets the operacion.
     * @param operacion the operacion to set
     */
    @Override
    public final void setOperacion(final int operacion) {
        this.operacion = operacion;
    }

    /**
     * Sets the prso cdg.
     * @param prsoCdg the prsoCdg to set
     */
    public final void setPrsoCdg(final String prsoCdg) {
        this.prsoCdg = prsoCdg;
    }

    /**
     * Sets the prso cdg eb.
     * @param prsoCdgEb the prsoCdgEb to set
     */
    public final void setPrsoCdgEb(final String prsoCdgEb) {
        this.prsoCdgEb = prsoCdgEb;
    }

    /**
     * Sets the prso cdg ed.
     * @param prsoCdgEd the prsoCdgEd to set
     */
    public final void setPrsoCdgEd(final String prsoCdgEd) {
        this.prsoCdgEd = prsoCdgEd;
    }

    /**
     * Sets the prso cdg md.
     * @param prsoCdgMd the new prso cdg md
     */
    public final void setPrsoCdgMd(final String prsoCdgMd) {
        this.prsoCdgMd = prsoCdgMd;
    }

    /**
     * Sets the rre evento bodega.
     * @param mvbgSeq the mvbg seq
     * @param bdgaCdg the bdga cdg
     * @param bdgaTpo the bdga tpo
     * @param ofcnCdg the ofcn cdg
     * @param bdgaTpoDestino the bdga tpo destino
     * @param ofcnCdgDestino the ofcn cdg destino
     * @param bdgaCdgDestino the bdga cdg destino
     * @param udmdCdg the udmd cdg
     * @param mcbgObservacion the mvbg observacion
     * @param prsoCdg the prso cdg
     * @return the reg rap evento vo
     */
    public final RegRapEventoVO setRREEventoBodega(final long mvbgSeq, final String bdgaCdg, final String bdgaTpo,
            final String ofcnCdg, final String bdgaTpoDestino, final String ofcnCdgDestino,
            final String bdgaCdgDestino, final String udmdCdg, final String mcbgObservacion, final String prsoCdg) {

        this.mvbgSeq = mvbgSeq;
        this.bdgaCdg = bdgaCdg;
        this.bdgaTpo = bdgaTpo;
        this.bdgaTpoDestino = bdgaTpoDestino;
        this.ofcnCdgDestino = ofcnCdgDestino;
        this.bdgaCdgDestino = bdgaCdgDestino;

        this.udmdCdg = udmdCdg;
        this.mvbgObservacion = mcbgObservacion;
        this.prsoCdg = prsoCdg;
        this.tpoEvt = 1;

        return this;
    }

    /**
     * Sets the rre evento documento.
     * @param evdcDocumento the evdc documento
     * @param evdcSeq the evdc seq
     * @param evtoSwtExcEvtEd the evto swt exc evt ed
     * @param evtoCdgEd the evto cdg ed
     * @param bdgaCdgEd the bdga cdg ed
     * @param bdgaTpoEd the bdga tpo ed
     * @param ofcnCdgEd the ofcn cdg ed
     * @param udmdCdgEd the udmd cdg ed
     * @param evdcRsva the evdc rsva
     * @param evdcPiezas the evdc piezas
     * @param evdcPeso the evdc peso
     * @param evdcObservacion the evdc observacion
     * @param prsoCdgEd the prso cdg ed
     * @return the reg rap evento vo
     */
    public final RegRapEventoVO setRREEventoDocumento(final long evdcDocumento, final long evdcSeq,
            final String evtoSwtExcEvtEd, final String evtoCdgEd, final String bdgaCdgEd, final String bdgaTpoEd,
            final String ofcnCdgEd, final String udmdCdgEd, final int evdcRsva, final int evdcPiezas,
            final int evdcPeso, final String evdcObservacion, final String prsoCdgEd) {
        this.evdcDocumento = evdcDocumento;
        this.evdcSeq = evdcSeq;
        this.evtoSwtExcEvtEd = evtoSwtExcEvtEd;
        this.evtoCdgEd = evtoCdgEd;

        this.bdgaCdgEd = bdgaCdgEd;
        this.bdgaTpoEd = bdgaTpoEd;
        this.ofcnCdgEd = ofcnCdgEd;
        this.udmdCdgEd = udmdCdgEd;
        this.evdcRsva = evdcRsva;
        this.evdcPiezas = evdcPiezas;
        this.evdcPeso = evdcPeso;
        this.evdcObservacion = evdcObservacion;
        this.prsoCdgEd = prsoCdgEd;

        this.tpoEvt = 4;

        return this;
    }

    /**
     * Sets the rre movimiento bodega.
     * @param evbgSeq the evbg seq
     * @param bdgaCdgEb the bdga cdg eb
     * @param bdgaTpoEb the bdga tpo eb
     * @param ofcnCdgEb the ofcn cdg eb
     * @param evtoSwtExcEvt the evto swt exc evt
     * @param udmdCdg the udmd cdg
     * @param bdgaTpoDestinoEb the bdga tpo destino eb
     * @param ofcnCdgDestinoEb the ofcn cdg destino eb
     * @param bdgaCdgDestinoEb the bdga cdg destino eb
     * @param evbgObservacion the evbg observacion
     * @param prsoCdgEb the prso cdg eb
     * @return the reg rap evento vo
     */
    public final RegRapEventoVO setRREMovimientoBodega(final long evbgSeq, final String bdgaCdgEb,
            final String bdgaTpoEb, final String ofcnCdgEb, final String evtoSwtExcEvt, final String udmdCdg,
            final String bdgaTpoDestinoEb, final String ofcnCdgDestinoEb, final String bdgaCdgDestinoEb,
            final String evbgObservacion, final String prsoCdgEb) {
        this.evbgSeq = evbgSeq;
        this.bdgaCdgEb = bdgaCdgEb;
        this.bdgaTpoEb = bdgaTpoEb;
        this.ofcnCdgEb = ofcnCdgEb;
        this.evtoSwtExcEvt = evtoSwtExcEvt;

        this.udmdCdg = udmdCdg;
        this.bdgaTpoDestinoEb = bdgaTpoDestinoEb;
        this.ofcnCdgDestinoEb = ofcnCdgDestinoEb;
        this.bdgaCdgDestinoEb = bdgaCdgDestinoEb;
        this.evbgObservacion = evbgObservacion;
        this.prsoCdgEb = prsoCdgEb;// nvarchar(20)
        this.tpoEvt = 2;

        return this;
    }

    /**
     * Sets the rre movimiento documento.
     * @param mvdcDocumento the mvdc documento
     * @param mvdcSeq the mvdc seq
     * @param bdgaCdgMd the bdga cdg md
     * @param bdgaTpoMd the bdga tpo md
     * @param ofcnCdgMd the ofcn cdg md
     * @param evtoSwtExcEvtMd the evto swt exc evt md
     * @param evtoCdg the evto cdg
     * @param udmdCdgMd the udmd cdg md
     * @param mvdcRsva the mvdc rsva
     * @param mvdcPiezas the mvdc piezas
     * @param mvdcPeso the mvdc peso
     * @param mvdcObservacion the mvdc observacion
     * @param prsoCdgMd the prso cdg md
     * @return the reg rap evento vo
     */
    public final RegRapEventoVO setRREMovimientoDocumento(final long mvdcDocumento, final long mvdcSeq,
            final String bdgaCdgMd, final String bdgaTpoMd, final String ofcnCdgMd, final String evtoSwtExcEvtMd,
            final String evtoCdg, final String udmdCdgMd, final int mvdcRsva, final int mvdcPiezas,
            final double mvdcPeso, final String mvdcObservacion, final String prsoCdgMd) {
        this.mvdcDocumento = mvdcDocumento;
        this.mvdcSeq = mvdcSeq;
        this.bdgaCdgMd = bdgaCdgMd;
        this.bdgaTpoMd = bdgaTpoMd;
        this.ofcnCdgMd = ofcnCdgMd;

        this.evtoSwtExcEvtMd = evtoSwtExcEvtMd;
        this.evtoCdg = evtoCdg;
        this.udmdCdgMd = udmdCdgMd;
        this.mvdcRsva = mvdcRsva;
        this.mvdcPiezas = mvdcPiezas;
        this.mvdcPeso = mvdcPeso;
        this.mvdcObservacion = mvdcObservacion;
        this.prsoCdgMd = prsoCdgMd;
        this.tpoEvt = 3;

        return this;
    }

    /**
     * Sets the tpo evt.
     * @param tpoEvt the tpoEvt to set
     */
    public final void setTpoEvt(final int tpoEvt) {
        this.tpoEvt = tpoEvt;
    }

    /**
     * Sets the udmd cdg.
     * @param udmdCdg the udmdCdg to set
     */
    public final void setUdmdCdg(final String udmdCdg) {
        this.udmdCdg = udmdCdg;
    }

    /**
     * Sets the udmd cdg eb.
     * @param udmdCdgEb the new udmd cdg eb
     */
    public final void setUdmdCdgEb(final String udmdCdgEb) {
        this.udmdCdgEb = udmdCdgEb;
    }

    /**
     * Sets the udmd cdg ed.
     * @param udmdCdgEd the udmdCdgEd to set
     */
    public final void setUdmdCdgEd(final String udmdCdgEd) {
        this.udmdCdgEd = udmdCdgEd;
    }

    /**
     * Sets the udmd cdg md.
     * @param udmdCdgMd the udmdCdgMd to set
     */
    public final void setUdmdCdgMd(final String udmdCdgMd) {
        this.udmdCdgMd = udmdCdgMd;
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

}
