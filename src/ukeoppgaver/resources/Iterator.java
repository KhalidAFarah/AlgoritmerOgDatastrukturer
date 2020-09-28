package ukeoppgaver.resources;

import java.util.function.Consumer;

public interface Iterator<T>                   // ligger i java.util
{
    public boolean hasNext();                    // er det flere igjen?
    public T next();                             // returnerer neste verdi

    default void remove(){
        throw new UnsupportedOperationException("remove");
    }                       // fjerner sist returnerte verdi
    default void forEachRemaining(Consumer<? super T> action){
        T element = next();
        while (element != null && hasNext()){
            action.accept(element);
            element = next();
        }
        action.accept(element);
        //System.out.print(action);
    }   // traversering
}
