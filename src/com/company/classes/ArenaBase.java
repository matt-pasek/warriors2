package com.company.classes;

import com.company.Team;
import com.company.classes.arenas.BaseArena;

import java.awt.*;

public class ArenaBase implements BaseArena {
  private boolean isOpened = false;
  public int[][] wallLocation;
  public Image baseImage;

  public Image getBaseImage() {
    return baseImage;
  }

  public int[][] getWallLocation() {
    return wallLocation;
  }

  public boolean open(Team team) {
    if (team.getTeamMembers().length < 1) {
      System.out.println("Not enough party members!");
      isOpened = false;
    } else {
      System.out.println("Welcome, heroes");
      isOpened = true;
    }
    return isOpened;
  }
}
