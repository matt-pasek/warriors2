package com.company;

import com.company.classes.ArenaBase;
import com.company.classes.CharacterClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel {
    private Team team;
    private CharacterClass[] players;
    private ArenaBase map;

    public GameField(Team team) {
        this.team = team;
        this.players = team.getTeamMembers();
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
            System.out.println("health points: " + player.getHealthPoints());
        }
        for (int[] wall : map.getWallLocation()) {
            g.drawImage(map.getBaseImage(), wall[0], wall[1], this);
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
