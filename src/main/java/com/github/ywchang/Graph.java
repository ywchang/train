package com.github.ywchang;

import java.util.*;

public class Graph {
  private Map<String, List<Route>> map = new HashMap<>();

  public void addRoute(String routeStr) {
    final Route route = Route.parse(routeStr);
    if (!map.containsKey(route.getStart())) {
      map.put(route.getStart(), new ArrayList<>());
    }
    map.get(route.getStart()).add(route);
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
    if (!map.containsKey(start)) {
      throw new RouteNotFoundException(start, end);
    }
    final Optional<Route> matchingRoute = map.get(start).stream()
        .filter(r -> r.getEnd().equals(end))
        .findFirst();
    if (!matchingRoute.isPresent()) {
      throw new RouteNotFoundException(start, end);
    }
    return matchingRoute.get();
  }

  public int getTripsWithMaxStops(String start, String end, int maxStops) {
    return getTripsWithMaxStepsRecursively(start, end, maxStops, 0);
  }

  private int getTripsWithMaxStepsRecursively(String start, String end, int maxStops, int stops) {
    if (stops > maxStops) {
      return 0;
    }
    int result = 0;
    if (start.equals(end) && stops > 0) {
      result++;
    }
    if (!map.containsKey(start)) {
      return result;
    }
    for (Route route : map.get(start)) {
      result += getTripsWithMaxStepsRecursively(route.getEnd(), end, maxStops, stops + 1);
    }
    return result;
  }

  public int getTripsWithExactStops(String start, String end, int exactStops) {
    return getTripsWithExactStopsRecursively(start, end, exactStops, 0);
  }

  private int getTripsWithExactStopsRecursively(String start, String end, int exactStops, int stops) {
    if (stops > exactStops) {
      return 0;
    }
    int result = 0;
    if (start.equals(end) && stops == exactStops) {
      return result + 1;
    }
    if (!map.containsKey(start)) {
      return result;
    }
    for (Route route : map.get(start)) {
      result += getTripsWithExactStopsRecursively(route.getEnd(), end, exactStops, stops + 1);
    }
    return result;
  }
}

