package processing.test.tehraster;

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.io.FileOutputStream; 
import java.io.FileInputStream; 
import java.lang.Object; 
import java.lang.reflect.*; 
import java.lang.Iterable; 
import java.util.*; 
import java.io.File; 
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

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TehRaster extends PApplet {








//Android










//final int REQUEST_ENABLE_BT = 4;
PGraphics screen;
PImage popup, square;
//ControlP5 cp5;
Activity activity;// = this.getActivity();
Context context;// = new Context();
//Slider abc;
APMediaPlayer theme ,music ,splash;
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
int Ngioc, Ncpu, Lcpu, think, setThink = 60;
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
  //font = createFont("PenguinAttack.ttf", 20);
  //textFont(font);
  // }*/
  //activity = this.getActivity();
  //context = activity.getApplicationContext();
  /* if (displayWidth % 960 == 0 && displayHeight % 540 == 0) {
  dw  = (displayWidth/640) * 1.5f;
  dh = (displayHeight/360) * 1.5f;
} else if ( displayWidth % 800 == 0 && displayHeight % 480 == 0) {
  dw  = (displayWidth/640) * 1.25f;
 dh  = (displayHeight/360) * 1.33f;
} 
if ( displayWidth % 640 == 0 || displayHeight % 360 == 0) {
  dw  = displayWidth/640;
  dh  = displayHeight/360;
  if (dw > dh)
    dh = dw;
  else if (dh > dw)
    dw = height/360;
}   
*/

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
    //image(Logo, 380*width/640, 30*height/360, displayWidth-380*width/640, displayHeight); 
    scena();
    scritte();
  } else if (scene.equals("set play")) {
    setPlay();
  } else if (scene.equals("credit")) {
    credits();
  } else if (scene.equals("game over")) {
    // println(q[0][q[0].length-1].player);
    textSize (18*height/360);
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
  if (!victory.equals("")) { //Faccio ricoaminciare la partita
    if (mouseX >= 240*width/640 && mouseY >= 200*height/360 && mouseX <= 400*width/640 && mouseY <= 230*height/360) {
      screenshot();
      savePhoto();
      reset();
      /*if (FTIME <= 2) {
       FTIME = 3;
       }*/
    }
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






int modes;
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
  Ngioc = Ncpu = 1;
  b = modes = 1;
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
  /*if (!isTaskRoot()) {
    finish();
    return;
  }*/
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
  if(splash!=null)
  splash.release();
  if(theme!=null)
  theme.release();
  if(music!=null)
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
class CPU {
  boolean play, exist, squalifica;
  int RGB;
  int punt, vv, controllo = 0; //punti partita e vittorie
  int varX, varY;
  int in=0;
  int pp;
  int[] X = new int[qx*qy];
  int[] Y = new int[qx*qy];
  int ex, p1, p2, p3, p4; //EX per i 150 punti finali, p1..4 sono i perks aggiuntivi al gioco
  CPU(boolean attivo, int cc, int punti, boolean vita, boolean sq) {
    play = attivo;
    RGB = cc;
    punt = punti;
    exist = vita;
    squalifica = sq;
  }

  public void viewCPU() {
    fill(RGB);
    text (punt, 600*width/640, 20*height/360);
    text("Wins:"+vv, 600*width/640, 320*height/360);
  }

  public void IA() {

    if (play == true) {

      if (Lcpu == 0) liv0();
      else if (Lcpu == 1) liv1(); 
      else if (Lcpu == 2) liv2();
      else if (Lcpu == 3) liv3();
      else if (Lcpu == 10) liv10();
    }
    if (punt > controllo) controllo = punt;
    if (mouseX >= 20*width/640 && mouseX <= 110*width/640 && mouseY >= 270*height/360 && mouseY <= 360*height/360)
      ;
    else {
      if (!backpressed)
        mouseReleased();
    }
  }

  public void liv10() {
    if (Nmosse == 4) {
      q[qx-9][6].tantipunti(); 
      play = false;
      Nmosse++;
    } else if (Nmosse == 10) {
      q[8][8].tantipunti(); 
      play = false;
      Nmosse++;
    } else if (Nmosse == 15) {
      q[6][9].tantipunti(); 
      play = false;
      Nmosse++;
    } else if(Nmosse == 20){
      q[1][9].tantipunti();
      play = false;
      Nmosse++;
    } else if(Nmosse == 25){
      q[0][9].tantipunti();
      play = false;
      Nmosse++;
    }
  }

  public void liv0() {
    for (int y = 0; y < q[0].length; y++) {
      for (int x = 0; x < q.length; x++) {
        if (q[x][y].colore == false) {
          X[in] = x;
          Y[in] = y;
          in++;
          //println("Sto ciclando con in ="+in);
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }

  public void liv1() {
    for (int y = 0; y < q[0].length; y++) {
      for (int x = 0; x < q.length; x++) {
        if (q[x][y].colore == false && in <= 70) {
          X[in] = x;
          Y[in] = y;
          in++;
          //println("Sto ciclando con in ="+in);
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }

  public void liv2() {
    int unozero = PApplet.parseInt(random(2));
    if (unozero == 1) {
      for (int y = 0; y < q[0].length; y++) {
        for (int x = q.length-1; x >= 0; x--) {
          if (q[x][y].colore == false && in <= 15) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in+"Caso1");
          }
        }
      }
    } else {
      for (int x = q.length-1; x >= 0; x--) {
        for (int y = 0; y < q[0].length; y++) {
          if (q[x][y].colore == false && in <= 15) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in);
          }
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    //  if (totale < 245) {
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }

  public void liv3() {
    int unozero = PApplet.parseInt(random(2));
    if (unozero == 1) {
      for (int y = 0; y < q[0].length; y++) {
        for (int x = q.length-1; x >= 0; x--) {
          if (q[x][y].colore == false && in <= 5) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in+"Caso1");
          }
        }
      }
    } else {
      for (int x = q.length-1; x >= 0; x--) {
        for (int y = 0; y < q[0].length; y++) {
          if (q[x][y].colore == false && in <= 5) {
            X[in] = x;
            Y[in] = y;
            in++;
            //println("Sto ciclando con in ="+in);
          }
        }
      }
    }
    pp = PApplet.parseInt(random(0, in+1));
    //  if (totale < 245) {
    while (X[pp]+1 == q[0][q[0].length-1].px && Y[pp]+1 == q[0][q[0].length-1].py) {
      pp = PApplet.parseInt(random(0, in+1));
    }
    varX = X[pp]+1;
    varY = Y[pp]+1;
    in = 0;
  }
}

float toccoX, toccoY;
boolean pausa = true;
public void computer() {
  if (g1.play == false && g2.play == false && g3.play == false && g4.play == false && playopts == false) {
    if (bot2.play == true || bot3.play == true || bot4.play == true) { // SE ALMENO UN BOT E' VERO

      if (bot2.play == true) {
        IA = true;
        bot2.IA();
        toccoX = (23*bot2.varX)*width/640;
        toccoY = (23*bot2.varY)*height/360;
        //return;
      } else if (bot3.play == true) {
        IA = true;
        bot3.IA();
        toccoX = (23*bot3.varX)*width/640;
        toccoY = (23*bot3.varY)*height/360;
        //return;
      } else if (bot4.play == true) {
        IA = true;
        bot4.IA();
        toccoX = (23*bot4.varX)*width/640;
        toccoY = (23*bot4.varY)*height/360;
        //return;
      }

      if (think >= setThink ) {
        think = 0;
        pausa = false;
      }
    }//Chiusa condizione di "SE ALMENO UN BOT E' VERO"
    else IA = false;
  }
}
public void TurniGiocatori() { //Si passa il turno da un giocatore all'altro da 2 a 4 giocatori
  if (((g2.exist == true && bot2.exist == false) || (g2.exist == false && bot2.exist == true)) && g3.exist == false && g4.exist == false && bot3.exist == false && bot4.exist == false) {
    if ((g1.punt != G1 && g1.play == true)  || (g2.punt != G2 && g2.play == true) || (bot2.punt != BOT2 && bot2.play == true)) {  //Partita a 2 giocatori
      if (IA == false) { //Solo quando gioca un umano
        noZona(); //Cancella zone precedenti
        mossa(); //Inserisce un massimo di 5 mosse
      }
      if (g2.exist == false && bot2.exist == true) { //Secondo giocatore = BOT
        g1.play =! g1.play;
        bot2.play =! bot2.play;
      } else if ( g2.exist == true && bot2.exist == false) { //Secondo giocatore = UMANO
        g1.play =! g1.play;
        g2.play =! g2.play;
      }
    }
  } else if (((g2.exist == true && bot2.exist == false) || (g2.exist == false && bot2.exist == true)) && ((g3.exist == true && bot3.exist == false) || (g3.exist == false && bot3.exist == true)) && g4.exist == false && bot4.exist == false) {
    if ((g1.punt != G1 && g1.play == true)  || (g2.punt != G2 && g2.play == true) || (bot2.punt != BOT2 && bot2.play == true) || (g3.punt != G3 && g3.play == true) || (bot3.punt !=  BOT3 && bot3.play == true)) { //Partita a 3 giocatori
      if (IA == false) { //Solo quando gioca un umano
        noZona(); //Cancella zone precedenti
        mossa(); //Inserisce un massimo di 5 mosse
      }
      if (g2.exist == true && g3.exist == false && bot2.exist == false && bot3.exist == true && g1.squalifica == false && g2.squalifica == false && bot3.squalifica == false ) { // 2 Umani e 1 Bot , nessuna squalifica
        if (g1.play == true && g2.play == false && bot3.play == false) { //Passa il turno al giocatore 2
          g1.play = false;
          g2.play = true;
          bot3.play = false;
        } else if (g1.play == false && g2.play == true && bot3.play == false) { //Passa il turno al giocatore 3
          g1.play = false;
          g2.play = false;
          bot3.play = true;
        } else if (g1.play == false && g2.play == false && bot3.play == true) { //Passa il turno al giocatore 1
          g1.play = true;
          g2.play = false;
          bot3.play = false;
        }
      }//Chiudo condizione senza squalifica
      else if (g2.exist == true && g3.exist == false && bot2.exist == false && bot3.exist == true && g1.squalifica == true && g2.squalifica == false && bot3.squalifica == false ) {
        g1.play = false;
        g2.play =! g2.play;
        bot3.play =! bot3.play;
      }//Chiusura con squalifica giocatore 1
      else if (g2.exist == true && g3.exist == false && bot2.exist == false && bot3.exist == true && g1.squalifica == false && g2.squalifica == true && bot3.squalifica == false ) {
        g2.play = false;
        g1.play =! g1.play;
        bot3.play =! bot3.play;
      }//Chiusura con squalifica giocatore 2
      else if (g2.exist == true && g3.exist == false && bot2.exist == false && bot3.exist == true && g1.squalifica == false && g2.squalifica == false && bot3.squalifica == true ) {
        bot3.play = false;
        g2.play =! g2.play;
        g1.play =! g1.play;
      }//Chiusura con squalifica bot 3
      else if (g2.exist == true && g3.exist == true && bot2.exist == false && bot3.exist == false && g1.squalifica == false && g2.squalifica == false && g3.squalifica == false) { //3 UMANI, nessuna squalifica
        if (g1.play == true && g2.play == false && g3.play == false) { //Passa il turno al giocatore 2
          g1.play = false;
          g2.play = true;
          g3.play = false;
        } else if (g1.play == false && g2.play == true && g3.play == false) { //Passa il turno al giocatore 3
          g1.play = false;
          g2.play = false;
          g3.play = true;
        } else if (g1.play == false && g2.play == false && g3.play == true) { //Passa il turno al giocatore 1
          g1.play = true;
          g2.play = false;
          g3.play = false;
        }
      }//Chiudo condizione senza squalifica
      else if (g2.exist == true && g3.exist == true && bot2.exist == false && bot3.exist == false && g1.squalifica == true && g2.squalifica == false && g3.squalifica == false) {
        g1.play = false;
        g2.play =! g2.play;
        g3.play =! g3.play;
      }//Chiusura con squalifica giocatore 1
      else if (g2.exist == true && g3.exist == true && bot2.exist == false && bot3.exist == false && g1.squalifica == false && g2.squalifica == true && g3.squalifica == false) {
        g2.play = false;
        g1.play =! g1.play;
        g3.play =! g3.play;
      }//Chiusura con squalifica giocatore 2
      else if (g2.exist == true && g3.exist == true && bot2.exist == false && bot3.exist == false && g1.squalifica == false && g2.squalifica == false && g3.squalifica == true) {
        g3.play = false;
        g2.play =! g2.play;
        g1.play =! g1.play;
      }//Chiusura con squalifica giocatore 3
      else if (g2.exist == false && g3.exist == false && bot2.exist == true && bot3.exist == true && g1.squalifica == false && bot2.squalifica == false && bot3.squalifica == false) { //Un umano e 2 Bot , nessuna squalifica
        if (g1.play == true && bot2.play == false && bot3.play == false) { //Passa il turno al giocatore 2
          g1.play = false;
          bot2.play = true;
          bot3.play = false;
        } else if (g1.play == false && bot2.play == true && bot3.play == false) { //Passa il turno al giocatore 3
          g1.play = false;
          bot2.play = false;
          bot3.play = true;
        } else if (g1.play == false && bot2.play == false && bot3.play == true) { //Passa il turno al giocatore 1
          g1.play = true;
          bot2.play = false;
          bot3.play = false;
        }
      }//Chiudo condizione con nessuna squalifica
      else if (g2.exist == false && g3.exist == false && bot2.exist == true && bot3.exist == true && g1.squalifica == true && bot2.squalifica == false && bot3.squalifica == false && !pausa) {
        g1.play = false;
        bot2.play =! bot2.play;
        bot3.play =! bot3.play;
        pausa = true;
      }//Chiusura con squalifica del giocatore 1
      else if (g2.exist == false && g3.exist == false && bot2.exist == true && bot3.exist == true && g1.squalifica == false && bot2.squalifica == true && bot3.squalifica == false) {
        bot2.play = false;
        g1.play =! g1.play;
        bot3.play =! bot3.play;
      }//Chiusura con squalifica del bot 2
      else if (g2.exist == false && g3.exist == false && bot2.exist == true && bot3.exist == true && g1.squalifica == false && bot2.squalifica == false && bot3.squalifica == true) {
        bot3.play = false;
        bot2.play =! bot2.play;
        g1.play =! g1.play;
      }//Chiusura con squalifica del bot3
    }//Chiudo controllo differenza punteggi
  }//Chiusura condizioni 3 giocatori
  else if (((g2.exist == true && bot2.exist == false) || (g2.exist == false && bot2.exist == true)) && ((g3.exist == true && bot3.exist == false) || (g3.exist == false && bot3.exist == true)) && ((g4.exist == true && bot4.exist == false) || (g4.exist == false && bot4.exist == true))) {
    //if (q[c1].colore == true)
    //  mossa();
    if ((g1.punt != G1 && g1.play == true)  || (g2.punt != G2 && g2.play == true) || (bot2.punt != BOT2 && bot2.play == true) || (g3.punt != G3 && g3.play == true) || (bot3.punt !=  BOT3 && bot3.play == true) || (g4.punt != G4 && g4.play == true) || (bot4.punt != BOT4 && bot4.play == true)) {
      if (IA == false) { //Solo quando gioca un umano
        noZona(); //Cancella zone precedenti
        mossa(); //Inserisce un massimo di 5 mosse
      }
      if (g2.exist == true && g3.exist == true && g4.exist == true && bot2.exist == false && bot3.exist == false && bot4.exist == false) { //4 UMANI
        if (g1.squalifica == false && g2.squalifica == false && g3.squalifica == false && g4.squalifica == false) { //Nessuna squalifica
          if (g1.play == true && g2.play == false && g3.play == false && g4.play == false) { //Passa il turno al giocatore 2
            g1.play = false;
            g2.play = true;
            g3.play = false;
            g4.play = false;
          } else if (g1.play == false && g2.play == true && g3.play == false && g4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = false;
            g3.play = true;
            g4.play = false;
          } else if (g1.play == false && g2.play == false && g3.play == true && g4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            g3.play = false;
            g4.play = true;
          } else if (g1.play == false && g2.play == false && g3.play == false && g4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            g3.play = false;
            g4.play = false;
          }
        }//Chiudo condizione senza sqalifiche
        else if (g1.squalifica == true && g2.squalifica == false && g3.squalifica == false && g4.squalifica == false) { //G1 squalificato
          g1.play = false;
          if (g2.play == true && g3.play == false && g4.play == false) { //Passa il turno al giocatore 3
            g2.play = false;
            g3.play = true;
            g4.play = false;
          } else if (g2.play == false && g3.play == true && g4.play == false) { //Passa il turno al giocatore 4
            g2.play = false;
            g3.play = false;
            g4.play = true;
          } else if (g1.play == false && g2.play == false && g3.play == false && g4.play == true) { //Passo il turno al giocatore 1
            g2.play = true;
            g3.play = false;
            g4.play = false;
          }
        }//Chiudo condizione G1 squalificato
        else if (g1.squalifica == false && g2.squalifica == true && g3.squalifica == false && g4.squalifica == false) { //G2 squalificato
          g2.play = false;
          if (g1.play == true && g3.play == false && g4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g3.play = true;
            g4.play = false;
          } else if (g1.play == false && g3.play == true && g4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g3.play = false;
            g4.play = true;
          } else if (g1.play == false && g3.play == false && g4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g3.play = false;
            g4.play = false;
          }
        }//Chiudo condizione G2 squalificato
        else if (g1.squalifica == false && g2.squalifica == false && g3.squalifica == true && g4.squalifica == false) { //G3 squalificato
          g3.play = false;
          if (g1.play == true && g2.play == false && g4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = true;
            g4.play = false;
          } else if (g1.play == false && g2.play == true && g4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            g4.play = true;
          } else if (g1.play == false && g2.play == false && g4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            g4.play = false;
          }
        }//Chiudo condizione G3 squalificato
        else if (g1.squalifica == false && g2.squalifica == false && g3.squalifica == false && g4.squalifica == true) { //G4 squalificato
          g4.play = false;
          if (g1.play == true && g2.play == false && g3.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = true;
            g3.play = false;
          } else if (g1.play == false && g2.play == true && g3.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            g3.play = true;
          } else if (g1.play == false && g2.play == false && g3.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            g3.play = false;
          }
        }//Chiudo condizione G4 squalificato
      }//Chiusra 4 UMANI
      else if (g2.exist == true && g3.exist == true && g4.exist == false && bot2.exist == false && bot3.exist == false && bot4.exist == true) { //3 UMANI 1 BOT
        if (g1.squalifica == false && g2.squalifica == false && g3.squalifica == false && bot4.squalifica == false) { //Nessuna squalifica
          if (g1.play == true && g2.play == false && g3.play == false && bot4.play == false) { //Passa il turno al giocatore 2
            g1.play = false;
            g2.play = true;
            g3.play = false;
            bot4.play = false;
          } else if (g1.play == false && g2.play == true && g3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = false;
            g3.play = true;
            bot4.play = false;
          } else if (g1.play == false && g2.play == false && g3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            g3.play = false;
            bot4.play = true;
          } else if (g1.play == false && g2.play == false && g3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            g3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione senza sqalifiche
        else if (g1.squalifica == true && g2.squalifica == false && g3.squalifica == false && bot4.squalifica == false) { //G1 squalificato
          g1.play = false;
          if (g2.play == true && g3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g2.play = false;
            g3.play = true;
            bot4.play = false;
          } else if (g2.play == false && g3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g2.play = false;
            g3.play = false;
            bot4.play = true;
          } else if (g1.play == false && g2.play == false && g3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g2.play = true;
            g3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione G1 squalificato
        else if (g1.squalifica == false && g2.squalifica == true && g3.squalifica == false && bot4.squalifica == false) { //G2 squalificato
          g2.play = false;
          if (g1.play == true && g3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g3.play = true;
            bot4.play = false;
          } else if (g1.play == false && g3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g3.play = false;
            bot4.play = true;
          } else if (g1.play == false && g3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione G2 squalificato
        else if (g1.squalifica == false && g2.squalifica == false && g3.squalifica == true && bot4.squalifica == false) { //G3 squalificato
          g3.play = false;
          if (g1.play == true && g2.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = true;
            bot4.play = false;
          } else if (g1.play == false && g2.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            bot4.play = true;
          } else if (g1.play == false && g2.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione G3 squalificato
        else if (g1.squalifica == false && g2.squalifica == false && g3.squalifica == false && bot4.squalifica == true) { //bot4 squalificato
          bot4.play = false;
          if (g1.play == true && g2.play == false && g3.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = true;
            g3.play = false;
          } else if (g1.play == false && g2.play == true && g3.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            g3.play = true;
          } else if (g1.play == false && g2.play == false && g3.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            g3.play = false;
          }
        }//Chiudo condizione bot4 squalificato
      }//Chiusura 3 umani 1 bot 
      else if (g2.exist == true && g3.exist == false && g4.exist == false && bot2.exist == false && bot3.exist == true && bot4.exist == true) { //2 UMANI 2 BOT
        if (g1.squalifica == false && g2.squalifica == false && bot3.squalifica == false && bot4.squalifica == false) { //Nessuna squalifica
          if (g1.play == true && g2.play == false && bot3.play == false && bot4.play == false) { //Passa il turno al giocatore 2
            g1.play = false;
            g2.play = true;
            bot3.play = false;
            bot4.play = false;
          } else if (g1.play == false && g2.play == true && bot3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = false;
            bot3.play = true;
            bot4.play = false;
          } else if (g1.play == false && g2.play == false && bot3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            bot3.play = false;
            bot4.play = true;
          } else if (g1.play == false && g2.play == false && bot3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            bot3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione senza sqalifiche
        else if (g1.squalifica == true && g2.squalifica == false && bot3.squalifica == false && bot4.squalifica == false) { //G1 squalificato
          g1.play = false;
          if (g2.play == true && bot3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g2.play = false;
            bot3.play = true;
            bot4.play = false;
          } else if (g2.play == false && bot3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g2.play = false;
            bot3.play = false;
            bot4.play = true;
          } else if (g1.play == false && g2.play == false && bot3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g2.play = true;
            bot3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione G1 squalificato
        else if (g1.squalifica == false && g2.squalifica == true && bot3.squalifica == false && bot4.squalifica == false) { //G2 squalificato
          g2.play = false;
          if (g1.play == true && bot3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            bot3.play = true;
            bot4.play = false;
          } else if (g1.play == false && bot3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            bot3.play = false;
            bot4.play = true;
          } else if (g1.play == false && bot3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            bot3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione G2 squalificato
        else if (g1.squalifica == false && g2.squalifica == false && bot3.squalifica == true && bot4.squalifica == false) { //bot3 squalificato
          bot3.play = false;
          if (g1.play == true && g2.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = true;
            bot4.play = false;
          } else if (g1.play == false && g2.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            bot4.play = true;
          } else if (g1.play == false && g2.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione bot3 squalificato
        else if (g1.squalifica == false && g2.squalifica == false && bot3.squalifica == false && bot4.squalifica == true) { //bot4 squalificato
          bot4.play = false;
          if (g1.play == true && g2.play == false && bot3.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            g2.play = true;
            bot3.play = false;
          } else if (g1.play == false && g2.play == true && bot3.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            g2.play = false;
            bot3.play = true;
          } else if (g1.play == false && g2.play == false && bot3.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            g2.play = false;
            bot3.play = false;
          }
        }//Chiudo condizione bot4 squalificato
      }//Chiusura 2 UMANI 2 BOT
      else if (g2.exist == false && g3.exist == false && g4.exist == false && bot2.exist == true && bot3.exist == true && bot4.exist == true) { //1 UMANO 3 BOT
        if (g1.squalifica == false && bot2.squalifica == false && bot3.squalifica == false && bot4.squalifica == false) { //Nessuna squalifica
          if (g1.play == true && bot2.play == false && bot3.play == false && bot4.play == false) { //Passa il turno al giocatore 2
            g1.play = false;
            bot2.play = true;
            bot3.play = false;
            bot4.play = false;
          } else if (g1.play == false && bot2.play == true && bot3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            bot2.play = false;
            bot3.play = true;
            bot4.play = false;
          } else if (g1.play == false && bot2.play == false && bot3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            bot2.play = false;
            bot3.play = false;
            bot4.play = true;
          } else if (g1.play == false && bot2.play == false && bot3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            bot2.play = false;
            bot3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione senza sqalifiche
        else if (g1.squalifica == true && bot2.squalifica == false && bot3.squalifica == false && bot4.squalifica == false){// && (BOT2 != bot2.punt || BOT3 != bot3.punt || BOT4 != bot4.punt)) { //G1 squalificato
          g1.play = false;
          if (bot2.play == true && bot3.play == false && bot4.play == false && BOT2 != bot2.punt && !pausa) { //Passa il turno al giocatore 3
           // println("2");
            bot2.play = false;
            bot3.play = true;
            bot4.play = false;
            pausa = true;
          } else if (bot2.play == false && bot3.play == true && bot4.play == false && BOT3 != bot3.punt && !pausa) { //Passa il turno al giocatore 4
            //println("3");
            bot2.play = false;
            bot3.play = false;
            bot4.play = true;
            pausa = true;
          } else if (bot2.play == false && bot3.play == false && bot4.play == true && BOT4 != bot4.punt && !pausa) { //Passo il turno al giocatore 1
            //println("4");
            bot2.play = true;
            bot3.play = false;
            bot4.play = false;
            pausa = true;
          }
        }//Chiudo condizione G1 squalificato
        else if (g1.squalifica == false && bot2.squalifica == true && bot3.squalifica == false && bot4.squalifica == false) { //bot2 squalificato
          bot2.play = false;
          if (g1.play == true && bot3.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            bot3.play = true;
            bot4.play = false;
          } else if (g1.play == false && bot3.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            bot3.play = false;
            bot4.play = true;
          } else if (g1.play == false && bot3.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            bot3.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione bot2 squalificato
        else if (g1.squalifica == false && bot2.squalifica == false && bot3.squalifica == true && bot4.squalifica == false) { //bot3 squalificato
          bot3.play = false;
          if (g1.play == true && bot2.play == false && bot4.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            bot2.play = true;
            bot4.play = false;
          } else if (g1.play == false && bot2.play == true && bot4.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            bot2.play = false;
            bot4.play = true;
          } else if (g1.play == false && bot2.play == false && bot4.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            bot2.play = false;
            bot4.play = false;
          }
        }//Chiudo condizione bot3 squalificato
        else if (g1.squalifica == false && bot2.squalifica == false && bot3.squalifica == false && bot4.squalifica == true) { //bot4 squalificato
          bot4.play = false;
          if (g1.play == true && bot2.play == false && bot3.play == false) { //Passa il turno al giocatore 3
            g1.play = false;
            bot2.play = true;
            bot3.play = false;
          } else if (g1.play == false && bot2.play == true && bot3.play == false) { //Passa il turno al giocatore 4
            g1.play = false;
            bot2.play = false;
            bot3.play = true;
          } else if (g1.play == false && bot2.play == false && bot3.play == true) { //Passo il turno al giocatore 1
            g1.play = true;
            bot2.play = false;
            bot3.play = false;
          }
        }//Chiudo condizione bot4 squalificato
      }//Chiusura 1 UMANO e 3 BOT
    }//Chiudo controllo differenza punteggi
  }//Chiusura condizioni 4 giocatori
  if (posto == true) { //Sembra fungere ma anche no
    mossa(); //Crea un massimo di 5 mosse disponibili per il giocatore
    //sf = int(random(0,10)); // Da modificare
  } 
  if (bot2.play || bot3.play || bot4.play) CPUNO();
}




public void gameover() { //Schermata di gameover che stampa il noyme del vincitore
  //screenshot();
  background(130);
  if (Ngioc == 1 && Ncpu == 1) { //1v1 VS PC
    if (g1.punt < bot2.punt)
      victory = parola[1]+"!";
    else if (bot2.punt < g1.punt)
      victory = parola[5]+"!";
    else 
    victory = parola[8]+"!";
  } else if (Ngioc == 2 && Ncpu == 1) { //1v1v1 , 1CPU
    if (g1.punt < g2.punt && g1.punt < bot3.punt)
      victory = parola[1]+"!";
    else if (g2.punt < g1.punt && g2.punt < bot3.punt)
      victory = parola[2]+"!";
    else if (bot3.punt < g1.punt && bot3.punt < g2.punt)
      victory = parola[6]+"!";
    else
      victory = parola[8]+"!";
  } else if (Ngioc == 3 && Ncpu == 1) { // 4 gioc 1 CPU
    if (g1.punt < g2.punt && g1.punt < g3.punt && g1.punt < bot4.punt)
      victory = parola[1]+"!";
    else if (g2.punt < g1.punt && g2.punt < g3.punt && g2.punt < bot4.punt)
      victory = parola[2]+"!";
    else if (g3.punt < g1.punt && g3.punt < g2.punt && g3.punt < bot4.punt)
      victory = parola[3]+"!";
    else if (bot4.punt < g2.punt && bot4.punt < g3.punt && g1.punt > bot4.punt)
      victory = parola[7]+"!";
    else 
    victory = parola[8]+"!";
  } else if (Ngioc == 1 && Ncpu == 2) { //2 BOT 
    if (g1.punt < bot2.punt && g1.punt < bot3.punt)
      victory = parola[1]+"!";
    else if (bot2.punt < g1.punt && bot2.punt < bot3.punt)
      victory = parola[5]+"!";
    else if (bot3.punt < bot2.punt && g1.punt > bot3.punt)
      victory = parola[6]+"!";
    else
      victory = parola[8]+"!";
  } else if (Ngioc == 1 && Ncpu == 3) { //3 bot
    if (g1.punt < bot2.punt && g1.punt < bot3.punt && g1.punt < bot4.punt)
      victory = parola[1]+"!";
    else if (bot2.punt < g1.punt && bot2.punt < bot3.punt && bot2.punt < bot4.punt)
      victory = parola[5]+"!";
    else if (bot3.punt < g1.punt && bot3.punt < bot2.punt && bot3.punt < bot4.punt)
      victory = parola[6]+"!";
    else if (bot4.punt < bot2.punt && bot4.punt < bot3.punt && g1.punt > bot4.punt)
      victory = parola[7]+"!";
    else
      victory = parola[8]+"!";
  } else if (Ngioc == 2 && Ncpu == 2) { //2 e 2
    if (g1.punt < g2.punt && g1.punt < bot3.punt && g1.punt < bot4.punt)
      victory = parola[1]+"!";
    else if (g2.punt < g1.punt && g2.punt < bot3.punt && g2.punt < bot4.punt)
      victory = parola[2]+"!";
    else if (bot3.punt < g1.punt && bot3.punt < g2.punt && bot3.punt < bot4.punt)
      victory = parola[6]+"!";
    else if (bot4.punt < g1.punt && bot4.punt < g2.punt && bot3.punt > bot4.punt)
      victory = parola[7]+"!";
    else
      victory = parola[8]+"!";
  } else if (Ngioc == 4 && Ncpu == 0) { //4 gioc
    if (g1.punt < g2.punt && g1.punt < g3.punt && g1.punt < g4.punt)
      victory = parola[1]+"!";
    else if (g2.punt < g1.punt && g2.punt < g3.punt && g2.punt < g4.punt)
      victory = parola[2]+"!";
    else if (g3.punt < g1.punt && g3.punt < g2.punt && g3.punt < g4.punt)
      victory = parola[3]+"!";
    else if (g4.punt < g1.punt && g4.punt < g2.punt && g3.punt > g4.punt)
      victory = parola[4]+"!";
    else
      victory = parola[8]+"!";
  } else if (Ngioc == 3 && Ncpu == 0) {//3 gioc
    if (g1.punt < g2.punt && g1.punt < g3.punt)
      victory = parola[1]+"!";
    else if (g2.punt < g1.punt && g2.punt < g3.punt)
      victory = parola[2]+"!";
    else if (g3.punt < g1.punt && g2.punt > g3.punt)
      victory = parola[3]+"!";
    else
      victory = parola[8]+"!";
  } else if (Ngioc == 2 && Ncpu == 0) {
    if (g1.punt < g2.punt)
      victory = parola[1]+"!";
    else if (g1.punt > g2.punt)
      victory = parola[2]+"!";
    else
      victory = parola[8]+"!";
  }
  punteggi();
  fill(240);
  if (victory != parola[8]+"!") {
    text(parola[19]+"\n"+victory, 320*width/640, 120*height/360);
  } else
    text(victory, 320*width/640, 120*height/360);
  rect(240*width/640, 200*height/360, 160*width/640, 30*height/360);
  fill(0);
  text(parola[20], 320*width/640, 220*height/360);
}

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
int tutorial, conto;

public void htp() {
  if (tutorial == 0) {
    conto = 0;
    sc = 0;
  } else {
    if (conto < 120)
      conto ++;
    else {
      conto = 0;
    }
  }
  background(75);
  if (mouseX <= 70*width/640 && mouseY <= 70*height/360) {
    fill(255, 0, 0);
    textSize (18*height/360);
  } else {
    fill(200);
    textSize (12*height/360);
  }
  text(parola[28], 50*width/640, 50*height/360);
  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 65*height/360 && mouseY <= 85*height/360) {
    fill(255, 0, 0);
    textSize (26*height/360);
  } else {
    fill(200);
    textSize (19*height/360);
  }
  text(parola[57], 320*width/640, 80*height/360);

  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 145*height/360 && mouseY <= 165*height/360) {
    fill(255, 0, 0);
    textSize (26*height/360);
  } else {
    fill(200);
    textSize (19*height/360);
  }
  text(parola[58], 320*width/640, 160*height/360);

  if (mouseX >= 260*width/640 && mouseX <= 380*width/640 && mouseY >= 225*height/360 && mouseY <= 245*height/360) {
    fill(255, 0, 0);
    textSize (26*height/360);
  } else {
    fill(200);
    textSize (19*height/360);
  }
    text(parola[77], 320*width/640, 240*height/360); //Bonus

  if (tutorial != 0) {
    background(0);
    fill(255);
    rect(640*width/640, 0, 640*width/640, 360*height/360);
    fill(230);
    if (tutorial == 1 || tutorial == 2) {
      for (int i = 0; i < 40; i++) { 
        if (parola[46].length()*i*width/640 >= 640*width/640 || parola[47].length()*i*width/640 >= 640*width/640 || parola[53].length()*i*width/640 >= 640*width/640) {
          textSize((i-1)*2*width/640);
          println((i-1)*2*width/640);
          break;
        }
      }
      textAlign(LEFT);
      text(parola[36]+"\n"+
        parola[37]+"\n"+
        parola[38]+"\n"+
        parola[39]+"\n"+
        parola[40]+"\n"+
        parola[41]+"\n"+
        parola[42]+"\n"+
        parola[43]+"\n"+
        parola[44], 10*width/640, 30*height/360)
        ;
    } else if ( tutorial == 3) {
      for (int i = 0; i < 40; i++)
        if (parola[79].length()*i*width/640 >= 640*width/640) {
          textSize((i-1)*2*width/640);
          println((i-1)*2*width/640);
          break;
        }
    }
  }

  if (tutorial == 1) {
    impossibile();
    if (sc < 15 && conto >=120)
      sc++;
    else if (sc >= 15 && conto >=120) {
      sc = 0;
    }
    fill(230);
    text(parola[45]+"\n"+
      parola[46]+"\n"+
      parola[47]+"\n"+
      parola[48]+"\n"+
      parola[49]+"\n"+
      parola[50], 10*width/640, 200*height/360)
      ;
  } else if (tutorial == 2) {
    impossibile();
    if (sc < 15 && conto >=120)
      sc++;
    else if (sc >= 15 && conto >=120) {
      sc = 0;
    }
    fill(230);
    text(parola[51]+"\n"+
      parola[52]+"\n"+
      parola[53]+"\n"+
      parola[54]+"\n"+
      parola[47]+"\n"+
      parola[48]+"\n"+
      parola[49]+"\n"+
      parola[50], 10*width/640, 200*height/360)
      ;
  }

  if (tutorial == 3) {
    textAlign(LEFT);
    background(255);
    fill(0);
    text(parola[78]+"\n"+
      parola[79], 10*width/640, 25*height/360);

    image(SW, 10*width/640, 60*height/360, 50*width/640, 50*width/640);
    text(parola[80]+"\n"+
      parola[81]+"\n"+
      parola[82]+"\n", 70*width/640, 80*height/360);
  }
}

public void impossibile() {
  if (tutorial == 2 && sc >= 10) {
    fill(255, 0, 255);
  } else {
    fill(240);
  }
  rect(410*width/640, 10*height/360, 230*width/640, 153*height/360); //Grande "quadrato"
  supFill();
  rect(410*width/640, (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360);
  if (tutorial == 1)
    tut1();
  if (tutorial == 2)
    tut2();
  for (int i = 0; i< 10; i++) {
    line(410*width/640, (10*height/360)+(17*height/360*i), 640*width/640, (10*height/360)+(17*height/360*i)); 
    line((410*width/640)+(i*23*width/640), 10*height/360, (410*width/640)+(i*23*width/640), 170*height/360);
  }
}

public void supFill() {
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
  fill(R, G, B);
}

public void tut2() {
  if (sc >= 0) {
    fill(255, 0, 255);
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(9*23*width/640), (10*height/360)+(17*height/360*2), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(5*23*width/640), (10*height/360)+(17*height/360*1), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato viola
  }
  if (sc >= 2) {
    rect((410*width/640)+(6*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(2*23*width/640), (10*height/360)+(17*height/360*2), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(8*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(4*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
  }
  if (sc >= 4) {
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*1), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(5*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato viola
  }

  if (sc >= 6) {
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(5*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
  }

  if (sc >= 8) {
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato viola / giallo
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato viola
    rect((410*width/640)+(2*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola
  }

  if (sc >= 10) {
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato viola / giallo
  }



  if (sc >= 15) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Grande quadrato rosso
  } 
  if (sc >= 13) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 11) {
    fill(255, 255, 0);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 9) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 7) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 5) {
    fill(255, 255, 0);
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 3) {
    fill(255, 255, 0);
    rect((410*width/640)+(6*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 1) {
    fill(255, 255, 0);
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 14) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Grande quadrato verde
  } 
  if (sc >= 12) {
    fill(g2.RGB);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*9*width/640, (17*height/360*2)); //Grande quadrato rosso
  }
  if (sc >= 10) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*4), 23*3*width/640, (17*height/360*3)); //Grande quadrato verde
  }
  if (sc >= 8) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato rosso
  }
  if (sc >= 6) {
    fill(g1.RGB);
    rect((410*width/640)+(3*23*width/640), (10*height/360), 23*7*width/640, (17*height/360*7)); //Grande quadrato verde
  }
  if (sc >= 4) {
    fill(g2.RGB);
    rect((410*width/640)+(6*23*width/640), (10*height/360), 23*4*width/640, (17*height/360*5)); //Grande quadrato rosso
  }
  if (sc >= 2) {
    fill(g1.RGB);
    rect((410*width/640)+(7*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato verde
  }
}

public void tut1() { 
  if (sc >= 15) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Grande quadrato rosso
  } 
  if (sc >= 13) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 11) {
    fill(255, 255, 0);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*8), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 9) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 7) {
    fill(255, 255, 0);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 5) {
    fill(255, 255, 0);
    rect((410*width/640)+(3*23*width/640), (10*height/360)+(17*height/360*6), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 3) {
    fill(255, 255, 0);
    rect((410*width/640)+(6*23*width/640), (10*height/360)+(17*height/360*4), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 1) {
    fill(255, 255, 0);
    rect((410*width/640)+(7*23*width/640), (10*height/360)+(17*height/360*3), 23*width/640, 17*height/360); //Quadrato giallo
  }
  if (sc >= 14) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*7), 23*width/640, 17*height/360); //Grande quadrato verde
  } 
  if (sc >= 12) {
    fill(g2.RGB);
    rect((410*width/640)+(1*23*width/640), (10*height/360)+(17*height/360*7), 23*9*width/640, (17*height/360*2)); //Grande quadrato rosso
  }
  if (sc >= 10) {
    fill(g1.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360)+(17*height/360*4), 23*3*width/640, (17*height/360*3)); //Grande quadrato verde
  }
  if (sc >= 8) {
    fill(g2.RGB);
    rect((410*width/640)+(0*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato rosso
  }
  if (sc >= 6) {
    fill(g1.RGB);
    rect((410*width/640)+(3*23*width/640), (10*height/360), 23*7*width/640, (17*height/360*7)); //Grande quadrato verde
  }
  if (sc >= 4) {
    fill(g2.RGB);
    rect((410*width/640)+(6*23*width/640), (10*height/360), 23*4*width/640, (17*height/360*5)); //Grande quadrato rosso
  }
  if (sc >= 2) {
    fill(g1.RGB);
    rect((410*width/640)+(7*23*width/640), (10*height/360), 23*3*width/640, (17*height/360*4)); //Grande quadrato verde
  }
}
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
public void credits() {  
  background(175);
  image(CC, 280*width/640, 100*height/360, 80*width/640, 80*width/640);
  //image(icon, 280*width/640, 190*height/360, 80*width/640, 80*width/640);
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 100*height/360 && mouseY <= 180*height/360) {
    background(175);
    text("Github (link)", 320*width/640, 220*height/360);
    image(CC, 280*width/640, 100*height/360, 80*width/640, 80*width/640);
  }
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 190*height/360 && mouseY <= 270*height/360) {
    //background(175);
    //text("Iconfinder (link)", 300*width/640, 160*height/360);
    //image(icon, 280*width/640, 190*height/360, 80*width/640, 80*width/640);
  }
  textSize(20*width/640);
  text("Credits: \nOhSerK", 320*width/640, 50*height/360);
}

public void creditRL() {
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 190*height/360 && mouseY <= 270*height/360);
    //link("https://www.iconfinder.com");
    //link("http://creativecommons.org/licenses/by/3.0/");
  if (mouseX >= 280*width/640 && mouseX <= 360*width/640 && mouseY >= 100*height/360 && mouseY <= 180*height/360)
    link("https://github.com/OhSerk/Raster");
}


//Bluetooth
public void provaBt() {/*
  if (!bluetoothAdapter.isEnabled()) {
    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); 
    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
  }
/*
  Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
  // If there are paired devices
  if (pairedDevices.size() > 0) {
    // Loop through paired devices
    for (BluetoothDevice device : pairedDevices) {
      // Add the name and address to an array adapter to show in a ListView
      mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
    }
  }
}
/*
  final BroadcastReceiver mReceiver = new BroadcastReceiver() {
 public void onReceive(Context context, Intent intent) {
 String action = intent.getAction();
 // When discovery finds a device
 if (BluetoothDevice.ACTION_FOUND.equals(action)) {
 // Get the BluetoothDevice object from the Intent
 BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
 // Add the name and address to an array adapter to show in a ListView
 mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
 }
 }
 };
 // Register the BroadcastReceiver
 IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
 registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy*/
}
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
class Quadrato {
  int px, py, codice, player;
  boolean active, colore, posizione, releaseds = false, zona;
  boolean segno = false, cont = false;
  float lax, lay, lux, luy;
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
    } else 
    stroke(1);

    noStroke();
    colora();
    if (playopts == false) {
      rect((23*px)*width/640, (23*py)*height/360, 22.5f*width/640, 22.5f*width/640);
      perks(ax, bx);// cx, dx);
    }
    noStroke();
  }


  public void gioco() {
    if (IA == false && (g1.play || g2.play || g3.play || g4.play) ) { //Se gioca un utente
      if (mouseX >= (23*px)*width/640 && mouseX  <= (((23*px)*width/640) + 22.5f*width/640)  && mouseY>= (23*py)*height/360 && mouseY  <= (((23*py)*height/360) + 22.5f*height/360) && active == false) {
        for (int i = 0; i < qx; i++) 
          for (int j = 0; j < qy; j++) 
            q[i][j].posizione = false;
        posizione = true;
        cont = true;
        if (gameHelp)
          Giallo();
      } else if ((mouseX >= (23*px)*width/640 && mouseX  <= (((23*px)*width/640) + 22.5f*width/640)  && mouseY>= (23*py)*height/360 && mouseY  <= (((23*py)*height/360) + 22.5f*height/360) && active == true && touch == false && pU == true && !playopts)) {
        swish(px-1, py-1);
      } else if (mouseX >= (23*q[0][q[0].length-1].px)*width/640 && mouseX  <= (((23*q[0][q[0].length-1].px)*width/640) + 22.5f*width/640)  && mouseY>= (23*q[0][q[0].length-1].py)*height/360 && mouseY  <= (((23*q[0][q[0].length-1].py)*height/360) + 22.5f*height/360) && totale <= qx*qy-5) {
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
      if (toccoX >= (23*px)*width/640 && toccoX  <= (((23*px)*width/640) + 22.5f*width/640)  && toccoY>= (23*py)*height/360 && toccoY  <= (((23*py)*height/360) + 22.5f*height/360) && active == false) {
        posizione = true;
      } else if (toccoX >= (23*q[0][q[0].length-1].px)*width/640 && toccoX  <= (((23*q[0][q[0].length-1].px)*width/640) + 22.5f*width/640)  && toccoY>= (23*q[0][q[0].length-1].py)*height/360 && toccoY  <= (((23*q[0][q[0].length-1].py)*height/360) + 22.5f*height/360) && totale <= qx*qy-5) {
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
        image(SW, (23*q[swx][swy].px)*width/640, (23*q[swx][swy].py)*height/360, 22.5f*width/640, 22.5f*width/640);
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
int c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
int C1, C2, C3, C4, C5, C6, C7, C8, C9, C10;
int ax, bx, cx, dx;
public void mossa() { //Possibile mosse disponibili dal giocatore
  if (modes == 2) {
    c2 = c1;
    c4 = c3;
    c6 = c5;
    c8 = c7;
    c10 = c9;
    C2 = C1;
    C4 = C3;
    C6 = C5;
    C8 = C7;
    C10 = C9;
    C1 = PApplet.parseInt(random(0, qy));
    C3 = PApplet.parseInt(random(0, qy));
    C5 = PApplet.parseInt(random(0, qy));
    C7 = PApplet.parseInt(random(0, qy));
    C9 = PApplet.parseInt(random(0, qy));
    c1 = PApplet.parseInt(random(0, qx));
    c3 = PApplet.parseInt(random(0, qx));
    c5 = PApplet.parseInt(random(0, qx));
    c7 = PApplet.parseInt(random(0, qx));
    c9 = PApplet.parseInt(random(0, qx));
    q[c1][C1].zona = true;
    q[c3][C3].zona = true;
    q[c5][C5].zona = true;
    q[c7][C7].zona = true;
    q[c9][C9].zona = true;

    if (totale > 230) {
      for (int iii = 0; iii < qy; iii++)
        for (int ii = 0; ii < qx; ii++) {
          if (g1.play == true || g2.play == true || g3.play == true || g4.play == true)
            q[ii][iii].zona = true;
          else
            q[ii][iii].zona = false;
        }
    }
  }
}

public void noZona() {
  ax = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
  bx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  cx = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
  dx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  if ( ax == 0 && bx == 9) {
    ax = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    bx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  }
  if ( cx == 0 && dx == 9) {
    cx = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    dx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  }
  if (ax == cx && bx == dx) {
    ax = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    bx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
    cx = PApplet.parseInt(random(0, PApplet.parseInt(qy/2)));
    dx = PApplet.parseInt(random(PApplet.parseInt(qy/2), qy));
  }

  if (modes == 2) {
    q[c2][C2].zona = false;
    q[c4][C4].zona = false;
    q[c6][C6].zona = false;
    q[c8][C8].zona = false;
    q[c10][C10].zona = false;
  }
}

public void CPUNO() {
  if (modes == 2) {
    for (int y = 0; y < qy; y++) 
      for (int x = 0; x < qx; x++) 
        q[x][y].zona = false;
  }
}

public void lessPoints(boolean k) {
  if (g1.play == true && g1.p2 == true && k == true) {
  }
}
public void swish(int sx, int sy) {
  int sw1, sw2, sw3, sw4;
  boolean troia1 = false, troia2 = false, troia3 = false, troia4 = false ;
  boolean gg1, gg2, gg3, gg4;
  gg1 = gg2 = gg3 = gg4 = false;
  sw1 = g1.punt;
  if (bot2.exist) sw2 = bot2.punt;
  else sw2 = g2.punt;
  if (bot3.exist) sw3 = bot3.punt;
  else sw3 = g3.punt;
  if (bot4.exist) sw4 = bot4.punt;
  else sw4 = g4.punt;
  if (g1.play == true && g1.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g2.exist == true && g2.squalifica == false) || (bot2.exist == true && bot2.squalifica == false)) && (q[sx][sy].player == 2 || troia2 == true)) {
          troia2 = true;
          if (q[i2][ii].player == 2) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 2;

          if (g2.exist)
            g2.punt = sw1;
          else
            bot2.punt = sw1;
          g1.punt = sw2;
        } else if (((g3.exist == true && g3.squalifica == false) || (bot3.exist == true && bot3.squalifica == false)) && (q[sx][sy].player == 3 || troia3 == true)) {
          troia3 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 3;

          if (g3.exist)
            g3.punt = sw1;
          else
            bot3.punt = sw1;
          g1.punt = sw3;
        } else if (((g4.exist == true && g4.squalifica == false) || (bot4.exist == true && bot4.squalifica == false)) && (q[sx][sy].player == 4 || troia4 == true)) {
          troia4 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 4;

          if (g4.exist)
            g4.punt = sw1;
          else
            bot4.punt = sw1;
          g1.punt = sw4;
        }
      }
    gg1 = true;
  } //Chiusura condizione player 1
  else if (g2.play == true && g2.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g1.exist == true && g1.squalifica == false)) && (q[sx][sy].player == 1 || troia1 == true)) {
          troia1 = true;
          if (q[i2][ii].player == 2) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 2;
          g2.punt = sw1;
          g1.punt = sw2;
        } else if (((g3.exist == true && g3.squalifica == false) || (bot3.exist == true && bot3.squalifica == false)) && (q[sx][sy].player == 3 || troia3 == true)) {
          troia3 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2)
            q[i2][ii].player = 3;

          if (g3.exist)
            g3.punt = sw2;
          else
            bot3.punt = sw2;
          g2.punt = sw3;
        } else if (((g4.exist == true && g4.squalifica == false) || (bot4.exist == true && bot4.squalifica == false)) && (q[sx][sy].player == 4 || troia4 == true)) {
          troia4 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2 )
            q[i2][ii].player = 4;

          if (g4.exist)
            g4.punt = sw2;
          else
            bot4.punt = sw2;
          g2.punt = sw4;
        }
      }
    gg2 = true;
  } //Chiusura condizione player 2
  else if (g3.play == true && g3.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g1.exist == true && g1.squalifica == false)) && (q[sx][sy].player == 1 || troia1 == true)) {
          troia1 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 3;
          g1.punt = sw3;
          g3.punt = sw1;
        } else if (((g2.exist == true && g2.squalifica == false) || (bot2.exist == true && bot2.squalifica == false)) && (q[sx][sy].player == 2 || troia2 == true)) {
          troia2 = true;
          if (q[i2][ii].player == 3) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2)
            q[i2][ii].player = 3;

          g3.punt = sw2;
          if (g2.exist)
            g2.punt = sw3;
          else
            bot2.punt = sw3;
        } else if (((g4.exist == true && g4.squalifica == false) || (bot4.exist == true && bot4.squalifica == false)) && (q[sx][sy].player == 4 || troia4 == true)) {
          troia4 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 3;
          else if (q[i2][ii].player == 3 )
            q[i2][ii].player = 4;

          if (g4.exist)
            g4.punt = sw3;
          else
            bot4.punt = sw3;
          g3.punt = sw4;
        }
      }
    gg3 = true;
  } //Chiusura condizione player 3
  else if (g4.play == true && g4.p1 == true) {
    for (int i2 = 0; i2 < qx; i2++)
      for (int ii = 0; ii< qy; ii++) {
        if (((g1.exist == true && g1.squalifica == false)) && (q[sx][sy].player == 1 || troia1 == true)) {
          troia1 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 1;
          else if (q[i2][ii].player ==1)
            q[i2][ii].player = 4;
          g1.punt = sw4;
          g4.punt = sw1;
        } else if (((g2.exist == true && g2.squalifica == false) || (bot2.exist == true && bot2.squalifica == false)) && (q[sx][sy].player == 2 || troia2 == true)) {
          troia2 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 2;
          else if (q[i2][ii].player == 2)
            q[i2][ii].player = 4;

          g4.punt = sw2;
          if (g2.exist)
            g2.punt = sw4;
          else
            bot2.punt = sw4;
        } else if (((g3.exist == true && g3.squalifica == false) || (bot3.exist == true && bot3.squalifica == false)) && (q[sx][sy].player == 3 || troia3 == true)) {
          troia3 = true;
          if (q[i2][ii].player == 4) 
            q[i2][ii].player = 3;
          else if (q[i2][ii].player == 3 )
            q[i2][ii].player = 4;

          g4.punt = sw3;
          if (g3.exist)
            g3.punt = sw4;
          else
            bot3.punt = sw4;
        }
      }
    gg4 = true;
  } //Chiusura condizione player 4
  G1 = g1.punt;
  G2 = g2.punt;
  G3 = g3.punt;
  G4 = g4.punt;
  if (gg1 == true) g1.p1 = false;
  if (gg2 == true) g2.p1 = false;
  if (gg3 == true) g3.p1 = false;
  if (gg4 == true) g4.p1 = false;
}

int curs = 1;
public void selectLingua() {
  background(75);
  if (mouseX >= 250*width/640 && mouseX <= 390*width/640 && mouseY >= 140*height/360 && mouseY <= 220*height/360) {
    mouseX = 100;
  } 
  fill(200);
  textSize( 28*width/640);
  text(parola[31], 320*width/640, 50*height/360); //Lingua
  imageMode(CENTER);
  image(Flag[curs], 320*width/640, 180*height/360, 210*width/640, 160*height/360);
  imageMode(CORNER);
  text(language, 320*width/640, 300*height/360);
  if (mouseX >= 90*width/640 && mouseX <= 150*width/640 && mouseY >= 150*height/360 && mouseY <= 210*height/360) {
    textSize(80*width/640);
    fill(255, 0, 0);
    mouseX = 1000;
  } else {
    fill(200);
    textSize(54*width/640);
  }
  text("<", 120*width/640, 180*height/360);
  if (mouseX >= 470*width/640 && mouseX <= 530*width/640 && mouseY >= 150*height/360 && mouseY <= 210*height/360) {
    textSize(80*width/640);
    fill(255, 0, 0);
    mouseX = 1000;
  } else {
    fill(200);
    textSize(54*width/640);
  }
  text(">", 500*width/640, 180*height/360);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TehRaster" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
