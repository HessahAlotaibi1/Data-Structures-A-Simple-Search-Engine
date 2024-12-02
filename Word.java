/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DS;
public class Word {
  String text;
LinkedList <Integer>doc_IDS; //list of id that connnect of words
public Word (String w){
    text=w;
    doc_IDS=new LinkedList<Integer>();
}
public void add_id(int id){
    if (!searchID(id))
        doc_IDS.insert(id);
}

    private boolean searchID(Integer id) { //search// we can used exist in linked list
       if (doc_IDS.empty())
        return false;
       doc_IDS.findFirst();
       while (!doc_IDS.last()){
           if (doc_IDS.retrieve().equals(id)){
               return true;
           }
           doc_IDS.findNext();
       }
       if(doc_IDS.retrieve().equals(id)){
           return  true;
       }
       return false;
       }
 public void display(){
            System.out.println("\n -----------------------------------");
            System.out.print("Word:"+text);
            System.out.print("\n Document IDs: ");
            System.out.print("[");
            doc_IDS.display();
            System.out.println("]");
       }

}
