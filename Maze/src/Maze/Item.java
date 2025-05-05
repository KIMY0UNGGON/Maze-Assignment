package Maze;

import java.io.*;
import java.util.*;

public class Item {
	public static String[] ItemTypes = {"Potion", "Armour", "Weapon"}; //아이템 종류
	public static ArrayList<Item> ALLItems;//모든  아이템
	private int effectValue;
	private String ItemName;
	private String Type;
	
	public Item(String Name, int eA, int  typeNum) {
		this.ItemName = Name;
		this.effectValue = eA;
		this.Type = ItemTypes[typeNum];
	}
	
	public String getName() {return ItemName;}
	public String getType() {return Type;}
	public int getEffect() {return effectValue;}
	//값 수정은 필요 없으므로 setter는 구현 X
	
	public static void InitItems() {
		ALLItems = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader( new FileReader("Items.txt"))) {
			String ItemInfo;
			while((ItemInfo = reader.readLine()) != null) {
				String[] sp_Info = ItemInfo.split(":");
				ALLItems.add(new Item(sp_Info[0], Integer.parseInt(sp_Info[1]), Integer.parseInt(sp_Info[2]) ));
				
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("실패");
		}
	}
}
