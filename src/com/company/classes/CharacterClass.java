package com.company.classes;

import javax.swing.*;
import java.awt.*;

public abstract class CharacterClass implements BaseClass {
  public boolean onCooldown = false;
  public static int[][] occupiedCells = new int[321][321];
  public static int playerCount = 0;
  private int number;
  private int healthPoints = 200;
  private int manaPoints;
  private int level;
  private AttackType attackType;
  private int attackAmount;
  private String name;
  private int maxHealthPoints;
  private int maxManaPoints;
  private int leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, abilityKey;
  protected String className;
  private int minRange, maxRange;

  public CharacterClass(
      String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey,
      int rightAttackKey, int minRange, int maxRange, int abilityKey) {
    this.number = ++playerCount;
    occupiedCells[x][y] = this.number;
    this.name = name;
    this.x = x;
    this.y = y;
    this.leftKey = leftKey;
    this.rightKey = rightKey;
    this.upKey = upKey;
    this.downKey = downKey;
    this.leftAttackKey = leftAttackKey;
    this.rightAttackKey = rightAttackKey;
    this.minRange = minRange;
    this.maxRange = maxRange;
    this.abilityKey = abilityKey;
  }

  public void setHealthPoints(int healthPoints) {
    System.out.println("SET HP1 " + healthPoints);
    if (healthPoints <= 0) {
      this.healthPoints = 0;
      this.dead();
    } else if (healthPoints > this.maxHealthPoints) {
      this.healthPoints = this.maxHealthPoints;
    } else {
      this.healthPoints = healthPoints;
    }
    System.out.println("SET HP2 " + this.healthPoints);
  }

  public void setManaPoints(int manaPoints) {
    if (manaPoints < 0) {
      this.manaPoints = 0;
    } else if (manaPoints > this.maxManaPoints) {
      this.manaPoints = this.maxManaPoints;
    } else {
      this.manaPoints = manaPoints;
    }
  }

  public void setLevel(int level) {
    if (level < 1) {
      System.out.println("We can't lose level");
    } else {
      this.level = level;
    }
  }

  public void setAttackType(AttackType attackType) {
    this.attackType = attackType;
  }

