# Projet multimodal avec processing
Projet réalisé par Emilien Vesin et Ambre Rouzade dans le cadre de la 3ème année d'école d'ingénieur en Systèmes Robotiques et Interactifs à l'UPSSITECH.
Ce projet permet d'afficher, dessiner, déplacer et supprimer des formes en utilisant IVY et sra5. Il y a également la possibilité de choisir la couleur.

Vous trouverez la vidéo de démonstration ici: https://github.com/Emilioprog/IHM-Multimodale/blob/master/Video_exemple.mp4 

# Utilisation

## Installation

Vous devez posséder java 17 sur votre ordinateur puis télécharger ce projet.

## Lancement

Pour lancer notre moteur multimodal, il suffit de lancer dans l'ordre énoncées les fichiers suivants:
- IHM-Multimodale/sra5/sra5_on.bat  (contient la grammaire parole, reconnaissance vocale)
- IHM-Multimodale/icar/Icarivy.bat   (contient le dictionnaire de forme chargé, il va donc reconnaître à chaque fois la forme que l'on dessine, si elle est comprise dans la dictionnaire_formes)
- IHM-Multimodale/visionneur_1_2/visionneur.bat   (permet de visionner tout ce qu'il se passe dans le système)
- IHM-Multimodale/Projet/Palette.exe   (fichier principal de multifusion, permettant de lier les différentes éléments multimodaux. Les formes et les commandes s'afficheront ici)

## Actions réalisables
###  Creer une forme
- prononcer "DESSINER + TRIANGLE (RECTANGLE/LOSANGE/CERCLE) [+ couleur] + ICI" + cliquer au niveau de l'emplacement désiré
- prononcer "CREER + CETTE FORME [+ couleur] + ICI" + dessiner la forme dans ICAR + cliquer au niveau de l'emplacement désiré
### Déplacer une forme
- prononcer "DEPLACER + CETTE FORME ICI" + cliquer sur la forme à déplacer + cliquer sur l'emplacement désiré
### Supprimer une forme
- prononcer "SUPPRIMER + CETTE FORME" + cliquer sur la forme à supprimer

## Grammaire parole
CREATE={créer, dessiner, tracer}

DELETE={supprimer, effacer}

MOVE={déplacer, bouger}



THIS={cette forme, ça}

THERE={là, ici, à cet endroit}

forme={triangle, losange, cercle, rectangle}

couleur={rouge, orange, jaune, vert, bleu, violet, noir}

# Chronogrammes

![image](https://github.com/user-attachments/assets/ec25c57c-c4da-46bb-ad83-883c36992025)

# Machine à états

![image](https://github.com/user-attachments/assets/921214b1-e7c8-4ef0-b58e-8353e99f6057)

