/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 14.9.2014 
 */
package ohtuesimerkki;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public class PlayerTest {

    Player player;

    public PlayerTest() {
    }

    @Before
    public void setUp() {
        player = new Player("Semenko", "EDM", 4, 12);
    }

    @Test
    public void testGetAssists() {
        assertEquals(12, player.getAssists());
    }

    /**
     * Test of setAssists method, of class Player.
     */
    @Test
    public void testSetAssists() {
        player.setAssists(0);
        assertEquals(0, player.getAssists());

    }

    /**
     * Test of getGoals method, of class Player.
     */
    @Test
    public void testGetGoals() {
        assertEquals(4, player.getGoals());
    }

    /**
     * Test of setGoals method, of class Player.
     */
    @Test
    public void testSetGoals() {
        player.setGoals(0);
        assertEquals(0, player.getGoals());
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        assertEquals("Semenko", player.getName());
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        player.setName("Steven");
        assertEquals("Steven", player.getName());
    }

    /**
     * Test of getTeam method, of class Player.
     */
    @Test
    public void testGetTeam() {
        assertEquals("EDM", player.getTeam());
    }

    /**
     * Test of setTeam method, of class Player.
     */
    @Test
    public void testSetTeam() {
        player.setTeam("Seaguls");
        assertEquals("Seaguls", player.getTeam());
    }

    /**
     * Test of getPoints method, of class Player.
     */
    @Test
    public void testGetPoints() {
        assertEquals(16, player.getPoints());
    }

    /**
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        assertEquals("Semenko              EDM  4 + 12 = 16", player.toString());
    }

    /**
     * Test of compareTo method, of class Player.
     */
    @Test
    public void testCompareTo() {
        assertEquals(-12, player.compareTo(new Player("Steven Seagull", "Trooppers", 1, 3)));
    }

}
