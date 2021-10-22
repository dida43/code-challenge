package org.dida43.path.finder;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.dida43.path.finder.exceptions.map.MultipleEndsException;
import org.dida43.path.finder.exceptions.map.MultipleStartsException;
import org.dida43.path.finder.exceptions.map.NoEndException;
import org.dida43.path.finder.exceptions.map.NoStartException;
import org.dida43.path.finder.exceptions.path.BrokenPathDirectionException;
import org.dida43.path.finder.exceptions.path.FakeTurnPathDirectionException;
import org.dida43.path.finder.exceptions.path.MultipleStartingPathDirectionException;
import org.dida43.path.finder.exceptions.path.TForkPathDirectionException;
import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.TravelAsciiMap;
import org.dida43.path.finder.pojos.Solution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TravelAsciiMapTest {
  private String getAbsolutePath(String resourceFileName) throws URISyntaxException {
    URL res = getClass().getClassLoader().getResource(resourceFileName);
    File file = Paths.get(res.toURI()).toFile();
    return file.getAbsolutePath();
  }

  @Test public void aBasicExample() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("01ABasicExample"));

    Solution solution = new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();

    assertEquals("ACB", solution.letters());
    assertEquals("@---A---+|C|+---+|+-B-x", solution.pathAsCharacters());
  }

  @Test public void goStraightThroughIntersections() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("02GoStraightThroughIntersections"));

    Solution solution = new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();

    assertEquals("ABCD", solution.letters());
    assertEquals("@|A+---B--+|+--C-+|-||+---D--+|x", solution.pathAsCharacters());
  }

  @Test public void lettersMayBeFoundOnTurns() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("03LettersMayBeFoundOnTurns"));

    Solution solution = new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();

    assertEquals("ACB", solution.letters());
    assertEquals("@---A---+|||C---+|+-B-x", solution.pathAsCharacters());
  }

  @Test public void doNotCollectALetterFromTheSameLocationTwice() throws Exception {
    String mapAsString =
      Main.readFile(getAbsolutePath("04DoNotCollectALetterFromTheSameLocationTwice"));

    Solution solution = new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();

    assertEquals("GOONIES", solution.letters());
    assertEquals("@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x", solution.pathAsCharacters());
  }

  @Test public void keepDirectionEvenInACompactSpace() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("05KeepDirectionEvenInACompactSpace"));

    Solution solution = new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();

    assertEquals("BLAH", solution.letters());
    assertEquals("@B+++B|+-L-+A+++A-+Hx", solution.pathAsCharacters());
  }

  @Test public void noStart() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("06NoStart"));

    Assertions.assertThrows(NoStartException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void noEnd() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("07NoEnd"));

    Assertions.assertThrows(NoEndException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void multipleStarts() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("08MultipleStarts"));

    Assertions.assertThrows(MultipleStartsException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void multipleEnds() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("09MultipleEnds"));

    Assertions.assertThrows(MultipleEndsException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void tForksAMultipleEnds() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("10aTForksMultipleEnds"));

    Assertions.assertThrows(MultipleEndsException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void tForksB() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("10bTForks"));

    Assertions.assertThrows(TForkPathDirectionException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void brokenPath() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("11BrokenPath"));

    Assertions.assertThrows(BrokenPathDirectionException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void multipleStartingPaths() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("12MultipleStartingPaths"));

    Assertions.assertThrows(MultipleStartingPathDirectionException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }

  @Test public void fakeTurn() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("13FakeTurn"));

    Assertions.assertThrows(FakeTurnPathDirectionException.class, () -> {
      new TravelAsciiMap(AsciiMap.ofString(mapAsString)).followPath();
    });
  }
}
