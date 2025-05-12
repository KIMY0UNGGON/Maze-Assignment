package Maze;


public abstract class Entity {
	private int hp;
	public int MAXHP;
	private String img;
	private String Name;
	private int AttackValue;
	private int ArmourValue;
	private int Level;
	private int exp;

	//각각 1~9, 10~19, 20~29, 30~39, 40~49, 50~까지 구간의 경험치
	private static final int[] EXP_INTERVER= {100, 200, 350, 400, 450, 500}; 
	public Entity(int hp, String img, String Name) {
		this.MAXHP = hp;
		this.hp = hp;
		this.img = img;
		this.Name = Name;
		this.AttackValue = 0;
		this.ArmourValue =0;
	}
	public int getAValue() { //방어력
		return ArmourValue;
	}
	public void setAValue(int armour) { //방어력
		this.ArmourValue += armour;
	}
	public void MinusHp(int hp) {
		this.hp -= hp;
	}
	public void recovery(int hp) {
		
		this.hp += hp;
		if(this.hp >= MAXHP) {
			this.hp = MAXHP;
		}
	}
	public int getEXP() {
		return exp;
	}
	public void LevelUp() {
		MAXHP += 10;
		hp+=10;
		System.out.println("레벨이 올랐습니다.");
		System.out.println("Lv. "+Level+" -> "+(++Level));
	}
	public void expUp(int n) {
		int e = exp;
		exp+= n;
		System.out.println("갑자기 몸이 달아오른 기분이 든다.");
		System.out.println("EXP : "+e+" -> "+exp);
		while(getInterver(Level) <= exp) {
			exp -= getInterver(Level);
			LevelUp();
		}
	}
	public int getInterver(int level) {
		if(level/10 >= 5) return EXP_INTERVER[5];
		return EXP_INTERVER[level/10];
	}
	public void setLevel(int level) {
		this.Level = level;
	}
	public int getLevel() {
		return Level;
	}
	public String getImg() {return img;}
	public int getHp() {return hp;}
	public void setAttack(int value) {
		this.AttackValue = value;
	}
	public int getAttack() {
		return AttackValue;
	}
	public String getName() {
		return Name;
	}
	public void getStatus() {
		System.out.println(img);
		System.out.println("LV. "+Level);
		System.out.println("이름 : "+Name);
		System.out.println("현재 체력 : "+hp+"/"+MAXHP);
	}
	public abstract void Attack(Entity e);
}
