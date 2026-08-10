package com.sklg.game;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class MazeJumpPuzzle {

	public static void main(String[] args) {
		int mazeSize = 5, mazeRowSize = 5, maxJumpSize = 2;
		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				try {
					int parsedInt = Integer.parseInt(args[i]);
					if (i == 0 && parsedInt > 0) mazeSize = parsedInt;
					if (i == 1 && parsedInt > 0) mazeRowSize = parsedInt;
					if (i == 2 && parsedInt > 0 && parsedInt < mazeSize && parsedInt < mazeRowSize) maxJumpSize = parsedInt;
					if (i > 2) break;
				} catch (NumberFormatException nfe) {}
			}
		}
		int[][] maze = generateRandomPositionedAbstaclesMaze(mazeSize, mazeRowSize);
		int result = findMinimumNoOfMoves(maze, maxJumpSize);

		if (result == -1) {
			System.out.println("Destination is unreachable.");
		} else {
			System.out.println("Minimum number of moves: " + result);
		}
	}

	private static int findMinimumNoOfMoves(int[][] maze, int maxJumpSize) {
		if (maze[0][0] == 1 || maze[maze.length-1][maze[0].length-1] == 1) {
			return -1;
		}
		if (maze.length == 1 && maze[0].length == 1) {
			return 0;
		}

		int[][] visited = new int[maze.length][maze[0].length];
		for (int[] row : visited) {
			Arrays.fill(row, -1);
		}
		visited[0][0] = 0;
		Queue<Position> queue = new ArrayDeque<>();
		queue.offer(new Position(0, 0));

		int[] directionRow = {0, 1, 0, -1};
		int[] directionCol = {1, 0, -1, 0};

		while (!queue.isEmpty()) {
			Position currentPostion = queue.poll();
			int currentRow = currentPostion.row;
			int currentCol = currentPostion.col;

			for (int i = 0; i < 4; i++) {
				for (int j = 1; j <= maxJumpSize; j++) {
					int newRow = currentRow + directionRow[i] * j;
					int newCol = currentCol + directionCol[i] * j;
					if (newRow < 0 || newRow >= maze.length || newCol < 0 || newCol >= maze[0].length) {
						break;
					}
					if (maze[newRow][newCol] == 1) {
						break;
					}
					if (visited[newRow][newCol] != -1) {
						continue;
					}
					visited[newRow][newCol] = visited[currentRow][currentCol] + 1;
					if (newRow == maze.length -1 && newCol == maze[0].length - 1) {
						return visited[newRow][newCol];
					}
					queue.offer(new Position(newRow, newCol));
				}
			}
		}
		return -1;
	}

	private static int[][] generateRandomPositionedAbstaclesMaze(int mazeSize, int mazeRowSize) {
		int[][] maze = new int[mazeSize][mazeRowSize];
		Random random = new Random();
		for (int row = 0; row < mazeSize; row++) {
			for (int col = 0; col < mazeRowSize; col++) {
				if ((row == col)) {
					maze[row][col] = 0;
				} else {
					maze[row][col] = random.nextInt(2);
				}
			}
		}
		System.out.println("Dynamically generated Maze:");
		StringBuilder sb = new StringBuilder(System.lineSeparator());
		for (int row = 0; row < mazeSize; row++) {
			for (int col = 0; col < mazeRowSize; col++) {
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
