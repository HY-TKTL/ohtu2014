
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;     // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;                       // Yhden kasvatuskerran yhteydessä suurennettava määrä
    private int[] luvut;                            // Lukujoukko 
    private int alkioidenLkm;                       // lukujen määrä

    public IntJoukko() {
        luvut = new int[OLETUSKAPASITEETTI];
        alusta();
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        luvut = new int[kapasiteetti];
        alusta();
    }
    
    private void alusta(){
        alusta(OLETUSKASVATUS);
    }
    
    private void alusta(int kasvatuskoko){
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti <= 0) {
            throw new
                IndexOutOfBoundsException("Negatiivinen tai tyhjä kapasiteetti");
        }
        if (kasvatuskoko <= 0) {
            throw new IndexOutOfBoundsException("Viheellinen kasvatuskoko");
        }
        luvut = new int[kapasiteetti];
        alusta(kasvatuskoko);

    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            tarkistaKasvatus();
            return true;
        }
        return false;
    }
    
    private void tarkistaKasvatus(){
        if (alkioidenLkm+1 == luvut.length) {
            
            int[] uusi = new int[alkioidenLkm + kasvatuskoko];
            
            System.arraycopy(luvut, 0, uusi, 0, alkioidenLkm);
            luvut = uusi;

        }
    }

    public boolean kuuluu(int luku) {
        return etsi(luku) != -1;
    }
    
    private int etsi(int luku){
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return i;
            }
        }
        return -1;
    }

    public boolean poista(int luku) {
        int i = etsi(luku);
        if (i != -1) {
            alkioidenLkm--;
            for (int j = i; j < alkioidenLkm; j++) {
                luvut[j] = luvut[j+1];
            }
            return true;
        }
        return false;
    }

    public int max() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += luvut[i];
            tuotos += ", ";
        }
        tuotos += luvut[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;

    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(luvut,0,taulu,0,alkioidenLkm);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
 
        return z;
    }
        
}