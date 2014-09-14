/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ghaas
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    public StatisticsTest() {
    }
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchPalauttaaOikeanPelaajan() {
        Player haettu = stats.search("Semenko");
        assertEquals("Semenko", haettu.getName());
    }
    
    @Test
    public void vaaraNimiEiLoydaMitaan() {
        assertEquals(null, stats.search("SDSADSASADDSA"));
    }
    
    @Test
    public void teamPalauttaaOikeatPelaajat() {
        List<Player> pit = stats.team("PIT");
        Player pelaaja = pit.get(0);
        assertEquals("Lemieux", pelaaja.getName());
    }
    
    @Test
    public void olematonJoukkuePalauttaaTyhjanListan() {
        List<Player> olematon = stats.team("olematon");
        assertEquals(0, olematon.size());
    }
    
    @Test
    public void topScoresLoytaaParhaimmanPelaajan() {
        List<Player> topPlayers = stats.topScorers(1);
        Player best = topPlayers.get(0);
        assertEquals("Gretzky", best.getName());
    }
}
