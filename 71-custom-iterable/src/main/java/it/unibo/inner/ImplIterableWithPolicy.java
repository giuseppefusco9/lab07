package it.unibo.inner;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T> {

    private final T[] elements;
    private Predicate<T> predicate;

    public ImplIterableWithPolicy(final T[] elems) {
        this(elems, new Predicate<T>() {
            public boolean test(T elem) {
                return true;
            } 
        });
    }

    public ImplIterableWithPolicy(final T[] elems, Predicate<T> pr) {
        this.elements = elems;
        this.predicate = pr;
    }


    public class ImplIterator implements Iterator<T> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            while(current < elements.length && !predicate.test(elements[current])){
                current++;
            }
            return elements.length > this.current;
        }

        @Override
        public T next() {
            if(hasNext()){
                return elements[current++];
            }
            else{
                throw new NoSuchElementException();
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new ImplIterator();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

}
