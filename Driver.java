
package com.DS;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Driver {
    static index index1;
    int totalTokens=0;
    index indexObj;
    InvertedIndex invertedObj;
    InvertedIndexBST invertedBSTObj;
    LinkedList<String> stopWords;
    LinkedList<String> unique_words=new LinkedList<String>();
    public Driver(){
        stopWords= new LinkedList<>();
        indexObj=new index();
        invertedObj= new InvertedIndex();
        invertedBSTObj=new InvertedIndexBST();
    }
    public void Load_stopWords(String fileName){
            try{
                File f=new File(fileName);
                Scanner s=new Scanner(f);
                while (s.hasNextLine()) {
                    String line=s.nextLine();
                    stopWords.insert(line);      
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
    }
    public void Load_all_doc(String fileName){
        String line=null;
        try{
            File f=new File(fileName);
            Scanner s=new Scanner(f);
            //to skip the first line or row
            s.nextLine();
            while(s.hasNextLine()){
                line=s.nextLine();
                if(line.trim().length()<3){
                    System.out.println("this line is empty, skipping this line"+line);
                    break;
                }
                String x=line.substring(0,line.indexOf(','));
                int id= Integer.parseInt(x.trim());
                System.out.println("line="+line);
                String content = line.substring(line.indexOf(',')+1).trim();
                System.out.println("content="+content);
                
                
                LinkedList<String>wordsDoc=index_inverted_index(content,id);
                Document d=new Document(id, wordsDoc,content);
                indexObj.add_Document(d);
                
            }
            
        }catch(Exception e){
            System.out.println("End of file or other error encountered!!");
        }
            System.out.print("total tokens is: "+totalTokens);

    }
    public LinkedList<String>index_inverted_index(String content, int id){
        LinkedList<String>wordsDoc=new LinkedList<String>();
 
       content = content.replaceAll("\'"," ");//*****
       content = content.replaceAll("-"," ");//*****
       //content = content.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", content);
        content = content.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
        String[] tokens=content.split("\\s+");
        totalTokens+=tokens.length;
        
       for(String w : tokens){
          // totalTokens+=1;
        if (!unique_words.exist(w)){
            unique_words.insert(w);
             //num_of_unique_words++; 
           }
            if(!existsIn_stop_words(w)){
                wordsDoc.insert(w);
                invertedObj.add(w, id);
            }
        }
       return wordsDoc;
    }
    public boolean existsIn_stop_words(String word){
        if(stopWords==null || stopWords.empty())
        return false;
        stopWords.findFirst();
        while (!stopWords.last()) {
            if(stopWords.retrieve().equals(word))
            return true;
            stopWords.findNext();     
        }
        //for the last 
        if(stopWords.retrieve().equals(word)){
        return true;}

        return false;
    }
    public void Load_all_files(String stopFile, String documentsFile){
        Load_stopWords(stopFile);
        Load_all_doc(documentsFile);
    }
    
    public void displayDocBYId(LinkedList<Integer>IDs){
        if (IDs.empty()){
        System.out.println("no documents exsist");
         return ;
}
            IDs.findFirst();
            while(!IDs.last())
            {
                Document d=indexObj.getDucmentById(IDs.retrieve());
                if (d!=null)
                {
               System.out.println("Document "+d.id+":"+d.content);
                }
                IDs.findNext();
            }
              Document d=indexObj.getDucmentById(IDs.retrieve());
                if (d!=null)
                {
               System.out.println("Document "+d.id+":"+d.content);
                }
              System.out.println("") ;
            }    
            public void display_doc_with_given_IDS(LinkedList<Integer>IDs){


if (IDs.empty()) {
System.out.println("no documents exist");
return;
}
IDs.findFirst();
while (!IDs.last () ){
Document d=index1.getDucmentById(IDs.retrieve()) ;
if (d!=null)
{
System.out.println ("Document "+d.id+":"+d.content) ;
}
IDs.findNext();
}
Document d=index1.getDucmentById (IDs.retrieve ()) ;
if (d!=null){
System. out. println ("Document "+d.id+": "+d.content) ;
}
System.out.println ("");
}
    
    public static void main(String[] args)
    {
        Driver d=new Driver();
        d.Load_all_files("stop.txt", "dataset.csv");
        d.indexObj.displayDocument();
        System.out.println("\n========================");
        d.invertedObj.display_inverted_index();
        Test5withMenu();
    }
   public void displayStopWords(){
       stopWords.display();
   }
   
    public static void display_Mune()

        {

System.out.println("1-Retrieve a term (there are choices"+ ":using index with lists"+ "-inverted index with lists"+ "-inverted index with BST.");

System.out.println ("2-Boolean Retrieval.");

System. out.println ("3-Ranked Retrieval.");

System.out.println ("4-Indexed Documents:print all documents ");

System.out.println("5- number of documents in the index");

System.out.println ("6-number of unique words in indexed.");

System.out.println ("7-show inverted index with List of lists");

System.out.println("8-show inverted index with BST");

System.out.println("9-Indexed Tokens:to show number of vocabulary and tokens in the index.");

System.out.println ("10-Exit");

 

        }

         public static void Test5withMenu(){

            Driver d=new Driver() ;

            d.Load_all_files( "stop.txt","dataset.csv");

            Scanner s=new Scanner (System.in);

            int ch=0;

           do {

          display_Mune();

         ch=s.nextInt();

          switch (ch)

         {

          case 1:

          System.out.println("enter a term to retrieve.");

          String term=s.next();

          term=term.toLowerCase().trim();

          System.out.println(":using index with lists");

          LinkedList<Integer>res=Driver.index1.get_all_documents_given_term(term);

          System.out.print("word: "+term+" [");

          res.display();

          System.out.println("]");

          System.out.println("----------------------------------");

          System.out.println("-inverted index with lists");

          boolean found=d.invertedObj.searchWord(term);

          if(found)

          d.invertedObj.inverted_index.retrieve().display();

          else

         System.out.println("not found in inverted index with lists");

         System.out.println("-inverted index with BST.");

         boolean found2=d.invertedBSTObj.searchWord(term);

         if (found2)

         d.invertedObj.inverted_index.retrieve().display();

         else

          System.out.println("Not found in inverted index with lists");

         break;

 

         case 2:

         s.nextLine();

         System.out.println("enter a query to retrieve");

         String query=s.nextLine();

         query=query.toLowerCase();

         query=query.replaceAll(" and "," AND ");

         query=query.replaceAll(" or "," OR ");

        System.out.println("\nwhich method you want to make query enter: \n"

        + "1-for using index \n"

        + "2-for using inverted index list of lists \n"

        + "3-for using BST\n");

        int x=s.nextInt();

        do{

         if(x==1){

         QueryProcessing_from_index g=new QueryProcessing_from_index(Driver.index1);

         System.out.println("=========="+query+"==========");

         LinkedList res1= QueryProcessing_from_index.MixedQuery(query);
         //ناقص ميثود

         d.display_doc_with_given_IDS(res1); 

        }

else if (x==2){

        query_processing g=new query_processing(d.invertedObj) ;

         System.out.println("=========="+query+"==========");

        LinkedList res1= query_processing.MixedQuery(query);

         d.display_doc_with_given_IDS(res1); 

        }

        else if(x==3){

        query_processing_bst g=new query_processing_bst(d.invertedBSTObj);

       System.out.println("=========="+query+"==========");

        LinkedList res1= query_processing_bst.MixedQuery(query);

         d.display_doc_with_given_IDS(res1); 

 

        }

else if(x==4)

break;

else

  System.out.println("WRONG query");

System.out.println("\nwhich method you want to make query enter: \n"+ "I-for using index \n"

+ "2-fut using inverted index list of lists \n"

+ "3-for using BST\n");

         x=s.nextInt();

         }while(x!=4);

    break;

    case 3:

s.nextLine();

System.out.println("enter a query to Rank");

String query2=s.nextLine();

query2=query2.toLowerCase();

Ranking R5=new Ranking(d.invertedBSTObj, index1, query2);

R5.insert_sotred_in_list();

R5.display_all_doc_with_score_usingList();

break;

case 4:

d.index1.displayDocument();

System.out.println("--------------");

break;

case 5:

System.out.println ("num of documents="+Driver.index1.all_doc.n);

System.out.println("--------------");       

    break;

    case 6:

System.out.println("num of unique words without stop words="+d.invertedObj.inverted_index.n);    

 System.out.println("--------------");       

    break;

case 7:

d.invertedObj.display_inverted_index();

break;

case 8:

d.invertedBSTObj.display_inverted_index();

break;

 

case 9:

System.out.println("num of tokens="+d.totalTokens) ;

System.out.println("num of toigns words including stop words="+d.unique_words.n) ;

break;

case 10:

System.out.println ("goodbye"); 

 break;

default:

    System.out.println ("error input ..TRY AGAIN >_<"); 

     break;

           }

         }while(ch!=10);
           
         }

      

            



         public void displaytopWordess(){

            stopWords.display();

         }

}


      

