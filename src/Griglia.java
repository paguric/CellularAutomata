public class Griglia {
    private static final int REGOLA_DEFAULT = 90; // fino a 255
    public static final int RIGHE = 200;
    public static final int COLONNE = 777; // possibilmente dispari
    public boolean[][] griglia;
    private int[] regola;
    private static int rigaCorrente;
    public Griglia() {
        this.griglia = new boolean[RIGHE][COLONNE];
        this.inizializzaPrimaGenerazione();
        regola = decimaleABinario(REGOLA_DEFAULT);

        // aggiunta zeri a sinistra dovessero mancare
        if (regola.length < 8) {
            int zeri = 8 -regola.length;
            int[] regola = new int[8];
            for (int i = 0; i < zeri; i++) {
                regola[i] = 0;
            }
            for (int i = zeri; i < 8; i++) {
                regola[i] = this.regola[i -zeri];
            }
            this.regola = regola;
        }

        // ribalta l'array
        for (int i = 0; i < 4; i++) {
            int temp = this.regola[i];
            this.regola[i] = this.regola[8 -(1 +i)];
            this.regola[8 -(1 +i)] = temp;
        }

        rigaCorrente = 0;
    }

    // setta a "true" il valore centrale della prima riga
    private void inizializzaPrimaGenerazione() {
        griglia[0][COLONNE /2] = true;
    }

    private static int[] decimaleABinario(int valoreDecimale) {
        // conta numero cifre binarie
        int cifre = 0;
        int copiaN = valoreDecimale;
        do {
            // copiaN = (int) Math.floor(copiaN/2);
            copiaN /= 2;
            cifre++;
        } while(copiaN != 0);

        int[] rappresentazioneBinaria = new int[cifre];
        do {
            rappresentazioneBinaria[--cifre] = valoreDecimale % 2;
            valoreDecimale = (valoreDecimale - valoreDecimale % 2) / 2;
        } while(valoreDecimale != 0);

        return rappresentazioneBinaria;
    }

    private static int binarioADecimale(int[] rappresentazioneBinaria) {
        int cifre = rappresentazioneBinaria.length;
        int valoreDecimale = 0;
        for (int i = 0; i < cifre; i++) {
            int base = 2 *rappresentazioneBinaria[i];
            if (base == 0) continue;
            valoreDecimale += (int) Math.pow(base, cifre -(1 + i));
        }
        return valoreDecimale;
    }

    public void prossimaGenerazione() {
        if (rigaCorrente >= RIGHE -1) return;
        rigaCorrente += 1;
        // condizione default: primo e ultimo elemento di ogni riga sono sempre morti
        griglia[rigaCorrente][0] = false;
        griglia[rigaCorrente][COLONNE -1] = false;

        for (int i = 1; i < COLONNE -2; i++) {  // salta le colonne settate di default
            int[] statoCluster = new int[3];    // stato di vicino sinistro, cellula corrente e vicino destro
            for (int j = -1; j < 2; j ++) {
                statoCluster[j +1] = griglia[rigaCorrente -1][i +j] ? 1 : 0;
            }
            int istruzione = binarioADecimale(statoCluster);
            griglia[rigaCorrente][i] = regola[istruzione] == 1;
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                s += griglia[i][j] ? 1 : " ";
            }
            if (i != RIGHE -1) s += "\n";
        }
        return s;
    }
}
