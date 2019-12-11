package view;


import controller.HumanClient;
import model.BasicConfiguration;
import model.BoardDataStructure;
import util.ProcessorMove;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import view.Piece;

/*
 * Classe che implementa la finestra di gioco con al suo interno la scacchiera e 
 * le sezioni per interagire e/o comunicare con il server
 */

public class Game {
	private Piece[][] graphic_board;
	private JTextArea server_msg_area;
	private JButton btnEnter;
	private JButton btnExitPiece;
	private JTextField txtStartCell;
	private JTextField txtDestinationCell;
	private JFrame frame;
	private Font font;
	private Color bgcolor;
	private Color black_cells;
	private Color white_cells;
	private int fontSize;
	private int cell_size;
	private int width;
	private int height;
	private Color color;
	private Point startingCell = null;
	private Point arrivalCell = null;
	private BoardDataStructure board;
	private HumanClient player;
	private String move;

	public Game ( BoardDataStructure board, HumanClient player ) {
		this.board = board;
		this.player = player;
		server_msg_area = new JTextArea();
		bgcolor = new Color(192,137,103);
		black_cells = new Color(170, 82, 45);
		white_cells = new Color(255, 250, 240);
		fontSize = 12;
		cell_size = 70;
		width = 1340;
		height = 980;
	}

