package com.sklg.game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MazeJumpPuzzleSolution {
	static int[] dirRow = {-1, 1, 0, 0};
	static int[] dirCol = {0, 0, -1, 1};

	public static void main(String[] args) {
		int rows = 5, cols = 5, jump = 2;
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				try {
					int parseInt = Integer.parseInt(args[i]);
					if (i == 0 && parseInt > 0) rows = parseInt;
					if (i == 1 && parseInt > 0) cols = parseInt;
					if (i == 2 && parseInt > 0 && parseInt < rows && parseInt < cols) jump = parseInt;
					if (i > 2) break;
				} catch (NumberFormatException nfe) {}
			}
		}
		int[][] maze = generateRandomPositionedAbstaclesMaze(rows, cols);
		int result = findMinimumNoOfMoves(maze, jump);

		if (result == -1) {
			System.out.println("Destination is unreachable.");
		} else {
			System.out.println("Minimum number of moves: " + result);
		}
	}

	private static int findMinimumNoOfMoves(int[][] maze, int k) {
		int n = maze.length;
		int m = maze[0].length;

		if (maze[0][0] == 1 || maze[n - 1][m - 1] == 1) {
			return -1;
		}
		if (n == 1 && m == 1) {
			return 0;
		}

		int[] dRow = {0, 1, 0, -1};
		int[] dCol = {1, 0, -1, 0};
		boolean[][] visited = new boolean[n][m];
		int[][] dist = new int[n][m];

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{0, 0});
		visited[0][0] = true;
		dist[0][0] = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int row = cur[0];
			int col = cur[1];

			if (row == n - 1 && col == m - 1) {
				return dist[row][col];
			}

			for (int dir = 0; dir < 4; dir++) {
				for (int x = 1; x <= k; x++) {
					int newRow = row + dRow[dir] * x;
					int newCol = col + dCol[dir] * x;

					// Stop extending this direction if we go out of bounds
					if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= m) {
						break;
					}
					// Stop extending this direction if we hit an obstacle
					if (maze[newRow][newCol] == 1) {
						break;
					}

					if (!visited[newRow][newCol]) {
						visited[newRow][newCol] = true;
						dist[newRow][newCol] = dist[row][col] + 1;
						queue.add(new int[]{newRow, newCol});
					}
				}
			}
		}

		return -1; // destination unreachable
	}

	private static int[][] generateRandomPositionedAbstaclesMaze(int rows, int cols) {
		int[][] maze = new int[rows][cols];
		Random random = new Random();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if ((row == col) || (row + col == rows)) {
					maze[row][col] = 0;
				} else {
					maze[row][col] = random.nextInt(2);
				}
			}
		}
		System.out.println("Dynamically generated Maze:");
		StringBuilder sb = new StringBuilder(System.lineSeparator());
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (col != 0) {
					sb.append(' ');
				}
				sb.append(maze[row][col]);
			}
			sb.append(System.lineSeparator());
		}
		System.out.println(sb.toString());
		return maze;
	}

	static class Position {
		int row;
		int col;

		Position (int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}
