package com.learn.provider_service;

import com.learn.provider_service.arithmetic.binaryTree.Tree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProviderServiceApplication.class)
public class BinaryBinaryTreeTest {

    @Test
    public void binaryTreeAdd() {
        Tree binaryTree = new Tree();
        int[] array = {1,2,3,4,5,6,7};
        // 完全二叉树
        binaryTree.createBinaryTree(array);
        //BinaryTree.recursionPreorderTraversal(binaryTree.getRoot());
        //BinaryTree.recursionMiddleorderTraversal(binaryTree.getRoot());
        Tree.recursionPostorderTraversal(binaryTree.getRoot());
    }
}
