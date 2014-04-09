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
    

    
    public List<ParametrosGenerales> buscaParametros() {
        
    	AccionesBDImpl db = new AccionesBDImpl();
        
            try {
            	
                if (db.conectar()) {
                	
             		List<ParametrosGenerales> resultado = db.buscaParametrosGenerales();

            		db.desconectar();
                    
            		return resultado;
                }
                
            } finally {
                db.desconectar();
            }
       
        return null;
    }
    
    

}
