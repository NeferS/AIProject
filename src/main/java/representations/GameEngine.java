package representations;

import java.util.LinkedList;
import java.util.List;

public interface GameEngine {

	public List<RepresentationNode> validActions(RepresentationNode configuration);

	public void start(Color color);
	
	public RepresentationNode getCurrentBoardState();

	public LinkedList<RepresentationNode> applyLegalMoves(RepresentationNode boardState, Moves movesType, boolean playerTurn, LinkedList<RepresentationNode> nextBoardStates);

	public void playerMakeMove(RepresentationNode boardState);
	
	public RepresentationNode enemyMakeMove(String encodedMove);

	//public List<RepresentationNode> getWBCaptureMoves(RepresentationNode boardState);

	public Color getPlayerColor();

	public Color getEnemyColor();
}
