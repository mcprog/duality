package com.mcprog.duality.module;

/**
 * An immutable type that provides a pair of objects
 */
public class Pair<T1, T2> {
    private T1 obj1;
    private T2 obj2;

    public Pair(T1 obj1, T2 obj2){
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    /**
     * Gets the first object in the pair
     * @return The first object
     */
    public T1 getOne(){
        return obj1;
    }

    /**
     * Gets the second object in the pair
     * @return The second object
     */
    public T2 getTwo(){
        return obj2;
    }
}
