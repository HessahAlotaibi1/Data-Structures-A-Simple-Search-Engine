/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package com.DS;

class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T val) {
        data = val;
        next = null;
    }
}

public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private Node<T> current;
int n= 0 ;
    public LinkedList() {
        head = current = null;
    }

    @Override
    public boolean empty() {
        return head == null;
    }

    @Override
    public boolean last() {
        return current != null && current.next == null;
    }

    @Override
    public boolean full() {
        return false; // LinkedList is never full (dynamically allocated)
    }

    @Override
    public void findFirst() {
        current = head;
    }

    @Override
    public void findNext() {
        if (current != null) {
            current = current.next;
        }
    }

    @Override
    public T retrieve() {
        if (current != null) {
            return current.data;
        }
        return null; // Or throw an exception if desired
    }

    @Override
    public void update(T val) {
        if (current != null) {
            current.data = val;
        }
    }

    @Override
    public void insert(T val) {
        n++;
        Node<T> newNode = new Node<>(val);
        if (empty()) {
            head = current = newNode; // If list is empty, set head and current to new node
        } else {
            Node<T> tmp = current.next;
            current.next = newNode;
            current = current.next;
            current.next = tmp;
        }
    }

    @Override
    public void remove() {
       // if (head == null) {
      //      return; // List is empty, nothing to remove}
      //  if (current == head) {
      //      head = head.next; // If removing head, move head to next node
      //  } else {
      //      Node<T> tmp = head;
      //      while (tmp != null && tmp.next != current) {
      //          tmp = tmp.next;
      //      }
      //      if (tmp != null) {
      //          tmp.next = current.next; // Skip the current node
      //      }
      //  }

      //  if (current != null) {
    //        current = (current.next == null) ? head : current.next;
    //} }
  if (current == head) {
            head = head.next; // If removing head, move head to next node
        } else {
            Node<T> tmp = head;
            while ( tmp.next != current) 
                tmp = tmp.next;
                tmp.next = current.next; // Skip the current node
            }

        if (current.next == null) 
            current =head;
        else 
            current= current.next;
        }
    @Override
    public void display() {
        if (this == null) 
            System.out.println("null List");
            if (head==null)
            System.out.println("empty List");

        Node<T> p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
       // System.out.println(); // To print a newline after the list
    }
    public boolean exist(T w) {
    Node<T> p = head;
      while (p != null) {
           if (p.data.equals(w))
               return true;  
            p = p.next;
    }
    return false;
    }
    //for test
    public static void main(String[] args) {
        List<String> L = new LinkedList<>();
        L.insert("aa");
        L.insert("b");
        L.insert("c");
        L.remove();
        L.display();
    }

    
}
