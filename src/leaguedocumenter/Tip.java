/*
 * Please do not copy/use any of this code without given permission from the author, Eric Hamilton
 */

package leaguedocumenter;

/**
 *
 * @author ehamilt4
 */

//This class was created in order to easily determine whether a tip is Champ-specific or general for later organization.
public class Tip {
    public boolean isChampSpecific = false;
    public String tip;
    
    //t determines what the text of the tip is, specific is whether or not the tip is Champion Specific or general.
    public Tip(String t, boolean specific){
        tip = t;
        isChampSpecific = specific;
    }
    
    @Override
    public String toString(){
        return tip;
    }

}
