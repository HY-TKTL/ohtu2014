/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ilari
 */
public class StatisricsTest {
    
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
    
    public StatisricsTest() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
        
    }
    
    @Test
    public void testSearch(){
        assertEquals(readerStub.getPlayers().get(0).toString(),stats.search("Semenko").toString());
        assertNull(stats.search("siika"));
    }
    
    @Test
    public void testTeam(){
        assertEquals(readerStub.getPlayers().get(1).toString(),stats.team("PIT").get(0).toString());
    }
    
    @Test
    public void testTopScorers(){
        assertEquals(readerStub.getPlayers().get(4).toString(),stats.topScorers(1).get(0).toString());
    }
    
}
