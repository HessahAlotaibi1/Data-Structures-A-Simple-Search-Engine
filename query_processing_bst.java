
package com.DS;


public class query_processing_bst {
    static InvertedIndexBST inverted;

    public query_processing_bst(InvertedIndexBST inverted){

        this.inverted=inverted;

    }

    public static LinkedList<Integer>BooleanQuery(String Query){

        if(!Query.contains("AND")&&!Query.contains("OR"))

        return AndQuery(Query);

        else if(Query.contains("AND")&&!Query.contains("OR"))

        return AndQuery(Query);

        else if (!Query.contains("AND")&&Query.contains("OR"))

        return ORQuery(Query);

        else

        return MixedQuery(Query);

    }

    public static LinkedList<Integer> MixedQuery(String Query){

LinkedList<Integer>A=new LinkedList<Integer>();

LinkedList<Integer>B=new LinkedList<Integer>();

if(Query.length()==0)

return A;

String ors[]=Query.split("OR");

 

A=AndQuery(ors[0]);

for(int i=1;i<ors.length;i++)

{

    B=AndQuery(ors[i]);

    A=ORQuery(A,B);

}

return A;

}

 

public static LinkedList<Integer> AndQuery(String Query){

LinkedList<Integer>A=new LinkedList<Integer>();

LinkedList<Integer>B=new LinkedList<Integer>();

 

String terms[]=Query.split("AND");

if(terms.length==0)

return A;

boolean found = inverted.searchWord(terms[0].trim().toLowerCase());

if(found){

A=inverted.inverted_index.retrieve().doc_IDS;

}

for(int i=1;i<terms.length;i++){

found=inverted.searchWord(terms[i].trim().toLowerCase());

if(found)

B=inverted.inverted_index.retrieve().doc_IDS;

 

A=AndQuery(A,B);

}

return A;

}





public static LinkedList<Integer>AndQuery(LinkedList<Integer>A,LinkedList<Integer>B)

{

    LinkedList<Integer> result=new LinkedList<Integer>();

    if(A.empty()||B.empty())

    return result;

A.findFirst();

while(true){

    boolean found=existsIn_result(result,A.retrieve());

    if(!found){

        B.findFirst();

        while(true)

        {

            if(B.retrieve().equals(A.retrieve()))

            {

                result.insert(A.retrieve());

                break;

            }

            if(!B.last())

            B.findNext();

            else

          break;

        }}

        if(!A.last())

        A.findNext();

        else

        break;}

 

        return result;

    }

  public static LinkedList<Integer> ORQuery(String Query){

LinkedList<Integer>A=new LinkedList<Integer>();

LinkedList<Integer>B=new LinkedList<Integer>();

 

String terms[]=Query.split("OR");

if(terms.length==0)

return A;

 

boolean found=inverted.searchWord(terms[0].trim().toLowerCase());

if(found){

A=inverted.inverted_index.retrieve().doc_IDS;

}

for(int i=1;i<terms.length;i++){

found=inverted.searchWord(terms[i].trim().toLowerCase());

if(found)

B=inverted.inverted_index.retrieve().doc_IDS;

 

A=AndQuery(A,B);

}

return A;

}

 public static LinkedList<Integer>ORQuery(LinkedList<Integer> a, LinkedList<Integer> b){
            LinkedList<Integer> res=new LinkedList<Integer>();
            if(a.empty()&& b.empty())
                return res;
            a.findFirst();
            while(!a.empty()){
           boolean found=existsIn_result(res,a.retrieve());
           if(!found){
               res.insert(a.retrieve());}
           if(!a.last()){
               a.findNext();
           }
           else break;}
            b.findFirst();
               while(!b.empty()){
                   boolean found=existsIn_result(res,b.retrieve());
                  if(!found){    
                      res.insert(b.retrieve());}
                      else 
                      break;
        
        }
            return res;}

/*public static LinkedList<Integer> notQuery(String Query,index ind1){

LinkedList<Integer>A=new LinkedList<Integer>();

LinkedList<Integer>B=new LinkedList<Integer>();

 

if(Query.length()==0)

return A;

if(!Query.contains("NOT"))

return A;

String term = Query.replaceFirst("NOT", "").trim().toLowerCase();

boolean found=inverted.search_word_in_iverted(term);

if(found){

    A=inverted.inverted_index.retrieve().doc_IDS;

}

if(ind1.all_doc.empty()) return A;

ind1.all_doc.findFirst();

while(!ind1.all_doc.retrieve().id){

    if(!A.exist(ind1.all_doc.retrieve().id))

    B.insert(ind1.all_doc.retrieve().id);

    ind1.all_doc.findNext();

}

if(!A.exist(ind1.all_doc.retrieve().id))

B.insert(ind1.all_doc.retrieve().id);

return B ;

 

}*/

 

public static boolean existsIn_result(LinkedList<Integer> result, Integer id) {

    if(result.empty()) return false;

    result.findFirst();

 

    while(!result.last()){

        if(result.retrieve().equals(id)){

            return true;

        }

        result.findNext();

    }

    if(result.retrieve().equals(id)){

        return true;

    }

    return false;

 

}

}


    

