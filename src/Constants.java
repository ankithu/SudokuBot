import net.sourceforge.tess4j.Tesseract;

public class Constants {
	//Coordinates of the top left corner of the sudoku board (just numbers)
	public static final int SUDOKU_TOP_LEFT_X = 610;
	public static final int SUDOKU_TOP_LEFT_Y = 349;
	//dimensions of board
	public static final int SUDOKU_BOARD_WIDTH = 465;
	public static final int SUDOKU_BOARD_HEIGHT = 465;
	//dimensinos of each square in the board
	public static final int CELL_WIDTH = 51;
	public static final int CELL_HEIGHT = 51;
	//small amount of offset that allows for room for slight error when screen shotting and when clicking
	public static final int CURSOR_OFFSET = 10;
	//datapath to tesseract data
	public static final String TESSERACT_DATAPATH = "/Users/ankithudupa/Downloads/Tess4J/tessdata";
	//directory for storing images
	public static final String PUZZLE_IMG_STORAGE_DIR = "/Users/ankithudupa/Desktop/test/";
	
}
