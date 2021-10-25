package org.dida43.map.walker.characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharactersTest {

  @Test public void test_value_AllCharacters() {
    assertEquals('A', Characters.A.value());
    assertEquals('B', Characters.B.value());
    assertEquals('C', Characters.C.value());
    assertEquals('D', Characters.D.value());
    assertEquals('E', Characters.E.value());
    assertEquals('G', Characters.G.value());
    assertEquals('H', Characters.H.value());
    assertEquals('I', Characters.I.value());
    assertEquals('L', Characters.L.value());
    assertEquals('N', Characters.N.value());
    assertEquals('O', Characters.O.value());
    assertEquals('S', Characters.S.value());

    assertEquals('+', Characters.TURN.value());
    assertEquals('-', Characters.HORIZONTAL.value());
    assertEquals('|', Characters.VERTICAL.value());
    assertEquals('x', Characters.END.value());
    assertEquals('@', Characters.START.value());
    assertEquals(' ', Characters.SPACE.value());
    assertEquals('\u0000', Characters.NON_VALID.value());
  }

  @Test public void test_charset() {
    assertEquals("ABCDEGHILNOS+-|x@ ", Characters.charset());
  }

  @Test public void test_isPath() {
    assertTrue(Characters.isPath('A'));
    assertTrue(Characters.isPath('B'));
    assertTrue(Characters.isPath('C'));
    assertTrue(Characters.isPath('D'));
    assertTrue(Characters.isPath('E'));
    assertTrue(Characters.isPath('G'));
    assertTrue(Characters.isPath('H'));
    assertTrue(Characters.isPath('I'));
    assertTrue(Characters.isPath('L'));
    assertTrue(Characters.isPath('N'));
    assertTrue(Characters.isPath('O'));
    assertTrue(Characters.isPath('S'));

    assertTrue(Characters.isPath('+'));
    assertTrue(Characters.isPath('-'));
    assertTrue(Characters.isPath('|'));
    assertTrue(Characters.isPath('x'));

    assertFalse(Characters.isPath('@'));
    assertFalse(Characters.isPath(' '));
    assertFalse(Characters.isPath('\u0000'));
  }

  @Test public void test_isLetter() {
    assertTrue(Characters.isLetter('A'));
    assertTrue(Characters.isLetter('B'));
    assertTrue(Characters.isLetter('C'));
    assertTrue(Characters.isLetter('D'));
    assertTrue(Characters.isLetter('E'));
    assertTrue(Characters.isLetter('G'));
    assertTrue(Characters.isLetter('H'));
    assertTrue(Characters.isLetter('I'));
    assertTrue(Characters.isLetter('L'));
    assertTrue(Characters.isLetter('N'));
    assertTrue(Characters.isLetter('O'));
    assertTrue(Characters.isLetter('S'));

    assertFalse(Characters.isLetter('+'));
    assertFalse(Characters.isLetter('-'));
    assertFalse(Characters.isLetter('|'));
    assertFalse(Characters.isLetter('x'));
    assertFalse(Characters.isLetter('@'));
    assertFalse(Characters.isLetter(' '));
    assertFalse(Characters.isLetter('\u0000'));
  }
}
