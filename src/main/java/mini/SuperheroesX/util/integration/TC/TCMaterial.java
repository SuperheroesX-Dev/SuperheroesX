package mini.SuperheroesX.util.integration.TC;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

@SuppressWarnings("WeakerAccess")
class TCMaterial extends Material {

    public final String oreDictSuffix;
    public TraitMap[] traits;
    public IMaterialStats[] stats;
    public IMaterialStats stat;
    public ItemStack representativeItemStack;
    public boolean castable;
    public boolean craftable;
    public Fluid fluid;

    public TCMaterial(@Nonnull String oreDictSuffix, int color, @Nonnull ItemStack representativeItemStack, @Nullable Fluid fluid, @Nonnull List<TraitMap> traits, IMaterialStats stat, IMaterialStats... stats) {
        super(oreDictSuffix.toLowerCase(), color);

        this.fluid = fluid;
        this.castable = this.fluid != null;
        this.craftable = !this.castable;
        this.representativeItemStack = representativeItemStack;
        this.traits = traits.toArray(new TraitMap[0]);
        this.oreDictSuffix = oreDictSuffix;
        this.stat = stat;
        this.stats = stats;

        TCIntegration.MATERIALS.add(this);
    }

    public TCMaterial(@Nonnull String oreDictSuffix, Color color, @Nonnull ItemStack representativeItemStack, boolean createFluid, @Nonnull List<TraitMap> traits, IMaterialStats stat, IMaterialStats... stats) {
        super(oreDictSuffix.toLowerCase(), color.getRGB());

        if (createFluid) {
            this.fluid = new TCFluid("molten_" + oreDictSuffix.toLowerCase(), color);
            setFluid(this.fluid);
        } else {
            this.fluid = null;
        }

        this.castable = this.fluid != null;
        this.craftable = !this.castable;
        this.representativeItemStack = representativeItemStack;
        this.traits = traits.toArray(new TraitMap[0]);
        this.oreDictSuffix = oreDictSuffix;
        this.stat = stat;
        this.stats = stats;

        TCIntegration.MATERIALS.add(this);
    }

    public Material addTraits(TraitMap... traits) {
        for (TraitMap trait :
                traits) {
            if (trait.getDependency() != null) {
                addTrait(trait.getTrait(), trait.getDependency());
            } else {
                addTrait(trait.getTrait());
            }
        }
        return this;
    }
}
