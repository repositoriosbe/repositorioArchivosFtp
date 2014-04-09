package cl.bluex.datos;

import java.sql.CallableStatement;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import cl.bluex.entidades.Constantes;
import cl.bluex.entidades.eventos.Registro;

/**
 * The Class ValidaEventoDB.
 */
public class ValidaEventoDB {

    /** The Constant LOGGER. */
    static final Logger LOGGER = Logger.getLogger(ValidaEventoDB.class);

//    /** The valida oficina. */
//    private final String VALIDA_OFICINA = "call PKG_REGISTRO_EVENTO2.VALIDA_OFICINA (?,?,?)";
//
//    /** The valida tipo bodega. */
//    private final String VALIDA_TIPO_BODEGA = "call PKG_REGISTRO_EVENTO2.VALIDA_TIPO_BODEGA(?,?)";
//
//    /** The valida bodega. */
//    private final String VALIDA_BODEGA = "call PKG_REGISTRO_EVENTO2.VALIDA_BODEGA_ACTIVA(?,?)";
//
//    /** The valida persona. */
//    private final String VALIDA_PERSONA = "call PKG_REGISTRO_EVENTO2.VALIDA_LOGIN_PRSO(?,?)";
//
//    /** The valida evento. */
//    private final String VALIDA_EVENTO = "call PKG_REGISTRO_EVENTO2.VALIDA_TIPO_EVENTO (?,?)";
//
//    /** The valida exepcion. */
//    private final String VALIDA_EXEPCION = "call PKG_REGISTRO_EVENTO2.VALIDA_TIPO_EXCEPCION(?,?)";
//
//    /** The valida duplicado. */
//    private final String VALIDA_DUPLICADO = "call PKG_REGISTRO_EVENTO2.VALIDA_REGISTRO_DUPLICADO(?,?,?,?,?,?,?,?,?,?)";
//
//    /** The insertar ctrt mov bodega. */
//    private final String INSERTAR_CTRT_MOV_BODEGA = "call PKG_REGISTRO_EVENTO2.INSERTA_CTRT_MOV_BODEGA2(?,?,?,?,?,?,?,?,?,?,?,?)";

    /** The insertar ctrt mov documento. */
    private final String INSERTAR_CTRT_MOV_DOCUMENTO = "call PKG_REGISTRO_EVENTO2.INSERTA_CTRT_MOV_DOCUMENTO2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    /** The Constant LARGO_DOCUMENTO. */
    private static final int LARGO_DOCUMENTO = 10;

