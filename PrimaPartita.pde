int Nmosse = 0;
public void primaparte() {
  background(back);
  griglia();
  if (playopts == false || backpressed == true) {
    // cp5.getController("sli")
    // .setVisible(false)
    //;
  }
  println(Nmosse);
  if (Nmosse == -1) {
    popup(parola[83], true);
  } else if (Nmosse == 0) {
    //Appare il primo popup
    popup(parola[83], true);
  } else if ( Nmosse == 1) {
    boolean punto = mouseX >= (23*(qx-3))*width/640 && mouseX  <= (((23*(qx-3))*width/640) + 22.5f*width/640) && mouseY>= (23*3)*height/360 && mouseY  <= (((23*3)*height/360) + 22.5f*height/360);
    //Chiuso il popup si illumina una sola casella, dove dovr\u00e0 toccare l'utente
    image(square, 23*(qx-3)*width/640, 23*3*height/360, 22.5f*width/640, 22.5f*height/360);
    if (punto && released) {
      g1.play = true;
      q[qx-4][2].tantipunti();
      g1.play = false;
      bot2.play = true;
      Nmosse++;
    } else if (!punto && mouseY < height-(70*height/360)) {
      popup(parola[84]+"\n"+parola[96], false); //Splasha popup 'non fare il furbo'
    }
  } else if ( Nmosse == 2) {
    popup(parola[85], true);
  } else if (Nmosse == 3) {
    image(square, 23*(qx-8)*width/640, 23*7*height/360, 22.5f*width/640, 22.5f*height/360);
    popup(parola[86], true);
  } else if (Nmosse == 4) {
    bot2.liv10();
  } else if (Nmosse == 5) {
    popup(parola[87], true);
  } else if (Nmosse == 6) {
    popup(parola[88], true);
  } else if ( Nmosse == 7) {
    boolean punto = mouseX >= (23)*width/640 && mouseX  <= (((23)*width/640) + 22.5f*width/640) && mouseY>= (23*8)*height/360 && mouseY  <= (((23*8)*height/360) + 22.5f*height/360);
    image(square, 23*width/640, 23*8*height/360, 22.5f*width/640, 22.5f*height/360);
    if (punto && released) {
      g1.play = true;
      q[0][7].tantipunti();
      g1.play = false;
      bot2.play = true;
      Nmosse++;
    } else if (!punto && mouseY < height-(70*height/360)) {
      popup(parola[84]+"\n"+parola[96], false); //Splasha popup 'non fare il furbo'
    }
  } else if (Nmosse == 8) {
    popup(parola[85], true);
  } else if (Nmosse == 9) {
    image(square, 23*(9)*width/640, 23*9*height/360, 22.5f*width/640, 22.5f*height/360);
    popup(parola[86], true);
  } else if (Nmosse == 10) {
    bot2.liv10();
  } else if (Nmosse == 11) {
    popup(parola[89], true);
  } else if (Nmosse == 12) {
    popup(parola[90], true);
  } else if (Nmosse == 13) {
    boolean punto = mouseX >= (23*8)*width/640 && mouseX  <= (((23*8)*width/640) + 22.5f*width/640) && mouseY>= (23*9)*height/360 && mouseY  <= (((23*9)*height/360) + 22.5f*height/360);
    image(square, 23*(8)*width/640, 23*9*height/360, 22.5f*width/640, 22.5f*height/360);
    image(SW, 23*(8)*width/640, 23*9*height/360, 22.5f*width/640, 22.5f*height/360);
    if (punto && released) {
      g1.play = true;
      q[7][8].tantipunti();
      g1.play = false;
      bot2.play = true;
      Nmosse++;
    } else if (!punto && mouseY < height-(70*height/360)) {
      popup(parola[84]+"\n"+parola[96], false); //Splasha popup 'non fare il furbo'
    }
  } else if (Nmosse == 14) {
    image(square, 23*(7)*width/640, 23*10*height/360, 22.5f*width/640, 22.5f*height/360);
    popup(parola[85], true);
  } else if (Nmosse == 15) {
    bot2.liv10();
  } else if (Nmosse == 16) {
    popup(parola[91], true);
  } else if (Nmosse == 17) {
    boolean punto = mouseX >= (23*(qx-7))*width/640 && mouseX  <= (((23*(qx-7))*width/640) + 22.5f*width/640) && mouseY>= (23*6)*height/360 && mouseY  <= (((23*6)*height/360) + 22.5f*height/360);
    image(square, 23*(qx-7)*width/640, 23*6*height/360, 22.5f*width/640, 22.5f*height/360);
    g1.p1 = true;
    if (punto && released) {
      g1.play = true;
      swish(qx-8, 5);
      Nmosse++;
    } else if (!punto && mouseY < height-(70*height/360)) {
      popup(parola[84]+"\n"+parola[96], false); //Splasha popup 'non fare il furbo'
    }
  } else if ( Nmosse == 18) {
    boolean punto = mouseX >= (23*(3))*width/640 && mouseX  <= (((23*(3))*width/640) + 22.5f*width/640) && mouseY>= (23*10)*height/360 && mouseY  <= (((23*10)*height/360) + 22.5f*height/360);
    image(square, 23*(3)*width/640, 23*10*height/360, 22.5f*width/640, 22.5f*height/360);
    if (punto && released) {
      g1.play = true;
      q[2][9].tantipunti();
      g1.play = false;
      bot2.play = true;
      Nmosse++;
    } else if (!punto && mouseY < height-(70*height/360)) {
      popup(parola[84]+"\n"+parola[96], false); //Splasha popup 'non fare il furbo'
    }
  } else if (Nmosse == 19) {
    image(square, 23*(2)*width/640, 23*10*height/360, 22.5f*width/640, 22.5f*height/360);
    popup(parola[86], true);
  } else if (Nmosse == 20) {
    bot2.liv10();
  } else if (Nmosse == 21) {
    popup(parola[92], true);
  } else if (Nmosse == 22) {
    popup(parola[93], true);
  } else if (Nmosse == 23) {
    boolean punto = mouseX >= (23*(1))*width/640 && mouseX  <= (((23*(1))*width/640) + 22.5f*width/640) && mouseY>= (23*9)*height/360 && mouseY  <= (((23*9)*height/360) + 22.5f*height/360);
    image(square, 23*(1)*width/640, 23*9*height/360, 22.5f*width/640, 22.5f*height/360);
    if (punto && released) {
      g1.play = true;
      q[0][8].tantipunti();
      g1.play = false;
      bot2.play = true;
      Nmosse++;
    } else if (!punto && mouseY < height-(70*height/360)) {
      popup(parola[84]+"\n"+parola[96], false); //Splasha popup 'non fare il furbo'
    }
  } else if (Nmosse == 24) {
    popup(parola[94], true);
  } else if (Nmosse == 25) {
    bot2.liv10();
    bot2.ex = 150;
    bot2.punt += 150;
    primavolta = false;
    scene = "game over";
  } else if (Nmosse == 26) {
    primavolta = false;
    scene = "game over";
  }
}

public void popup(String up, boolean t) {
  image(popup, 0, height-(70*height/360), width, 70*height/360);
  fill(245);
  text(up, width/2, height-(45*height/360));
  textSize(15*width/640);
  if (t) {
    text(parola[95], width/2, height-(15*height/360));
    if (mouseY >= height-(70*height/360) && released) {
      //mouseY = mouseX = 0;
      Nmosse++;
    }
  }
}

/* */