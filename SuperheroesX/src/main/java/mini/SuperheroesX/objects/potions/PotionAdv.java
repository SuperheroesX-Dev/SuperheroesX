package mini.SuperheroesX.objects.potions;



import net.minecraft.potion.Potion;

import net.minecraft.potion.PotionEffect;



public class PotionAdv extends Potion {



    private final boolean visible;



    protected PotionAdv(boolean isBadEffectIn, int liquidColorIn, boolean visible) {

        super(isBadEffectIn, liquidColorIn);

        this.visible = visible;

    }



    @Override

    public boolean shouldRender(PotionEffect effect) {

        return super.shouldRender(effect) && visible;

    }



    @Override

    public boolean shouldRenderHUD(PotionEffect effect) {

        return super.shouldRenderHUD(effect) && visible;

    }



    @Override

    public boolean shouldRenderInvText(PotionEffect effect) {

        return super.shouldRenderInvText(effect) && visible;

    }

}