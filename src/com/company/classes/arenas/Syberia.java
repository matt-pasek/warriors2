package com.company.classes.arenas;

import com.company.classes.ArenaBase;

import javax.swing.*;

public class Syberia extends ArenaBase {
  public Syberia() {
    int[][] blocks = { { 2 * 40, 1 * 80 }, { 6 * 40, 1 * 80 }, { 2 * 40, 3 * 80 }, { 6 * 40, 3 * 80 } };
    this.wallLocation = blocks;
    this.baseImage = new ImageIcon("iceBlock.png").getImage();
  }
}
