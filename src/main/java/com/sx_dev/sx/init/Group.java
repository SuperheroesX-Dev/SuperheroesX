package com.sx_dev.sx.init;

import com.sx_dev.sx.util.config.ModConfig;

enum Group {
    MARVEL,
    DC,
    ANY;

    public boolean enabled() {
        switch (this) {
            case MARVEL:
                return ModConfig.common.marvelItems;
            case DC:
                return ModConfig.common.dcItems;
            case ANY:
                return true;
            default:
                return false;
        }
    }
}
