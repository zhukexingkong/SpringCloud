package com.learn.provider_service;

import com.learn.provider_service.arithmetic.cycleList.CycleList;
import com.learn.provider_service.arithmetic.cycleList.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderServiceApplication.class)
public class CycleListTest {
    @Test
    public void testCycleList(){
        CycleList cycleList = new CycleList();
        //cycleList.createCycleList(10,9);
        cycleList.createNormalList(10);
        if(!cycleList.hasCycle()){
            System.out.println(-1);
        }else{
            Node cycleNode = cycleList.detectCycleNode();
            System.out.println(cycleNode.pos);
        }
    }
}
