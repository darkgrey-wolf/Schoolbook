/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageCore;

import java.util.Date;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author Retaliation
 */
public class FormHandler {
    private JTextField tfNameF;
    private JTextField tfNameM;
    private JTextField tfNameL;
    private JTextField tfPhone;
    private JTextField tfDate;
    private JTextField tfDateYear;
    private JComboBox comboGender;
    private JComboBox comboMonth;
    private JComboBox comboType;
//    private JButton buttonSubmit;
//    private JButton buttonManage;
    private String msg;
    public FormHandler(JTextField nf,JTextField nm, JTextField nl, JTextField tp,
                       JTextField td, JTextField tdy,JComboBox gend, JComboBox month,
                       JComboBox type){
        tfNameF = nf;
        tfNameM = nm;
        tfNameL = nl;
        tfPhone = tp;
        tfDate = td;
        tfDateYear = tdy;
        comboGender = gend;
        comboMonth = month;
        comboType = type;
        msg = "";
    }
    public void resetForm(){
        tfNameF.setText("");
        tfNameM.setText("");
        tfNameL.setText("");
        tfPhone.setText("");
        tfDate.setText("");
        tfDateYear.setText("");
        comboGender.setSelectedIndex(0);
        comboMonth.setSelectedIndex(0);
        comboType.setSelectedIndex(0);
    }
    public void setEditable(boolean b){
        tfNameF.setEditable(b);
        tfNameM.setEditable(b);
        tfNameL.setEditable(b);
        tfPhone.setEditable(b);
        tfDate.setEditable(b);
        tfDateYear.setEditable(b);
        comboGender.setEnabled(b);
        comboMonth.setEnabled(b);
        comboType.setEnabled(b);
    }
    public boolean isValid(){
        Integer year,date;
        Long phone;
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        if(tfNameF.getText().isEmpty()|tfNameM.getText().isEmpty()|tfNameL.getText().isEmpty()){
            msg = "Some inputs are empty.";
            return false;
        }
        try {
            year = new Integer(tfDateYear.getText());
            date = new Integer(tfDate.getText());
            phone = new Long(tfPhone.getText());
        }catch(NumberFormatException e){
            msg+="Some inputs should only contain numbers such as birth date.\n";
            return false;
        }
        //validate year
        if(year>c.get(Calendar.YEAR)){
            msg+= "The birth year is in future!\n";
            return false;
        }
        if((c.get(Calendar.YEAR)-year)>200){
            msg+= "The birth date is somewhat of unrealistic. Please check your input.\n";
            return false;
        }
        //Validate date
        if(!dateMonthChecker(year,comboMonth.getSelectedIndex(),date)){
            msg+= "Month and day of month does not match.\n";
            return false;
        }
        
        return true;
    }
    public boolean dateMonthChecker(int year,int month,int date){
        boolean bLeapYear = false;
        if(date>31|date<0|month>11|month<0) return false;
        switch(month){
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                if(date>31) return false;
                break;
            case 3:
            case 5:
            case 8:
            case 10:
                if(date>30) return false;
                break;
            case 1: //February
                if(year%100==0){ //Leap Year Check
                    if(year%400==0){
                        bLeapYear = true;
                    }
                }
                else if(year%4==0){
                    bLeapYear=true;
                }
                if(!bLeapYear&date>28){
                    return false;
                }
                else if(bLeapYear&date>29){
                    return false;
                }
                break;
        }
        return true;
    }
    public String getMsg(){
        String temp = msg;
        msg = "";
        return temp;
    }
}
