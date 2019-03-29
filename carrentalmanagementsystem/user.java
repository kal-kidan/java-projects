 
package carrentalmanagementsystem;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

 
public class user  extends carlist{
    
    public static void user(){
        Scanner scan=new Scanner(System.in);
        int choice;
        System.out.println("1. to see list of cars");
         System.out.println("2. to see who issued car");
         System.out.println("3. to see who return car");
         System.out.println("4. to issue car");
         System.out.println("5. to retun car");
         System.out.println("6. to punishement ");
         System.out.println("7. to search ");
         choice=scan.nextInt();
         switch(choice){
             case 1:
                 listcar();
                 break;
             case 2:
                 listofcarissued();
                 break;
             case 3:
                 listofcarreturned();
                 break;
             case 4:
                 issuecar();
                 break;
             case 5:
                 returncar();
                 break;  
             case 6:
                 punishement();
                 break;
             case 7:
                 search();
                 break;
             default :
                 System.out.print("enter correct number");
                   break;   
                 
         }
    }

    private static void listofcarissued() {
        try {
            File file;
            FileReader fileread;
            BufferedReader br;
            String line;
            file=new File("issuecar.txt");
            
            fileread=new FileReader(file)  ;
            br=new BufferedReader(fileread);
             while((line=br.readLine())!=null){
                  System.out.println(line); 
                   
                }
        } catch (FileNotFoundException ex) {
             System.out.print(ex); 
        } catch (IOException ex) {
             System.out.print(ex); 
        }
    }

    private static void listofcarreturned() {
         try {
            File file;
            FileReader fileread;
            BufferedReader br;
            String line;
            file=new File("returncar.txt");
            
            fileread=new FileReader(file)  ;
            br=new BufferedReader(fileread);
             while((line=br.readLine())!=null){
                  System.out.println(line); 
                   
                }
        } catch (FileNotFoundException ex) {
             System.out.print(ex); 
        } catch (IOException ex) {
             System.out.print(ex); 
        }
    }

    private static void issuecar() {
        try {
            String cusname,cusid,carid;
            int carno,exitno=0,date;
            FileWriter filewriter;
            PrintWriter pr;
            File file;
            Scanner scan=new Scanner(System.in);
            do{
             System.out.println("enter customer name");
            cusname=scan.nextLine();
            System.out.println("enter customer id  ");
            cusid=scan.nextLine();
            System.out.println("enter date ");
            date=scan.nextInt();
            System.out.println("enter car id ");
            carid=scan.nextLine();
            String filename="issuecar.txt";
            FileOutputStream fw=new FileOutputStream(filename,true);
            fw.write(cusname.concat(" ").getBytes());
            fw.write(cusid.concat(" ").getBytes());
            fw.write(date);
            fw.write(carid.concat(" \n").getBytes());
            System.out.print("enter 1 to exit and any key to continue");
            exitno=scan.nextInt();
            }while(exitno!=1);
        } catch (IOException ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void returncar() {
         try {
        String cusname,cusid,carid;
            int carno,exitno=1,date;
            FileWriter filewriter;
            PrintWriter pr;
            File file;
            Scanner scan=new Scanner(System.in);
            do{
             System.out.println("enter customer name");
            cusname=scan.nextLine();
            System.out.println("enter customer id  ");
            cusid=scan.nextLine();
            System.out.println("enter date ");
            date=scan.nextInt();
            System.out.println("enter car id ");
            carid=scan.nextLine();
            String filename="returncar.txt";
            FileOutputStream fw=new FileOutputStream(filename,true);
            fw.write(cusname.concat(" ").getBytes());
            fw.write(cusid.concat(" ").getBytes());
            fw.write(date);
            fw.write(carid.concat(" \n").getBytes());
            System.out.print("enter 1 to exit and any key to continue");
            exitno=scan.nextInt();
            }while(exitno!=1);
        } catch (IOException ex) {
             System.out.print(ex);
        } } 
    
    public static void punishement(){
        int dayofissued,dayofreturned,result;
        Scanner scan=new Scanner(System.in);
       
       System.out.println("enter day you issued");
       dayofissued=scan.nextInt();
       System.out.println("enter day you returned");
       dayofreturned=scan.nextInt();
       result=(dayofissued-dayofreturned)*100;
       if(result==0){
           System.out.println("thank you for returning in time");
       }
       else{
         System.out.println("your punishement is"+result);
       }
       
}
}
    

 