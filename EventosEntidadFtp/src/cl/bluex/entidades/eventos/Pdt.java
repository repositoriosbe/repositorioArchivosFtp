package cl.bluex.entidades.eventos;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="PDT", namespace="PDT")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pdt {
	
	@XmlElement(name="HEAD")
	private Head head;
	
	@XmlElement(name="TABLA")
	private List<Tabla> listaTabla;

	public List<Tabla> getListaTabla() {
		return listaTabla;
	}

}
