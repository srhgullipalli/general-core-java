package com.sklg.game;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MazeJumpBFS {

	// Helper class to store cell coordinates and its current distance
	static class Cell {
		int r, c, dist;

		Cell(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	public static int minMovesToReachDestination(int[][] grid, int k) {
		// Edge Case: If grid is invalid or start/destination is blocked
		if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;

		int n = grid.length;
		int m = grid[0].length;

		if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1;
		if (n == 1 && m == 1) return 0; // Already at the destination

		// Direction vectors for Up, Down, Left, Right
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};

		// Distance matrix initialized to Infinity
		int[][] dist = new int[n][m];
		for (int[] row : dist) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}

		Queue<Cell> queue = new LinkedList<>();

		// Initialize starting position
		queue.add(new Cell(0, 0, 0));
		dist[0][0] = 0;

		while (!queue.isEmpty()) {
			Cell curr = queue.poll();

			// If we reached the bottom-right corner, return the minimum moves
			if (curr.r == n - 1 && curr.c == m - 1) {
				return curr.dist;
			}

			// Explore all 4 cardinal directions
			for (int i = 0; i < 4; i++) {
				// Jump from 1 up to k cells
				for (int x = 1; x <= k; x++) {
					int newR = curr.r + dRow[i] * x;
					int newC = curr.c + dCol[i] * x;

					// 1. Boundary Check
					if (newR < 0 || newR >= n || newC < 0 || newC >= m) {
						break; // Out of bounds, stop moving further in this direction
					}

					// 2. Obstacle Check
					if (grid[newR][newC] == 1) {
						break; // Blocked by wall, cannot jump over or onto it
					}

					// 3. Distance Optimization Check
					// If a cell was reached faster or equal by another path, 
					// it has already propagated further in this direction.
					if (dist[newR][newC] <= curr.dist) {
						break; 
					}

					// 4. Update and Enqueue if a shorter path is found
					if (dist[newR][newC] > curr.dist + 1) {
						dist[newR][newC] = curr.dist + 1;
						queue.add(new Cell(newR, newC, dist[newR][newC]));
					}
				}
			}
		}

		// Return -1 if the destination cell is unreachable
		return -1;
	}

	public static void main(String[] args) {
		// 0 = Empty, 1 = Obstacle
		int[][] maze = {
				{0, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 0, 0, 0},
				{0, 1, 1, 0}
		};
		int k = 2; // Maximum jump parameter

		int result = minMovesToReachDestination(maze, k);
		System.out.println("Minimum moves required: " + result);
	}
}
