package org.jboss.weld.environment.servlet.test.bootstrap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.jboss.weld.environment.servlet.test.bootstrap.EventHolder.events;

public class MyServletContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }

    public void contextInitialized(ServletContextEvent sce) {
        events.add(sce);
    }

}
