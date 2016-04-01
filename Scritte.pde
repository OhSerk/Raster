public void scritte() {
  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 35*height/360 && mouseY <= 55*height/360) {
    fill(255, 0, 0);
    textSize (25*height/360);
  } else {
    fill(80);
    textSize (18*height/360);
  }

  text(parola[22], 320*width/640, 50*height/360); //Gioca

  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 95*height/360 && mouseY <= 115*height/360) {
    fill(255, 0, 0);
    textSize (25*height/360);
  } else {
    fill(80);
    textSize (18*height/360);
  }
  text(parola[23], 320*width/640, 110*height/360); //Opzioni
  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 155*height/360 && mouseY <= 175*height/360) {
    fill(255, 0, 0);
    textSize (25*height/360);
  } else {
    fill(80);
    textSize (18*height/360);
  }
  text(parola[24], 320*width/640, 170*height/360); //Storico


  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 215*height/360 && mouseY <= 235*height/360) {
    fill(255, 0, 0);
    textSize (25*height/360);
  } else {
    fill(80);
    textSize (18*height/360);
  }
  text(parola[25], 320*width/640, 230*height/360); //Come si gioca



  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 275*height/360 && mouseY <= 295*height/360) {
    fill(255, 0, 0);
    textSize (25*height/360);
  } else {
    fill(80);
    textSize (18*height/360);
  }
    text(parola[26], 320*width/640, 290*height/360);
  fill(255);
  textSize (18*height/360);
  //  text(parola[27], 80*width/640, 340*height/360);
}