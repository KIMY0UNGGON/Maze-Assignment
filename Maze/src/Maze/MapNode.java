package Maze;

import java.util.Scanner;

public class MapNode implements IScreen{
	private int stage;
	private boolean goal;
	private MapNode Left,Mid,Right;
	public boolean L,M,R;
	public boolean getGoal() {return goal;}
	public MapNode(int stage) {
		this.stage = stage;
		if(stage > 20 && (int)(Math.random()*10) < 5) {
			goal = true;
		}
		else if(stage == 25) {
			goal = true;
		}
		L = ((int)(Math.random()*3) == 0);
		M = ((int)(Math.random()*3) == 0);
		R = ((int)(Math.random()*3) == 0);
		if(!L&&!M&&!R) {
			switch((int)(Math.random()*3)) {
			case 0 -> L = true;
			case 1 -> M = true;
			case 2 -> R = true;
			}
		}
	}
	public boolean IsMovable(int vect) {
		return switch(vect) {
		case 0 -> L;
		case 1 -> M;
		case 2 -> R;
		default -> false;
		};
	}
	public void Init() {
		int s = stage+1;
		Left = L ? new MapNode(s) : null;
		Mid = M? new MapNode(s):null;
		Right = R? new MapNode(s):null;
	}
	public MapNode getMap(int n) { //0은 left, 1은 mid 2는 right
		MapNode result = null;
		switch(n) {
		case 0:
			result = Left;
			Mid = null;
			Right = null;
			break;
		case 1:
			result = Mid;
			Left = null;
			Right = null;
			break;
		case 2:
			result = Right;
			Mid = null;
			Left = null;
			break;
		}
		return result;
	}
	public int getStage(){return stage;}
	
	@Override
	public void printLoad() {IScreen.printWall(L,M,R);}
	@Override
	public MapNode move(Player p, int vect, Scanner sc) {
		// TODO Auto-generated method stub
		int stage = getStage();
		IScreen.UpdateMove(vect);
		if((int)(Math.random()*10) <= ((int)Math.ceil(stage/5)*2)) {
			//랜덤 에너미 생성
			Enemy e  = new Enemy(stage, (int)(Math.random()*4),p);
			PlayManage.Battle(p, e, sc);
		}
		else if(stage <= 20 && (int)(Math.random()*2) == 0){
			//1/2확률로 아이템 드롭
			PlayManage.DropItem(p,false, sc);
		}
		else if(stage > 20 && (int)(Math.random()*10)==0) {
			PlayManage.DropItem(p,false, sc);
		}
		MapNode Next = getMap(vect);
		Next.Init();
		return Next;
		
	}
}
