/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DS;


public class InvertedIndex {
    LinkedList<Word>inverted_index;
    public InvertedIndex(){
        inverted_index=new LinkedList<Word>();
    }
    public void add(String text, int id){
        if(!searchWord(text)){
            Word w=new Word(text);
            w.doc_IDS.insert(id);
            inverted_index.insert(w); 
        }
        else{
            Word existing_word=inverted_index.retrieve();
            existing_word.add_id(id);
        }
    }
    public boolean searchWord(String w){
        if(inverted_index==null||inverted_index.empty())
        return false;
        inverted_index.findFirst();
        while (!inverted_index.last()) {
            if(inverted_index.retrieve().text.equals(w)){
                return true;
            }
            inverted_index.findNext();
        }
        if(inverted_index.retrieve().equals(w)){ //check for the last one 
            return true;
        }
        return false;
    }
    public void display_inverted_index(){
        if(inverted_index==null){
            System.out.println("The inverted index is null !!");
            return;
        }
        else if(inverted_index.empty()){
            System.out.println("The inverted index is empty !!");
            return;
        }
        inverted_index.findFirst();
        while(!inverted_index.last()){
            inverted_index.retrieve().display();
            inverted_index.findNext();
        }
        inverted_index.retrieve().display();
    }
}

