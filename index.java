
package com.DS;

public class index {
 LinkedList<Document>all_doc; // object of document 
   public index(){
     all_doc=new LinkedList<Document>();
   }
   public void add_Document(Document d){
       all_doc.insert(d);
   }

        public Document getDucmentById(int id ){
       if (all_doc.empty()){
           System.out.println("no documents exist");
           return null;
   }
         all_doc.findFirst();
     while(!all_doc.last()){
          if (all_doc.retrieve().id==id)
              return all_doc.retrieve();
               all_doc.findNext();
       }
       if (all_doc.retrieve().id==id)
           return all_doc.retrieve();
       return null; 
}
        
 
   public void displayDocument(){
       if (all_doc==null){
           System.out.println("null docs");
           return;
       }
       else if (all_doc.empty())
       {
           System.out.println("empty docs"); 
           return;
       }
       all_doc.findFirst();
       while(!all_doc.last()){
           System.out.println("\n-----------------------------");
           System.out.println("ID:"+ all_doc.retrieve().id);
            all_doc.retrieve().words.display();
            all_doc.findNext();
   }
     System.out.println("\n-----------------------------");
           System.out.println("ID:"+ all_doc.retrieve().id);
            all_doc.retrieve().words.display();
   }
   public LinkedList<Integer> get_all_documents_given_term(String term){
       LinkedList<Integer> res=new LinkedList<Integer>();
       if(all_doc.empty()){
           System.out.println("no documents exist");
           return null;
       }
       all_doc.findFirst();
       while(!all_doc.last()){
           if(all_doc.retrieve().words.exist(term.toLowerCase().trim()))
               res.insert(all_doc.retrieve().id);
           all_doc.findNext();
       }
       if(all_doc.retrieve().words.exist(term.toLowerCase().trim()))
           res.insert(all_doc.retrieve().id);
       return res;
   }
   
   public static void main (String[]args){
         /* index ind1 = new index();
        LinkedList<String> words = new LinkedList<>();
        words.insert("national");
         words.insert("flag");
        Document d1 = new Document(1,words);
        ind1.add_Document(d1);
        ind1.displayDocument();
   */ }   // to check     
}
