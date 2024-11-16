public class InvertedIndex{
LinkedList<Word>inverted_index;
public InvertedIndex(){
 inverted_index=new LinkedList<Word>();
}
public void add(String text,int id)
{
if(!search_word_in_iverted(text)){
    Word w=new Word(text);
    w.doc_IDS.insert(id);
     inverted_index.insert(w);
}
else{
    Word existing_word=inverted_index.retrieve();
    existing_word.add_id(id);
}
}
public boolean  search_word_in_iverted(String w){
    if(inverted_index==null||inverted_index.empty())
    return false;
    inverted_index.findFirst();
    while (!inverted_index.last()) {
       if(inverted_index.retrieve().text.equals(W)){
        return true;
    }
   inverted_index.findFirst();
}
if(inverted_index.retrieve().equals(w)){
 return true;
 }
 return false;}
 public void display_inverted_index(){
    if(inverted_index==null){
        System.out.println("null inverted index");
        return;
    }
    else if(inverted_index.empty()){
        System.out.println("empty inverted index");
        return;
    }
    inverted_index.findFirst();
    while (!inverted_index.last()) {
        inverted_index.retrieve().display();
       inverted_index.findNext();
        
    }
    inverted_index.retrieve().display();
 }
}