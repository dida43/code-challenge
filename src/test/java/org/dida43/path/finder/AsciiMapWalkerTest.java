package org.dida43.path.finder;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.dida43.path.finder.exceptions.AsciiMapException;
import org.dida43.path.finder.exceptions.map.MultipleEndsException;
import org.dida43.path.finder.exceptions.map.MultipleStartsException;
import org.dida43.path.finder.exceptions.map.NoEndException;
import org.dida43.path.finder.exceptions.map.NoStartException;
import org.dida43.path.finder.exceptions.path.BrokenPathDirectionException;
import org.dida43.path.finder.exceptions.path.FakeTurnPathDirectionException;
import org.dida43.path.finder.exceptions.path.MultipleStartingPathDirectionException;
import org.dida43.path.finder.exceptions.path.TForkPathDirectionException;
import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.AsciiMapWalker;
import org.dida43.path.finder.pojos.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsciiMapWalkerTest {
  private String getAbsolutePath(String resourceFileName) throws URISyntaxException {
    URL res = getClass().getClassLoader().getResource(resourceFileName);
    File file = Paths.get(res.toURI()).toFile();
    return file.getAbsolutePath();
  }

  @Test public void aBasicExample() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("01ABasicExample"));

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ACB", path.pathAsLetters());
    assertEquals("@---A---+|C|+---+|+-B-x", path.pathAsCharacters());
  }

  @Test public void goStraightThroughIntersections() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("02GoStraightThroughIntersections"));

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ABCD", path.pathAsLetters());
    assertEquals("@|A+---B--+|+--C-+|-||+---D--+|x", path.pathAsCharacters());
  }

  @Test public void lettersMayBeFoundOnTurns() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("03LettersMayBeFoundOnTurns"));

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("ACB", path.pathAsLetters());
    assertEquals("@---A---+|||C---+|+-B-x", path.pathAsCharacters());
  }

  @Test public void doNotCollectALetterFromTheSameLocationTwice() throws Exception {
    String mapAsString =
      Main.readFile(getAbsolutePath("04DoNotCollectALetterFromTheSameLocationTwice"));

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("GOONIES", path.pathAsLetters());
    assertEquals("@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x", path.pathAsCharacters());
  }

  @Test public void keepDirectionEvenInACompactSpace() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("05KeepDirectionEvenInACompactSpace"));

    Path path = AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));

    assertEquals("BLAH", path.pathAsLetters());
    assertEquals("@B+++B|+-L-+A+++A-+Hx", path.pathAsCharacters());
  }

  @Test public void noStart() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("06NoStart"));

    Assertions.assertThrows(NoStartException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void noEnd() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("07NoEnd"));

    Assertions.assertThrows(NoEndException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void multipleStarts() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("08MultipleStarts"));

    Assertions.assertThrows(MultipleStartsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void multipleEnds() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("09MultipleEnds"));

    Assertions.assertThrows(MultipleEndsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void tForksAMultipleEnds() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("10aTForksMultipleEnds"));

    Assertions.assertThrows(MultipleEndsException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void tForksB() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("10bTForks"));

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void brokenPath() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("11BrokenPath"));

    Assertions.assertThrows(BrokenPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void multipleStartingPaths() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("12MultipleStartingPaths"));

    Assertions.assertThrows(MultipleStartingPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void fakeTurn() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("13FakeTurn"));

    Assertions.assertThrows(FakeTurnPathDirectionException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void nonAsciiMap() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("14NonAsciiMap"));

    Assertions.assertThrows(AsciiMapException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }

  @Test public void emptyMap() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("15EmptyMap"));

    Assertions.assertThrows(AsciiMapException.class, () -> {
      AsciiMapWalker.recordPath(AsciiMap.ofString(mapAsString));
    });
  }
}
