package com.github.ywchang;

import java.util.Optional;

public class DistanceCalculator {
  private final Graph graph;
  private final String[] towns;

  public DistanceCalculator(Graph graph, String[] towns) {
    this.graph = graph;
    this.towns = towns;
  }

  public int getDistance() throws RouteNotFoundException {
    int totalDistance = 0;
    for (int i = 0; i < this.towns.length - 1; i++) {
      String start = this.towns[i];
      String end = this.towns[i + 1];
      Route matchingRoute = findRoute(start, end);
      totalDistance += matchingRoute.getDistance();
    }
    return totalDistance;
  }

  private Route findRoute(String start, String end) throws RouteNotFoundException {
    if (this.graph.outRoutes(start).isEmpty()) {
      throw new RouteNotFoundException(start, end);
    }
    final Optional<Route> matchingRoute = this.graph.outRoutes(start).stream()
        .filter(r -> r.getEnd().equals(end))
        .findFirst();
    if (!matchingRoute.isPresent()) {
      throw new RouteNotFoundException(start, end);
    }
    return matchingRoute.get();
  }
}
