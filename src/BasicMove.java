import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BasicMove {
	// Num, accuracy, power, category, desc, name, pp , priority, flags, chance, self, boosts,
	// crit ratio, drain, heal,status,target, type
//    private boolean isSpecial;
	private int moveNumber;
	int accuracy;
	private int basePower;
	String category;
	String description;
	private String moveName;
	int pp;
	int priority;
	String flags;
	private int chance;
	private boolean self;
	private String boosts;
	private double critical;
	private Ratio drain;
	private Ratio heal;
	private String status;
	private String target;
	private String type;

	// private String status;
//    private int statusChance;
//    private String modifier;
//    private int modifierMagnitude;
//    private int modifierChance;
//    private int priority;
	public BasicMove(int moveNumberParam) {

		moveNumber = moveNumberParam;
		setStats(moveNumber);
	}

	public BasicMove(String moveName1, int moveNumber1, int basePower1, String typ1) {
		moveNumber = moveNumber1;
		moveName = moveName1;
		basePower = basePower1;
		type = typ1;
	}

	public int convertMoveName(String moveName) {
		int moveNumber = -1;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Moves.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Moves.txt"));
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
				if (s[5].equalsIgnoreCase(moveName)) {
					moveNumber = Integer.parseInt(s[0]);
					break;
				}
			} catch (Exception e) {

			}
		}
		return moveNumber;
	}

	private void setStats(int moveNumber) {
		// code way to set all the stats;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Moves.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Moves.txt"));
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splittedCopy;
		splittedCopy = copy.split("\n");
		String stats = splittedCopy[moveNumber - 1];
		String[] s = stats.split("#");
		moveNumber = Integer.parseInt(s[0]);
		// Num, accuracy, power, category, desc, name, pp , priority, flags, chance, self, boosts,
		// crit ratio, drain, heal,status,target, type
		accuracy = (s[1].equalsIgnoreCase("true")) ? 100 : Integer.parseInt(s[1]);
		basePower = Integer.parseInt(s[2]);
		category = s[3];
		description = s[4];
		moveName = s[5];
		pp = Integer.parseInt(s[6]);
		priority = Integer.parseInt(s[7]);
		flags = s[8];
		chance = (s[9].equalsIgnoreCase("null")) ? 0 : Integer.parseInt(s[9]);
		self = (s[10].equalsIgnoreCase("null")) ? false : true;
		boosts = s[11];
		critical = (s[12].equalsIgnoreCase("null")) ? 0 : Integer.parseInt(s[12]);
		if(s[13].equalsIgnoreCase("null")) {
			drain = new Ratio(0, 1);
		}
		else {
			String[] s2 = s[13].replaceAll("[\\[\\],]", "").split(" ");
			int first = Integer.parseInt(s2[0]);
			int second = Integer.parseInt(s2[1]);
			drain = new Ratio(first, second);
		}
		if(s[14].equalsIgnoreCase("null")) {
			heal = new Ratio(0, 1);
		}
		else {
			String[] s2 = s[14].replaceAll("[\\[\\],]", "").split(" ");
			int first = Integer.parseInt(s2[0]);
			int second = Integer.parseInt(s2[1]);
			heal = new Ratio(first, second);
		}
		status = s[15];
		target = s[16];
		type = s[17];

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

	public String getMoveName() {
		return moveName;
	}

	public int getMoveNumber() {
		return moveNumber;
	}

	public int getBasePower() {
		return basePower;
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
		String answer = "BasePower: " + basePower + "\n" + "MoveNumber: " + moveNumber + "\n" + "MoveName: " + moveName
				+ "\n" + "Type: " + type;
//                +"\n"+
//                "Status: "+status
//                +"\n"+
//                "Status Chance: "+statusChance+"/100"
//                +"\n";
		return answer;
	}
}
