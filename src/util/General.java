package util;

import representations.GameEngine;

/**Classe di utilità in cui 'buttare' i riferimenti a tutti gli oggetti istanziati solo una volta (o alle variabili)
 * e che sono utilizzati da più di una classe.
 * @author Vincenzo Parrilla
 */
public final class General {
	public static GameEngine gameEngine;
	public static boolean isWhite;
}
