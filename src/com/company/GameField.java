package com.company;

import com.company.classes.ArenaBase;
import com.company.classes.CharacterClass;
import com.company.classes.MonsterClass;
import com.company.classes.arenas.Syberia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel {
    private Team team;
    private CharacterClass[] players;
    public static MonsterClass[] monsters;
    private ArenaBase map;

    public GameField(Team team, MonsterClass[] monsters) {
        this.team = team;
        this.players = team.getTeamMembers();
        this.monsters = monsters;
        this.map = team.getArena();
        setFocusable(true);
        addKeyListener(new FieldKeyListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CharacterClass player : players) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
            g.drawString("" + player.getHealthPoints(), player.getX(), player.getY() + 12);
            g.drawString("kills: " + player.getMonstersKilled(), player.getX(), player.getY() + 26);
            System.out.println("health points: " + player.getHealthPoints());
        }
        for (int[] wall : map.getWallLocation()) {
            g.drawImage(map.getBaseImage(), wall[0], wall[1], this);
        }
        for (int j = 0; j <= Team.i; j++) {
            if (monsters[j].isAlive()) {
                g.drawImage(monsters[j].getImage(), monsters[j].getX(), monsters[j].getY(), this);
                g.drawString("" + monsters[j].getHealth(), monsters[j].getX(), monsters[j].getY() + 12);
            }
        }
    }

    public class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            for (CharacterClass player : players) {
                if (key == player.getLeftKey()) {
                    // player.setX(player.getX() - 40);
                    player.left();
                }
                if (key == player.getRightKey()) {
                    // player.setX(player.getX() + 40);
                    player.right();
                }
                if (key == player.getUpKey()) {
                    // player.setY(player.getY() - 40);
                    player.up();
                }
                if (key == player.getDownKey()) {
                    // player.setY(player.getY() + 40);
                    player.down();
                }
                if (key == player.getLeftAttackKey()) {
                    player.setAttackLeftImage();

                    if (player.getX() >= 0 + (40 * player.getMinRange())
                            && CharacterClass.occupiedCells[player.getX() - 40 * player.getMinRange()][player
                                    .getY()] > 0
                            && CharacterClass.occupiedCells[player.getX() - 40 * player.getMinRange()][player
                                    .getY()] < 5) {
                        player.attack(
                                players[(CharacterClass.occupiedCells[player.getX() - 40 * player.getMinRange()][player
                                        .getY()]) - 1]);
                    } else if (player.getX() >= 0 + (40 * player.getMinRange())
                            && CharacterClass.occupiedCells[player.getX() - 40 * player.getMinRange()][player
                                    .getY()] > 5) {
                        player.attack(
                                monsters[(CharacterClass.occupiedCells[player.getX() - 40 * player.getMinRange()][player
                                        .getY()]) - 6]);
                    } else if (player.getX() >= 0 + (40 * player.getMaxRange())
                            && CharacterClass.occupiedCells[player.getX() - 40 * player.getMaxRange()][player
                                    .getY()] > 0
                            && CharacterClass.occupiedCells[player.getX() - 40 * player.getMaxRange()][player
                                    .getY()] < 5) {
                        player.attack(
                                players[(CharacterClass.occupiedCells[player.getX() - 40 * player.getMaxRange()][player
                                        .getY()]) - 1]);
                    } else if (player.getX() >= 0 + (40 * player.getMaxRange())
                            && CharacterClass.occupiedCells[player.getX() - 40 * player.getMaxRange()][player
                                    .getY()] > 5) {
                        player.attack(
                                monsters[(CharacterClass.occupiedCells[player.getX() - 40 * player.getMaxRange()][player
                                        .getY()]) - 6]);
                    }

                    // timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200);
                }
                if (key == player.getRightAttackKey()) {
                    player.setAttackRightImage();
                    if (player.getX() <= 320 - (40 * player.getMinRange())
                            && CharacterClass.occupiedCells[player.getX() + 40 * player.getMinRange()][player
                                    .getY()] > 0
                            && CharacterClass.occupiedCells[player.getX() + 40 * player.getMinRange()][player
                                    .getY()] < 5) {
                        player.attack(
                                players[(CharacterClass.occupiedCells[player.getX() + 40 * player.getMinRange()][player
                                        .getY()]) - 1]);
                    } else if (player.getX() <= 320 - (40 * player.getMinRange())
                            && CharacterClass.occupiedCells[player.getX() + 40 * player.getMinRange()][player
                                    .getY()] > 5) {
                        player.attack(
                                monsters[(CharacterClass.occupiedCells[player.getX() + 40 * player.getMinRange()][player
                                        .getY()]) - 6]);
                    } else if (player.getX() <= 320 - (40 * player.getMaxRange())
                            && CharacterClass.occupiedCells[player.getX() + 40 * player.getMaxRange()][player
                                    .getY()] > 0
                            && CharacterClass.occupiedCells[player.getX() + 40 * player.getMaxRange()][player
                                    .getY()] < 5) {
                        player.attack(
                                players[(CharacterClass.occupiedCells[player.getX() + 40 * player.getMaxRange()][player
                                        .getY()]) - 1]);
                    } else if (player.getX() <= 320 - (40 * player.getMaxRange())
                            && CharacterClass.occupiedCells[player.getX() + 40 * player.getMaxRange()][player
                                    .getY()] > 5) {
                        player.attack(
                                monsters[(CharacterClass.occupiedCells[player.getX() + 40 * player.getMaxRange()][player
                                        .getY()]) - 6]);
                    }

                    // timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200);
                }
                if (key == player.getAbilityKey()) {
                    player.ability();
                }
            }
            repaint();
        }
    }
}
