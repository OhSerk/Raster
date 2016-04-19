import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.lang.Object;
import java.lang.reflect.*;
import java.lang.Iterable;
import java.util.*;
import java.io.File;
//Android
import android.os.Parcelable.Creator;
import apwidgets.*;
import android.content.*;
import android.os.Bundle;
import android.view.WindowManager;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
//final int REQUEST_ENABLE_BT = 4;
PGraphics screen;
PImage popup, square;// load[] = new PImage[7];
//ControlP5 cp5;
Activity activity;// = this.getActivity();
Context context;// = new Context();
//Slider abc;
APMediaPlayer theme, music, splash;
//Theme per il menu, music per il gioco:
Quadrato[][] q = new Quadrato[25][10];
int qx = q.length, qy = q[0].length;
Giocatori g1 = new Giocatori(true, color(0, 255, 0), 0, true, false);
Giocatori g2 = new Giocatori(false, color(255, 0, 0), 0, false, false);
Giocatori g3 = new Giocatori(false, color(0, 0, 255), 0, false, false);
Giocatori g4 = new Giocatori(false, color(0, 255, 255), 0, false, false);
CPU bot2 =     new CPU(false, color(255, 0, 0), 0, false, false);
CPU bot3 =     new CPU(false, color(0, 0, 255), 0, false, false);
CPU bot4 =     new CPU(false, color(0, 255, 255), 0, false, false);
String[] list;// = new String[30]; //Lista di dati per il salvataggio 
boolean playG, playB;
int i, a=0, b= 1, r, sf = 0, sc; // indici;
int totale, G1, G2, G3, G4, BOT2, BOT3, BOT4, aspetta; //Variabile relative ai punteggi
int Ngioc = 1, Ncpu, Lcpu, think, setThink = 60;
//float dw, dh;
PImage OP, RT, SF, SW, MN, Logo, Face, Games, CC, info, play, icon, YouTube, Flag[] = new PImage[2];
int back = color(49, 64, 86);
//PImage[] sfondo = new PImage[5];
float R, G, B; //Colori per il quadrato finale
PImage[] IMG = new PImage[1000];
String data[], parola[], carica, italiano = "italiano.txt", english="english.txt", deutsch = "deutsch.txt", shqip = "shqip.txt", espanol = "espanol.txt";
boolean touch, released, IA = false, playopts = false, end = false, posto = false, backpressed = false;
boolean sound = true, effect=true, musicTh = true, musicMn = true, soundTh = true, soundMn = true; //Variabili per le opzioni di "musica"
boolean pU = false;
boolean gameHelp = true;
int sli = 3; //Valore della slider per il tempo di risposta della CPU
String scene, difficolta, victory, language = "English"; //Scene serve per muoversi tra i menu //Difficolt\u00e0 serve per la difficolt\u00e0 della CPU //Victory difinisce il vincitore delle partite //Language... beh
String FILENAME = "R"; //nome del file
String string = "prova"; //Al primo avvio
String input=""; 
String realString="";
long NPar = -1; //Conta il numero della partita
boolean primavolta; //Per la prima vota che si apre il gioco
//int FTIME = 0;
int bP = 0;
public void settings() {
  orientation(LANDSCAPE);
  fullScreen(P2D);
  theme= new APMediaPlayer(this);
  theme.setMediaFile("men.mp3");
  theme.setLooping(true);
  theme.setVolume(1.0f, 1.0f);  

  music=new APMediaPlayer(this);
  music.setMediaFile("pia.mp3");
  music.setLooping(true);
  music.setVolume(0.3f, 0.3f);

  splash= new APMediaPlayer(this);
  splash.setMediaFile("lol.mp3");
  splash.setLooping(false);
  splash.setVolume(1.0f, 1.0f);
}

