package GameSquares;

import gui.Dice;
import gui.Board.RollingTheDice;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import Main.Player;

public class SqueezePlay extends GameSquare {
    private static final long      serialVersionUID = 1L;
    private static Player[]        players;
    public static int              rolled;
    public static volatile boolean rolledEven       = false;
    
    public SqueezePlay(int id, Player[] players) {
        super(id, type.SqueezePlay);
        SqueezePlay.players = players;
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        gui.AdditionalWindows.SqueezePlay a = new gui.AdditionalWindows.SqueezePlay(pl);
        JDialog r = new JDialog(null, "Squeeze Play", Dialog.ModalityType.APPLICATION_MODAL);
        r.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        a.setBounds(0, 0, 250, 250);
        r.add(a);
        r.setSize(350, 200);
        r.setVisible(true);
        r.setAlwaysOnTop(true);
        r.isAutoRequestFocus();
        r.requestFocusInWindow();
    }
    
    public static void isRolledEven(Player pl) {
        rolled = new Dice().roll();
        
        if ((rolled % 2) == 0) {
            rolledEven = true;
            for (int i = 0; i < players.length; i++) {
                if (pl != players[i])
                    players[i].pay(pl, 200);
                
            }
        } else
            rolledEven = false;
    }
    
    @Override
    public String toString() {
        return "Squeeze Play. Location: " + id;
    }
    
}
