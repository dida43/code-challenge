package org.dida43.path.finder.exceptions.direction;

import org.dida43.path.finder.pojos.Coordinates;

public class FakeTurnDirectionException extends Exception{
  public FakeTurnDirectionException(Coordinates coordinates){
    super("Coordinates: " + coordinates.toString() + " contain fake turn.");
  }
}
