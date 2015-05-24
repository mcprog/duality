package com.mcprog.duality.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jameswomack on 5/23/15.
 */
public abstract class SupplyModule<TOut> implements ISupplier<TOut>, IWorldAccessor<TOut> {
    protected List<TOut> objects         = new ArrayList<>();
    protected List<TOut> objectsToAdd    = new ArrayList<>();
    protected List<TOut> objectsToRemove = new ArrayList<>();

    /**
     * This is to be called at the start of your game
     */
    public abstract void initialize();

    /**
     * This is to be called at the beginning of your update method
     */
    public abstract void begin();

    /**
     * This is to be called at the end of your update method
     */
    public void end(){
        objects.removeAll(objectsToRemove);
        objects.addAll(objectsToAdd);
    }

    @Override
    public void requestRemove(TOut remove){
        objectsToRemove.add(remove);
    }

    @Override
    public void requestAdd(TOut add){
        objectsToAdd.add(add);
    }

    @Override
    public Stream<TOut> getStream() {
        return objects.stream();
    }
}
