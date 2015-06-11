package com.mcprog.duality.module;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by jameswomack on 6/10/15.
 */
public class FunctionInfoSerializer implements JsonSerializer<FunctionInfo> {
    @Override
    public JsonElement serialize(FunctionInfo src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Add the jsonObject properties
        jsonObject.addProperty("className", src.getClassName());
        jsonObject.addProperty("functionName", src.getFunctionName());
        jsonObject.addProperty("type1", src.getType1().getName());
        jsonObject.addProperty("type2", src.getType2().getName());
        jsonObject.addProperty("returnType", src.getReturnType().getName());

        // Return the object
        return jsonObject;
    }
}
