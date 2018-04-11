package mini.SuperheroesX.objects.armor;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.util.interfaces.IHasModel;
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

public class ArmorBase extends ItemArmor implements IHasModel

{

	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) 

	{

		super(materialIn, renderIndexIn, equipmentSlotIn);

		setUnlocalizedName(name);

		setRegistryName(name);

        setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB);

		

		ItemInit.ITEMS.add(this);

	}

	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (this.isWearingFullSet(player, ItemInit.CHESTPLATE_SUPERBOY, ItemInit.LEGGINGS_SUPERBOY, ItemInit.BOOTS_SUPERBOY)) {
		super.onArmorTick(world, player, stack); 
		
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 1, false, false));
		player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 0, 4, false, false));
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 0, 2, false, false));
		player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 0, 2, false, false));
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 0, 4, false, false)); }


		}
	
	private boolean isWearingFullSet(EntityPlayer player, Item chestplateSuperman, Item leggingsSuperman,
			Item bootsSuperman) {
		return
				!player.inventory.armorItemInSlot(2).isEmpty() && player.inventory.armorItemInSlot(2).getItem() == chestplateSuperman
						&& !player.inventory.armorItemInSlot(1).isEmpty() && player.inventory.armorItemInSlot(1).getItem() == leggingsSuperman
						&& !player.inventory.armorItemInSlot(0).isEmpty() && player.inventory.armorItemInSlot(0).getItem() == bootsSuperman;
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