package gui.Board;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import GameSquares.Ownable;
import GameSquares.Cards.Card.CardType;
import Main.Player;
import Main.Properties;

public class PlayerInfoCard extends JPanel {
    private static final long serialVersionUID = 1L;
    private JLabel            lblName, lblMoney, lblCards, lblLands, lblLocation, lblStocks;
    private JSeparator        separator1, separator2;
    private JPanel            pnlPlayer;
    private JTextPane         txtLands;
    private int               id;
    
    PlayerInfoCard(int id) {
        this.id = id;
        setLayout(null);
        
        pnlPlayer = new JPanel();
        pnlPlayer.setBorder(new LineBorder(new Color(51, 204, 0), 2, true));
        pnlPlayer.setBounds(0, 0, 377, 227);
        add(pnlPlayer);
        pnlPlayer.setLayout(null);
        
        lblName = new JLabel("asdsa");
        lblName.setFont(new Font("Tahoma", Font.ITALIC, 17));
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(0, 0, 377, 24);
        pnlPlayer.add(lblName);
        
        lblMoney = new JLabel("Money:" + Properties.START_GOLD);
        lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMoney.setBounds(10, 28, 113, 36);
        pnlPlayer.add(lblMoney);
        
        lblLocation = new JLabel("Location:0");
        lblLocation.setBounds(160, 40, 100, 14);
        pnlPlayer.add(lblLocation);
        
        lblCards = new JLabel("<html>Jail:0<br>Online:0</html>");
        lblCards.setBounds(291, 26, 86, 42);
        pnlPlayer.add(lblCards);
        
        lblLands = new JLabel("Lands");
        lblLands.setBounds(10, 74, 46, 14);
        pnlPlayer.add(lblLands);
        
        lblStocks = new JLabel("Stocks: 0 - 0 - 0 - 0 - 0 - 0");
        lblStocks.setBounds(120, 74, 199, 14);
        pnlPlayer.add(lblStocks);
        
        separator1 = new JSeparator();
        separator1.setForeground(new Color(51, 204, 0));
        separator1.setBounds(2, 68, 373, 2);
        pnlPlayer.add(separator1);
        
        separator2 = new JSeparator();
        separator2.setForeground(new Color(51, 204, 0));
        separator2.setToolTipText("fgdfgdfg");
        separator2.setBounds(2, 24, 373, 2);
        pnlPlayer.add(separator2);
        
        txtLands = new JTextPane();
        txtLands.setEnabled(false);
        txtLands.setEditable(false);
        txtLands.setBounds(10, 89, 357, 127);
        pnlPlayer.add(txtLands);
        
    }
    public void refresh() {
        if (Main.Main.players.length < id + 1)
            return;
        
        Player crrtPlayer = Main.Main.players[id];
        
        // Refresh Name
        lblName.setText(crrtPlayer.getName() + "");
        
        // Refresh Money
        lblMoney.setText("Money:" + crrtPlayer.getMoney());
        
        // Refresh Location
        lblLocation.setText("Location:" + crrtPlayer.getLocation());
        
        // Refresh Stocks
        int[] stocksArry = crrtPlayer.getStocks();
        lblStocks.setText("Stocks:" + stocksArry[0] + " - " + stocksArry[1] + " - " + stocksArry[2] + " - "
            + stocksArry[3] + " - " + stocksArry[4] + " - " + stocksArry[5]);
        
        // Refresh Jail - Online
        ArrayList<CardType> arrys = crrtPlayer.getCardsInventory();
        int goj = 0, op = 0;
        for (CardType crdty : arrys) {
            switch (crdty) {
                case GetOutOfJail:
                    goj++;
                    break;
                case OnlinePricing:
                    op++;
                    break;
                default:
                    break;
            }
        }
        lblCards.setText("<html>Jail:" + goj + "<br>Online:" + op + "</html>");
        
        // Refresh Lands
        ArrayList<Ownable> arry = crrtPlayer.getOwnedSquares();
        Collections.sort(arry, (Ownable o1, Ownable o2) -> o1.getID() - o2.getID());
        String lands = "";
        for (int j = 0; j < arry.size(); j++)
            if (j > 0)
                lands += " - " + arry.get(j).getID();
            else
                lands += arry.get(j).getID();
        txtLands.setText(lands);
    }
    
    public void disable() {
        pnlPlayer.setBorder(new LineBorder(SystemColor.activeCaption, 2, true));
        separator1.setForeground(SystemColor.activeCaption);
        separator2.setForeground(SystemColor.activeCaption);
    }
    
    public void enable() {
        pnlPlayer.setBorder(new LineBorder(new Color(51, 204, 0), 2, true));
        separator1.setForeground(new Color(51, 204, 0));
        separator2.setForeground(new Color(51, 204, 0));
    }
}




















//
