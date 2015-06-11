package com.mcprog.duality.module;

import com.google.gson.*;

import java.io.InvalidObjectException;
import java.lang.reflect.Type;

/**
 * Created by jameswomack on 6/10/15.
 */
public class FunctionInfoDeserializer implements JsonDeserializer<FunctionInfo> {
    @Override
    public FunctionInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FunctionInfo functionInfo = new FunctionInfo();

        JsonObject jsonObject = json.getAsJsonObject();

        // Set the function info stuff that is easy
        functionInfo.setClassName(jsonObject.getAsJsonObject("className").getAsString());
        functionInfo.setFunctionName(jsonObject.getAsJsonObject("functionName").getAsString());

        // Try to load in the type classes
        try {
            Class<?> type1 = Class.forName(jsonObject.getAsJsonObject("type1").getAsString());
            functionInfo.setType1(type1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException("Class not found: type1");
        }

        try {
            Class<?> type2 = Class.forName(jsonObject.getAsJsonObject("type2").getAsString());
            functionInfo.setType2(type2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException("Class not found: type2");
        }

        try{
            Class<?> returnType = Class.forName(jsonObject.getAsJsonObject("returnType").getAsString());
            functionInfo.setReturnType(returnType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException("Class not found: returnType");
        }

        return functionInfo;
    }
}
