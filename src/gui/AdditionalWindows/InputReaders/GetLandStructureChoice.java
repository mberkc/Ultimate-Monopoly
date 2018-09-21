package gui.AdditionalWindows.InputReaders;

import javax.swing.JOptionPane;

public class GetLandStructureChoice {
    public int response;
    
    public GetLandStructureChoice(String title) {
        String[] options = new String[] { "House", "Hotel", "Skyscraper", "Cancel" };
        response = JOptionPane.showOptionDialog(null, "Build options",
            "Select which structure to build,", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, options[0]);
    }
    public int getResponse() {
        return response;
    }
}
