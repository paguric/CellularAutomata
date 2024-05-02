import javax.swing.*;
import java.util.Arrays;

// Implements Singleton pattern
public class SimulationPanel extends JPanel {
    private static SimulationPanel instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private int[] rule = new int[8];

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

    public void setRule(int rule) {
        int[] binaryRule = BinaryOperationsUtils.decimalToBinary(rule);

        // adds leading zeros to the rule array if it has less than 8 elements
        if (binaryRule.length < 8) {
            int zeros = 8 -binaryRule.length;
            int[] longerBinaryRule = new int[8];
            for (int i = 0; i < zeros; i++) {
                longerBinaryRule[i] = 0;
            }
            for (int i = zeros; i < 8; i++) {
                longerBinaryRule[i] = binaryRule[i -zeros];
            }
            binaryRule = longerBinaryRule;
        }

        this.rule = binaryRule;
    }

}
