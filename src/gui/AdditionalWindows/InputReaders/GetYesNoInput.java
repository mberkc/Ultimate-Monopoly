package gui.AdditionalWindows.InputReaders;

import javax.swing.JOptionPane;

public class GetYesNoInput {
    Boolean getAnswer;
    
    public GetYesNoInput(String header, String text, String yesMessage, String noMessage) {
        int reply = JOptionPane.showConfirmDialog(null, header, (text),
            JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            getAnswer = true;
            JOptionPane.showMessageDialog(null, yesMessage);
        }
        else {
            JOptionPane.showMessageDialog(null, noMessage);
            getAnswer = false;
        }
    }
    
    public GetYesNoInput(String header, String text) {
        int reply = JOptionPane.showConfirmDialog(null, header, (text),
            JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            getAnswer = true;
        }
        else {
            getAnswer = false;
        }
    }
    
    public boolean getValue() {
        return getAnswer;
    }
}
