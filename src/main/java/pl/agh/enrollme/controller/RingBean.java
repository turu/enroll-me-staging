package pl.agh.enrollme.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RingBean implements Serializable {

    private List<Player> players;

    private Player selectedPlayer;

    private int selectedId = 0;

    public RingBean() {
        players = new ArrayList<Player>();

        players.add(new Player("Messi", 100, "messi.jpg", "CF"));
        players.add(new Player("Bojan", 9, "bojan.jpg", "CF"));
        players.add(new Player("Iniesta", 8, "iniesta.jpg", "CM"));
        players.add(new Player("Villa", 7, "villa.jpg", "CF"));
        players.add(new Player("Xavi", 6, "xavi.jpg", "CM"));
        players.add(new Player("Puyol", 5, "puyol.jpg", "CB"));
        players.add(new Player("Piotr", 77, "turek.jpg", "PT"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public int getSelectedId() {
        if(selectedPlayer != null) {
            selectedId = players.indexOf(selectedPlayer);
        }
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public class Player implements Serializable {
        private String name;
        private int id;
        private String image;
        private String cf;

        public Player(String messi, int i, String s, String cf) {
            this.name = messi;
            this.id = i;
            this.image = s;
            this.cf = cf;
        }

        public String getName() {
            return name;
        }

        public void setName(String messi) {
            this.name = messi;
        }

        public int getId() {
            if(id < 100) {
                id = id + 1;
            }

            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCf() {
            return cf;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }
    }
}
