package com.learn.provider_service;

import java.util.ArrayList;

public class CycleList {
    class Node {
        public int pos;
        public Node next;

        public Node(int pos) {
            this.pos = pos;
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    // 建立普通链表
    public boolean createNormalList(int nodeCount){
        if(nodeCount < 1){
            return false;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i < nodeCount; i++){
            nodes.add(new Node(i));
        }
        root = nodes.get(0);
        // 建立链表
        for(int j = 0; j < nodeCount - 1; j++){
            nodes.get(j).next = nodes.get(j + 1);
        }
        return true;
    }

    // 建立有nodeCount个元素的链表，环的位置为ringPosition
    public boolean createCycleList(int nodeCount, int ringPosition){
        if(nodeCount < 1 || ringPosition < 1 || ringPosition > nodeCount){
            return false;
        }
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i = 0; i < nodeCount; i++){
            nodes.add(new Node(i));
        }
        root = nodes.get(0);
        // 建立链表
        for(int j = 0; j < nodeCount - 1; j++){
            nodes.get(j).next = nodes.get(j + 1);
        }
        nodes.get(nodeCount - 1).next = nodes.get(ringPosition - 1);
        return true;
    }

    /**
     * 查看是否有环(一快一慢，有环的话则值相等)
     * @param
     * @return
     */
    public boolean hasCycle() {
        Node fast = root;
        Node slow = root;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找环节点:
     * @param
     * @return
     */
    public Node detectCycleNode() {
        Node fast = root;
        Node slow = root;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                break;
            }
        }
        if(fast == null || fast.next == null){
            return null;
        }

        slow = root;
        while(fast != slow){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args){
        CycleList cycleList = new CycleList();
        //cycleList.createNormalList(10);
        cycleList.createCycleList(10,9);
        if(!cycleList.hasCycle()){
            System.out.println(-1);
        }else{
            Node cycleNode = cycleList.detectCycleNode();
            System.out.println(cycleNode.pos);
        }
    }
}
