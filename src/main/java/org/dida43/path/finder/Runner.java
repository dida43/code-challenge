package org.dida43.path.finder;

import org.dida43.path.finder.map.AsciiMap;
import org.dida43.path.finder.map.TravelAsciiMap;
import org.dida43.path.finder.pojos.Solution;

public class Runner {
  public static Solution run(String mapAsString) throws Exception {
    AsciiMap asciiMap = AsciiMap.ofString(mapAsString);
    TravelAsciiMap travelAsciiMap = new TravelAsciiMap(asciiMap);
    return travelAsciiMap.findSolution();
  }
}