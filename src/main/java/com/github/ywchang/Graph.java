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

  public List<Route> outRoutes(String town) {
    if (!map.containsKey(town)) {
      return new ArrayList<>();
    }
    return map.get(town);
  }

  public int getDistance(String... towns) throws RouteNotFoundException {
    return new DistanceCalculator(this, towns).getDistance();
  }

  public int getTripsWithMaxStops(String start, String end, int maxStops) {
    return new TripsCalculator(this, start, end).getTripsWithMaxStops(maxStops);
  }

  public int getTripsWithExactStops(String start, String end, int exactStops) {
    return new TripsCalculator(this, start, end).getTripsWithExactStops(exactStops);
  }

}

