/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageExceptions;

/**
 *
 * @author Retaliation
 */
public class NoSubjectException extends Exception {
    int gl;
    public NoSubjectException(int i){
        gl = i;
    }
    @Override
    public String toString(){
        return "Subject at Grade "+ gl + " with that ID does not exist";
    }
}
