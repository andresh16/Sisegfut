/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.comm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(
{
    ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface GwtRpcEndPoint
{
    String value() default "";
}
