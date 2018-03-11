/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageCore;
import packageFrames.*;

/**
 *
 * @author Retaliation
 */
public class ConsoleSelector {
    Core cycle;
    private boolean bRepeatFlag;
    private boolean bWaitFlag;
    private PersonInfo piBasicInfo;
    public ConsoleSelector(Core cycle){
        this.cycle = cycle;
    }
    synchronized public void startConsole(PersonInfo basicInfo){
        //System.out.println("startConsole: " + strAcType + " " + fID +" " + strFullName);
        this.piBasicInfo=basicInfo;
        switch(piBasicInfo.getType()){
            case "Admin":
                callAdminConsole();
                break;
            case "Teacher":
                callTeacherConsole();
                break;
            case "Student":
                callStudentConsole();
                break;
            case "Default":
                break;
        }
        //cycle.setRepeat(bRepeatFlag);
    }
    public void callAdminConsole(){
        FrameAdmin conAdmin = new FrameAdmin(cycle,piBasicInfo);
    }
    public void callTeacherConsole(){
        FrameTeacher conTeacher = new FrameTeacher(cycle,piBasicInfo);
    }
    public void callStudentConsole(){
        FrameStudent conStudent = new FrameStudent(cycle,piBasicInfo);
    }
}
