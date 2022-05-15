package com.company.classes.arenas;

import com.company.classes.ArenaBase;

import javax.swing.*;

public class FallenCity extends ArenaBase {
  public FallenCity() {
    int[][] blocks = { { 2 * 40, 0 * 80 }, { 6 * 40, 0 * 80 }, { 2 * 40, 4 * 80 }, { 6 * 40, 4 * 80 },
        { 0 * 40, 2 * 80 }, { 8 * 40, 2 * 80 }, { 4 * 40, 2 * 80 } };
    this.wallLocation = blocks;
    this.baseImage = new ImageIcon("images/wallBlock.png").getImage();
  }
}
