package com.learn.provider_service.arithmetic.CycleDepend;

import java.util.*;

public class CyclicDependencyDetector {
    private static HashMap<String,Module> moduleHashMap = new HashMap<>();

    // 判断循环依赖
    public static void checkCyclicDependency(List<Module> moduleList, Module startModule) {
        getModuleHashMap(moduleList);
        // 排序
        List<String> dependencyOrderList = new ArrayList<>();
        for(String oneName : startModule.getDependencies()){
            Module oneModule = moduleHashMap.get(oneName);
            for(String nextName : oneModule.getDependencies()) {
                addDependencyOrderList(dependencyOrderList, oneName, nextName);
            }
        }
        // 检查是否循环依赖,存在则输出依赖顺序
        for(String orderList : dependencyOrderList){
            printCyclicDependency(orderList);
        }
    }

    // 排序
    private static void addDependencyOrderList(List<String> dependencyOrderList, String oneName, String nextName) {
        boolean existLastChar = false;
        List<String> newOrderList = new ArrayList<>();
        for(String orderStr : dependencyOrderList) {
            if(getLastChar(orderStr).equals(oneName)){
                existLastChar = true;
                newOrderList.add(orderStr + nextName);
            }
        }
        if(existLastChar){
            dependencyOrderList.addAll(newOrderList);
        }else {
            dependencyOrderList.add(oneName + nextName);
        }
    }

    // 存在循环依赖输出依赖顺序
    private static void printCyclicDependency(String orderlist){
        if(containRepeatChar(orderlist)){
            char[] elements = orderlist.toCharArray();
            String tab = "";
            for(char e : elements){
                System.out.println(tab + "Module_" + e);
                tab += "\t";
            }
        }
    }

    // 获取str最后一个字符
    private static String getLastChar(String str){
        int length = str.length();
        if(length < 1){
            return "";
        }
        return String.valueOf(str.charAt(length - 1));
    }

    // 判断是否有重复字符
    private static boolean containRepeatChar(String str){
        if(str == null || str.isEmpty()){
            return false;
        }
        char[] elements = str.toCharArray();
        for(char e : elements){
            if(str.indexOf(e) != str.lastIndexOf(e)){
                return true;
            }
        }
        return false;
    }

    // 获取Module HashMap
    public static void getModuleHashMap(List<Module> moduleList){
        for(Module module1 : moduleList){
            moduleHashMap.put(module1.getName(), module1);
        }
    }
}
