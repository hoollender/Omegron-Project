package com.omegron.model.enums;

public enum ManufacturersEnum {
    CaseIH,
    Caterpillar,
    Claas,
    DeutzFahr,
    Fendt,
    Ford,
    JCB,
    JohnDeere,
    Kubota,
    MasseyFerguson,
    McCormick,
    NewHolland,
    Valtra;

    @Override
    public String toString() {
        // Add spaces before each capital letter (except the first one)
        return this.name().replaceAll("([a-z])([A-Z])", "$1 $2");
    }
}
