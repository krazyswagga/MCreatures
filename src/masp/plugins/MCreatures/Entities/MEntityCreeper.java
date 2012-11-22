package masp.plugins.MCreatures.Entities;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.server.EntityCreeper;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityOcelot;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.PathfinderGoal;
import net.minecraft.server.PathfinderGoalAvoidPlayer;
import net.minecraft.server.PathfinderGoalBreakDoor;
import net.minecraft.server.PathfinderGoalFloat;
import net.minecraft.server.PathfinderGoalHurtByTarget;
import net.minecraft.server.PathfinderGoalLookAtPlayer;
import net.minecraft.server.PathfinderGoalMeleeAttack;
import net.minecraft.server.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.PathfinderGoalRandomLookaround;
import net.minecraft.server.PathfinderGoalRandomStroll;
import net.minecraft.server.PathfinderGoalSelector;
import net.minecraft.server.PathfinderGoalSwell;
import net.minecraft.server.World;


public class MEntityCreeper extends EntityCreeper {

	private static PathfinderGoalSelector gs;

	@SuppressWarnings("unchecked")
	public MEntityCreeper(World world) {
		super(world);
		try{
			gs = this.goalSelector;
			Field gsa = net.minecraft.server.PathfinderGoalSelector.class.getDeclaredField("a");
			gsa.setAccessible(true);


			Field goals = this.goalSelector.getClass().getDeclaredField("a");
			goals.setAccessible(true);
			((List<PathfinderGoal>) goals.get(this.goalSelector)).clear();



			Field targets = this.targetSelector.getClass().getDeclaredField("a");
			targets.setAccessible(true);
			((List<PathfinderGoal>) targets.get(this.targetSelector)).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.goalSelector.a(0, new PathfinderGoalFloat(this));
		this.goalSelector.a(1, new PathfinderGoalBreakDoor(this));
		this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, this.bw * 1.9f, false)); 
		this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, this.bw, true));
		this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, this.bw));
		this.goalSelector.a(5, new PathfinderGoalMoveThroughVillage(this, this.bw, false));
		this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, this.bw));
		this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F)); 
		this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
		this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 8.0F, 0, true)); 
		this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 16.0F, 0, false));
	}
	@SuppressWarnings("unchecked")
	public static void creatEntityCreeper(World world) {
		EntityCreeper ec = new EntityCreeper(world);

		try{
			Field gsa = net.minecraft.server.PathfinderGoalSelector.class.getDeclaredField("a");
			gsa.setAccessible(true);


			Field goals = ec.getClass().getDeclaredField("a");
			goals.setAccessible(true);
			((List<PathfinderGoal>) goals.get(ec)).clear();



			Field targets = ec.getClass().getDeclaredField("a");
			targets.setAccessible(true);
			((List<PathfinderGoal>) targets.get(ec)).clear();
			
			 gs.a(1, new PathfinderGoalFloat(ec));
		     gs.a(2, new PathfinderGoalSwell(ec));
		     gs.a(3, new PathfinderGoalAvoidPlayer(ec, EntityOcelot.class, 6.0F, 0.25F, 0.3F));
		     gs.a(4, new PathfinderGoalMeleeAttack(ec, 0.25F, false));
		     gs.a(5, new PathfinderGoalRandomStroll(ec, 0.2F));
		     gs.a(6, new PathfinderGoalLookAtPlayer(ec, EntityHuman.class, 8.0F));
		     gs.a(6, new PathfinderGoalRandomLookaround(ec));
		     gs.a(1, new PathfinderGoalNearestAttackableTarget(ec, EntityHuman.class, 16.0F, 0, true));
		     gs.a(2, new PathfinderGoalHurtByTarget(ec, false));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}

