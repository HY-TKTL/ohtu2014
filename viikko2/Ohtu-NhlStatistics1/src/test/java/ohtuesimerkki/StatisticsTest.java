/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tsarpf
 */
public class StatisticsTest {
	Statistics stats;
	int playerCount;
	ArrayList<Player> players;	
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
		players = new ArrayList<Player>();

		players.add(new Player("Gretzky", "EDM", 55, 89));
		players.add(new Player("Yzerman", "DET", 47, 56));
		players.add(new Player("Lemieux", "PIT", 45, 54));
		players.add(new Player("Kurri",   "EDM", 37, 53));
		players.add(new Player("Semenko", "EDM", 4, 12));


		playerCount = players.size();
		Collections.sort(players);

		Reader readerStub = new Reader() {
			public List<Player> getPlayers() {

				List<Player> players = new ArrayList<Player>();

				players.add(new Player("Semenko", "EDM", 4, 12));
				players.add(new Player("Kurri",   "EDM", 37, 53));
				
				players.add(new Player("Lemieux", "PIT", 45, 54));
				players.add(new Player("Yzerman", "DET", 47, 56));
				players.add(new Player("Gretzky", "EDM", 55, 89));

				return players;
			}
		};
		stats = new Statistics(readerStub);
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of search method, of class Statistics.
	 */
	@Test
	public void testSearch() {
		System.out.println("search");
		String semenko = "Semenko";
		String lemieux = "Lemieux";
		Player player = stats.search("Lemieux");
		assertEquals(player.getName(), lemieux);

		player = stats.search("Semenko");
		assertEquals(player.getName(), semenko);

		
	}

	@Test
	public void testEmptySearch()
	{
		String jotain = "herppetyderpderp";
		assertEquals(stats.search(jotain), null);
	}

	/**
	 * Test of team method, of class Statistics.
	 */
	@Test
	public void testTeam() {
		System.out.println("team");
		ArrayList<Player> derp = (ArrayList<Player>)stats.team("EDM");
		for(int i = 0; i < derp.size(); i++)
		{
			assertEquals(derp.get(i).getTeam(), "EDM");
		}

	}

	/**
	 * Test of topScorers method, of class Statistics.
	 */
	@Test
	public void testTopScorers() {
		System.out.println("players: " + players.size());
		ArrayList<Player> playerst = (ArrayList<Player>)stats.topScorers(players.size());
		System.out.println("playerst: " + playerst.size());

		ArrayList<Player> playersa = (ArrayList<Player>)players;
		
		for(int i = 0; i < playersa.size(); i++)
		{
			assertTrue(playersa.get(i).getName().equals(playerst.get(i).getName()));
		}	
		System.out.println("topScorers");
	}
}
