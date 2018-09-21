package gui.Debug;

import gui.Board.PlayerInfo;
import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import GameSquares.Ownable;
import GameSquares.Cards.ChanceCard;
import GameSquares.Cards.CommunityChestCard;
import Main.Admin;
import Main.Main;
import Main.Player;

public class DicePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public static int         Die1, Die2, SpeedDie;
    public static boolean     chcbxTicked;
    public static boolean     chcbxExist       = false;
    public static Choice      chcOwnables;
    public static int         currentPlayerID;
    
    public DicePanel() {
        setLayout(null);
        
        JTextField txtDie1 = new JTextField();
        txtDie1.setBounds(12, 10, 116, 22);
        add(txtDie1);
        
        JTextField txtDie2 = new JTextField();
        txtDie2.setBounds(140, 10, 116, 22);
        add(txtDie2);
        
        JTextField txtSpeedDie = new JTextField();
        txtSpeedDie.setBounds(268, 10, 116, 22);
        add(txtSpeedDie);
        
        txtDie1.setText(Die1 + "");
        txtDie2.setText(Die2 + "");
        txtSpeedDie.setText(SpeedDie + "");
        
        JCheckBox chckbxSetDice = new JCheckBox("Fix Dice");
        chckbxSetDice.setBounds(392, 9, 99, 25);
        chckbxSetDice.addActionListener(al -> {
            if (chcbxTicked)
                chcbxTicked = false;
            else
                chcbxTicked = true;
            Die1 = Integer.parseInt(txtDie1.getText());
            Die2 = Integer.parseInt(txtDie2.getText());
            SpeedDie = Integer.parseInt(txtSpeedDie.getText());
        });
        if (chcbxTicked) chckbxSetDice.setSelected(true);
        add(chckbxSetDice);
        chcbxExist = true;
        
        chcOwnables = new Choice();
        chcOwnables.setBounds(237, 58, 261, 20);
        add(chcOwnables);
        
        JButton btnNewButton = new JButton("Upgrade");
        btnNewButton.addActionListener(al -> {
            Admin.upgradeOwnable(chcOwnables.getSelectedItem());
            Debug.refreshLands();
        });
        btnNewButton.setBounds(504, 56, 110, 28);
        add(btnNewButton);
        
        JButton btnDowngrade = new JButton("Downgrade");
        btnDowngrade.addActionListener(al -> {
            Admin.downgradeOwnable(chcOwnables.getSelectedItem());
            Debug.refreshLands();
        });
        btnDowngrade.setBounds(504, 84, 110, 28);
        add(btnDowngrade);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(12, 45, 602, 2);
        add(separator);
        
        Choice chcPlayers = new Choice();
        chcPlayers.addItemListener(il -> {
            fillCombo(chcPlayers.getSelectedIndex());
        });
        chcPlayers.setBounds(12, 57, 116, 22);
        for (Player pl : Main.players)
            chcPlayers.add(pl.getName());
        add(chcPlayers);
        
        JLabel lblOwnedPropertied = new JLabel("Owned Properties:");
        lblOwnedPropertied.setBounds(129, 58, 110, 21);
        add(lblOwnedPropertied);
        
        JLabel lblCard = new JLabel("Card:");
        lblCard.setBounds(165, 121, 60, 21);
        add(lblCard);
        
        Choice chcCards = new Choice();
        chcCards.setBounds(237, 121, 261, 20);
        for (ChanceCard ccc : Main.chanceDeck.getDeck())
            if (ccc.isKeepable())
                chcCards.add(ccc.getName());
        for (CommunityChestCard ccc : Main.communityDeck.getDeck())
            if (ccc.isKeepable())
                chcCards.add(ccc.getName());
        add(chcCards);
        
        JButton btnGiveCard = new JButton("Give");
        btnGiveCard.addActionListener(al -> {
            Player pl = Main.players[chcPlayers.getSelectedIndex()];
            for (ChanceCard cc : Main.chanceDeck.getDeck())
                if (cc.getName().equals(chcCards.getSelectedItem())) {
                    pl.addToCardInventory(cc.getType());
                }
            for (CommunityChestCard ccc : Main.communityDeck.getDeck())
                if (ccc.getName().equals(chcCards.getSelectedItem()))
                    pl.addToCardInventory(ccc.getType());
            PlayerInfo.refreshData();
        });
        btnGiveCard.setBounds(504, 118, 110, 28);
        add(btnGiveCard);
        
        JLabel lblNewLabel = new JLabel("Stock:");
        lblNewLabel.setBounds(165, 184, 60, 14);
        add(lblNewLabel);
        
        Choice chcStocks = new Choice();
        chcStocks.setBounds(237, 178, 261, 20);
        chcStocks.add("AcmeMotors Stock Share");
        chcStocks.add("UnitedRailways Stock Share");
        chcStocks.add("GeneralRadIO Stock Share");
        chcStocks.add("NationalUtilities Stock Share");
        chcStocks.add("AlliedSteamships Stock Share");
        chcStocks.add("MotionPictures Stock Share");
        add(chcStocks);
        
        JButton btnGiveStock = new JButton("Give");
        btnGiveStock.addActionListener(al -> {
            Main.players[chcPlayers.getSelectedIndex()].giveStock(chcStocks.getSelectedIndex());
            PlayerInfo.refreshData();
        });
        btnGiveStock.setBounds(504, 177, 110, 28);
        add(btnGiveStock);
        
        JButton btnRemoveStock = new JButton("Remove");
        btnRemoveStock.addActionListener(al -> {
            Main.players[chcPlayers.getSelectedIndex()].removeStock(chcStocks.getSelectedIndex());
            PlayerInfo.refreshData();
        });
        btnRemoveStock.setBounds(504, 205, 110, 28);
        add(btnRemoveStock);
    }
    private void fillCombo(int playerID) {
        chcOwnables.removeAll();
        if (Main.players != null && Main.players.length - 1 >= currentPlayerID) {
            for (Ownable ownable : Main.players[playerID].getOwnedSquares()) {
                chcOwnables.add(ownable.getName());
            }
        }
    }
}
