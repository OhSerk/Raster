class CPU {
  boolean play, exist, squalifica;
  int RGB;
  int punt, vv, controllo = 0; //punti partita e vittorie
  int varX, varY;
  int in=0;
  int pp;
  int[] X = new int[qx*qy];
  int[] Y = new int[qx*qy];
  int ex, p1, p2, p3, p4; //EX per i 150 punti finali, p1..4 sono i perks aggiuntivi al gioco
  CPU(boolean attivo, int cc, int punti, boolean vita, boolean sq) {
    play = attivo;
    RGB = cc;
    punt = punti;
    exist = vita;
    squalifica = sq;
  }

  public void viewCPU() {
    fill(RGB);
    text (punt, 600*width/640, 20*height/360);
    text("Wins:"+vv, 600*width/640, 320*height/360);
  }

  public void IA() {

    if (play == true) {

      if (Lcpu == 0) liv0();
      else if (Lcpu == 1) liv1(); 
      else if (Lcpu == 2) liv2();
      else if (Lcpu == 3) liv3();
      else if (Lcpu == 10) liv10();
    }
    if (punt > controllo) controllo = punt;
    if (mouseX >= 20*width/640 && mouseX <= 110*width/640 && mouseY >= 270*height/360 && mouseY <= 360*height/360)
      ;
    else {
      if (!backpressed)
        mouseReleased();
    }
  }

  public void liv10() {
    if (Nmosse == 4) {
      q[qx-9][6].tantipunti(); 
      play = false;
      Nmosse++;
    } else if (Nmosse == 10) {
      q[8][8].tantipunti(); 
      play = false;
      Nmosse++;
    } else if (Nmosse == 15) {
      q[6][9].tantipunti(); 
      play = false;
      Nmosse++;
    } else if(Nmosse == 20){
      q[1][9].tantipunti();
      play = false;
      Nmosse++;
    } else if(Nmosse == 25){
      q[0][9].tantipunti();
      play = false;
      Nmosse++;
    }
  }

  public void liv0() {
    for (int y = 0; y < q[0].length; y++) {
      for (int x = 0; x < q.length; x++) {
        if (q[x][y].colore == false) {
          X[in] = x;
          Y[in] = y;
          in++;
          //println("Sto ciclando con in ="+in);
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }

  public void liv1() {
    for (int y = 0; y < q[0].length; y++) {
      for (int x = 0; x < q.length; x++) {
        if (q[x][y].colore == false && in <= 70) {
          X[in] = x;
          Y[in] = y;
          in++;
          //println("Sto ciclando con in ="+in);
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }

  public void liv2() {
    int unozero = PApplet.parseInt(random(2));
    if (unozero == 1) {
      for (int y = 0; y < q[0].length; y++) {
        for (int x = q.length-1; x >= 0; x--) {
          if (q[x][y].colore == false && in <= 15) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in+"Caso1");
          }
        }
      }
    } else {
      for (int x = q.length-1; x >= 0; x--) {
        for (int y = 0; y < q[0].length; y++) {
          if (q[x][y].colore == false && in <= 15) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in);
          }
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    //  if (totale < 245) {
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }

  public void liv3() {
    int unozero = PApplet.parseInt(random(2));
    if (unozero == 1) {
      for (int y = 0; y < q[0].length; y++) {
        for (int x = q.length-1; x >= 0; x--) {
          if (q[x][y].colore == false && in <= 5) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in+"Caso1");
          }
        }
      }
    } else {
      for (int x = q.length-1; x >= 0; x--) {
        for (int y = 0; y < q[0].length; y++) {
          if (q[x][y].colore == false && in <= 5) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in);
          }
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    //  if (totale < 245) {
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }
}

float toccoX, toccoY;
boolean pausa = true;
public void computer() {
  if (g1.play == false && g2.play == false && g3.play == false && g4.play == false && playopts == false) {
    if (bot2.play == true || bot3.play == true || bot4.play == true) { // SE ALMENO UN BOT E' VERO

      if (bot2.play == true) {
        IA = true;
        bot2.IA();
        toccoX = (23*bot2.varX)*width/640;
        toccoY = (23*bot2.varY)*height/360;
        //return;
      } else if (bot3.play == true) {
        IA = true;
        bot3.IA();
        toccoX = (23*bot3.varX)*width/640;
        toccoY = (23*bot3.varY)*height/360;
        //return;
      } else if (bot4.play == true) {
        IA = true;
        bot4.IA();
        toccoX = (23*bot4.varX)*width/640;
        toccoY = (23*bot4.varY)*height/360;
        //return;
      }

      if (think >= setThink ) {
        think = 0;
        pausa = false;
      }
    }//Chiusa condizione di "SE ALMENO UN BOT E' VERO"
    else IA = false;
  }
}