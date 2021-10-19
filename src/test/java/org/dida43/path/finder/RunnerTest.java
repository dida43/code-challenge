package org.dida43.path.finder;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.dida43.path.finder.pojos.Solution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunnerTest {
  private String getAbsolutePath(String resourceFileName) throws URISyntaxException {
    URL res = getClass().getClassLoader().getResource(resourceFileName);
    File file = Paths.get(res.toURI()).toFile();
    return file.getAbsolutePath();
  }

  @Test public void ABasicExample() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("01ABasicExample"));

    Solution solution = Runner.run(mapAsString);

    assertEquals("ACB", solution.letters());
    assertEquals("@---A---+|C|+---+|+-B-x", solution.pathAsCharacters());
  }

  @Test public void GoStraightThroughIntersections() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("02GoStraightThroughIntersections"));

    Solution solution = Runner.run(mapAsString);

    assertEquals("ABCD", solution.letters());
    assertEquals("@|A+---B--+|+--C-+|-||+---D--+|x", solution.pathAsCharacters());
  }

  @Test public void LettersMayBeFoundOnTurns() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("03LettersMayBeFoundOnTurns"));

    Solution solution = Runner.run(mapAsString);

    assertEquals("ACB", solution.letters());
    assertEquals("@---A---+|||C---+|+-B-x", solution.pathAsCharacters());
  }

  @Test public void DoNotCollectALetterFromTheSameLocationTwice() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("04DoNotCollectALetterFromTheSameLocationTwice"));

    Solution solution = Runner.run(mapAsString);

    assertEquals("GOONIES", solution.letters());
    assertEquals("@-G-O-+|+-+|O||+-O-N-+|I|+-+|+-I-+|ES|x", solution.pathAsCharacters());
  }

  @Test public void KeepDirectionEvenInACompactSpace() throws Exception {
    String mapAsString = Main.readFile(getAbsolutePath("05KeepDirectionEvenInACompactSpace"));

    Solution solution = Runner.run(mapAsString);

    assertEquals("BLAH", solution.letters());
    assertEquals("@B+++B|+-L-+A+++A-+Hx", solution.pathAsCharacters());
  }
}
