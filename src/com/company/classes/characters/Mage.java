package com.company.classes.characters;

import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

public class Mage extends CharacterClass {
  public Mage(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey,
      int rightAttackKey, int abilityKey) {
    super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, 1, 2, abilityKey);
    this.setAttackType(AttackType.MAGICAL_RANGED);
    this.setAttackAmount(100);
    this.setMaxHealthPoints(350);
    setHealthPoints(350);
    this.setMaxManaPoints(1000);
    setManaPoints(1000);
    this.className = "Mage";
    this.uploadImage("mBase.png", "mLeft.png", "mRight.png");

  }

  public void left() {
    int newPositionX = this.getX() >= 40 ? this.getX() - 40 : 320;
    tryChangePosition(newPositionX, this.getY());
  }

  public void right() {
    int newPositionX = this.getX() < 320 ? this.getX() + 40 : 0;
    tryChangePosition(newPositionX, this.getY());

  }

  public void up() {
    int newPositionY = this.getY() >= 80 ? this.getY() - 80 : 320;
    tryChangePosition(this.getX(), newPositionY);
  }

  public void down() {
    int newPositionY = this.getY() < 320 ? this.getY() + 80 : 0;
    tryChangePosition(this.getX(), newPositionY);
  }

}
