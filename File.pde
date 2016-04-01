
public void caricamento() {

  if (language.equals("English")) {
    parola=loadStrings(english);
  } else if (language.equals("Italiano")) {
    parola=loadStrings(italiano);
  } else if (language.equals("Deutsch")) {
    parola=loadStrings(deutsch);
  } else if (language.equals("Shqip")) {
    parola=loadStrings(shqip);
  } else if (language.equals("Espanol")) {
    parola=loadStrings(espanol);
  }
}

public void salvataggio()
{
  //level = livello;
  //data[0]=nfc(level);
  //saveStrings(filename, data);
}
int ts;
public void contP5() {

  /*cp5.addSlider("sli")
    .setPosition(40*width/640, 200*height/360)   
    .setWidth(int(400*width/640))
    .setHeight(int(20*height/360))
    .setRange(1, 10) // values can range from big to small as well
    .setValue(sli)
    .setNumberOfTickMarks(10)
    .setVisible(false)
    ;*/
}