package GameSquares;

import Main.Player;

public interface Ownable {
    public void sell();
    public int getPrice();
    public void setOwner(Player pl);
    public String getName();
    public Player getOwner();
    public int getID();
    public void upgrade();
    public void downgrade();
    public String getUpgradeState();
    public boolean isOwned();
}
