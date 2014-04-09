package cl.bluex.negocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import cl.bluex.datos.ValidaEventoDB;
import cl.bluex.entidades.Constantes;
import cl.bluex.entidades.DirectoryFiles;
import cl.bluex.entidades.ParametrosGenerales;
import cl.bluex.entidades.eventos.Pdt;
import cl.bluex.entidades.eventos.Registro;
import cl.bluex.entidades.eventos.Tabla;

/**
 * The Class ProcesarArchivos.
 */
public class ProcesarArchivos {

	/** The Constant LOGGER. */
	static final Logger LOGGER = Logger.getLogger(ProcesarArchivos.class);

	/** The acc. */
	private AccionesFTPImpl ftp;

	/** The util. */
	Utilidad util = new Utilidad();


	/**
	 * Comenzar.
	 * 
	 * Proceso EVENTOS
	 * Recupera archivos desde ftp y los procesa en tabla CTRT
	 *
	 * @param pgen the pgen
	 * @return true, if successful
	 * @throws SocketException             the socket exception
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	public boolean comenzar(ParametrosGenerales pgen) throws SocketException, IOException {

		LOGGER.info("[eventos] Iniciando proceso EMPRESA [" + pgen.getEmpresa() +"]  : "+ util.getFechaHora());
		
		this.ftp = new AccionesFTPImpl(pgen.getIpServidor(), pgen.getUsuario(), pgen.getClave());
		
		boolean respuesta = false;
		
		try{
		
			// String pathActual = System.getProperty("user.dir");

			String pathActual = Constantes.PATH_INICIAL_EVENTOS;

			final String _rutaCarpetaLocal = pathActual	+ pgen.getDirDescargas();
			final String _rutaLocalError = pathActual	+ pgen.getDirErrorLocal();
			final String _rutaLocalBackup = pathActual	+ pgen.getDirBackupLocal();
			final String _rutaProcesadasLocal = pathActual	+ pgen.getDirProcLocal();
			final String _rutaBackupFtp = pgen.getDirBackupFtp();
			final String _rutaErrorFtp = pgen.getDirErrorFtp();

			String _fTPextencionTmp = Constantes.FTP_TMP_EXTENCION;
			

			if (ftp.conectar()) {

				ftp.setPassiveMode(true);

				// Lista directorios FTP
				List<String> listDirectoriosFTP = ftp.listSubdirNames();

				List<String> listaArchLocal = null;
				int cantArchivos = 0;
				List<String> listaArchivosEventos = new ArrayList<String>();

				LOGGER.info("[eventos] Conectando al FTP           : "	+ ftp.getHost());
				LOGGER.info("[eventos] Lista Directorios FTP (raiz): "	+ listDirectoriosFTP);
				LOGGER.info("[eventos] Ruta descarga local         : "	+ _rutaCarpetaLocal);
				
				if (listDirectoriosFTP.size() > 0) {

					// Copia archivos desde directorio FTP a directoio local
					for (final String _directorio : listDirectoriosFTP) {

						if (!ftp.isConnected()) {
							ftp.conectar();
						}
						final DirectoryFiles directoryFiles = new DirectoryFiles();
						directoryFiles.setPath(_directorio + File.separator	+ Constantes.CARPETA_FTP_TO_BE);
						List<String> listArchivosFTP = ftp.listFileNamesSub(File.separator + _directorio + File.separator + Constantes.CARPETA_FTP_TO_BE);
						LOGGER.info("[eventos] Descargando desde Directorio : " + _directorio + "\t" + " - Archivos en directorio => "	+ listArchivosFTP.size());
						listaArchLocal = getFilesFTP(File.separator + _directorio + File.separator + "TO_BE", _rutaCarpetaLocal, _fTPextencionTmp, directoryFiles);
						cantArchivos = cantArchivos + listaArchLocal.size();

					}

					if (cantArchivos > 0) {

						LOGGER.info("[eventos] Archivos Descargados desde FTP : " + cantArchivos);
						String[] archivosLocal = directorioLocal(_rutaCarpetaLocal);
						LOGGER.info("[eventos] Archivos en direcotiro local   : " + archivosLocal.length);

						for (int i = 0; i < archivosLocal.length; i++) {

							listaArchivosEventos.add(archivosLocal[i]);

						}

						procesarEventos(listaArchivosEventos, _rutaCarpetaLocal, _rutaLocalError, _rutaLocalBackup);
						enviarArchivoFTP(_rutaLocalBackup, _rutaLocalBackup, _rutaBackupFtp);
						enviarArchivoFTP(_rutaLocalError, _rutaLocalError, _rutaErrorFtp);
						copiaCarpetaProcesadas(_rutaLocalBackup, _rutaProcesadasLocal, "_backup_" + pgen.getEmpresa());
						eliminaArchivosLocal(_rutaLocalBackup);
						copiaCarpetaProcesadas(_rutaLocalError, _rutaProcesadasLocal, "_error_" + pgen.getEmpresa());
						eliminaArchivosLocal(_rutaLocalError);

					} else {
						LOGGER.info("[eventos] No se encontraron archivos para descarga. Se desconecta del FTP.");
					}
				}
				ftp.desconectar();
				LOGGER.info("[eventos] Proceso terminado EMPRESA [" + pgen.getEmpresa() +"]  : "+ util.getFechaHora());
				return respuesta = true;
			} else {
				LOGGER.error("[eventos] Error en la conexion al FTP.");
				LOGGER.error("[eventos] Host  :" + ftp.getHost());
				LOGGER.error("[eventos] Usser :" + ftp.getUserName());
				LOGGER.error("[eventos] Proceso terminado EMPRESA [" + pgen.getEmpresa() +"]  : "+ util.getFechaHora());
			}
			ftp.desconectar();
			LOGGER.info("[eventos] Proceso terminado EMPRESA [" + pgen.getEmpresa() +"]  : "+ util.getFechaHora());
			return respuesta;
		
		} catch (final SocketException e) {
			LOGGER.error("[eventos] Error en la conexion al FTP - " + e.getMessage());  
			LOGGER.error("[eventos] Host  :" + ftp.getHost()); 
		} catch (final IOException e) {
			LOGGER.error("[eventos] Error de I/O: " + e.getMessage());
		}
		
		return respuesta;
		
	}

	/**
	 * Procesar eventos.
	 *
	 * @param listaArchivosLocalEventos the lista archivos local eventos
	 * @param _rutaCarpetaLocal the _ruta carpeta local
	 * @param _rutaLocalError the _ruta local error
	 * @param _rutaLocalBackup the _ruta local backup
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void procesarEventos(List<String> listaArchivosLocalEventos,
			String _rutaCarpetaLocal, String _rutaLocalError,
			String _rutaLocalBackup) throws IOException {

		String nombreArchivo = null;
		boolean respuestaCtrt = false;
		int cantArchivosCTRT = 0;

		if (listaArchivosLocalEventos.size() > 0) {
			
			LOGGER.info("[procesarEventos] Descomprimiendo archivos desde : " + _rutaCarpetaLocal);
			
			for (int i = 0; i < listaArchivosLocalEventos.size(); i++) {

				nombreArchivo = ftp.unZipFile(_rutaCarpetaLocal, listaArchivosLocalEventos.get(i));

				if (nombreArchivo == null || nombreArchivo.equals(Constantes.VACIO)) {

					LOGGER.info("[procesarEventos] Archivo con ERROR \t [" + listaArchivosLocalEventos.get(i) + "] \t [Tipo Evento]");
					
					// copia a carpeta error
					boolean resp = copiaProcesadas(_rutaCarpetaLocal, _rutaLocalError, listaArchivosLocalEventos.get(i));

					if (resp) {
						File fileZip = new File(_rutaCarpetaLocal + File.separator + listaArchivosLocalEventos.get(i));
						fileZip.delete();
					}

				} else {

					respuestaCtrt = procesarFicheroEventos(nombreArchivo,_rutaCarpetaLocal);

					if (respuestaCtrt) {
						cantArchivosCTRT++;
						// copia a carpeta backup
						boolean resp = copiaProcesadas(_rutaCarpetaLocal,_rutaLocalBackup,listaArchivosLocalEventos.get(i));

						if (resp) {
							File fileZip = new File(_rutaCarpetaLocal + File.separator + listaArchivosLocalEventos.get(i));
							File fileTxt = new File(_rutaCarpetaLocal + File.separator + nombreArchivo);
							fileZip.delete();
							fileTxt.delete();
						}

					} else {
						LOGGER.info("[procesarEventos] Archivo Descomprimido \t [ "
								+ nombreArchivo
								+ "] \t NO insertado en CTRT \t [Tipo Evento] se envia a carpeta error.");
						// copia a carpeta error
						boolean resp = copiaProcesadas(_rutaCarpetaLocal,
								_rutaLocalError,
								listaArchivosLocalEventos.get(i));

						if (resp) {
							File fileZip = new File(_rutaCarpetaLocal + File.separator + listaArchivosLocalEventos.get(i));
							File fileTxt = new File(_rutaCarpetaLocal + File.separator + nombreArchivo);
							fileZip.delete();
							fileTxt.delete();
						}
					}
				}
			}

			LOGGER.info("[procesarEventos] Cantidad de Archivos procesados en CTRT : "	+ cantArchivosCTRT);
		}

	}


	/**
	 * Enviar archivo ftp.
	 *
	 * @param rutaLocalArchivos the ruta local archivos
	 * @param localFile the local file
	 * @param hostFile the host file
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void enviarArchivoFTP(final String rutaLocalArchivos,
			String localFile, String hostFile) throws FileNotFoundException,
			IOException {

		LOGGER.info("[enviarArchivoFTP] Enviando archivos al FTP carpeta " + hostFile.toUpperCase());
		List<String> listaLocal = ftp.listaArchivosLocal(rutaLocalArchivos + File.separator + "");
		ftp.conectar();
		for (final String _strFichero : listaLocal) {
			FileInputStream fis = null;
			// FileOutputStream fos = null;
			fis = new FileInputStream(localFile + File.separator + _strFichero);

			ftp.setFileTransferMode(ftp.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();

			String destinoArchivoFTP = hostFile + File.separator + _strFichero;

			if (ftp.storeFile(destinoArchivoFTP, fis)) {
				LOGGER.info("[enviarArchivoFTP] Se envio archivo => " + _strFichero);
			} else {
				LOGGER.info("[enviarArchivoFTP] Error en envio al FTP archivo :" + _strFichero);
			}

			fis.close();

		}
		ftp.desconectar();

	}


	/**
	 * Gets the files.
	 * 
	 * @param pathArchivo
	 *            the path archivo
	 * @param pathLocal
	 *            the path local
	 * @param FTPextencionTmp
	 *            the fT pextencion tmp
	 * @param directoryFiles
	 *            the directory files
	 * @return the files
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private List<String> getFilesFTP(final String pathArchivo,
			final String pathLocal, final String FTPextencionTmp,
			final DirectoryFiles directoryFiles) throws IOException {

		List<String> listaArchLocal = ftp.descargarArchivosFTP(pathArchivo,
				pathLocal, FTPextencionTmp);
		directoryFiles.setFilesInDirectory(listaArchLocal);
		return listaArchLocal;

	}

	/**
	 * Gets the string archivo.
	 * 
	 * @param fichero
	 *            the fichero
	 * @return the string archivo
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static String getStringArchivo(final String fichero)
			throws IOException {
		final BufferedReader br = new BufferedReader(new FileReader(new File(
				fichero)));
		String lineIn = null;
		lineIn = br.readLine();
		br.close();
		return lineIn;
	}

	/**
	 * Copia carpeta procesadas.
	 *
	 * @param pathOrigen            the path origen
	 * @param pathDestino            the path destino
	 * @param tipo the tipo
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	public void copiaCarpetaProcesadas(String pathOrigen, String pathDestino,
			String tipo) throws IOException {

		String nombreCapeta = creaNuevaCarpeta(pathDestino, tipo );
		List<String> listaArchivos = ftp.listaArchivosLocal(pathOrigen);

		for (int i = 0; i < listaArchivos.size(); i++) {
			try {
				File origen = new File(pathOrigen + File.separator
						+ listaArchivos.get(i));
				File destino = new File(pathDestino + File.separator
						+ nombreCapeta + File.separator + listaArchivos.get(i));
				FileInputStream in = new FileInputStream(origen);
				FileOutputStream out = new FileOutputStream(destino);
				int c;
				while ((c = in.read()) != -1) {
					out.write(c);
				}
				in.close();
				out.close();
			} catch (IOException e) {
				LOGGER.info("[copiaCarpetaProcesadas] Error de entrada/salida!!!");
			}
		}

	}

	/**
	 * Copia procesadas.
	 *
	 * @param pathOrigen the path origen
	 * @param pathDestino the path destino
	 * @param archivo the archivo
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean copiaProcesadas(String pathOrigen, String pathDestino, String archivo) throws IOException {

		boolean respuesta = false;

		try {
			File origen = new File(pathOrigen + File.separator + archivo);
			File destino = new File(pathDestino + File.separator + File.separator + archivo);
			FileInputStream in = new FileInputStream(origen);
			FileOutputStream out = new FileOutputStream(destino);
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			respuesta = true;
			in.close();
			out.close();

		} catch (IOException e) {
			LOGGER.info("[copiaProcesadas] Error de entrada/salida!!!");
			respuesta = false;
		}

		return respuesta;
	}

	/**
	 * Crea nueva carpeta.
	 *
	 * @param pathDestino            the path destino
	 * @param tipo the tipo
	 * @return the string
	 */
	public String creaNuevaCarpeta(String pathDestino, String tipo) {

		Date date = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fechaConFormato = formato.format(date);
		String dia = fechaConFormato.substring(0, 2);
		String mes = fechaConFormato.substring(3, 5);
		String annio = fechaConFormato.substring(6, 10);

		File directorio = new File(pathDestino + 
									File.separator + 
									annio + 
									mes	+ 
									dia + 
									tipo );
		directorio.mkdirs();
		return annio + mes + dia + tipo;
	}

