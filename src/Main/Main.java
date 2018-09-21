package Main;

import gui.AdditionalWindows.MessageDisplayer;
import gui.AdditionalWindows.InputReaders.GetTextInput;
import gui.Board.Board;
import gui.Board.PlayerInfo;
import gui.Board.RollingTheDice;
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import GameSquares.Auction;
import GameSquares.BirthdayGiftSquare;
import GameSquares.BonusSquare;
import GameSquares.BusTicketSquare;
import GameSquares.Cab;
import GameSquares.FreePark;
import GameSquares.GameSquare;
import GameSquares.GameSquare.type;
import GameSquares.GoToJail;
import GameSquares.HollandTunnel;
import GameSquares.Jail;
import GameSquares.Land;
import GameSquares.Land.color;
import GameSquares.PayDay;
import GameSquares.ReverseDirection;
import GameSquares.RollOnce;
import GameSquares.SqueezePlay;
import GameSquares.StartSquare;
import GameSquares.StockExchange;
import GameSquares.Subway;
import GameSquares.TransitStation;
import GameSquares.Utility;
import GameSquares.Cards.Chance;
import GameSquares.Cards.ChanceDeck;
import GameSquares.Cards.CommunityChest;
import GameSquares.Cards.CommunityChestDeck;
import GameSquares.Taxes.IncomeTax;
import GameSquares.Taxes.LuxuryTax;
import GameSquares.Taxes.TaxRefund;

public class Main {
    
    public static ChanceDeck         chanceDeck     = null;
    public static CommunityChestDeck communityDeck  = null;
    public static GameSquare[]       gameSquares    = null;
    public static Player[]           players        = null;
    public static Player             CurrentPlayer  = null;
    volatile static Boolean          roundEnded     = false;
    private volatile static Boolean  stopTurnLoop   = false;
    private static GetTextInput      temp;
    static Board                     board;
    public static int                pool           = 0, musicID = 1;
    private static boolean           loadPrevious   = false;
    public static boolean            loadProtection = false;
    private static AudioRunner       musicPlayer;
    
