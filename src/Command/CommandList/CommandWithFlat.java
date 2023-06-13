package Command.CommandList;

import Exceptions.IllegalValueException;
import PossibleClassInCollection.Flat.Flat;

public interface CommandWithFlat {
   void setFlat() throws IllegalValueException;
   Flat getFlat();
}
