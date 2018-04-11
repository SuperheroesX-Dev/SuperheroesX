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
import net.minecraft.world.World;

public class ArmorDeadpool extends ItemArmor implements IHasModel

{

	public ArmorDeadpool(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) 

	{

		super(materialIn, renderIndexIn, equipmentSlotIn);

		setUnlocalizedName(name);

		setRegistryName(name);

        setCreativeTab(SuperheroesX.SUPERHEROES_X_TAB);

		

		ItemInit.ITEMS.add(this);

	}

	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (this.isWearingFullSet(player, ItemInit.HELMET_DEADPOOL, ItemInit.CHESTPLATE_DEADPOOL, ItemInit.LEGGINGS_DEADPOOL,
				ItemInit.BOOTS_DEADPOOL)) {
		super.onArmorTick(world, player, stack); 
		
		player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 0, 1, true, false));
		player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 0, 1, true, false));
			player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 0, 1, true, false));
		player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 0, 2, true, false));
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

	public void registerModels() 

	{

        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");

	}

}