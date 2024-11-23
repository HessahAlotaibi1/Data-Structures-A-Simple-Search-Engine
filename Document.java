
package com.DS;
//import com.DS.LinkedList; 

class Document {
LinkedList<String> words = new LinkedList<>();//list of words that conenect of id document 
int id ;
String content;
Document(int id, LinkedList<String>words,String content){
this.id= id;
this.words=words;
this.content=content;
}
}
