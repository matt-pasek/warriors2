package com.company.classes.characters;

import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Warrior extends CharacterClass {
  String images = "../images/warrior/";

  public Warrior(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey,
      int rightAttackKey, int abilityKey) {
    super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, 1, 1, abilityKey);
    this.setAttackType(AttackType.PHYSICAL);
    this.setAttackAmount(75);
    this.setMaxHealthPoints(900);
    setHealthPoints(900);
    this.className = "Warior";
    this.uploadImage("wBase.png", "wLeft.png", "wRight.png");
  }

  public void left() {
    int newPositionX = this.getX() > 40 ? this.getX() - 40 : 0;
    tryChangePosition(newPositionX, this.getY());
  }

  public void right() {
    int newPositionX = this.getX() < 320 ? this.getX() + 40 : 320;
    tryChangePosition(newPositionX, this.getY());
  }

  public void up() {
    int newPositionY = this.getY() >= 80 ? this.getY() - 80 : 0;
    System.out.println(newPositionY);
    tryChangePosition(this.getX(), newPositionY);
  }

  public void down() {
    int newPositionY = this.getY() < 320 ? this.getY() + 80 : 320;
    tryChangePosition(this.getX(), newPositionY);
  }

}
