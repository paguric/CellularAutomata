import javax.swing.*;

// Implements Singleton pattern
public class SimulationFrame extends JFrame implements Runnable {
    private static SimulationFrame instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private volatile boolean shutdown = false;

    private SimulationFrame() {
        setTitle("Cellular Automaton");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        // add components here
        add(MainMenu.getInstance());

        setVisible(true);
    }

    public static SimulationFrame getInstance() {
        if (instance == null) {
            instance = new SimulationFrame();
        }
        return instance;
    }

    private void update(double deltaTime) {

    }

    @Override
    public void run() {

        double lastFrameTime = 0.0;
        while (!shutdown) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }

        }
    }
}
