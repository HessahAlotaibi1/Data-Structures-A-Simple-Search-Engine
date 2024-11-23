
package com.DS;


public class query_processing {
    static InvertedIndex inverted;
    public query_processing(InvertedIndex inverted){
        this.inverted=inverted;
    }
    public static LinkedList<Integer>BooleanQuery(String Query){
        if(!Query.contains("AND")&&!Query.contains("OR"))
            return AndQuery(Query);
        else if(Query.contains("AND")&&!Query.contains("OR"))
            return AndQuery(Query);
        else if(!Query.contains("AND")&&Query.contains("OR"))
            return OrQuery(Query);
        else 
            return MixedQuery(Query);
        
    }
    public static LinkedList<Integer>MixedQuery(String Query){
        LinkedList<Integer> a=new LinkedList<Integer>();
        LinkedList<Integer> b=new LinkedList<Integer>();   
        if(Query.length()==0)
            return a;
        String ors[]=Query.split("OR");
        a=AndQuery(ors[0]);
        for(int i=1;i<ors.length;i++){
            b=AndQuery(ors[i]);
            a=OrQuery(a,b);
        }
            return a;
    }
    public static LinkedList<Integer>AndQuery(String Query){
        LinkedList<Integer> a=new LinkedList<Integer>();
        LinkedList<Integer> b=new LinkedList<Integer>();
        String terms[]=Query.split("AND");
        //check if is empty or not
        if(terms.length==0)
            return a;
        boolean found=inverted.searchWord(terms[0].trim().toLowerCase());
        if(found)
            a=inverted.inverted_index.retrieve().doc_IDS;
        for(int i=1;i<terms.length;i++){
            found=inverted.searchWord(terms[i].trim().toLowerCase());
        if(found)
            b=inverted.inverted_index.retrieve().doc_IDS;
        
        a=AndQuery(a,b);}
        return a;
        
    }
        public static LinkedList<Integer>AndQuery(LinkedList<Integer> a, LinkedList<Integer> b){
            LinkedList<Integer> res=new LinkedList<Integer>();
            if(a.empty()|| b.empty())
                return res;
            a.findFirst();
            while(true){
           boolean found=existsInRes(res,a.retrieve());
           if(!found){
               b.findFirst();
           while(true){
                  if(b.retrieve().equals(a.retrieve())){    
                      res.insert(a.retrieve());
                      break;
              }
                  if(!b.last())
                      b.findNext();
                  else
                      break;     
        }
}
           if(!a.last())
               a.findNext();
           else 
               break;
        }
            return res;}
        
   public static LinkedList<Integer>OrQuery(String Query){
        LinkedList<Integer> a=new LinkedList<Integer>();
        LinkedList<Integer> b=new LinkedList<Integer>();
        String terms[]=Query.split("OR");
        //check if is empty or not
        if(terms.length==0)
            return a;
        boolean found=inverted.searchWord(terms[0].trim().toLowerCase());
        if(found)
            a=inverted.inverted_index.retrieve().doc_IDS;
        for(int i=1;i<terms.length;i++){
            found=inverted.searchWord(terms[i].trim().toLowerCase());
        if(found)
            b=inverted.inverted_index.retrieve().doc_IDS;
        
        a=OrQuery(a,b);}
        return a;
        
    }
   public static LinkedList<Integer>OrQuery(LinkedList<Integer> a, LinkedList<Integer> b){
            LinkedList<Integer> res=new LinkedList<Integer>();
            if(a.empty()&& b.empty())
                return res;
            a.findFirst();
            while(!a.empty()){
           boolean found=existsInRes(res,a.retrieve());
           if(!found){
               res.insert(a.retrieve());}
           if(!a.last()){
               a.findNext();
           }
           else break;}
            b.findFirst();
               while(!b.empty()){
                   boolean found=existsInRes(res,b.retrieve());
                  if(!found){    
                      res.insert(b.retrieve());}
                      else 
                      break;
        
        }
            return res;}
   public static LinkedList<Integer>notQuery(String Query, index ind1){
        LinkedList<Integer> a=new LinkedList<Integer>();
        LinkedList<Integer> b=new LinkedList<Integer>();
        if(Query.length()==0)
            return a;
        if(!Query.contains("NOT"))
            return a;
        String term=Query.replaceFirst("NOT","").trim().toLowerCase();
        boolean found=inverted.searchWord(term);
        if(found)
        {
            a=inverted.inverted_index.retrieve().doc_IDS;

        }
        if(ind1.all_doc.empty())
            return a;
        ind1.all_doc.findFirst();
        while(!ind1.all_doc.last()){
            if(!a.exist(ind1.all_doc.retrieve().id))
                b.insert(ind1.all_doc.retrieve().id);
            ind1.all_doc.findNext();
            
        }
        if(!a.exist(ind1.all_doc.retrieve().id))
            b.insert(ind1.all_doc.retrieve().id);
        return b;
        
   }
   public static boolean existsInRes(LinkedList<Integer> res, Integer id){
       if(res.empty())
           return false;
       res.findFirst();
       while(!res.last()){
           if(res.retrieve().equals(id)){
               return true;}
           res.findNext();
   }
       if(res.retrieve().equals(id)){
           return true;
       }
       return false;
   
   
}
}