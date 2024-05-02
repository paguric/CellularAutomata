public class Main {
    public static void main(String[] args) {

        Grid testGrid = new Grid();


        for (int i = 0; i < Grid.RIGHE; i++) {
            testGrid.nextGeneration();
        }

        System.out.println(testGrid);


    }
}
