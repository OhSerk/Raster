public void statRl() { //Scelta del men\u00f9 iniziale
  if (frameCount > 200) {
    if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 35*height/360 && mouseY <= 55*height/360) {
      scene = "set play";
    } else if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 95*height/360 && mouseY <= 115*height/360) {
      scene = "options";
    } else if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 215*height/360 && mouseY <= 235*height/360 ) {
      scene = "HTP";
      mouseY = 0;
    } else if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 155*height/360 && mouseY <= 175*height/360) {
      scene = "storico";
    } else if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 275*height/360 && mouseY <= 295*height/360) {
      scene = "credit";
    } else if (mouseX >= 450*width/640 && mouseX <= 570*width/640 && mouseY >= 140*height/360 && mouseY <= 220*height/360) {
      link("https://www.youtube.com/channel/UC2BsuKWCjU1Tsgy3jO8iNLQ");
    } else if (mouseX >= 60*width/640 && mouseX <= 140*width/640 && mouseY >= 140*height/360 && mouseY <= 220*height/360) {
      link("https://www.facebook.com/Jarsick-672069209596438/");
    }
  }
}
public void storiRl() {
  if (mouseY <= 10*height/360+((PApplet.parseInt(230*height/360)/3)*3)) {
    if (NPar > sc && sc < 9) {
      sc++;
    } else if (NPar > sc && sc >=9 && sc+pag < NPar) {
      pag++;
    } else if (sc+pag == NPar) {
      pag = 0;
    }
  } else if (mouseX >= 240*width/640 && mouseX <= 360*width/640) {
    take = !take;
  } else if (mouseX >= 400*width/640 && mouseX <= 560*width/640) {
    phone = !phone;
  } else if ( mouseX <= 200*width/640) {
    for (int i = 0; i < NPar; i++) {
      String del = dataPath("Raster_"+i+".png");
      File f = new File(del);
      if (f.exists()) {
        f.delete();
      }
    }
    NPar = -1;
    sc = 0;
    scene = "menu";
  }
}
public void setPlayR() { //Controlla la quantit\u00e0 massima di giocatori in una partita

  if (mouseX <= 180*width/640 && mouseY >= 70*height/360 && mouseY <= 140*height/360) 
    modes = 1; //Scelgo la modalit\u00e0 di gioco
  else if (mouseX <= 180*width/640 && mouseY >= 160*height/360 && mouseY <= 230*height/360 && !primavolta)
    modes = 2;

  if (!primavolta) { //Incrementa / Decrementa numero e difficolt\u00e0 dei giocatori SOLO se non \u00e8 la prima volta che si gioca
    if (mouseX >= 240*width/640 && mouseX <= 270*width/640 && mouseY >= 100*height/360 && mouseY <= 130*height/360) {
      Ngioc--;
      if (Ngioc == 0)
        Ngioc = 4;
      if (Ngioc == 4 && Ncpu > 0)
        Ncpu = 0;
    }
    if (mouseX >= 370*width/640 && mouseX <= 400*width/640 && mouseY >= 100*height/360 && mouseY <= 130*height/360) {
      Ngioc++;
      if (Ngioc == 5)
        Ngioc = 1;
      if (Ngioc == 2 && Ncpu == 3)
        Ncpu--;
      else if (Ngioc == 3 && Ncpu == 2)
        Ncpu--;
      else if (Ngioc == 4 && Ncpu == 1)
        Ncpu--;
    }//(mouseX >= 355*width/640 && mouseX <= 415*width/640 && mouseY >= 305*height/360 && mouseY <= 329*height/360 && Ncpu != 0)
    if (mouseX >= 240*width/640 && mouseX <= 270*width/640 && mouseY >= 210*height/360 && mouseY <= 240*height/360) {
      Ncpu--;
      if (Ncpu < 0)
        Ncpu = 3;
      if (Ncpu == 3 && Ngioc > 1)
        Ngioc = 1;
    }
    if (mouseX >= 370*width/640 && mouseX <= 400*width/640 && mouseY >= 210*height/360 && mouseY <= 240*height/360) { 
      Ncpu++;
      if (Ncpu == 4)
        Ncpu = 0;
      if (Ngioc == 2 && Ncpu == 3)
        Ngioc--;
      else if (Ngioc == 3 && Ncpu == 2)
        Ngioc--;
      else if (Ngioc == 4 && Ncpu == 1)
        Ngioc--;
    }//(mouseX >= 255*width/640 && mouseX <= 315*width/640 && mouseY >= 305*height/360 && mouseY <= 329*height/360
    if (Ncpu != 0) {
      if (mouseX >= 235*width/640 && mouseX <= 295*width/640 && mouseY >= 305*height/360 && mouseY <= 329*height/360 && Ncpu != 0) {
        Lcpu--;
        if (Lcpu < 0)
          Lcpu = 3;
      }
      if (mouseX >= 355*width/640 && mouseX <= 415*width/640 && mouseY >= 305*height/360 && mouseY <= 329*height/360 && Ncpu != 0) {
        Lcpu++;
        if (Lcpu > 3)
          Lcpu = 0;
      }
    }
  } else {
    Lcpu = 10;
    Ncpu = 1;
    Ngioc = 1;
  }
  if (mouseX >= 490*width/640 && mouseY >= 120*height/360 && mouseX <= 570*width/640 && mouseY <= 220*height/360 && ((Ngioc >= 2) || (Ncpu > 0 && Ngioc > 0)) && modes != 0) {//  && ((Ncpu >= 1 && Lcpu <= 1) || (Ncpu == 0))) {
    if (modes == 1) {
      for (int lsd = 0; lsd < q.length; lsd++)
        for (int ii = 0; ii < q[0].length; ii++) {
          q[lsd][ii].zona = true;
          q[lsd][ii].posizione = false;
        }
    }
    if (!primavolta) {
      scene = "play";
    } else {
      Nmosse = mouseY = -1;
      scene = "p";
      music.pause();
      theme.pause();
    }
    aspetta = 0;
  }
}

