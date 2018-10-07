
import java.util.Scanner;
import java.util.LinkedList;


public class Main {
	
	public static void main(String arg[]) {
		Scanner input = new Scanner(System.in);
		LinkedList<Role> team0 = new LinkedList<Role>();
		LinkedList<Role> team1 = new LinkedList<Role>();
		
		Role hero0 = new Role(30,0); 
		Role hero1 = new Role(30,0);
		team0.add(0,hero0);
		team1.add(0,hero1);
		
		int time,showTime=-1,attNum0=0,attNum1=0,flag=-1;
		String action;
		time = input.nextInt();
		input.nextLine();
		
		while(time-- > 0) {
			action = input.nextLine();
			String [] temp = action.split(" ");
			switch(temp[0]) {
			case "summon":
				if(showTime == -1) {
					summon(temp,team0,attNum0);
					attNum0++;
				}
				else {
					summon(temp,team1,attNum1);
					attNum1++;
				}	
				break;
			case "attack": 
				if(showTime == -1) {
					flag=attack(temp,team0,team1);
					switch (flag) {
					case 0:attNum0--;break;
					case 1:attNum1--;break;
					case 2:attNum0--;attNum1--;break;
					default :break;
					}
				}
				else {
					flag=attack(temp,team1,team0);
					switch (flag) {
					case 0:attNum1--;break;
					case 1:attNum0--;break;
					case 2:attNum0--;attNum1--;break;
					default :break;
					}
				}	
				break;
			
			case "end":
				showTime = -showTime;
				break;
			default:
				break;
			}
		}
		hero0 = team0.get(0);
		hero1 = team1.get(0);
		if(hero0.live>0&&hero1.live>0)
			System.out.println(0);
		else if(hero0.live>0)
			System.out.println(1);
		else
			System.out.println(-1);
		display(team0,attNum0);
		display(team1,attNum1);
		input.close();
	}
	public static class Role{
		int live,power;
		Role(int live ,int power){
			this.live = live;
			this.power = power;
		}
	}
	public static int attack(String []temp,LinkedList myTeam,LinkedList yourTeam){
		int attPos,defPos,flag;
		attPos = Integer.parseInt(temp[1]);
		defPos = Integer.parseInt(temp[2]);
		Role attacker,defender;
		attacker = (Role) myTeam.get(attPos);
		defender = (Role) yourTeam.get(defPos);
		attacker.live -= defender.power;
		defender.live -= attacker.power;
		if(attacker.live <= 0&&defender.live <= 0&&defPos != 0) {
			myTeam.remove(attPos);
			yourTeam.remove(defPos);
			flag = 2;
		}
		else if(defender.live <= 0&&defPos != 0) {
			yourTeam.remove(defPos);
			flag = 1;
		}
		else if(attacker.live <= 0){
			myTeam.remove(attPos);
			flag = 0;
		}
		else 
			flag = -1;
		return flag;
	}
	public static void display(LinkedList team,int myNum) {
		/*Role contain = (Role) team.get(0);
		if(contain.live <= 0)
			System.out.println(0);
		else
			System.out.println(contain.live);
		System.out.print(myNum);
		if(myNum > 0) {
			for(int i = 1; i<=myNum ;i++) {
				contain = (Role) team.get(i);
				System.out.print(" "+contain.live);
			}
		}
		System.out.println();*/
	}
	static void summon(String []temp,LinkedList team,int myNum){
		int position,live,power;
		position = Integer.parseInt(temp[1]);
		power = Integer.parseInt(temp[2]);
		live = Integer.parseInt(temp[3]);
		Role attendant = new Role(live,power);
		team.add(position, attendant);
		attendant = (Role) team.get(position);
	}
}
