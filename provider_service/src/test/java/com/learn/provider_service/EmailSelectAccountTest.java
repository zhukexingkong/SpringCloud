package com.learn.provider_service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderServiceApplication.class)
public class EmailSelectAccountTest {

    @Test
    public void getDistinctAccountCount(){
        //String[][] accountInfo = {{"1", "johnsmith@mail.com", "john00@mail.com"}, {"2", "johnnybravo@mail.com"}, {"3", "johnsmith@mail.com", "john_newyork@mail.com"}, {"4", "mary@mail.com"}, {"5", "johnsmith@mail.com", "bob@mail.com"}};
        String[][] accountInfo = {{"1", "johnsmith@mail.com", "john00@mail.com"}, {"2", "johnsmith@mail.com"}, {"3", "johnsmith@mail.com", "john_newyork@mail.com"}, {"4", "johnsmith@mail.com"},{"5", "johnsmith@mail.com", "bob@mail.com"}};
        HashMap<String, Set<String>> accountHashMap = new HashMap<>();
        for(int i = 0; i < accountInfo.length; i++){
            if(accountInfo[i].length < 2){
                continue;
            }
            // new account or not flag
            boolean newAccount = true;
            // exist account name
            String existAccountName = "";
            Set<String> valuePut = new HashSet<>();
            for(Map.Entry<String, Set<String>> entry : accountHashMap.entrySet()){
                // account is exist
                for(int j = 1; j < accountInfo[i].length; j++) {
                    if(entry.getValue().contains(accountInfo[i][j])){
                        newAccount = false;
                        existAccountName = entry.getKey();
                        valuePut.addAll(entry.getValue());
                        for(int k = 1; k < accountInfo[i].length; k++){
                            valuePut.add(accountInfo[i][k]);
                        }
                        break; // 终结3循环
                    }
                }

                if(!newAccount){
                    break; // 终结2循环
                }
            }
            // new account
            if(newAccount){
                for(int k = 1; k < accountInfo[i].length; k++) {
                    valuePut.add(accountInfo[i][k]);
                }
                accountHashMap.put(accountInfo[i][0], valuePut);
            }
            // exist account
            else {
                accountHashMap.put(existAccountName, valuePut);
            }

        }
        System.out.println("Account num: " + accountHashMap.size());
    }
}
