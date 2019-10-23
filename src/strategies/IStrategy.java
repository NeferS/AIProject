package strategies;

import representations.Representation;

/**Definisce una strategia che il giocatore automatico può seguire sulla rapprentazione dello
 * spazio degli stati R.
 * @param R la rappresentazione dello spazio degli stati
 * @author Vincenzo Parrilla
 */
public interface IStrategy {
	
	/*Assegna un valore h(n) al nodo R.*/
	double h(Representation node);
	/*Potrebbe essere necessario valutare se cambiare strategia o meno in base
	 * alla configurazione del nodo R.*/
	IStrategy valuateStrategy(Representation configuration);
}
