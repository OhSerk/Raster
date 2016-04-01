int tutorial, conto;

public void htp() {
  if (tutorial == 0) {
    conto = 0;
    sc = 0;
  } else {
    if (conto < 120)
      conto ++;
    else {
      conto = 0;
    }
  }
  background(75);
  if (mouseX <= 70*width/640 && mouseY <= 70*height/360) {
    fill(255, 0, 0);
    textSize (18*height/360);
  } else {
    fill(200);
    textSize (12*height/360);
  }
  text(parola[28], 50*width/640, 50*height/360);
  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 65*height/360 && mouseY <= 85*height/360) {
    fill(255, 0, 0);
    textSize (26*height/360);
  } else {
    fill(200);
    textSize (19*height/360);
  }
  text(parola[57], 320*width/640, 80*height/360);

  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 145*height/360 && mouseY <= 165*height/360) {
    fill(255, 0, 0);
    textSize (26*height/360);
  } else {
    fill(200);
    textSize (19*height/360);
  }
  text(parola[58], 320*width/640, 160*height/360);

  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 225*height/360 && mouseY <= 245*height/360) {
    fill(255, 0, 0);
    textSize (26*height/360);
  } else {
    fill(200);
    textSize (19*height/360);
  }
    text(parola[77], 320*width/640, 240*height/360); //Bonus

  if (tutorial != 0) {
    background(0);
    fill(255);
    rect(640*width/640, 0, 640*width/640, 360*height/360);
    fill(230);
    if (tutorial == 1 || tutorial == 2) {
      for (int i = 0; i < 40; i++) { 
        if (parola[46].length()*i*width/640 >= 640*width/640 || parola[47].length()*i*width/640 >= 640*width/640 || parola[53].length()*i*width/640 >= 640*width/640) {
          textSize((i-1)*2*width/640);
          println((i-1)*2*width/640);
          break;
        }
      }
      textAlign(LEFT);
      text(parola[36]+"\n"+
        parola[37]+"\n"+
        parola[38]+"\n"+
        parola[39]+"\n"+
        parola[40]+"\n"+
        parola[41]+"\n"+
        parola[42]+"\n"+
        parola[43]+"\n"+
        parola[44], 10*width/640, 30*height/360)
        ;
    } else if ( tutorial == 3) {
      for (int i = 0; i < 40; i++)
        if (parola[79].length()*i*width/640 >= 640*width/640) {
          textSize((i-1)*2*width/640);
          println((i-1)*2*width/640);
          break;
        }
    }
  }

  if (tutorial == 1) {
    impossibile();
    if (sc < 15 && conto >=120)
      sc++;
    else if (sc >= 15 && conto >=120) {
      sc = 0;
    }
    fill(230);
    text(parola[45]+"\n"+
      parola[46]+"\n"+
      parola[47]+"\n"+
      parola[48]+"\n"+
      parola[49]+"\n"+
      parola[50], 10*width/640, 200*height/360)
      ;
  } else if (tutorial == 2) {
    impossibile();
    if (sc < 15 && conto >=120)
      sc++;
    else if (sc >= 15 && conto >=120) {
      sc = 0;
    }
    fill(230);
    text(parola[51]+"\n"+
      parola[52]+"\n"+
      parola[53]+"\n"+
      parola[54]+"\n"+
      parola[47]+"\n"+
      parola[48]+"\n"+
      parola[49]+"\n"+
      parola[50], 10*width/640, 200*height/360)
      ;
  }

  if (tutorial == 3) {
    textAlign(LEFT);
    background(255);
    fill(0);
    text(parola[78]+"\n"+
      parola[79], 10*width/640, 25*height/360);

    image(SW, 10*width/640, 60*height/360, 50*width/640, 50*width/640);
    text(parola[80]+"\n"+
      parola[81]+"\n"+
      parola[82]+"\n", 70*width/640, 80*height/360);
  }
}

