package gui.Debug;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import GameSquares.Cab;
import GameSquares.GameSquare;
import GameSquares.Land;
import GameSquares.Ownable;
import GameSquares.TransitStation;
import GameSquares.Cards.Chance;
import GameSquares.Cards.CommunityChest;
import Main.Admin;
import Main.Main;

public class PlayerPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private int               playerID         = 0;
    private JTextField        txtMoney1;
    private Choice            choiceOwnLand, choiceNeuLand;
    
    public PlayerPanel(int playerID) {
        setLayout(null);
        this.playerID = playerID;
        setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
        
        JLabel lblPlayerName1 = new JLabel("Player " + (playerID + 1));
        lblPlayerName1.setBounds(0, 0, 625, 22);
        lblPlayerName1.setHorizontalAlignment(SwingConstants.LEFT);
        lblPlayerName1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblPlayerName1);
        
        JLabel lblLocation1 = new JLabel("Location: ");
        lblLocation1.setBounds(167, 35, 76, 16);
        add(lblLocation1);
        
        JLabel lblMoney1 = new JLabel("Money: " + Admin.getPlayerMoney(this.playerID));
        lblMoney1.setBounds(10, 35, 118, 16);
        add(lblMoney1);
        
        JButton btnSetMon1 = new JButton("Set");
        btnSetMon1.addActionListener(al -> {
            Admin.setMoney(playerID, getInt(txtMoney1));
            lblMoney1.setText("Money: " + Admin.getPlayerMoney(this.playerID));
        });
        btnSetMon1.setBounds(55, 77, 81, 42);
        add(btnSetMon1);
        
        JButton btnMin1 = new JButton("-");
        btnMin1.addActionListener(al -> {
            Admin.decreaseMoneyBy(playerID, getInt(txtMoney1));
            lblMoney1.setText("Money: " + Admin.getPlayerMoney(this.playerID));
        });
        btnMin1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnMin1.setAlignmentY(Component.TOP_ALIGNMENT);
        btnMin1.setBounds(12, 98, 43, 21);
        add(btnMin1);
        
        JButton btnPls1 = new JButton("+");
        btnPls1.addActionListener(al -> {
            Admin.increaseMoneyBy(playerID, getInt(txtMoney1));
            lblMoney1.setText("Money: " + Admin.getPlayerMoney(this.playerID));
        });
        btnPls1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnPls1.setAlignmentY(Component.TOP_ALIGNMENT);
        btnPls1.setBounds(12, 77, 43, 21);
        add(btnPls1);
        
        txtMoney1 = new JTextField();
        txtMoney1.setColumns(10);
        txtMoney1.setBounds(12, 53, 125, 22);
        txtMoney1.setToolTipText("" + Admin.getPlayerMoney(this.playerID));
        add(txtMoney1);
        
        Choice choiceLoc1 = new Choice();
        fillAllSquares(choiceLoc1);
        choiceLoc1.select(Admin.getPlayerLocation(this.playerID));
        choiceLoc1.setBounds(167, 53, 140, 22);
        add(choiceLoc1);
        
        JButton btnSetLoc1 = new JButton("Set");
        btnSetLoc1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Admin.movePlayerToForced(playerID, choiceLoc1.getSelectedItem());
            }
        });
        btnSetLoc1.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSetLoc1.setBounds(167, 77, 70, 41);
        add(btnSetLoc1);
        
        JButton btnMove1 = new JButton("Move");
        btnMove1.addActionListener(al -> {
            Admin.movePlayerTo(playerID, choiceLoc1.getSelectedItem());
        });
        btnMove1.setAlignmentX(0.5f);
        btnMove1.setBounds(237, 77, 70, 41);
        add(btnMove1);
        
        JLabel lblOwnLands1 = new JLabel("Owned Lands");
        lblOwnLands1.setBounds(329, 35, 140, 16);
        add(lblOwnLands1);
        
        choiceOwnLand = new Choice();
        fillOwnLands(choiceOwnLand, playerID);
        choiceOwnLand.setBounds(329, 53, 140, 22);
        add(choiceOwnLand);
        
        choiceNeuLand = new Choice();
        fillNeuLands(choiceNeuLand, playerID);
        choiceNeuLand.setBounds(473, 53, 140, 22);
        add(choiceNeuLand);
        
        JLabel lblNeuLands1 = new JLabel("Neutral Lands");
        lblNeuLands1.setBounds(473, 35, 140, 16);
        add(lblNeuLands1);
        
        JButton btnRemoveLnd1 = new JButton("Remove");
        btnRemoveLnd1.addActionListener(al -> {
            Admin.removeOwnership(playerID, choiceOwnLand.getSelectedItem());
            Debug.refreshLands();
        });
        btnRemoveLnd1.setBounds(329, 77, 140, 41);
        add(btnRemoveLnd1);
        
        JButton btnClaimLnd1 = new JButton("Claim");
        btnClaimLnd1.addActionListener(al -> {
            Admin.giveOwnership(playerID, choiceNeuLand.getSelectedItem());
            Debug.refreshLands();
        });
        btnClaimLnd1.setBounds(473, 77, 140, 41);
        add(btnClaimLnd1);
        
        JSeparator separatorH1 = new JSeparator();
        separatorH1.setOrientation(SwingConstants.VERTICAL);
        separatorH1.setBounds(148, 22, 2, 109);
        add(separatorH1);
        
        JSeparator separatorH2 = new JSeparator();
        separatorH2.setOrientation(SwingConstants.VERTICAL);
        separatorH2.setBounds(315, 22, 2, 109);
        add(separatorH2);
        
        JSeparator separatorV1 = new JSeparator();
        separatorV1.setOrientation(SwingConstants.HORIZONTAL);
        separatorV1.setBounds(0, 22, 625, 109);
        add(separatorV1);
        
    }
    
    private int getInt(JTextField txtField) {
        if (txtField.getText() != null && txtField.getText().length() >= 1)
            return Integer.parseInt(txtField.getText());
        else
            return 0;
    }
    
    private void fillAllSquares(Choice choice) {
        ArrayList<String> sqNames = new ArrayList<String>();
        if (Main.gameSquares != null)
            for (GameSquare gsq : Main.gameSquares) {
                if (gsq instanceof Land || gsq instanceof TransitStation)
                    sqNames.add(((Ownable) gsq).getName());
                else if (gsq instanceof Chance || gsq instanceof CommunityChest || gsq instanceof Cab)
                    sqNames.add(gsq.getType() + " " + gsq.getID());
                else
                    sqNames.add(gsq.getType() + "");
            }
        else
            choice.add("...");
        Collections.sort(sqNames);
        for (String nextName : sqNames) {
            choice.add(nextName);
        }
    }
    private void fillNeuLands(Choice choice, int playerID) {
        ArrayList<String> gsqNames = new ArrayList<String>();
        if (Main.players != null && Main.players.length - 1 >= playerID) {
            for (GameSquare gsq : Main.gameSquares)
                if (gsq instanceof Ownable && ((Ownable) gsq).getOwner() == null)
                    gsqNames.add(((Ownable) gsq).getName());
        }
        Collections.sort(gsqNames);
        for (String nextName : gsqNames) {
            choice.add(nextName);
        }
    }
    private void fillOwnLands(Choice choice, int playerID) {
        ArrayList<String> gsqNames = new ArrayList<String>();
        if (Main.players != null && Main.players.length - 1 >= playerID) {
            for (Ownable ownable : Main.players[playerID].getOwnedSquares()) {
                gsqNames.add(((Ownable) ownable).getName());
            }
        }
        Collections.sort(gsqNames);
        for (String nextName : gsqNames) {
            choice.add(nextName);
        }
    }
    
    void refresh() {
        choiceOwnLand.removeAll();
        choiceNeuLand.removeAll();
        fillOwnLands(choiceOwnLand, playerID);
        fillNeuLands(choiceNeuLand, playerID);
    }
}
