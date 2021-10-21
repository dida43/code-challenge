package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.pojos.Coordinates;

public class MultipleStartingPathException extends Exception{
  public MultipleStartingPathException(Coordinates coordinates){
    super("Multiple paths from coordinates: " + coordinates.toString() + " instead of one.");
  }
}
