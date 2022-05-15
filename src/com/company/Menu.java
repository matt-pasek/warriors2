package com.company;

import com.company.classes.ArenaBase;
import com.company.classes.CharacterClass;
import com.company.classes.arenas.FallenCity;
import com.company.classes.arenas.Syberia;
import com.company.classes.characters.Archer;
import com.company.classes.characters.Healer;
import com.company.classes.characters.Mage;
import com.company.classes.characters.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JPanel {
  private Team team;
  private int whatSelected = 0;
  private String[] mapNames = { "syberia", "fallencity" };
  private int selectedMap = 0;
  private int playerNumber = 4;
  private MenuWindow frame;

  public Menu(MenuWindow frame) {
    this.frame = frame;
    setFocusable(true);
    addKeyListener(new Menu.FieldKeyListener());
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    switch (whatSelected) {
      case 0:
        g.drawString("> Map: " + mapNames[selectedMap], 50, 50);
        g.drawString("Player Number: " + playerNumber, 50, 75);
        g.drawString("Start", 50, 100);
        break;
      case 1:
        g.drawString("Map: " + mapNames[selectedMap], 50, 50);
        g.drawString("> Player Number: " + playerNumber, 50, 75);
        g.drawString("Start", 50, 100);
        break;
      case 2:
        g.drawString("Map: " + mapNames[selectedMap], 50, 50);
        g.drawString("Player Number: " + playerNumber, 50, 75);
        g.drawString("> Start", 50, 100);
        break;
    }
  }

  public class FieldKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
      super.keyPressed(e);
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_UP && whatSelected > 0) {
        whatSelected--;
      }
      if (key == KeyEvent.VK_DOWN && whatSelected < 2) {
        whatSelected++;
      }
      if (key == KeyEvent.VK_LEFT && whatSelected == 0 && selectedMap > 0) {
        selectedMap--;
      }
      if (key == KeyEvent.VK_RIGHT && whatSelected == 0 && selectedMap < mapNames.length - 1) {
        selectedMap++;
      }
      if (key == KeyEvent.VK_LEFT && whatSelected == 1 && playerNumber > 1) {
        playerNumber--;
      }
      if (key == KeyEvent.VK_RIGHT && whatSelected == 1 && playerNumber < 4) {
        playerNumber++;
      }
      if (key == KeyEvent.VK_ENTER && whatSelected == 2) {
        Team team = null;
        switch (playerNumber) {
          case 1:
            team = new Team(
                new Warrior("Filip", 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q,
                    KeyEvent.VK_E, 0));
            break;
          case 2:
            team = new Team(
                new Warrior("Andrew", 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q,
                    KeyEvent.VK_E, 0),
                new Mage("Salazar", 0, 320, KeyEvent.VK_H, KeyEvent.VK_K, KeyEvent.VK_U, KeyEvent.VK_J, KeyEvent.VK_Y,
                    KeyEvent.VK_I, 0));
            break;
          case 3:
            team = new Team(
                new Warrior("John", 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q,
                    KeyEvent.VK_E, 0),
                new Mage("Steve", 0, 320, KeyEvent.VK_H, KeyEvent.VK_K, KeyEvent.VK_U, KeyEvent.VK_J, KeyEvent.VK_Y,
                    KeyEvent.VK_I, 0),
                new Archer("Anatol", 320, 0, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
                    KeyEvent.VK_DELETE, KeyEvent.VK_PAGE_DOWN, 0));
            break;
          case 4:
            team = new Team(
                new Warrior("Smerth", 0, 0, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q,
                    KeyEvent.VK_E, 0),
                new Mage("Sewerus", 0, 320, KeyEvent.VK_H, KeyEvent.VK_K, KeyEvent.VK_U, KeyEvent.VK_J, KeyEvent.VK_Y,
                    KeyEvent.VK_I, 0),
                new Archer("Robin", 320, 0, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN,
                    KeyEvent.VK_DELETE, KeyEvent.VK_PAGE_DOWN, 0),
                new Healer("Magnat Magnum", 320, 320, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD3, KeyEvent.VK_NUMPAD5,
                    KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD9));
            break;
        }
        ArenaBase map = null;
        switch (selectedMap) {
          case 0:
            map = new Syberia();
            break;
          case 1:
            map = new FallenCity();
            break;
        }
        if (team.enterArena(map)) {
          team.setArena(map);
          team.start();
          for (int[] wall : map.getWallLocation()) {
            CharacterClass.occupiedCells[wall[0]][wall[1]] = 5;
          }
        } else {
          System.out.println("Game over");
        }
        frame.dispose();
      }
      repaint();
    }
  }
}
