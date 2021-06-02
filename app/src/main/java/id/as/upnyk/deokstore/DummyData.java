package id.as.upnyk.deokstore;

import java.util.ArrayList;

public class DummyData {
    private static int images[] = {
            R.drawable.apex,
            R.drawable.brawhalla,
            R.drawable.csgo,
            R.drawable.destiny2,
            R.drawable.dota,
            R.drawable.forza,
            R.drawable.gta,
            R.drawable.itt,
            R.drawable.paladins,
            R.drawable.war_thunder,
    };

    private static String[] title = {
            "Apex Legends",
            "Brawlhalla",
            "Counter-Strike: Global Offensive",
            "Destiny 2",
            "Dota 2",
            "Forza Horizon 4",
            "Grand Theft Auto V",
            "It Takes Two",
            "Paladins",
            "War Thunder"
    };

    private static String[] genre = {
            "Action, Adventure",
            "Action, Indie",
            "Action",
            "Action, Adventure",
            "Action, Strategy",
            "Racing",
            "Action, Adventure",
            "Action, Adventure",
            "Action",
            "Action, Simulation, Multiplayer"
    };


    public static ArrayList<GameModel> generateGames(){
        ArrayList<GameModel> games = new ArrayList<>();
        for (int i = 0; i < title.length; i++){
            games.add(new GameModel(
                    images[i],
                    title[i],
                    genre[i]
            ));
        }
        return games;
    }
}
