package Maze;

import java.util.*;

public class GameMain {

	public static void main(String[] args) {

	
		MapNode m = new MapNode(1); //맵 생성
		m.Init();
		Player p = null;
		String name = null;
		int num = 0;
		try (Scanner sc = new Scanner(System.in)) {
			for(int i= 0; i< 12; i++) {
				System.out.println("|");
			}
			System.out.println("현재 이클립스의 콘솔로 제작하여 제대로 실행을 하려면 콘솔의 크기를 설정해야합니다.");
			System.out.print("현재 출력된 문자들이 콘솔에 가득차게 설정하셨으면 엔터키를 눌러주세요");
			sc.nextLine();
			Item.InitItems();
			IScreen.Clear();
			System.out.println("갑자기 눈을 뜨니 갈림길만 있는 곳입니다.");
			System.out.println("뒤는 막혀있어 앞으로 밖에 갈 수 없다는 것을 깨달은 당신은 앞으로 나아갈 것을 결심합니다.");
			while(p == null) {
				if(name == null) { //아이콘 재입력시에는 닉네임은 받지 않음
					System.out.print("당신의 이름을 입력해주세요 : ");
					name = sc.nextLine();
				}
				System.out.println("당신의 플레이어 아이콘을 선택 해주세요 ");
				System.out.printf("1.%s 2.%s 3.%s 4.%s : ",Player.Type[0],Player.Type[1],Player.Type[2],Player.Type[3]);
				try {
					num = sc.nextInt();
					if(num > 4 || num <=0) throw new Exception("입력");	
					sc.nextLine();
				}
				catch(Exception e) {
					sc.nextLine();
					System.out.println("잘못된 입력입니다.");
					continue;
				}
				p = new Player(name,num-1);
			}
			name = null;
			
			while(!m.getGoal() && p.getHp() > 0 ) { //골이 나올때까지 반복
				
				m.printLoad();
				try {
					System.out.print("현재 스테이지 : "+m.getStage()+" 왼쪽 이동 <1> 가운데 이동 <2> 오른쪽 이동 <3> 플레이어 상태 <4> 가방 <5> : ");
					num = sc.nextInt();
					if(num <= 0 || num > 5) {throw new Exception("입력오류");}
				}
				catch(Exception e) {
					sc.nextLine();
					System.out.println("다시 입력해주세요.");
					continue;
				}
				sc.nextLine();
				if(num < 4) {
					if(m.IsMovable(num-1)) {
						m = m.move(p,num-1,sc);
					}
					else {
						System.out.println("이쪽으로는 이동이 불가능한것 같다...");
						System.out.println("엔터<-");
						sc.nextLine();
					}
				}
				else if(num == 4) {
					p.getStatus();
					System.out.println("돌아가시려면 엔터키를 눌러주세요");
					sc.nextLine();
				}
				else if (num == 5){
					Item item = PlayManage.BagManage(p,false,sc);
					if(item == null) {
						continue;
					}
					p.releaseItem(item);
				}
				
			}
			if(m.getGoal()) {
				IScreen.Clear();
				System.out.println("길을 지나오자 밝은 빛이 당신을 비췄습니다.");
				System.out.println("처음보는 마이크를 들고 있는 생물이 당신에게 말을 걸기 시작했습니다.");
				System.out.println("??? : 축하합니다! 미로의 끝에 도달하였습니다. 선물로 케이크를 드리겠습니다.");
				System.out.println("주변을 둘러보자 앞에 있는 생물과 비슷하게 생긴 생물들이 웃으면서 당신을 보고 있습니다.");
				System.out.println("당황스러워 하는 당신을 신경쓰지 않는 듯 마이크를 들고 있는 생물이 당신에게 커다란 케이크를 선물로 건넵니다.");
				System.out.print("엔터<- (케이크가 크게 나옵니다. 콘솔창의 크기를 변경하여 주십시오)");
				sc.nextLine();
				System.out.print(IScreen.Cake);
				System.out.println("HAPPY END?");
			}
			else{
				System.out.println("눈앞이 깜깜해졌다.");
				System.out.println(p.getName()+"은 미로의 거름이 되어갔다.");
				System.out.println("DEAD END");
			}
		}

	}
}