public void setup() {

  //size(displayWidth, displayHeight);
  // context = new Context();
  //activity = this.getActivity();
  //cp5 = new ControlP5(this);
  primavolta = true;
  creaFileVuoto();
  if (displayWidth >= 640 ||displayHeight >= 360)
    Logo = loadImage("logo.png");
  else if (displayWidth < 640 ||displayHeight < 360)
    Logo = loadImage("Slogo.png");
  background(0);
  image(Logo, displayWidth/4, 0, displayHeight, displayHeight);
  println(displayWidth+" - "+displayHeight); 
  //Salvataggio {
  if (existing(FILENAME)==true) {
    lettura();
  } else {
    scrittura(string);
    lettura();
  }
  sli = 1;   


  if (displayWidth >= 640 ||displayHeight >= 360) {
    OP = loadImage("Options.png");
    RT = loadImage("Return.png");
    SF = loadImage("Semaforo.png");
    SW = loadImage("Scambio.png");
    Face = loadImage("facebook.png");
    CC = loadImage("Git.png");
    info = loadImage("I.png");
    Games = loadImage("games.png");
    icon = loadImage("iconfinder.png");
    play = loadImage("play.png");
    YouTube = loadImage("you.png");
  } else {
    OP = loadImage("SOptions.png");
    RT = loadImage("SReturn.png");
    SF = loadImage("SSemaforo.png");
    SW = loadImage("SScambio.png");
    Face = loadImage("Sfacebook.png");
    CC = loadImage("SGit.png");
    info = loadImage("SI.png");
    Games = loadImage("Sgames.png");
    icon = loadImage("Siconfinder.png");
    play = loadImage("Splay.png");
    YouTube = loadImage("Syou.png");
  }
  popup = loadImage("10.png");
  square = loadImage("11.png");
  Flag[0] = loadImage("IT.png");
  Flag[1] = loadImage("UK.png");
  int c = 0;
  for (i = 0; i < q.length; i++) 
    for (int ii = 0; ii < q[0].length; ii++) {
      q[i][ii]  = new Quadrato(i+1, ii+1, c, false, 0, false, false);
      c++;
    }
  //println(width/640 + " " + height/360);
  //println(displayWidth + " " + displayHeight);
  reset();
  caricamento(); //Da modificare in "FILE"
  textAlign(CENTER);
  //contP5(); //In "File"
  if (input.equals("prova")) {
    scene = "lingua";
  }
  //primavolta = false;
}
public void draw() {
  if (frameCount > 200)
    background(200);

  g1.po();
  if ( g1.p1 == true || g2.p1 == true || g3.p1 == true || g4.p1 == true ) //Controllo se almeno un giocatore ha attivato il perk!
    pU = true;
  else pU = false;
  if (q[c1][C1].colore  && q[c3][C3].colore  && q[c5][C5].colore  && q[c7][C7].colore  && q[c9][C9].colore )
    posto = true;
  else if (q[c1][C1].colore == false && q[c3][C3].colore  && q[c5][C5].colore  && q[c7][C7].colore  && q[c9][C9].colore )
    posto = true;
  else if (q[c1][C1].colore  && q[c3][C3].colore == false && q[c5][C5].colore  && q[c7][C7].colore  && q[c9][C9].colore )
    posto = true;
  else if (q[c1][C1].colore  && q[c3][C3].colore  && q[c5][C5].colore == false && q[c7][C7].colore  && q[c9][C9].colore )
    posto = true;
  else if (q[c1][C1].colore  && q[c3][C3].colore  && q[c5][C5].colore  && q[c7][C7].colore == false && q[c9][C9].colore )
    posto = true;
  else if (q[c1][C1].colore  && q[c3][C3].colore  && q[c5][C5].colore  && q[c7][C7].colore  && q[c9][C9].colore == false)
    posto = true;
  else
    posto = false;

  suoneria(); 




  totale = g1.punt+g2.punt+g3.punt+g4.punt+bot2.punt+bot3.punt+bot4.punt;
  if (q[0][q[0].length-1].colore == true || end == true)
    scene = "game over";
  if (scene.equals("lingua") && frameCount > 200) {
    caricamento();
    selectLingua();
  } else if (scene.equals("p")) {
    primaparte();
  } else if (scene.equals("play")) {
    TurniGiocatori();
    justplay();
    if (aspetta == 0) {
      mossa();
      aspetta = 1;
    }
  } else if (scene.equals("menu") && frameCount > 200) {
    textAlign(CENTER);
    scena();
    scritte();
  } else if (scene.equals("set play")) {
    setPlay();
  } else if (scene.equals("credit")) {
    credits();
  } else if (scene.equals("game over")) {
    // println(q[0][q[0].length-1].player);
    gameover();
  } else if (scene.equals("options")) {
    caricamento();
    opzioni();
  } else if (scene.equals("HTP")) {
    htp();
  } else if (scene.equals("multi")) {
    provaBt();
  } else if (scene.equals("storico")) {
    storico();
  }

  if (touch == false)
    released = false;
  touch = true;

  println(frameRate);

  //println(frameRate);
  //fill(255);
  //ellipse(640*width/640, 360*height/360, 20, 20);
}

