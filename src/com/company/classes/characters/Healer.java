package com.company.classes.characters;

import com.company.classes.AttackType;
import com.company.classes.CharacterClass;

import java.util.Timer;
import java.util.TimerTask;

public class Healer extends CharacterClass {
  public Healer(String name, int x, int y, int leftKey, int rightKey, int upKey, int downKey, int leftAttackKey,
      int rightAttackKey, int abilityKey) {
    super(name, x, y, leftKey, rightKey, upKey, downKey, leftAttackKey, rightAttackKey, 1, 2, abilityKey);
    this.setAttackType(AttackType.HEALING);
    this.setAttackAmount(50);
    this.setMaxHealthPoints(500);
    setHealthPoints(500);
    this.setMaxManaPoints(1000);
    setManaPoints(1000);
    this.className = "Healer";
    this.uploadImage("hBase.png", "hLeft.png", "hRight.png");
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

  @Override
  public void ability() {
    if (!onCooldown) {
      this.restoreHealth(100);
      this.onCooldown = true;
      Healer thisPlayer = this;
      Timer timer = new Timer();
      TimerTask task = new TimerTask() {
        @Override
        public void run() {
          thisPlayer.endCooldown();
        }
      };
      timer.schedule(task, 5000);
    }
  }

  public void endCooldown() {
    this.onCooldown = false;
  }
}
