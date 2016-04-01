class Giocatori {
  boolean play, exist, squalifica;
  int RGB;
  int punt, vv; //punti partita e vittorie
  int ex;
  boolean p1, p2, p3, p4; //EX per i 150 punti finali, p1..4 sono i perks aggiuntivi al gioco
  Giocatori (boolean attivo, int cc, int punti, boolean vita, boolean sq) {
    play = attivo;
    RGB = cc;
    punt = punti;
    exist = vita;
    squalifica = sq;
  }
  public void po() {

    if (g1.play == true || g2.play == true || g3.play == true || g4.play == true) {
      playG = true;
      setThink = PApplet.parseInt(frameRate);
    } else if (g1.play == false && g2.play == false && g3.play == false && g4.play == false)
      playG = false;

    if (bot2.play == false && bot3.play == false && bot4.play == false)
      playB = false;
  }


  public void view() {
    textSize(16*width/640);

    if (play && (p1  || p2  || p3  || p4 )) {
      fill(240);
      rect(470*width/640, 290*height/360, 110*width/640, 50*height/360);
      fill(0);
      text(parola[61], 520*width/640, 320*height/360);
    }
  }
}