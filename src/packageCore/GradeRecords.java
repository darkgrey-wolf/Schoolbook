/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageCore;

import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.table.DefaultTableModel;
import packageExceptions.NoSubjectException;

/**
 *
 * @author Retaliation
 */
public class GradeRecords {
    private ArrayList<Subject> subjects;
    private final int iGradeLevel;
    public GradeRecords(DefaultTableModel dm, int gl){
        ////iterate for each row
        subjects = new ArrayList<>();
        iGradeLevel = gl;
        Subject temp;
        String strTemp;
        double d1,d2,d3,d4;
        for(int k=0;k<dm.getRowCount();k++){
            strTemp = (String) dm.getValueAt(k,0);
            d1 = (double) dm.getValueAt(k,1);
            d2 = (double) dm.getValueAt(k,2);
            d3 = (double) dm.getValueAt(k,3);
            d4 = (double) dm.getValueAt(k,4);
            temp = new Subject(strTemp,iGradeLevel,d1,d2,d3,d4);
            subjects.add(temp);
        }
    }
    public GradeRecords(ArrayList<Subject> subs, int gl){
        subjects = subs;
        iGradeLevel = gl;
    }
    public Subject getSubject(int i) throws NoSubjectException{
        ListIterator li = subjects.listIterator();
        Subject temp;
        while(li.hasNext()){
            temp = (Subject) li.next();
            if(temp.getId()==i) return temp;
        }
        throw new NoSubjectException(iGradeLevel);
    }
    public DefaultTableModel updateModel(DefaultTableModel in){
        ListIterator li = subjects.listIterator();
        Subject temp;
        while(li.hasNext()){
            temp = (Subject)li.next();
            in.addRow(new Object[]{temp.getName(),temp.get1(),temp.get2(),temp.get3(),temp.get4(),temp.getAve()});
        }
        return in;
    }
    public DefaultTableModel updateModel2(DefaultTableModel in){
        return in;
    }
    public double getGenAve(){
        ListIterator li = subjects.listIterator();
        Subject temp;
        double count = 0;
        double total = 0;
        for(;li.hasNext();count++){
            temp = (Subject) li.next();
            total += temp.getAve();
        }
        return total/count;
    }
    
}
