package cl.bluex.entidades.eventos;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Head {

	@XmlElement(name="OP")
	private String op;
	
	@XmlElement(name="ID")
	private String id;
	
	@XmlElement(name="NMR_PDT")
	private String numeroPdt;
	
	@XmlElement(name="IP_MVIL")
	private String ipMvil;
	
	@XmlElement(name="TPO_MSJE")
	private String tipoMensaje;
	
	@XmlElement(name="FCH")
	private String fecha;
	
	@XmlElement(name="POSTA")
	private String posta;
	
}
