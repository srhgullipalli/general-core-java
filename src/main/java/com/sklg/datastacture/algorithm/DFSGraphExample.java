package com.sklg.datastacture.algorithm;

import java.util.ArrayList;
import java.util.List;

public class DFSGraphExample {

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

		// Helper method for recursive DFS traversal
		private void dfsHelper(int currentVertex, boolean[] visited) {
			// Mark the current node as visited and print it
			visited[currentVertex] = true;
			System.out.print(currentVertex + " ");

			// Recur for all the vertices adjacent to this vertex
			for (int neighbor : adjacencyList.get(currentVertex)) {
				if (!visited[neighbor]) {
					dfsHelper(neighbor, visited);
				}
			}
		}

		// Main DFS traversal method from a given source node
		public void performDFS(int startVertex) {
			// Track visited nodes to prevent infinite loops from cycles
			boolean[] visited = new boolean[vertices];

			System.out.print("DFS Traversal starting from vertex " + startVertex + ": ");

			// Invoke the recursive helper function
			dfsHelper(startVertex, visited);
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

		// Execute DFS traversal starting from node 0
		graph.performDFS(0);
	}
}
