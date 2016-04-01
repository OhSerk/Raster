int c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
int C1, C2, C3, C4, C5, C6, C7, C8, C9, C10;
int ax, bx, cx, dx;
public void mossa() { //Possibile mosse disponibili dal giocatore
  if (modes == 2) {
    c2 = c1;
    c4 = c3;
    c6 = c5;
    c8 = c7;
    c10 = c9;
    C2 = C1;
    C4 = C3;
    C6 = C5;
    C8 = C7;
    C10 = C9;
    C1 = PApplet.parseInt(random(0, qy));
    C3 = PApplet.parseInt(random(0, qy));
    C5 = PApplet.parseInt(random(0, qy));
    C7 = PApplet.parseInt(random(0, qy));
    C9 = PApplet.parseInt(random(0, qy));
    c1 = PApplet.parseInt(random(0, qx));
    c3 = PApplet.parseInt(random(0, qx));
    c5 = PApplet.parseInt(random(0, qx));
    c7 = PApplet.parseInt(random(0, qx));
    c9 = PApplet.parseInt(random(0, qx));
    q[c1][C1].zona = true;
    q[c3][C3].zona = true;
    q[c5][C5].zona = true;
    q[c7][C7].zona = true;
    q[c9][C9].zona = true;

    if (totale > 230) {
      for (int iii = 0; iii < qy; iii++)
        for (int ii = 0; ii < qx; ii++) {
          if (g1.play == true || g2.play == true || g3.play == true || g4.play == true)
            q[ii][iii].zona = true;
          else
            q[ii][iii].zona = false;
        }
    }
  }
}

public void noZona() {
  ax = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
  bx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  cx = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
  dx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  if ( ax == 0 && bx == 9) {
    ax = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    bx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  }
  if ( cx == 0 && dx == 9) {
    cx = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    dx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  }
  if (ax == cx && bx == dx) {
    ax = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    bx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
    cx = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    dx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  }

  if (modes == 2) {
    q[c2][C2].zona = false;
    q[c4][C4].zona = false;
    q[c6][C6].zona = false;
    q[c8][C8].zona = false;
    q[c10][C10].zona = false;
  }
}

public void CPUNO() {
  if (modes == 2) {
    for (int y = 0; y < qy; y++) 
      for (int x = 0; x < qx; x++) 
        q[x][y].zona = false;
  }
}

