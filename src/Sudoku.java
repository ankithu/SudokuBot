import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku {
	private int callbacks = 0;
	public int[][] solution;
	private int[] getNextToFill(int[][] puzzle) {
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j]==0) {
					int[] coordinate = {i,j};
					return coordinate;
				}
			}
		}
		int[] none = {-1,-1};
		return none;
	}
	private boolean isValid(int[][] puzzle, int[] coordinate, int guess) {
		//printPuzzle(puzzle);
		return (isHorizontal(puzzle, coordinate, guess)&&isVertical(puzzle, coordinate, guess)&&isBox(puzzle, coordinate, guess));
	}
	private boolean isHorizontal(int[][] puzzle, int[] coordinate,int guess) {
		int x = coordinate[0];
		int y = coordinate[1];
		for (int i = 0; i < 9; i ++) {
			if ((guess == puzzle[i][y])&&(x!=i)) {
				return false;
			}
		}
		return true;
	}
	private boolean isVertical(int[][] puzzle, int[] coordinate, int guess) {
		int x = coordinate[0];
		int y = coordinate[1];
		for (int i = 0; i < 9; i ++) {
			if ((guess == puzzle[x][i])&&(y!=i)) {
				return false;
			}
		}
		return true;
	}
	private boolean isBox(int[][] puzzle, int[] coordinate, int guess) {
		int x = coordinate[0];
		int y = coordinate[1];
		int startX = (x / 3)*3;
		int startY = (y / 3)*3;
		for (int i = startX; i < startX+3; i ++) {
			for (int j = startY; j < startY+3; j++) {
				if ((guess == puzzle[i][j])&&((y!=j)&&(x!=i))) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean solve(int[][] puzzle) {
		callbacks++;
		if (callbacks % 100 == 0) {
			//System.out.println("Call Backs: " + callbacks);
		}
		int[] coord = getNextToFill(puzzle);
		if (coord[0] == -1) {
			solution = puzzle;
			return true;
		}
		//System.out.println(Arrays.toString(coord));
		for (int guess = 1; guess <= 9; guess++) {
			if (isValid(puzzle, coord, guess)) {
				puzzle[coord[0]][coord[1]] = guess;
				if (solve(puzzle)) {
					return true;
				}
			}
			puzzle[coord[0]][coord[1]] = 0;
		}
		return false;
	}
	public void printPuzzle(int[][] puzzle) {
		if (puzzle == null) {
			System.out.println("No solution");
			return;
		}
		System.out.println();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j ++) {
				System.out.print(puzzle[i][j]+" ");
				if (j % 3 == 2) {
					System.out.print(" ");
				}
			}
			System.out.println();
			if (i % 3 == 2) {
				System.out.println();
			}
		}
	}
}
