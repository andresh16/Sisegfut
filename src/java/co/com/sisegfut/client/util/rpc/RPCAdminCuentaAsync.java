/**
* RPCAdminCuentaAsync
* Versión 1.0
* 15/09/2013
*
* Copyright(c) 2007-2012, Boos IT.
* admin@boos.com.co
*
* http://boos.com.co/license
**/

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Cuentas;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;


/**
 *
 * @author josorio
 */
public interface RPCAdminCuentaAsync extends RPCMaestroAsync<Cuentas>{
    
public void getCuentas(Long IdUserLogin,AsyncCallback<List<Cuentas>> callback);

public void getUpdateCuenta(Cuentas cuenta,AsyncCallback<List<Cuentas>> callback);

public void getUpdateCuentaTransferir(Long idCuenta,Double saldoTrans,AsyncCallback<Void> callback);
}
