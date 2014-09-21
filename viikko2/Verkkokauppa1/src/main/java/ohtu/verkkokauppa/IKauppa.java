
package ohtu.verkkokauppa;

/**
 *
 * @author Toni Oksanen
 */
public interface IKauppa {

  void aloitaAsiointi();

  void lisaaKoriin(int id);

  void poistaKorista(int id);

  boolean tilimaksu(String nimi, String tiliNumero);
  
}