public void primaRL() {
  bot2.exist = true;
  aspetta++;
}



public void assegna() { //Vado a dire quali giocatori partecipano alla partita (con la variabile booleana "exist")

  if (Ngioc == 1 && Ncpu == 1) { 
    bot2.exist = true;
  } else if (Ngioc == 2 && Ncpu == 1) {
    g2.exist = true;
    bot3.exist = true;
  } else if (Ngioc == 3 && Ncpu == 1) {
    g2.exist = true;
    g3.exist = true;
    bot4.exist = true;
  } else if (Ngioc == 1 && Ncpu == 2) {
    bot2.exist = true;
    bot3.exist = true;
  } else if (Ngioc == 1 && Ncpu == 3) {
    bot2.exist = true;
    bot3.exist = true;
    bot4.exist = true;
  } else if (Ngioc == 2 && Ncpu == 2) {
    g2.exist = true;
    bot3.exist = true;
    bot4.exist = true;
  } else if (Ngioc == 4 && Ncpu == 0) {
    g2.exist = true;
    g3.exist = true;
    g4.exist = true;
  } else if (Ngioc == 3 && Ncpu == 0) {
    g2.exist = true;
    g3.exist = true;
  } else if (Ngioc == 2 && Ncpu == 0) {
    g2.exist = true;
  }
  println(aspetta+" = aspetta");
  aspetta++;
}


public void selectRL() {
  //if (mouseX <= 70*width/640 && mouseY <= 70*height/360)
  // scene = "menu";
  if (mouseX >= 250*width/640 && mouseX <= 390*width/640 && mouseY >= 140*height/360 && mouseY <= 230*height/360) {
    /* if (language.equals("English"))
     language = "Italiano";
     else if (language.equals("Italiano"))
     language = "Deutsch";
     else if (language.equals("Deutsch"))
     language = "Shqip";
     else if (language.equals("Shqip"))
     language = "Espanol";
     else if (language.equals("Espanol"))
     language = "English";*/
  }

  if (mouseX >= 90*width/640 && mouseX <= 150*width/640 && mouseY >= 150*height/360 && mouseY <= 210*height/360) {
    if (curs == 0)
      curs = Flag.length-1;
    else 
    curs--;
  } 
  if (mouseX >= 470*width/640 && mouseX <= 530*width/640 && mouseY >= 150*height/360 && mouseY <= 210*height/360) {
    if (curs != Flag.length-1)
      curs++;
    else 
    curs = 0;
  }

  switch(curs) {
  case 0:
    language = "Italiano";
    break;
  case 1:
    language = "English";
    break;
  }
  // 320*width/640, 180*height/360, 210*width/640, 160*height/360
  if (mouseX >= 215*width/640 && mouseX <= 425*width/640 && mouseY >= 130*height/360 && mouseY <= 290*height/360) {
    scene = "menu";
    mouseY = mouseX = 0;
  }
}




