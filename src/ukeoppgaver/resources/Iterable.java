package ukeoppgaver.resources;

import java.util.Spliterator;
import java.util.function.Consumer;

public interface Iterable<T>                         // ligger i java.lang
{
    Iterator<T> iterator();                            // en iterator

    default void forEach(Consumer<? super T> action){
        iterator().forEachRemaining(action);
    }  // traversering
    default Spliterator spliterator(){
        throw new UnsupportedOperationException("Splitterator");
    }                // parallellprosessering
}
