package com.github.ywchang;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {
  private List<Route> routes = new ArrayList<>();

  public void addRoute(String route) {
    routes.add(Route.parse(route));
  }

  public int getDistance(String... towns) throws RouteNotFoundException {
    int totalDistance = 0;
    for (int i = 0; i < towns.length - 1; i++) {
      String start = towns[i];
      String end = towns[i + 1];
      Route matchingRoute = findRoute(start, end);
      totalDistance += matchingRoute.getDistance();
    }
    return totalDistance;
  }

  private Route findRoute(String start, String end) throws RouteNotFoundException {
    final Optional<Route> matchingRoute = routes.stream()
        .filter(r -> r.getStart().equals(start) && r.getEnd().equals(end))
        .findFirst();
    if (!matchingRoute.isPresent()) {
      throw new RouteNotFoundException(start, end);
    }
    return matchingRoute.get();
  }
}

