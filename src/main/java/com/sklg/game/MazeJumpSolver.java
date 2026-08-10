package com.sklg.game;

import java.util.LinkedList;
import java.util.Queue;

public class MazeJumpSolver {

	// Directions: right, down, left, up
	static final int[] dRow = {0, 1, 0, -1};
	static final int[] dCol = {1, 0, -1, 0};

	/**
	 * Returns the minimum number of moves to go from (0,0) to (n-1,m-1)
	 * where each move slides 1..k cells in a cardinal direction, and every
	 * cell along the path (including the destination) must be in-bounds
	 * and obstacle-free (0). Returns -1 if unreachable.
	 */
	public static int minMoves(int[][] maze, int k) {
		int n = maze.length;
		int m = maze[0].length;

		if (maze[0][0] == 1 || maze[n - 1][m - 1] == 1) {
			return -1;
		}
		if (n == 1 && m == 1) {
			return 0;
		}

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

	public static void main(String[] args) {
		int[][] maze = {
				{0, 0, 1, 0, 0},
				{0, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 0, 1, 0},
				{1, 1, 0, 0, 0}
		};

		int k = 2;

		int result = minMoves(maze, k);

		if (result == -1) {
			System.out.println("Destination is unreachable.");
		} else {
			System.out.println("Minimum number of moves: " + result);
		}
	}
}
