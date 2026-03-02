package com.narxoz.rpg.enemy;

public class BasicEnemy implements Enemy {
    protected final String title;
    protected final int damage;
    protected int health;

    public BasicEnemy(String title, int damage, int health) {
        this.title = title;
        this.damage = damage;
        this.health = health;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void applyDamage(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    @Override
    public boolean isDefeated() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }
}