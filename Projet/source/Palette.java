/* autogenerated by Processing revision 1290 on 2025-12-31 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import fr.dgac.ivy.*;
import fr.dgac.ivy.tools.*;
import gnu.getopt.*;

import fr.dgac.ivy.*;
import java.util.*;
import java.awt.Point;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class Palette extends PApplet {

///*
// * Palette Graphique - prélude au projet multimodal 3A SRI
// * 4 objets gérés : cercle, rectangle(carré), losange et triangle
// * (c) 05/11/2019
// * Dernière révision : 28/04/2020
// */
 
//import java.awt.Point;

//ArrayList<Forme> formes; // liste de formes stockées
//FSM mae; // Finite Sate Machine
//int indice_forme;
//PImage sketch_icon;


//void setup() {
//  size(800,600);
//  surface.setResizable(true);
//  surface.setTitle("Palette multimodale");
//  surface.setLocation(20,20);
//  sketch_icon = loadImage("Palette.jpg");
//  surface.setIcon(sketch_icon);
  
//  formes= new ArrayList(); // nous créons une liste vide
//  noStroke();
//  mae = FSM.INITIAL;
//  indice_forme = -1;
//}

//void draw() {
//  background(0);
//  println("MAE : " + mae + " indice forme active ; " + indice_forme);
//  switch (mae) {
//    case INITIAL:  // Etat INITIAL
//      background(255);
//      fill(0);
//      text("Etat initial (c(ercle)/l(osange)/r(ectangle)/t(riangle) pour créer la forme à la position courante)", 50,50);
//      text("m(ove)+ click pour sélectionner un objet et click pour sa nouvelle position", 50,80);
//      text("click sur un objet pour changer sa couleur de manière aléatoire", 50,110);
//      break;
      
//    case AFFICHER_FORMES:  // 
//    case DEPLACER_FORMES_SELECTION: 
//    case DEPLACER_FORMES_DESTINATION: 
//      affiche();
//      break;   
      
//    default:
//      break;
//  }  
//}

//// fonction d'affichage des formes m
//void affiche() {
//  background(255);
//  /* afficher tous les objets */
//  for (int i=0;i<formes.size();i++) // on affiche les objets de la liste
//    (formes.get(i)).update();
//}

//void mousePressed() { // sur l'événement clic
//  Point p = new Point(mouseX,mouseY);
  
//  switch (mae) {
//    case AFFICHER_FORMES:
//      for (int i=0;i<formes.size();i++) { // we're trying every object in the list
//        // println((formes.get(i)).isClicked(p));
//        if ((formes.get(i)).isClicked(p)) {
//          (formes.get(i)).setColor(color(random(0,255),random(0,255),random(0,255)));
//        }
//      } 
//      break;
      
//   case DEPLACER_FORMES_SELECTION:
//     for (int i=0;i<formes.size();i++) { // we're trying every object in the list        
//        if ((formes.get(i)).isClicked(p)) {
//          indice_forme = i;
//          mae = FSM.DEPLACER_FORMES_DESTINATION;
//        }         
//     }
//     if (indice_forme == -1)
//       mae= FSM.AFFICHER_FORMES;
//     break;
     
//   case DEPLACER_FORMES_DESTINATION:
//     if (indice_forme !=-1)
//       (formes.get(indice_forme)).setLocation(new Point(mouseX,mouseY));
//     indice_forme=-1;
//     mae=FSM.AFFICHER_FORMES;
//     break;
     
//    default:
//      break;
//  }
//}


//void keyPressed() {
//  Point p = new Point(mouseX,mouseY);
//  switch(key) {
//    case 'r':
//      Forme f= new Rectangle(p);
//      formes.add(f);
//      mae=FSM.AFFICHER_FORMES;
//      break;
      
//    case 'c':
//      Forme f2=new Cercle(p);
//      formes.add(f2);
//      mae=FSM.AFFICHER_FORMES;
//      break;
    
//    case 't':
//      Forme f3=new Triangle(p);
//      formes.add(f3);
//       mae=FSM.AFFICHER_FORMES;
//      break;  
      
//    case 'l':
//      Forme f4=new Losange(p);
//      formes.add(f4);
//      mae=FSM.AFFICHER_FORMES;
//      break;    
      
