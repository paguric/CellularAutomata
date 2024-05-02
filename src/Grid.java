//public class Grid {

//    public boolean[][] grid;
//    private static int currentRow;
//    public Grid() {
//        this.grid = new boolean[ROWS][COLUMNS];
//        this.initializeFirstGeneration();
//
//
//        // invert the rule array
//        for (int i = 0; i < 4; i++) {
//            int temp = this.rule[i];
//            this.rule[i] = this.rule[8 -(1 +i)];
//            this.rule[8 -(1 +i)] = temp;
//        }
//
//        currentRow = 0;
//    }
//
//    // default starting generation: a single cell in the middle of the first row is set to true (alive)
//    private void initializeFirstGeneration() {
//        grid[0][COLUMNS /2] = true;
//    }
//
//
//
//
//
//    public void nextGeneration() {
//        if (currentRow >= ROWS -1) return;
//        currentRow += 1;
//        // default condition: first and last cell are always false (dead)
//        grid[currentRow][0] = false;
//        grid[currentRow][COLUMNS -1] = false;
//
//        for (int i = 1; i < COLUMNS -2; i++) {  // skips 2 already set cells
//            int[] clusterState = new int[3];    // saves left neighbor, current cell and right neighbor
//            for (int j = -1; j < 2; j ++) {
//                clusterState[j +1] = grid[currentRow -1][i +j] ? 1 : 0;
//            }
//            int instruction = binaryToDecimal(clusterState);
//            grid[currentRow][i] = rule[instruction] == 1;
//        }
//    }
//
//    @Override
//    public String toString() {
//        String s = "";
//        for (int i = 0; i < ROWS; i++) {
//            for (int j = 0; j < COLUMNS; j++) {
//                s += grid[i][j] ? 1 : " ";
//            }
//            if (i != ROWS -1) s += "\n";
//        }
//        return s;
//    }
//}
