package representations;

import java.util.List;

public interface GameEngine {
	
	/**Calcola e restituisce tutte le mosse valide a partire dalla configurazione passata come parametro.
	 * @param configuration la configurazione di partenza
	 * @return un array di mosse valide
	 */
	List<RepresentationNode> validActions(RepresentationNode configuration, Color playingColor, byte depth);

	void start(Color color);
	
	RepresentationNode getCurrentBoardState();
	
	void playerMakeMove(RepresentationNode boardState);
	
	RepresentationNode enemyMakeMove(String encodedMove);

	Color getPlayerColor();

	Color getEnemyColor();
}
