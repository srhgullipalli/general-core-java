package com.sklg.datastacture.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSGraphExample {

	// Class to represent a Graph using an Adjacency List
	static class Graph {
		private final int vertices;
		private final List<List<Integer>> adjacencyList;

		// Constructor
		public Graph(int vertices) {
			this.vertices = vertices;
			this.adjacencyList = new ArrayList<>(vertices);
			for (int i = 0; i < vertices; i++) {
				this.adjacencyList.add(new ArrayList<>());
			}
		}

		// Method to add an edge to the graph (directed)
		public void addEdge(int source, int destination) {
			this.adjacencyList.get(source).add(destination);
			// Uncomment the line below if you want an undirected graph:
			// this.adjacencyList.get(destination).add(source);
		}

		// BFS traversal method from a given source node
		public void performBFS(int startVertex) {
			// Track visited nodes to prevent infinite loops from cycles
			boolean[] visited = new boolean[vertices];

			// Queue to manage the level-order exploration
			Queue<Integer> queue = new LinkedList<>();

			// Mark the starting node as visited and enqueue it
			visited[startVertex] = true;
			queue.add(startVertex);

			System.out.print("BFS Traversal starting from vertex " + startVertex + ": ");

			while (!queue.isEmpty()) {
				// Dequeue a vertex from the queue and print it
				int currentVertex = queue.poll();
				System.out.print(currentVertex + " ");

				// Get all adjacent vertices of the dequeued vertex
				for (int neighbor : adjacencyList.get(currentVertex)) {
					// If an adjacent vertex has not been visited, process it
					if (!visited[neighbor]) {
						visited[neighbor] = true;
						queue.add(neighbor);
					}
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// Create a graph with 5 vertices (0 to 4)
		Graph graph = new Graph(5);

		// Add directed edges
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 4);
		graph.addEdge(2, 4);

		// Execute BFS traversal starting from node 0
		graph.performBFS(0);
	}
}
