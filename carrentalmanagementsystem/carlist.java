 
package carrentalmanagementsystem;
 
import java.io.*;
 
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class carlist {
    public static void listcar(){
        int carno ;
        File file;
        FileReader fileread;
        BufferedReader br;
        String line;
         Scanner scanner=new Scanner(System.in);
         System.out.println("Insert the catagory of the car you want to list"); 
         System.out.println("1. public bus"); 
         System.out.println("2. home car");  
         System.out.println("3 sport car"); 
         carno= scanner.nextInt();
        if(carno==1){
             int i=1;
            try {
                file=new File("publicbus.txt");
                
                fileread=new FileReader(file)  ;
                br=new BufferedReader(fileread);
                System.out.println("Car Id   CarName   CarColor   price"); 
                System.out.println("____________________________________\n________________________________________"); 
                while((line=br.readLine())!=null){
                  System.out.println(i+".      "+line); 
                  i++;
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex); 
            } catch (IOException ex) {
               System.out.println(ex); 
            }
        }
         else if(carno==2){
             int i=1;
             try {
                file=new File("homecar.txt");
                
                fileread=new FileReader(file)  ;
                br=new BufferedReader(fileread);
                System.out.println("Car Id   CarName   CarColor   price"); 
                System.out.println("____________________________________\n________________________________________"); 
                while((line=br.readLine())!=null){
                       
                  System.out.println(i+".      "+line); 
                  i++; 
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex); 
            } catch (IOException ex) {
               System.out.println(ex); 
            } 
        }
           else if(carno==3){
               int i=1;
              try {
                file=new File("sportcar.txt");
                
                fileread=new FileReader(file)  ;
                br=new BufferedReader(fileread);
                System.out.println("Car Id   CarName   CarColor   price"); 
                System.out.println("____________________________________\n________________________________________"); 
                while((line=br.readLine())!=null){
                  System.out.println(i+".    "+line); 
                  i++;   
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex); 
            } catch (IOException ex) {
               System.out.println(ex); 
            }
        }
        }
    public static void search(){
         int carno ;
        File file;
        FileReader fileread;
        BufferedReader br;
        String line;
        String id=null;
         Scanner scanner=new Scanner(System.in);
         Scanner input=new Scanner(System.in);
         System.out.println("Insert the catagory of the car you want to list"); 
         System.out.println("1. public bus"); 
         System.out.println("2. home car");  
         System.out.println("3 sport car"); 
         carno= scanner.nextInt();
         Boolean searchfound=false;
         if(carno==1){
             System.out.println("insert the id of the car you want to search....");
             id=input.nextLine();
             try {
                 file=new File("publicbus.txt");
                 
                 fileread=new FileReader(file)  ;
                 br=new BufferedReader(fileread);
                  
                 try {
                     if(br.readLine()==null){
                       System.out.println("no car registered yet");  
                     }
                     while((line=br.readLine())!=null){
                         if(line.contains(id)){
                             searchfound=true;
                                  System.out.println(line);
                                  
                         }
                     }
                     if(!searchfound){
                        System.out.println("Search not found please enter correct id");  
                     }
                 } catch (IOException ex) {
                       System.out.println(ex);
                 }
             } catch (FileNotFoundException ex) {
                         System.out.println(ex); 
             }
         }
            if(carno==2){
             System.out.println("insert the id of the car you want to search....");
             id=input.nextLine();
             try {
                 file=new File("homecar.txt");
                 
                 fileread=new FileReader(file)  ;
                 br=new BufferedReader(fileread);
                  
                 try {
                     if(br.readLine()==null){
                       System.out.println("no car registered yet");  
                     }
                     while((line=br.readLine())!=null){
                         if(line.contains(id)){
                             searchfound=true;
                                  System.out.println(line);
                                  
                         }
                     }
                     if(!searchfound){
                        System.out.println("Search not found please enter correct id");  
                     }
                 } catch (IOException ex) {
                       System.out.println(ex);
                 }
             } catch (FileNotFoundException ex) {
                         System.out.println(ex); 
             }
         }
               if(carno==3){
             System.out.println("insert the id of the car you want to search....");
             id=input.nextLine();
             try {
                 file=new File("sportcar.txt");
                 
                 fileread=new FileReader(file)  ;
                 br=new BufferedReader(fileread);
                  
                 try {
                     if(br.readLine()==null){
                       System.out.println("no car registered yet");  
                     }
                     while((line=br.readLine())!=null){
                         if(line.contains(id)){
                             searchfound=true;
                                  System.out.println(line);
                                  
                         }
                     }
                     if(!searchfound){
                        System.out.println("Search not found please enter correct id");  
                     }
                 } catch (IOException ex) {
                       System.out.println(ex);
                 }
             } catch (FileNotFoundException ex) {
                         System.out.println(ex); 
             }
         }
    }
    }
 