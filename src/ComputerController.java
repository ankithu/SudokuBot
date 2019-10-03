import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ComputerController {
	private Robot robot;
	private String fileName;
	public ComputerController() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void takeScreenShot(int[] topLeft, int[] size) throws InterruptedException, IOException {
		Rectangle rect = new Rectangle();
		rect.x = topLeft[0];
		rect.y = topLeft[1];
		rect.height = size[1];
		rect.width = size[0];
		BufferedImage Image = robot.createScreenCapture(rect); 
		
        
        int idx = 0;    
        for (int y = Constants.CURSOR_OFFSET; y <= Image.getWidth()-35; y += Constants.CELL_HEIGHT) {
        	for (int x = Constants.CURSOR_OFFSET; x <= Image.getHeight()-35; x += Constants.CELL_WIDTH) {
                ImageIO.write(Image.getSubimage(x, y, 35, 35), "jpg", new File(Constants.PUZZLE_IMG_STORAGE_DIR+/*fileName.replaceAll(".jpg", "")+"/" */+ idx++ + ".jpg"));
            }
        }
		
	}
	
	public void enterPuzzle(ArrayList<int[]> unfilled, int[][] puzzle, int[] topLeft) throws InterruptedException {

		int currentX = topLeft[0] + Constants.CURSOR_OFFSET;
		int currentY = topLeft[1] + Constants.CURSOR_OFFSET;
		int delay = 10;
		System.out.println(unfilled);
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j++) {
				//System.out.println("step 1");
				int[] target = {i,j};
				boolean modify = false;
				for (int ii = 0; ii < unfilled.size(); ii++) {
					if ((unfilled.get(ii)[0] == i) && (unfilled.get(ii)[1] == j)) {
						modify = true;
						break;
					}
				}
				if (modify) {
					robot.mouseMove(currentX+10, currentY+10);
					Thread.sleep(delay);
					
					robot.keyPress(getKeyCode(puzzle[i][j]));
					Thread.sleep(delay);
					
					robot.keyRelease(getKeyCode(puzzle[i][j]));
					Thread.sleep(delay);
				
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(delay);
					
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
					Thread.sleep(delay);
				}
				currentX += Constants.CELL_WIDTH;
			}
			currentX = topLeft[0];
			currentY += Constants.CELL_HEIGHT;
		}
	}
	private int getKeyCode(int input) {
		switch (input){
		case 1:
			return KeyEvent.VK_1;
		case 2:
			return KeyEvent.VK_2;
		case 3:
			return KeyEvent.VK_3;
		case 4:
			return KeyEvent.VK_4;
		case 5:
			return KeyEvent.VK_5;
		case 6:
			return KeyEvent.VK_6;
		case 7:
			return KeyEvent.VK_7;
		case 8:
			return KeyEvent.VK_8;
		case 9:
			return KeyEvent.VK_9;
		default:
			return -1;
			
		}
	}
}
