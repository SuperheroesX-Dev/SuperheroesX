package mini.SuperheroesX.util.integration.TC;

import java.util.ArrayList;

class TraitList extends ArrayList<TraitMap> {
    public TraitList addE(TraitMap traitMap) {
        add(traitMap);
        return this;
    }
}
