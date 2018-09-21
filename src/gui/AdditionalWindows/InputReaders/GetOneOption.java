package gui.AdditionalWindows.InputReaders;

import javax.swing.JOptionPane;

public class GetOneOption {
    public int response;
    
    public GetOneOption(int first, int second, int third, String title) {
        String[] options = new String[] { "First", "Second", "Third" };
        response = JOptionPane.showOptionDialog(null, " Select First for " + first + " or select Second for " +
            second + " or select Third for " + third, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
        
    }
    
    public int getResponse() {
        return response;
        
    }
    
}
