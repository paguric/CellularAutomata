import javax.swing.*;

// Implements Singleton pattern
public class SimulationFrame extends JFrame implements Runnable {
    private static SimulationFrame instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 20;
    private volatile boolean shutdown = false;
    private volatile boolean running = true;
    private static final MainMenu mainMenu = MainMenu.getInstance();
    private static final SimulationPanel simulationPanel = SimulationPanel.getInstance();
    private SimulationFrame() {
        setTitle("Cellular Automata Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static SimulationFrame getInstance() {
        if (instance == null) {
            instance = new SimulationFrame();
        }
        return instance;
    }

    private void update() {

        if (!running) {
            return;
        }

        simulationPanel.setNextGeneration();

        if (simulationPanel.isGenerationComplete()) {
            showMenu();
        }

        repaint();
    }

    @Override
    public void run() {

        showMenu();

        double lastFrameTime = 0.0;
        while (!shutdown) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update();

            try {
                Thread.sleep(FRAMES_PER_SECOND);
            } catch (Exception e) {

            }

        }
    }

    private void showMenu() {

        getContentPane().removeAll();

        add(mainMenu);
        mainMenu.setVisible(true);

        while(mainMenu.isVisible()) {
            try {
                Thread.sleep(FRAMES_PER_SECOND);
            } catch (Exception e) {

            }
        }

        getContentPane().removeAll();

        simulationPanel.reset();
        add(simulationPanel);

        revalidate();
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void close() {
        shutdown = true;
    }

}
