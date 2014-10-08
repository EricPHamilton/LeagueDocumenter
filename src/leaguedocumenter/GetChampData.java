/*
 * Please do not copy/use any of this code without given permission from the author, Eric Hamilton
 */

package leaguedocumenter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ehamilt4
 */
public class GetChampData {
    
    //Interprets data from the champion File in order to display
    //String s is the string returned by LeagueDocumenter.makeGameInfoString()
    public static String getData(String s){
        
        int wins = 0;
        int loss = 0;
        int k = 0;
        int d = 0;
        int a = 0;
        double kpg = 0; //Kills per game
        double dpg = 0; //Deaths per game
        double apg = 0; //Assists per game
        
        ArrayList<String> tips = new ArrayList<String>();
        
        List<String> lines = GetChampData.getString(s);
        for(String data : lines){
            if(data.startsWith("End Score:")){
                String replacement = data.replace("End Score: ", ""); 
                String[] kda = replacement.split(" ");
                k+=Integer.parseInt(kda[0]);
                d+=Integer.parseInt(kda[1]);
                a+=Integer.parseInt(kda[2]);
            }else if(data.startsWith("Win?")){
                if(data.contains("Yes")){
                    wins++;
                }else{
                    loss++;
                }
            }else if(data.startsWith("-)")){
                tips.add(data);
            }
        }
        String info = "";
        info+="K - "+k+" | D - "+d+" | A - "+a+"\n";
        
        //Calculates and rounds averages
        DecimalFormat twoDec = new DecimalFormat("#.##");
        kpg = Double.valueOf(twoDec.format((double)k / (double)(wins + loss)));
        dpg = Double.valueOf(twoDec.format((double)d / (double)(wins + loss)));
        apg = Double.valueOf(twoDec.format((double)a / (double)(wins + loss)));
        
        info+="Per Game Averages: "+"K - "+kpg+" | D - "+dpg+" | A - "+apg+"\n";
        info+="Wins - "+wins+" | Losses - "+loss+"\n";
        
        // W/L Ratio
        DecimalFormat fourDec = new DecimalFormat("#.####");
        double winloss = Double.valueOf(fourDec.format((double)wins / (double)(wins+loss)));
        winloss = winloss*100; //Converting for percentage
        info+="Win/Loss | " + winloss + "%" + "\n";
        
        //Tips
        info+="\n"+"Tips: "+"\n";
        for(String tip : tips){
            info+=tip+"\n";
        }
        return info;
    }
    
    //Grabs the information from the champion file with each line being a new element in a List<String>
    //String champ is the champion that the user played that game. used to determine what file to write to.
    public static List<String> getString(String champ){
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("Game Results"+File.separator+champ+".txt"), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(GetChampData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lines;
    }
}
