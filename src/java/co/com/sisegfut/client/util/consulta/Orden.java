/**
* Orden
*
* 
*
* 
**/

package co.com.sisegfut.client.util.consulta;

import java.io.Serializable;

/**
 *
 * @author 
 */
public enum Orden implements Serializable{
    ASC("Asc"), DESC("Desc");
    
    private String label;
    
    private Orden(String label) {
        this.label= label;
    }
    
    @Override
    public String toString() {
        return this.label;
    }
}
