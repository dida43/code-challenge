package org.dida43.path.finder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.dida43.path.finder.map.Solution;

public class Main {

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      throw new Exception("You need to specify file path!");
    }
    String mapAsString = readFile(args[0]);

    Solution solution = Runner.run(mapAsString);

    System.out.println("Letters: "+solution.letters());
    System.out.println("Path as characters: "+solution.pathAsCharacters());
  }

  static String readFile(String path) throws IOException
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }
}
