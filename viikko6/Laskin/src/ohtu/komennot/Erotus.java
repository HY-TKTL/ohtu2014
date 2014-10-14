package ohtu.komennot;


import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ilari
 */
public class Erotus extends Komento {

    private int edellinenLuku;
    
    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus,tuloskentta,syotekentta);
    }

    @Override
    public void suorita() {
        int arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        sovellus.miinus(arvo);
        edellinenLuku = arvo;
        super.paivitaTulos();
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenLuku);
        super.paivitaTulos();
    }
    
    
}
