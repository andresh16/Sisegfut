/**
 * RPCAdminCuenta Versión 1.0 15/09/2013
 * 
* Copyright(c) 2007-2012, Boos IT. admin@boos.com.co
 * 
* http://boos.com.co/license
*
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Cuentas;
import java.util.List;

/**
 *
 * @author josorio
 */
public interface RPCAdminCuenta extends RPCMaestro<Cuentas> {

    public List<Cuentas> getCuentas(Long IdUserLogin);

    public void getUpdateCuenta(Cuentas cuenta);

    public void getUpdateCuentaTransferir(Long idCuenta, Double saldoTrans);
}
