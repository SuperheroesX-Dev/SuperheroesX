package mini.SuperheroesX.objects.armor;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.init.PotionInit;
import mini.SuperheroesX.util.interfaces.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ArmorBlackPanther extends ItemArmor implements IHasModel {
	public ArmorBlackPanther(String name,ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
	{
			super(materialIn, renderIndexIn, equipmentSlotIn);

			setUnlocalizedName(name);
			setRegistryName(name);
        setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB);

			ItemInit.ITEMS.add(this);
		}

		@Override
		public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
			if (this.isWearingFullSet(player, ItemInit.HELMET_BLACKPANTHER, ItemInit.CHESTPLATE_BLACKPANTHER, ItemInit.LEGGINGS_BLACKPANTHER,
					ItemInit.BOOTS_BLACKPANTHER)) {
			super.onArmorTick(world, player, stack); 
			 if (player.isSprinting()) {
					player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 4, true, false));
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), 2, 2, 0);
			 }
				player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 0, 1, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 3, 2, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 0, 5, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 0, 5, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 0, 1, true, false));
			player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 0, 3, true, false));
	 }


			}
		
		private boolean isWearingFullSet(EntityPlayer player, Item helmetBlackPanther, Item chestplateBlackPanther, Item leggingsBlackPanther,Item bootBlackPanther) {
			return
					!player.inventory.armorItemInSlot(3).isEmpty() && player.inventory.armorItemInSlot(3).getItem() == helmetBlackPanther
							&& !player.inventory.armorItemInSlot(2).isEmpty() && player.inventory.armorItemInSlot(2).getItem() == chestplateBlackPanther
							&& !player.inventory.armorItemInSlot(1).isEmpty() && player.inventory.armorItemInSlot(1).getItem() == leggingsBlackPanther
							&& !player.inventory.armorItemInSlot(0).isEmpty() && player.inventory.armorItemInSlot(0).getItem() == bootBlackPanther;
		}

		
		private void effectPlayer(EntityPlayer player, Potion potion, int duration, int amplifier) {
	        if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1) {
	            player.addPotionEffect(new PotionEffect(potion, duration, amplifier, false, false)); }
		}

		@Override

		public void registerModels() 

		{

            SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");

		}

	}
