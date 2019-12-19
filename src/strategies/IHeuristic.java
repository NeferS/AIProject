package strategies;

import representations.Color;
import representations.RepresentationNode;

/**Definisce una strategia che il giocatore automatico pu� seguire sulla rapprentazione dello
 * spazio degli stati R.
 * @param R la rappresentazione dello spazio degli stati
 * @author Vincenzo Parrilla
 */
public interface IHeuristic {
	
	/**Assegna un valore h(n) al nodo ricevuto in input.
	 * @param node la configurazione da valutare
	 * @return il valore della funzione euristica h
	 */
	public double h(RepresentationNode node);
	
	/**Informa l'euristica del colore del giocatore.
	 * @param c il colore del giocatore
	 */
	public void color(Color c);

}
