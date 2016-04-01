int pg = 0;
public void PlayOpt() {
  if (back == color(0) || back == color(79, 18, 18) || back == color(49, 64, 86))
    fill(200);
  else
    fill(0);
  textSize (18*height/360);
  text(parola[33], 320*width/640, 40*height/360);
  // text(parola[34]+int(setThink/int(frameRate)), 90*width/640, 255*height/360);
  if (gameHelp)
    fill(0, 255, 0);
  else fill(255, 0, 0);
  if (mouseX >= 270*width/640 && mouseX <= 360*width/640 && mouseY >= 250*height/360 && mouseY <= 300*height/360 && mousePressed)
    textSize(24*height/360);
  text(parola[97], 320*width/640, 270*height/360);
  stroke(0);
  fill(200);
  rect(90*width/640, 90*height/360, 80*width/640, 80*width/640);
  fill(49, 64, 86);
  rect(180*width/640, 90*height/360, 80*width/640, 80*width/640);
  fill(229, 141, 7);
  rect(270*width/640, 90*height/360, 80*width/640, 80*width/640);
  fill(163, 105, 157);
  rect(360*width/640, 90*height/360, 80*width/640, 80*width/640);
  fill(79, 18, 18);
  rect(450*width/640, 90*height/360, 80*width/640, 80*width/640); 
  //cp5.getController("")
  //  .setVisible(true)
  //;
 // setThink = int(frameRate);
}



public void opzioni() { //Scritte presenti nel men\u00f9 di opzioni
  background(75);
  if (mouseX <= 70*width/640 && mouseY <= 70*height/360) {
    fill(255, 0, 0);
    textSize (18*height/360);
  } else {
    fill(200);
    textSize (15*height/360);
  }
  text(parola[28], 50*width/640, 50*height/360); //Back
  textSize (20*height/360);

  int L;
  if (parola[29].length() % 2 == 0)
    L = 1;
  else
    L = 0;
  if (sound == false)
    fill(20);
  else if (!musicMn && !musicTh && sound)
    fill(130);
  else if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 40*height/360 && mouseY <= 90*height/360 && sound == true && (soundMn || musicTh)) {
    fill(255, 0, 0);
    textSize (24*height/360);
    if (musicTh)
      line(320*width/640+(((parola[29].length()/2)-L)*20*height/360), 75*height/360, 515*width/640, 30*height/360);
    if (musicMn)
      line(320*width/640+(((parola[29].length()/2)-L)*20*height/360), 75*height/360, 515*width/640, 120*height/360);
  } else {
    fill(200);
    stroke(230);
    if (musicTh)
      line(320*width/640+(((parola[29].length()/2)-L)*20*height/360), 75*height/360, 515*width/640, 30*height/360);
    if (musicMn)
      line(320*width/640+(((parola[29].length()/2)-L)*20*height/360), 75*height/360, 515*width/640, 120*height/360);
  }
  text(parola[29], 320*width/640, 80*height/360); //Musica

  textSize (19*height/360);
  textAlign(LEFT);
  if (sound == false || musicTh == false)
    fill(20);
  else if (mouseX >= 500*width/640 && mouseX <= 600*width/640 && mouseY >= 15*height/360 && mouseY <= 85*height/360 && sound && soundTh) {
    fill(255, 0, 0);
    textSize (24*height/360);
  } else fill(200);
  text(parola[59], 520*width/640, 35*height/360);  //Menu

  textSize (19*height/360);
  if (sound == false || musicMn == false)
    fill(20);
  else if (mouseX >= 500*width/640 && mouseX <= 600*width/640 && mouseY >= 105*height/360 && mouseY <= 155*height/360 && sound && soundMn) {
    fill(255, 0, 0);
    textSize (24*height/360);
  } else fill(200);
  text(parola[60], 520*width/640, 125*height/360); //Gioco

  textAlign(CENTER);
  textSize (20*height/360);
  if (effect == false)
    fill(20);
  else if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 120*height/360 && mouseY <= 170*height/360 && effect == true) {
    fill(255, 0, 0);
    textSize (24*height/360);
  } else
    fill(200);
  text(parola[30], 320*width/640, 160*height/360); //Effetti

  textSize (20*height/360);
  if (mouseX >= 250*width/640 && mouseX <= 390*width/640 && mouseY >= 200*height/360 && mouseY <= 270*height/360) {
    fill(255, 0, 0);
    textSize (24*height/360);
  } else
    fill(200);
  text(parola[31]+"\n"+language, 320*width/640, 240*height/360); //Lingua
}