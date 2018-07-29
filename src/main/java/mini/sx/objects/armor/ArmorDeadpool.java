package mini.sx.objects.armor;

import mini.sx.SuperheroesX;
import mini.sx.init.ItemInit;
import mini.sx.init.PotionInit;
import mini.sx.util.interfaces.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ArmorDeadpool extends ItemArmor implements IHasModel
{

    public ArmorDeadpool(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{

		super(materialIn, renderIndexIn, equipmentSlotIn);

		setUnlocalizedName(name);
		setRegistryName(name);
        setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB_MATERIALS);


		ItemInit.ITEMS.add(this);
	}

	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (this.isWearingFullSet(player, ItemInit.HELMET_DEADPOOL, ItemInit.CHESTPLATE_DEADPOOL, ItemInit.LEGGINGS_DEADPOOL,
				ItemInit.BOOTS_DEADPOOL)) {
		super.onArmorTick(world, player, stack);

            player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 5, 1, true, false));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 5, 2, true, false));
 }


		}
	
	private boolean isWearingFullSet(EntityPlayer player, Item helmetDeadpool, Item chestplateDeadpool, Item leggingsDeadppol,Item bootsDeadpool) {
		return
				!player.inventory.armorItemInSlot(3).isEmpty() && player.inventory.armorItemInSlot(3).getItem() == helmetDeadpool
						&& !player.inventory.armorItemInSlot(2).isEmpty() && player.inventory.armorItemInSlot(2).getItem() == chestplateDeadpool
						&& !player.inventory.armorItemInSlot(1).isEmpty() && player.inventory.armorItemInSlot(1).getItem() == leggingsDeadppol
						&& !player.inventory.armorItemInSlot(0).isEmpty() && player.inventory.armorItemInSlot(0).getItem() == bootsDeadpool;
	}

	
	private void effectPlayer(EntityPlayer player, Potion potion, int duration, int amplifier) {
        if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1) {
            player.addPotionEffect(new PotionEffect(potion, duration, amplifier, false, false)); }
	}
	@Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
           ModelBiped armorModel = null;

        if (itemStack != null) {
           armorModel = new ModelBiped(0.5F);

           if (armorModel != null) {
              armorModel.setModelAttributes(_default);
                return armorModel;
            }
        }

        return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
    }
	@Override

	public void registerModels() 

	{

        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");

	}

}