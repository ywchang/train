package com.github.ywchang;

public class Route {
  private String start;
  private String end;
  private int distance;

  public static Route parse(String route) {
    String startTown = route.substring(0, 1);
    String endTown = route.substring(1, 2);
    int distance = Integer.parseInt(route.substring(2));
    return new Route(startTown, endTown, distance);
  }

  private Route(String start, String end, int distance) {
    this.start = start;
    this.end = end;
    this.distance = distance;
  }

  public String getStart() {
    return start;
  }

  public String getEnd() {
    return end;
  }

  public int getDistance() {
    return distance;
  }
}
