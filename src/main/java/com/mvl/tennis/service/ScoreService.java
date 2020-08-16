package com.mvl.tennis.service;

import com.mvl.tennis.model.ScoreJoueur;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static com.mvl.tennis.service.ScoreUtils.*;

public class ScoreService {

    private ScoreService() {

    }

    public static final BiFunction<String, ImmutablePair<ScoreJoueur, ScoreJoueur>, ImmutablePair<ScoreJoueur, ScoreJoueur>>
            updateScoreJoueurs = (nomGagnant, pair) -> {

        final ScoreJoueur scoreGagnant;
        final ScoreJoueur scorePerdant;

        if (gagneManche.test(pair.left, nomGagnant)) {
            scoreGagnant = pair.left;
            scorePerdant = pair.right;
        } else {
            scoreGagnant = pair.right;
            scorePerdant = pair.left;
        }

        scoreGagnant.ajouterUnPointAuScore();

        if (jeuEstGagne.test(scoreGagnant, scorePerdant)) {
            ajoutNouveauJeuGagne.andThen(reinitialiseScore).apply(scoreGagnant);
            reinitialiseScore.apply(scorePerdant);
        }
        return ImmutablePair.of(scoreGagnant, scorePerdant);
    };

    /*
     * Affichage du score du jeu en cours
     */
    private static final Consumer<ImmutablePair<ScoreJoueur, ScoreJoueur>> afficherResultatPointsEnCours = pair -> {

        final String scoreActuel;

        if (pair.left.getScoreActuel() == pair.right.getScoreActuel()) {
            scoreActuel = phraseEgalitePointsEnCours.apply(pair);
        } else if (pair.left.getScoreActuel() > 3 || pair.right.getScoreActuel() > 3) {
            scoreActuel = phraseScorePointsAvecAvantage.apply(pair);
        } else {
            scoreActuel = phraseScorePoints.apply(pair);
        }

        System.out.println(scoreActuel);
    };

    /*
     * Affichage nombre de jeu gagnés par chaque joueurs
     */
    private static final Consumer<ImmutablePair<ScoreJoueur, ScoreJoueur>> afficherResultatJeu = pair -> {

        final String scoreJeu;

        if (pair.left.getNbJeuGagnes() == pair.right.getNbJeuGagnes()) {
            scoreJeu = phraseEgaliteJeu.apply(pair.left.getNbJeuGagnes());
        } else {
            scoreJeu = phraseScoreJeuParJoueur.apply(pair.left) + " et " + phraseScoreJeuParJoueur.apply(pair.right);
        }

        System.out.println(scoreJeu);
    };

    public static final Consumer<List<ImmutablePair<ScoreJoueur, ScoreJoueur>>> afficherResultats = pairs ->
            afficherResultatJeu
                    .andThen(afficherResultatPointsEnCours)
                    .accept(pairs.get(pairs.size() - 1));

}
