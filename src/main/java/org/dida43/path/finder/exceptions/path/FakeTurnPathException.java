package org.dida43.path.finder.exceptions.path;

import org.dida43.path.finder.pojos.Coordinates;

public class FakeTurnPathException extends Exception{
  public FakeTurnPathException(Coordinates coordinates){
    super("Coordinates: " + coordinates.toString() + " contain fake turn.");
  }
}