	/**
	 * Elimina archivos local.
	 * 
	 * @param pathLocal
	 *            the path local
	 */
	public void eliminaArchivosLocal(String pathLocal) {
		String direccion = pathLocal;
		File directorio = new File(direccion);
		File f;
		if (directorio.isDirectory()) {
			String[] files = directorio.list();
			if (files.length > 0) {
				for (String archivo : files) {
					f = new File(direccion + File.separator + archivo);
					f.delete();
					f.deleteOnExit();
				}
			} else {
				LOGGER.info("[eliminaArchivosLocal] Directorio vacio: "
						+ pathLocal);
			}
		}
	}


	/**
	 * Procesar.
	 *
	 * @param archivoLocal the archivo local
	 * @param path            the path
	 * @return the list
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	private boolean procesarFicheroEventos(final String archivoLocal,
			final String path) throws IOException {

		String respuesta = "";
		int regInsertados = 0;
		int regNoInsertados = 0;
		final ValidaEventoDB db = new ValidaEventoDB();

		final Pdt evento = getPdt(path + File.separator + archivoLocal);

		Iterator<Tabla> i = evento.getListaTabla().iterator();
		while (i.hasNext()) {
			Tabla tabla = (Tabla) i.next();
			Iterator<Registro> x = tabla.getListaRegistro().iterator();
			while (x.hasNext()) {
				Registro registro = (Registro) x.next();

				if ("MOVIMIENTO_DOCUMENTO".equals(tabla.getNombreTabla())) {

					respuesta = db.creaRegistroCtrtDocumento(registro, 3);

				} else if ("EVENTO_DOCUMENTO".equals(tabla.getNombreTabla())) {

					respuesta = db.creaRegistroCtrtDocumento(registro, 4);

				} else if ("MOVIMIENTO_BODEGA".equals(tabla.getNombreTabla())) {

				} else if ("EVENTO_BODEGA".equals(tabla.getNombreTabla())) {

				}

				if (respuesta.equals(Constantes.VALOR_UNO)) {
					regInsertados++;
				} else {
					regNoInsertados++;
				}

			}
		}

		if (regInsertados > 0) {
			LOGGER.info("[procesarFicheroEventos] Archivo Descomprimido \t ["
					+ archivoLocal + "] \t PROCESADO en CTRT \t [Tipo Evento]");
			LOGGER.info("[procesarFicheroEventos] Registros insertados CORRECTAMENTE : "
					+ regInsertados);
			LOGGER.info("[procesarFicheroEventos] Registros NO insertados            : "
					+ regNoInsertados);
			return true;
		} else {
			return false;
		}

	}


	/**
	 * Gets the pdt.
	 *
	 * @param fichero the fichero
	 * @return the pdt
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private Pdt getPdt(final String fichero) throws IOException {

		Pdt pdt = new Pdt();

		try {
			String archivo = fichero;
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			String newText = "";
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("<PDT>", "<ns2:PDT xmlns:ns2='PDT'>")
						.trim();
				line = line.replaceAll("</PDT>", "</ns2:PDT>").trim();
				newText = newText + line;
			}
			br.close();
			FileWriter fw = new FileWriter(archivo);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(newText);
			bw.close();

			JAXBContext jaxbContext = JAXBContext.newInstance(Pdt.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			File XMLfile = new File(archivo);
			pdt = (Pdt) jaxbUnmarshaller.unmarshal(XMLfile);

			// Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
			// true);
			// jaxbMarshaller.marshal(pdt, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return pdt;
	}


	/**
	 * Directorio local.
	 *
	 * @param pathDirectorio the path directorio
	 * @return the string[]
	 */
	public String[] directorioLocal(String pathDirectorio) {
		File dir = new File(pathDirectorio);

		String[] ficheros = dir.list();

		if (ficheros == null) {
			LOGGER.info("[listaDirectorio] No hay ficheros en el directorio especificado");
		}

		return ficheros;
	}
}