public void mousePressed() {
  touch = true;
  released = false;
}

public void mouseReleased() {
  G1 = g1.punt;
  G2 = g2.punt;
  G3 = g3.punt;
  G4 = g4.punt;
  BOT2 = bot2.punt;
  BOT3 = bot3.punt;
  BOT4 = bot4.punt;
  if (scene.equals("menu") && frameCount > 200)
    statRl(); //Scelta del men\u00f9 iniziale
  else if (scene.equals("p")) {
    if (aspetta <= 2)
      primaRL();
  } else if (scene.equals("credit"))
    creditRL();
  else if (scene.equals("storico"))
    storiRl();
  else if (scene.equals("set play")) {
    setPlayR(); //Controlla la quantit\u00e0 massima di giocatori in una partita
    mouseX = 6400;
    mouseY = 3200;
  } else if (scene.equals("play")) {
    if (aspetta <= 2)
      assegna(); //Vado a dire quali giocatori partecipano alla partita (con la variabile booleana "exist")
    oRL(); //Modifica le opzioni durante la partita
  } else if (scene.equals("options")) {
    optRl(); //Men\u00f9 di opzioni
    mouseX = 6400;
    mouseY = 3200;
  } else if (scene.equals("HTP")) {
    htpRL(); //Menu di tutorial
  } else if ( scene.equals("lingua")) {
    selectRL(); //Menu inziale per la prima volta che si gioca
  }
  if (!victory.equals("")) { //Faccio ricominciare la partita
    if (mouseX >= 200*width/640 && mouseY >= 135*height/360 && mouseX <= 450*width/640 && mouseY <= 215*height/360 && !primavolta) {
      screenshot();
      savePhoto();
      reset();
    } else if (mouseY >= 345*height/360)
      link("https://play.google.com/store/search?q=pub%3AJarsick");

    if (mouseX >= 270*width/640 && mouseX <= 370*width/640 && mouseY >= 250*height/360 && mouseY <= 300*height/360)
      take = !take;
  }
  if (touch == true) {
    released = true;
    touch = false;
  }
}

public void griglia() {
  //fill(0);
  //rect( 23*width/640, 23*height/360, 575*width/640, 230*height/360); 
  for (a = 0; a < q.length; a++)
    for (b = 0; b < q[0].length; b++)
      q[a][b].view();
}






