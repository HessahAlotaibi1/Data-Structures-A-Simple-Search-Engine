/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DS;
public interface List<T> {
    boolean empty();
    boolean full();
    void findFirst();
    void findNext();
    boolean last();
    void update(T e);
    T retrieve();
    void remove();
    void display();
    void insert(T e);
}
  

