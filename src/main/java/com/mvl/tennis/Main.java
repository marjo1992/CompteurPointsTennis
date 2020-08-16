package com.mvl.tennis;

import com.google.common.collect.ImmutableList;
import com.mvl.tennis.model.ScoreJoueur;
import com.mvl.tennis.service.ScoreService;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Main {

    private static final String fileName = "src/main/resources/score.txt";

    public static void main(String[] args) {

        // Récupèration des données du match
        final List<String> joueursGagnants;
        try (final Stream<String> stream = Files.lines(Paths.get(fileName))) {
            joueursGagnants = stream.collect(collectingAndThen(toList(), ImmutableList::copyOf));
        } catch (final IOException e) {
            System.out.println("Erreur lors de la lecture du fichier. Veuillez compléter le fichier " + fileName + ".");
            return;
        }

        // On récupère les joueurs
        final List<ScoreJoueur> scoresJoueurs =
                joueursGagnants.stream()
                        .distinct()
                        .map(ScoreJoueur::new)
                        .collect(collectingAndThen(toList(), ImmutableList::copyOf));

        // Vérification du nombre de joueurs
        if (scoresJoueurs.size() != 2) {
            System.out.println("Le nombre de joueur est incorrect.");
            return;
        }

        final ImmutablePair<ScoreJoueur, ScoreJoueur> pairScoresJoueurs =
                ImmutablePair.of(scoresJoueurs.get(0), scoresJoueurs.get(1));

        // Calculs des scores
        final List<ImmutablePair<ScoreJoueur, ScoreJoueur>> resultats = joueursGagnants.stream()
                .map(joueurGagnant -> ScoreService.updateScoreJoueurs.apply(joueurGagnant, pairScoresJoueurs))
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));

        // Affichage des scores
        ScoreService.afficherResultats.accept(resultats);
    }
}
