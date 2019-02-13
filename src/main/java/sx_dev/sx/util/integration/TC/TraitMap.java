package sx_dev.sx.util.integration.TC;

import slimeknights.tconstruct.library.traits.ITrait;

import javax.annotation.Nullable;

class TraitMap {
    private String dependency;
    private ITrait trait;

    public TraitMap(ITrait trait, @Nullable String dependency) {
        this.dependency = dependency;
        this.trait = trait;
    }

    public ITrait getTrait() {
        return trait;
    }

    public String getDependency() {
        return dependency;
    }
}
