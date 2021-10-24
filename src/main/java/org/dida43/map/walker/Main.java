package org.dida43.map.walker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.dida43.map.walker.exceptions.AsciiMapException;
import org.dida43.map.walker.map.AsciiMap;
import org.dida43.map.walker.map.AsciiMapWalker;
import org.dida43.map.walker.pojos.Path;

public class Main {

  public static void main(String[] args) {

    String filePath = filePathFromArguments(args);

    String mapAsString = readFileAsString(filePath);

    Path asciiMapPath = getPathFromAsciiMap(mapAsString);

    System.out.println("Letters "+asciiMapPath.asLetters());
    System.out.println("Path as characters "+asciiMapPath.asCharacters());
    System.exit(0);
  }

  private static String filePathFromArguments(String[] args) {
    if (args.length != 1) {
      System.out.println("Error: File path argument missing");
      System.exit(1);
    }
    return args[0];
  }

  private static String readFileAsString(String filePath) {
    String mapAsString = null;
    try {
      mapAsString = readFile(filePath);
    } catch (IOException e) {
      System.out.println("Error: Cannot read file "+filePath);
      System.exit(1);
    }
    return mapAsString;
  }

  private static String readFile(String path) throws IOException
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }

  private static Path getPathFromAsciiMap(String mapAsString) {
    Path path = null;
    try {
      AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
      path = AsciiMapWalker.recordPath(asciiMap);
    } catch (AsciiMapException e) {
      System.out.println("Error: "+e.getMessage());
      System.exit(1);
    }
    return path;
  }
}
