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
public class Nollaa extends Komento {
    
    private int edellinenArvo;
    
    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        super(sovellus,tuloskentta,syotekentta);

    }

    @Override
    public void suorita() {
        edellinenArvo = sovellus.tulos();
        sovellus.nollaa();
        super.paivitaTulos();
    }

    @Override
    public void peru() {
        sovellus.plus(edellinenArvo);
        super.paivitaTulos();
    }
    
}
