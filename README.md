# Compteur de point pour un match de tennis

## Rappel des règles de tennis :

Chaque joueur peut avoir un score qui suit cette suite de nombre 0 15 30 40

* Si un joueur est à 40 et gagne la balle, il remporte le jeu. Cependant il existe une règle spéciale :
* Si les deux joueurs sont à 40, on dit qu'il y a égalité :
* S'il y a égalité, le vainqueur de la prochaine balle aura l'avantage.
* Si le joueur qui a l'avantage remporte la balle, il remporte le jeu
* Si le joueur qui n'a pas l'avantage remporte la balle, le jeu revient à égalité.

Pour une autre interprétation des règles, voici l'article de wikipedia sur les règles du tennis : https://fr.wikipedia.org/wiki/Tennis#R%C3%A8gles

## Exercice

Ecrire un programme qui prend en entrée un fichier avec la liste des vainqueurs de chaque balle d'un même match de tennis, et de produire le score (final ou non) du match.

Voici un exemple de fichier d'entrée

* Nadal
* Federer
* Nadal
* Nadal
* Nadal
* Federer
* Federer
* Nadal
* Nadal
* Federer
* Nadal
* Nadal
* Federer
* Federer
* Federer
* Nadal
* Nadal
* Federer

Le résultat attendu est 1 jeu partout, 30-15 pour Nadal. Explications :

* Nadal   //15-0
* Federer //15-15
* Nadal   //30-15
* Nadal   //40-15
* Nadal   //Jeu Nadal (1-0)
* Federer //15-0
* Federer //30-0
* Nadal   //30-15
* Nadal   //30-30
* Federer //40-30
* Nadal   //égalité
* Nadal   //avantage Nadal
* Federer //égalité
* Federer //avantage Federer
* Federer //Jeu Federer (1-1)
* Nadal   //15-0
* Nadal   //30-0
* Federer //30-15

## Implémentation

Le fichier avec la liste des joueurs gagnants est "score.txt" qui est enregistré dans le dossier "src/main/resources".
Pour lancer le programme, il faut exécuter la fonction com.mvl.tennis.Main.main().