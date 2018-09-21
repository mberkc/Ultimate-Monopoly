package GameSquares;

import gui.AdditionalWindows.StockExcWindow;
import gui.Board.RollingTheDice;
import Main.Main;
import Main.Player;

public class StockExchange extends GameSquare {
    private static final long serialVersionUID = 1L;
    
    public StockExchange(int id) {
        super(id, type.StockExchange);
    }
    
    public enum stockType {
        AcmeMotors(0, 100, "AcmeMotors Stock Share"),
        UnitedRailways(1, 110, "UnitedRailways Stock Share"),
        GeneralRadIO(2, 120, "GeneralRadIO Stock Share"),
        NationalUtilities(3, 130, "NationalUtilities Stock Share"),
        AlliedSteamships(4, 140, "AlliedSteamships Stock Share"),
        MotionPictures(5, 150, "MotionPictures Stock Share");
        
        private int    order;
        private int    price;
        private String value;
        
        stockType(int order, int price, String value) {
            this.order = order;
            this.price = price;
            this.value = value;
        }
        public static stockType getStock(int i) {
            for (stockType st : stockType.values())
                if (st.order == i)
                    return st;
            return null;
        }
        public int getOrder() {
            return order;
        }
        public int getPrice() {
            return price;
        }
        public String getValue() {
            return value;
        }
        
        @Override
        public String toString() {
            return value;
        }
        
    }
    
    @Override
    public void onArrive(Player pl) {
        RollingTheDice.logAdd(pl.getName() + " moved to " + toString());
        StockExcWindow ste = new StockExcWindow(pl);
        ste.setVisible(true);
        
        String div = "";
        for (Player player : Main.players) {
            int divident = (int) (((stockType.AcmeMotors.getPrice() / 10) * (Math.pow(2.5, player.getStockAmount(stockType.AcmeMotors)) - 1))) +
                (int) (((stockType.AlliedSteamships.getPrice() / 10) * (Math.pow(2.5, player.getStockAmount(stockType.AlliedSteamships)) - 1))) +
                (int) (((stockType.GeneralRadIO.getPrice() / 10) * (Math.pow(2.5, player.getStockAmount(stockType.GeneralRadIO)) - 1))) +
                (int) (((stockType.MotionPictures.getPrice() / 10) * (Math.pow(2.5, player.getStockAmount(stockType.MotionPictures)) - 1))) +
                (int) (((stockType.NationalUtilities.getPrice() / 10) * (Math.pow(2.5, player.getStockAmount(stockType.NationalUtilities)) - 1))) +
                (int) (((stockType.UnitedRailways.getPrice() / 10) * (Math.pow(2.5, player.getStockAmount(stockType.UnitedRailways)) - 1)));
            
            player.addMoney(divident);
            div += " " + divident + "    ";
        }
        RollingTheDice.logAdd("Dividents paid: " + div);
    }
    @Override
    public String toString() {
        return "Stock Exchange Square";
    }
    public static stockType getTheFirstAvailableStock(){
    	Player[] temp = Main.players;
    	for(int i = 0;i<5;i++){
    		if(temp[0].getStockAmount(stockType.getStock(i)) != 6 && temp[1].getStockAmount(stockType.getStock(i)) != 6
    				&& temp[2].getStockAmount(stockType.getStock(i)) != 6 &&
    				temp[3].getStockAmount(stockType.getStock(i)) != 6) {
    			return stockType.getStock(i);
    		}
    	}
    	System.out.println("sorry there is no available stock to buy for all players");
		return null;
    	
    }
    
}




















//
