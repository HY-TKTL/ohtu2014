/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 14.9.2014 
 */

package ohtu.verkkokauppa;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public interface PankkiInterface {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}
