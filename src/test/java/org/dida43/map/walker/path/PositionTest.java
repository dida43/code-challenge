package org.dida43.map.walker.path;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PositionTest {

  @Test public void test_equals() {
    assertEquals(new Position(0, 0), new Position(0, 0));
    assertNotEquals(new Position(0, 0), new Position(1, 0));
    assertNotEquals(new Position(0, 0), new Position(0, 1));
  }

  @Test public void test_newPosition() {
    assertEquals(new Position(0, 0).up(), new Position(-1, 0));
    assertEquals(new Position(0, 0).down(), new Position(1, 0));
    assertEquals(new Position(0, 0).left(), new Position(0, -1));
    assertEquals(new Position(0, 0).right(), new Position(0, 1));
  }
}
