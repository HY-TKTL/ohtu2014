/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 14.9.2014 
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {

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

    @Before
    public void setUp() throws Exception {
        stats = new Statistics(readerStub);
    }

    /**
     * Test of search method, of class Statistics.
     */
    @Test
    public void testSearch() {
        assertEquals("Kurri", stats.search("urri").getName());
    }
        @Test
    public void testSearchNotFoundisNull() {
            assertNull(stats.search("Seaguls"));
    }

    /**
     * Test of team method, of class Statistics.
     */
    @Test
    public void testTeam() {
        List<Player> l = stats.team("EDM");
        assertEquals("Semenko", l.get(0).getName());
        assertEquals("Kurri", l.get(1).getName());
        assertEquals("Gretzky", l.get(2).getName());
    }

    /**
     * Test of topScorers method, of class Statistics.
     */
    @Test
    public void testTopScorers() {
        List<Player> l = stats.topScorers(3);
        assertEquals("Gretzky", l.get(0).getName());
        assertEquals("Lemieux", l.get(1).getName());
        assertEquals("Yzerman", l.get(2).getName());
    }
}
