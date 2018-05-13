package mini.SuperheroesX.objects.armor;

import mini.SuperheroesX.SuperheroesX;
import mini.SuperheroesX.init.ItemInit;
import mini.SuperheroesX.init.PotionInit;
import mini.SuperheroesX.util.interfaces.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
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


public class ArmorCaptainAmerica extends ItemArmor implements IHasModel {

    public ArmorCaptainAmerica(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {

		super(materialIn, renderIndexIn, equipmentSlotIn);

		setUnlocalizedName(name);
		setRegistryName(name);

		ItemInit.ITEMS.add(this);
	}

	@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		if (this.isWearingFullSet(player, ItemInit.HELMET_CAPTAIN_AMERICA, ItemInit.CHESTPLATE_CAPTAIN_AMERICA, ItemInit.LEGGINGS_CAPTAIN_AMERICA,
				ItemInit.BOOTS_CAPTAIN_AMERICA)) {
		super.onArmorTick(world, player, stack); 

            player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 3, 1, true, false));
		player.addPotionEffect(new PotionEffect(PotionInit.INVISIBLE_STRENGTH, 3, 4, true, false));
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 3, 1, true, false));
		player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 3, 2, true, false)); }

		}
	
	private boolean isWearingFullSet(EntityPlayer player, Item helmetCaptain_America, Item chestplateCaptain_America, Item leggingsCaptain_America, Item bootsCaptain_America) {
        return
                !player.inventory.armorItemInSlot(3).isEmpty() && player.inventory.armorItemInSlot(3).getItem() == helmetCaptain_America
                        && !player.inventory.armorItemInSlot(2).isEmpty() && player.inventory.armorItemInSlot(2).getItem() == chestplateCaptain_America
                        && !player.inventory.armorItemInSlot(1).isEmpty() && player.inventory.armorItemInSlot(1).getItem() == leggingsCaptain_America
                        && !player.inventory.armorItemInSlot(0).isEmpty() && player.inventory.armorItemInSlot(0).getItem() == bootsCaptain_America;
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

    public void registerModels() {
        SuperheroesX.PROXY.registerItemRenderer(this, 0, "inventory");
	}

	public static class Set {

        public final ArmorCaptainAmerica helmet, chestplate, leggings, boots;

        public Set(String name, ArmorMaterial material) {
            helmet = new ArmorCaptainAmerica("helmet_" + name, material, 1, EntityEquipmentSlot.HEAD);
            chestplate = new ArmorCaptainAmerica("chestplate_" + name, material, 1, EntityEquipmentSlot.CHEST);
            leggings = new ArmorCaptainAmerica("leggings_" + name, material, 2, EntityEquipmentSlot.LEGS);
            boots = new ArmorCaptainAmerica("boots_" + name, material, 1, EntityEquipmentSlot.FEET);
        }

        public Set setCreativeTab(CreativeTabs tab) {
            this.helmet.setCreativeTab(tab);
            this.chestplate.setCreativeTab(tab);
            this.leggings.setCreativeTab(tab);
            this.boots.setCreativeTab(tab);
            return this;
        }

        public Set setRepairItem(ItemStack item) {
            helmet.getArmorMaterial().setRepairItem(item);
            return this;
        }
	}

}