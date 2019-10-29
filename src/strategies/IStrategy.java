package strategies;

import representations.RepresentationNode;

/**Definisce una strategia che il giocatore automatico può seguire sulla rapprentazione dello
 * spazio degli stati R.
 * @param R la rappresentazione dello spazio degli stati
 * @author Vincenzo Parrilla
 */
public interface IStrategy {
	
	/**Assegna un valore h(n) al nodo ricevuto in input.
	 * @param node la configurazione da valutare
	 * @return il valore della funzione euristica h
	 */
	double h(RepresentationNode node);
	
	/*Potrebbe essere necessario valutare se cambiare strategia o meno in base alla configurazione del nodo ricevuto.*/
	/**Restituisce la migliore strategia attuabile a partire dalla configurazione ricevuta in input,
	 * @param configuration la configurazione attuale
	 * @return la prossima strategia (può coincidere con this)
	 */
	IStrategy valuateStrategy(RepresentationNode configuration);
}
