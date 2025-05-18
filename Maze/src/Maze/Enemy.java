package Maze;

public class Enemy extends Entity{
	public static final String[] EnemyType= {"‍🐱‍👤","🤖","👹", "👾"};
	public static final String[] EnemyName = {"Ninja Cat","Runaway Robot", "Oni","Unknown Alien"};
	public Enemy(int Stage, int TypeNum, Player p) {
		super((int)((Math.random()*Stage+1)*12), EnemyType[TypeNum], EnemyName[TypeNum]);
		setAttack((int)(Math.random()*Stage+5)); //5~ stage+5
		setAttack(getAttack()*(1+(int)(p.getAValue()*(Math.random()*0.0005))));			
		
		setLevel((getHp()/10)+1);
	}
	
	@Override
	public void Attack(Entity e) { 
		int damage= (int)(getAttack()*(1-(e.getAValue()*0.001)));
		if(damage <= 0) damage = 1;
		e.MinusHp(damage);
	}
}

