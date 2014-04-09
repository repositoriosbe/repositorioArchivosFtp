package cl.bluex.datos;

import java.util.List;

import cl.bluex.entidades.ParametrosGenerales;
import cl.bluex.entidades.Pgen;

/**
 * The Class Parametro.
 */
public class Parametro {

	/** The pgen. */
    private final Pgen pgen;
    
	/**
	 * Instantiates a new parametro.
	 * 
	 * @param pgen
	 *            the pgen
	 */
    public Parametro(Pgen pgen){
        this.pgen = pgen;
    }
    
    /**
     * Busca parametros.
     * @return the hash map
     */
//    public HashMap<String, Object> buscaParametros() {
    public List<ParametrosGenerales> buscaParametros() {
        AccionesBDImpl db = new AccionesBDImpl();
        
            try {
                if (db.conectar()) {
//                    ResultSet rs = db.buscaParametros(this.pgen.getEmpresa().toString(), this.pgen.getIntTipoAplicacion());
//    
//                    HashMap<String, Object> map = new HashMap<String, Object>();
//    
//                    while (rs.next()) {
//                        map.put((String) rs.getObject("PGEN_NMR"), rs.getObject("PGEN_VALOR1"));
//                    }
//                    return map;
                    

            		List<ParametrosGenerales> resultado = db.buscaParametrosGenerales();

            		db.desconectar();
                    
            		return resultado;
                }
//            } catch (SQLException e) {
//                e.printStackTrace();
            } finally {
                db.desconectar();
            }
       
        return null;
    }

}
