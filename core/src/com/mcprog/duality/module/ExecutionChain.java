package com.mcprog.duality.module;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by jameswomack on 5/23/15.
 */
public class ExecutionChain<T> {
    private List<ThreeFunction<IWorldAccessor<T>, T, T>> executionOrder;
    private Consumer<T> finalizer;

    /**
     * Generates an ExecutionChain from a JSON file
     * @param order The file to generate from
     * @param <E> The Type
     * @return The ExecutionChain
     */
    public static <E> ExecutionChain<E> construct(String order){
        // TODO: Implement
        return null;
    }

    /**
     * Executes the chain with the given supply module
     * @param module
     */
    public void execute(SupplyModule<T> module){
        List<T> supply = module.getSupply();

        // We have to use a for loop because we will set
        // the data within the body of the loop
        for (int i = 0; i < supply.size(); ++i){
            T entity = supply.get(i);

            // We go through each function and apply it to the entity
            for (ThreeFunction<IWorldAccessor<T>, T, T> func : executionOrder) {
                entity = func.apply(module, entity);
            }

            // We then set the supply to the processed entity
            supply.set(i, entity);
        }
    }
}
