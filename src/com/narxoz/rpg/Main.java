package com.narxoz.rpg;

import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.Combatant;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.BasicEnemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.hero.Mage;
import com.narxoz.rpg.hero.Warrior;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== RPG Battle Engine Demo ===\n");

        Warrior warrior = new Warrior("Arthas");
        Mage mage = new Mage("Jaina");
        Goblin goblin = new Goblin();


        List<Combatant> heroes = new ArrayList<>();
        heroes.add(new HeroCombatantAdapter(warrior));
        heroes.add(new HeroCombatantAdapter(mage));

        List<Combatant> enemies = new ArrayList<>();
        enemies.add(new EnemyCombatantAdapter(goblin));
        enemies.add(new EnemyCombatantAdapter(new BasicEnemy("Goblin 2", 12, 60)));

        BattleEngine engineA = BattleEngine.getInstance();
        BattleEngine engineB = BattleEngine.getInstance();
        System.out.println("--- Singleton Pattern Check ---");
        System.out.println("Engine A Memory Hash: " + engineA.hashCode());
        System.out.println("Engine B Memory Hash: " + engineB.hashCode());
        System.out.println("Same instance? " + (engineA == engineB));
        System.out.println();

        engineA.setRandomSeed(42L);
        EncounterResult result = engineA.runEncounter(heroes, enemies);


        for (String line : result.getBattleLog()) {
            System.out.println(line);
        }

        System.out.println("\n--- BATTLE SUMMARY ---");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("\n=== Demo Complete ===");
    }
}
