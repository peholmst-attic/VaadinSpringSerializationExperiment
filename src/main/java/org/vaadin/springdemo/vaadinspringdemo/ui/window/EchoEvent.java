/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.springdemo.vaadinspringdemo.ui.window;

import org.springframework.context.ApplicationEvent;

/**
 *
 * @author peholmst
 */
public class EchoEvent extends ApplicationEvent {

    private final String message;

    public EchoEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
