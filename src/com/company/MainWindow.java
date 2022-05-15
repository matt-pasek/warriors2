package com.company;

import com.company.classes.MonsterClass;

import javax.swing.*;

public class MainWindow extends JFrame {
  public MainWindow(int weight, int height, Team team, MonsterClass[] monsters) {
    setSize(weight, height);
    setLocationRelativeTo(null);
    add(new GameField(team, monsters));
    setVisible(true);
  }
}