int modes = 1;
public void setPlay() { //IMPOSTAZIONE DELLA PARTITA
  fill(60);
  textSize (18*height/360);
  text(parola[9], 320*width/640, 30*height/360);
  fill(100);
  textSize(15*height/360);
  text(parola[10], 320*width/640, 80*height/360);
  text(parola[11], 320*width/640, 190*height/360); 
  line(200*width/640, 0, 200*width/640, displayHeight); //Divide la scelta della modalit\u00e0 di gioco dal numero di giocatori
  text(parola[56], 100*width/640, 30*height/360);
  if (modes == 1) {
    fillmode();

    for (int i = 10; i < 24; i++) {
      if (parola[57].length()*i*width/640 >= 150*width/640) {
        textSize((i-1)*2*width/640);
        println((i-1)*2);
        break;
      }
    }
    //textSize (24*height/360);
  } else { 
    fill(100); 
    textSize(15*height/360);
  }
  text(parola[57], 100*width/640, 120*height/360); //Modalit\u00e0 1
  if (modes == 2 && !primavolta) { 
    fillmode();
    for (int i = 10; i < 24; i++) { 
      if (parola[58].length()*i*width/640 >= 150*width/640) {
        textSize((i-1)*2*width/640);
        println((i-1)*2);
        break;
      }
    }
    //textSize (24*height/360);
  } else { 
    fill(100); 
    textSize(15*height/360);
  }
  if (!primavolta)
    text(parola[58], 100*width/640, 200*height/360); //Modalit\u00e0 2
  if (modes == 3) { 
    fill(200, 0, 0); 
    textSize (18*height/360);
  } else { 
    fill(100); 
    textSize(15*height/360);
  }
  //text("?", 100*width/640, 280*height/360);
  textSize(25*width/640);
  if (!primavolta) {
    if (mouseX >= 240*width/640 && mouseX <= 270*width/640 && mouseY >= 100*height/360 && mouseY <= 130*height/360) fill(0, 130, 130); 
    else fill(255);
    text("<", 255*width/640, 120*height/360);
    if (mouseX >= 370*width/640 && mouseX <= 400*width/640 && mouseY >= 100*height/360 && mouseY <= 130*height/360) fill(0, 130, 130); 
    else fill(255);
    text(">", 385*width/640, 120*height/360);
    if (mouseX >= 240*width/640 && mouseX <= 270*width/640 && mouseY >= 210*height/360 && mouseY <= 240*height/360) fill(0, 130, 130); 
    else fill(255);
    text("<", 255*width/640, 230*height/360);
    if (mouseX >= 370*width/640 && mouseX <= 400*width/640 && mouseY >= 210*height/360 && mouseY <= 240*height/360) fill(0, 130, 130); 
    else fill(255);
    text(">", 385*width/640, 230*height/360);
  }
  if (Ngioc == 1) fill(g1.RGB); 
  else if (Ngioc == 2) fill(g2.RGB); 
  else if (Ngioc == 3) fill(g3.RGB);  
  else if (Ngioc == 4) fill(g4.RGB);
  text(Ngioc, 320*width/640, 125*height/360);
  if (Ncpu == 1 && Ngioc == 1) fill(bot2.RGB); 
  else if (Ncpu == 2 && Ngioc == 1) fill(bot3.RGB); 
  else if (Ncpu == 3 && Ngioc == 1) fill(bot4.RGB); 
  else if (Ncpu == 1 && Ngioc == 2) fill(bot3.RGB); 
  else if (Ncpu == 2 && Ngioc == 2) fill(bot4.RGB); 
  else if (Ncpu == 1 && Ngioc == 3) fill(bot4.RGB); 
  else if (Ncpu == 0) fill(50);
  text(Ncpu, 320*width/640, 235*height/360);
  textSize(15*width/640);
  if (Ncpu != 0 && !primavolta) {
    if (mouseX >= 255*width/640 && mouseX <= 315*width/640 && mouseY >= 305*height/360 && mouseY <= 329*height/360 && Ncpu != 0) fill(0, 130, 130); 
    else fill(255);
    text("<", 240*width/640, 320*height/360);
    if (mouseX >= 355*width/640 && mouseX <= 415*width/640 && mouseY >= 305*height/360 && mouseY <= 329*height/360 && Ncpu != 0) fill(0, 130, 130); 
    else fill(255);
    text(">", 400*width/640, 320*height/360);
    fill(100);
    textSize(17*width/640);
    text(parola[12], 320*width/640, 280*height/360);
    difficolta = parola[13+Lcpu]; //Da riga 14 a riga 17 del file di testo
    if (parola[13+Lcpu].charAt(0) == '*')
      fill(255, 0, 0);
    else if ( parola[13+Lcpu].charAt(0) != '*')
      fill(0, 0, 0);
    text(difficolta, 320*width/640, 320*height/360);
  }
  if (((Ngioc >= 2) || (Ncpu > 0 && Ngioc >= 1)) && modes != 0) {// && ((Ncpu >= 1 && Lcpu <= 1) || (Ncpu == 0))) {
    textSize(24*height/360);
    fill(0);
    if (mouseX >= 490*width/640 && mouseY >= 120*height/360 && mouseX <= 570*width/640 && mouseY <= 220*height/360)
      image(play, 455*width/640, 105*height/360, 130*width/640, 130*height/360);
    else
      image(play, 470*width/640, 120*height/360, 100*width/640, 100*height/360); //Immagine di play
    text(parola[17], 520*width/640, 80*height/360); //Stampa la parola play!
  }
}


