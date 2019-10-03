
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException; 
  

public class Main { 
	
    public static void main(String[] args) throws InterruptedException, IOException, TesseractException 
    { 
        Tesseract tesseract = new Tesseract(); 
        Sudoku solver = new Sudoku();
        ComputerController controller = new ComputerController();
        

    	int[] topLeft = {Constants.SUDOKU_TOP_LEFT_X,Constants.SUDOKU_TOP_LEFT_Y};
		int[] size = {Constants.SUDOKU_BOARD_HEIGHT,Constants.SUDOKU_BOARD_HEIGHT};
		Thread.sleep(2000);
    	controller.takeScreenShot(topLeft, size);
        tesseract.setDatapath(Constants.TESSERACT_DATAPATH); 
        int number = 0;
        int[][] puzzle = new int[9][9];
        long ocrTime = System.currentTimeMillis();
        ArrayList<int[]> unfilledCoordinates = new ArrayList<int[]>();
        for (int i = 0; i < 9; i ++) {
        	for (int j = 0; j < 9; j++) {
        		 String text = tesseract.doOCR(new File(Constants.PUZZLE_IMG_STORAGE_DIR+ number +".jpg")); 
            	 text = text.replaceAll("\\s","");
            	 if (text.length() == 0) {
            		 puzzle[i][j] = 0;
            		 int[] coord = {i,j};
            		 unfilledCoordinates.add(coord);
            	 }
            	 else {
            		 puzzle[i][j] = Integer.parseInt(text);
            	 }
            	
               	 number++;
        	}
        }
        System.out.println("ocr time: " + ((System.currentTimeMillis()-ocrTime)/1000.0));
        long solveTime = System.currentTimeMillis();
        solver.solve(puzzle);
        System.out.println("puzzle solve time: " + ((System.currentTimeMillis()-solveTime)/1000.0));
   	 	solver.printPuzzle(solver.solution);
   	 	long enterTime = System.currentTimeMillis();
        controller.enterPuzzle(unfilledCoordinates, puzzle, topLeft);
        System.out.println("puzzle entry time: " + ((System.currentTimeMillis()-enterTime)/1000.0));
 
    } 
    
}
