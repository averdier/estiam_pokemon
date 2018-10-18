package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tournament {

    private final String name;
    private final int maxMonsters;
    private List<Pokemon> monsters;


    public Tournament(String name, int maxMonsters, List<Pokemon> monsters) throws Exception {
        this.name = name;
        this.maxMonsters = maxMonsters;
        this.monsters = new ArrayList<Pokemon>();
        this.initMonsters(monsters);
    }

    private void initMonsters(List<Pokemon> monsters) throws Exception {
        if (monsters.size() < this.maxMonsters) {
            throw new Exception("Not enough monsters to start");
        }
        Random r = new Random();
        int randomPayload = monsters.size();

        while (this.monsters.size() < this.maxMonsters) {
            Pokemon p;

            do {
                p = monsters.get(r.nextInt(randomPayload));
            } while (this.monsters.contains(p));

            this.monsters.add(p);
        }
    }

    public void start() throws Exception {
        System.out.println("Tournament: " + this.name + " is started\n");
        while (!this.isFinished()) {
            this.doBattle();
            System.out.println("\n");
        }

        System.out.println(this.monsters.get(0).getName() + " win tournament");
    }

    public void doBattle() throws Exception {
        if (this.monsters.size() < 2) {
            throw new Exception("Need at least 2 monsters");
        }

        if (this.isFinished()) {
            throw new Exception("Tournament already finished");
        }

        Random r = new Random();
        int randomPayload = this.monsters.size();

        Pokemon m1 = this.monsters.get(r.nextInt(randomPayload));
        Pokemon m2;
        do {
            m2 = this.monsters.get(r.nextInt(randomPayload));
        } while (m1.equals(m2));


        System.out.println(m1.getName() + " VS " + m2.getName());
        m1.shoutBattle();
        m2.shoutBattle();


        boolean flip = false;
        while (!m1.isDead() && !m2.isDead()) {
            if (flip) {
                m2.attack(m1);
                flip = false;
            }
            else {
                m1.attack(m2);
                flip = true;
            }
        }

        if (!m1.isDead()) {
            System.out.println(m1.getName() + " win battle");
            this.monsters.remove(m2);
        }
        else {
            System.out.println(m2.getName() + " win battle");
            this.monsters.remove(m1);
        }
    }

    public boolean isFinished() {
        return this.monsters.size() == 1;
    }
}

