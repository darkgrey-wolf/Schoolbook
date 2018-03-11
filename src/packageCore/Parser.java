/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageCore;

import java.util.StringTokenizer;

/**
 *
 * @author Retaliation
 */
public class Parser {
    public int getInt(String in) throws NumberFormatException{
        Integer i = new Integer(in);
        return i;
    }
    public String getString(int in, int lenght){
        Integer i = in;
        String fill = "0";
        String str = "";
        int difference = lenght - i.toString().length();
        if(difference == 0){
            return i.toString();
        }
        else if(difference > 0){
            for(int k=0;k<difference;k++){
                str = str + fill;
            }
            return str + i.toString();
        }
        else {
            return "Out of bound.";
        }
    }
    
    //used returns an object that contains sectionId(int) and sectionName(string)
    public Object[] parse1(String in){
        StringTokenizer tk = new StringTokenizer(in,":");
        int id = 0;
        String name = "NULL";
        while(tk.hasMoreTokens()){
            id = getInt(tk.nextToken());
            name = tk.nextToken();
        }
        return new Object[]{id,name};
    }
    
}