//    case 'm' : // move
//      mae=FSM.DEPLACER_FORMES_SELECTION;
//      break;
//  }
//}
/*
 * Classe Cercle
 */ 
 
public class Cercle extends Forme {
  
  int rayon;
  
  public Cercle(Point p) {
    super(p);
    this.rayon=80;
  }
   
  public void update() {
    fill(this.c);
    circle((int) this.origin.getX(),(int) this.origin.getY(),this.rayon);
  }  
   
  public boolean isClicked(Point p) {
    // vérifier que le cercle est cliqué
   PVector OM= new PVector( (int) (p.getX() - this.origin.getX()),(int) (p.getY() - this.origin.getY())); 
   if (OM.mag() <= this.rayon/2)
     return(true);
   else 
     return(false);
  }
  
  protected double perimetre() {
    return(2*PI*this.rayon);
  }
  
  protected double aire(){
    return(PI*this.rayon*this.rayon);
  }
}
/*
 * Enumération de a Machine à Etats (Finite State Machine)
 *
 *
 */
 
public enum FSM {
  INITIAL, /* Etat Initial */ 
  WAITING,
  AFFICHER_FORMES, 
  DEPLACER_FORMES_SELECTION,
  DEPLACER_FORMES_DESTINATION,
  ACTION,
  SUPPRIMER_FORMES_SELECTION
}
/*****
 * Création d'un nouvelle classe objet : Forme (Cercle, Rectangle, Triangle
 * 
 * Date dernière modification : 28/10/2019
 */

abstract class Forme {
 Point origin;
 int c;
 
 Forme(Point p) {
   this.origin=p;
   this.c = color(127);
 }
 
 public void setColor(int c) {
   this.c=c;
 }
 
 public int getColor(){
   return(this.c);
 }
 
 public abstract void update();
 
 public Point getLocation() {
   return(this.origin);
 }
 
 public void setLocation(Point p) {
   this.origin = p;
 }
 
 public abstract boolean isClicked(Point p);
 
 // Calcul de la distance entre 2 points
 protected double distance(Point A, Point B) {
    PVector AB = new PVector( (int) (B.getX() - A.getX()),(int) (B.getY() - A.getY())); 
    return(AB.mag());
 }
 
 protected abstract double perimetre();
 protected abstract double aire();
}
/*
 * Classe Losange
 */ 
 
public class Losange extends Forme {
  Point A, B,C,D;
  
  public Losange(Point p) {
    super(p);
    // placement des points
    A = new Point();    
    A.setLocation(p);
    B = new Point();    
    B.setLocation(A);
    C = new Point();  
    C.setLocation(A);
    D = new Point();
    D.setLocation(A);
    B.translate(40,60);
    D.translate(-40,60);
    C.translate(0,120);
  }
  
  public void setLocation(Point p) {
      super.setLocation(p);
      // redéfinition de l'emplacement des points
      A.setLocation(p);   
      B.setLocation(A);  
      C.setLocation(A);
      D.setLocation(A);
      B.translate(40,60);
      D.translate(-40,60);
      C.translate(0,120);   
  }
  
  public void update() {
    fill(this.c);
    quad((float) A.getX(), (float) A.getY(), (float) B.getX(), (float) B.getY(), (float) C.getX(), (float) C.getY(),  (float) D.getX(),  (float) D.getY());
  }  
  
  public boolean isClicked(Point M) {
    // vérifier que le losange est cliqué
    // aire du rectangle AMD + AMB + BMC + CMD = aire losange  
    if (round( (float) (aire_triangle(A,M,D) + aire_triangle(A,M,B) + aire_triangle(B,M,C) + aire_triangle(C,M,D))) == round((float) aire()))
      return(true);
    else 
      return(false);  
  }
  
  protected double perimetre() {
    //
    PVector AB= new PVector( (int) (B.getX() - A.getX()),(int) (B.getY() - A.getY())); 
    PVector BC= new PVector( (int) (C.getX() - B.getX()),(int) (C.getY() - B.getY())); 
    PVector CD= new PVector( (int) (D.getX() - C.getX()),(int) (D.getY() - C.getY())); 
    PVector DA= new PVector( (int) (A.getX() - D.getX()),(int) (A.getY() - D.getY())); 
    return( AB.mag()+BC.mag()+CD.mag()+DA.mag()); 
  }
  
