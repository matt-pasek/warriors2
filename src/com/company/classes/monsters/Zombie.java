package com.company.classes.monsters;

import com.company.classes.CharacterClass;
import com.company.classes.MonsterClass;

import javax.swing.*;

public class Zombie extends MonsterClass {
  public Zombie(CharacterClass[] players) {
    super(players);
    this.speed = 2000;
    this.attackAmount = 100;
    this.health = 200;
    this.maxHealth = 200;
    this.image = new ImageIcon("aBase.png").getImage();
  }
}
