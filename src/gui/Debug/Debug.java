package gui.Debug;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import Main.Main;

public class Debug extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel            contentPane;
    static PlayerPanel        Player1, Player2, Player3, Player4;
    
    public Debug() {
        switch (Main.players.length) {
            case 1:
                setBounds(25, 25, 631, 192);
                break;
            case 2:
                setBounds(25, 25, 631, 343);
                break;
            case 3:
                setBounds(25, 25, 631, 492);
                break;
            default:
                setBounds(25, 25, 631, 655);
                break;
        }
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Consolas", Font.PLAIN, 15));
        tabbedPane.setBorder(null);
        tabbedPane.setBounds(0, 0, 665, 739);
        contentPane.add(tabbedPane);
        
        JPanel PlayersTab = new JPanel();
        PlayersTab.setBorder(null);
        tabbedPane.addTab("Players", null, PlayersTab, null);
        PlayersTab.setLayout(null);
        
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        // Player 1
        Player1 = new PlayerPanel(0);
        Player1.setBounds(0, 0, 625, 131);
        PlayersTab.add(Player1);
        
        // Player 2
        Player2 = new PlayerPanel(1);
        Player2.setBounds(0, 150, 625, 131);
        PlayersTab.add(Player2);
        
        // Player 3
        Player3 = new PlayerPanel(2);
        Player3.setBounds(0, 300, 625, 131);
        PlayersTab.add(Player3);
        
        // Player 4
        Player4 = new PlayerPanel(3);
        Player4.setBounds(0, 450, 625, 131);
        PlayersTab.add(Player4);
        
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        DicePanel DiceTab = new DicePanel();
        tabbedPane.addTab("Dice-Property", null, DiceTab, null);
     
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    
    static void refreshLands() {
        Player1.refresh();
        Player2.refresh();
        Player3.refresh();
        Player4.refresh();
    }
}
