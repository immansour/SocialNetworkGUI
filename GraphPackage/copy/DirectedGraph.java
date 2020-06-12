package GraphPackage.copy;
import java.util.Iterator;
import StackAndQueuePackage.*;

/**
 * DirectedGraph: An implementation of a Directed Graph that uses a Dictionary
 * 				  to hold the vertices, and an Adjecancy List to hold neighbors.
 * 				  It can be made an Undirected Graph by simply adding the reciprocal
 * 				  edge whenever adding an edge.
 * 
 * @author Yousef Helal
 * 
 * @version 1.0
 * 
 * @date 6/3/2020
 *
 */
public class DirectedGraph<T> implements GraphInterface<T> {
	
	private DictionaryInterface<T, VertexInterface<T>> vertices; //A Dictionary of all verticies stored in the Graph
	private int edgeCount; //A count of all the edges in the Graph

	/**
	 * A Constructor for DirectedGraph
	 */
	public DirectedGraph() {
		
		vertices = new LinkedDictionary<>();
		edgeCount = 0;
	
	} // end default constructor

	
	///============================================================================
	 
	/** 
	* Adds a given vertex to this graph.
    * @param vertexLabel  An object that labels the new vertex and is
    *                    distinct from the labels of current vertices.
    * @return  True if the vertex is added, or false if not. 
    */
	public boolean addVertex(final T vertexLabel) {
		
		
		VertexInterface<T> toAdd = new Vertex<T>(vertexLabel);
		VertexInterface<T> isAdded = this.vertices.add(vertexLabel, toAdd); //Will hold null if Vertex with same label did NOT exist before
		
		return (isAdded == null);
	}

	 
	/** 
	* Adds a weighted edge between two given distinct vertices that 
    * are currently in this graph. The desired edge must not already 
    * be in the graph. In a directed graph, the edge points toward
    * the second vertex given.
    * 
    * @param begin  An object that labels the origin vertex of the edge.
    * 
    * @param end    An object, distinct from begin, that labels the end
    *              vertex of the edge.
    * 
    * @param edgeWeight  The real value of the edge's weight.
    * 
    * @return  True if the edge is added, or false if not. 
    */
	public boolean addEdge(final T begin, final T end, final double weight) {

		VertexInterface<T> beginVer = this.vertices.getValue(begin);
		VertexInterface<T> endVer = this.vertices.getValue(end);
		
		boolean isSuccess = false;
		
		if ((beginVer != null) && (endVer != null)) {
			
			isSuccess = beginVer.connect(endVer, weight);
			
			if (isSuccess) {
				
				this.edgeCount++;
				
			}
			
		}

		return isSuccess;
	}
	
	/**
	 * Removes the edge between two vertices
	 * 
	 * @param begin The beginning of the edge to remove
	 * 
	 * @param end The end of the edge to remove
	 * 
	 * @return True if the remove is successful, False otherwise
	 */
	public boolean removeEdge(final T begin, final T end) {
		
		boolean result = false;
		
		if (vertices.contains(begin) && vertices.contains(end)) {
			
			vertices.getValue(begin).unConnect(vertices.getValue(end));
			result = true;
			
		}
		
		return result;
		
	}

	 
	/** Adds an unweighed edge between two given distinct vertices 
    * that are currently in this graph. The desired edge must not
    * already be in the graph. In a directed graph, the edge points 
    * toward the second vertex given.
    * 
    * @param begin  An object that labels the origin vertex of the edge.
    * 
    * @param end    An object, distinct from begin, that labels the end
    *              vertex of the edge.
    * @return  True if the edge is added, or false if not.
    */
	public boolean addEdge(final T begin, final T end) {
		
		boolean isSuccess = this.addEdge(begin, end, 0);
		
		return isSuccess;
		
	}

	/**
	 * Gets a vertex of the graph with the specified label
	 * 
	 * @param label The label of the vertex to get
	 * 
	 * @return The vertex if the graph contains the label, null otherwise
	 */
	public VertexInterface<T> getVertex(final T label) {
		
		VertexInterface<T> result = null;
		
		if (this.vertices.contains(label)) {
			
			result = this.vertices.getValue(label);
			
		}
		
		return result;
		
	}
	
	/** Sees whether an edge exists between two given vertices.
    * 
    * @param begin  An object that labels the origin vertex of the edge.
    * 
    * @param end    An object that labels the end vertex of the edge.
    * 
    * @return  True if an edge exists. 
    */
	public boolean hasEdge(final T begin, final T end) {
		
		VertexInterface<T> beginVer = this.vertices.getValue(begin);
		VertexInterface<T> endVer = this.vertices.getValue(end);
		
		boolean isFound = false;
		
		if (beginVer != null) {
			
			Iterator<VertexInterface<T>> neighbors = beginVer.getNeighborIterator();
			VertexInterface<T> iter;
			
			while ((!isFound) && (neighbors.hasNext())) {
				
				iter = neighbors.next();
				
				if (iter.equals(endVer)) {
					
					isFound = true;
					
				}
				
			}
		}
		
		return isFound;
	}

