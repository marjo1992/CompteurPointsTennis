package com.mvl.tennis.service;

import com.mvl.tennis.model.ScoreJoueur;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

class ScoreUtils {

    private ScoreUtils() {

    }

    /*
     * Actions sur les scores
     */

    static final UnaryOperator<ScoreJoueur> ajoutNouveauJeuGagne = scoreJoueur -> {
        scoreJoueur.nouveauJeuGagne();
        return scoreJoueur;
    };

    static final UnaryOperator<ScoreJoueur> reinitialiseScore = scoreJoueur -> {
        scoreJoueur.scoreActuelReinitialisation();
        return scoreJoueur;
    };

    /*
     * Prédicats des scores
     */

    private static final BiPredicate<ScoreJoueur, ScoreJoueur> scoreSuperieurAdversaire = (scoreGagnant, scorePerdant) ->
            scoreGagnant.getScoreActuel() > scorePerdant.getScoreActuel() + 1;

    private static final BiPredicate<ScoreJoueur, ScoreJoueur> scoreSuperieurTrois =
            (scoreGagnant, scorePerdant) -> scoreGagnant.getScoreActuel() > 3;

    static final BiPredicate<ScoreJoueur, ScoreJoueur> jeuEstGagne = scoreSuperieurAdversaire.and(scoreSuperieurTrois);

    static final BiPredicate<ScoreJoueur, String> gagneManche = (scoreJoueur, nomGagnantManche) ->
            scoreJoueur.getNom().equals(nomGagnantManche);

    /*
     * Fonctions pour construire les phrases de résultats à afficher
     */

    static final Function<Integer, String> plurielJeu = nbJeu -> nbJeu > 1 ? "x" : "";

    static Function<ImmutablePair<ScoreJoueur, ScoreJoueur>, String> getNomJoueurAvecLePlusDePoints = pair ->
            pair.left.getScoreActuel() > pair.right.getScoreActuel() ? pair.left.getNom() : pair.right.getNom();

    static final Function<Integer, String> phraseEgaliteJeu = nbJeu -> nbJeu + " jeu" + plurielJeu.apply(nbJeu) + " partout";

    static final Function<ScoreJoueur, String> phraseScoreJeuParJoueur = scoreJoueur ->
            scoreJoueur.getNbJeuGagnes() + " jeu" + plurielJeu.apply(scoreJoueur.getNbJeuGagnes()) + " pour " + scoreJoueur.getNom();

    static final Function<ImmutablePair<ScoreJoueur, ScoreJoueur>, String> phraseEgalitePointsEnCours = pair ->
            getValeurScore(pair.left.getScoreActuel()) + "-" + getValeurScore(pair.left.getScoreActuel()) + " égalité";

    static final Function<ImmutablePair<ScoreJoueur, ScoreJoueur>, String> phraseScorePointsAvecAvantage = pair ->
            "Avantage pour " + getNomJoueurAvecLePlusDePoints.apply(pair);

    static final Function<ImmutablePair<ScoreJoueur, ScoreJoueur>, String> phraseScorePoints = pair ->
            getValeurScore(pair.left.getScoreActuel()) + "-" + getValeurScore(pair.right.getScoreActuel())
                    + " pour " + (getNomJoueurAvecLePlusDePoints.apply(pair));

    /*
     * Récupération de la valeur de la suite de nombre 0 15 30 40 correspondant au score du joueur
     */
    static int getValeurScore(final int score) {
        switch(score) {
            case 0:
                return 0;
            case 1:
                return 15;
            case 2:
                return 30;
            default:
                return 40;
        }
    }
}
