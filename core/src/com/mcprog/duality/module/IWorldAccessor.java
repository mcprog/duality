package com.mcprog.duality.module;

import java.util.stream.Stream;

/**
 * This class is meant to modify and search the world
 * it will be injected into each module as a dependency object
 */
public interface IWorldAccessor<T> {
    void requestAdd(T object);
    void requestRemove(T object);
    Stream<T> getStream();
}