public void punteggi() {
  fill (g1.RGB);
  text (g1.punt-g1.ex, 30*width/640, 20*height/360);
  text ("+"+g1.ex, 60*width/640, 45*height/360);
  fill (g2.RGB);
  if (g2.exist == true && bot2.exist == false) {
    text (g2.punt-g2.ex, 180*width/640, 20*height/360);
    text ("+"+g2.ex, 210*width/640, 45*height/360);
  } else if ( g2.exist == false && bot2.exist == true) {
    text (bot2.punt-bot2.ex, 180*width/640, 20*height/360);
    text ("+"+bot2.ex, 210*width/640, 45*height/360);
  }
  fill (g3.RGB);
  if (g3.exist == true && bot3.exist == false) {
    text (g3.punt-g3.ex, 330*width/640, 20*height/360);
    text ("+"+g3.ex, 360*width/640, 45*height/360);
  } else if ( g3.exist == false && bot3.exist == true) {
    text (bot3.punt-bot3.ex, 330*width/640, 20*height/360);
    text ("+"+bot3.ex, 360*width/640, 45*height/360);
  }
  fill (g4.RGB);
  if (g4.exist == true && bot4.exist == false) {
    text (g4.punt-g4.ex, 480*width/640, 20*height/360);
    text ("+"+g4.ex, 510*width/640, 45*height/360);
  } else if ( g4.exist == false && bot4.exist == true) {
    text (bot4.punt-bot4.ex, 480*width/640, 20*height/360);
    text ("+"+bot4.ex, 510*width/640, 45*height/360);
  }
}






public void reset() {
  realString=str(sli)+parseChar(10)+
    language+parseChar(10)+
    str(musicMn)+parseChar(10)+
    str(musicTh)+parseChar(10)+
    str(sound)+parseChar(10)+
    str(effect)+parseChar(10)+
    str(back)+parseChar(10)+
    str(NPar)+parseChar(10)+
    str(take)+parseChar(10)+
    str(phone)+parseChar(10)+
    str(primavolta)+parseChar(10)+
    str(gameHelp)
    ; //Mi salva la scelta di avere o meno la musica
  scrittura(realString);
  mouseX = mouseY = 9990;
  scene = "menu"; 
  G1 = G2 = G3 = G4 = BOT2 = BOT3 = BOT4 = a = aspetta = i = r = Lcpu = g1.punt = g2.punt = g3.punt = g4.punt = bot2.punt = bot3.punt = bot4.punt = 
    bot2.ex = bot3.ex = bot4.ex = g1.ex = g2.ex = g3.ex = g4.ex = 0;
  b = 1;
  R = G = B = 20;
  IA = end = touch = released = g2.exist = g3.exist = g4.exist = bot2.exist = bot3.exist = bot4.exist = g2.play = g3.play = g4.play = bot2.play = bot3.play = bot4.play =
    g1.squalifica = g2.squalifica = g3.squalifica = g4.squalifica = bot2.squalifica = bot3.squalifica = bot4.squalifica = 
    backpressed = g1.p1 = g2.p1 = g3.p1 = g4.p1 = false;
  g1.play = g1.exist = true;
  textSize (15*height/360);
  for (a = 0; a < q.length; a++)
    for (b = 0; b < q[0].length; b++) {
      q[a][b].colore = false;
      q[a][b].posizione = false;
      q[a][b].active = false;
      q[a][b].zona = false;
      q[a][b].segno = false;
      q[a][b].player = 0;
    }
  Nmosse = 0;
  loadScreen();
  victory = "";
}
//private InterstitialAd mInterstitialAd;
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
  //mInterstitialAd = new InterstitialAd(this);
  //mInterstitialAd.setAdUnitId("ca-app-pub-5012960537308882/3697402259");
}
/*
public void adManager()
 {
 requestNewInterstitial();
 adListener();
 requestNewInterstitial();
 adListener();  
 }
 
 private void requestNewInterstitial() {
 AdRequest adRequest = new AdRequest.Builder()
 .addTestDevice("02157df20ae82908")
 .build();
 
 mInterstitialAd.loadAd(adRequest);
 }
 
 public void adListener()
 {
 
 mInterstitialAd.setAdListener(new AdListener() {
 @Override
 public void onAdLoaded()
 {
 displayAd();
 }
 @Override
 public void onAdClosed() {
 //requestNewInterstitial();
 }
 });}
 
 public void displayAd(){
 if (mInterstitialAd.isLoaded()) {
 mInterstitialAd.show();
 }
 }
 */
