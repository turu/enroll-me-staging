package pl.agh.enrollme.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RingBean implements Serializable {

    private List<Player> players;

    private Player selectedPlayer;

    public RingBean() {
        players = new ArrayList<Player>();

        players.add(new Player("Messi", 10, "messi.jpg", "CF"));
        players.add(new Player("Bojan", 9, "bojan.jpg", "CF"));
        players.add(new Player("Iniesta", 8, "iniesta.jpg", "CM"));
        players.add(new Player("Villa", 7, "villa.jpg", "CF"));
        players.add(new Player("Xavi", 6, "xavi.jpg", "CM"));
        players.add(new Player("Puyol", 5, "puyol.jpg", "CB"));
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

    public class Player {
        private String messi;
        private int i;
        private String s;
        private String cf;

        public Player(String messi, int i, String s, String cf) {
            this.messi = messi;
            this.i = i;
            this.s = s;
            this.cf = cf;
        }

        public String getMessi() {
            return messi;
        }

        public void setMessi(String messi) {
            this.messi = messi;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getCf() {
            return cf;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }
    }
}