  protected double aire(){
    PVector AC= new PVector( (int) (C.getX() - A.getX()),(int) (C.getY() - A.getY())); 
    PVector BD= new PVector( (int) (D.getX() - B.getX()),(int) (D.getY() - B.getY())); 
    return((AC.mag()*BD.mag())/2);
  } 
  
  private double perimetre_triangle(Point I, Point J, Point K) {
    //
    PVector IJ= new PVector( (int) (J.getX() - I.getX()),(int) (J.getY() - I.getY())); 
    PVector JK= new PVector( (int) (K.getX() - J.getX()),(int) (K.getY() - J.getY())); 
    PVector KI= new PVector( (int) (I.getX() - K.getX()),(int) (I.getY() - K.getY())); 
    
    return( IJ.mag()+JK.mag()+KI.mag()); 
  }
   
  // Calcul de l'aire d'un triangle par la méthode de Héron 
  private double aire_triangle(Point I, Point J, Point K){
    double s = perimetre_triangle(I,J,K)/2;
    double aire = s*(s-distance(I,J))*(s-distance(J,K))*(s-distance(K,I));
    return(sqrt((float) aire));
  }
}
/*
 * Classe Rectangle
 */ 
 
public class Rectangle extends Forme {
  
  int longueur;
  
  public Rectangle(Point p) {
    super(p);
    this.longueur=60;
  }
   
  public void update() {
    fill(this.c);
    square((int) this.origin.getX(),(int) this.origin.getY(),this.longueur);
  }  
  
  public boolean isClicked(Point p) {
    int x= (int) p.getX();
    int y= (int) p.getY();
    int x0 = (int) this.origin.getX();
    int y0 = (int) this.origin.getY();
    
    // vérifier que le rectangle est cliqué
    if ((x>x0) && (x<x0+this.longueur) && (y>y0) && (y<y0+this.longueur))
      return(true);
    else  
      return(false);
  }
  
  // Calcul du périmètre du carré
  protected double perimetre() {
    return(this.longueur*4);
  }
  
  protected double aire(){
    return(this.longueur*this.longueur);
  }
}
/*
 * Classe Triangle
 */ 
 
public class Triangle extends Forme {
  Point A, B,C;
  public Triangle(Point p) {
    super(p);
    // placement des points
    A = new Point();    
    A.setLocation(p);
    B = new Point();    
    B.setLocation(A);
    C = new Point();    
    C.setLocation(A);
    B.translate(40,60);
    C.translate(-40,60);
  }
  
    public void setLocation(Point p) {
      super.setLocation(p);
      // redéfinition de l'emplacement des points
      A.setLocation(p);   
      B.setLocation(A);  
      C.setLocation(A);
      B.translate(40,60);
      C.translate(-40,60);   
  }
  
  public void update() {
    fill(this.c);
    triangle((float) A.getX(), (float) A.getY(), (float) B.getX(), (float) B.getY(), (float) C.getX(), (float) C.getY());
  }  
  
  public boolean isClicked(Point M) {
    // vérifier que le triangle est cliqué
    
    PVector AB= new PVector( (int) (B.getX() - A.getX()),(int) (B.getY() - A.getY())); 
    PVector AC= new PVector( (int) (C.getX() - A.getX()),(int) (C.getY() - A.getY())); 
    PVector AM= new PVector( (int) (M.getX() - A.getX()),(int) (M.getY() - A.getY())); 
    
    PVector BA= new PVector( (int) (A.getX() - B.getX()),(int) (A.getY() - B.getY())); 
    PVector BC= new PVector( (int) (C.getX() - B.getX()),(int) (C.getY() - B.getY())); 
    PVector BM= new PVector( (int) (M.getX() - B.getX()),(int) (M.getY() - B.getY())); 
    
    PVector CA= new PVector( (int) (A.getX() - C.getX()),(int) (A.getY() - C.getY())); 
    PVector CB= new PVector( (int) (B.getX() - C.getX()),(int) (B.getY() - C.getY())); 
    PVector CM= new PVector( (int) (M.getX() - C.getX()),(int) (M.getY() - C.getY())); 
    
    if ( ((AB.cross(AM)).dot(AM.cross(AC)) >=0) && ((BA.cross(BM)).dot(BM.cross(BC)) >=0) && ((CA.cross(CM)).dot(CM.cross(CB)) >=0) ) { 
      return(true);
    }
    else
      return(false);
  }
  
