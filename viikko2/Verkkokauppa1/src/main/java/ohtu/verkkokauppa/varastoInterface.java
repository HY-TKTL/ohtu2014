/*
 * Aks copyright from the author Marko <markoma@iki.fi>.
 * Creation date: 14.9.2014 
 */

package ohtu.verkkokauppa;

/**
 *
 * @author Marko <markoma@iki.fi>
 */
public interface varastoInterface {

    Tuote haeTuote(int id);

    void otaVarastosta(Tuote t);

    void palautaVarastoon(Tuote t);

    int saldo(int id);
    
}
