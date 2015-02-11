import com.henko.container.JettyEmbeddedRunner;

public class Launcher {
    public static void main(String[] args) throws Exception {
        new JettyEmbeddedRunner().startServer();
    }
}
