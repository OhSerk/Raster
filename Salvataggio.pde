public boolean existing(String fname) {
  File file= getBaseContext().getFileStreamPath(fname);
  return file.exists();
}

public void lettura() {

  try {
    FileInputStream fin = openFileInput(FILENAME);
    int c;
    //println(input+" Prima del ciclo");
    while ((c=fin.read()) != -1) 
      input=input+Character.toString((char)c);
    //println(input+" Dopo");
    fin.close();
  } 
  catch(Exception e) {
    e.printStackTrace();
  }
  if (!input.equals("prova")) { 
    list = split(input, parseChar(10));
    sli = PApplet.parseInt(list[0]);
    language = list[1];
    musicMn = PApplet.parseBoolean(list[2]);
    musicTh = PApplet.parseBoolean(list[3]);
    sound = PApplet.parseBoolean(list[4]);
    effect = PApplet.parseBoolean(list[5]);
    back = color(PApplet.parseInt(list[6]));
    NPar = PApplet.parseInt(list[7]);
    take = PApplet.parseBoolean(list[8]);
    phone = PApplet.parseBoolean(list[9]);
    primavolta = PApplet.parseBoolean(list[10]);
    gameHelp = PApplet.parseBoolean(list[11]);
  }
}

public void scrittura(String obj) {
  try {
    FileOutputStream fout = openFileOutput(FILENAME, Context.MODE_PRIVATE);
    fout.write(obj.getBytes());
    fout.close();
  } 
  catch(Exception e) {
    e.printStackTrace();
  }
}

public void creaFileVuoto() {
  String[] stringa = {""};
  saveStrings("//sdcard/Raster/Dati.jsk", stringa);
}

public void screenshot() {
  screen = createGraphics(PApplet.parseInt(575*width/640), PApplet.parseInt(230*height/360), JAVA2D);
  screen.beginDraw();
  screen.textAlign(CENTER, CENTER);
  screen.background(back);
  if (totale >= (qx*qy)-1)
    q[0][qy-1].colore = true;
  for (a = 0; a < q.length; a++) {
    for (b = 0; b < q[0].length; b++) {
      if (q[a][b].player == 1)
        screen.fill(g1.RGB);
      else if (q[a][b].player == 2)
        screen.fill(g2.RGB);
      else if (q[a][b].player == 3)
        screen.fill(g3.RGB);
      else if (q[a][b].player == 4)
        screen.fill(g4.RGB);
      else if (!q[a][b].colore)
        screen.fill(245);
      screen.rect((23*a)*width/640, (23*b)*height/360, 22.5f*width/640, 22.5f*width/640);
    }
  }
  screen.endDraw();
}

public void savePhoto() {
  if (take) {
    NPar++;
    String nome = "//sdcard/Raster/Raster_"+hour()+"_"+day()+"_"+year()+"_"+minute()+".png";
    screen.save("Raster_"+NPar+".png");
    if (phone)
      screen.save(nome);
  }
}

public void loadScreen() {
  if (NPar >= 0 && NPar < 1000) {
    for (int i = 0; i < NPar+1; i++) {
      if (IMG[i] == null)
        IMG[i] = loadImage("Raster_"+i+".png"); //carico gli screenshot
    }
  }
}