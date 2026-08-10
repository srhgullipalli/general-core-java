package com.sklg.game;

import java.util.LinkedList;
import java.util.Queue;

public class BSFWithJump {

	// Define direction vectors for 1-step moves: up, down, left, right
	private static final int[] dRow = {-1, 1, 0, 0};
	private static final int[] dCol = {0, 0, -1, 1};

	public static void bfsWithJump(int[][] grid, int startX, int startY, int k) {
		int rows = grid.length;
		int cols = grid[0].length;

		// Visited matrix to keep track of already explored cells
		boolean[][] visited = new boolean[rows][cols];

		// Queue to store the coordinates of the cells to be explored
		Queue<int[]> queue = new LinkedList<>();

		// Initialize the starting point
		queue.add(new int[]{startX, startY});
		visited[startX][startY] = true;

		System.out.println("Starting BFS from (" + startX + ", " + startY + ") with jump k=" + k);

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];

			System.out.println("Visited: (" + r + ", " + c + ") Value: " + grid[r][c]);

			// 1. Explore 1-step direct neighbors (Up, Down, Left, Right)
			for (int i = 0; i < 4; i++) {
				int newRow = r + dRow[i];
				int newCol = c + dCol[i];

				if (isValid(newRow, newCol, rows, cols) && !visited[newRow][newCol]) {
					visited[newRow][newCol] = true;
					queue.add(new int[]{newRow, newCol});
				}
			}

			// 2. Explore 'k' jump moves
//			int[] jumpRow = {r - k, r + k, r, r};
//			int[] jumpCol = {c, c, c - k, c + k};
//
//			for (int i = 0; i < 4; i++) {
//				int jRow = jumpRow[i];
//				int jCol = jumpCol[i];
//
//				if (isValid(jRow, jCol, rows, cols) && !visited[jRow][jCol]) {
//					visited[jRow][jCol] = true;
//					queue.add(new int[]{jRow, jCol});
//				}
//			}
		}
	}

	// Helper method to check if the cell is within the 2D array boundaries
	private static boolean isValid(int r, int c, int rows, int cols) {
		return (r >= 0 && r < rows && c >= 0 && c < cols);
	}

	public static void main(String[] args) {
		// Sample 2D array (e.g., 5x5 grid)
		int[][] grid = {
				{0, 0, 1, 0, 0},
				{0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 0, 1, 0},
				{1, 1, 0, 0, 0}
		};

		int jumpK = 2; // Jump distance

		// Start BFS from the top-left corner (0,0)
		bfsWithJump(grid, 0, 0, jumpK);
	}
}