    /**
     * Crea registro ctrt documento.
     * @param evento the evento
     * @return the string
     */
    public String creaRegistroCtrtDocumento( Registro evento, int tipoEvento) {
        AccionesBDImpl db = new AccionesBDImpl();
        int intContador = 0;

        if (db.conectar()) {
            try {
            	
                CallableStatement preparedStatement = db.getConnection().prepareCall(INSERTAR_CTRT_MOV_DOCUMENTO);
                switch (tipoEvento) {
                    case 3:
                    	
                        preparedStatement.setString(++intContador, evento.getOFCN_CDG());//getOfcnCdgMd
//                        LOGGER.info("ivofcn_cdg :" + intContador + "-" + evento.getOFCN_CDG());
                        
                        preparedStatement.setString(++intContador,
                                this.extraerCdgEmpresa(String.valueOf(evento.getMVDC_DOCUMENTO())));//getMvdcDocumento
//                        LOGGER.info("ivempr_doc	:"+ intContador + "-" + evento.getMVDC_DOCUMENTO());
                        
                        preparedStatement.setString(++intContador,
                                this.extraerTpoDocumento(String.valueOf(evento.getMVDC_DOCUMENTO())));//getMvdcDocumento
//                        LOGGER.info("ivtpdc_doc	:"+ intContador + "-" + evento.getMVDC_DOCUMENTO());
                        
                        preparedStatement.setString(++intContador,
                                this.extraerFolio(String.valueOf(evento.getMVDC_DOCUMENTO())));//getMvdcDocumento
//                        LOGGER.info("ivfolio_doc	:"+ intContador + "-" + evento.getMVDC_DOCUMENTO());
                        
                        preparedStatement.setString(++intContador, String.valueOf(evento.getMVDC_SEQ()).substring(0, 8));//getMvdcSeq
//                        LOGGER.info("ivfecha	:" + intContador + "-" + String.valueOf(evento.getMVDC_SEQ()).substring(0, 8));
                        
                        preparedStatement.setString(++intContador, String.valueOf(evento.getMVDC_SEQ()).substring(8));//getMvdcSeq
//                        LOGGER.info("ivhora	:"+ intContador + "-" + String.valueOf(evento.getMVDC_SEQ()).substring(8));
                        
                        preparedStatement.setString(++intContador, evento.getOFCN_CDG() + evento.getBDGA_TPO() 
                                + evento.getBDGA_CDG()); //getOfcnCdgMd - getBdgaTpoMd - getBdgaCdgMd
//                        LOGGER.info("ivbdga_cdg :  " + "(getOfcnCdgEd + getBdgaTpoEd + getBdgaCdgEd) :"+ intContador + "-" + evento.getOFCN_CDG() + evento.getBDGA_TPO()
//                                + evento.getBDGA_CDG());
                        

                        if (" ".equals(evento.getEVTO_SWT_EXC_EVT())) { //getEvtoSwtExcEvtMd()
                           
                        	preparedStatement.setString(++intContador, null);
//                            LOGGER.info("ivtpev_cdg	:"+ intContador + "-" + null);
                            
                            preparedStatement.setString(++intContador, null);
//                            LOGGER.info("ivtpex_cdg	:"+ intContador + "-" + null);
                            
                        } else if (evento.getEVTO_SWT_EXC_EVT().equals(Constantes.VALOR_UNO)) { //getEvtoSwtExcEvtMd()
                        	
                            preparedStatement.setString(++intContador, evento.getEVTO_CDG()); //getEvtoCdg()
//                            LOGGER.info("ivtpev_cdg	:"+ intContador + "-" + evento.getEVTO_CDG());
                            
                            preparedStatement.setString(++intContador, Constantes.VACIO);
//                            LOGGER.info("ivtpex_cdg	:"+ intContador + "-" + null);
                            
                        } else {
                            preparedStatement.setString(++intContador, Constantes.VACIO);
//                            LOGGER.info("ivtpev_cdg	:"+ intContador + "-" + null);
                            
                            preparedStatement.setString(++intContador, evento.getEVTO_CDG()); //getEvtoCdg()
//                            LOGGER.info("ivtpex_cdg	:"+ intContador + "-" + evento.getEVTO_CDG()); 
                            
                        }
                        preparedStatement.setString(++intContador, String.valueOf((evento.getMVDC_PIEZAS()))); //getMvdcPiezas()
//                        LOGGER.info("ivcntpza	:" + intContador + "-" + evento.getMVDC_PIEZAS()); 
                        
                        preparedStatement.setString(++intContador, String.valueOf((evento.getMVDC_DOCUMENTO()))); //getMvdcDocumento()
//                        LOGGER.info("ivnmrdoc	:"+ intContador + "-" + evento.getMVDC_DOCUMENTO()); 
                        
                        preparedStatement.setString(++intContador, evento.getPRSO_CDG()); //getPrsoCdgMd()
//                        LOGGER.info("ivprso_cdg	:"+ intContador + "-" + evento.getPRSO_CDG()); 
                        

                        if (evento.getEVTO_CDG().trim().equals("PU")) { //getEvtoCdg()
//                            preparedStatement.setString(++intContador, Integer.toString(evento.getMvdcRsva())); //getMvdcRsva()
                        	preparedStatement.setString(++intContador,  evento.getMVDC_RSVA());
//                        	LOGGER.info("ivglosa	:"+ intContador + "-" + "0"); 	
                        	
                        } else {
                            if (evento.getMVDC_OBSERVACION() != null) { //getMvdcObservacion
                                String subobs = "";
                                final String obs = evento.getMVDC_OBSERVACION().toUpperCase(); //getMvdcObservacion
                                if (obs.length() > 20) {
                                    subobs = obs.substring(0, 20);
                                } else {
                                    subobs = obs;
                                }
                                preparedStatement.setString(++intContador, subobs);
//                                LOGGER.info("ivglosa	:"+ intContador + "-" + subobs); 
                                
                            } else {
                                preparedStatement.setString(++intContador, null);
//                                LOGGER.info("ivglosa	:"+ intContador + "-" + null); 
                                
                            }
                        }
                        
                        
                        if (evento.getMVDC_PIEZAS() == null || evento.getMVDC_PIEZAS().equals(Constantes.VACIO)) {
                        	
                        	evento.setEVDC_PESO(Constantes.VALOR_CERO);
                        	preparedStatement.setString(++intContador, this.formatPeso(String.valueOf(evento.getMVDC_PIEZAS()))); //getMvdcPiezas
                            
                        } else {
                            
                        	preparedStatement.setString(++intContador, this.formatPeso(String.valueOf(evento.getMVDC_PIEZAS()))); //getMvdcPiezas
                        	
                        }
                        
                        break;
                    
                    case 4:

                    	preparedStatement.setString(++intContador, evento.getOFCN_CDG());//getOfcnCdgEd
//                        LOGGER.info("ivofcn_cdg :" + intContador + "-" + evento.getOFCN_CDG());
                        
                        preparedStatement.setString(++intContador,
                                this.extraerCdgEmpresa(String.valueOf(evento.getEVDC_DOCUMENTO()))); //getEvdcDocumento
//                        LOGGER.info("ivempr_doc	:"+ intContador + "-" + evento.getEVDC_DOCUMENTO());
                        
                        preparedStatement.setString(++intContador,
                                this.extraerTpoDocumento(String.valueOf(evento.getEVDC_DOCUMENTO()))); //getEvdcDocumento
//                        LOGGER.info("ivtpdc_doc	:"+ intContador + "-" + evento.getEVDC_DOCUMENTO());
                        
                        preparedStatement.setString(++intContador,
                                this.extraerFolio(String.valueOf(evento.getEVDC_DOCUMENTO()))); //getEvdcDocumento
//                        LOGGER.info("ivfolio_doc	:"+ intContador + "-" + evento.getEVDC_DOCUMENTO());
                        
                        preparedStatement.setString(++intContador, String.valueOf(evento.getEVDC_SEQ()).substring(0, 8));//getEvdcSeq
//                        LOGGER.info("ivfecha	:" + intContador + "-" + String.valueOf(evento.getEVDC_SEQ()).substring(0, 8));
                        
                        preparedStatement.setString(++intContador, String.valueOf(evento.getEVDC_SEQ()).substring(8));//getEvdcSeq
//                        LOGGER.info("ivhora	:"+ intContador + "-" + String.valueOf(evento.getEVDC_SEQ()).substring(8));
                        
                        preparedStatement.setString(++intContador, evento.getOFCN_CDG() + evento.getBDGA_TPO()
                                + evento.getBDGA_CDG()); //getOfcnCdgEd  getBdgaTpoEd  getBdgaCdgEd
//                        LOGGER.info("ivbdga_cdg :  " + "(getOfcnCdgEd + getBdgaTpoEd + getBdgaCdgEd) :"+ intContador + "-" + evento.getOFCN_CDG() + evento.getBDGA_TPO()
//                                + evento.getBDGA_CDG());

                        if (" ".equals(evento.getEVTO_SWT_EXC_EVT())) { //getEvtoSwtExcEvtEd
                            preparedStatement.setString(++intContador, null);
//                            LOGGER.info("ivtpev_cdg	:"+ intContador + "-" + null);
                            preparedStatement.setString(++intContador, null);
//                            LOGGER.info("ivtpex_cdg	:"+ intContador + "-" + null);
                            
                        } else if (Constantes.VALOR_UNO.equals(evento.getEVTO_SWT_EXC_EVT())) { //getEvtoSwtExcEvtEd
                            preparedStatement.setString(++intContador, evento.getEVTO_CDG()); //getEvtoCdgEd
//                            LOGGER.info("ivtpev_cdg	:"+ intContador + "-" + evento.getEVTO_CDG());
                            preparedStatement.setString(++intContador, null);
//                            LOGGER.info("ivtpex_cdg	:"+ intContador + "-" + null);
                        } else {
                            preparedStatement.setString(++intContador, null);
//                            LOGGER.info("ivtpev_cdg	:"+ intContador + "-" + null);
                            
                            preparedStatement.setString(++intContador, evento.getEVTO_CDG()); //getEvtoCdgEd
//                            LOGGER.info("ivtpex_cdg	:"+ intContador + "-" + evento.getEVTO_CDG()); 
                            
                        }
                        preparedStatement.setString(++intContador, String.valueOf(evento.getEVDC_PIEZAS()));//getEvdcPiezas
//                        LOGGER.info("ivcntpza	:" + intContador + "-" + evento.getEVDC_PIEZAS()); 
                        
                        preparedStatement.setString(++intContador, String.valueOf(evento.getEVDC_DOCUMENTO())); //getEvdcDocumento
//                        LOGGER.info("ivnmrdoc	:"+ intContador + "-" + evento.getEVDC_DOCUMENTO()); 
                        
                        preparedStatement.setString(++intContador, evento.getPRSO_CDG()); //getPrsoCdgEd
//                        LOGGER.info("ivprso_cdg	:"+ intContador + "-" + evento.getPRSO_CDG()); 

                        if ("PU".equals(evento.getEVTO_CDG().trim())) { //getEvtoCdgEd
//                            preparedStatement.setString(++intContador, Integer.toString(evento.getEvdcRsva())); //getEvdcRsva
                        	preparedStatement.setString(++intContador, evento.getEVDC_RSVA()); 
//                        	LOGGER.info("ivglosa	:"+ intContador + "-" + "0"); 
                        	

                        	
                        } else {

                            if (evento.getEVDC_OBSERVACION() != null) { //getEvdcObservacion
                                final String obs = evento.getEVDC_OBSERVACION().toUpperCase(); //getEvdcObservacion
                                String subobs;
                                if (obs.length() > 20) {
                                    subobs = obs.substring(0, 20);
                                } else {
                                    subobs = obs;
                                }
                                preparedStatement.setString(++intContador, subobs);
//                                LOGGER.info("ivglosa	:"+ intContador + "-" + subobs); 
                                
                            } else {
                                preparedStatement.setString(++intContador, null);
//                                LOGGER.info("ivglosa	:"+ intContador + "-" + null); 
                                
                            }
                        }

                        if (evento.getEVDC_PESO() == null || evento.getEVDC_PESO().trim().equals(Constantes.VACIO)) {
                        	
                        	evento.setEVDC_PESO(Constantes.VALOR_CERO);
                        	preparedStatement.setString(++intContador, this.formatPeso(String.valueOf(evento.getEVDC_PESO()))); //getEvdcPeso
                            
                        } else {
                            
                        	preparedStatement.setString(++intContador, this.formatPeso(String.valueOf(evento.getEVDC_PESO()))); //getEvdcPeso
                        	
                        }
                        
                        break;
                }
//                preparedStatement.setLong(++intContador, evento.getEmpresa()); //getEmpresa
                preparedStatement.setLong(++intContador, 2000L); //getEmpresa
//                switch (evento.getTpoEvt()) { //getTpoEvt
//                LOGGER.info("ivempresacdg	:"+ intContador + "-" + "2000L");
                
                	switch (tipoEvento) { //getTpoEvt
                    case 3:
                    	
                        preparedStatement.setString(++intContador, evento.getBDGA_TPO()); //getBdgaTpoMd
//                        LOGGER.info("itipobodega	:"+ intContador + "-" +  evento.getBDGA_TPO()); 
                        
                        preparedStatement.setString(++intContador, evento.getBDGA_CDG()); //getBdgaCdgMd
//                        LOGGER.info("ibodegacdg	:"+ intContador + "-" +  evento.getBDGA_CDG()); 
                        
                        break;
                    case 4:
                    	
                        preparedStatement.setString(++intContador, evento.getBDGA_TPO()); //getBdgaTpoEd
//                        LOGGER.info("itipobodega	:"+ intContador + "-" +  evento.getBDGA_TPO()); 
                        
                        preparedStatement.setString(++intContador, evento.getBDGA_CDG()); //getBdgaCdgEd
//                        LOGGER.info("ibodegacdg	:"+ intContador + "-" +  evento.getBDGA_CDG()); 
                        
                        break;
                	}

                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                LOGGER.info("oresultado	:"+ intContador ); 
                preparedStatement.registerOutParameter(++intContador, OracleTypes.CHAR);
//                LOGGER.info("osqlmsjerror	:"+ intContador ); 
                
                preparedStatement.execute();
                //Long lgSalida = (Long) preparedStatement.getLong(--intContador);

                return preparedStatement.getString(--intContador);

            } catch (Exception ex) {
                LOGGER.error(ex.getMessage().concat(" al insertar el documento "));
            } finally {
                db.desconectar();
            }
        }
        return "";
    }

    
//    /**
//   * Crea registro ctrt bodega.
//   * @param evento the evento
//   * @return the string
//   */
//  public String creaRegistroCtrtBodega( Registro evento) {
//      AccionesBDImpl db = new AccionesBDImpl();
//      int intContador = 0;
//
//      if (db.conectar()) {
//          try {
//              CallableStatement preparedStatement = db.getConnection().prepareCall(INSERTAR_CTRT_MOV_BODEGA);
//
//              switch (evento.getTpoEvt()) {
//                  case 1:
//                      preparedStatement.setString(++intContador, evento.getOfcnCdg());
//                      preparedStatement.setString(++intContador,
//                              evento.getOfcnCdg() + evento.getBdgaTpo() + evento.getBdgaCdg());
//                      preparedStatement.setString(++intContador, String.valueOf(evento.getMvbgSeq()).substring(0, 8));
//                      preparedStatement.setString(++intContador, String.valueOf(evento.getMvbgSeq()).substring(8));
//                      preparedStatement.setString(++intContador,
//                              evento.getOfcnCdgDestino() + evento.getBdgaTpoDestino() + evento.getBdgaCdgDestino());
//                      preparedStatement.setString(++intContador, Constantes.VACIO);
//                      preparedStatement.setString(++intContador, null);
//                      preparedStatement.setString(++intContador, null);
//                      preparedStatement.setString(++intContador, evento.getPrsoCdg());
//
//                      if (evento.getMvbgObservacion() != null) {
//                          final String obs = evento.getMvbgObservacion().toUpperCase().trim();
//                          String subobs = (obs.length() > 20) ? obs.substring(0, 18) : obs;
//                          preparedStatement.setString(++intContador, subobs);
//                      } else {
//                          preparedStatement.setString(++intContador, Constantes.VACIO);
//                      }
//                      break;
//                  case 2:
//                      preparedStatement.setString(++intContador, evento.getOfcnCdgEb());
//                      preparedStatement.setString(++intContador, evento.getOfcnCdgEb() + evento.getBdgaTpoEb()
//                              + evento.getBdgaCdgEb());
//                      preparedStatement.setString(++intContador, String.valueOf(evento.getEvbgSeq()).substring(0, 8));
//                      preparedStatement.setString(++intContador, String.valueOf(evento.getEvbgSeq()).substring(8));
//                      preparedStatement.setString(
//                              ++intContador,
//                              evento.getOfcnCdgDestinoEb() + evento.getBdgaTpoDestinoEb()
//                                      + evento.getBdgaCdgDestinoEb());
//                      preparedStatement.setString(++intContador, Constantes.VACIO);
//
//                      if (" ".equals(evento.getEvtoSwtExcEvt())) {
//                          preparedStatement.setString(++intContador, null);
//                          preparedStatement.setString(++intContador, null);
//                      } else if (evento.getEvtoSwtExcEvt().equals(Constantes.VALOR_UNO)) {
//                          preparedStatement.setString(++intContador, evento.getEvtoCdgEb());
//                          preparedStatement.setString(++intContador, null);
//                      } else {
//                          preparedStatement.setString(++intContador, null);
//                          preparedStatement.setString(++intContador, evento.getEvtoCdgEb());
//                      }
//                      preparedStatement.setString(++intContador, evento.getPrsoCdgEb());
//
//                      if (evento.getEvbgObservacion() != null) {
//                          final String obs = evento.getEvbgObservacion().toUpperCase().trim();
//                          String subobs = (obs.length() > 20) ? obs.substring(0, 18) : obs;
//                          preparedStatement.setString(++intContador, subobs);
//                      } else {
//                          preparedStatement.setString(++intContador, Constantes.VACIO);
//                      }
//
//                      break;
//              }
//              preparedStatement.setLong(++intContador, evento.getEmpresa());
//
//              switch (evento.getTpoEvt()) {
//                  case 1:
//                      preparedStatement.setString(++intContador, evento.getBdgaTpo());
//                      preparedStatement.setString(++intContador, evento.getBdgaCdg());
//                      break;
//                  case 2:
//                      preparedStatement.setString(++intContador, evento.getBdgaTpoEb());
//                      preparedStatement.setString(++intContador, evento.getBdgaCdgEb());
//                      break;
//              }
//
//              preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//              preparedStatement.registerOutParameter(++intContador, OracleTypes.CHAR);
//              preparedStatement.execute();
//              Long lgSalida = (Long) preparedStatement.getLong(--intContador);
//              if (lgSalida == 1)
//                  return preparedStatement.getString(intContador);
//              else {
//                  String strError = preparedStatement.getString(++intContador);
//                  LOGGER.error(strError.concat(" al insertar el documento bodega"));
//              }
//          } catch (Exception ex) {
//              LOGGER.error(ex.getMessage().concat(" al insertar el documento bodega"));
//          } finally {
//              db.desconectar();
//          }
//      }
//      return "";
//  }

    
    
//    /**
//     * Valida oficina.
//     * @param evento the evento
//     * @param empresa the empresa
//     * @param isOrigen the is origen
//     * @return the hash map
//     */
//    public Long validaOficina(final Registro evento, Integer empresa, boolean isOrigen) {
//        AccionesBDImpl db = new AccionesBDImpl();
//        Long lgSalida = 0L;
//        if (db.conectar()) {
//            try {
//                int intContador = 0;
//                final CallableStatement preparedStatement = db.getConnection().prepareCall(VALIDA_OFICINA);
//
//                if (isOrigen) {
//                    switch (evento.getTpoEvt()) {
//                        case 1:
//                            preparedStatement.setString(++intContador, evento.getOFCN_CDG());
//                            break;
//                        case 2:
//                            preparedStatement.setString(++intContador, evento.getOfcnCdgEb());
//                            break;
//                        case 3:
//                            preparedStatement.setString(++intContador, evento.getOfcnCdgMd());
//                            break;
//                        case 4:
//                            preparedStatement.setString(++intContador, evento.getOfcnCdgEd());
//                            break;
//                    }
//                } else {
//                    switch (evento.getTpoEvt()) {
//                        case 1:
//                            preparedStatement.setString(++intContador, evento.getOfcnCdgDestino());
//                            break;
//                        case 2:
//                            preparedStatement.setString(++intContador, evento.getOfcnCdgDestinoEb());
//                            break;
//                    }
//                }
//
//                preparedStatement.setInt(++intContador, empresa);
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                preparedStatement.execute();
//                lgSalida = (Long) preparedStatement.getLong(intContador);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                db.desconectar();
//            }
//        }
//        return lgSalida;
//    }
//    

//    /**
//     * Valida tipo bodega.
//     * @param evento the evento
//     * @param isOrigen the is origen
//     * @return the hash map
//     */
//    public Long validaTipoBodega(final RegRapEventoVO evento, boolean isOrigen) {
//        AccionesBDImpl db = new AccionesBDImpl();
//        Long lgSalida = 0L;
//        if (db.conectar()) {
//            try {
//                int intContador = 0;
//                final CallableStatement preparedStatement = db.getConnection().prepareCall(VALIDA_TIPO_BODEGA);
//
//                if (isOrigen) {
//                    switch (evento.getTpoEvt()) {
//                        case 1:
//                            preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpo()));
//                            break;
//                        case 2:
//                            preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoEb()));
//                            break;
//                        case 3:
//                            preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoMd()));
//                            break;
//                        case 4:
//                            preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoEd()));
//                            break;
//                    }
//                } else {
//                    switch (evento.getTpoEvt()) {
//                        case 1:
//                            preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoDestino()));
//                            break;
//                        case 2:
//                            preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoDestinoEb()));
//                            break;
//                    }
//                }
//
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                preparedStatement.execute();
//                lgSalida = (Long) preparedStatement.getLong(intContador);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                db.desconectar();
//            }
//        }
//        return lgSalida;
//    }
//
//    /**
//     * Valida bodega.
//     * @param evento the evento
//     * @param isOrigen the is origen
//     * @return the hash map
//     */
//    public Long validaBodega(final RegRapEventoVO evento, boolean isOrigen) {
//        AccionesBDImpl db = new AccionesBDImpl();
//        Long lgSalida = 0L;
//        if (db.conectar()) {
//            try {
//                int intContador = 0;
//                final CallableStatement preparedStatement = db.getConnection().prepareCall(VALIDA_BODEGA);
//
//                if (isOrigen) {
//                    switch (evento.getTpoEvt()) {
//                        case 1:
//                            preparedStatement.setString(++intContador, evento.getBdgaCdg());
//                            break;
//                        case 2:
//                            preparedStatement.setString(++intContador, evento.getBdgaCdgEb());
//                            break;
//                        case 3:
//                            preparedStatement.setString(++intContador, evento.getBdgaCdgMd());
//                            break;
//                        case 4:
//                            preparedStatement.setString(++intContador, evento.getBdgaCdgEd());
//                            break;
//                    }
//                } else {
//                    switch (evento.getTpoEvt()) {
//                        case 1:
//                            preparedStatement.setString(++intContador, evento.getBdgaCdgDestino());
//                            break;
//                        case 2:
//                            preparedStatement.setString(++intContador, evento.getBdgaCdgDestinoEb());
//                            break;
//                    }
//                }
//
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                preparedStatement.execute();
//                lgSalida = (Long) preparedStatement.getLong(intContador);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                db.desconectar();
//            }
//        }
//        return lgSalida;
//    }
//
//    /**
//     * Valida persona.
//     * @param evento the evento
//     * @return the hash map
//     */
//    public Long validaPersona(final RegRapEventoVO evento) {
//        AccionesBDImpl db = new AccionesBDImpl();
//        Long lgSalida = 0L;
//        if (db.conectar()) {
//            try {
//                int intContador = 0;
//                final CallableStatement preparedStatement = db.getConnection().prepareCall(VALIDA_PERSONA);
//
//                switch (evento.getTpoEvt()) {
//                    case 1:
//                        preparedStatement.setString(++intContador, evento.getPrsoCdg());
//                        break;
//                    case 2:
//                        preparedStatement.setString(++intContador, evento.getPrsoCdgEb());
//                        break;
//                    case 3:
//                        preparedStatement.setString(++intContador, evento.getPrsoCdgMd());
//                        break;
//                    case 4:
//                        preparedStatement.setString(++intContador, evento.getPrsoCdgEd());
//                        break;
//                }
//
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                preparedStatement.execute();
//                lgSalida = (Long) preparedStatement.getLong(intContador);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                db.desconectar();
//            }
//        }
//        return lgSalida;
//    }
//
//    /**
//     * Valida evento excepcion.
//     * @param evento the evento
//     * @return the hash map
//     */
//    public Long validaEventoExcepcion(final RegRapEventoVO evento) {
//        AccionesBDImpl db = new AccionesBDImpl();
//        Long lgSalida = 0L;
//        if (db.conectar()) {
//            try {
//                int intContador = 0;
//
//                String strTipoEvtoExcep = null;
//                boolean isEvento = true;
//
//                switch (evento.getTpoEvt()) {
//                    case 2:
//                        strTipoEvtoExcep = evento.getEvtoCdgEb();
//                        isEvento = evento.getEvtoSwtExcEvt().equals(Constantes.VALOR_UNO);
//                        break;
//                    case 3:
//                        strTipoEvtoExcep = evento.getEvtoCdg();
//                        isEvento = evento.getEvtoSwtExcEvtMd().equals(Constantes.VALOR_UNO);
//                        break;
//                    case 4:
//                        strTipoEvtoExcep = evento.getEvtoCdgEd();
//                        isEvento = evento.getEvtoSwtExcEvtEd().equals(Constantes.VALOR_UNO);
//                        break;
//                    default:
//                        break;
//                }
//                CallableStatement preparedStatement = null;
//                if (isEvento) {
//                    preparedStatement = db.getConnection().prepareCall(VALIDA_EVENTO);
//                } else {
//                    preparedStatement = db.getConnection().prepareCall(VALIDA_EXEPCION);
//                }
//
//                preparedStatement.setString(++intContador, strTipoEvtoExcep);
//
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                preparedStatement.execute();
//                lgSalida = (Long) preparedStatement.getLong(intContador);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                db.desconectar();
//            }
//        }
//        return lgSalida;
//    }
//
//    /**
//     * Valida evento duplicado.
//     * @param evento the evento
//     * @param strFecha the str fecha
//     * @return true, if successful
//     */
//    public boolean validaEventoDuplicado(RegRapEventoVO evento, String strFecha) {
//        AccionesBDImpl db = new AccionesBDImpl();
//        int intContador = 0;
//
//        String fechaAux = strFecha;
//        if (db.conectar()) {
//            try {
//                CallableStatement preparedStatement = db.getConnection().prepareCall(VALIDA_DUPLICADO);
//                switch (evento.getTpoEvt()) {
//                    case 1:
//                        preparedStatement.setString(++intContador, null);
//                        preparedStatement.setString(++intContador, null);
//                        preparedStatement.setString(++intContador, fechaAux);
//                        preparedStatement.setString(++intContador, evento.getPrsoCdg());
//                        break;
//                    case 2:
//                        if (evento.getEvtoSwtExcEvt().equals(Constantes.VALOR_UNO)) {
//                            preparedStatement.setString(++intContador, evento.getEvtoCdgEb());
//                            preparedStatement.setString(++intContador, null);
//                        } else if (evento.getEvtoSwtExcEvt().equals("2")) {
//                            preparedStatement.setString(++intContador, null);
//                            preparedStatement.setString(++intContador, evento.getEvtoCdgEb());
//                        }
//                        preparedStatement.setString(++intContador, fechaAux);
//                        preparedStatement.setString(++intContador, evento.getPrsoCdgEb());
//                        break;
//                    case 3:
//                        if (evento.getEvtoSwtExcEvtMd().equals(Constantes.VALOR_UNO)) {
//                            preparedStatement.setString(++intContador, evento.getEvtoCdg());
//                            preparedStatement.setString(++intContador, null);
//                        } else if (evento.getEvtoSwtExcEvtMd().equals("2")) {
//                            preparedStatement.setString(++intContador, null);
//                            preparedStatement.setString(++intContador, evento.getEvtoCdg());
//                        }
//                        preparedStatement.setString(++intContador, fechaAux);
//                        preparedStatement.setString(++intContador, evento.getPrsoCdgMd());
//                        break;
//                    case 4:
//                        if (evento.getEvtoSwtExcEvtEd().equals(Constantes.VALOR_UNO)) {
//                            preparedStatement.setString(++intContador, evento.getEvtoCdgEd());
//                            preparedStatement.setString(++intContador, null);
//                        } else if (evento.getEvtoSwtExcEvtEd().equals("2")) {
//                            preparedStatement.setString(++intContador, null);
//                            preparedStatement.setString(++intContador, evento.getEvtoCdgEd());
//                        }
//                        preparedStatement.setString(++intContador, fechaAux);
//                        preparedStatement.setString(++intContador, evento.getPrsoCdgEd());
//                        break;
//                }
//
//                switch (evento.getTpoEvt()) {
//                    case 1:
//                    case 2:
//                        preparedStatement.setLong(++intContador, new Long(0));
//                        break;
//                    case 3:
//                        preparedStatement.setLong(++intContador, new Long(evento.getMvdcDocumento()));
//                        break;
//                    case 4:
//                        preparedStatement.setLong(++intContador, new Long(evento.getEvdcDocumento()));
//                        break;
//                }
//
//                switch (evento.getTpoEvt()) {
//                    case 1:
//                        preparedStatement.setString(++intContador, evento.getOfcnCdgDestino());// ofcnCdgDestino
//                        preparedStatement.setString(++intContador, evento.getBdgaCdgDestino());
//                        preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoDestino()));
//                        break;
//                    case 2:
//                        preparedStatement.setString(++intContador, evento.getOfcnCdgDestinoEb());// ofcnCdgDestinoEb
//                        preparedStatement.setString(++intContador, evento.getBdgaCdgDestinoEb());
//                        preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoDestinoEb()));
//                        break;
//                    case 3:
//                        preparedStatement.setString(++intContador, evento.getOfcnCdgMd());// ofcnCdgMd
//                        preparedStatement.setString(++intContador, evento.getBdgaCdgMd());
//                        preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoMd()));
//                        break;
//                    case 4:
//                        preparedStatement.setString(++intContador, evento.getOfcnCdgEd());// OfcnCdgDestinoEb
//                        preparedStatement.setString(++intContador, evento.getBdgaCdgEd());
//                        preparedStatement.setInt(++intContador, Integer.valueOf(evento.getBdgaTpoEd()));
//                        break;
//                }
//
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.CHAR);
//                preparedStatement.execute();
//                Long lgSalida = (Long) preparedStatement.getLong(--intContador);
//                if (lgSalida == 1)
//                    return true;
//                else {
//                    String strError = preparedStatement.getString(++intContador);
//                    LOGGER.error(strError.concat(" al validar evento duplicado"));
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                db.desconectar();
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Crea registro ctrt bodega.
//     * @param evento the evento
//     * @return the string
//     */
//    public String creaRegistroCtrtBodega(RegRapEventoVO evento) {
//        AccionesBDImpl db = new AccionesBDImpl();
//        int intContador = 0;
//
//        if (db.conectar()) {
//            try {
//                CallableStatement preparedStatement = db.getConnection().prepareCall(INSERTAR_CTRT_MOV_BODEGA);
//
//                switch (evento.getTpoEvt()) {
//                    case 1:
//                        preparedStatement.setString(++intContador, evento.getOfcnCdg());
//                        preparedStatement.setString(++intContador,
//                                evento.getOfcnCdg() + evento.getBdgaTpo() + evento.getBdgaCdg());
//                        preparedStatement.setString(++intContador, String.valueOf(evento.getMvbgSeq()).substring(0, 8));
//                        preparedStatement.setString(++intContador, String.valueOf(evento.getMvbgSeq()).substring(8));
//                        preparedStatement.setString(++intContador,
//                                evento.getOfcnCdgDestino() + evento.getBdgaTpoDestino() + evento.getBdgaCdgDestino());
//                        preparedStatement.setString(++intContador, Constantes.VACIO);
//                        preparedStatement.setString(++intContador, null);
//                        preparedStatement.setString(++intContador, null);
//                        preparedStatement.setString(++intContador, evento.getPrsoCdg());
//
//                        if (evento.getMvbgObservacion() != null) {
//                            final String obs = evento.getMvbgObservacion().toUpperCase().trim();
//                            String subobs = (obs.length() > 20) ? obs.substring(0, 18) : obs;
//                            preparedStatement.setString(++intContador, subobs);
//                        } else {
//                            preparedStatement.setString(++intContador, Constantes.VACIO);
//                        }
//                        break;
//                    case 2:
//                        preparedStatement.setString(++intContador, evento.getOfcnCdgEb());
//                        preparedStatement.setString(++intContador, evento.getOfcnCdgEb() + evento.getBdgaTpoEb()
//                                + evento.getBdgaCdgEb());
//                        preparedStatement.setString(++intContador, String.valueOf(evento.getEvbgSeq()).substring(0, 8));
//                        preparedStatement.setString(++intContador, String.valueOf(evento.getEvbgSeq()).substring(8));
//                        preparedStatement.setString(
//                                ++intContador,
//                                evento.getOfcnCdgDestinoEb() + evento.getBdgaTpoDestinoEb()
//                                        + evento.getBdgaCdgDestinoEb());
//                        preparedStatement.setString(++intContador, Constantes.VACIO);
//
//                        if (" ".equals(evento.getEvtoSwtExcEvt())) {
//                            preparedStatement.setString(++intContador, null);
//                            preparedStatement.setString(++intContador, null);
//                        } else if (evento.getEvtoSwtExcEvt().equals(Constantes.VALOR_UNO)) {
//                            preparedStatement.setString(++intContador, evento.getEvtoCdgEb());
//                            preparedStatement.setString(++intContador, null);
//                        } else {
//                            preparedStatement.setString(++intContador, null);
//                            preparedStatement.setString(++intContador, evento.getEvtoCdgEb());
//                        }
//                        preparedStatement.setString(++intContador, evento.getPrsoCdgEb());
//
//                        if (evento.getEvbgObservacion() != null) {
//                            final String obs = evento.getEvbgObservacion().toUpperCase().trim();
//                            String subobs = (obs.length() > 20) ? obs.substring(0, 18) : obs;
//                            preparedStatement.setString(++intContador, subobs);
//                        } else {
//                            preparedStatement.setString(++intContador, Constantes.VACIO);
//                        }
//
//                        break;
//                }
//                preparedStatement.setLong(++intContador, evento.getEmpresa());
//
//                switch (evento.getTpoEvt()) {
//                    case 1:
//                        preparedStatement.setString(++intContador, evento.getBdgaTpo());
//                        preparedStatement.setString(++intContador, evento.getBdgaCdg());
//                        break;
//                    case 2:
//                        preparedStatement.setString(++intContador, evento.getBdgaTpoEb());
//                        preparedStatement.setString(++intContador, evento.getBdgaCdgEb());
//                        break;
//                }
//
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.NUMBER);
//                preparedStatement.registerOutParameter(++intContador, OracleTypes.CHAR);
//                preparedStatement.execute();
//                Long lgSalida = (Long) preparedStatement.getLong(--intContador);
//                if (lgSalida == 1)
//                    return preparedStatement.getString(intContador);
//                else {
//                    String strError = preparedStatement.getString(++intContador);
//                    LOGGER.error(strError.concat(" al insertar el documento bodega"));
//                }
//            } catch (Exception ex) {
//                LOGGER.error(ex.getMessage().concat(" al insertar el documento bodega"));
//            } finally {
//                db.desconectar();
//            }
//        }
//        return "";
//    }

    
    /**
     * Extraer cdg empresa.
     * @param documento the documento
     * @return the string
     */
    private String extraerCdgEmpresa(final String documento) {
        String retorno = Constantes.VACIO;
        if (documento.length() > LARGO_DOCUMENTO) {
            retorno = documento.substring(0, 1);
        }
        return retorno;
    }

    /**
     * Extraer tpo documento.
     * @param documento the documento
     * @return the string
     */
    private String extraerTpoDocumento(final String documento) {
        final String retorno = Constantes.VACIO;
        if (documento.length() > LARGO_DOCUMENTO) {
            return documento.substring(1, 2);
        }
        return retorno;
    }

    /**
     * Extraer folio.
     * @param documento the documento
     * @return the string
     */
    private String extraerFolio(final String documento) {
        String retorno = documento;
        if (documento.length() > LARGO_DOCUMENTO) {
            retorno = documento.substring(2);
        }
        return retorno;
    }

    /**
     * Format peso.
     * @param peso the peso
     * @return the string
     */
    private final String formatPeso(final String peso) {
        return peso.replace('.', ',');
    }
}
