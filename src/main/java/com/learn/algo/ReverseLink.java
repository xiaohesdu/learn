package com.learn.algo;

/**
 * @author gonghe.hogan
 */

public class ReverseLink {

    public void reverseLink(Node node){
        Node next = node.next;
        if (node == null || next == null){
            return;
        }

    }

    static class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
        }

        private void setNext(Node node){
            next = node;
        }

    }


    public static void main(String[] args) {
        Node node1 = new ReverseLink.Node(1);
        Node node2 = new ReverseLink.Node(2);
        Node node3 = new ReverseLink.Node(3);
        Node node4 = new ReverseLink.Node(4);
    }
}
