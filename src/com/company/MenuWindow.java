package com.company;

import javax.swing.*;

public class MenuWindow extends JFrame {
  public MenuWindow(int weight, int height) {
    setSize(weight, height);
    setLocationRelativeTo(null);
    add(new Menu(this));
    setVisible(true);
  }
}
