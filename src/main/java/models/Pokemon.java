package models;

import java.util.Random;

public class Pokemon {

    private final String name;
    private final String battleShout;
    private final String winShout;
    private final String loseShout;

    private float life;
    private int level;
    private int power;


    public Pokemon(String name, String winShout, String loseShout, String battleShout, float life, int level, int power) {
        this.name = name;
        this.winShout = winShout;
        this.loseShout = loseShout;
        this.battleShout = battleShout;
        this.life = life;
        this.level = level;
        this.power = power;
    }

    public Pokemon(String name, String winShout, String loseShout, String battleShout) {
        this(name, winShout, loseShout, battleShout, 15, 1, 10);
    }

    public void attack(Pokemon pokemon) throws Exception {
        if (this.isDead()) {
            throw new Exception("Pokemon is dead");
        }

        Random r = new Random();
        pokemon.receiveAttack(r.nextInt((this.level * this.power) + 1));

        if (pokemon.isDead()) {
            this.shoutWin();
        }
    }

    public void receiveAttack(float total) throws Exception {
        if (this.isDead()) {
            throw new Exception("Pokemon already dead");
        }

        this.life -= total;

        if (this.isDead()) {
            this.shoutLose();
        }
    }

    public boolean isDead() { return this.life <= 0; }

    public String toString() {
        return "Pokemon{name: " + this.getName() +
                ", level: " + this.getLevel() +
                ", power: " + this.getPower() +
                ", life: " + this.getLife() +
                "}";
    }

    public boolean equals(Pokemon pokemon) {
        return this.name.equals(pokemon.getName()) &&
                this.winShout.equals(pokemon.getWinShout()) &&
                this.loseShout.equals(pokemon.getLoseShout()) &&
                this.life == pokemon.getLife() &&
                this.power == pokemon.getPower() &&
                this.level == pokemon.getLevel();
    }

    public void shoutBattle() { System.out.println(this.name + ": " + this.battleShout);}
    public void shoutWin() { System.out.println(this.name + ": " + this.winShout);}
    public void shoutLose() { System.out.println(this.name + ": " + this.loseShout);}


    public String getName() {
        return name;
    }

    public String getWinShout() {
        return winShout;
    }

    public String getLoseShout() {
        return loseShout;
    }

    public String getBattleShout() {
        return battleShout;
    }

    public float getLife() {
        return life;
    }

    public int getLevel() {
        return level;
    }

    public int getPower() {
        return power;
    }
}
