
package com.DS;

public class InvertedIndexBST {
    BST<Word> inverted_index; 

    public InvertedIndexBST() {
        inverted_index = new BST<Word>();
    }
    public void add_from_inverted_list(InvertedIndex inverted){
        if(inverted.inverted_index.empty())
        return;

        inverted.inverted_index.findFirst();
        while(!inverted.inverted_index.last()){
   inverted_index.insert(inverted.inverted_index.retrieve().text,inverted.inverted_index.retrieve());
   inverted.inverted_index.findNext();
}
inverted_index.insert(inverted.inverted_index.retrieve().text,inverted.inverted_index.retrieve());   
}
public void add(String text,int id)
{
    if(!searchWord(text)){
        Word w=new Word(text);
        w.doc_IDS.insert(id);
      inverted_index.insert(text,w);
    }else{
        Word existing_word=inverted_index.retrieve();
        existing_word.add_id(id);
    }
}
public boolean searchWord(String w){
return inverted_index.findkey(w);}

public void display_inverted_index(){
    if(inverted_index==null){
        System.out.println("null inverted index");
        return;
    }
    else if(inverted_index.empty()){
        System.out.println("empty inverted index");
        return;
    }
    inverted_index.inOrder();
}
}