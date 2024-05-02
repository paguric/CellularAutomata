import javax.swing.*;
import java.awt.*;

// Implements Singleton pattern
public class SimulationPanel extends JPanel {
    private static SimulationPanel instance = null;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int ROWS = 200;
    public static final int COLUMNS = 200;
    private int[] rule = new int[8];
    private boolean[][] generationsGrid = new boolean[ROWS][COLUMNS];
    private int currentGeneration = 0;

    private SimulationPanel() {
        setSize(WIDTH, HEIGHT);

        setFirstGeneration();

        setVisible(true);
    }

    public static SimulationPanel getInstance() {
        if (instance == null) {
            instance = new SimulationPanel();
        }
        return instance;
    }

    // default starting generation: a single cell in the middle of the first row is set to true (alive)
    private void setFirstGeneration() {
        boolean[] firstGeneration = new boolean[COLUMNS];
        firstGeneration[COLUMNS /2] = true;
        generationsGrid[currentGeneration++] = firstGeneration;
    }

    public void setNextGeneration() {
        if (currentGeneration >= ROWS) return;
        generationsGrid[currentGeneration] = computeNextGeneration();
        currentGeneration++;
    }

    private boolean[] computeNextGeneration() {
        if (currentGeneration >= ROWS) return null;
        boolean[] nextGeneration = new boolean[COLUMNS];

        for (int i = 0; i < COLUMNS; i++) {  // skips 2 already set cells
            int[] clusterState = new int[3];    // saves left neighbor, current cell and right neighbor
            for (int j = -1; j < 2; j ++) {
                int columnIndex = i + j < 0 ? COLUMNS - 1 : i + j >= COLUMNS ? 0 : i + j;

                clusterState[j +1] = generationsGrid[currentGeneration -1][columnIndex] ? 1 : 0;
            }
            int instruction = BinaryOperationsUtils.binaryToDecimal(clusterState);
            nextGeneration[i] = rule[7 - instruction] == 1; // rule is inverted (I don't know why)
        }

        return nextGeneration;

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

                if (generationsGrid[i] != null && generationsGrid[i][j]) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(cellWidth * j, cellHeight * i, cellWidth, cellHeight);

            }
        }

    }

    public boolean isGenerationComplete() {
        return currentGeneration >= ROWS;
    }

    public void reset() {
        currentGeneration = 0;
        generationsGrid = new boolean[ROWS][COLUMNS];
        setFirstGeneration();
    }

}
