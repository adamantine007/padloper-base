package com.henko;

import com.henko.container.JettyEmbeddedRunner;

/**
 * Main class of application that start server
 *
 * @see JettyEmbeddedRunner
 * @author Ruslan Kurchenko
 */

public class Launcher {
    public static void main(String[] args) throws Exception {
        new JettyEmbeddedRunner().startServer();
    }
}