	/** 
	* Sees whether this graph is empty.
    * 
    * @return  True if the graph is empty. 
    */
	public boolean isEmpty() {
		
		return this.vertices.isEmpty();
	
	}

	/** 
	* Gets the number of vertices in this graph.
    * 
    * @return  The number of vertices in the graph.
    */
	public int getNumberOfVertices() {

		return this.vertices.getSize();
	
	}

	/** 
	* Gets the number of edges in this graph.
    * 
    * @return  The number of edges in the graph. 
    */
	public int getNumberOfEdges() {
	
		return this.edgeCount;

	}

	/**
	* Removes all vertices and edges from this graph.
	*/
	public void clear() {

		this.vertices.clear();
		this.edgeCount = 0;
		
	}

	/** Performs a breadth-first traversal of this graph.
    * @param origin  An object that labels the origin vertex of the traversal.
    * @return  A queue of labels of the vertices in the traversal, with
    *         the label of the origin vertex at the queue's front.
    */
	public QueueInterface<T> getBreadthFirstTraversal(T begin) {
		
		this.resetVertices();
		
		QueueInterface<T> order = new LinkedQueue<T>();
		QueueInterface<VertexInterface<T>> verticesQueue = new LinkedQueue<VertexInterface<T>>();
		
		VertexInterface<T> beginVer = this.vertices.getValue(begin);
		beginVer.visit();
		order.enqueue(begin);
		verticesQueue.enqueue(beginVer);
		
		VertexInterface<T> frontVer;
		Iterator<VertexInterface<T>> neighbors;
		VertexInterface<T> neighbor;
		
		while (!verticesQueue.isEmpty()) {
			
			frontVer = verticesQueue.dequeue();
			neighbors = frontVer.getNeighborIterator();
			
			while (neighbors.hasNext()) {
				
				neighbor = neighbors.next();
				
				if (!neighbor.isVisited()) {
					
					neighbor.visit();
					verticesQueue.enqueue(neighbor);
					order.enqueue(neighbor.getLabel());
					
				}
				
			}
			
		}
		
		return order;
		
	}
	
	///============================================================================
	
	protected void resetVertices() {
		
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		
		while (vertexIterator.hasNext()) {
		
			VertexInterface<T> nextVertex = vertexIterator.next();
			nextVertex.unvisit();
			nextVertex.setCost(0);
			nextVertex.setPredecessor(null);
		
		} // end while
	
	} // end resetVertices

	
	
	public QueueInterface<T> getDepthFirstTraversal(T origin) {
// Assumes graph is not empty
		resetVertices();
		QueueInterface<T> traversalOrder = new LinkedQueue<T>();
		StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();

		VertexInterface<T> originVertex = vertices.getValue(origin);
		originVertex.visit();
		traversalOrder.enqueue(origin); // Enqueue vertex label
		vertexStack.push(originVertex); // Enqueue vertex

		while (!vertexStack.isEmpty()) {
	
			VertexInterface<T> topVertex = vertexStack.peek();
			VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();

			if (nextNeighbor != null) {
			
				nextNeighbor.visit();
				traversalOrder.enqueue(nextNeighbor.getLabel());
				vertexStack.push(nextNeighbor);
		
			} else // All neighbors are visite
				vertexStack.pop();
		
		} // end while

		return traversalOrder;
	
	} // end getDepthFirstTraversal

	
	public StackInterface<T> getTopologicalOrder() {
	
		resetVertices();

		StackInterface<T> vertexStack = new LinkedStack<>();
		int numberOfVertices = getNumberOfVertices();
		
		for (int counter = 1; counter <= numberOfVertices; counter++) {
		
			VertexInterface<T> nextVertex = findTerminal();
			nextVertex.visit();
			vertexStack.push(nextVertex.getLabel());
		
		} // end for

		return vertexStack;
	
	} // end getTopologicalOrder

	
	
	/** Precondition: path is an empty stack (NOT null) */
	public int getShortestPath(T begin, T end, StackInterface<T> path) {
	
		resetVertices();
		boolean done = false;
		QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();

		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		originVertex.visit();
// Assertion: resetVertices() has executed setCost(0)
// and setPredecessor(null) for originVertex

		vertexQueue.enqueue(originVertex);

		while (!done && !vertexQueue.isEmpty()) {
		
			VertexInterface<T> frontVertex = vertexQueue.dequeue();

			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			
			while (!done && neighbors.hasNext()) {
			
				VertexInterface<T> nextNeighbor = neighbors.next();

				if (!nextNeighbor.isVisited()) {
				
					nextNeighbor.visit();
					nextNeighbor.setCost(1 + frontVertex.getCost());
					nextNeighbor.setPredecessor(frontVertex);
					vertexQueue.enqueue(nextNeighbor);
				
				} // end if

				if (nextNeighbor.equals(endVertex))
					done = true;
			
			} // end while
		} // end while

// Traversal ends; construct shortest path
		int pathLength = (int) endVertex.getCost();
		path.push(endVertex.getLabel());

		VertexInterface<T> vertex = endVertex;
		while (vertex.hasPredecessor()) {
			
			vertex = vertex.getPredecessor();
			path.push(vertex.getLabel());
	
		} // end while

		return pathLength;
	} // end getShortestPath

	
	
