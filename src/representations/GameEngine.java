package representations;

import java.util.List;

public interface GameEngine {
	

	/**Calcola e restituisce tutte le mosse valide a partire dalla configurazione passata come parametro.
	 * @param configuration la configurazione di partenza
	 * @return un array di mosse valide
	 */
	public List<RepresentationNode> validActions(RepresentationNode configuration);

	public void start(Color color);
	
	public RepresentationNode getCurrentBoardState();
	
	public void playerMakeMove(RepresentationNode boardState);
	
	public RepresentationNode enemyMakeMove(String encodedMove);
	
}
