package com.mvl.tennis.model;

import org.apache.commons.lang3.Validate;

import java.util.function.Predicate;

public class ScoreJoueur {

    private final String nom;
    private int scoreActuel;
    private int nbJeuGagnes;

    public ScoreJoueur(final String nom) {
        this.nom = Validate.notBlank(nom);
        this.scoreActuel = 0;
        this.nbJeuGagnes = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getScoreActuel() {
        return scoreActuel;
    }

    public int getNbJeuGagnes() {
        return nbJeuGagnes;
    }

    public void ajouterUnPointAuScore() {
        this.scoreActuel++;
    }

    public void scoreActuelReinitialisation() {
        this.scoreActuel = 0;
    }

    public void nouveauJeuGagne() {
        this.nbJeuGagnes++;
    }

    @Override
    public String toString() {
        return "{ nom = " + nom + ", score actuel = " + scoreActuel + ", nb jeu gagnés = " + nbJeuGagnes + " }";
    }
}
