package Obsolete;

import gui.Board.Board;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.*;
import Main.Admin;
import Main.Main;
import Main.Player;

public class Debug extends JFrame implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private ArrayList<JTextField> moneyFields, locationFields, cardFields;
    private JButton               set;
    private ArrayList<JButton>    plusFields, minusFields;
    private ArrayList<JLabel>     identifiers, moneyLabels, locationLabels, cardLabels;
    
    
    private Player[]              players = Main.players;
    
    public Debug() {
        setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        moneyFields = new ArrayList<JTextField>();
        locationFields = new ArrayList<JTextField>();
        cardFields = new ArrayList<JTextField>();
        plusFields = new ArrayList<JButton>();
        minusFields = new ArrayList<JButton>();
        identifiers = new ArrayList<JLabel>();
        moneyLabels = new ArrayList<JLabel>();
        locationLabels = new ArrayList<JLabel>();
        cardLabels = new ArrayList<JLabel>();
        
        
        for (int i = 0; i < players.length; i++) {
            
            // plus buttons
            plusFields.add(new JButton("+"));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 5;
            c.gridy = 4 * i + 1;
            add(plusFields.get(i), c);
            plusFields.get(i).addActionListener(this);
            
            // minus buttons
            minusFields.add(new JButton("-"));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 6;
            c.gridy = 4 * i + 1;
            add(minusFields.get(i), c);
            minusFields.get(i).addActionListener(this);
            
            // moneyFields
            moneyFields.add(new JTextField(Integer.toString(players[i].getMoney()), 10));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 4 * i + 1;
            c.gridwidth = 3;
            add(moneyFields.get(i), c);
            
            // locationFields
            locationFields.add(new JTextField(Integer.toString(players[i].getLocation()), 10));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 4 * i + 2;
            add(locationFields.get(i), c);
            
            // cardFields
            cardFields.add(new JTextField(10));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 4 * i + 3;
            add(cardFields.get(i), c);
            
            // moneyLabels
            moneyLabels.add(new JLabel("Money: "));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 4 * i + 1;
            add(moneyLabels.get(i), c);
            
            // locationLabels
            locationLabels.add(new JLabel("Location: "));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 4 * i + 2;
            add(locationLabels.get(i), c);
            
            // cardLabels
            cardLabels.add(new JLabel("Give Ownership: "));
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = 4 * i + 3;
            add(cardLabels.get(i), c);
            
            // identifiers
            identifiers.add(new JLabel("   	-" + (i + 1) + "st player-  "));
            c.fill = GridBagConstraints.VERTICAL;
            c.gridwidth = 1;
            c.gridx = 0;
            c.gridy = 4 * i;
            add(identifiers.get(i), c);
        }
        
        // set
        
        set = new JButton("SET");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 16;
        c.gridwidth = 6;
        add(set, c);
        
        set.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == set) {
            setAction();
            this.dispose();
        } else {
            for (int i = 0; i < players.length; i++) {
                int money = Integer.parseInt(moneyFields.get(i).getText());
                
                if (e.getSource() == plusFields.get(i)) {
                    money += 5000;
                    moneyFields.get(i).setText("" + money);
                } else if (e.getSource() == minusFields.get(i)) {
                    if (money > 5000) money -= 5000;
                    moneyFields.get(i).setText("" + money);
                }
            }
        }
    }
    
    private void setAction() {  // /move player to larý deðiþtir
        // int x, y, location;
        
        for (int i = 0; i < players.length; i++) {
            // set player money amount
            players[i].setMoney(Integer.parseInt(moneyFields.get(i).getText()));
            
            // set player properties
            StringTokenizer st = new StringTokenizer(cardFields.get(i).getText(), " ,-");
            while (st.hasMoreTokens()) {
                int currentLand = Integer.parseInt((String) st.nextToken());
                Admin.giveOwnership(players[i], currentLand);
            }
        }
        
        
        // location = Integer.parseInt(locationFields.get(0).getText());
        // x = Board.squareHolder.getSquare(location).getX() - (players[0].getID() * 25);
        // y = Board.squareHolder.getSquare(location).getY();
        // Admin.movePlayerTo(players[0], location); // change me to movePlayerToforced
        // Board.zero.setBounds(x, y, 50, 40);
        //
        // location = Integer.parseInt(locationFields.get(1).getText());
        // x = Board.squareHolder.getSquare(location).getX() - (players[1].getID() * 25);
        // y = Board.squareHolder.getSquare(location).getY();
        // Admin.movePlayerTo(players[1], location); // change me to movePlayerToforced
        // Board.one.setBounds(x, y, 50, 40);
        //
        // location = Integer.parseInt(locationFields.get(2).getText());
        // x = Board.squareHolder.getSquare(location).getX() - (players[2].getID() * 25);
        // y = Board.squareHolder.getSquare(location).getY();
        // Admin.movePlayerTo(players[2], location); // change me to movePlayerToforced
        // Board.two.setBounds(x, y, 50, 40);
        //
        // location = Integer.parseInt(locationFields.get(3).getText());
        // x = Board.squareHolder.getSquare(location).getX() - (players[3].getID() * 25);
        // y = Board.squareHolder.getSquare(location).getY();
        // Admin.movePlayerTo(players[3], location); // change me to movePlayerToforced
        // Board.three.setBounds(x, y, 50, 40);
        
        // Board.informationTable.refreshData();
        Board.informationTable.revalidate();;
    }
}
