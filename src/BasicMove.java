import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BasicMove {
	private String moveName;
	private int moveNumber;
	private int basePower;
	String description;
	int pp;
	String flags;
//    private boolean isSpecial;
	private String type;
	String category;
	int accuracy;
	int priority;

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
		if (s[1].equalsIgnoreCase("true")) {
			accuracy = 100;
		} else {
			accuracy = Integer.parseInt(s[1]);
		}
		basePower = Integer.parseInt(s[2]);
		category = s[3];
		description = s[4];
		moveName = s[5];
		pp = Integer.parseInt(s[6]);
		priority = Integer.parseInt(s[7]);
		flags = s[8];
		type = s[9];

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
