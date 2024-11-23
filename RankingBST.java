
package com.DS;

public class RankingBST {

static String Query;

static InvertedIndexBST invertedBSTObj;

static index indexObj;

static LinkedList<Integer> all_doc_in_query;

//staltic LinkedList<Doc Rank>all doc ranked;

static BST_int<Integer>DocIDs_withRank;

public RankingBST(InvertedIndexBST invertedBSTObj, index indexObj, String Query) {

this.invertedBSTObj=invertedBSTObj;

this.indexObj=indexObj;

this.Query=Query;

all_doc_in_query=new LinkedList<Integer>();

DocIDs_withRank=new BST_int<Integer>();

//all_doc_ranked new LinkedList<Doc_Rank>();

}

public static void insert_stored_inBST(){

RankQuery(Query); // to fill all_doc_in_query

if(all_doc_in_query.empty()) {

System.out.println("empty query..");

return;

}

all_doc_in_query.findFirst();

while(!all_doc_in_query.last()) {

Document d= getDucmentById(all_doc_in_query.retrieve ());

int Rank=getDucmentRankScore(d, Query); //getDucmentById

DocIDs_withRank.insert (Rank, all_doc_in_query.retrieve());

all_doc_in_query.findNext();

}

Document d= getDucmentById(all_doc_in_query.retrieve());

int Rank=getDucmentRankScore(d, Query);

DocIDs_withRank.insert (Rank, all_doc_in_query.retrieve());

}

 

public static void display_all_doc_with_score() {

DocIDs_withRank.display_decreasing();

}

public static Document getDucmentById(int id) {

return indexObj.getDucmentById(id);

}

public static int term_frequency_in_doc (Document d, String term) {

int freq=0;

LinkedList<String>words=d.words;

if(words.empty())

    return 0;

words.findFirst();

while(!words.last()) {

if (words.retrieve().equalsIgnoreCase (term))

freq++;

words.findNext();

}

if (words.retrieve().equalsIgnoreCase (term))

freq++;

return freq;   

}

public static int getDucmentRankScore(Document d , String Query){

    if (Query.length()==0)

        return 0;

    String terms[]=Query.split(" ");

    int sumFreq=0;

    for (int i=0; i<terms.length ;i++)

    {

        sumFreq+=term_frequency_in_doc(d , terms[i].trim().toLowerCase());

    }

    return sumFreq;

}

public static void RankQuery(String Query) {

LinkedList<Integer> A=new LinkedList<Integer>();

if(Query.length()==0)

    return;

String terms[]=Query.split(" ");

boolean found=false;

for(int i=0;i<terms.length;i++) {

found=invertedBSTObj.searchWord(terms[i].trim().toLowerCase()); //searchWord

if (found)

A=invertedBSTObj.inverted_index.retrieve().doc_IDS;

// Adding in 1 List (A);

Adding_in_1_List_sorted(A);

}

}

  public static void Adding_in_1_List(LinkedList<Integer>A) {

if (A.empty())

return;

A.findFirst();

while(!A.empty())

{

boolean found=existsIn_result(all_doc_in_query,A.retrieve());

if(!found) { // not found in result

all_doc_in_query.insert(A.retrieve());

}//end if

if(!A.last())

A.findNext();

else

break;

      }   

  }

 

public static void Adding_in_1_List_sorted(LinkedList<Integer>A){

    if(A.empty())

    return;

    A.findFirst();

    while(!A.empty()){

        boolean found=existsIn_result(all_doc_in_query,A.retrieve());

        if(!found){

            insert_sorted_Id_list(A.retrieve());

        }

        if(!A.last())

        A.findNext();

else

        break;

    }

}//////

   public static void insert_sorted_Id_list(Integer id) {

if(all_doc_in_query.empty())

{ all_doc_in_query.insert(id);

return;

 }

all_doc_in_query.findFirst();

while(!all_doc_in_query.last())

{

if(id>all_doc_in_query.retrieve()){

Integer id1=all_doc_in_query.retrieve();

all_doc_in_query.update(id);

all_doc_in_query.insert(id1);

return;

}

 }
   }
public static boolean existsIn_result(LinkedList<Integer> result ,Integer id){

    if (result.empty())

        return false;

    result.findFirst();

while (!result.last()) {

if (result.retrieve().equals(id)) {

return true;

}

result.findNext();

}

if (result.retrieve().equals(id)) {

return true;

}

return false;

}

}