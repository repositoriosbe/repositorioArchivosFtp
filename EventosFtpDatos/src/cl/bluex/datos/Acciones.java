package cl.bluex.datos;

import java.io.IOException;
import java.net.SocketException;

// TODO: Auto-generated Javadoc
/**
 * The Interface Acciones.
 */
public interface Acciones {

	
	/**
	 * Conectar.
	 * 
	 * @return true, if successful
	 * @throws SocketException
	 *             the socket exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    boolean conectar() throws SocketException, IOException;

	/**
	 * Desconectar.
	 * 
	 * @return true, if successful
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
    boolean desconectar() throws IOException;
}
