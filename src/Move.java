//
//  Author: Kumar Chandra
//  Revised: Kumar Chandra
//           5/10/20
//
//  Notes:
//       An immutable object that holds data about a move.
//
//  Bugs:
//      unknown
//
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Move {
	// Num, accuracy, power, category, desc, name, pp , priority, flags, chance,
	// self, boosts,
	// crit ratio, drain, heal,status,target, type

	private int number;
	private int accuracy;
	private int power;
	private String category;
	private String description;
	private String name;
	private int basePP;
	private int pp;
	private int priority;
	private String flags;
	private int chance;
	private boolean self;//true if the efeccts target the user
	private String boosts;
	private double critical;
	private Ratio drain;
	private Ratio heal;
	private String status;
	private boolean target;//true if the target is the user
	private String type;
	private String id;
	private Ratio multiHit;
	private Ratio recoil;
	//more info about each stat in the calculate basic damage method of calculator

	public Move(int number) {

		this.number = number;
		setStats(number);
	}
	public Move(String name){
		int x=convertName(name);
		number=x;
		setStats(x);
	}


	public int convertName(String name) {//takes a moves name and gives its corresponding move number
		int number = -1;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Text/Moves.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Text/Moves.txt"));
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splittedCopy;
		splittedCopy = copy.split("\n");
		for (int i = 0; i < 719; i++) {
			String stats = splittedCopy[i];
			String[] s = stats.split("#");
			try {
				if (s[5].equalsIgnoreCase(name)) {
					number = Integer.parseInt(s[0]);
					break;
				}
			} catch (Exception e) {

			}
		}
		return number;
	}

	private void setStats(int number) {//takes a its move  number and sets its stats using the MOves txt file

		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Text/Moves.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Text/Moves.txt"));
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splittedCopy;
		splittedCopy = copy.split("\n");
		String stopdoingthat = splittedCopy[number - 1];
		String[] s = stopdoingthat.split("#");
		number = Integer.parseInt(s[0]);

		accuracy = (s[1].equalsIgnoreCase("true")) ? 101 : Integer.parseInt(s[1]);
		power = Integer.parseInt(s[2]);
		category = s[3];
		description = s[4];
		name = s[5];
		basePP = Integer.parseInt(s[6]);
		pp = basePP;
		priority = Integer.parseInt(s[7]);
		flags = s[8];
		chance = (s[9].equalsIgnoreCase("null")) ? 0 : Integer.parseInt(s[9]);
		self = (s[10].equalsIgnoreCase("null")) ? false : true;
		boosts = s[11];
		critical = (s[12].equalsIgnoreCase("null")) ? 0 : Integer.parseInt(s[12]);
		if (s[13].equalsIgnoreCase("null")) {
			drain = new Ratio(0, 1);
		} else {
			String[] s2 = s[13].replaceAll("[\\[\\],]", "").split(" ");
			int first = Integer.parseInt(s2[0]);
			int second = Integer.parseInt(s2[1]);
			drain = new Ratio(first, second);
		}
		if (s[14].equalsIgnoreCase("null")) {
			heal = new Ratio(0, 1);
		}
		//
		else {
			String[] s2 = s[14].replaceAll("[\\[\\],]", "").split(" ");
			int first = Integer.parseInt(s2[0]);
			int second = Integer.parseInt(s2[1]);
			heal = new Ratio(first, second);
		}
		status = s[15];
		target = (s[16].equalsIgnoreCase("null")) ? false : true;
		type = s[17];

		if (s[18].equalsIgnoreCase("null")) {
			multiHit = new Ratio(1, 1);
		} else {
			String[] s2 = s[18].replaceAll("[\\[\\],]", "").split(" ");
			int first = Integer.parseInt(s2[0]);
			int second = Integer.parseInt(s2[1]);
			multiHit = new Ratio(first, second);
		}

		if (s[19].equalsIgnoreCase("null")) {
			recoil = new Ratio(0, 1);
		} else {
			String[] s2 = s[19].replaceAll("[\\[\\],]", "").split(" ");
			int first = Integer.parseInt(s2[0]);
			int second = Integer.parseInt(s2[1]);
			recoil = new Ratio(first, second);
		}
		id = s[20];

	}

	public String getName() {
		return name;
	}//self explanatory

	public int getNumber() {
		return number;
	}//self explanatory

	public int getPower() {
		return power;
	}//self explanatory


	public String getType() {
		return type;
	}
//self explanatory

	public String toString() {//self explanatory
		String answer = "\n" + "Name: " + name
				+ "\n" + "PP: " + pp + " / " + basePP
				+ "\n" + "Category: " + category
				+ "\n" + "Power: " + power
				+ "\n" + "Accuracy: " + accuracy
				+ "\n" + "Description: " + description
				+ "\n" + "Boosts: " + boosts
				+ "\n" + "Status: " + status
				+ "\n" + "Priority: " + priority
				+ "\n" + "Side effects targets self? : " + self
				+ "\n" + "Type: " + type;
		return answer;
	}

	public boolean isSelf() {//self explanatory
		return self;
		//true if the boosts targets self
	}

	public int getAccuracy() {
		return accuracy;
	}//self explanatory

	public String getCategory() {
		return category;
	}
	//self explanatory
	public boolean isTarget() {
		return target;
		//true if damage and or boosts target self
	}

	public String getId() {
		return id;
	}
	//self explanatory
	public String getBoosts() {
		return boosts;
	}

	public int getChance() {
		return chance;
	}//self explanatory

	public String getStatus() {
		return status;
	}
	//self explanatory
	public int getBasePP() {
		return basePP;
	}

	public int getPP() {
		return pp;
	}

	public void usePP() {
		pp--;
	}
	//self explanatory
	public double getCritical() {
		return critical;
	}

	public double getRecoil() {
		return  (double)recoil.getFirst()/(double)recoil.getSecond();
	}
	public Ratio getMultiHit() {
		return  multiHit;
	}
	public int getPriority() {
		return priority;
	}//self explanatory
	public double getDrain(){
		return (double)drain.getFirst()/(double)drain.getSecond();
	}//self explanatory
	public double getHeal(){
		return (double)heal.getFirst()/(double)heal.getSecond();
	}


}