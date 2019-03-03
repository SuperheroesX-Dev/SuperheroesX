package com.sx_dev.sx.init;

import com.sx_dev.sx.util.config.ModConfig;

enum Group {
    MARVEL,
    DC,
    ANY;

    public boolean enabled() {
        switch (this) {
            case MARVEL:
                return ModConfig.CommonConfig.marvelItems.get();
            case DC:
                return ModConfig.CommonConfig.dcItems.get();
            case ANY:
                return true;
            default:
                return false;
        }
    }
}