  protected double perimetre() {
    //
    PVector AB= new PVector( (int) (B.getX() - A.getX()),(int) (B.getY() - A.getY())); 
    PVector AC= new PVector( (int) (C.getX() - A.getX()),(int) (C.getY() - A.getY())); 
    PVector BC= new PVector( (int) (C.getX() - B.getX()),(int) (C.getY() - B.getY())); 
    
    return( AB.mag()+AC.mag()+BC.mag()); 
  }
   
  // Calcul de l'aire du triangle par la méthode de Héron 
  protected double aire(){
    double s = perimetre()/2;
    double aire = s*(s-distance(B,C))*(s-distance(A,C))*(s-distance(A,B));
    return(sqrt((float) aire));
  }
}




FSM currentState;
String recognizedAction = "";
String recognizedForm   = "";
String recognizedColor  = "";

int formeSelectionnee = -1;
ArrayList<Forme> formes;
Point point1;

Ivy bus, busIcar;

public void setup() {
  /* size commented out by preprocessor */;
  surface.setResizable(true);
  surface.setTitle("Palette multimodale");
  surface.setLocation(20, 20);

  formes = new ArrayList<>();
  currentState = FSM.INITIAL;

  noStroke();

  try {
      bus = new Ivy("sra5", "sra_tts_bridge is ready", null);
      busIcar = new Ivy("ICAR", "ICAR_bridge is ready", null);

      bus.start("127.255.255.255:2010");
      busIcar.start("127.255.255.255:2010");

      bus.bindMsg("^sra5 Text=(.*) Confidence=(.*)", new IvyMessageListener() {
        public void receive(IvyClient client,String[] args) {
          println("Sra5 : " + args[0]);
          parseVoiceMessage(args[0]);
        }
      });

      bus.bindMsg("^sra5 Event=Speech_Rejected", (client, args) -> {
          println("Commande vocale non reconnue.");
      });

      bus.bindMsg("^sra5 Parsed=(.*) Confidence=(.*) NP=.*", new IvyMessageListener() {
        public void receive(IvyClient client, String[] args) {
          String parsed = args[0];
          float conf = Float.parseFloat(args[1]);
          println("Vous avez dit : " + parsed + " avec un taux de confiance de " + conf);
          parseVoiceMessage(parsed);
        }
      });

      busIcar.bindMsg("^ICAR Gesture=(.*)", new IvyMessageListener() {
        public void receive(IvyClient client, String[] args) {
          println("ICAR : " + args[0]);
          parseIcarGesture(args[0]);
        }
      });

  } catch (IvyException e) {
      e.printStackTrace();
  }
}

public void parseVoiceMessage(String message) {
  recognizedAction = "";
  recognizedForm   = "";
  recognizedColor  = "";

  String[] tokens = splitTokens(message, " ");

  for (String t : tokens) {
    t = t.trim();
    if (t.startsWith("action=")) {
      recognizedAction = t.substring("action=".length()).toUpperCase();
    } 
    else if (t.startsWith("form=")) {
      recognizedForm = t.substring("form=".length()).toUpperCase();
    }
    else if (t.startsWith("color=")) {
      recognizedColor = t.substring("color=".length()).toUpperCase();
    }
  }

  switch(recognizedAction) {
    case "CREATE":
      currentState = FSM.ACTION;
      break;
    case "MOVE":
      currentState = FSM.DEPLACER_FORMES_SELECTION;
      break;
    case "DELETE":
      currentState = FSM.SUPPRIMER_FORMES_SELECTION;
      break;
    default:
      currentState = FSM.WAITING;
      println("Action inconnue ou non gérée : " + recognizedAction);
  }

  println("Action = " + recognizedAction + ", Forme = " + recognizedForm + ", Couleur = " + recognizedColor);
}

