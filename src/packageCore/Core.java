/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageCore;
import packageFrames.*;
public class Core {
    private boolean bRepeat;
    private boolean bWaitFlag;
    private FrameIdLog frameLogin;
    private ConsoleSelector selector;
    private int icount;
    Core(){
        bRepeat = true;
        bWaitFlag = true;
        //icount = 0;
    }
    synchronized public void start(){
        
        while(bRepeat){
            bWaitFlag = true;
            selector = new ConsoleSelector(this);
            frameLogin = new FrameIdLog(selector,this);
            while(bWaitFlag){
                try {
                wait();
                } catch (InterruptedException ex) {
                    System.out.println("Unexpected interruption: " + ex);
                }
            }
            System.gc();
            //System.out.println("Stopped waiting.");
        }
    }
    synchronized public void setRepeat(boolean in){
        bRepeat = in;
        bWaitFlag = false;
        notify();
    }
}
