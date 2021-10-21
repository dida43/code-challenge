package org.dida43.path.finder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import org.dida43.path.finder.exceptions.AsciiMapException;
import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.TravelAsciiMap;
import org.dida43.path.finder.pojos.Solution;

public class Main {

  public static void main(String[] args) {

    final Logger logger = Logger.getLogger("Software Sauna Code Challenge");
    try {
      final FileHandler fileHandler = new FileHandler("asciimap.log");
    } catch (IOException e) {
      System.out.println("Cannot write to log file!");
      System.exit(1);
    }

    if (args.length != 1) {
      System.out.println("You need to specify file path!");
      System.exit(1);
    }
    String mapAsString = null;
    try {
      mapAsString = readFile(args[0]);
    } catch (IOException e) {
      System.out.println("Cannot read file!");
      logger.fine(e.getMessage());
      System.exit(1);
    }

    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
    TravelAsciiMap travelAsciiMap = new TravelAsciiMap(asciiMap);
    Solution solution = null;
    try {
      solution = travelAsciiMap.findSolution();
    } catch (AsciiMapException e) {
      System.out.println("Error");
      logger.fine(e.getMessage());
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
