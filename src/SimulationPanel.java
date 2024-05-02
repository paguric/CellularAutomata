import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// Implements Singleton pattern
public class SimulationPanel extends JPanel {
    private static SimulationPanel instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int ROWS = 200;
    public static final int COLUMNS = 200;
    private int[] rule = new int[8];
    public boolean[][] generationsGrid = new boolean[ROWS][COLUMNS];

    private SimulationPanel() {
        setSize(WIDTH, HEIGHT);
        setLayout(new GridLayout());



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

    @Override
    public void paintComponent(Graphics g) {
        int cellWidth = WIDTH / COLUMNS;
        int cellHeight = HEIGHT / ROWS;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {

                if (generationsGrid[i][j]) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(cellWidth * j, cellHeight * i, cellWidth, cellHeight);

            }
        }

    }

}
