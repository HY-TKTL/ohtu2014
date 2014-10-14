/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statistics.matcher;

import statistics.Player;

/**
 *
 * @author Ilari
 */
public class QueryBuilder{
    And terms;
    
    public QueryBuilder(){
        terms = new And();
    }
    public QueryBuilder playsIn(String team){
        terms.add(new PlaysIn(team));
        return this;
    }
    public QueryBuilder hasAtLeast(int n,String what){
        terms.add(new HasAtLeast(n,what));
        return this;
    }
    public QueryBuilder hasFewerThan(int n,String what){
        terms.add(new HasFewerThan(n,what));
        return this;
    }

    public QueryBuilder matches(Matcher p) {
        terms.add(p);
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... one){
        terms.add(new Or(one));
        return this;
    }
    public Matcher build(){
        return this.terms;
    }
    public QueryBuilder select(){
        return new QueryBuilder();
    }
}
