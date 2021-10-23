package org.dida43.map.walker.map;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.dida43.map.walker.exceptions.AsciiMapException;
import org.dida43.map.walker.exceptions.map.MultipleEndsException;
import org.dida43.map.walker.exceptions.map.MultipleStartsException;
import org.dida43.map.walker.exceptions.map.NoEndException;
import org.dida43.map.walker.exceptions.map.NoStartException;
import org.dida43.map.walker.exceptions.path.BrokenPathDirectionException;
import org.dida43.map.walker.exceptions.path.FakeTurnPathDirectionException;
import org.dida43.map.walker.exceptions.path.MultipleStartingPathDirectionException;
import org.dida43.map.walker.exceptions.path.TForkPathDirectionException;
import org.dida43.map.walker.pojos.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsciiMapWalkerTest {

  private String resourceFileAsString(String resourceFileName)
    throws IOException, URISyntaxException
  {
    URL res = getClass().getClassLoader().getResource(resourceFileName);
    File file = Paths.get(res.toURI()).toFile();
    byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    return new String(encoded, StandardCharsets.UTF_8);
  }

  @Test public void aBasicExample() throws Exception {
    String mapAsString = resourceFileAsString("01ABasicExample");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ACB", path.asLetters());
    assertEquals("@---A---+|C|+---+|+-B-x", path.asCharacters());
  }

  @Test public void goStraightThroughIntersections() throws Exception {
    String mapAsString = resourceFileAsString("02GoStraightThroughIntersections");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ABCD", path.asLetters());
    assertEquals("@|A+---B--+|+--C-+|-||+---D--+|x", path.asCharacters());
  }

  @Test public void lettersMayBeFoundOnTurns() throws Exception {
    String mapAsString = resourceFileAsString("03LettersMayBeFoundOnTurns");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ACB", path.asLetters());
    assertEquals("@---A---+|||C---+|+-B-x", path.asCharacters());
  }

  @Test public void doNotCollectALetterFromTheSameLocationTwice() throws Exception {
    String mapAsString = resourceFileAsString("04DoNotCollectALetterFromTheSameLocationTwice");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("GOONIES", path.asLetters());
    assertEquals("@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x", path.asCharacters());
  }

  @Test public void keepDirectionEvenInACompactSpace() throws Exception {
    String mapAsString = resourceFileAsString("05KeepDirectionEvenInACompactSpace");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("BLAH", path.asLetters());
    assertEquals("@B+++B|+-L-+A+++A-+Hx", path.asCharacters());
  }

  @Test public void noStart() throws Exception {
    String mapAsString = resourceFileAsString("06NoStart");

    Assertions.assertThrows(NoStartException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void noEnd() throws Exception {
    String mapAsString = resourceFileAsString("07NoEnd");

    Assertions.assertThrows(NoEndException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void multipleStarts() throws Exception {
    String mapAsString = resourceFileAsString("08MultipleStarts");

    Assertions.assertThrows(MultipleStartsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void multipleEnds() throws Exception {
    String mapAsString = resourceFileAsString("09MultipleEnds");

    Assertions.assertThrows(MultipleEndsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void tForksAMultipleEnds() throws Exception {
    String mapAsString = resourceFileAsString("10aTForksMultipleEnds");

    Assertions.assertThrows(MultipleEndsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void tForksB() throws Exception {
    String mapAsString = resourceFileAsString("10bTForks");

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void brokenPath() throws Exception {
    String mapAsString = resourceFileAsString("11BrokenPath");

    Assertions.assertThrows(BrokenPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void multipleStartingPaths() throws Exception {
    String mapAsString = resourceFileAsString("12MultipleStartingPaths");

    Assertions.assertThrows(MultipleStartingPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void fakeTurn() throws Exception {
    String mapAsString = resourceFileAsString("13FakeTurn");

    Assertions.assertThrows(FakeTurnPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void nonAsciiMap() throws Exception {
    String mapAsString = resourceFileAsString("14NonAsciiMap");

    Assertions.assertThrows(AsciiMapException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void emptyMap() throws Exception {
    String mapAsString = resourceFileAsString("15EmptyMap");

    Assertions.assertThrows(AsciiMapException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }
}
