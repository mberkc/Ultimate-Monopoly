package gui.AdditionalWindows.InputReaders;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class GetTextInput {
    JOptionPane pane;
    String      Answer;
    
    public GetTextInput(String question) {
        Answer = JOptionPane.showInputDialog(null, question);
    }
    
    public String getString() {
        return Answer;
    }
    
    public int getInt() {
        Scanner sc = new Scanner(Answer);
        int output = 0;
        
        if (sc.hasNextInt())
            output = sc.nextInt();
        sc.close();
        
        return output;
    }
}
