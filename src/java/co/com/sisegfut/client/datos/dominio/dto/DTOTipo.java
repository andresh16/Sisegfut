

package co.com.sisegfut.client.datos.dominio.dto;

import java.io.Serializable;

/**
 *
 * @author anfeh_000
 */
public class DTOTipo implements Serializable {

    private int id;
    private String nombre;

    public DTOTipo() {
    }

    public DTOTipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}

