/*
 * Please do not copy/use any of this code without given permission from the author, Eric Hamilton
 */

package leaguedocumenter;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * @author ehamilt4
 * @version 1.00
 */

//This class mainly collects the information and provides the methods to output data as a string.
public class LeagueDocumenter {
    
    //All data required to write to file
    public static String role = "";
    public static boolean isBot = false;
    public static String ownChampion = "";
    public static String ownSupport = null;
    public static String enemyChampion = "";
    public static String enemySupport = null;
    public static ArrayList<Tip> tips = new ArrayList<Tip>();
    public static int kills, deaths, assists;
    public static int difficulty = 0;
    public static boolean isWin;

    public static void main(String[] args) throws InterruptedException {
        
        try {
			DecideWhich.runGUI();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Will chain into champDecGUI, TipsGUI, and FinishGUI
        
    }
    
    //Takes data about the role and stores it into local variables.
    //String type declares what the data is, newValue is what the data should be changed to.
    public static void giveRoleData(String type, String newValue){ 
        if(type.equalsIgnoreCase("Role")){
            role = newValue;
            if(role.equalsIgnoreCase("ADC") || role.equalsIgnoreCase("Support")){
                isBot = true;
            }
        }
    }
    
    //Give champion data and store into local variables
    //String type declares what the data is, newValue is what the data should be changed to.
    public static void giveChampionData(String type, String champion){
        switch (type) {
            case "Self":
                ownChampion = champion;
                break;
            case "Enemy":
                enemyChampion = champion;
                break;
            case "Partner": //Partners will be null if there is one person per lane
                ownSupport = champion;
                break;
            case "Enemy Partner":
                enemySupport = champion;
                break;
        }
    }
    
    //Give end-game data and store it.
    //String type declares what the data is, newValue is what the data should be changed to.
    public static void giveEndGameData(String type, String data){
        
        switch(type){
            case "KDA":
                String[] kda = data.split(" "); //Does this to support double-digit kdas

                    kills = Integer.parseInt(kda[0]);
                    deaths = Integer.parseInt(kda[1]);
                    assists = Integer.parseInt(kda[2]);
                    //JOptionPane.showMessageDialog(new JFrame(), "Inputted data: (" + data + ") is not valid for a K D A. Check the formatting.", "ERROR IN FORMAT", JOptionPane.ERROR_MESSAGE);
                break;
            case "Difficulty":
                difficulty = Integer.parseInt(data);
                break;
            case "Result":
                isWin = data.equalsIgnoreCase("true");
                break;        
        }
    }
    
    //Add a tip to the arraylist for storage
    public static void addTip(Tip t){
        tips.add(t);
    }

    //Creates a string to output to a file for easy-output.
    public static String makeGameInfoString(){
        String s = "";
        s+="Role: " + role + " "+ "\n";
        if(isBot){
            s+="Champion: " + ownChampion + " | Partner: " + ownSupport + " " + "\n";
            s+="Enemy ADC: " + enemyChampion + " | Enemy Partner: " + enemySupport + " " + "\n";
        }else{
            s+="Champion: " + ownChampion + " " + "\n";  
            s+="Enemy: " + enemyChampion + " " + "\n";
        }
        s+= "Tips: " + "\n";
        for(Tip t : tips){
            if(t.isChampSpecific){
                s+= "-)" + t.toString() + "\n";
            }
        }
        s+= "End Score: " +kills+" "+deaths+" "+assists+"\n";
        s+= "Difficulty in Lane: " +difficulty+" out of 100" + "\n";
        if(isWin){
            s+="Win? Yes" + "\n";
        }else{
            s+="Win? No" + "\n";
        }
        s+="[]"+"\n";
        return s;
    }
    
    //Makes the General Tips string in order to output to General Tips.txt
    public static String makeGeneralTipsString(){
        String s = "";
        for(Tip t : tips){
            if(!t.isChampSpecific){
                s+=t.toString()+"\n";
            }
        }
        return s;
    }
    
}
