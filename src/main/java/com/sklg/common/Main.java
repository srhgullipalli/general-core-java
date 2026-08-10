package com.sklg.common;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static class MazeCell {
		int row;
		int col;
		int distance;

		public MazeCell(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;

		}
	}
	public static void main(String[] args) {
		//Creating static maze with 0's and 1's
		int[][] maze = {
				{0, 0, 0, 0},
				{1, 1, 0, 1},
				{0, 0, 0, 0},
				{0, 1, 1, 0}
		};

		//Maximum jump 
		int k = 2;

		System.out.println("Minimum moves required: " + findMinimumMovesToReachDestination(maze, k));
	}

	static int findMinimumMovesToReachDestination(int[][] maze, int k) {
		//Handling edge cases
		int rows = maze.length;
		int cols = maze[0].length;
		if (maze.length == 0 || maze[0].length == 0 || maze[0][0] == 1 || maze[rows-1][cols-1] == 1) {
			return -1;
		}
		if (rows == 1 && cols == 1) {
			return 0;
		}

		// Direction vectors for Up, Down, Left, Right
		int[] dRow = {-1, 1, 0, 0};
		int[] dCol = {0, 0, -1, 1};

		int[][] dist = new int[rows][cols];
		for (int[] row : dist) {
			Arrays.fill(row, -1);
		}

		dist[0][0] = 0;
		Queue<MazeCell> queue = new LinkedList<>();
		queue.add(new MazeCell(0, 0, 0));

		while(!queue.isEmpty()) {
			MazeCell currentCell = queue.poll();

			if (currentCell.row == rows - 1 && currentCell.col == cols -1) {
				return currentCell.distance;
			}

			for (int i = 0; i < 4; i++) {
				for (int x = 1; x <= k; x++) {
					int newRow = currentCell.row + dRow[i] * x;
					int newCol = currentCell.col + dCol[i] * x;

					if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
						break;
					}
					if (maze[newRow][newCol] == 1) {
						break;
					}
					if(dist[newRow][newCol] <= currentCell.distance) {
						break;
					}

					if (dist[newRow][newCol] > currentCell.distance + 1) {
						dist[newRow][newCol] = currentCell.distance + 1;
						queue.add(new MazeCell(newRow, newCol, dist[newRow][newCol]));
					}
				}
			}

		}

		return -1;
	}
}
