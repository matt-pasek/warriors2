package com.company;

import com.company.classes.ArenaBase;
import com.company.classes.CharacterClass;
import com.company.classes.MonsterClass;
import com.company.classes.monsters.Witch;
import com.company.classes.monsters.Zombie;

public class Team {
  private CharacterClass[] teamMembers;
  public static int i = 0;
  private ArenaBase arena;

  public void setArena(ArenaBase arena) {
    this.arena = arena;
  }

  public Team(CharacterClass... members) {
    teamMembers = new CharacterClass[members.length];

    for (int i = 0; i < teamMembers.length; i++) {
      teamMembers[i] = members[i];
    }
  }

  public CharacterClass[] getTeamMembers() {
    return teamMembers;
  }

  public void info() {
    for (CharacterClass teamMembers : teamMembers) {
      teamMembers.info();
    }
  }

  public boolean enterArena(ArenaBase arena1) {
    return arena1.open(this);
  }

  public void start() {
    MonsterClass[] monsters = new MonsterClass[999];
    monsters[i] = new Witch(this.teamMembers);
    MainWindow mw = new MainWindow(375, 438, this, monsters);
    this.spawnMonsters();
  }

  public void spawnMonsters() {
    Team thisTeam = this;
    if (MonsterClass.currentMonsters <= 3) {
      i++;
      if (i % 2 == 0) {
        GameField.monsters[i] = new Zombie(this.teamMembers);
      } else {
        GameField.monsters[i] = new Witch(this.teamMembers);
      }
      new java.util.Timer().schedule(
          new java.util.TimerTask() {
            @Override
            public void run() {
              thisTeam.spawnMonsters();
            }
          }, 5000);
    }
  }

  public ArenaBase getArena() {
    return arena;
  }
}
