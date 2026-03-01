package com.narxoz.rpg.battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class BattleEngine {
    private static BattleEngine instance;
    private Random random = new Random(1L);

    private BattleEngine() {
    }

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }

    public BattleEngine setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public void reset() {
        this.random = new Random(1L);
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        // TODO: validate inputs and run round-based battle
        EncounterResult result = new EncounterResult();
        int rounds = 0;
        List<Combatant> activeTeamA = new ArrayList<>(teamA);
        List<Combatant> activeTeamB = new ArrayList<>(teamB);

        result.addLog("--- Battle Started! ---");

        while (!activeTeamA.isEmpty() && !activeTeamB.isEmpty()) {
            rounds++;
            result.addLog("\n---Round " + rounds + " --- ");

            // Team A attacks Team B
            for (Combatant attacker : activeTeamA) {
                if (activeTeamB.isEmpty()) break;
                if (!attacker.isAlive()) continue;

                Combatant target = activeTeamB.get(random.nextInt(activeTeamB.size()));
                int damage = attacker.getAttackPower();
                target.takeDamage(damage);

                result.addLog(attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage.");

                if (!target.isAlive()) {
                    result.addLog(target.getName() + " has been defeated!");
                    activeTeamB.remove(target);
                }
            }

            // Team B attacks Team A
            for (Combatant attacker : activeTeamB) {
                if (activeTeamA.isEmpty()) break;
                if (!attacker.isAlive()) continue;

                Combatant target = activeTeamA.get(random.nextInt(activeTeamA.size()));
                int damage = attacker.getAttackPower();
                target.takeDamage(damage);

                result.addLog(attacker.getName() + " attacks " + target.getName() + " for " + damage + " damage.");

                if (!target.isAlive()) {
                    result.addLog(target.getName() + " has been defeated!");
                    activeTeamA.remove(target);
                }
            }
        }


        result.setRounds(rounds);
        if (activeTeamA.isEmpty() && activeTeamB.isEmpty()) {
            result.setWinner("Draw");
        } else if (activeTeamB.isEmpty()) {
            result.setWinner("Team A (Heroes)");
        } else {
            result.setWinner("Team B (Enemies)");
        }

        return result;
    }
}
