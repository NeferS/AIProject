package util;

import representations.GameEngine;

/**Classe di utilit� in cui 'buttare' i riferimenti a tutti gli oggetti istanziati solo una volta (o alle variabili)
 * e che sono utilizzati da pi� di una classe.
 * @author Vincenzo Parrilla
 */
public final class General {
	public static GameEngine gameEngine;
	public static boolean isWhite;
	public static byte moves = 0;
	public static boolean isGameEnded = false;
}
