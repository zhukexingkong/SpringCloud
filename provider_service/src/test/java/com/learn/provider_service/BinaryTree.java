package com.learn.provider_service;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree {
    class Node {

        public int data;

        public Node leftNode;

        public Node rightNode;

        public Node(int key) {
            this.data = key;
        }

    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    // 创建完全二叉树
    public void createBinaryTree(int[] array) {
        ArrayList<Node> nodes = new ArrayList<>();
        //        将一个数组的值依次转换为Node节点
        for(int data:array){
            nodes.add(new Node(data));
        }
        // 第一个数为根节点
        root = nodes.get(0);
        // 建立二叉树
        for (int i = 0; i <array.length/2; i++) {
            // 左孩子
            nodes.get(i).leftNode = nodes.get(i*2+1);
            // 右孩子
            if(i*2+2 < nodes.size()){//避免偶数的时候 下标越界
                nodes.get(i).rightNode = nodes.get(i*2+2);
            }
        }
    }

    /**
     * 递归遍历-先序
     * @param root
     */
    public static void recursionPreorderTraversal(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            recursionPreorderTraversal(root.leftNode);
            recursionPreorderTraversal(root.rightNode);
        }
    }

    // 非递归先序遍历
    public static void preorderTraversal(Node root) {
        // 用来暂存节点的栈
        Stack<Node> treeNodeStack = new Stack<Node>();
        // 新建一个游标节点为根节点
        Node node = root;
        // 当遍历到最后一个节点的时候，无论它的左右子树都为空，并且栈也为空
        // 所以，只要不同时满足这两点，都需要进入循环
        while (node != null || !treeNodeStack.isEmpty()) {
            // 若当前考查节点非空，则输出该节点的值
            // 由考查顺序得知，需要一直往左走
            while (node != null) {
                System.out.print(node.data + " ");
                // 为了之后能找到该节点的右子树，暂存该节点
                treeNodeStack.push(node);
                node = node.leftNode;
            }
            // 一直到左子树为空，则开始考虑右子树
            // 如果栈已空，就不需要再考虑
            // 弹出栈顶元素，将游标等于该节点的右子树
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                node = node.rightNode;
            }
        }
    }

    /**
     * 递归遍历-中序
     * @param root
     */
    public static void recursionMiddleorderTraversal(Node root) {
        if (root != null) {
            recursionMiddleorderTraversal(root.leftNode);
            System.out.print(root.data + " ");
            recursionMiddleorderTraversal(root.rightNode);
        }
    }

    // 非递归中序遍历
    public static void middleorderTraversal(Node root) {
        Stack<Node> treeNodeStack = new Stack<Node>();
        Node node = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.leftNode;
            }
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                System.out.print(node.data + " ");
                node = node.rightNode;
            }
        }
    }

    /**
     * 递归遍历-后序
     * @param root
     */
    public static void recursionPostorderTraversal(Node root) {
        if (root != null) {
            recursionPostorderTraversal(root.leftNode);
            recursionPostorderTraversal(root.rightNode);
            System.out.print(root.data + " ");
        }
    }

    // 非递归后序遍历
    public static void postorderTraversal(Node root) {
        Stack<Node> treeNodeStack = new Stack<Node>();
        Node node = root;
        Node lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.leftNode;
            }
            //查看当前栈顶元素
            node = treeNodeStack.peek();
            //如果其右子树也为空，或者右子树已经访问
            //则可以直接输出当前节点的值
            if (node.rightNode == null || node.rightNode == lastVisit) {
                System.out.print(node.data + " ");
                treeNodeStack.pop();
                lastVisit = node;
                node = null;
            } else {
                //否则，继续遍历右子树
                node = node.rightNode;
            }
        }
    }

    public static void main(String[] args){
        BinaryTree binaryTree = new BinaryTree();
        int[] array = {1,2,3,4,5,6,7};
        // 完全二叉树
        binaryTree.createBinaryTree(array);
        //BinaryTree.recursionPreorderTraversal(binaryTree.getRoot());
        //BinaryTree.recursionMiddleorderTraversal(binaryTree.getRoot());
        BinaryTree.recursionPostorderTraversal(binaryTree.getRoot());
        System.out.println();
    }
}


