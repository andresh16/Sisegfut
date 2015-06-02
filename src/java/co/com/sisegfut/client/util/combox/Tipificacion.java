/**
 * Tipificacion
 * Versión 1.0
 * 5/09/2013
 * Copyright(c) 2007-2013, Boos IT.
 * admin@boos.com.co
 *
 * http://boos.com.co/license
 **/

package co.com.sisegfut.client.util.combox;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

/**
 *
 * @author josorio
 */
public class Tipificacion extends BaseTreeModel {

        public Tipificacion() {
        }

        public Tipificacion(String name, int id) {
            set("name", name);
            set("id", id);
        }
        
        public Tipificacion(String name, int id, long idL) {
            set("name", name);
            set("id", id);
            set("idL", idL);
        }
        
        public Tipificacion(String name, int id, long idL, String adicional) {
            set("name", name);
            set("id", id);
            set("idL", idL);
            set("adicional", adicional);
        }

        public Integer getId() {
            return (Integer) get("id");
        }
        
        public Long getIdL() {
            return (Long) get("idL");
        }

        public String getName() {
            return (String) get("name");
        }
        
        public String getAdicional() {
            return (String) get("adicional");
        }

        public String toString() {
            return getName();
        }
    }