public void onPause() {
  super.onPause();
  splash.pause();
  music.pause();
  theme.pause();
  println("Pause");
}

public void onResume() { 
  super.onResume();
  println("Resume");
}

public void onStop() { 
  super.onStop();
  splash.pause();
  music.pause();
  theme.pause();
  println("Stop");
}

public void onDestroy() {
  super.onDestroy();
  println("Destroy");
  if (splash!=null)
    splash.release();
  if (theme!=null)
    theme.release();
  if (music!=null)
    music.release();
}



public void onBackPressed() {
  mouseX = 6400;
  mouseY = 3200;
  textAlign(CENTER);
  if (scene.equals("play"))
    backpressed =! backpressed;
  else if ( scene.equals("menu"))
    exit();
  else {
    textAlign(CENTER);
    reset();
    tutorial = sc = pag = 0;
  }
  bP++;
  if (bP == 5) {
    //  splashaAd();
  }
  if (bP >= 10) {
    bP = 3;
    //Nel caso in cui si buggasse, non si sa mai
  }
  salvamelo();
}

public void salvamelo() {
  realString=str(sli)+parseChar(10)+
    language+parseChar(10)+
    str(musicMn)+parseChar(10)+
    str(musicTh)+parseChar(10)+
    str(sound)+parseChar(10)+
    str(effect)+parseChar(10)+
    str(back)+parseChar(10)+
    str(NPar)+parseChar(10)+
    str(take)+parseChar(10)+
    str(phone)+parseChar(10)+
    str(primavolta)+parseChar(10)+
    str(gameHelp)
    ; //Mi salva la scelta di avere o meno la musica
  scrittura(realString);
}

public void splashaAd() {
  //Fai vedere l'ad e resetti il contatore (la variabile bP) quando \u00e8 stato chiuso l'ad 
  /*mouseX = width/2;
   mouseY = height/2;
   mousePressed = true;
   mousePressed();
   mouseReleased();
   mousePressed = false;*/
  //adManager();
}

public void suoneria() {
  if (!sound) {
    theme.pause();
    music.pause();
  }
  if (!musicTh) {
    theme.pause();
    theme.seekTo(0);
  }
  if (!musicMn) {
    music.pause();
    music.seekTo(0);
  }

  if (scene.equals("play") || scene.equals("p")) {
    if (musicMn && sound) {
      theme.seekTo(0);
      theme.pause();
      music.start();
    }
    if (musicTh && sound) {
      theme.seekTo(0);
      theme.pause();
    }
  } else if (!scene.equals("play") && frameCount > 200) {
    if (musicTh && sound)
    {
      music.seekTo(0);
      music.pause();
      theme.start();
    }
    if (musicMn && sound) {
      music.pause();
      music.seekTo(0);
    }
  }
}