public void lessPoints(boolean k) {
  if (g1.play == true && g1.p2 == true && k == true) {
  }
}
public void swish(int sx, int sy) {
  int sw1, sw2, sw3, sw4;
  boolean troia1 = false, troia2 = false, troia3 = false, troia4 = false ;
  boolean gg1, gg2, gg3, gg4;
  gg1 = gg2 = gg3 = gg4 = false;
  sw1 = g1.punt;
  if (bot2.exist) sw2 = bot2.punt;
  else sw2 = g2.punt;
  if (bot3.exist) sw3 = bot3.punt;
  else sw3 = g3.punt;
  if (bot4.exist) sw4 = bot4.punt;
  else sw4 = g4.punt;
  if (g1.play == true && g1.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g2.exist == true && g2.squalifica == false) || (bot2.exist == true && bot2.squalifica == false)) && (q[sx][sy].player == 2 || troia2 == true)) {
          troia2 = true;
          if (q[i2][ii].player == 2) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 2;

          if (g2.exist)
            g2.punt = sw1;
          else
            bot2.punt = sw1;
          g1.punt = sw2;
        } else if (((g3.exist == true && g3.squalifica == false) || (bot3.exist == true && bot3.squalifica == false)) && (q[sx][sy].player == 3 || troia3 == true)) {
          troia3 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 3;

          if (g3.exist)
            g3.punt = sw1;
          else
            bot3.punt = sw1;
          g1.punt = sw3;
        } else if (((g4.exist == true && g4.squalifica == false) || (bot4.exist == true && bot4.squalifica == false)) && (q[sx][sy].player == 4 || troia4 == true)) {
          troia4 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 4;

          if (g4.exist)
            g4.punt = sw1;
          else
            bot4.punt = sw1;
          g1.punt = sw4;
        }
      }
    gg1 = true;
  } //Chiusura condizione player 1
  else if (g2.play == true && g2.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g1.exist == true && g1.squalifica == false)) && (q[sx][sy].player == 1 || troia1 == true)) {
          troia1 = true;
          if (q[i2][ii].player == 2) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 2;
          g2.punt = sw1;
          g1.punt = sw2;
        } else if (((g3.exist == true && g3.squalifica == false) || (bot3.exist == true && bot3.squalifica == false)) && (q[sx][sy].player == 3 || troia3 == true)) {
          troia3 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2)
            q[i2][ii].player = 3;

          if (g3.exist)
            g3.punt = sw2;
          else
            bot3.punt = sw2;
          g2.punt = sw3;
        } else if (((g4.exist == true && g4.squalifica == false) || (bot4.exist == true && bot4.squalifica == false)) && (q[sx][sy].player == 4 || troia4 == true)) {
          troia4 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2 )
            q[i2][ii].player = 4;

          if (g4.exist)
            g4.punt = sw2;
          else
            bot4.punt = sw2;
          g2.punt = sw4;
        }
      }
    gg2 = true;
  } //Chiusura condizione player 2
  else if (g3.play == true && g3.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g1.exist == true && g1.squalifica == false)) && (q[sx][sy].player == 1 || troia1 == true)) {
          troia1 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 3;
          g1.punt = sw3;
          g3.punt = sw1;
        } else if (((g2.exist == true && g2.squalifica == false) || (bot2.exist == true && bot2.squalifica == false)) && (q[sx][sy].player == 2 || troia2 == true)) {
          troia2 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2)
            q[i2][ii].player = 3;

          g3.punt = sw2;
          if (g2.exist)
            g2.punt = sw3;
          else
            bot2.punt = sw3;
        } else if (((g4.exist == true && g4.squalifica == false) || (bot4.exist == true && bot4.squalifica == false)) && (q[sx][sy].player == 4 || troia4 == true)) {
          troia4 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 3;
          else if (q[i2][ii].player == 3 )
            q[i2][ii].player = 4;

          if (g4.exist)
            g4.punt = sw3;
          else
            bot4.punt = sw3;
          g3.punt = sw4;
        }
      }
    gg3 = true;
  } //Chiusura condizione player 3
  else if (g4.play == true && g4.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g1.exist == true && g1.squalifica == false)) && (q[sx][sy].player == 1 || troia1 == true)) {
          troia1 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 4;
          g1.punt = sw4;
          g4.punt = sw1;
        } else if (((g2.exist == true && g2.squalifica == false) || (bot2.exist == true && bot2.squalifica == false)) && (q[sx][sy].player == 2 || troia2 == true)) {
          troia2 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2)
            q[i2][ii].player = 4;

          g4.punt = sw2;
          if (g2.exist)
            g2.punt = sw4;
          else
            bot2.punt = sw4;
        } else if (((g3.exist == true && g3.squalifica == false) || (bot3.exist == true && bot3.squalifica == false)) && (q[sx][sy].player == 3 || troia3 == true)) {
          troia3 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 3;
          else if (q[i2][ii].player == 3 )
            q[i2][ii].player = 4;

          g4.punt = sw3;
          if (g3.exist)
            g3.punt = sw4;
          else
            bot3.punt = sw4;
        }
      }
    gg4 = true;
  } //Chiusura condizione player 4
  G1 = g1.punt;
  G2 = g2.punt;
  G3 = g3.punt;
  G4 = g4.punt;
  if (gg1 == true) g1.p1 = false;
  if (gg2 == true) g2.p1 = false;
  if (gg3 == true) g3.p1 = false;
  if (gg4 == true) g4.p1 = false;
}

int curs = 1;
public void selectLingua() {
  background(75);
  if (mouseX >= 250*width/640 && mouseX <= 390*width/640 && mouseY >= 140*height/360 && mouseY <= 220*height/360) {
    mouseX = 100;
  } 
  fill(200);
  textSize( 28*width/640);
  text(parola[31], 320*width/640, 50*height/360); //Lingua
  imageMode(CENTER);
  image(Flag[curs], 320*width/640, 180*height/360, 210*width/640, 160*height/360);
  imageMode(CORNER);
  text(language, 320*width/640, 300*height/360);
  if (mouseX >= 90*width/640 && mouseX <= 150*width/640 && mouseY >= 150*height/360 && mouseY <= 210*height/360) {
    textSize(80*width/640);
    fill(255, 0, 0);
    mouseX = 1000;
  } else {
    fill(200);
    textSize(54*width/640);
  }
  text("<", 120*width/640, 180*height/360);
  if (mouseX >= 470*width/640 && mouseX <= 530*width/640 && mouseY >= 150*height/360 && mouseY <= 210*height/360) {
    textSize(80*width/640);
    fill(255, 0, 0);
    mouseX = 1000;
  } else {
    fill(200);
    textSize(54*width/640);
  }
  text(">", 500*width/640, 180*height/360);
}