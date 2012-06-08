/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.springdemo.vaadinspringdemo.ejb;

import javax.ejb.Local;

/**
 *
 * @author peholmst
 */
@Local
public interface Echo {
    
    String echo(String message);
}
