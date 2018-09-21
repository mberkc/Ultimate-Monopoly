package GameSquares.Cards;

import java.io.Serializable;
import java.util.Random;
import GameSquares.Cards.CommunityChestCards.GeneralRepairs;
import GameSquares.Cards.CommunityChestCards.InheritStock;
import GameSquares.Cards.CommunityChestCards.OnlinePricing;
import GameSquares.Cards.CommunityChestCards.VehicleImpounded;

public class CommunityChestDeck implements Serializable, Deck {
    private static final long    serialVersionUID = 1L;
    private CommunityChestCard[] cards            = null;
    
    public CommunityChestDeck() {
        CommunityChestCard[] cards = { // new BargainBusiness(),
        // new HouseCondemned(),
        // new RecieveConsultancyFee(), new RenovationSuccess(),
        // new StreetRepair(),
                new OnlinePricing(), new VehicleImpounded(), new GeneralRepairs(), new InheritStock() };
        this.cards = cards;
    }
    
    public CommunityChestCard draw() {
        return cards[new Random().nextInt(cards.length)];
    }
    
    public CommunityChestCard[] getDeck() {
        return cards;
    }
}
