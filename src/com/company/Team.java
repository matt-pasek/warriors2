package com.company;

import com.company.classes.ArenaBase;
import com.company.classes.CharacterClass;

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
    MainWindow mw = new MainWindow(375, 438, this);
  }

  public ArenaBase getArena() {
    return arena;
  }
}
