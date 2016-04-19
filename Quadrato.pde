class Quadrato {
  int px, py, codice, player;
  boolean active, colore, posizione, releaseds = false, zona;
  boolean segno = false, cont = false;
  float lax, lay, lux, luy;
  private float pos = 23, dim = 22;
  Quadrato(int posizioneX, int posizioneY, int numero, boolean attivo, int giocatore, boolean colorato, boolean area) {
    px = posizioneX; 
    py = posizioneY;
    codice = numero; //da 0 a 251;
    active = attivo; //Per vedere se \u00e8 stato gia attivato
    player = giocatore; //Indica quale giocatore ha colorato il quadretto
    colore = colorato; //se \u00e8 colorato o bianco
    zona = area; //Indica la possibile area in cui ci si pu\u00f2 muovere
  }

  public void view() { //Permette la visualizzazione della griglia
    // segno = false;
    if (!primavolta) {
      gioco();
    }
    colora();
    if (playopts == false) {
      strokeWeight(1);
      stroke(0);
      rect((pos*px)*width/640, (pos*py)*height/360, dim*width/640, dim*width/640);
      perks(ax, bx);// cx, dx);
    }
    //noStroke();
  }


  public void gioco() {
    if (IA == false && (g1.play || g2.play || g3.play || g4.play) ) { //Se gioca un utente
      if (mouseX >= (pos*px)*width/640 && mouseX  <= (((pos*px)*width/640) + dim*width/640)  && mouseY>= (pos*py)*height/360 && mouseY  <= (((pos*py)*height/360) + dim*height/360) && active == false) {
        for (int i = 0; i < qx; i++) 
          for (int j = 0; j < qy; j++) 
            q[i][j].posizione = false;
        posizione = true;
        cont = true;
        if (gameHelp)
          Giallo();
      } else if ((mouseX >= (pos*px)*width/640 && mouseX  <= (((pos*px)*width/640) + dim*width/640)  && mouseY>= (pos*py)*height/360 && mouseY  <= (((pos*py)*height/360) + dim*height/360) && active == true && touch == false && pU == true && !playopts)) {
        swish(px-1, py-1);
      } else if (mouseX >= (pos*q[0][q[0].length-1].px)*width/640 && mouseX  <= (((pos*q[0][q[0].length-1].px)*width/640) + dim*width/640)  && mouseY>= (pos*q[0][q[0].length-1].py)*height/360 && mouseY  <= (((pos*q[0][q[0].length-1].py)*height/360) + dim*height/360) && totale <= qx*qy-5) {
        q[0][q[0].length-1].posizione = false;
        q[0][q[0].length-1].active = false;
        q[0][q[0].length-1].zona = false;
        mouseX = 0;
        mouseY = 0;
      } else if (!segno) {   
        posizione = false;
        cont = false;
      } else {
        cont = false;
      }
    } else if (IA == true) { //Se gioca il PC
      if (toccoX >= (pos*px)*width/640 && toccoX  <= (((pos*px)*width/640) + dim*width/640)  && toccoY>= (pos*py)*height/360 && toccoY  <= (((pos*py)*height/360) + dim*height/360) && active == false) {
        posizione = true;
      } else if (toccoX >= (pos*q[0][q[0].length-1].px)*width/640 && toccoX  <= (((pos*q[0][q[0].length-1].px)*width/640) + dim*width/640)  && toccoY>= (pos*q[0][q[0].length-1].py)*height/360 && toccoY  <= (((pos*q[0][q[0].length-1].py)*height/360) + dim*height/360) && totale <= qx*qy-5) {
        // q[0][q[0].length-1].colore = false;   
        q[0][q[0].length-1].posizione = false;
        q[0][q[0].length-1].active = false;
      } else      posizione = false;
    }


    if (cont && zona && !backpressed  && !playopts && (playG  && !playB && posizione && !active && !touch && aspetta != 0) || (!playG && posizione && !active && IA )) { //Se \u00e8 stata confermata la posizione del tocco del giocatore
      if (effect) {
        splash.pause();
        splash.seekTo(0);
      }
      tantipunti();
      if (effect)
        splash.start();
      //if (pU == true)
      //lessPoints(true);
    } //Chiusura condizione rilascio

    if (q[0][q[0].length-1].colore == true && !scene.equals("game over")) { //ultimo quadretto del gioco
      /* if (FTIME == 2)
       FTIME = 3;*/
      if (q[0][q[0].length-1].player == 1) {
        g1.ex = 150;
        g1.punt += 151;
      } else if (q[0][q[0].length-1].player == 2) {
        if (g2.exist == true && bot2.exist == false) {
          g2.ex = 150;
          g2.punt += 151;
        } else if (g2.exist == false && bot2.exist == true) {
          bot2.ex = 150;
          bot2.punt += 151;
        }
      } else if (q[0][q[0].length-1].player == 3) {
        if (g3.exist == true && bot3.exist == false) {
          g3.ex = 150;
          g3.punt += 151;
        } else if (g3.exist == false && bot3.exist == true) {
          bot3.ex = 150;
          bot3.punt += 151;
        }
      } else if (q[0][q[0].length-1].player == 4) {
        if (g4.exist == true && bot4.exist == false) {
          g4.ex = 150;
          g4.punt += 151;
        } else if (g4.exist == false && bot4.exist == true) {
          bot4.ex = 150;
          bot4.punt += 151;
        }
      }
      q[0][q[0].length-1].player = 5;
    }
  }

  public void Giallo() {
    for (int i = px-1; i < qx; i++) 
      for (int j = 0; j < py; j++) {
        q[i][j].posizione = true;
        q[i][j].segno = true;
      }
  }

  public void colora() {
    if (colore == false && posizione == false && zona == false && modes == 2 ) 
      fill(245);
    else if (colore == false && posizione == false && zona == true && modes == 1 )
      fill(245);
    else if (colore == false && posizione == false && zona == true && modes == 2) 
      fill(255, 0, 255);
    else if (colore == true && player == 1 && g1.squalifica == false)
      fill(g1.RGB);
    else if (colore == true && player == 1 && g1.squalifica == true) 
      fill(100);
    else if (colore == true && player == 2 && g2.squalifica == false && bot2.squalifica == false) 
      fill(g2.RGB);
    else if (colore == true && player == 2 && (g2.squalifica == true || bot2.squalifica == true)) 
      fill(100);
    else if (colore == true && player == 3 && g3.squalifica == false && bot3.squalifica == false) 
      fill(g3.RGB);
    else if (colore == true && player == 3 && (g3.squalifica == true || bot3.squalifica == true)) 
      fill(100);
    else if (colore == true && player == 4 && g4.squalifica == false && bot4.squalifica == false) 
      fill(g4.RGB);
    else if (colore == true && player == 4 && (g4.squalifica == true || bot4.squalifica == true))
      fill(100);
    else if ((posizione == true) && colore == false) {
      fill(255, 255, 0);
    }

    if (q[0][q[0].length-1].colore == false)
      q[0][q[0].length-1].player = 6;

    if (colore == false && player == 6) {
      fill(R, G, B); 
      superQ();
    }
  }

  public void superQ() {
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
  }

  public void perks(int swx, int swy) {//, int mnx, int mny) {
    if (totale >= (qx*qy)-(qy*4) && bot2.play == false && bot3.play == false && bot4.play == false && playopts == false) {
      if (q[swx][swy].colore == false)
        image(SW, (pos*q[swx][swy].px)*width/640, (pos*q[swx][swy].py)*height/360, dim*width/640, dim*width/640);
      //if (q[mnx][mny].colore == false)
      //    image(MN, (23*q[mnx][mny].px)*width/640, (23*q[mnx][mny].py)*height/360, 22.5*width/640, 22.5*width/640);
    }
  }

  public void tantipunti() {
    for (int o = py-1; o >= 0; o--) {
      for (int z = px-1; z < q.length; z++) {
        q[z][o].colore = true;
        if (q[z][o].colore == true && q[z][o].active == false) {
          if (g1.play == true && g2.play == false && g3.play == false && g4.play == false && bot2.play == false && bot3.play == false && bot4.play == false) {
            g1.punt++;
            q[z][o].player = 1;
            if (ax == z && bx == o && totale >= (qx*qy)-(qy*2)) {
              g1.p1 = true;
            }
            //if (cx == z && dx == o && totale >= 230) {
            //  g1.p2 = true;
            //}
          } else if (g1.play == false && (g2.play == true || bot2.play == true) && g3.play == false && g4.play == false && bot3.play == false && bot4.play == false) {
            if (g2.exist == true && bot2.exist == false)      //aumento i punti del secondo player o del PC
              g2.punt++;
            else if (g2.exist == false && bot2.exist == true) 
              bot2.punt++;
            q[z][o].player = 2;
            if (ax == z && bx == o && totale >= (qx*qy)-(qy*2)) {
              g2.p1 = true;
            }
            //if (cx == z && dx == o && totale >= 230) {
            //  g2.p2 = true;
            //}
          } else if (g1.play == false && g2.play == false && (g3.play == true || bot3.play == true) && g4.play == false && bot2.play == false  && bot4.play == false) {
            if (g3.exist == true && bot3.exist == false)      //aumento i punti del terzo player o del PC
              g3.punt++;
            else if (g3.exist == false && bot3.exist == true)
              bot3.punt++;
            q[z][o].player = 3;
            if (ax == z && bx == o && totale >= (qx*qy)-(qy*2)) {
              g3.p1 = true;
            }
            //if (cx == z && dx == o && totale >= 230) {
            //  g3.p2 = true;
            //}
          } else if (g1.play == false && g2.play == false && g3.play == false && bot2.play == false && bot3.play == false && (g4.play == true || bot4.play == true)) {
            if (g4.exist == true && bot4.exist == false)      //aumento i punti del quarto player o del PC
              g4.punt++;
            else if (g4.exist == false && bot4.exist == true)
              bot4.punt++;
            q[z][o].player = 4;
            if (ax == z && bx == o && totale >= (qx*qy)-(qy*2)) {
              g4.p1 = true;
            }
            //if (cx == z && dx == o && totale >= 230) {
            //  g4.p2 = true;
            //}
          }

          q[z][o].active = true;
        }
      } //chiusura secondo for
    } //chiusura primo for
    IA = false;
  }
}