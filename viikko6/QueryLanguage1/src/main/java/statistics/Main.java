package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        QueryBuilder query = new QueryBuilder();
        
        Matcher m = query.select().oneOf(
                        query.select().playsIn("PHI")
                             .hasAtLeast(10, "goals")
                             .hasFewerThan(20, "assists").build(),

                        query.select().playsIn("EDM")
                             .hasAtLeast(50, "points").build()
                       ).build();
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
    }
}
