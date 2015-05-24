package com.mcprog.duality.module;

/**
 * Created by jameswomack on 5/23/15.
 */
public interface ThreeFunction<T1, T2, TOut> {
    TOut apply(T1 param1, T2 param2);
}