	/** Precondition: path is an empty stack (NOT null) */
	public double getCheapestPath(T begin, T end, StackInterface<T> path) {
		
		resetVertices();
		boolean done = false;

// Use EntryPQ instead of Vertex because multiple entries contain
// the same vertex but different costs - cost of path to vertex is EntryPQ's priority value
		PriorityQueueInterface<EntryPQ> priorityQueue = new HeapPriorityQueue<>();

		VertexInterface<T> originVertex = vertices.getValue(begin);
		VertexInterface<T> endVertex = vertices.getValue(end);

		priorityQueue.add(new EntryPQ(originVertex, 0, null));

		while (!done && !priorityQueue.isEmpty()) {
		
			EntryPQ frontEntry = priorityQueue.remove();
			VertexInterface<T> frontVertex = frontEntry.getVertex();

			if (!frontVertex.isVisited()) {
			
				frontVertex.visit();
				frontVertex.setCost(frontEntry.getCost());
				frontVertex.setPredecessor(frontEntry.getPredecessor());

				if (frontVertex.equals(endVertex))
					done = true;
				else {
				
					Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
					Iterator<Double> edgeWeights = frontVertex.getWeightIterator();
					
					while (neighbors.hasNext()) {
					
						VertexInterface<T> nextNeighbor = neighbors.next();
						Double weightOfEdgeToNeighbor = edgeWeights.next();

						if (!nextNeighbor.isVisited()) {
						
							double nextCost = weightOfEdgeToNeighbor + frontVertex.getCost();
							priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
						
						} // end if
					
					} // end while
			
				} // end if
		
			} // end if
	
		} // end while

// Traversal ends, construct cheapest path
		double pathCost = endVertex.getCost();
		path.push(endVertex.getLabel());

		VertexInterface<T> vertex = endVertex;
		while (vertex.hasPredecessor()) {

			vertex = vertex.getPredecessor();
	
			path.push(vertex.getLabel());
		} // end while

		return pathCost;

	} // end getCheapestPath

	
	
	
	protected VertexInterface<T> findTerminal() {
	
		boolean found = false;
		VertexInterface<T> result = null;

		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();

		while (!found && vertexIterator.hasNext()) {
		
			VertexInterface<T> nextVertex = vertexIterator.next();

// If nextVertex is unvisited AND has only visited neighbors)
			if (!nextVertex.isVisited()) {
			
				if (nextVertex.getUnvisitedNeighbor() == null) {
				
					found = true;
					result = nextVertex;
			
				} // end if
			
			} // end if
		
		} // end while

		return result;
	
	} // end findTerminal

// Used for testing
	public void displayEdges() {
	
		System.out.println("\nEdges exist from the first vertex in each line to the other vertices in the line.");
		System.out.println("(Edge weights are given; weights are zero for unweighted graphs):\n");
		Iterator<VertexInterface<T>> vertexIterator = vertices.getValueIterator();
		
		while (vertexIterator.hasNext()) {
		
			((Vertex<T>) (vertexIterator.next())).display();
		
		} // end while
	
	} // end displayEdges

	private class EntryPQ implements Comparable<EntryPQ> {
	
		private VertexInterface<T> vertex;
		private VertexInterface<T> previousVertex;
		private double cost; // cost to nextVertex

		private EntryPQ(VertexInterface<T> vertex, double cost, VertexInterface<T> previousVertex) {
		
			this.vertex = vertex;
			this.previousVertex = previousVertex;
			this.cost = cost;
		
		} // end constructor

		public VertexInterface<T> getVertex() {
		
			return vertex;
		
		} // end getVertex

		public VertexInterface<T> getPredecessor() {
		
			return previousVertex;
		
		} // end getPredecessor

		public double getCost() {
		
			return cost;
		
		} // end getCost

		public int compareTo(EntryPQ otherEntry) {
// Using opposite of reality since our priority queue uses a maxHeap;
// could revise using a minheap
			return (int) Math.signum(otherEntry.cost - cost);
		
		} // end compareTo

		public String toString() {
		
			return vertex.toString() + " " + cost;
		
		} // end toString
	
	} // end EntryPQ
} // end DirectedGraph