/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DS;
import com.DS.LinkedList; 

class Document {
LinkedList<String> words = new LinkedList<>();//list of words that conenect of id document 
int id ;

Document(int id, LinkedList<String>words){
this.id= id;
this.words=words;
}
}
