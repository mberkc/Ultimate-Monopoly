package GameSquares.Cards;

import java.io.Serializable;
import java.util.Random;
import GameSquares.Cards.ChanceCards.BusTicket;
import GameSquares.Cards.ChanceCards.ChangingLanes;
import GameSquares.Cards.ChanceCards.EntertainmentRocks;
import GameSquares.Cards.ChanceCards.GetOutOfJail;
import GameSquares.Cards.ChanceCards.GoToJail;
import Main.Player;

public class ChanceDeck implements Serializable, Deck {
    private static final long serialVersionUID = 1L;
    private ChanceCard[]      cards            = null;
    
    public ChanceDeck(Player[] players) {
        ChanceCard[] cards = { // new AdvanceToSqueezePlay(), new
                               // AdvanceToStart(), new
                               // AdvanceToStCharlesPlace(),
                // new AdvanceToTheNearestRailroad(), new
                // AdvanceToTheNearestUtility(),
                // new ElectedAsChairPerson(players),
                // new GeneralRepairs(),
                new GetOutOfJail(),
                new GoToJail(),
                // new SchoolFees(), new ThreeSpacesBack(),
                // new Hurricane(), new MargiGras(), new PropertyTaxes(),
                new BusTicket(),
                new ChangingLanes(), new EntertainmentRocks() };
        this.cards = cards;
    }
    
    public ChanceCard draw() {
        return cards[new Random().nextInt(cards.length)];
    }
    
    public ChanceCard[] getDeck() {
        return cards;
    }
}
