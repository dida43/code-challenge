package org.dida43.path.finder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.dida43.path.finder.exceptions.AsciiMapException;
import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.AsciiMapWalker;
import org.dida43.path.finder.pojos.Path;

public class Main {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Error: You need to specify file path");
      System.exit(1);
    }
    String mapAsString = null;
    try {
      mapAsString = readFile(args[0]);
    } catch (IOException e) {
      System.out.println("Error: Cannot read file");
      System.exit(1);
    }

    Path path = null;
    try {
      AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
      path = AsciiMapWalker.recordPath(asciiMap);
    } catch (AsciiMapException e) {
      System.out.println("Error: "+e.getMessage());
      System.exit(1);
    }

    System.out.println("LetterCharacters: "+path.pathAsLetters());
    System.out.println("Path as characters: "+path.pathAsCharacters());
    System.exit(0);
  }

  static String readFile(String path) throws IOException
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, StandardCharsets.UTF_8);
  }
}
