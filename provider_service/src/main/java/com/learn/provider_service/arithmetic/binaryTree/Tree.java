package com.learn.provider_service.arithmetic.binaryTree;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 */
public class Tree extends AbstractTree {

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

    @Override
    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.data > key) {
                current = current.leftNode;
            } else if (current.data < key) {
                current = current.rightNode;
            } else {
                return current;
            }
        }
        return null;
    }

    @Override
    public boolean insert(int data) {
        count++;
        //如果第一个节点为空 设置第一个节点
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return true;
        }
        Node current = root;
        Node parentNode = null;

        while (current != null) {
            parentNode = current;
            //当前值比新插入值大
            if (current.data > data) {
                current = current.leftNode;
                //若左节点为空 则直接插入即可
                if (current == null) {
                    parentNode.leftNode = newNode;
                    return true;
                }
            } else {
                //当前值小于新插入值
                current = current.rightNode;
                if (current == null) {
                    parentNode.rightNode = newNode;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除共三种情况
     * 1 该节点是叶子节点
     * 2 该节点有一个叶子节点
     * 3 该节点有两个叶子节点
     *
     * @param data
     */
    @Override
    public boolean delete(int data) {
        Node current = root;
        Node parentNode = root;
        //当前节点是否为左节点
        boolean isLeftNode = false;

        //定位data的位置
        while (current.data != data) {
            parentNode = current;
            if (current.data > data) {
                isLeftNode = true;
                current = current.leftNode;
            } else {
                isLeftNode = false;
                current = current.rightNode;
            }

            if (current == null) {
                return false;
            }
        }
        // 1 第一种情况 此节点为叶子节点
        if (current.leftNode == null && current.rightNode == null) {
            if (current == root) {
                root = null;
            } else if (isLeftNode) {
                //如果要删除的节点为父节点的左节点 把父节点的左节点置为空
                parentNode.leftNode = null;
            } else {
                parentNode.rightNode = null;
            }
            return true;
        }
        //2 当前节点有一个节点
        if (current.leftNode == null && current.rightNode != null) {
            if (root == current) {
                root = current.rightNode;
            } else if (isLeftNode) {
                parentNode.leftNode = current.rightNode;
            } else {
                parentNode.rightNode = current.rightNode;
            }
        } else if (current.leftNode != null && current.rightNode == null) {
            if (root == current) {
                root = current.leftNode;
            } else if (isLeftNode) {
                parentNode.leftNode = current.leftNode;
            } else {
                parentNode.rightNode = current.leftNode;
            }
        }

        //3 当前节点有两个节点

        if(current.leftNode != null && current.rightNode != null){
            //获取删除节点的后继结点
            Node successor = getSuccessor(current);
            if (root == current) {
                root = successor;
            } else if (isLeftNode) {
                parentNode.leftNode = successor;
            } else {
                parentNode.rightNode = successor;
            }
        }
        return false;
    }
    /**
     * 获取要删除节点的后继节点
     *
     * @param delNode
     * @return
     */
    public Node getSuccessor(Node delNode) {

        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightNode;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftNode;
        }
        if (successor != delNode.rightNode) {
            successorParent.leftNode = successor.rightNode;
            successor.rightNode = delNode.rightNode;
        }
        return successor;
    }

}
