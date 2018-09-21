package GameSquares.Cards.ChanceCards;

import gui.AdditionalWindows.MessageDisplayer;
import gui.Board.RollingTheDice;
import java.io.Serializable;
import GameSquares.Cards.ChanceCard;
import GameSquares.StockExchange.stockType;
import Main.Main;
import Main.Player;

public class EntertainmentRocks extends ChanceCard implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public EntertainmentRocks() {
        super(CardType.EntertainmentRocks, false);
    }
    
    @Override
    public void onDraw(Player pl) {
        new MessageDisplayer("You picked EntertainmentRocks Card.");
        RollingTheDice.logContinue("EntertainmentRocks Card.");
        String div = "";
        for (Player player : Main.players) {
            int divident = (int) (((stockType.GeneralRadIO.getPrice() / 10) * (Math.pow(2.5,
                player.getStockAmount(stockType.GeneralRadIO)) - 1)))
                +
                (int) (((stockType.MotionPictures.getPrice() / 10) * (Math.pow(2.5,
                    player.getStockAmount(stockType.MotionPictures)) - 1)));
            
            player.addMoney(divident);
            div += " " + divident + "    ";
        }
        RollingTheDice.logAdd("Dividents paid: " + div);
    }
    
    public String toString() {
        return "Entertainment Rocks";
    }
    
    @Override
    public String getName() {
        return "Entertainment Rocks";
    }
    
}
