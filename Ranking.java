
package com.DS;
class docRank{
    int id; 
    int rank;
    public docRank(int i, int r){
        id=i;
        rank=r;
    }
    public void display(){
        System.out.printf("%-8d%-8d\n",id,rank);
    }
}

public class Ranking {
    static String Query;
    static InvertedIndexBST invertedObjBST;
    static index indexObj;
    static LinkedList<Integer> all_doc_in_query;
    static LinkedList<docRank> all_doc_ranked;
    
    public Ranking(InvertedIndexBST invertedObjBST, index indexObj, String Query){
        this.invertedObjBST=invertedObjBST;
        this.indexObj=indexObj;
        this.Query=Query;
        all_doc_in_query=new LinkedList<Integer>();
        all_doc_ranked=new LinkedList<docRank>();
    }
    public static void display_all_doc_with_score_usingList(){
        if(all_doc_ranked.empty()){
            System.out.println("empty");
            return;
        }
        System.out.printf("%-8s%-8s\n","DocID","Score");
        all_doc_ranked.findFirst();
        while(!all_doc_ranked.last()){
            all_doc_ranked.retrieve().display();
            all_doc_ranked.findNext();
        }
            all_doc_ranked.retrieve().display();

    }
    public static Document get_doc_given_id(int id){
        return indexObj.getDucmentById(id);
    }
    public static int term_frequency_in_doc(Document d, String term){
        int freq=0;
        LinkedList<String>words=d.words;
        if(words.empty())
            return 0;
        words.findFirst();
        while(!words.last()){
        if(words.retrieve().equals(term))
            freq++;
        words.findNext();
    }
        if(words.retrieve().equalsIgnoreCase(term))
            freq++;
        return freq;
    
}
    public static int get_doc_rank_score(Document d,String Query){
        if(Query.length()==0)
            return 0;
        String terms[]=Query.split(" ");
        int sum_freq=0;
        for(int i=0; i<terms.length;i++){
         sum_freq+=term_frequency_in_doc(d,terms[i].trim().toLowerCase());
         
    }
        return sum_freq;
}
    public static void RankQuery(String Query){
        LinkedList<Integer> A=new LinkedList<Integer>();
        if(Query.length()==0)return;
        String terms[]=Query.split("\\s+");
        boolean found=false;
        for(int i=0 ; i<terms.length;i++){
            found=invertedObjBST.searchWord(terms[i].trim().toLowerCase());
            if(found)
              A=invertedObjBST.inverted_index.retrieve().doc_IDS;
            Adding_in_1_List_stored(A);
            
        }
    }
    public static void Adding_in_1_List_stored(LinkedList<Integer>A){
        if(A.empty())
            return;
        A.findFirst();
        while(!A.empty()){
            boolean found=existsIn_result(all_doc_in_query,A.retrieve());
            if(!found)
                insert_sorted_Id_list(A.retrieve());
            if(!A.last())
                A.findNext();
            else break;
            
        }
    }
  public static boolean existsIn_result(LinkedList<Integer> result, Integer id){ 
      if(result.empty())return false;
      result.findFirst();
      while(!result.last()){
          if(result.retrieve().equals(id))
              return true;
                result.findNext();
      }
      if(result.retrieve().equals(id))
          return true;
      
      return false;
      
  }
  public static void insert_sorted_Id_list(Integer id){
      if(all_doc_in_query.empty())
      {
          all_doc_in_query.insert(id);
          return;
      }
      all_doc_in_query.findFirst();
      while(!all_doc_in_query.last()){
          if(id<all_doc_in_query.retrieve()){
              Integer id1=all_doc_in_query.retrieve();
              all_doc_in_query.update(id);
              all_doc_in_query.insert(id1);
              return;}
              else 
              all_doc_in_query.findNext();
              
      }
      if(id<all_doc_in_query.retrieve()){
        Integer id1=all_doc_in_query.retrieve();
              all_doc_in_query.update(id);
              all_doc_in_query.insert(id1);
              return;  
      }
      else 
          all_doc_in_query.insert(id);
        
  }
  public static void insert_sotred_in_list(){
      RankQuery(Query);
      if(all_doc_in_query.empty()){
          System.out.println("empty query");
      return;}
      all_doc_in_query.findFirst();
      while(!all_doc_in_query.last()){
          Document d=get_doc_given_id(all_doc_in_query.retrieve());
          int Rank=get_doc_rank_score(d,Query);
          docRank d1=new docRank(all_doc_in_query.retrieve(),Rank);
          insert_sotred_list(d1);
          all_doc_in_query.findNext();
      }
      Document d=get_doc_given_id(all_doc_in_query.retrieve());
      int Rank=get_doc_rank_score(d,Query);
      insert_sotred_list(new docRank(all_doc_in_query.retrieve(),Rank));
       
  }
  public static void insert_sotred_list(docRank dr){
      if(all_doc_ranked.empty())
      {
          all_doc_ranked.insert(dr);
          return;
          
      }
      all_doc_ranked.findFirst();
      while(!all_doc_ranked.last()){
          if(dr.rank>all_doc_ranked.retrieve().rank){
              docRank dr1=all_doc_ranked.retrieve();
              all_doc_ranked.update(dr);
              all_doc_ranked.insert(dr1);
              return;
              
          }
          else all_doc_ranked.findNext();
      }
      if(dr.rank>all_doc_ranked.retrieve().rank){
docRank dr1=all_doc_ranked.retrieve();
              all_doc_ranked.update(dr);
              all_doc_ranked.insert(dr1);
              return;      }
      else all_doc_ranked.insert(dr);
      
  }
}
    
