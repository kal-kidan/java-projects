 
package carrentalmanagementsystem;

import java.io.*;
import java.util.Scanner;
 
 
public class Carrentalmanagementsystem extends carlist{

 
    public static void main(String[] args) {
         System.out.println("*******************************************************");
         System.out.println("*_____________________________________________________*");
         System.out.println("*             Welcome To AddisAbeba Car Rental        *");
         System.out.println("*_____________________________________________________*");
         System.out.println("*1.  enter 1 for user                                 *");
         System.out.println("*2.  enter 2 for admin                                *");
         System.out.println("*******************************************************");
          Scanner input= new Scanner(System.in);
       int inputfromuser=input.nextInt();
       if(inputfromuser==1){
           new user().user();
       }
       else if(inputfromuser==2){
            System.out.print("\f");
           String username=null,password=null,line;
           Boolean bool=true;
          Scanner in= new Scanner(System.in);
        System.out.println("Username:");
        username=in.nextLine();
        System.out.println("Password:");
        password=in.nextLine();
        FileReader fileread;
        BufferedReader br;
        File file;
         int count=0;
        try {
            file=new File("password.txt");
         
              fileread=new FileReader(file)  ;
           br=new BufferedReader(fileread);
           while((line=br.readLine())!=null){
               if(count==0){
                   if(!line.equals(username)){
                       bool=false;
                   }
               }
               else if(count==1){
                     if(!line.equals(password)){
                       bool=false;
                   }
               }
               count++;
           }
           if(bool){
               admin ad=new admin();
               ad.loginhome();
           }
           else{
                  System.out.println("you entered wrong password or username:");
           }
        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }
                 
       
    else {
          System.out.print("please enter correct number");
      
       }
    }}

    

    