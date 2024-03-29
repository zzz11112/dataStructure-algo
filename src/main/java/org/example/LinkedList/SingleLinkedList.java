package org.example.LinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class SingleLinkedList implements Iterable<Integer> {
    private Node head = null;//头指针

    /**
     * 节点类
     */
    private static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 向链表头加入元素
     */
    public void addFirst(int value) {
        head = new Node(value, head);

    }


    /**
     * while循环实现
     *
     * @param consumer
     */
    public void loop1(Consumer<Integer> consumer) {
        Node pointer = head;
        while (pointer != null) {
            consumer.accept(pointer.value);
            pointer = pointer.next;
        }
    }

    /**
     * for循环实现
     *
     * @param consumer
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     * 递归函数
     * @param curr
     */
    private void recursion(Node curr){
        if(curr == null){
            return;
        }
        System.out.println(curr.value);
        recursion(curr.next);
    }

    public void loop3(){
        Node node = head;
        recursion(node);
    }


    /**
     * 迭代器实现
     *
     * @return pointer.value
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            Node pointer = head;

            @Override
            public boolean hasNext() {
                return pointer != null;//下一个节点等于null，返回假
            }

            @Override
            public Integer next() {
                int v = pointer.value;
                pointer = pointer.next;
                return v;
            }
        };
    }

    public Node findLast() {
        if (head == null) return null;
        Node p;
        for (p = head; p.next != null; p = p.next) {

        }
        return p;
    }

    public void addLast(int value) {
        Node last = findLast();
        if (last == null){
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);

    }

    private Node findNode(int index){
        int i=0;
        for(Node p = head;p!=null;p=p.next,i++){
            if(i==index){
                return p;
            }
        }
        return null;
    }

    /**
     * 返回对应索引的值
     * @param index
     * @return
     */
    public int get(int index) throws IllegalArgumentException{
        Node node = findNode(index);
        if(node == null){
            throw illegalIndex(index);
        }
        return node.value;
    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index[%d] is illegal%n", index));
    }

    /**
     * 向索引位置加入元素
     */
    public void insert(int index,int value){
        if(index==0){
            addFirst(value);
            return;
        }
        Node prev=findNode(index-1);
        if(prev==null){
            throw illegalIndex(index);
        }
        prev.next=new Node(value, prev.next);
    }

    /**
     * 删除第一个
     */
    public void removeFirst(){
        if(head==null){
            throw illegalIndex(0);
        }
        head = head.next;
    }

    public void remove(int index){
        if(index==0){
            removeFirst();
            return;
        }
        Node prev=findNode(index-1);
        if(prev==null){
            throw illegalIndex(index);
        }
        Node removed = prev.next;
        if(removed==null){
            throw illegalIndex(index);
        }
        prev.next = removed.next;
    }
}