public void justplay() {
  background(back);
  atg(); //AlternativeGameOver, faccio in modo che il gioco finisca prima di finire se i giocatori vengono espulsi
  griglia();
  if ((bot2.play || bot3.play || bot4.play) && !playopts && !backpressed) {
    think++;
    noZona();
  }
  if (think >= setThink && g1.play == false) {
    //while(BOT2 == bot2.punt && BOT3 == bot3.punt && BOT4 == bot4.punt){
    computer(); 
    //think = setThink+1;
  }
  if (playopts == true) {
    PlayOpt(); 
    image(RT, 20*width/640, 270*height/360, 90*width/640, 90*width/640);
  }
  if (playopts == false || backpressed == true) {
    // cp5.getController("sli")
    // .setVisible(false)
    //;
  }
  if (playopts == false && backpressed == false) { 
    g1.view();
    g2.view();
    g3.view();
    g4.view();
    textAlign(CENTER);
    textSize(30*width/640);
    //Il "bottone delle opzioni"
    image(OP, 20*width/640, 270*height/360, 90*width/640, 90*width/640);
    //Il semaforo per i turni dei giocatori
    if (g1.play == true) {
      fill(g1.RGB); //Allah
      rect(250*width/640, 280*height/360, 55*width/640, 70*width/640);
    } else if (g2.play == true || bot2.play == true) {
      fill(g2.RGB);
      rect(305*width/640, 280*height/360, 55*width/640, 70*width/640);
      fill(0);
      if (bot2.play == true) text("B", 325*width/640, 325*height/360);
    } else if (g3.play == true || bot3.play == true) {
      fill(g3.RGB);
      rect(355*width/640, 280*height/360, 55*width/640, 70*width/640);
      fill(0);
      if (bot3.play == true) text("B", 383*width/640, 325*height/360);
    } else if (g4.play == true || bot4.play == true) {
      fill(g4.RGB);
      rect(405*width/640, 280*height/360, 55*width/640, 70*width/640);
      fill(0);
      if (bot4.play == true) text("B", 435*width/640, 325*height/360);
    }
    fill(245);
    image(SF, 240*width/640, 270*height/360, 230*width/640, 90*height/360);
  }

  if (backpressed == true) {
    fill(255, 127, 0);
    rect(0, 0, 640*width/640, 360*height/360);
    fill(139, 0, 0);
    rect(180*width/640, 180*height/360, 80*width/640, 30*height/360); //Quadrato per dire NO
    fill(0, 104, 139);
    rect(400*width/640, 180*height/360, 80*width/640, 30*height/360); //Quadrato per dire SI
    fill(0);
    textSize(23*height/360);
    text(parola[70], 320*width/640, 90*height/360);
    text(parola[71], 440*width/640, 205*height/360);
    text(parola[72], 220*width/640, 205*height/360);
  }

  if (totale == qx*qy-1 && ((mousePressed && mouseX >= 23*width/640 && mouseX <= 575*width/640 && mouseY >= 23*height/360 && mouseY <= 230*height/360) || (IA)) && !backpressed && !playopts) {
    /*if (FTIME == 2)
     FTIME = 3;*/
    if (g1.play == true) 
      q[0][q[0].length-1].player = 1;
    else if (g2.play == true || bot2.play == true) 
      q[0][q[0].length-1].player = 2;
    else if (g3.play == true || bot3.play == true) 
      q[0][q[0].length-1].player = 3;
    else if (g4.play == true || bot4.play == true) 
      q[0][q[0].length-1].player = 4;
    q[0][q[0].length-1].colore = true;
    q[0][q[0].length-1].view();
    q[0][q[0].length-1].tantipunti();
  }
}
int colrand, delay = 60;
int co;
public void fillmode() {
  fill(co);
  delay--;
  if (delay <= 0) {
    if (colrand == 0)
      co = color(255, 0, 0);
    else if (colrand == 1) 
      co = color(0, 255, 0);
    else if (colrand == 2)
      co = color(0, 0, 255);
    else if (colrand == 3) 
      co = color(0, 255, 255);
    else if (colrand == 4) 
      co = color(255, 255, 0);
    else if (colrand == 5) 
      co = color(255, 0, 255);
    else if (colrand == 6)
      co = color(0, 0, 0);
    colrand = PApplet.parseInt(random(7));
    delay = 60;
  }
}

