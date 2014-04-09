package cl.bluex.inicio;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import cl.bluex.entidades.Constantes;
import cl.bluex.entidades.ParametrosGenerales;
import cl.bluex.negocio.Parametros;
import cl.bluex.negocio.ProcesarArchivos;

/**
 * The Class Principal.
 * 
 * Proceso que busca archivos tipo NoEventos desde ftp por empresas,
 * los descarga a carpetas locales, los procesa en OXML.
 * Luego de procesados los deja en carpeta backup y error 
 * segun corresponda en el ftp de cada empresa.
 * 
 */
public class Principal {

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(Principal.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args) throws IOException {

		LOGGER.info("[noEventos] Obteniendo parametros generales");
		
		final Long lgEmpresa = Long.parseLong(Constantes.EMPRESA);
		final int tipoAplicacion = Integer.parseInt(Constantes.TIPO_APLICACION);
		
		// Busca PGEN
		final List<ParametrosGenerales> parametrosFTP = new Parametros(lgEmpresa,
				tipoAplicacion).buscaParametros();
		
		ProcesarArchivos proceso = new ProcesarArchivos();
		
		// Segun cantidad de registros desde pgen se ejecuta el proceso
		 for (ParametrosGenerales pgen : parametrosFTP) {

			 proceso.comenzar(pgen);
			 
		 }
		
		
	}
	
	
}


