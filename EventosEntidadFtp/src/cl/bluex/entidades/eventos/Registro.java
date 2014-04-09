package cl.bluex.entidades.eventos;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Registro {
	
	@XmlElement(name="EVDC_DOCUMENTO")
	private String EVDC_DOCUMENTO;
	
	@XmlElement(name="MVDC_DOCUMENTO")
	private String MVDC_DOCUMENTO;
	
	@XmlElement(name="EVDC_SEQ")
	private String EVDC_SEQ;
	
	@XmlElement(name="MVDC_SEQ")
	private String MVDC_SEQ;	
	
	@XmlElement(name="EVTO_SWT_EXC_EVT")
	private String EVTO_SWT_EXC_EVT;
	
	@XmlElement(name="EVTO_CDG")
	private String EVTO_CDG;
	
	@XmlElement(name="BDGA_CDG")
	private String BDGA_CDG;
	
	@XmlElement(name="BDGA_TPO")
	private String BDGA_TPO;
	
	@XmlElement(name="OFCN_CDG")
	private String OFCN_CDG;
	
	@XmlElement(name="EVDC_PIEZAS")
	private String EVDC_PIEZAS;
	
	@XmlElement(name="MVDC_PIEZAS")
	private String MVDC_PIEZAS;	
		
	@XmlElement(name="MVDC_OBSERVACION")
	private String MVDC_OBSERVACION;
	
	@XmlElement(name="EVDC_OBSERVACION")
	private String EVDC_OBSERVACION;	
	
	@XmlElement(name="PRSO_CDG")
	private String PRSO_CDG;
	
	@XmlElement(name="EVDC_SWT_ENVIADO")
	private String EVDC_SWT_ENVIADO;
	
	@XmlElement(name="MVDC_SWT_ENVIADO")
	private String MVDC_SWT_ENVIADO;	
	
	@XmlElement(name="UDMD_CDG")
	private String UDMD_CDG;
	
	@XmlElement(name="EVDC_FCH_ENVIO")
	private String EVDC_FCH_ENVIO;
	
	@XmlElement(name="MVDC_FCH_ENVIO")
	private String MVDC_FCH_ENVIO;
	
	@XmlElement(name="EVDC_PESO")
	private String EVDC_PESO;
	
	@XmlElement(name="EVDC_RSVA")
	private String EVDC_RSVA;
	
	@XmlElement(name="MVDC_RSVA")
	private String MVDC_RSVA;

	public String getEVDC_DOCUMENTO() {
		return EVDC_DOCUMENTO;
		
		
	}

	public void setEVDC_DOCUMENTO(String eVDC_DOCUMENTO) {
		EVDC_DOCUMENTO = eVDC_DOCUMENTO;
	}

	public String getMVDC_DOCUMENTO() {
		return MVDC_DOCUMENTO;
	}

	public void setMVDC_DOCUMENTO(String mVDC_DOCUMENTO) {
		MVDC_DOCUMENTO = mVDC_DOCUMENTO;
	}

	public String getEVDC_SEQ() {
		return EVDC_SEQ;
	}

	public void setEVDC_SEQ(String eVDC_SEQ) {
		EVDC_SEQ = eVDC_SEQ;
	}

	public String getMVDC_SEQ() {
		return MVDC_SEQ;
	}

	public void setMVDC_SEQ(String mVDC_SEQ) {
		MVDC_SEQ = mVDC_SEQ;
	}

	public String getEVTO_SWT_EXC_EVT() {
		return EVTO_SWT_EXC_EVT;
	}

	public void setEVTO_SWT_EXC_EVT(String eVTO_SWT_EXC_EVT) {
		EVTO_SWT_EXC_EVT = eVTO_SWT_EXC_EVT;
	}

	public String getEVTO_CDG() {
		return EVTO_CDG;
	}

	public void setEVTO_CDG(String eVTO_CDG) {
		EVTO_CDG = eVTO_CDG;
	}

	public String getBDGA_CDG() {
		return BDGA_CDG;
	}

	public void setBDGA_CDG(String bDGA_CDG) {
		BDGA_CDG = bDGA_CDG;
	}

	public String getBDGA_TPO() {
		return BDGA_TPO;
	}

	public void setBDGA_TPO(String bDGA_TPO) {
		BDGA_TPO = bDGA_TPO;
	}

	public String getOFCN_CDG() {
		return OFCN_CDG;
	}

	public void setOFCN_CDG(String oFCN_CDG) {
		OFCN_CDG = oFCN_CDG;
	}

	public String getEVDC_PIEZAS() {
		return EVDC_PIEZAS;
	}

	public void setEVDC_PIEZAS(String eVDC_PIEZAS) {
		EVDC_PIEZAS = eVDC_PIEZAS;
	}

	public String getMVDC_PIEZAS() {
		return MVDC_PIEZAS;
	}

	public void setMVDC_PIEZAS(String mVDC_PIEZAS) {
		MVDC_PIEZAS = mVDC_PIEZAS;
	}

	public String getEVDC_OBSERVACION() {
		return EVDC_OBSERVACION;
	}

	public void setEVDC_OBSERVACION(String eVDC_OBSERVACION) {
		EVDC_OBSERVACION = eVDC_OBSERVACION;
	}

	public String getPRSO_CDG() {
		return PRSO_CDG;
	}

	public void setPRSO_CDG(String pRSO_CDG) {
		PRSO_CDG = pRSO_CDG;
	}

	public String getEVDC_SWT_ENVIADO() {
		return EVDC_SWT_ENVIADO;
	}

	public void setEVDC_SWT_ENVIADO(String eVDC_SWT_ENVIADO) {
		EVDC_SWT_ENVIADO = eVDC_SWT_ENVIADO;
	}

	public String getMVDC_SWT_ENVIADO() {
		return MVDC_SWT_ENVIADO;
	}

	public void setMVDC_SWT_ENVIADO(String mVDC_SWT_ENVIADO) {
		MVDC_SWT_ENVIADO = mVDC_SWT_ENVIADO;
	}

	public String getUDMD_CDG() {
		return UDMD_CDG;
	}

	public void setUDMD_CDG(String uDMD_CDG) {
		UDMD_CDG = uDMD_CDG;
	}

	public String getEVDC_FCH_ENVIO() {
		return EVDC_FCH_ENVIO;
	}

	public void setEVDC_FCH_ENVIO(String eVDC_FCH_ENVIO) {
		EVDC_FCH_ENVIO = eVDC_FCH_ENVIO;
	}

	public String getMVDC_FCH_ENVIO() {
		return MVDC_FCH_ENVIO;
	}

	public void setMVDC_FCH_ENVIO(String mVDC_FCH_ENVIO) {
		MVDC_FCH_ENVIO = mVDC_FCH_ENVIO;
	}

	public String getMVDC_OBSERVACION() {
		return MVDC_OBSERVACION;
	}

	public void setMVDC_OBSERVACION(String mVDC_OBSERVACION) {
		MVDC_OBSERVACION = mVDC_OBSERVACION;
	}

	public String getEVDC_PESO() {
		return EVDC_PESO;
	}

	public void setEVDC_PESO(String eVDC_PESO) {
		EVDC_PESO = eVDC_PESO;
	}

	public String getEVDC_RSVA() {
		return EVDC_RSVA;
	}

	public void setEVDC_RSVA(String eVDC_RSVA) {
		EVDC_RSVA = eVDC_RSVA;
	}

	public String getMVDC_RSVA() {
		return MVDC_RSVA;
	}

	public void setMVDC_RSVA(String mVDC_RSVA) {
		MVDC_RSVA = mVDC_RSVA;
	}	
}
