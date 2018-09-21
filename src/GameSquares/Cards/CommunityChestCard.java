package GameSquares.Cards;

import java.io.Serializable;

public abstract class CommunityChestCard implements Card, Serializable {
    private static final long serialVersionUID = 1L;
    
    private CardType          type;
    private boolean           keepable;
    
    public CommunityChestCard(CardType type, boolean isKeepable) {
        this.type = type;
        this.keepable = isKeepable;
    }
    
    public CardType getType() {
        return type;
    }
    
    public boolean isKeepable() {
        return keepable;
    }
}
