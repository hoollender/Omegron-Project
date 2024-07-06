package com.omegron.model.enums;

public enum TransmissionTypeEnum {
    StandartGear,
    Synchromesh,
    PowerShuttle,
    PowerShift,
    Hydrostatic,
    CVT,
    DualClutch,
    IVT;

    @Override
    public String toString() {
        // Add spaces before each capital letter (except the first one)
        return this.name().replaceAll("([a-z])([A-Z])", "$1 $2");
    }
}
