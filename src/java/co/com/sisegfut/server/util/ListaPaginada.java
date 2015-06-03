/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class ListaPaginada<T extends Serializable> {

    private int total;
    List<T> lista;

    public ListaPaginada()
    {
        total = 0;
        lista = new ArrayList<T>();
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
