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
public class DuplicateAccountException extends Exception{
    @Override
    public String toString(){
        return "Sorry, another account with exact same name"
              + "already existed in the database.";
    }
    
}
