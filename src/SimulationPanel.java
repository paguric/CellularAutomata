import javax.swing.*;

// Implements Singleton pattern
public class SimulationPanel extends JPanel {
    private static SimulationPanel instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private SimulationPanel() {
        setSize(WIDTH, HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
    }

    public static SimulationPanel getInstance() {
        if (instance == null) {
            instance = new SimulationPanel();
        }
        return instance;
    }


}