  public void setAttackAmount(int attackAmount) {
    this.attackAmount = attackAmount;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMaxHealthPoints(int maxHealthPoints) {
    this.maxHealthPoints = maxHealthPoints;
  }

  public void setMaxManaPoints(int maxManaPoints) {
    this.maxManaPoints = maxManaPoints;
  }

  public int getHealthPoints() {
    return healthPoints;
  }

  public int getManaPoints() {
    return manaPoints;
  }

  public int getLevel() {
    return level;
  }

  public AttackType getAttackType() {
    return attackType;
  }

  public int getAttackAmount() {
    return attackAmount;
  }

  public String getName() {
    return name;
  }

  public int getMaxHealthPoints() {
    return maxHealthPoints;
  }

  public int getMaxManaPoints() {
    return maxManaPoints;
  }

  public void attack(CharacterClass attackedPlayer) {
    int gainedHealthPoints = 0;
    if (this.attackType == AttackType.HEALING) {
      if (this.manaPoints >= this.attackAmount) {
        if (attackedPlayer.getHealthPoints() + this.attackAmount > attackedPlayer.getMaxHealthPoints()) {
          gainedHealthPoints = attackedPlayer.getMaxHealthPoints() - attackedPlayer.getHealthPoints();
          loseMana(gainedHealthPoints);
          attackedPlayer.setHealthPoints(attackedPlayer.getMaxHealthPoints());
        } else {
          gainedHealthPoints = this.attackAmount;
          attackedPlayer.restoreHealth(this.attackAmount);
          loseMana(this.attackAmount);
        }
        System.out.println(this.className + " healed " + attackedPlayer.className + " for " + gainedHealthPoints
            + " and lost " + gainedHealthPoints + " mana");
      } else {
        System.out.println("Not enough mana");
      }
    }
    if (this.attackType == AttackType.MAGICAL || this.attackType == AttackType.MAGICAL_RANGED) {
      if (this.manaPoints >= this.attackAmount) {
        attackedPlayer.loseHealth(this.attackAmount);
        loseMana(this.attackAmount);
      } else {
        System.out.println("Not enough mana");
      }
      System.out.println(this.className + " attacked " + attackedPlayer.className + " for " + this.attackAmount);
    } else {
      attackedPlayer.loseHealth(this.attackAmount);
      System.out.println(this.className + " attacked " + attackedPlayer.className + " for " + this.attackAmount);
    }

  }

  @Override
  public void restoreHealth(int amount) {
    setHealthPoints(this.getHealthPoints() + amount);
  }

  @Override
  public void loseHealth(int amount) {
    setHealthPoints(this.getHealthPoints() - amount);
  }

  @Override
  public void restoreMana(int amount) {
    setManaPoints(this.getManaPoints() + amount);
  }

  @Override
  public void loseMana(int amount) {
    setManaPoints(this.getManaPoints() - amount);
  }

  @Override
  public void levelUp() {
    setLevel(getLevel() + 1);
  }

  @Override
  public void info() {
    System.out.println("Name: " + this.name + "\nCurrentHP: " + this.healthPoints + "\nCurrentmana: "
        + this.maxManaPoints + "\nLevel: " + this.level);
  }

  private Image image, baseImage, attackLeftImage, attackRightImage;
  private int x, y;

  public void setImage(Image image) {
    this.image = image;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public Image getImage() {
    return image;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void uploadImage(String baseImage, String attackLeftImage, String attackRightImage) {
    baseImage = "images\\" + baseImage;
    attackLeftImage = "images\\" + attackLeftImage;
    attackRightImage = "images\\" + attackRightImage;
    this.baseImage = new ImageIcon(baseImage).getImage();
    this.attackLeftImage = new ImageIcon(attackLeftImage).getImage();
    this.attackRightImage = new ImageIcon(attackRightImage).getImage();
    setBaseImage();
  }

  public void setBaseImage() {
    this.image = this.baseImage;
  }

  public void setAttackLeftImage() {
    this.image = this.attackLeftImage;
  }

  public void setAttackRightImage() {
    this.image = this.attackRightImage;
  }

  public int getLeftKey() {
    return leftKey;
  }

  public int getRightKey() {
    return rightKey;
  }

  public int getUpKey() {
    return upKey;
  }

  public int getDownKey() {
    return downKey;
  }

  public int getLeftAttackKey() {
    return leftAttackKey;
  }

  public int getRightAttackKey() {
    return rightAttackKey;
  }

  public void tryChangePosition(int newPositionX, int newPositionY) {
    if (occupiedCells[newPositionX][newPositionY] == 0) {
      occupiedCells[this.x][this.y] = 0;
      occupiedCells[newPositionX][newPositionY] = this.number;
      this.x = newPositionX;
      this.y = newPositionY;
    } else {
      reduceHealth(50);
    }
  }

  protected void reduceHealth(int amount) {
    setHealthPoints(this.getHealthPoints() - amount);
  }

  protected void healHealth(int amount) {
    setHealthPoints(this.getHealthPoints() + amount);
  }

  public abstract void left();

  public abstract void right();

  public abstract void up();

  public abstract void down();

  public int getMaxRange() {
    return maxRange;
  }

  public int getMinRange() {
    return minRange;
  }

  public void ability() {
  }

  public int getAbilityKey() {
    return abilityKey;
  }

  public void dead() {
    this.healthPoints = this.maxHealthPoints;
    int x, y;
    boolean again = true;
    do {
      x = ((int) (Math.random() * (9))) * 40;
      y = ((int) (Math.random() * (5))) * 80;
      if (occupiedCells[x][y] == 0) {
        occupiedCells[this.x][this.y] = 0;
        again = false;
        this.x = x;
        this.y = y;
        occupiedCells[this.x][this.y] = this.number;
      }
    } while (again);
  }

}
