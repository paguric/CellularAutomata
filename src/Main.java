import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Griglia grigliaTest = new Griglia();


        for (int i = 0; i < Griglia.RIGHE; i++) {
            grigliaTest.prossimaGenerazione();
        }

        System.out.println(grigliaTest);


    }
}
