/*
 * Decompiled with CFR 0.145.
 */
package view;

import controller.BasicConfiguration;
import controller.BoardDataStructure;
import controller.Cell;
import controller.Colour;
import controller.HumanClient;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ProgressMonitor;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import view.Piece;

public class GUI {
    public static int giornata_corrente;
    public static int girone_corrente;
    static Piece[][] graphic_board;
    static JTextArea server_msg_area;
    static JTextArea piece_selected;
    static boolean started;
    static JFrame frame;
    static ProgressMonitor progressMonitor;
    static Font font;
    static SimpleAttributeSet attr;
    static StyledDocument doc;
    static Color bgcolor;
    static Color black_cells;
    static Color white_cells;
    static JTable rank_table;
    static Object[][] rank_data;
    static String[] rank_col_name;
    static JTable[][] cal_table;
    static String[] cal_col_name;
    static Object[][] cal_data;
    static int partita_corrente;
    static int fontSize;
    static int cell_size;
    static int width;
    static int height;
    static int calWidth;
    public static Color color;
    protected static Point startingCell = null;
	protected static Point arrivalCell = null;

    static {
        server_msg_area = new JTextArea();
        piece_selected = new JTextArea();
        started = false;
        attr = new SimpleAttributeSet();
        bgcolor = new Color(64, 224, 208);
        black_cells = new Color(170, 82, 45);
        white_cells = new Color(255, 250, 240);
        rank_col_name = new String[]{"POS", "SQUADRA", "PUNTI", "G", "V", "N", "P"};
        cal_col_name = new String[]{"", ""};
        fontSize = 12;
        cell_size = 70;
        width = 1920;
        height = 1080;
    }

    public static boolean isStarted() {
        return started;
    }

    public static void showGUI(BoardDataStructure board) {
        int i;
        started = true;
        UIManager.put("Panel.background", bgcolor);
        frame = new JFrame("Dipole");
        frame.setDefaultCloseOperation(3);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 0));
        font = new Font(UIManager.getFont("Panel.font").getFontName(), 0, fontSize);
        JPanel panel_board = new JPanel(null);
        panel_board.setLocation(0, 0);
        panel_board.setSize(new Dimension(cell_size * 9, cell_size * 9));
        graphic_board = new Piece[8][8];
        Color bgcolor = UIManager.getColor("Panel.background");
        panel_board.add(new Piece(bgcolor, cell_size, 0, 0));

        for (i = 0; i < 8; ++i) {
            panel_board.add((Component)new Piece(bgcolor, cell_size, 0, i + 1, "" + (1 + i)), true);
        }
        for (i = 0; i < 8; ++i) {
        	panel_board.add((Component)new Piece(bgcolor, cell_size, i + 1, 0, (char)(65 + i)), true);
        	for (int j = 0; j < 8; ++j) {
        		GUI.graphic_board[i][j] = (i + j) % 2 == 0 ? new Piece(white_cells, cell_size, i + 1, j + 1) : new Piece(black_cells, cell_size, i + 1, j + 1);
        		GUI.graphic_board[i][j].addMouseListener(new MouseClick(GUI.graphic_board[i][j]));                
        		panel_board.add(GUI.graphic_board[i][j]);

        	}
        }
        
        frame.add(panel_board);
        server_msg_area.setFont(font);
        server_msg_area.setEditable(false);
        server_msg_area.setBackground(Color.DARK_GRAY);
        server_msg_area.setForeground(Color.WHITE);
        JScrollPane scrollMsgArea = new JScrollPane(server_msg_area);
        scrollMsgArea.setPreferredSize(new Dimension(20, 1));
        scrollMsgArea.setVerticalScrollBarPolicy(22);
        scrollMsgArea.setHorizontalScrollBarPolicy(32);
        scrollMsgArea.setSize(new Dimension(panel_board.getWidth(), height - panel_board.getHeight() - 40));
        scrollMsgArea.setLocation(0, panel_board.getHeight());
        frame.add(scrollMsgArea);
        frame.setSize(width, height);
        frame.setLocation(0, 0);
        frame.setVisible(true);
        GUI.set_initial_configuration(board);
    }

    public static void set_initial_configuration(BoardDataStructure board) {
        graphic_board[BasicConfiguration.black_start_coodinates[0]][BasicConfiguration.black_start_coodinates[1]].repaint(board.get(BasicConfiguration.black_start_coodinates[0], BasicConfiguration.black_start_coodinates[1]));
        graphic_board[BasicConfiguration.white_start_coodinates[0]][BasicConfiguration.white_start_coodinates[1]].repaint(board.get(BasicConfiguration.white_start_coodinates[0], BasicConfiguration.white_start_coodinates[1]));
    }

    public static void refreshBoard(BoardDataStructure board) {
        for (int i = 0; i < board.num_rows(); ++i) {
            for (int j = 0; j < board.num_cols(); ++j) {
                graphic_board[i][j].repaint(board.get(i, j));
            }
        }
    }

    public static void publish_server(String text) {
        server_msg_area.append(text);
        server_msg_area.setCaretPosition(server_msg_area.getText().length());
    }

    public static void main(String[] args) throws Exception {
        BoardDataStructure board = new BoardDataStructure(8, 8);
        GUI.showGUI(board);
        int[] ta1 = new int[]{1, 2};
        board.add(ta1, Colour.White, 2);
        Thread.sleep(1000L);
        GUI.refreshBoard(board);
        int[] ta2 = new int[]{2, 3};
        board.add(ta2, Colour.Black, 10);
        Thread.sleep(1000L);
        GUI.refreshBoard(board);
        int[] ta3 = new int[]{3, 4};
        board.add(ta3, Colour.White, 3);
        Thread.sleep(1000L);
        GUI.refreshBoard(board);
    }

    public static void showProgressBar(String title, int time) {
        progressMonitor = new ProgressMonitor(frame, title, "", 0, time);
        progressMonitor.setProgress(0);
    }

    public static void refreshProgressBar(double f) {
        progressMonitor.setProgress((int)(f * (double)progressMonitor.getMaximum()));
    }

    public static void disposeProgressBar() {
        progressMonitor.close();
    }
    
    public static class MouseClick implements MouseListener{
    	Piece p;
    	public MouseClick(Piece piece) {
    		this.p = piece;
		}

    	@Override
    	public void mouseClicked(MouseEvent e) {
    		startingCell = p.getPosizione();
    		if (GUI.color.equals(p.color)) {
    			GUI.publish_server(startingCell.x + " - " + startingCell.y);
    		}
    	}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}