    public static void main(String[] args) {
        changeUITheme();
        initializeDecks();
        initializeGameSquares();
        initializePlayers();
        initializePlayerNames();
        initializeBoard();
        
        startMusic();
        
        if (loadPrevious)
            continueFromSave();
        else
            runGame();
    }
    private static void changeUITheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
            | UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
    }
    
    private static void initializePlayers() {
        int num0fPlayers = 0;
        String numberOfPlayers = "";
        try {
            while (num0fPlayers == 0 || num0fPlayers < 0 || numberOfPlayers == null) {
                temp = new GetTextInput("How many players?");
                if (temp.getString().equals("l") || temp.getString().equals("load")) {
                    loadPrevious = true;
                    break;
                }
                num0fPlayers = temp.getInt();
                numberOfPlayers = temp.getString();
            }
            players = new Player[num0fPlayers];
        } catch (Exception e) {}
        System.out.println("Player initialization is complete...");
    }
    
    public static void initializeDecks() {
        chanceDeck = new ChanceDeck(players);
        communityDeck = new CommunityChestDeck();
        System.out.println("Deck initialization is complete...");
    }
    
    public static void initializeGameSquares() {
        gameSquares = new GameSquare[120];
        if (loadPrevious) {
            System.out.println("Game Square initialization is complete...");
            return;
        }
        
        gameSquares[0] = new StartSquare(0);
        gameSquares[1] = new Land(1, "Mediterranean Avenue", color.puple, 60, 2).addDeedInfo(10, 30, 90, 160, 250, 750,
            35, 50);
        gameSquares[2] = new CommunityChest(2, communityDeck);
        gameSquares[3] = new Land(3, "Baltic Avenue", color.puple, 60, 4).addDeedInfo(20, 60, 180, 320, 450, 950, 30,
            50);
        gameSquares[4] = new IncomeTax(4);
        gameSquares[5] = new TransitStation(5, 47, "Reading");
        gameSquares[6] = new Land(6, "Oriental Avenue", color.lightBlue, 100, 6).addDeedInfo(30, 90, 270, 400, 550,
            1050, 50, 50);
        gameSquares[7] = new Chance(7, chanceDeck, players);
        gameSquares[8] = new Land(8, "Vermont Avenue", color.lightBlue, 100, 6)
            .addDeedInfo(30, 90, 270, 400, 550, 1050, 50, 50);
        gameSquares[9] = new Land(9, "Connecticut Avenue", color.lightBlue, 100, 8)
            .addDeedInfo(30, 90, 270, 400, 550, 1050, 50, 50);
        gameSquares[10] = new Jail(10);
        gameSquares[11] = new Land(11, "St. Charles Place", color.pink, 140, 10).addDeedInfo(50, 150, 450, 625, 750,
            1250, 70, 100);
        gameSquares[12] = new Utility(12, "Electric Company", type.ElectricCompany);
        gameSquares[13] = new Land(13, "States Avenue", color.pink, 140, 10).addDeedInfo(50, 150, 450, 625, 750, 1250,
            70, 100);
        gameSquares[14] = new Land(14, "Virginia Avenue", color.pink, 160, 12).addDeedInfo(60, 180, 500, 700, 900,
            1400, 80, 100);
        gameSquares[15] = new TransitStation(15, 105, "Pennsylvania");
        gameSquares[16] = new Land(16, "St. James Place", color.orange, 180, 14).addDeedInfo(70, 200, 550, 750, 950,
            1450, 90, 100);
        gameSquares[17] = new CommunityChest(17, communityDeck);
        gameSquares[18] = new Land(18, "Tennessee Avenue", color.orange, 180, 14).addDeedInfo(70, 200, 550, 750, 950,
            1450, 90, 100);
        gameSquares[19] = new Land(19, "New York Avenue", color.orange, 200, 16).addDeedInfo(80, 220, 600, 800, 1000,
            1500, 100, 100);
        gameSquares[20] = new FreePark(20);
        gameSquares[21] = new Land(21, "Kentucky Avenue", color.red, 220, 18).addDeedInfo(90, 250, 700, 875, 1050,
            2050, 110, 150);
        gameSquares[22] = new Chance(22, chanceDeck, players);
        gameSquares[23] = new Land(23, "Indiana Avenue", color.red, 220, 18).addDeedInfo(90, 250, 700, 875, 1050, 2050,
            110, 150);
        gameSquares[24] = new Land(24, "Illinois Avenue", color.red, 240, 20).addDeedInfo(100, 300, 750, 925, 1100,
            2100, 120, 150);
        gameSquares[25] = new TransitStation(25, 75, "Short Line");
        gameSquares[26] = new Land(26, "Atlantic Avenue", color.yellow, 260, 22).addDeedInfo(110, 330, 800, 975, 1050,
            2150, 130, 150);
        gameSquares[27] = new Land(27, "Ventnor Avenue", color.yellow, 260, 22).addDeedInfo(110, 330, 800, 975, 1050,
            2150, 130, 150);
        gameSquares[28] = new Utility(28, "Water Works", type.WaterWorks);
        gameSquares[29] = new Land(29, "Marvin Gardens", color.yellow, 280, 22).addDeedInfo(120, 360, 850, 1025, 1200,
            2200, 140, 150);
        gameSquares[30] = new RollOnce(30);
        gameSquares[31] = new Land(31, "Pacific Avenue", color.green, 300, 26).addDeedInfo(130, 390, 900, 1100, 1275,
            2275, 150, 200);
        gameSquares[32] = new Land(32, "North Carolina Avenue", color.green, 300, 26).addDeedInfo(130, 390, 900, 1100,
            1275, 2275, 150, 200);
        gameSquares[33] = new CommunityChest(33, communityDeck);
        gameSquares[34] = new Land(34, "Pennsylvania Avenue", color.green, 320, 28).addDeedInfo(150, 450, 1000, 1200,
            1400, 2400, 160, 200);
        gameSquares[35] = new TransitStation(35, 117, "B&O");
        gameSquares[36] = new Chance(36, chanceDeck, players);
        gameSquares[37] = new Land(37, "Park Place", color.blue, 350, 35).addDeedInfo(175, 500, 1100, 1300, 1500, 2500,
            175, 200);
        gameSquares[38] = new LuxuryTax(38);
        gameSquares[39] = new Land(39, "Boardwalk", color.blue, 400, 50).addDeedInfo(200, 600, 1400, 1700, 2000, 3000,
            200, 200);
        gameSquares[40] = new Subway(40);
        gameSquares[41] = new Land(41, "Lake Street", color.lightPink, 30, 1).addDeedInfo(5, 15, 45, 80, 125, 625, 15,
            50);
        gameSquares[42] = new CommunityChest(42, communityDeck);
        gameSquares[43] = new Land(43, "Nicollet Avenue", color.lightPink, 30, 1).addDeedInfo(5, 15, 45, 80, 125, 625,
            15, 50);
        gameSquares[44] = new Land(44, "Hennepin Avenue", color.lightPink, 60, 3).addDeedInfo(15, 45, 120, 240, 350,
            850, 30, 50);
        gameSquares[45] = new BusTicketSquare(45);
        gameSquares[46] = new Cab(46, "Checker");
        gameSquares[47] = new TransitStation(47, 5, "Reading");
        gameSquares[48] = new Land(48, "Esplanade Avenue", color.lightGreen, 90, 5).addDeedInfo(25, 80, 225, 360, 600,
            1000, 50, 50);
        gameSquares[49] = new Land(49, "Canal Street", color.lightGreen, 90, 5).addDeedInfo(25, 80, 225, 360, 600,
            1000, 50, 50);
        gameSquares[50] = new Chance(50, chanceDeck, players);
        gameSquares[51] = new Utility(51, "Cable Company", type.CableCompany);
        gameSquares[52] = new Land(52, "Magazine Street", color.lightGreen, 120, 8).addDeedInfo(40, 100, 300, 450, 600,
            1100, 60, 50);
        gameSquares[53] = new Land(53, "Bourbon Street", color.lightGreen, 120, 8).addDeedInfo(40, 100, 300, 450, 600,
            1100, 60, 50);
        gameSquares[54] = new HollandTunnel(54, 114);
        gameSquares[55] = new Auction(55);
        gameSquares[56] = new Land(56, "Katy Freeway", color.lightYellow, 150, 11).addDeedInfo(55, 160, 475, 650, 800,
            1300, 70, 100);
        gameSquares[57] = new Land(57, "Westheimer Road", color.lightYellow, 150, 11).addDeedInfo(55, 160, 475, 650,
            800, 1300, 70, 100);
        gameSquares[58] = new Utility(58, "Internet Service Provider", type.InternetServiceProvider);
        gameSquares[59] = new Land(59, "Kirby Drive", color.lightYellow, 180, 14)
            .addDeedInfo(70, 200, 550, 750, 950, 1450, 80, 100);
        gameSquares[60] = new Land(60, "Cullen Bouleward", color.lightYellow, 180, 14)
            .addDeedInfo(70, 200, 550, 750, 950, 1450, 80, 100);
        gameSquares[61] = new Chance(61, chanceDeck, players);
        gameSquares[62] = new Cab(62, "Black and White");
        gameSquares[63] = new Land(63, "Deklab Avenue", color.oceanGreen, 210, 17).addDeedInfo(85, 240, 670, 840, 1025,
            1525, 90, 100);
        gameSquares[64] = new CommunityChest(64, communityDeck);
        gameSquares[65] = new Land(65, "Andrew Young Itnl Boulevard", color.oceanGreen, 210, 17).addDeedInfo(85, 240,
            670, 840, 1025, 1525, 90, 100);
        gameSquares[66] = new Land(66, "Decatur Street", color.oceanGreen, 240, 20).addDeedInfo(100, 300, 750, 925,
            1100, 1600, 100, 100);
        gameSquares[67] = new Land(67, "Peachtree Street", color.oceanGreen, 240, 20).addDeedInfo(100, 300, 750, 925,
            1100, 1600, 100, 100);
        gameSquares[68] = new PayDay(68);
        gameSquares[69] = new Land(69, "Randolph Street", color.magenta, 270, 23).addDeedInfo(115, 345, 825, 1010,
            1180, 2180, 110, 150);
        gameSquares[70] = new Chance(70, chanceDeck, players);
        gameSquares[71] = new Land(71, "Lake Shore Drive", color.magenta, 270, 23).addDeedInfo(115, 345, 825, 1010,
            1180, 2180, 110, 150);
        gameSquares[72] = new Land(72, "Wacker Drive", color.magenta, 300, 26).addDeedInfo(130, 390, 900, 1100, 1275,
            2275, 120, 150);
        gameSquares[73] = new Land(73, "Michigan Avenue", color.magenta, 300, 26).addDeedInfo(130, 390, 900, 1100,
            1275, 2275, 120, 150);
        gameSquares[74] = new Cab(74, "Yellow");
        gameSquares[75] = new TransitStation(75, 25, "Short Line");
        gameSquares[76] = new CommunityChest(76, communityDeck);
        gameSquares[77] = new Land(77, "South Temple", color.gold, 330, 32).addDeedInfo(160, 470, 1050, 2250, 1500,
            2500, 130, 200);
        gameSquares[78] = new Land(78, "West Temple", color.gold, 330, 32).addDeedInfo(160, 470, 1500, 1250, 1500,
            2500, 130, 200);
        gameSquares[79] = new Utility(79, "Trash Collector", type.TrashCollector);
        gameSquares[80] = new Land(80, "North Temple", color.gold, 360, 38).addDeedInfo(170, 520, 1125, 1275, 1425,
            1650, 140, 200);
        gameSquares[81] = new Land(81, "Temple Square", color.gold, 360, 38).addDeedInfo(170, 520, 1125, 1275, 1425,
            1650, 140, 200);
        gameSquares[82] = new GoToJail(82);
        gameSquares[83] = new Land(83, "South Street", color.lightRed, 390, 45).addDeedInfo(210, 575, 1300, 1600, 1800,
            3300, 150, 250);
        gameSquares[84] = new Land(84, "Broad Street", color.lightRed, 390, 45).addDeedInfo(210, 575, 1300, 1600, 1800,
            3300, 150, 250);
        gameSquares[85] = new Land(85, "Walnut Street", color.lightRed, 420, 55).addDeedInfo(225, 630, 1450, 1750,
            2050, 3550, 160, 250);
        gameSquares[86] = new CommunityChest(86, communityDeck);
        gameSquares[87] = new Land(87, "Market Street", color.lightRed, 420, 55).addDeedInfo(225, 630, 1450, 1750,
            2050, 3550, 160, 250);
        gameSquares[88] = new BusTicketSquare(88);
        gameSquares[89] = new Utility(89, "Sewage System", type.SewegeSystem);
        gameSquares[90] = new Cab(90, "Ute");
        gameSquares[91] = new BirthdayGiftSquare(91);
        gameSquares[92] = new Land(92, "Mulholland Drive", color.darkRed, 450, 70).addDeedInfo(350, 750, 1600, 1850,
            2100, 3600, 175, 300);
        gameSquares[93] = new Land(93, "Ventura Boulevard", color.darkRed, 480, 80).addDeedInfo(400, 825, 1800, 2175,
            2550, 4050, 200, 300);
        gameSquares[94] = new Chance(94, chanceDeck, players);
        gameSquares[95] = new Land(95, "Rodeo Drive", color.darkRed, 510, 90).addDeedInfo(450, 900, 2000, 2500, 3000,
            4500, 250, 300);
        gameSquares[96] = new SqueezePlay(96, players);
        gameSquares[97] = new Land(97, "The Embarcadero", color.white, 210, 17).addDeedInfo(85, 240, 475, 670, 1025,
            1525, 105, 100);
        gameSquares[98] = new Land(98, "Fisherman's Wharf", color.white, 250, 21).addDeedInfo(105, 320, 780, 950, 1125,
            1625, 125, 100);
        gameSquares[99] = new Utility(99, "Telephone Company", type.TelephoneCompany);
        gameSquares[100] = new CommunityChest(100, communityDeck);
        gameSquares[101] = new Land(101, "Beacon Street", color.black, 330, 30).addDeedInfo(160, 470, 1050, 1250, 1500,
            2500, 165, 200);
        gameSquares[102] = new BonusSquare(102);
        gameSquares[103] = new Land(103, "Boylston Street", color.black, 330, 30).addDeedInfo(160, 470, 1050, 1250,
            1500, 2500, 165, 200);
        gameSquares[104] = new Land(104, "NewBurly Street", color.black, 380, 40).addDeedInfo(185, 550, 1200, 1500,
            1700, 2700, 190, 200);
        gameSquares[105] = new TransitStation(105, 15, "Pennsylvania");
        gameSquares[106] = new Land(106, "Fift Avenue", color.grey, 430, 60).addDeedInfo(220, 650, 1500, 1800, 2100,
            3600, 215, 300);
        gameSquares[107] = new Land(107, "Madison Avenue", color.grey, 430, 60).addDeedInfo(220, 650, 1500, 1800, 2100,
            3600, 215, 300);
        gameSquares[108] = new StockExchange(108);
        gameSquares[109] = new Land(109, "Wall Street", color.grey, 500, 80).addDeedInfo(300, 800, 1800, 2200, 2700,
            4200, 250, 300);
        gameSquares[110] = new TaxRefund(110);
        gameSquares[111] = new Utility(111, "Gas Company", type.GasCompany);
        gameSquares[112] = new Chance(112, chanceDeck, players);
        gameSquares[113] = new Land(113, "Florida Avenue", color.brown, 130, 9).addDeedInfo(45, 120, 350, 500, 700,
            1200, 65, 50);
        gameSquares[114] = new HollandTunnel(114, 54);
        gameSquares[115] = new Land(115, "Miami Avenue", color.brown, 130, 9).addDeedInfo(45, 120, 320, 500, 700, 1200,
            65, 50);
        gameSquares[116] = new Land(116, "Biscayne Avenue", color.brown, 150, 11).addDeedInfo(55, 160, 475, 650, 800,
            1300, 75, 50);
        gameSquares[117] = new TransitStation(117, 35, "B&O");
        gameSquares[118] = new ReverseDirection(118);
        gameSquares[119] = new Land(119, "Lombard Street", color.white, 210, 17).addDeedInfo(85, 240, 475, 670, 1025,
            1525, 105, 100);
        System.out.println("Game Square initialization is complete...");
    }
    
    private static void initializePlayerNames() {
        for (int i = 0; i < players.length; i++) {
            String name = null;
            while (name == null || name.length() < 1)
                name = new GetTextInput("Name of player " + (i + 1) + " : ").getString();
            players[i] = new Player(i, name, gameSquares);
        }
        
        System.out.println("Player Name initialization is complete...");
    }
    
    private static void initializeBoard() {
        board = new Board(players, gameSquares);
        if (loadPrevious)
            SaveLoad.load();
        
        PlayerInfo.refreshData();
    }
    
    public static void continueFromSave() {
        RollingTheDice.logAdd("Continuing from save! It's " + CurrentPlayer.getName() + "'s turn.");
        board.round.loadCurrentPlayer(CurrentPlayer);
        int startID = CurrentPlayer.getID();
        for (int i = CurrentPlayer.getID(); i < Main.players.length; i++) {
            roundEnded = false;
            CurrentPlayer = Main.players[i];
            if (Main.players[i].getLocation() == Properties.HEAVEN_ID)
                continue;
            
            if (i != startID)
                board.setCurrentPlayer(Main.players[i]);
            
            while (!roundEnded)
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {};
            
            if (loadProtection)
                board.reduceLoadProtection();
        }
        stopTurnLoop = false;
        runGame();
    }
    
    
    private static void runGame() {
        Player lastPlayer = new Player(gameSquares);
        game:
        for (;;) {
            run:
            {
                RollingTheDice.logAdd("Turn: " + RollingTheDice.getTurn());
                for (Player currentPlayer : players) {
                    turn:
                    {
                        roundEnded = false;
                        CurrentPlayer = currentPlayer;
                        if (currentPlayer.getLocation() == Properties.HEAVEN_ID)
                            break turn;
                        
                        if (lastPlayer == currentPlayer) {
                            break game;
                        }
                        
                        if (stopTurnLoop)
                            break run;
                        
                        board.setCurrentPlayer(currentPlayer);
                        
                        while (!roundEnded) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {}
                            if (stopTurnLoop) {
                                continueFromSave();
                                break run;
                            };
                        }
                        lastPlayer = currentPlayer;
                        
                        if (loadProtection)
                            board.reduceLoadProtection();
                    }
                }
            }
        }
        new MessageDisplayer("Congratulations you have finished the game !");
    }
    public static void endRound() {
        roundEnded = true;
    }
    public static void stopTurnLoop() {
        stopTurnLoop = true;
    }
    
    
    
    public static void startMusic() {
        musicPlayer = new AudioRunner(musicID);
        musicPlayer.start();
    }
    public static void stopMusic() {
        musicPlayer.stopp();
    }
    public static void changeMusic(int i) {
        musicPlayer.stopp();
        musicID = i;
        musicPlayer = new AudioRunner(musicID);
        musicPlayer.start();
    }
}


class AudioRunner extends Thread {
    URL  music1 = getClass().getResource("/Resources/Music/hitthatjive.wav");
    URL  music2 = getClass().getResource("/Resources/Music/darude.wav");
    URL  playingMusic;
    Clip c;
    
    public AudioRunner(int i) {
        switch (i) {
            case 1:
                playingMusic = music1;
                RollingTheDice.logAdd("Music: hit that jive");
                break;
            case 2:
                playingMusic = music2;
                RollingTheDice.logAdd("Music: darude");
                break;
        }
    }
    
    public void stopp() {
        this.interrupt();
        c.close();
    }
    
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                c = AudioSystem.getClip();
                c.open(AudioSystem.getAudioInputStream(new File(playingMusic.toURI())));
                c.start();
                Thread.sleep(c.getMicrosecondLength() / (1000));
            }
        } catch (Exception e) {}
    }
}
