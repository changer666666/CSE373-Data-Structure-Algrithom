package astar;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;


/**
 * @see ShortestPathsSolver for more method documentation
 */
public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private Map<Vertex, Double> distTo;
    private Map<Vertex, Vertex> edgeTo;
    private TreeMapMinPQ pq;
    private Set<Vertex> visited;

    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;

    /**
     * Immediately solves and stores the result of running memory optimized A*
     * search, computing everything necessary for all other methods to return
     * their results in constant time. The timeout is given in seconds.
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        solution = new ArrayList<>();
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        pq = new TreeMapMinPQ<>();
        visited = new HashSet<>();

        Stopwatch sw = new Stopwatch();
        distTo.put(start, 0.0);
        pq.add(start, distTo.get(start) + input.estimatedDistanceToGoal(start, end));
        edgeTo.put(start, null);
        numStatesExplored = 0;

        while (!pq.isEmpty()) {
            Vertex curNode = (Vertex) pq.removeSmallest();
            visited.add(curNode);
            numStatesExplored++;
            timeSpent = sw.elapsedTime();
            if (timeSpent > timeout) {
                //timeout
                outcome = SolverOutcome.TIMEOUT;
                throw new UnsupportedOperationException();
            }

            if (curNode.equals(end)) {
                //end
                findSolution(start, end);
                findSolutionWeight(end);
                outcome = SolverOutcome.SOLVED;
                timeSpent = sw.elapsedTime();
                return;
            }
            for (WeightedEdge<Vertex> neighborEdge : input.neighbors(curNode)) {
                Vertex neighbor;
                neighbor = neighborEdge.to();

                double curDist = distTo.get(curNode) + neighborEdge.weight();
                if (!pq.contains(neighbor) && !visited.contains(neighbor)) {
                    pq.add(neighbor, curDist + input.estimatedDistanceToGoal(neighbor, end));
                }
                if (!distTo.containsKey(neighbor)) {
                    distTo.put(neighbor, curDist);
                    edgeTo.put(neighbor, curNode);
                } else if (curDist < distTo.get(neighbor)) {
                    distTo.put(neighbor, curDist);
                    edgeTo.put(neighbor, curNode);
                    pq.changePriority(neighbor, curDist + input.estimatedDistanceToGoal(neighbor, end));
                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
    }

    private void findSolution(Vertex start, Vertex end) {
        solution.add(end);
        if (end.equals(start)) {
            return;
        }

        Vertex curNode = edgeTo.get(end);
        while (!curNode.equals(start)) {
            solution.add(0, curNode);
            curNode = edgeTo.get(curNode);
        }
        solution.add(0, start);
    }

    private void findSolutionWeight(Vertex end) {
        solutionWeight = distTo.get(end);
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    /** The total number of priority queue removeSmallest operations. */
    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