public void oRL() { //Modifica le opzioni durante la partita
  if (backpressed == true) {
    if (mouseX >= 400*width/640 && mouseY >= 180*height/360 && mouseX <= 480*width/640 && mouseY <= 210*height/360) {
      reset();
    } else if (mouseX >= 180*width/640 && mouseY >= 180*height/360 && mouseX <= 260*width/640 && mouseY <= 210*height/360) {
      backpressed = false;
      aspetta = 0;
    }
  }
  if (mouseX >= 20*width/640 && mouseX <= 110*width/640 && mouseY >= 270*height/360 && mouseY <= 360*height/360 &&(!bot2.play && !bot3.play && !bot4.play )) {
    playopts =! playopts;
    for(int i = 0; i < qx; i++)
      for(int j = 0; j < qy; j++)
        q[i][j].posizione = false;
  }
  if (playopts == true) {
    if(mouseX >= 270*width/640 && mouseX <= 360*width/640 && mouseY >= 250*height/360 && mouseY <= 300*height/360){
       gameHelp = !gameHelp; 
    }
    if (mouseX >= 540*width/640 && mouseY >= 70*height/360 && mouseY <= 200*height/360) {  
      if (pg < 1)
        pg++;
      else
        pg = 0;
    }
    if (mouseX >= 90*width/640 && mouseX <= 170*width/640 && mouseY >= 90*height/360 && mouseY <= 170*height/360) {
      sf = 0+(5*pg);
      back = color(200);
    } else if (mouseX >= 180*width/640 && mouseX <= 260*width/640 && mouseY >= 90*height/360 && mouseY <= 170*height/360) {
      sf = 1+(5*pg);
      back = color(49, 64, 86);
    } else if (mouseX >= 270*width/640 && mouseX <= 350*width/640 && mouseY >= 90*height/360 && mouseY <= 170*height/360) {
      sf = 2+(5*pg);
      back = color(229, 141, 7);
    } else if (mouseX >= 360*width/640 && mouseX <= 440*width/640 && mouseY >= 90*height/360 && mouseY <= 170*height/360) {
      sf = 3+(5*pg);
      back = color(163, 105, 157);
    } else if (mouseX >= 450*width/640 && mouseX <= 530*width/640 && mouseY >= 90*height/360 && mouseY <= 170*height/360) {
      sf = 4+(5*pg);
      back = color(79, 18, 18);
    }
  }
}

public void optRl() { //Men\u00f9 di opzioni
  if (mouseX >= 500*width/640 && mouseX <= 600*width/640 && mouseY >= 15*height/360 && mouseY <= 85*height/360 && sound) {
    //soundTh =! soundTh;
    musicTh =! musicTh;
  }
  if (mouseX >= 500*width/640 && mouseX <= 600*width/640 && mouseY >= 105*height/360 && mouseY <= 155*height/360 && sound) {
    //soundMn =! soundMn;
    musicMn =! musicMn;
  }
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 40*height/360 && mouseY <= 90*height/360)
    sound =! sound; 
  else if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 120*height/360 && mouseY <= 170*height/360)
    effect =! effect;
  else if (mouseX <= 70*width/640 && mouseY <= 70*height/360)
    scene = "menu";
  if (mouseX >= 250*width/640 && mouseX <= 390*width/640 && mouseY >= 200*height/360 && mouseY <= 270*height/360) {
    if (language.equals("English"))
      language = "Italiano";
    else if (language.equals("Italiano"))
      /* language = "Deutsch";
       else if (language.equals("Deutsch"))
       language = "Shqip";
       else if (language.equals("Shqip"))
       language = "Espanol";
       else if (language.equals("Espanol"))*/
      language = "English";
  }
}

public void htpRL() { //Menu di tutorial
  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 65*height/360 && mouseY <= 85*height/360 && tutorial == 0) {
    tutorial = 1;
    sc = 0;
    conto = 0;
  } else if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 145*height/360 && mouseY <= 165*height/360 && tutorial == 0) {
    tutorial = 2;
    conto = 0;
    sc = 0;
  } else if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 225*height/360 && mouseY <= 245*height/360 && tutorial == 0) {
    tutorial = 3;
    mouseX = 700;
  } else if (tutorial != 0) {
    textAlign(CENTER);
    tutorial = 0;
  } else if (mouseX <= 70*width/640 && mouseY <= 70*height/360)
    scene = "menu";
}