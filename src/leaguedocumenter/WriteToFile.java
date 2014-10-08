/*
 * Please do not copy/use any of this code without given permission from the author, Eric Hamilton
 */

package leaguedocumenter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ehamilt4
 */

//This class handles writing to files.
public class WriteToFile {
    
    //Writes the the champion file
    //String champ is what the champion played is, String s is what the method attempts to write to the file.
    public static void writeToFile(String champ, String s){
        try{
            
            //Checks if game folder containing champion data is created
            File f = new File("Game Results"); //Folder containing game data
            if(!f.isDirectory()){ //If folder does not exist
                System.out.println("Creating Directory: " + f.toString());
                boolean result = f.mkdir();
                if(result){
                    System.out.println("Created Directory!");
                }else{
                    System.out.println("Error while creating directory.");
                }
            }
            
            //Checks game folder for champion file
            File champData = new File("Game Results\\"+champ+".txt");
            if(!f.exists()){ //If file does not exist
                System.out.println("Creating Champion text file: "+champData.toString());
                boolean result = champData.createNewFile();
                if(result){
                    System.out.println("Created File!");
                }else{
                    System.out.println("Error while creating file.");
                }
            }
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Game Results" + File.separator + champ + ".txt", true)));
            out.write(s);
            out.flush();
            out.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
   
    //Writes to the GeneralTips file.
    //String s is what the method writes to the file.
    public static void writeToTipsFile(String s) {
        try{
            //Checks if tip file is created
            File tipsFile = new File("General Tips.txt");
            if(!tipsFile.exists()){ //If file does not exist
                System.out.println("Creating Tip text file.");
                boolean result = tipsFile.createNewFile();
                if(result){
                    System.out.println("Created Tips File!");
                }else{
                    System.out.println("Error while creating Tip file.");
                }
            }
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("General Tips.txt", true)));
            out.write(s);
            out.flush();
            System.out.println("Written.");
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