float ly1=1, lY1=1, ly2=359, lY2=359; //Per le linee
float p1, p2, p3, p4;
boolean sale = false, pepe = false;
public void scena() {
  if (mouseX >= 450*width/640 && mouseX <= 570*width/640 && mouseY >= 140*height/360 && mouseY <= 220*height/360) {
    image(YouTube, 450*width/640, 110*height/360, 140*width/640, 140*width/640);
  } else {
    image(YouTube, 480*width/640, 140*height/360, 80*width/640, 80*width/640);
  }

  if (mouseX >= 60*width/640 && mouseX <= 140*width/640 && mouseY >= 140*height/360 && mouseY <= 220*height/360) {
    image(Face, 30*width/640, 110*height/360, 140*width/640, 140*width/640);
  } else {
    image(Face, 60*width/640, 140*height/360, 80*width/640, 80*width/640);
  }
  stroke(0);
  strokeWeight(10*height/360);
  line(220*width/640, ly1*height/360, 220*width/640, lY1*height/360);
  line(420*width/640, ly2*height/360, 420*width/640, lY2*height/360);
  stroke(g1.RGB);
  point(220*width/640, p1*height/360);
  stroke(g2.RGB);
  point(220*width/640, p2*height/360);
  stroke(g3.RGB);
  point(420*width/640, p3*height/360);
  stroke(g4.RGB);
  point(420*width/640, p4*height/360);
  stroke(0);
  strokeWeight(1);
  if (sale == false && pepe == false) {
    ly1+=0.6f;
    ly2-=0.6f;
    p1 = ly1;
    p2 = lY1;
    p3 = ly2;
    p4 = lY2;
  } else if (sale == true && pepe == false) {
    ly1-=0.6f;
    ly2+=0.6f;
    p1 = lY1;
    p2 = ly1;
    p3 = lY2;
    p4 = ly2;
  } else if (sale == false && pepe == true) {
    lY1+=0.6f;
    lY2-=0.6f;
    p1 = ly1;
    p2 = lY1;
    p3 = ly2;
    p4 = lY2;
  } else if (sale && pepe) {
    lY1-=0.6f;
    lY2+=0.6f;
    p1 = lY1;
    p2 = ly1;
    p3 = lY2;
    p4 = ly2;
  }

  if (ly1 >= 360 && ly2 <= 0) {
    sale = false;
    pepe = true;
    ly2 = 1;
    ly1 = 359;
  } else if (ly1<=0 && ly2 >= 360) {
    sale = false;
    pepe = false;
    ly1 = 1;
    ly2 = 359;
  } else if (lY1<=0 && lY2 >= 360) {
    sale = true;
    pepe = false;
    lY1 = 1;
    lY2 = 359;
  } else if (lY1 >= 360 && lY2 <= 0) {
    sale = true;
    pepe = true;
    lY2 = 1;
    lY1 = 359;
  }
}
boolean take = true; //per lo screenshot
boolean phone = true; //per salvarlo all'interno del telefono
int pag = 0;
public void storico() {
  background(30); 
  if (NPar >= 0)
    image(IMG[0+pag], 10*width/640, 10*height/360, PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 1)
    image(IMG[1+pag], 10*width/640, 10*height/360+(PApplet.parseInt(230*height/360)/3), PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 2)
    image(IMG[2+pag], 10*width/640, 10*height/360+((PApplet.parseInt(230*height/360)/3)*2), PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 3)
    image(IMG[3+pag], 10*width/640+(PApplet.parseInt(575*width/640)/3), 10*height/360, PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 4)
    image(IMG[4+pag], 10*width/640+(PApplet.parseInt(575*width/640)/3), 10*height/360+(PApplet.parseInt(230*height/360)/3), PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 5)
    image(IMG[5+pag], 10*width/640+(PApplet.parseInt(575*width/640)/3), 10*height/360+((PApplet.parseInt(230*height/360)/3)*2), PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 6)
    image(IMG[6+pag], 10*width/640+((PApplet.parseInt(575*width/640)/3)*2), 10*height/360, PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 7)
    image(IMG[7+pag], 10*width/640+((PApplet.parseInt(575*width/640)/3)*2), 10*height/360+(PApplet.parseInt(230*height/360)/3), PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);
  if (sc >= 8)
    image(IMG[8+pag], 10*width/640+((PApplet.parseInt(575*width/640)/3)*2), 10*height/360+((PApplet.parseInt(230*height/360)/3)*2), PApplet.parseInt(575*width/640)/3, PApplet.parseInt(230*height/360)/3);

  fill(180);
  if (NPar >= 1)
    text(1+sc+"/"+PApplet.parseInt(1+NPar)+" "+parola[76], displayWidth/2, 300*height/360);
  text(parola[73], displayWidth/2-displayWidth/4, 320*height/360);
  if (take) {
    fill(180);
  } else fill(60);
  text(parola[74], displayWidth/2, 320*height/360);

  if (take && phone) {
    fill(180);
  } else fill(60);
  text(parola[75], displayWidth/2+displayWidth/4, 320*height/360);
}