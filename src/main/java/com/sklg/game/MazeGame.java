package com.sklg.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class MazeGame {

	static class State {
		int row, col;

		State(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static int minMoves(int[][] maze, int k) {
		int n = maze.length;
		int m = maze[0].length;

		// Start or destination is blocked
		if (maze[0][0] == 1 || maze[n - 1][m - 1] == 1) {
			return -1;
		}

		// Already at destination
		if (n == 1 && m == 1) {
			return 0;
		}

		// Distance / visited array
		int[][] dist = new int[n][m];
		for (int[] row : dist) {
			Arrays.fill(row, -1);
		}

		Queue<State> queue = new ArrayDeque<>();

		queue.offer(new State(0, 0));
		dist[0][0] = 0;

		// Four cardinal directions
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};

		while (!queue.isEmpty()) {
			State current = queue.poll();

			int r = current.row;
			int c = current.col;

			// Try all four directions
			for (int dir = 0; dir < 4; dir++) {

				// Try jumps from 1 to k cells
				for (int jump = 1; jump <= k; jump++) {
					int nr = r + dr[dir] * jump;
					int nc = c + dc[dir] * jump;

					// Outside maze -> further jumps in this direction
					// will also be outside, so stop.
					if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
						break;
					}

					// Obstacle blocks the entire jump path.
					// Therefore, we cannot jump beyond it either.
					if (maze[nr][nc] == 1) {
						break;
					}

					// If already visited, we don't need to add it again.
					if (dist[nr][nc] != -1) {
						continue;
					}

					dist[nr][nc] = dist[r][c] + 1;

					// Destination reached
					if (nr == n - 1 && nc == m - 1) {
						return dist[nr][nc];
					}

					queue.offer(new State(nr, nc));
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/*
		 * Input format:
		 *
		 * n m k
		 * maze row 1
		 * maze row 2
		 * ...
		 * maze row n
		 *
		 * Example:
		 * 4 4 2
		 * 0 0 1 0
		 * 0 0 0 0
		 * 1 0 1 0
		 * 0 0 0 0
		 */

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] maze = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < m; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(minMoves(maze, k));
	}
}
