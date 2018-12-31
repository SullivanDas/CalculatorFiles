
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author David Sullivan
 */

public class client {
    
    public static void main(String[] args){

        showMenu();
        
    }
    
    public static void showMenu(){
        String response;
        
        String info = "Enter an equation \n";
        
        try{
            response = JOptionPane.showInputDialog(null, info, "Menu", 0);
            

            if(response.isEmpty()){
                showMenu();
            }
            else{
                evaluate(response);
            }

            


        }
        catch(NullPointerException npe){
            
        }

    }
        
    public static void evaluate(String equation){
        ShuntingYard shunting = new ShuntingYard();
        ArrayQueue queue;
        
        try{

            queue = shunting.infixToPost(equation);
            
            double answer = Double.parseDouble(shunting.evaulate(queue));
            String result = shunting.splitEquation(equation) + " = " + answer;
            showAnswer(result);
            showMenu();

        }
        catch(IllegalStateException ise){
            failedInput();
            showMenu();
        }

    }
    
        
    /**
     * notifies the user of a failed input.
     */
    public static void failedInput(){
        JOptionPane.showMessageDialog(null, "Please enter a valid Expression" , "Error", 0);
    }
    
    public static boolean confirmExit(){
       int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit confirmation", 0);
       
       return response == 0;
    }
    /**
     * Shows an answer on a JPanel.
     * 
     * @param <T> the type passed
     * @param t the answer of type t that will be displayed.
     */
    public static <T> void showAnswer(T t){
        JOptionPane.showMessageDialog(null, t.toString(), "Answer", 0);
    }
}
