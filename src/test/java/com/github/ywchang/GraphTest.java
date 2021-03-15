package com.github.ywchang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
  @Test
  public void should_get_distance_of_a_simple_route() throws RouteNotFoundException {
    Graph graph = new Graph();
    graph.addRoute("AB5");
    graph.addRoute("BC4");
    assertEquals(9, graph.getDistance("A", "B", "C"));
  }

  @Test
  public void should_get_distance_of_a_complex_route() throws RouteNotFoundException {
    Graph graph = new Graph();
    graph.addRoute("AE7");
    graph.addRoute("EB3");
    graph.addRoute("BC4");
    graph.addRoute("CD8");
    assertEquals(22, graph.getDistance("A", "E", "B", "C", "D"));
  }

  @Test
  public void should_throw_exception_if_route_does_not_exist() {
    Graph graph = new Graph();
    assertThrows(RouteNotFoundException.class, () -> {
      graph.getDistance("A", "B");
    });
  }

  @Test
  public void should_return_trips_with_maximum_stops() {
    Graph graph = new Graph();
    graph.addRoute("CE2");
    graph.addRoute("EB3");
    graph.addRoute("BC4");
    assertEquals(1, graph.getTripsWithMaxStops("C", "C", 3));
  }

  @Test
  public void should_return_trips_with_maximum_stops_with_loop() {
    Graph graph = new Graph();
    graph.addRoute("CD8");
    graph.addRoute("DC8");
    assertEquals(2, graph.getTripsWithMaxStops("C", "C", 4));
  }

  @Test
  public void should_return_trips_with_exact_stops() {
    Graph graph = new Graph();
    graph.addRoute("CE2");
    graph.addRoute("EB3");
    graph.addRoute("BC4");
    assertEquals(1, graph.getTripsWithExactStops("C", "C", 3));
    assertEquals(0, graph.getTripsWithExactStops("C", "C", 4));
  }

  @Test
  public void should_return_trips_with_exact_stops_with_loop() {
    Graph graph = new Graph();
    graph.addRoute("CD8");
    graph.addRoute("DC8");
    assertEquals(1, graph.getTripsWithExactStops("C", "C", 4));
  }
}