public void parseIcarGesture(String gesture) {
  String recognizedGesture = gesture.trim().toUpperCase();

  switch (recognizedGesture) {
    case "CIRCLE":
    case "TRIANGLE":
    case "RECTANGLE":
    case "DIAMOND":
      recognizedForm = recognizedGesture;
      recognizedAction = "CREATE";
      if (recognizedColor == null 
          || recognizedColor.isEmpty() 
          || recognizedColor.equals("UNDEFINED")) {
        recognizedColor = "DARK";
      }
      currentState = FSM.ACTION;
      break;

    default:
      println("Geste ICAR non reconnu : " + recognizedGesture);
      currentState = FSM.WAITING;
      break;
  }
}

public void draw() {
  background(255);
  for (Forme f : formes) {
    f.update();
  }

  switch (currentState) {
    case INITIAL:
      currentState = FSM.WAITING;
      break;

    case WAITING:
      fill(0);
      text("En attente de commande vocale ...", 20, 20);
      break;

    case ACTION:
      fill(0);
      text("Cliquez pour créer la forme " + recognizedForm + " de couleur " + recognizedColor, 20, 20);
      break;

    case DEPLACER_FORMES_SELECTION:
      fill(0);
      text("Cliquez sur la forme à déplacer", 20, 20);
      break;

    case DEPLACER_FORMES_DESTINATION:
      fill(0);
      text("Cliquez sur l’endroit où déplacer la forme", 20, 20);
      break;
      
    case SUPPRIMER_FORMES_SELECTION:
      fill(0);
      text("Cliquez sur la forme à supprimer ...", 20, 20);
      break;

    default:
      break;
  }
}

public void mousePressed() {
  Point p = new Point(mouseX, mouseY);

  switch (currentState) {
    case ACTION:
      if (recognizedAction.equals("CREATE")) {
        if (!recognizedForm.isEmpty()) {
          createForm(p, recognizedForm, recognizedColor);
          println("Forme créée : " + recognizedForm + " à " + p);
        } else {
          println("Aucune forme reconnue, on ne fait rien.");
        }
      }
      currentState = FSM.WAITING;
      break;

    case DEPLACER_FORMES_SELECTION:
      formeSelectionnee = findClickedFormeIndex(p);
      if (formeSelectionnee != -1) {
        println("Forme à déplacer sélectionnée : index " + formeSelectionnee);
        currentState = FSM.DEPLACER_FORMES_DESTINATION;
      }
      break;

    case DEPLACER_FORMES_DESTINATION:
      if (formeSelectionnee != -1) {
        formes.get(formeSelectionnee).setLocation(p);
        println("Forme déplacée à " + p);
      }
      currentState = FSM.WAITING;
      break;
      
    case SUPPRIMER_FORMES_SELECTION:
      int index = findClickedFormeIndex(p);
      if (index != -1) {
        formes.remove(index);
        println("Forme supprimée, index = " + index);
      }
      currentState = FSM.WAITING;
      break;

    default:
      break;
  }
}

public void createForm(Point p, String form, String colorName) {
  Forme newForm = null;
  switch(form) {
    case "CIRCLE":
      newForm = new Cercle(p);
      break;
    case "RECTANGLE":
      newForm = new Rectangle(p);
      break;
    case "DIAMOND":
      newForm = new Losange(p);
      break;
    case "TRIANGLE":
      newForm = new Triangle(p);
      break;
    default:
      return;
  }

  int c = translateColor(colorName);
  newForm.setColor(c);
  formes.add(newForm);
}

public int translateColor(String col) {
  String colorName = col.trim().toUpperCase();
  switch (colorName) {
    case "BLUE":
      return color(0, 0, 255);
    case "RED":
      return color(255, 0, 0);
    case "GREEN":
      return color(0, 255, 0);
    case "YELLOW":
      return color(255, 255, 0);
    case "ORANGE":
      return color(255, 128, 0);
    case "PURPLE":
      return color(238,130,238);
    case "DARK":
      return color(0, 0, 0);
    default:
      println("Couleur inconnue : " + colorName + ", on prend par défaut color(127)");
      return color(127);
  }
}

public int findClickedFormeIndex(Point p) {
  for (int i = 0; i < formes.size(); i++) {
    Forme f = formes.get(i);
    if (f.isClicked(p)) {
      return i;
    }
  }
  return -1;
}


  public void settings() { size(800, 600); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Palette" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
