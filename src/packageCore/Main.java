package packageCore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
          boolean b = false;
          for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
              b = true;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
          }
          if(!b){
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          }
          Core core = new Core();
          core.start();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
           System.out.println("Error at start up: " + ex.getMessage());
           JOptionPane.showMessageDialog(null,"Start up error","Fatal Error",JOptionPane.ERROR_MESSAGE);
        }
       
    }
    
}
