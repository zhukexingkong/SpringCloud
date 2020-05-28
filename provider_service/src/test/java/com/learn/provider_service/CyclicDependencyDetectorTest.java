package com.learn.provider_service;

import com.learn.provider_service.arithmetic.CycleDepend.CyclicDependencyDetector;
import com.learn.provider_service.arithmetic.CycleDepend.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderServiceApplication.class)
public class CyclicDependencyDetectorTest {

    @Test
    public void testCyclicDependency() {
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
        CyclicDependencyDetector.getModuleHashMap(moduleList);
        CyclicDependencyDetector.checkCyclicDependency(moduleList, startModule);
    }
}
