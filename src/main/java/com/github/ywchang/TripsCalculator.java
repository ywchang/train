package com.github.ywchang;

public class TripsCalculator {
  private final Graph graph;
  public final String start;
  public final String end;

  public TripsCalculator(Graph graph, String start, String end) {
    this.graph = graph;
    this.start = start;
    this.end = end;
  }

  public int getTripsWithExactStops(int exactStops) {
    return getTripsWithExactStopsRecursively(this.graph, this.start, this.end, exactStops, 0);
  }

  private int getTripsWithExactStopsRecursively(Graph graph, String start, String end, int exactStops, int stops) {
    if (stops > exactStops) {
      return 0;
    }
    int result = 0;
    if (start.equals(end) && stops == exactStops) {
      return result + 1;
    }
    for (Route route : graph.outRoutes(start)) {
      result += this.getTripsWithExactStopsRecursively(graph, route.getEnd(), end, exactStops, stops + 1);
    }
    return result;
  }

  public int getTripsWithMaxStops(int maxStops) {
    return getTripsWithMaxStopsRecursively(this.graph, this.start, this.end, maxStops, 0);
  }

  private int getTripsWithMaxStopsRecursively(Graph graph, String start, String end, int maxStops, int stops) {
    if (stops > maxStops) {
      return 0;
    }
    int result = 0;
    if (start.equals(end) && stops > 0) {
      result++;
    }
    for (Route route : graph.outRoutes(start)) {
      result += this.getTripsWithMaxStopsRecursively(graph, route.getEnd(), end, maxStops, stops + 1);
    }
    return result;
  }
}
