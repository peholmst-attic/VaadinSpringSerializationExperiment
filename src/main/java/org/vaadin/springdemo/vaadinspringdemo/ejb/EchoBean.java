/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.springdemo.vaadinspringdemo.ejb;

import javax.ejb.Stateless;

/**
 *
 * @author peholmst
 */
@Stateless
public class EchoBean implements Echo {

    @Override
    public String echo(String message) {
        return message;
    }
    
}
