package com.henko.container;

import com.henko.controller.PersonController;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * This class uses Jetty Servlet Container to start the Server in embedded mode.
 * Clients must not build .war files to start application.
 *
 * There are few steps to run:
 *  1. Create server.
 *  2. Create ServerConnector and set idle timeout, accept queue size, host and port,
 *     and ofc. set this connector to server.
 *  3. Create handler that will keep ContextHandlerCollection, with Servlets, and Resource Handler,
 *     with base of your WEB resources.
 *  4. Bound handlers with server and start it.
 *
 *  @see Server
 *  @see HandlerList
 *  @see ContextHandlerCollection
 *  @see ervletContextHandler
 *  @see ServletHolder
 *  @see PersonController
 *
 *  @author Ruslan Kurchenko
 */

public class JettyEmbeddedRunner {
    public void startServer() {
        try {
            Server server = new Server();

            // Connectors
            ServerConnector connector = new ServerConnector(server);
            connector.setIdleTimeout(1000);
            connector.setAcceptQueueSize(10);
            connector.setPort(8080);
            connector.setHost("localhost");
            server.setConnectors(new Connector[] { connector });

            // Handlers
            HandlerList handlers = new HandlerList();

            ContextHandlerCollection contexts = new ContextHandlerCollection();
            ServletContextHandler rootContext = new ServletContextHandler(contexts, "/", true, false);
            rootContext.addServlet(new ServletHolder(PersonController.class), "/person.do");

            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setResourceBase("./src/main/webapp");

            handlers.setHandlers(new Handler[] { resourceHandler, contexts });
            server.setHandler(handlers);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
