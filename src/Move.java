import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Move {
	// Num, accuracy, power, category, desc, name, pp , priority, flags, chance,
	// self, boosts,
	// crit ratio, drain, heal,status,target, type
//    private boolean isSpecial;
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
	private boolean self;
	private String boosts;
	private double critical;
	private Ratio drain;
	private Ratio heal;
	private String status;
	private boolean target;
	private String type;
	private String id;

//    HashMap<String,Object> stats=new HashMap<String, Object>();
	// private String status;
//    private int statusChance;
//    private String modifier;
//    private int modifierMagnitude;
//    private int modifierChance;
//    private int priority;
	public Move(int number) {

		this.number = number;
		setStats(number);
	}
	public Move(String name){
		int x=convertName(name);
		number=x;
		setStats(x);
	}
//	public Move(String name, int number, int power, String type) {
//		this.number = number;
//		this.name = name;
//		this.power = power;
//		this.type = type;
//	}

	public int convertName(String name) {
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

	private void setStats(int number) {
		// code way to set all the stats;
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
		// Num, accuracy, power, category, desc, name, pp , priority, flags, chance,
		// self, boosts,
		// crit ratio, drain, heal,status,target, type
//        for(int i = 0;i<s.length;i++) {
//            System.out.println(s[i]);
//        }
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
		id = s[18];
//        stats.put("accuracy",accuracy);
//        stats.put("power",power);
//        stats.put("category",category);
//        stats.put("description",description);
//        stats.put("name",name);
//        stats.put("pp",pp);
//        stats.put("priority",priority);
//        stats.put("flags",flags);

//        for(int i =0;i<s.length;i++){
//
//        }
//        name=s[1];
//        type1=s[2];
//        type2=s[3];
//        health=Integer.parseInt(s[4]);
//        attack=Integer.parseInt(s[5]);
//        defense=Integer.parseInt(s[6]);
//        speed=Integer.parseInt(s[9]);
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public int getPower() {
		return power;
	}

//    public int getStatusChance() {
//        return statusChance;
//    }
//
//
//
//    public String getStatus() {
//        return status;
//    }

	public String getType() {
		return type;
	}

//    public int getModifierChance() {
//        return modifierChance;
//    }
//
//    public int getModifierMagnitude() {
//        return modifierMagnitude;
//    }
////
////    public String getModifier() {
////        return modifier;
////    }
////
////    public boolean isSpecial() {
////        return isSpecial;
////    }
//
//    public int getPriority() {
//        return priority;
//    }

	public String toString() {
		String answer = "\n" + "Name: " + name
				+ "\n" + "Power: " + power
				+ "\n" + "Accuracy: " + accuracy
				+ "\n" + "Description: " + description
				+ "\n" + "Boosts: " + boosts
				+ "\n" + "Status: " + status
				+ "\n" + "Side effects targets self? : " + self
				+ "\n" + "Type: " + type;
		return answer;
	}

	public boolean isSelf() {
		return self;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public String getCategory() {
		return category;
	}

	public boolean isTarget() {
		return target;
	}

	public String getId() {
		return id;
	}

	public String getBoosts() {
		return boosts;
	}

	public int getChance() {
		return chance;
	}

	public String getStatus() {
		return status;
	}

	public int getBasePP() {
		return basePP;
	}

	public int getPP() {
		return pp;
	}

	public void usePP() {
		pp--;
	}

	public double getCritical() {
		return critical;
	}

	public int getPriority() {
		return priority;
	}
}