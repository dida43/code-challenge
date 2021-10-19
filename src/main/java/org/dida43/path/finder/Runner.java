package org.dida43.path.finder;

import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.Solution;

public class Runner {
  public static Solution run(String mapAsString) {
    AsciiMap asciiMap = new AsciiMap(mapAsString);
    Solution solution = null;
    try {
      solution = asciiMap.findSolution();
    } catch (Exception e) {
      System.out.println("Error");
    }
    return solution;
  }
}