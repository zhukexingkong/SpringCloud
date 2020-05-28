package com.learn.provider_service;
import java.util.*;

public class CyclicDependencyDetector {
    static class Module {
        private String name;
        private Set<String> dependencies;

        public String getName() {
            return name;
        }

        public Set<String> getDependencies() {
            return dependencies;
        }

        public Module(String name, Set<String> dependencies){
            this.name = name;
            this.dependencies = dependencies;
        }
    }

    private static HashMap<String, Module> moduleHashMap = new HashMap<>();

    // 判断循环依赖
    public static void checkCyclicDependency(List<Module> moduleList, Module startModule) {
        getModuleHashMap(moduleList);
        // 检查有没有依赖自身
        String selfName = startModule.getName();
        for(String oneName : startModule.getDependencies()){
            if(oneName.equals(selfName)){
                System.out.println("startModule依赖自己");
            }
        }

        // 依赖的循环依赖
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

    public static void main(String[] args) {
        Module A = new Module("A", new HashSet<String>(){{
            add("B");
            add("C");
            add("D");
        }});
        Module B = new Module("B",new HashSet<String>(){{
            add("C");
            add("F");
        }});
        Module C = new Module("C",new HashSet<String>(){{
            add("A");
            add("F");
        }});
        Module D = new Module("D",null);
        Module F = new Module("F",null);
        List<Module> moduleList = new ArrayList<>();
        moduleList.add(A);
        moduleList.add(B);
        moduleList.add(C);
        moduleList.add(D);
        moduleList.add(F);
        Module startModule  = new Module("startModule", new HashSet<String>(){{
            add("A");
            add("B");
            add("C");
        }});
        getModuleHashMap(moduleList);
        checkCyclicDependency(moduleList, startModule);
    }
}
