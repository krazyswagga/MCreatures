package masp.plugins.MCreatures.Entities;

import java.lang.reflect.Field;
import java.util.List;

import masp.plugins.MCreatures.API.MCreature;
import net.minecraft.server.EntityCreeper;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityVillager;
import net.minecraft.server.PathfinderGoal;
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
import net.minecraft.server.World;

public class MEntityZombie extends EntityCreeper {
	public static MCreature MCreature;
	@SuppressWarnings("unchecked")
	public MEntityZombie(World world) {
		super(world);
		try{
			//Set Navigation
            Field gsa = net.minecraft.server.PathfinderGoalSelector.class.getDeclaredField("a");
            gsa.setAccessible(true);

			//Clear Goals
			Field goals = this.goalSelector.getClass().getDeclaredField("a");
			goals.setAccessible(true);
			((List<PathfinderGoal>) goals.get(this.goalSelector)).clear();

			//Clear Targets
			
			Field targets = this.targetSelector.getClass().getDeclaredField("a");
			targets.setAccessible(true);
			((List<PathfinderGoal>) targets.get(this.targetSelector)).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalBreakDoor(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, this.bw * 1.9f, false)); // this one to attack human
        this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, this.bw, true));
        this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, this.bw));
        this.goalSelector.a(5, new PathfinderGoalMoveThroughVillage(this, this.bw, false));
        this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, this.bw));
        this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F)); // this one to look at human
        this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 8.0F, 0, true)); // this one to target human
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 16.0F, 0, false));
    }
	public static void setAPIMob(MCreature MCreature) {
		MEntityZombie.MCreature = MCreature;
		
	}
}