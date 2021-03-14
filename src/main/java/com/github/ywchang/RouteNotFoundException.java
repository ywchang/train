package com.github.ywchang;

public class RouteNotFoundException extends Exception {
  public RouteNotFoundException(String startTown, String endTown) {
    super(String.format("Route from %s to %s does not exist", startTown, endTown));
  }
}
