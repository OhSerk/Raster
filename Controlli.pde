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
        else if (g1.squalifica == true && bot2.squalifica == false && bot3.squalifica == false && bot4.squalifica == false) {// && (BOT2 != bot2.punt || BOT3 != bot3.punt || BOT4 != bot4.punt)) { //G1 squalificato
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
  stroke(0);
  rect(200*width/640, 135*height/360, 250*width/640, 80*height/360); //Bottone per il main menù
  rect(0, 320*height/360, width, height); //Jarsick Games

  textSize(18*height/360);
  if (victory != parola[8]+"!") {
    text(parola[19]+"\n"+victory, 320*width/640, 100*height/360);
  } else
    text(victory, 320*width/640, 100*height/360);
  fill(0);
  text(parola[98], 320*width/640, 345*height/360); //https://play.google.com/store/search?q=pub%3AJarsick
  text(parola[74]+"?", 320*width/640, 250*height/360); //Salva screen?
  if (TSP == 0) //Resetto questa variabile quando cambio la lingua
    for (int ix = 15; ix < 40; ix++) {
      if ((parola[20].length()/2)*ix*height/360 >= 450) {
        TSP = ix;
        break;
      }
    }
  textSize(TSP*height/360);
 // println(TSP);
  text(parola[20], 320*width/640, 180*height/360);
  text(parola[71+int(!take)], 320*width/640, 290*height/360); //Si no
  textSize(18*height/360);
}
int TSP = 0;