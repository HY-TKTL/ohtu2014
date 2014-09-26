/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author teeyoshi
 */
public class StatisticsTest {
    Statistics stats;
    Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }

    };

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void etsiPelaaja() {
        
        Player kurri = stats.search("Kurri");
        assertEquals("Kurri", kurri.getName());
    }
    
    @Test
    public void searchPlayrNotInList() {
        assertEquals(null, stats.search("Kulli"));
    }
    
    @Test
    public void searchTeam() {
        assertEquals("EDM", stats.search("Kurri").getTeam());
//        assertEquals("", stats.search("F").getTeam());
        List<Player> team = stats.team("EDM");
        assertEquals(3, team.size());
        assertEquals("Semenko", team.get(0).getName());
        
    }
    
    @Test
    public void topScorers() {
        
    }
    
    

}
