import javax.swing.*;

// Implements Singleton pattern
public class SimulationFrame extends JFrame {
    private static SimulationFrame instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

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

}
