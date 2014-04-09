package cl.bluex.entidades.eventos;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Tabla {

    @XmlAttribute(name="NOMBRE")
    private String nombreTabla;
    
	@XmlElement(name="REGISTRO")
	private List<Registro> listaRegistro;
	
	public String getNombreTabla() {
		return nombreTabla;
	}

	public List<Registro> getListaRegistro() {
		return listaRegistro;
	}

}
