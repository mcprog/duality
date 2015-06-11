package com.mcprog.duality.module;

import com.google.gson.Gson;

/**
 * Created by jameswomack on 6/10/15.
 */
public class FunctionInfo {
    private String className;
    private String functionName;
    private Class<?> type1;
    private Class<?> type2;
    private Class<?> returnType;

    public FunctionInfo(){
    }

    /**
     * Gets the className to get the function from
     * @return The class name
     */
    public String getClassName(){
        return className;
    }

    /**
     * Sets the class name of the functionInfo
     * @param className the class name
     */
    public void setClassName(String className){
        this.className = className;
    }

    /**
     * Gets the functionName of the function to load
     * @return The function name
     */
    public String getFunctionName(){
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * Gets the type of the first parameter
     * @return The type of the first parameter
     */
    public Class<?> getType1(){
        return type1;
    }


    public void setType1(Class<?> type1) {
        this.type1 = type1;
    }


    /**
     * Gets the type of the second parameter
     * @return The Type of the second paramter
     */
    public Class<?> getType2(){
        return type2;
    }

    public void setType2(Class<?> type2) {
        this.type2 = type2;
    }

    /**
     * Gets the return type of the function
     * @return The return type
     */
    public Class<?> getReturnType(){
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }
}
