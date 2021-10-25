package org.dida43.map.walker.map;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.dida43.map.walker.CodeChallengeException;
import org.dida43.map.walker.enums.PathDirection;
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

  @Test public void test_recordPath_ABasicExample() throws Exception {
    String mapAsString = resourceFileAsString("01ABasicExample");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ACB", path.asLetters());
    assertEquals("@---A---+|C|+---+|+-B-x", path.asCharacters());
  }

  @Test public void test_recordPath_GoStraightThroughIntersections() throws Exception {
    String mapAsString = resourceFileAsString("02GoStraightThroughIntersections");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ABCD", path.asLetters());
    assertEquals("@|A+---B--+|+--C-+|-||+---D--+|x", path.asCharacters());
  }

  @Test public void test_recordPath_LettersMayBeFoundOnTurns() throws Exception {
    String mapAsString = resourceFileAsString("03LettersMayBeFoundOnTurns");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ACB", path.asLetters());
    assertEquals("@---A---+|||C---+|+-B-x", path.asCharacters());
  }

  @Test public void test_recordPath_DoNotCollectALetterFromTheSameLocationTwice() throws Exception {
    String mapAsString = resourceFileAsString("04DoNotCollectALetterFromTheSameLocationTwice");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("GOONIES", path.asLetters());
    assertEquals("@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x", path.asCharacters());
  }

  @Test public void test_recordPath_KeepDirectionEvenInACompactSpace() throws Exception {
    String mapAsString = resourceFileAsString("05KeepDirectionEvenInACompactSpace");

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("BLAH", path.asLetters());
    assertEquals("@B+++B|+-L-+A+++A-+Hx", path.asCharacters());
  }

  @Test public void test_recordPath_NoStart() throws Exception {
    String mapAsString = resourceFileAsString("06NoStart");

    Assertions.assertThrows(AsciiMap.NoStartException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_NoEnd() throws Exception {
    String mapAsString = resourceFileAsString("07NoEnd");

    Assertions.assertThrows(AsciiMap.NoEndException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_MultipleStarts() throws Exception {
    String mapAsString = resourceFileAsString("08MultipleStarts");

    Assertions.assertThrows(AsciiMap.MultipleStartsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_MultipleEnds() throws Exception {
    String mapAsString = resourceFileAsString("09MultipleEnds");

    Assertions.assertThrows(AsciiMap.MultipleEndsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_TForksAMultipleEnds() throws Exception {
    String mapAsString = resourceFileAsString("10aTForksMultipleEnds");

    Assertions.assertThrows(AsciiMap.MultipleEndsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_TForksB() throws Exception {
    String mapAsString = resourceFileAsString("10bTForks");

    Assertions.assertThrows(PathDirection.TForkException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_BrokenPath() throws Exception {
    String mapAsString = resourceFileAsString("11BrokenPath");

    Assertions.assertThrows(AsciiMapWalker.BrokenPathException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_MultipleStartingPaths() throws Exception {
    String mapAsString = resourceFileAsString("12MultipleStartingPaths");

    Assertions.assertThrows(PathDirection.MultipleStartException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_FakeTurn() throws Exception {
    String mapAsString = resourceFileAsString("13FakeTurn");

    Assertions.assertThrows(PathDirection.FakeTurnException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_NonAsciiMap() throws Exception {
    String mapAsString = resourceFileAsString("14NonAsciiMap");

    Assertions.assertThrows(CodeChallengeException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void test_recordPath_EmptyMap() throws Exception {
    String mapAsString = resourceFileAsString("15EmptyMap");

    Assertions.assertThrows(CodeChallengeException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }
}
