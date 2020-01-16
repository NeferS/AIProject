package util;

/**Questo oggetto rappresenta una tupla composta da due elementi, meglio definita come 'coppia'.
 * @author AVL
 *
 * @param <E0> il tipo del primo elemento della coppia
 * @param <E1> il tipo del secondo elemento della coppia
 */
public class Pair<E0,E1> {
	
	protected E0 element0;
	protected E1 element1;
	
	/**Costruttore di default. Gli elemento della coppia possono essere contemporaneamente 'null'.
	 */
	public Pair() { }
	
	/**Costruttore che inizializza la coppia utilizzando come elemento quelli passati tramite parametro.
	 * Entrambi i parametri possono essere 'null', anche contemporaneamente.
	 * @param elem0 il primo elemento della coppia
	 * @param elem1 il secondo elemento della coppia
	 */
	public Pair(E0 elem0, E1 elem1) {
		element0 = elem0;
		element1 = elem1;
	}
	
	/**Restituisce il primo elemento della coppia.
	 * @return il primo elemento
	 */
	public E0 getElement0() { return element0; }
	/**Restituisce il secondo elemento della coppia.
	 * @return il secondo elemento
	 */
	public E1 getElement1() { return element1; }
	/**Sostituisce il primo elemento della coppia con quello passato e restituisce il valore che è stato sostituito.
	 * @param newElem il nuovo primo elemento della coppia
	 * @return il vecchio primo elemento della coppia
	 */
	public E0 setElement0(E0 newElem) {
		E0 oldElem = element0;
		element0 = newElem;
		return oldElem;
	}
	/**Sostituisce il secondo elemento della coppia con quello passato e restituisce il valore che è stato sostituito.
	 * @param newElem il nuovo secondo elemento della coppia
	 * @return il vecchio secondo elemento della coppia
	 */
	public E1 setValue(E1 newElem) {
		E1 oldElem = element1;
		element1 = newElem;
		return oldElem;
	}
}
