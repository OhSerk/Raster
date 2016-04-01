public void atg() {
  if (q[0][q[0].length-1].colore == false) { //Il controllo viene fatto prima del gameover
    if (Ncpu + Ngioc == 4) { //Chi prende un 50% della griglia viene squalificato (non gioca pi\u00f9)
      if (g1.punt >= PApplet.parseInt(((qx*qy)*50)/100)) {
        g1.squalifica = true;
      }
      if (g2.punt >= PApplet.parseInt(((qx*qy)*50)/100)) {
        g2.squalifica = true;
      }
      if (g3.punt >= PApplet.parseInt(((qx*qy)*50)/100)) {
        g3.squalifica = true;
      }
      if (g4.punt >= PApplet.parseInt(((qx*qy)*50)/100)) {
        g4.squalifica = true;
      }
      if (bot2.punt >= PApplet.parseInt(((qx*qy)*50)/100)) {
        bot2.squalifica = true;
      }
      if (bot3.punt >= PApplet.parseInt(((qx*qy)*50)/100)) {
        bot3.squalifica = true;
      }
      if (bot4.punt >= PApplet.parseInt(((qx*qy)*50)/100)) {
        bot4.squalifica = true;
      }
    }
    if(Ncpu + Ngioc == 3){ //Chi prende un 60% della griglia viene ssqualificato (non gioca pi\u00f9)
      if (g1.punt >= PApplet.parseInt(((qx*qy)*60)/100)) {
        g1.squalifica = true;
      }
      if (g2.punt >= PApplet.parseInt(((qx*qy)*60)/100)) {
        g2.squalifica = true;
      }
      if (g3.punt >= PApplet.parseInt(((qx*qy)*60)/100)) {
        g3.squalifica = true;
      }
      if (bot2.punt >= PApplet.parseInt(((qx*qy)*60)/100)) {
        bot2.squalifica = true;
      }
      if (bot3.punt >= PApplet.parseInt(((qx*qy)*60)/100)) {
        bot3.squalifica = true;
      }
    }
    if(Ncpu + Ngioc == 2){ //Chi prende un 70% della griglia viene ssqualificato (non gioca pi\u00f9)
      if(g1.punt >= PApplet.parseInt(((qx*qy)*70)/100)){
        g1.squalifica = true;
        end = true;
      }
      if(g2.punt >= PApplet.parseInt(((qx*qy)*70)/100)){
        g2.squalifica = true;
        end = true;
      }
      if(bot2.punt >= PApplet.parseInt(((qx*qy)*70)/100)){
        bot2.squalifica = true;
        end = true;
      }
    }
  }//Chiudo condizione se il quadrato in basso a sinistra non \u00e8 ancora colorato
}