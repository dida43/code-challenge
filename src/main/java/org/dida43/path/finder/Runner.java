package org.dida43.path.finder;

import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.TravelAsciiMap;
import org.dida43.path.finder.pojos.Solution;

public class Runner {
  public static Solution run(String mapAsString) {
    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
    TravelAsciiMap travelAsciiMap = new TravelAsciiMap(asciiMap);
    Solution solution = null;
    try {
      solution = travelAsciiMap.findSolution();
    } catch (Exception e) {
      System.out.println("Error");
    }
    return solution;
  }
}