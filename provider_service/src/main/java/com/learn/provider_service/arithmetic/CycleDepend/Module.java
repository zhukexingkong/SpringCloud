package com.learn.provider_service.arithmetic.CycleDepend;

import java.util.*;

public class Module {
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