	public void showGUI() {
		int i;
		UIManager.put("Panel.background", bgcolor);
		frame = new JFrame("Dipole");
		frame.setDefaultCloseOperation(3);
		frame.getContentPane().setLayout(null);
		font = new Font(UIManager.getFont("Panel.font").getFontName(), 0, fontSize);
		JPanel panel_board = new JPanel(null);
		panel_board.setLocation(0, 0);
		panel_board.setSize(new Dimension(cell_size * 9, cell_size * 9));
		graphic_board = new Piece[8][8];
		Color bgcolor = UIManager.getColor("Panel.background");
		panel_board.add(new Piece(bgcolor, cell_size, 0, 0));

		/*
		 * Disegna la scacchiera con i JPanel Piece del package view
		 */
		for (i = 0; i < 8; ++i) {
			panel_board.add((Component)new Piece(bgcolor, cell_size, 0, i + 1, "" + (1 + i)), true);
		}
		for (i = 0; i < 8; ++i) {
			panel_board.add((Component)new Piece(bgcolor, cell_size, i + 1, 0, (char)(65 + i)), true);
			for (int j = 0; j < 8; ++j) {
				graphic_board[i][j] = (i + j) % 2 == 0 ? new Piece(white_cells, cell_size, i + 1, j + 1) : new Piece(black_cells, cell_size, i + 1, j + 1);
				graphic_board[i][j].addMouseListener(new MouseClick(graphic_board[i][j]));                
				panel_board.add(graphic_board[i][j]);

			}
		}
		frame.add(panel_board);

		/*
		 * Area laterale per comunicare con il server
		 */
		server_msg_area.setFont(font);
		server_msg_area.setEditable(false);
		server_msg_area.setBackground(Color.DARK_GRAY);
		server_msg_area.setForeground(Color.WHITE);
		JScrollPane scrollMsgArea = new JScrollPane(server_msg_area);
		scrollMsgArea.setPreferredSize(new Dimension(20, 1));
		scrollMsgArea.setVerticalScrollBarPolicy(22);
		scrollMsgArea.setHorizontalScrollBarPolicy(32);
		scrollMsgArea.setSize(new Dimension(panel_board.getWidth(), height-140));
		scrollMsgArea.setLocation(panel_board.getWidth()+40, 70);
		frame.add(scrollMsgArea);

		/*
		 * Pannello per l'interazione con l'utente
		 */
		JPanel panel_action = new JPanel(null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{46, 0, 0};
		gbl_panel.rowHeights = new int[]{38, 0, 0, 0, 14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_action.setLayout(gbl_panel);
		JLabel lblNewLabel = new JLabel("Casella di partenza");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_action.add(lblNewLabel, gbc_lblNewLabel);
		txtStartCell = new JTextField();
		txtStartCell.setEditable(false);
		txtStartCell.setBackground(bgcolor);
		txtStartCell.setBorder(null);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_action.add(txtStartCell, gbc_textField);
		txtStartCell.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Casella di arrivo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_action.add(lblNewLabel_1, gbc_lblNewLabel_1);
		txtDestinationCell = new JTextField();
		txtDestinationCell.setEditable(false);
		txtDestinationCell.setBackground(bgcolor);
		txtDestinationCell.setBorder(null);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panel_action.add(txtDestinationCell, gbc_textField_1);
		txtDestinationCell.setColumns(10);
		btnEnter = new JButton("ENTER");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(30, 0, 5, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		btnEnter.addActionListener(new MouseClick());
		panel_action.add(btnEnter, gbc_btnNewButton);
		btnExitPiece = new JButton("MANDA FUORI");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(30, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		btnExitPiece.addActionListener(new MouseClick());
		panel_action.add(btnExitPiece, gbc_btnNewButton_1);
		panel_action.setSize(new Dimension(panel_board.getWidth() - 140,height - (panel_board.getHeight()+140)));
		panel_action.setLocation(40, panel_board.getHeight()+40);
		frame.getContentPane().add(panel_action);

		frame.setSize(width, height);
		frame.setLocation(0, 0);
		frame.setVisible(true);
		set_initial_configuration(board);
	}

	public void setColor(representations.Color colour) {
		if (colour == representations.Color.BLACK) {
			this.color = Color.BLACK;
		}else {
			this.color = Color.WHITE;
		}
	}

	public void set_initial_configuration(BoardDataStructure board) {
		graphic_board[BasicConfiguration.black_start_coodinates[0]][BasicConfiguration.black_start_coodinates[1]].repaint(board.get(BasicConfiguration.black_start_coodinates[0], BasicConfiguration.black_start_coodinates[1]));
		graphic_board[BasicConfiguration.white_start_coodinates[0]][BasicConfiguration.white_start_coodinates[1]].repaint(board.get(BasicConfiguration.white_start_coodinates[0], BasicConfiguration.white_start_coodinates[1]));
	}

	public void refreshBoard(BoardDataStructure board) {
		for (int i = 0; i < board.num_rows(); ++i) {
			for (int j = 0; j < board.num_cols(); ++j) {
				graphic_board[i][j].repaint(board.get(i, j));
			}
		}
	}

	public void publish_server(String text) {
		server_msg_area.append(" \n");
		server_msg_area.append(text);
		server_msg_area.setCaretPosition(server_msg_area.getText().length());
	}

	public class MouseClick implements MouseListener, ActionListener{
		Piece p;
		public MouseClick(Piece piece) {
			this.p = piece;
		}

		public MouseClick() {}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (p.bgcolor == black_cells ) {
				if (color.equals(p.color) && e.getClickCount() == 2) {
					startingCell = p.getPosizione();
					txtStartCell.setText(ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) ));
				}else if (startingCell != null){
					if (arrivalCell != null) {
						graphic_board[arrivalCell.x][arrivalCell.y].setBorder(null); 
					}
					arrivalCell = p.getPosizione();
					graphic_board[arrivalCell.x][arrivalCell.y].setBorder(BorderFactory.createLineBorder(new Color(244, 208, 63), 3)); 
					txtDestinationCell.setText(ProcessorMove.calculateCell(new model.Point(arrivalCell.x, arrivalCell.y)));		
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==btnEnter) {
				try {
					model.Point sp = new model.Point(startingCell.x, startingCell.y);
					model.Point ap = new model.Point(arrivalCell.x, arrivalCell.y);
					move = txtStartCell.getText()+","+ProcessorMove.calculateDirection(sp,ap);
					int response = JOptionPane.showConfirmDialog(frame,"Vuoi confermare la mossa?");  
					if(response==JOptionPane.YES_OPTION || ProcessorMove.checkMove(board.get(sp.x, sp.y), board.get(ap.x, ap.y)) ){  
						player.sendMove(move);
					}  else if (response==JOptionPane.NO_OPTION) {}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(frame, "Attenzione, mossa non valida.");
				}
			}else if (e.getSource()==btnExitPiece) {
				if (startingCell == null ) {
					JOptionPane.showMessageDialog(frame, "Seleziona prima uno stack");
				}else {
					String[] options = {"1", "2", "3", "4", "5","6","7","8","9","10","11","12"};
					int numPieceRmv = JOptionPane.showOptionDialog(null, "Quante pedine vuoi spostare fuori dalla schacchiera?",
							"Click a button",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
					String [] options2 = {"N", "NE", "E", "SE", "S","SO","O","NO"};
					int dir = JOptionPane.showOptionDialog(null, "Verso quale direzione?",
							"Click a button",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, null);
					switch(dir) {
						case 0: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",N,"+ (numPieceRmv+1);break;
						case 1: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",NE,"+ (numPieceRmv+1);break;
						case 2: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",E,"+ (numPieceRmv+1);break;
						case 3: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",SE,"+ (numPieceRmv+1);break;
						case 4: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",S,"+ (numPieceRmv+1);break;
						case 5: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",SW,"+ (numPieceRmv+1);break;
						case 6: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",W,"+ (numPieceRmv+1);break;
						case 7: move = ProcessorMove.calculateCell(new model.Point(startingCell.x, startingCell.y) )+",NW,"+ (numPieceRmv+1);break;
					}
					player.sendMove(move);
				}

			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}

}
