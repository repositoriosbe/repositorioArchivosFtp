package cl.bluex.entidades;

public class DetalleErrorTO {

	private Integer codigoError;
	private Integer codigoArchivo;
	private String contenido;
	
	/**
	 * 
	 */
	public DetalleErrorTO() {
		super();
	}

	/**
	 * 
	 * @param codigoError
	 * @param codigoArchivo
	 * @param contenido
	 */
	public DetalleErrorTO(Integer codigoError, Integer codigoArchivo,
			String contenido) {
		super();
		this.codigoError = codigoError;
		this.codigoArchivo = codigoArchivo;
		this.contenido = contenido;
	}

	/**
	 * @return the codigoError
	 */
	public Integer getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(Integer codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * @return the codigoArchivo
	 */
	public Integer getCodigoArchivo() {
		return codigoArchivo;
	}

	/**
	 * @param codigoArchivo the codigoArchivo to set
	 */
	public void setCodigoArchivo(Integer codigoArchivo) {
		this.codigoArchivo = codigoArchivo;
	}

	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
}
