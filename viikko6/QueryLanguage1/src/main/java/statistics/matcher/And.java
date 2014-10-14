package statistics.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import statistics.Player;

public class And implements Matcher {

    private ArrayList<Matcher> matchers;

    public And(Matcher... matchers) {
        this.matchers = new ArrayList<Matcher>(Arrays.asList(matchers));
    }
    
    public void add(Matcher matcher){
        matchers.add(matcher);
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            if (!matcher.matches(p)) {
                return false;
            }
        }

        return true;
    }
}
