
package com.deathpotion.dp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class myEvents {
	
	@SubscribeEvent
	public void DeathPotion(LivingDeathEvent event){
		
		DamageSource src=null;
		if(event.getSource()!=null)
			src=event.getSource();
		
		if(src!=null)
		{
			boolean hasMethod=false;
			Method[] meths=src.getClass().getMethods();
			for(Method m:meths)
			{
				if(m.getName().equals("getTrueSource")) {
					 hasMethod = true;
					 break;
				}
			}
			if(hasMethod)
				if(src.getTrueSource() instanceof PlayerEntity)
				{
	
					LivingEntity entity=(LivingEntity) src.getTrueSource();
					PlayerEntity pl=(PlayerEntity) entity;
					int n=13;
					int random=(int) (Math.random()*n);
					Potion killer;
					switch(random)
					{
					case 0:
						killer=Potions.HARMING;
						break;
					case 1:
						killer=Potions.SLOW_FALLING;
						break;
					case 2:
						killer=Potions.POISON;
						break;
					case 3:
						killer=Potions.HEALING;
						break;
					case 4:
						killer=Potions.LUCK;
						break;
					case 5:
						killer=Potions.LEAPING;
						break;
					case 6:
						killer=Potions.SLOWNESS;
						break;
					case 7:
						killer=Potions.SWIFTNESS;
						break;
					case 8:
						killer=Potions.WEAKNESS;
						break;
					case 9:
						killer=Potions.REGENERATION;
						break;
					case 10:
						killer=Potions.STRENGTH;
						break;
					case 11:
						killer=Potions.STRONG_HARMING;
						break;
					case 12:
						killer=Potions.STRONG_TURTLE_MASTER;
						break;
					default:
						killer=Potions.NIGHT_VISION;
						break;
					}
					
			         for(EffectInstance effectinstance : killer.getEffects()) {
			            if (effectinstance.getPotion().isInstant()) {
			               effectinstance.getPotion().affectEntity(pl, pl, entity, effectinstance.getAmplifier(), 1.0D);
			            } else {
			               entity.addPotionEffect(new EffectInstance(effectinstance));
			            }
			         }
				}
		}
	}
}
			