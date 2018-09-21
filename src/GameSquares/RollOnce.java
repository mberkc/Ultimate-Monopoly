package GameSquares;

import gui.Dice;
import gui.Board.RollingTheDice;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import Main.Player;

public class RollOnce extends GameSquare {
    private static final long      serialVersionUID = 1L;
    public static volatile boolean rolledSame       = false;
    static int                     winningPrize     = 100;
    public static int              rolled, randomRoll;
    
    
    public RollOnce(int id) {
        super(id, type.RollOnce);
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        gui.AdditionalWindows.RollOnce a = new gui.AdditionalWindows.RollOnce(pl);
        JDialog r = new JDialog(null, "Roll Once", Dialog.ModalityType.APPLICATION_MODAL);
        a.setBounds(0, 0, 250, 250);
        r.add(a);
        r.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        r.setSize(350, 200);
        r.setAlwaysOnTop(true);
        r.setVisible(true);
        
    }
    
    public static void isRolledSame(Player pl, int randomRoll) {
        rolled = new Dice().roll();
        if (rolled == randomRoll) {
            rolledSame = true;
            pl.addMoney(winningPrize);
        } else
            rolledSame = false;
        
    }
    
    @Override
    public String toString() {
        return "Roll Once. Location: " + id;
    }
}
