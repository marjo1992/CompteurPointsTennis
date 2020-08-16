package com.mvl.tennis.service;

import com.google.common.collect.ImmutableList;
import com.mvl.tennis.model.ScoreJoueur;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class ScoreServiceTest {

    private static final String NOM_JOUEUR_NADAL = "Nadal";
    private static final String NOM_JOUEUR_FEDERER = "Federer";

    @Test
    public void updateScoreJoueurs_15_30_pour_Nadal_1_jeu_partout_et_Nadal_gagne_manche() {

        // Initialisation score Nadal
        final ScoreJoueur scoreJoueurNadal = new ScoreJoueur(NOM_JOUEUR_NADAL);
        scoreJoueurNadal.nouveauJeuGagne(); // 1 jeu
        scoreJoueurNadal.ajouterUnPointAuScore(); // 15 points
        scoreJoueurNadal.ajouterUnPointAuScore(); // 30 points

        // Initialisation score Federer
        final ScoreJoueur scoreJoueurFederer = new ScoreJoueur(NOM_JOUEUR_FEDERER);
        scoreJoueurFederer.nouveauJeuGagne(); // 1 jeu
        scoreJoueurFederer.ajouterUnPointAuScore(); // 15 points

        // On test le calcul des scores
        final ImmutablePair<ScoreJoueur, ScoreJoueur> resultat =
                ScoreService.updateScoreJoueurs.apply(NOM_JOUEUR_NADAL, ImmutablePair.of(scoreJoueurNadal, scoreJoueurFederer));

        // Récupération et vérification du nouveau score de Nadal
        final ScoreJoueur nouveauScoreJoueurNadal = ImmutableList.of(resultat.left, resultat.right).stream()
                .filter(scoreJoueur -> NOM_JOUEUR_NADAL.equals(scoreJoueur.getNom()))
                .collect(collectingAndThen(toList(), ImmutableList::copyOf)).get(0);

        Assertions.assertThat(nouveauScoreJoueurNadal).extracting(ScoreJoueur::getScoreActuel).isEqualTo(3);
        Assertions.assertThat(nouveauScoreJoueurNadal).extracting(ScoreJoueur::getNbJeuGagnes).isEqualTo(1);
    }

}