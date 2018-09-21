package gui.Board;

import javax.swing.JLabel;
import javax.swing.JPanel;
import Main.Main;
import Main.Player;

public class PlayerInfo extends JPanel {
    
    private static final long serialVersionUID = 1L;
    static String[][]         data;
    static PlayerInfoCard     playerCard1, playerCard2, playerCard3, playerCard4;
    
    public PlayerInfo(int size) {
        setLayout(null);
        
        playerCard1 = new PlayerInfoCard(0);
        playerCard1.setBounds(0, 0, 377, 227);
        add(playerCard1);
        
        playerCard2 = new PlayerInfoCard(1);
        playerCard2.setBounds(387, 0, 377, 227);
        add(playerCard2);
        
        playerCard3 = new PlayerInfoCard(2);
        playerCard3.setBounds(0, 236, 377, 227);
        add(playerCard3);
        
        playerCard4 = new PlayerInfoCard(3);
        playerCard4.setBounds(387, 236, 377, 227);
        add(playerCard4);
    }
    
    public static void refreshData() {
        playerCard1.refresh();
        playerCard2.refresh();
        playerCard3.refresh();
        playerCard4.refresh();
        
        refreshPlayerLocations();
        Board.refreshPoolMoney();
    }
    
    public static void setActivePlayerCard(int id) {
        playerCard1.disable();
        playerCard2.disable();
        playerCard3.disable();
        playerCard4.disable();
        switch (id)
        {
            case 0:
                playerCard1.enable();
                break;
            case 1:
                playerCard2.enable();
                break;
            case 2:
                playerCard3.enable();
                break;
            case 3:
                playerCard4.enable();
                break;
        }
    }
    private static void refreshPlayerLocations() {
        for (int i = 0; i < Main.players.length; i++) {
            Player current = Main.players[i];
            JLabel Player = null;
            switch (current.getID()) {
                case 0:
                    Player = Board.zero;
                    break;
                case 1:
                    Player = Board.one;
                    break;
                case 2:
                    Player = Board.two;
                    break;
                case 3:
                    Player = Board.three;
                    break;
            }
            
            int id = current.getLocation();
            int playerID = current.getID();
            int x, y;
            switch (playerID) {
                case 0:
                    x = Board.squareHolder.getSquare(id).getX() - 28;
                    y = Board.squareHolder.getSquare(id).getY() - 40;
                    break;
                case 1:
                    x = Board.squareHolder.getSquare(id).getX() - 55;
                    y = Board.squareHolder.getSquare(id).getY() - 40;
                    break;
                case 2:
                    x = Board.squareHolder.getSquare(id).getX() - 55;
                    y = Board.squareHolder.getSquare(id).getY() - 13;
                    break;
                case 3:
                    x = Board.squareHolder.getSquare(id).getX() - 28;
                    y = Board.squareHolder.getSquare(id).getY() - 13;
                    break;
                default:
                    x = Board.squareHolder.getSquare(id).getX() - (current.getID() * 30);
                    y = Board.squareHolder.getSquare(id).getY();
                    break;
            }
            
            final JLabel Playerr = Player;
            Runnable moveAnimation = () -> {
                int startX = Playerr.getBounds().x;
                int startY = Playerr.getBounds().y;
                int endX = x;
                int endY = y;
                
                if (startX > endX && startY > endY)
                    while (startX > endX || startY > endY) {
                        Playerr.setBounds(startX, startY, 50, 40);
                        if (startX > endX)
                            startX--;
                        if (startY > endY)
                            startY--;
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {}
                    }
                if (startX > endX && startY < endY)
                    while (startX > endX || startY < endY) {
                        Playerr.setBounds(startX, startY, 50, 40);
                        if (startX > endX)
                            startX--;
                        if (startY < endY)
                            startY++;
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {}
                    }
                if (startX < endX && startY > endY)
                    while (startX < endX || startY > endY) {
                        Playerr.setBounds(startX, startY, 50, 40);
                        if (startX < endX)
                            startX++;
                        if (startY > endY)
                            startY--;
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {}
                    }
                if (startX < endX && startY < endY)
                    while (startX < endX || startY < endY) {
                        Playerr.setBounds(startX, startY, 50, 40);
                        if (startX < endX)
                            startX++;
                        if (startY < endY)
                            startY++;
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {}
                    }
            };
            new Thread(moveAnimation).start();            
        }
    }
}
