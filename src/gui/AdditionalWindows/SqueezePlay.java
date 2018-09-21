package gui.AdditionalWindows;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Main.Player;

public class SqueezePlay extends JPanel implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    private JLabel            picked, result, dice;
    private JButton           button;
    private Player            player;
    
    public SqueezePlay(Player pl) {
        
        setLayout(null);
        this.player = pl;
        
        picked = new JLabel("If you roll an even number you will get 600$");
        button = new JButton();
        dice = new JLabel();
        result = new JLabel();
        
        Image boardImage = new ImageIcon("dice.png").getImage();
        dice = new JLabel(new ImageIcon(boardImage));
        dice.setBounds(7, 10, ((int) dice.getPreferredSize().getWidth()), ((int) dice.getPreferredSize().getHeight()));
        add(dice);
        
        picked.setBounds(70, 74, ((int) picked.getPreferredSize().getWidth()), ((int) picked.getPreferredSize()
            .getHeight()));
        add(picked);
        
        result.setText("result is: ");
        result.setBounds(7, 115, ((int) result.getPreferredSize().getWidth()), ((int) result.getPreferredSize()
            .getHeight()));
        add(result);
        
        button.addActionListener(this);
        button.setText("Roll");
        button.setBounds(7, 75, ((int) button.getPreferredSize().getWidth()), ((int) button.getPreferredSize()
            .getHeight()));
        add(button);
    }
    
    public void actionPerformed(ActionEvent arg0) {
        // Create the Dice and roll
        button.setEnabled(false);
        GameSquares.SqueezePlay.isRolledEven(player);
        boolean a = GameSquares.SqueezePlay.rolledEven;
        int b = GameSquares.SqueezePlay.rolled;
        
        if (a) {
            result.setText("You rolled : " + b + "   it is even!!!");
            result.setBounds(7, 115, ((int) result.getPreferredSize().getWidth()), ((int) result.getPreferredSize()
                .getHeight()));
        } else {
            
            result.setText("You rolled : " + b + "   Better luck next time, it is odd");
            result.setBounds(7, 115, ((int) result.getPreferredSize().getWidth()), ((int) result.getPreferredSize()
                .getHeight()));
        }
    }
    
    
    
}
