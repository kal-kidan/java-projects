
package carrentalmanagementsystem;

import java.io.*;
 
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

 
  class admin extends carlist {
    public static void main(String[] args) {
    }

    public static void loginhome() { 
        int choice;
        Scanner input= new Scanner(System.in);
        
         System.out.println("1 to add car"); 
        System.out.println("2 to delete car"); 
        System.out.println("3 to see list of car"); 
        System.out.println("4 to change your password");
        System.out.println("5 search");
        choice=input.nextInt();
        if(choice==1){
            insert();
        }
        if(choice==2){
            delete();
        }
        if(choice==3){
           listcar();  
        }
         if(choice==4){
           changepassword();  
        }
           if(choice==5){
           search();  
        }
        
                
    }

  

    private static void delete() {
        int carno,numofcar=0;
        File file,file2;
        FileReader fileread,fileread2;
        BufferedReader br,br2;
        FileOutputStream fw,fw2;
        String line,line2;
        int x=0;
         Scanner scanner=new Scanner(System.in);
         Scanner scan=new Scanner(System.in);
         System.out.println("Insert the catagory of the car you want to delete"); 
        System.out.println("1. public bus"); 
         System.out.println("2. home car");  
         System.out.println("3. sport car");
          carno= scanner.nextInt();
          System.out.println("Insert the number of the car you want to delete");
          numofcar=scan.nextInt();
          if(carno==1){
            try { 
                int counter=0;
             file=new File("publicbus.txt");        
                fileread=new FileReader(file)  ;
                fw=new FileOutputStream("filefordeletingpulicbus.txt",false);
                br=new BufferedReader(fileread);
                while((line=br.readLine())!=null){
                    counter++;
                    if(numofcar!=counter){
                  System.out.println(line);
                  fw.write(line.getBytes() );
                  
                    }
                }
                file2=new File("filefordeletingpulicbus.txt");
                fileread2=new FileReader(file2)  ;
                 br2=new BufferedReader(fileread2);
                 fw2=new FileOutputStream("publicbus.txt",false);
                  while((line2=br2.readLine())!=null){ 
                    fw2.write(line2.concat(" \n").getBytes() );
                   
                }
                
            } catch (FileNotFoundException ex) {
                 System.out.println(ex);
            } catch (IOException ex) {
                 System.out.println(ex);
            }
          }
          else if(carno==2){
               try { 
                int counter=0;
             file=new File("homecar.txt");        
                fileread=new FileReader(file)  ;
                fw=new FileOutputStream("filefordeletingpulicbus.txt",false);
                br=new BufferedReader(fileread);
                while((line=br.readLine())!=null){
                    counter++;
                    if(numofcar!=counter){
                  System.out.println(line);
                  fw.write(line.getBytes() );
                  
                    }
                }
                file2=new File("filefordeletinghomecar.txt");
                fileread2=new FileReader(file2)  ;
                 br2=new BufferedReader(fileread2);
                 fw2=new FileOutputStream("homecar.txt",false);
                  while((line2=br2.readLine())!=null){ 
                  fw2.write(line2.concat(" \n").getBytes() );
                   
                }
                
            } catch (FileNotFoundException ex) {
                 System.out.println(ex);
            } catch (IOException ex) {
                 System.out.println(ex);
            }
          }
          else if(carno==3){
               try { 
                int counter=0;
             file=new File("sportcar.txt");        
                fileread=new FileReader(file)  ;
                fw=new FileOutputStream("filefordeletingsportcar.txt",false);
                br=new BufferedReader(fileread);
                while((line=br.readLine())!=null){
                    counter++;
                    if(numofcar!=counter){
                  System.out.println(line);
                  fw.write(line.getBytes() );
                  
                    }
                }
                file2=new File("filefordeletingsportcar.txt");
                fileread2=new FileReader(file2)  ;
                 br2=new BufferedReader(fileread2);
                 fw2=new FileOutputStream("sportcar.txt",false);
                  while((line2=br2.readLine())!=null){ 
                  fw2.write(line2.concat(" \n").getBytes() );
                   
                }
                
            } catch (FileNotFoundException ex) {
                 System.out.println(ex);
            } catch (IOException ex) {
                 System.out.println(ex);
            }
          }
    }

    private static void insert() {
        int carno,exitno=0;
           FileWriter filewriter;
        PrintWriter pr;
        File file;
        String carid=null,carname=null,carcolor=null,carprice=null;
        Scanner scan=new Scanner(System.in);
            
         System.out.println("Insert the catagory of the car you want to add"); 
         System.out.println("1. public bus"); 
         System.out.println("2. home car");  
         System.out.println("3 sport car"); 
         carno=scan.nextInt();
     if(carno==1){
          Scanner scanner=new Scanner(System.in);
    do{
        
        System.out.println("Insert Car Id"); 
        carid=scanner.nextLine();
          System.out.println("Insert Car Name"); 
        carname=scanner.nextLine();
          System.out.println("Insert Car Color"); 
        carcolor=scanner.nextLine();
        System.out.println("Insert Car Price"); 
        carprice=scanner.nextLine();
            try {
               String filename="publicbus.txt";
          FileOutputStream fw=new FileOutputStream(filename,true);
                 fw.write(carid.concat("        ").getBytes());
                 fw.write(carname.concat("       ").getBytes());
                 fw.write(carcolor.concat("        ").getBytes());
                 fw.write(carprice.concat("        \n").getBytes());
                 
            } catch (IOException ex) {
                  System.out.println(ex);  
            }
             System.out.println("the car added successfully");
             System.out.println("insert 1 to exit and any key to continue");
            exitno=scan.nextInt();
    }while(exitno!=1);
     }
     else if(carno==2){
              Scanner scanner=new Scanner(System.in);
    do{
        
        System.out.println("Insert Car Id"); 
        carid=scanner.nextLine();
          System.out.println("Insert Car Name"); 
        carname=scanner.nextLine();
          System.out.println("Insert Car Color"); 
        carcolor=scanner.nextLine();
         System.out.println("Insert Car Price"); 
        carprice=scanner.nextLine();
            try {
               String filename="homecar.txt";
          FileOutputStream fw=new FileOutputStream(filename,true);
                 fw.write(carid.concat("        ").getBytes());
                 fw.write(carname.concat("        ").getBytes());
                 fw.write(carcolor.concat("       ").getBytes());
                 fw.write(carprice.concat("        ").getBytes());
                 
            } catch (IOException ex) {
                  System.out.println(ex);  
            }
             System.out.println("the car added successfully");
             System.out.println("insert 1 to exit and any key to continue");
            exitno=scan.nextInt();
    }while(exitno!=1);
     }
     else if(carno==3){
            Scanner scanner=new Scanner(System.in);
    do{
        
        System.out.println("Insert Car Id"); 
        carid=scanner.nextLine();
          System.out.println("Insert Car Name"); 
        carname=scanner.nextLine();
          System.out.println("Insert Car Color"); 
        carcolor=scanner.nextLine();
         System.out.println("Insert Car Price"); 
        carprice=scanner.nextLine();
        
            try {
                file=new File("sportcar.txt");
               String filename="sportcar.txt";
          FileOutputStream fw=new FileOutputStream(filename,true);
                 fw.write(carid.concat("      ").getBytes());
                 fw.write(carname.concat("      ").getBytes());
                 fw.write(carcolor.concat("       ").getBytes());
                 fw.write(carprice.concat("        \n").getBytes());
                 
            } catch (IOException ex) {
                  System.out.println(ex);  
            }
             System.out.println("the car added successfully");
             System.out.println("insert 1 to exit and any key to continue");
            exitno=scan.nextInt();
    }while(exitno!=1);
     }
    }

    public static void changepassword() {
         
        FileOutputStream fw = null;
        try {
            String newpassword;
            Scanner scan=new Scanner(System.in);
            System.out.println("enter the new password");
            newpassword=scan.nextLine();
            String filename="password.txt";
            fw = new FileOutputStream(filename,false);
            fw.write("admin".concat("\n").getBytes());
            fw.write(newpassword.getBytes());
        } catch (FileNotFoundException ex) {
           System.out.println(ex);
        } catch (IOException ex) {
           System.out.println(ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
               System.out.println(ex);
            }
        }
        int c;
          Scanner input=new Scanner(System.in);
           System.out.println("enter 1 for backing to menu and any other key to exit");
     c=input.nextInt();
    if(c==1){
   loginhome();   
  }
    else{
        System.exit(0);
    }
    }
   
    }


