package com.github.ywchang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest {
  @Test
  public void should_parse_a_route_from_string() {
    Route route = Route.parse("AB15");
    assertEquals("A", route.getStart());
    assertEquals("B", route.getEnd());
    assertEquals(15, route.getDistance());
  }
}
