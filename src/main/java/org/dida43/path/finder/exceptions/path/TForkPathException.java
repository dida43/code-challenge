package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.pojos.Coordinates;

public class TForkPathException extends Exception{
  public TForkPathException(Coordinates coordinates){
    super("Coordinates: " + coordinates.toString() + " contain T fork. Cannot determine path");
  }
}
