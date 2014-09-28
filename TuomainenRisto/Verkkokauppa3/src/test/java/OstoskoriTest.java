/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author risto
 */
public class OstoskoriTest {
    
    Ostoskori kori;
    Kauppa kauppa;
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    
    public OstoskoriTest() {
        kori = mock(Ostoskori.class);
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa=new Kauppa(varasto,pankki,viite);
    }
    

    @Test
    public void testi1() {

        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 1
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 

        // tehdään ostokset
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        kauppa.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455",5);   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    } 

    @Test
    public void kahdenTuotteenVeloitusToimii() {
        when(viite.uusi()).thenReturn(5);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"kokista",7));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2,"paskaa",7));
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("Adolf Hitler", "1111");
        
        verify(pankki).tilisiirto("Adolf Hitler", 5, "1111", "33333-44455", 14);
        
    }
    
    @Test
    public void kahdenTuotteetJoistaToinenPuuttuuMaksaminenToimii() {
        when(viite.uusi()).thenReturn(26);
        when(varasto.saldo(1)).thenReturn(0);
        when(varasto.saldo(2)).thenReturn(26);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"jotain",6));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2,"kokista",7));
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("Joku","1");
        verify(pankki).tilisiirto("Joku", 26, "1", "33333-44455", 7);
    }
    
     @Test
     public void kaksiSamaaTuotettaLaskutetaanOikein() {
         when(viite.uusi()).thenReturn(1).thenReturn(2);
         when(varasto.saldo(1)).thenReturn(10).thenReturn(9);
         when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"kokista",77));
         kauppa.aloitaAsiointi();
         kauppa.lisaaKoriin(1);
         kauppa.lisaaKoriin(1);
         kauppa.tilimaksu("asdf", "1");
         verify(pankki).tilisiirto("asdf",1,"1","33333-44455", 154);
     }
    
     
     @Test
     public void nollataanValissa() {
         when(viite.uusi()).thenReturn(1).thenReturn(2);
         when(varasto.saldo(1)).thenReturn(10);
         when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"kokista",5));
         kauppa.aloitaAsiointi();
         kauppa.lisaaKoriin(1);
         kauppa.tilimaksu("joku","1");
         kauppa.aloitaAsiointi();
         kauppa.lisaaKoriin(1);
         kauppa.tilimaksu("joku","1");
         verify(pankki).tilisiirto("joku", 1, "1", "33333-44455", 5);
         
     }
     
     @Test
     public void viiteNumeroitapyydetaanVitunMonta() {
         when(viite.uusi()).thenReturn(1).thenReturn(2);
         when(varasto.saldo(1)).thenReturn(10);
         when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"kokista",5));
         kauppa.aloitaAsiointi();
         kauppa.lisaaKoriin(1);
         kauppa.tilimaksu("joku","1");
         kauppa.aloitaAsiointi();
         kauppa.lisaaKoriin(1);
         kauppa.tilimaksu("joku", "2");
         verify(viite, times(2)).uusi(); 
     }
     
     @Test
     public void poistetaanKutenPitaa() {
         when(viite.uusi()).thenReturn(1).thenReturn(2);
         when(varasto.saldo(1)).thenReturn(10);
         when(varasto.haeTuote(1)).thenReturn(new Tuote(1,"kokista",5));
         kauppa.aloitaAsiointi();
         kauppa.lisaaKoriin(1);
         kauppa.poistaKorista(1);
         verify(varasto).palautaVarastoon(new Tuote(1,"kokista",5));
     }
}
