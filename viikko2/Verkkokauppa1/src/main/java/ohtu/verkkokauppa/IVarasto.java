
package ohtu.verkkokauppa;

/**
 *
 * @author Toni Oksanen
 */
public interface IVarasto {

  Tuote haeTuote(int id);

  void otaVarastosta(Tuote t);

  void palautaVarastoon(Tuote t);

  int saldo(int id);
  
}
