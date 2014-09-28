
package ohtu.verkkokauppa;

import java.util.ArrayList;

/**
 *
 * @author Toni Oksanen
 */
public interface IKirjanpito {

  ArrayList<String> getTapahtumat();

  void lisaaTapahtuma(String tapahtuma);
  
}
