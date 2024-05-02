import javax.swing.*;
import java.awt.event.ActionListener;

// Implements Singleton pattern
public class SimulationFrame extends JFrame implements Runnable {
    private static SimulationFrame instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int FRAMES_PER_SECOND = 30;
    private volatile boolean shutdown = false;
    private volatile boolean paused = false;
    private static MainMenu mainMenu = MainMenu.getInstance();
    private SimulationFrame() {
        setTitle("Cellular Automata Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        // add components here
        add(mainMenu);

        setVisible(true);
    }

    public static SimulationFrame getInstance() {
        if (instance == null) {
            instance = new SimulationFrame();
        }
        return instance;
    }

    private void update(double deltaTime) {
//        revalidate();
        SimulationPanel.getInstance().setNextGeneration();
        repaint();
    }

    @Override
    public void run() {
        if (paused) {
            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }
            return;
        }

        while(mainMenu.isVisible()) {
            try {
                Thread.sleep(FRAMES_PER_SECOND);
            } catch (Exception e) {

            }
        }

        getContentPane().removeAll();
        add(SimulationPanel.getInstance());
        revalidate();

        double lastFrameTime = 0.0;
        while (!shutdown) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try {
                Thread.sleep(FRAMES_PER_SECOND);
            } catch (Exception e) {

            }

        }
    }

    public void unPause() {
        paused = false;
    }

}
