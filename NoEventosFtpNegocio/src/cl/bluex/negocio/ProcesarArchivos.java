package cl.bluex.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.log4j.Logger;

import cl.bluex.datos.AccionesBDImpl;
import cl.bluex.entidades.Constantes;
import cl.bluex.entidades.DirectoryFiles;
import cl.bluex.entidades.Oxml;
import cl.bluex.entidades.ParametrosGenerales;

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
     * Descarga desde FTP y posterior procesamiento en OXML 
     *
     * @param pgen the pgen
     * @return true, if successful
     * @throws SocketException the socket exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public boolean comenzar(ParametrosGenerales pgen) throws SocketException, IOException {

		LOGGER.info("[noEventos] Iniciando proceso EMPRESA [" + pgen.getEmpresa() +"]  : "+ Utilidad.getFechaHora());
		
		this.ftp = new AccionesFTPImpl(pgen.getIpServidor(), pgen.getUsuario(), pgen.getClave());
		
		boolean respuesta = false;
		
		try{
		
			// String pathActual = System.getProperty("user.dir");

			String pathActual = Constantes.PATH_INICIAL_NOEVENTOS;

			final String _rutaCarpetaLocal = pathActual	+ pgen.getDirDescargas();
			final String _rutaLocalError = pathActual	+ pgen.getDirErrorLocal();
			final String _rutaLocalBackup = pathActual	+ pgen.getDirBackupLocal();
			final String _rutaProcesadasLocal = pathActual	+ pgen.getDirProcLocal();
			final String _rutaBackupFtp = pgen.getDirBackupFtp();
			final String _rutaErrorFtp = pgen.getDirErrorFtp();

			String _fTPextencionTmp = Constantes.FTP_TMP_EXTENCION;
			
        
        if (ftp.conectar()) {
            
            ftp.setPassiveMode(true);
            
            //Lista directorios FTP 
            List<String> listDirectoriosFTP = ftp.listSubdirNames();
           
            
            List<String> listaArchLocal = null;
            int cantArchivos = 0;
            List<String> listaArchivosNoEventos = new ArrayList<String>();
            
            LOGGER.info("[noEventos] Conectando al FTP           : "  + ftp.getHost());
            LOGGER.info("[noEventos] Lista Directorios FTP (raiz): "  + listDirectoriosFTP);
            LOGGER.info("[noEventos] Ruta descarga local         : "  + _rutaCarpetaLocal);
           
            
            if (listDirectoriosFTP.size() > 0){
            
	            //Copia archivos desde directorio FTP a directoio local
	            for (final String _directorio : listDirectoriosFTP) {
	               
	            	if (!ftp.isConnected()) {
	                    ftp.conectar();
	                }
	            	final DirectoryFiles directoryFiles = new DirectoryFiles();
	            	directoryFiles.setPath(_directorio + File.separator + Constantes.CARPETA_FTP_TO_BE);
	            	List<String> listArchivosFTP = ftp.listFileNamesSub(File.separator +_directorio + File.separator + Constantes.CARPETA_FTP_TO_BE);
	            	LOGGER.info("[noEventos] Descargando desde Directorio : "  + _directorio + "\t" + " - Archivos en directorio => " + listArchivosFTP.size());
	            	listaArchLocal = getFilesFTP(File.separator +_directorio + File.separator + "TO_BE", _rutaCarpetaLocal, _fTPextencionTmp, directoryFiles);
	            	cantArchivos = cantArchivos + listaArchLocal.size();
	            	
	            }
	            
                if (cantArchivos > 0) {
                	
	                    LOGGER.info("[noEventos] Archivos Descargados desde FTP : " + cantArchivos);
	                    String [] archivosLocal = directorioLocal(_rutaCarpetaLocal);
	                    LOGGER.info("[noEventos] Archivos en direcotiro local   : " + archivosLocal.length);
	                    
						for (int i = 0; i < archivosLocal.length; i++) {
	
								listaArchivosNoEventos.add(archivosLocal[i]);
	
						}
						
						procesarNoEventos(listaArchivosNoEventos, _rutaCarpetaLocal, _rutaLocalError, _rutaLocalBackup);
                		enviarArchivoFTP(_rutaLocalBackup, _rutaLocalBackup, _rutaBackupFtp);
                		enviarArchivoFTP(_rutaLocalError, _rutaLocalError, _rutaErrorFtp);
                		copiaCarpetaProcesadas(_rutaLocalBackup, _rutaProcesadasLocal , "_backup_" + pgen.getEmpresa());
                		eliminaArchivosLocal(_rutaLocalBackup);
                		copiaCarpetaProcesadas(_rutaLocalError, _rutaProcesadasLocal, "_error_" + pgen.getEmpresa());
                		eliminaArchivosLocal(_rutaLocalError);
                		
                }else{
                	 LOGGER.info("[noEventos] No se encontraron archivos para descarga. Se desconecta del FTP.");
                }
            }
            	ftp.desconectar();
                LOGGER.info("[noEventos] Proceso terminado EMPRESA [" + pgen.getEmpresa() +"]  : "+ Utilidad.getFechaHora());
                return respuesta = true;
        }else{
            LOGGER.error("[noEventos] Error en la conexion al FTP.");  
            LOGGER.error("[noEventos] Host  :" + ftp.getHost()); 
            LOGGER.error("[noEventos] Usser :" + ftp.getUserName()); 
            LOGGER.error("[noEventos] Proceso terminado EMPRESA [" + pgen.getEmpresa() +"]  : "+ Utilidad.getFechaHora());
        }
        ftp.desconectar();
        LOGGER.info("[noEventos] Proceso terminado EMPRESA [" + pgen.getEmpresa() +"]  : "+ Utilidad.getFechaHora());
        return respuesta;
        
		} catch (final SocketException e) {
			LOGGER.error("[noEventos] Error en la conexion al FTP - " + e.getMessage());  
			LOGGER.error("[noEventos] Host  :" + ftp.getHost()); 
		} catch (final IOException e) {
			LOGGER.error("[noEventos] Error de I/O: " + e.getMessage());
		}
		
		return respuesta;
    }

    
    
	/**
	 * Procesar no eventos.
	 *
	 * @param listaArchivosLocalNoEventos the lista archivos local no eventos
	 * @param _rutaCarpetaLocal the _ruta carpeta local
	 * @param _rutaLocalError the _ruta local error
	 * @param _rutaLocalBackup the _ruta local backup
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void procesarNoEventos(List<String> listaArchivosLocalNoEventos,
			String _rutaCarpetaLocal, String _rutaLocalError,
			String _rutaLocalBackup) throws IOException {

		String nombreArchivo = null;
		boolean respuestaOxml = false;
		int cantOXMLinsertadas = 0;

		if (listaArchivosLocalNoEventos.size() > 0) {
			LOGGER.info("[procesarNoEventos] Descomprimiendo archivos desde : "
					+ _rutaCarpetaLocal);
			for (int i = 0; i < listaArchivosLocalNoEventos.size(); i++) {

				nombreArchivo = ftp.unZipFile(_rutaCarpetaLocal,
						listaArchivosLocalNoEventos.get(i));

				if (nombreArchivo == null
						|| nombreArchivo.equals(Constantes.VACIO)) {

					LOGGER.info("[procesarNoEventos] Archivo con ERROR \t ["	+ listaArchivosLocalNoEventos.get(i) + "] \t [Tipo No Evento]");
					// copia a carpeta error
					boolean resp = copiaProcesadas(_rutaCarpetaLocal, _rutaLocalError, listaArchivosLocalNoEventos.get(i));

					if (resp) {
						File fileZip = new File(_rutaCarpetaLocal + File.separator + listaArchivosLocalNoEventos.get(i));
						fileZip.delete();
					}

				} else {

					respuestaOxml = procesarFicheroNoEventos(nombreArchivo, _rutaCarpetaLocal);

					if (respuestaOxml) {
						LOGGER.info("[procesarNoEventos] Archivo Descomprimido \t ["
								+ nombreArchivo
								+ "] \t INSERTADO en OXML \t [Tipo No Evento]");
						cantOXMLinsertadas++;
						// copia a carpeta backup
						boolean resp = copiaProcesadas(_rutaCarpetaLocal, _rutaLocalBackup, listaArchivosLocalNoEventos.get(i));

						if (resp) {
							File fileZip = new File(_rutaCarpetaLocal + File.separator + listaArchivosLocalNoEventos.get(i));
							File fileTxt = new File(_rutaCarpetaLocal + File.separator + nombreArchivo);
							fileZip.delete();
							fileTxt.delete();
						}

					} else {
						LOGGER.info("[procesarNoEventos] Archivo Descomprimido \t [ "
								+ nombreArchivo
								+ "] \t NO insertado en OXML \t [Tipo No Evento] se envia a carpeta error.");
						// copia a carpeta error
						boolean resp = copiaProcesadas(_rutaCarpetaLocal, _rutaLocalError, listaArchivosLocalNoEventos.get(i));

						if (resp) {
							File fileZip = new File(_rutaCarpetaLocal + File.separator + listaArchivosLocalNoEventos.get(i));
							File fileTxt = new File(_rutaCarpetaLocal + File.separator + nombreArchivo);
							fileZip.delete();
							fileTxt.delete();
						}
					}
				}
			}

			LOGGER.info("[procesarNoEventos] Cantidad de registros Insertados en OXML : "
					+ cantOXMLinsertadas);
		}
	}
	
	
    /**
     * Procesar No eventos.
     *
     * @param archivoLocal the archivo local
     * @param path the path
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
	private boolean procesarFicheroNoEventos(final String archivoLocal, final String path) throws IOException {
		
		boolean respuesta = false;
		final AccionesBDImpl db = new AccionesBDImpl();

		final File archivo = new File(path, archivoLocal);
		final Oxml oxml = getOxml(archivo.getAbsolutePath());
		
		if (oxml.getOxmlxml() != null && !oxml.getOxmlxml().trim().isEmpty()) {
		
			respuesta = db.insertarOxml(oxml);
			
		}
		
		db.desconectar();
		return respuesta;
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
	public void enviarArchivoFTP(final String rutaLocalArchivos, String localFile, String hostFile)	throws FileNotFoundException, IOException {
		
		LOGGER.info("[enviarArchivoFTP] Enviando archivos al FTP carpeta " + hostFile.toUpperCase());
		List<String> listaLocal = ftp.listaArchivosLocal(rutaLocalArchivos+ File.separator + "");
		ftp.conectar();
		for (final String _strFichero : listaLocal) {
			FileInputStream  fis = null;
//			FileOutputStream fos = null;
			fis = new FileInputStream(localFile + File.separator + _strFichero);
			
			
			ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			
			String destinoArchivoFTP = hostFile + File.separator + _strFichero;
			
			if (ftp.storeFile(destinoArchivoFTP , fis)) {
				LOGGER.info("[enviarArchivoFTP] Se envio archivo => " + _strFichero );
			} else{
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
    private List<String> getFilesFTP(final String pathArchivo, final String pathLocal,
            final String FTPextencionTmp, final DirectoryFiles directoryFiles)
            throws IOException {
        
        List<String> listaArchLocal = ftp.descargarArchivosFTP(pathArchivo, pathLocal, FTPextencionTmp);
        directoryFiles.setFilesInDirectory(listaArchLocal);
        return listaArchLocal;
        
        
    }
    

    /**
     * Gets the string archivo.
     * @param fichero the fichero
     * @return the string archivo
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public final static String getStringArchivo(final String fichero) throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader(new File(fichero)));
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
    public void copiaCarpetaProcesadas(String pathOrigen, String pathDestino , String tipo) throws IOException{
       
    	String nombreCapeta = creaNuevaCarpeta(pathDestino , tipo);
        List<String> listaArchivos = ftp.listaArchivosLocal(pathOrigen);
        
        for (int i = 0; i < listaArchivos.size(); i++) {
            try {
                File origen = new File(pathOrigen + File.separator + listaArchivos.get(i));
                File destino = new File(pathDestino + File.separator + nombreCapeta + File.separator
                        + listaArchivos.get(i));
                FileInputStream in = new FileInputStream(origen);
                FileOutputStream out = new FileOutputStream(destino);
                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
                in.close();
                out.close();
            } catch(IOException e) {
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
    public boolean copiaProcesadas(String pathOrigen,
			String pathDestino, String archivo) throws IOException {

		boolean respuesta = false;
		
		try {
			File origen = new File(pathOrigen + File.separator + archivo);
			File destino = new File(pathDestino + File.separator 
					+ File.separator + archivo);
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
			LOGGER.info("[copiaCarpetaProcesadas] Error de entrada/salida!!!");
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
    public String creaNuevaCarpeta(String pathDestino , String tipo){
       
    	Date date = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaConFormato = formato.format(date); 
        String dia = fechaConFormato.substring(0,2);
        String mes = fechaConFormato.substring(3,5);
        String annio = fechaConFormato.substring(6,10);
        
        File directorio = new File(pathDestino + 
        							File.separator + 
        							annio +
        							mes +
        							dia + 
        							tipo);
        directorio.mkdirs();
        return annio+mes+dia+tipo;
    }
    
	/**
	 * Elimina archivos local.
	 * 
	 * @param pathLocal
	 *            the path local
	 */
    public void eliminaArchivosLocal(String pathLocal){
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
                LOGGER.info("[eliminaArchivosLocal] Directorio vacio: " + pathLocal);
            }
        }
    }
    

    /**
     * Gets the oxml.
     * @param fichero the fichero
     * @return the oxml
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private Oxml getOxml(final String fichero) throws IOException {
        final Oxml oxml = new Oxml();

        final String lineIn = getStringArchivo(fichero);

        final String[] tagOp = {"<OP>", "</OP>"};
        final String[] tagID = {"<ID>", "</ID>"};
        final String[] tagPosta = {"<POSTA>", "</POSTA>"};

        final String opPDT = lineIn.substring(lineIn.indexOf(tagOp[0]) + tagOp[1].length() - 1,
                lineIn.indexOf(tagOp[1]));
        final String idSesion = lineIn.substring(lineIn.indexOf(tagID[0]) + tagID[1].length() - 1,
                lineIn.indexOf(tagID[1]));

        final String idPosta = lineIn.substring(lineIn.indexOf(tagPosta[0]) + tagPosta[1].length() - 1,
                lineIn.indexOf(tagPosta[1]));

        oxml.setOxmloperacion(Long.parseLong(opPDT));
        oxml.setOxmlid_sesion(Long.parseLong(idSesion));
        oxml.setOxmlestado("NUEVO");
        oxml.setOxmlprso_cdg_crea("DEMONIO");
        oxml.setOxmlprso_cdg_edita("DEMONIO");
        oxml.setOxmlprioridad(0L);
        oxml.setPstacdg(idPosta);

        oxml.setOxmlxml(lineIn);

        return oxml;
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

		if (ficheros == null){
			LOGGER.info("[listaDirectorio] No hay ficheros en el directorio especificado");
		}

		return ficheros;
	}
}
