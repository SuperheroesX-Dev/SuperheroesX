package sx_dev.sx.objects.potions;

import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;

public class StrengthAdv extends PotionAttackDamage {

    private final boolean show;

    public StrengthAdv(boolean show) {
        super(false, 9643043, 3.0D);
        this.show = show;
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return super.shouldRenderInvText(effect) && show;
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return super.shouldRenderHUD(effect) && show;
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return super.shouldRender(effect) && show;
    }
}
