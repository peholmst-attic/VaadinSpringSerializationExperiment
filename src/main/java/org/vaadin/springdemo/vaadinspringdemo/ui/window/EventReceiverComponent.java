/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.springdemo.vaadinspringdemo.ui.window;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author peholmst
 */
@Component
public class EventReceiverComponent extends CustomComponent implements ApplicationListener<EchoEvent> {

    private static final long serialVersionUID = 1L;
    protected static final Logger logger = Logger.getLogger(EventReceiverComponent.class.getName());
    protected static final Level LOGLEVEL = Level.INFO;    
        
    private Label messageLabel;

    public EventReceiverComponent() {
        VerticalLayout layout = new VerticalLayout();
        messageLabel = new Label("No message received");
        layout.addComponent(messageLabel);
        setCompositionRoot(layout);
    }       
    
    @Override
    public void onApplicationEvent(EchoEvent e) {
        logger.log(LOGLEVEL, "Received event {0}", e);
        messageLabel.setValue(String.format("Message \"%s\" received on %s", e.getMessage(), new Date(e.getTimestamp())));
    }
    
    @PostConstruct
    protected void init() {
        logger.log(LOGLEVEL, "Initializing EventReceiverComponent {0}", this);
    }
    
    @PreDestroy
    protected void destroy() {
        logger.log(LOGLEVEL, "Destroying EventReceiverComponent {0}", this);        
    }    
    
}
