/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DS;
import java.io.File ;
import java.util.Scanner ;
public class reading {
    // Method to load data from a file
    public static void Load(String fileName) {
        String line = null;
        try {
            // Open the file
            File f = new File(fileName);
            Scanner s = new Scanner(f);

         
                s.nextLine(); // Skips the first line (header)

            // Read the remaining lines in the file
            while (s.hasNextLine()) {
                line = s.nextLine();
             if (line.trim().length()<3){
          System.out.println("empty line found,skipping this line="+line);
          break;
             }
            System.out.println(line);
         //  String[] values =line.split(",");
         String x =line.substring(0,line.indexOf(','));
         int id= Integer.parseInt(x.trim());
         String content = line.substring(line.indexOf(',')+1).trim();   
         }
        } catch(Exception e){
        System.out.println("end of File"); 
                } 
 }
     public static void main (String []args){
         Load("dataset.csv");
         Load ("stop.txt");
     }
}
