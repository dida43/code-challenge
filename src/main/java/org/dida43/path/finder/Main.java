package org.dida43.path.finder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.TravelAsciiMap;
import org.dida43.path.finder.pojos.Solution;

public class Main {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("You need to specify file path!");
      System.exit(1);
    }
    String mapAsString = null;
    try {
      mapAsString = readFile(args[0]);
    } catch (IOException e) {
      System.out.println("Cannot read file!");
      System.exit(1);
    }

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
    TravelAsciiMap travelAsciiMap = new TravelAsciiMap(asciiMap);
    Solution solution = null;
    try {
      solution = travelAsciiMap.findSolution();
    } catch (Exception e) {
      System.out.println("Error");
      System.exit(1);
    }

    System.out.println("Letters: "+solution.letters());
    System.out.println("Path as characters: "+solution.pathAsCharacters());
    System.exit(0);
  }

  static String readFile(String path) throws IOException
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }
}