public void impossibile() {
  if (tutorial == 2 && sc >= 10) {
    fill(255, 0, 255);
  } else {
    fill(240);
  }
  rect(410*width/640, 10*height/360, 230*width/640, 153*height/360); //Grande "quadrato"
  supFill();
  rect(410*width/640, (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360);
  if (tutorial == 1)
    tut1();
  if (tutorial == 2)
    tut2();
  for (int i = 0; i< 10; i++) {
    line(410*width/640, (10*height/360)+(17*height/360*i), 640*width/640, (10*height/360)+(17*height/360*i)); 
    line((410*width/640)+(i*23*width/640), 10*height/360, (410*width/640)+(i*23*width/640), 170*height/360);
  }
}

public void supFill() {
  if (B < 200)
    B++;
  if (R < 160)
    R++;
  if (G < 240)
    G+=0.5f;
  if (B >= 200)
    B = 20;
  if (R >= 160 && R < 161)
    R = 255;
  if (G >= 240)
    G = 15;
  if (R > 160)
    R-= 0.3f;
  fill(R, G, B);
}

public void tut2() {
  if (sc >= 0) {
    fill(255, 0, 255);
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(9*23*width/640), (10*height/360)+(17*height/360*2), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(5*23*width/640), (10*height/360)+(17*height/360*1), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato viola
  }
  if (sc >= 2) {
    rect((410*width/640)+(6*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(2*23*width/640), (10*height/360)+(17*height/360*2), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(8*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(4*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
  }
  if (sc >= 4) {
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*1), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(5*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato viola
  }

  if (sc >= 6) {
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(5*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
  }

  if (sc >= 8) {
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(2*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
  }

  if (sc >= 10) {
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola / giallo
  }



  if (sc >= 15) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Grande quadrato rosso
  } 
  if (sc >= 13) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 11) {
    fill(255, 255, 0);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 9) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 7) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 5) {
    fill(255, 255, 0);
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 3) {
    fill(255, 255, 0);
    rect((410*width/640)+(6*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 1) {
    fill(255, 255, 0);
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 14) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Grande quadrato verde
  } 
  if (sc >= 12) {
    fill(g2.RGB);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*9*width/640, (17*height/360*2)); //Grande quadrato rosso
  }
  if (sc >= 10) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*4), 23*3*width/640, (17*height/360*3)); //Grande quadrato verde
  }
  if (sc >= 8) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato rosso
  }
  if (sc >= 6) {
    fill(g1.RGB);
    rect((410*width/640)+(3*23*width/640), (10*height/360), 23*7*width/640, (17*height/360*7)); //Grande quadrato verde
  }
  if (sc >= 4) {
    fill(g2.RGB);
    rect((410*width/640)+(6*23*width/640), (10*height/360), 23*4*width/640, (17*height/360*5)); //Grande quadrato rosso
  }
  if (sc >= 2) {
    fill(g1.RGB);
    rect((410*width/640)+(7*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato verde
  }
}

public void tut1() { 
  if (sc >= 15) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Grande quadrato rosso
  } 
  if (sc >= 13) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 11) {
    fill(255, 255, 0);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 9) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 7) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 5) {
    fill(255, 255, 0);
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 3) {
    fill(255, 255, 0);
    rect((410*width/640)+(6*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 1) {
    fill(255, 255, 0);
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 14) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Grande quadrato verde
  } 
  if (sc >= 12) {
    fill(g2.RGB);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*9*width/640, (17*height/360*2)); //Grande quadrato rosso
  }
  if (sc >= 10) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*4), 23*3*width/640, (17*height/360*3)); //Grande quadrato verde
  }
  if (sc >= 8) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato rosso
  }
  if (sc >= 6) {
    fill(g1.RGB);
    rect((410*width/640)+(3*23*width/640), (10*height/360), 23*7*width/640, (17*height/360*7)); //Grande quadrato verde
  }
  if (sc >= 4) {
    fill(g2.RGB);
    rect((410*width/640)+(6*23*width/640), (10*height/360), 23*4*width/640, (17*height/360*5)); //Grande quadrato rosso
  }
  if (sc >= 2) {
    fill(g1.RGB);
    rect((410*width/640)+(7*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato verde
  }
}