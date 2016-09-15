package org.example.pltw.medialib;

import java.lang.reflect.Array;

/**
 * Created by BrandonPhan on 9/13/16.
 */
public class GenericArray<E> {

    private Object[] a;

    public  GenericArray(int s) {
        a = new Object[s];
    }

    E getItem(int i) {
        final E e = (E) a[i];
        return e;
    }
}
