/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.springdemo.vaadinspringdemo.ui.window;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.vaadin.springdemo.vaadinspringdemo.ejb.Echo;

/**
 *
 * @author peholmst
 */
@Component(value="MainWindow")
public class MainWindow extends Window {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Application application;
    @Resource
    private transient ApplicationContext context;
    @Resource
    private transient ApplicationEventPublisher eventPublisher;
    @EJB
    private transient Echo echo;
    @Autowired
    private EventReceiverComponent eventReceiver;
    
    protected static final Logger logger = Logger.getLogger(MainWindow.class.getName());
    protected static final Level LOGLEVEL = Level.INFO;
    
    public MainWindow() {
        super("Spring Demo");
    }

    @PostConstruct
    protected void initComponents() {
        // This method will be called after deserialization, so remember to remove all existing components
        removeAllComponents();
        logger.log(LOGLEVEL, "Initializing MainWindow {0} of application {1}", new Object[] {this, application});
        Label label = new Label("Hello, I'm owned by the application " + application + " and I live in the context " + context);
        addComponent(label);

        final TextField message = new TextField("Message");
        addComponent(message);

        final Button echoButton = new Button("Echo", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                String result = echo.echo((String) message.getValue());
                eventPublisher.publishEvent(new EchoEvent(MainWindow.this, result));
            }
        });
        addComponent(echoButton);
        addComponent(eventReceiver);
    }

    @PreDestroy
    protected void destroy() {
        logger.log(LOGLEVEL, "Destroying MainWindow {0} of application {1}", new Object[] {this, application});        
    }
}
