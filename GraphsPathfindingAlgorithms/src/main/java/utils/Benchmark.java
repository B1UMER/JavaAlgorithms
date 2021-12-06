package utils;

import algorithms.GraphSearchAlgorithms;
import graphs.Graph;
import graphs.GraphWeighted;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@Threads(1)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g",})
public class Benchmark {

  TestDataGraph testDataGraph;
  TestDataGraphWeight testDataGraphWeight;

  @State(Scope.Thread)
  public static class TestDataGraph {

    static Graph testGraph = new Graph(true);
    static Node startNode;

    void init() {
      Node a = new Node(0, "0");
      Node b = new Node(1, "1");
      Node c = new Node(2, "2");
      Node d = new Node(3, "3");
      Node e = new Node(4, "4");

      testGraph.addEdge(a, d);
      testGraph.addEdge(a, b);
      testGraph.addEdge(a, c);
      testGraph.addEdge(c, d);
      startNode = new Node(0, "0");
    }
  }

  public static class TestDataGraphWeight {

    static GraphWeighted testGraphWeight = new GraphWeighted(true);
    static NodeWeighted sourceNode;
    static NodeWeighted destNode;

    void init() {
      NodeWeighted zero = new NodeWeighted(0, "0");
      NodeWeighted one = new NodeWeighted(1, "1");
      NodeWeighted two = new NodeWeighted(2, "2");
      NodeWeighted three = new NodeWeighted(3, "3");
      NodeWeighted four = new NodeWeighted(4, "4");
      NodeWeighted five = new NodeWeighted(5, "5");
      NodeWeighted six = new NodeWeighted(6, "6");

      testGraphWeight.addEdge(zero, one, 8);
      testGraphWeight.addEdge(zero, two, 11);
      testGraphWeight.addEdge(one, three, 3);
      testGraphWeight.addEdge(one, four, 8);
      testGraphWeight.addEdge(one, two, 7);
      testGraphWeight.addEdge(two, four, 9);
      testGraphWeight.addEdge(three, four, 5);
      testGraphWeight.addEdge(three, five, 2);
      testGraphWeight.addEdge(four, six, 6);
      testGraphWeight.addEdge(five, four, 1);
      testGraphWeight.addEdge(five, six, 8);

      sourceNode = testGraphWeight.getNode(0);
      destNode = testGraphWeight.getNode(6);
    }
  }

  @Setup(Level.Trial)
  public void initGraph() {
    testDataGraph = new TestDataGraph();
    testDataGraph.init();
  }

  @Setup(Level.Trial)
  public void initGraphWeight() {
    testDataGraphWeight = new TestDataGraphWeight();
    testDataGraphWeight.init();
  }


  @org.openjdk.jmh.annotations.Benchmark
  public void measureDFS() {
    GraphSearchAlgorithms
        .depthFirstSearchModified(TestDataGraph.startNode, TestDataGraph.testGraph);
  }

  @org.openjdk.jmh.annotations.Benchmark
  public void measureBFS() {
    GraphSearchAlgorithms.breadthFirstSearch(TestDataGraph.startNode, TestDataGraph.testGraph);
  }

  @org.openjdk.jmh.annotations.Benchmark
  public void measureDijkstra() {
    GraphSearchAlgorithms.dijkstraShortestPath(TestDataGraphWeight.sourceNode,
        TestDataGraphWeight.destNode,
        TestDataGraphWeight.testGraphWeight);
  }

  @org.openjdk.jmh.annotations.Benchmark
  public void measureFordBellman() {
    GraphSearchAlgorithms.fordBellman(TestDataGraphWeight.sourceNode,
        TestDataGraphWeight.destNode,
        TestDataGraphWeight.testGraphWeight
    );
  }

}
