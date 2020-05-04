public class Move {
	private String moveName;
	private int moveNumber;
	private int basePower;
	private boolean isSpecial;
	private String type;
	private String status;
	private int statusChance;
	private String modifier;
	private int modifierMagnitude;
	private int modifierChance;
	private int priority;

	public Move(int moveNumberParam) {
		moveNumber = moveNumberParam;
	}

//    private void setStats(int moveNumber){
//        //code way to set all the stats;
//    }

	public String getMoveName() {
		return moveName;
	}

	public int getMoveNumber() {
		return moveNumber;
	}

	public int getBasePower() {
		return basePower;
	}

	public int getStatusChance() {
		return statusChance;
	}

	public String getStatus() {
		return status;
	}

	public String getType() {
		return type;
	}

	public int getModifierChance() {
		return modifierChance;
	}

	public int getModifierMagnitude() {
		return modifierMagnitude;
	}

	public String getModifier() {
		return modifier;
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public int getPriority() {
		return priority;
	}

	public String toString() {
		String answer = "BasePower: " + basePower + "\n" + "MoveNumber: " + moveNumber + "\n" + "MoveName: " + moveName
				+ "\n" + "Type: " + type + "\n" + "Status: " + status + "\n" + "Status Chance: " + statusChance + "/100"
				+ "\n";
		return answer;
	}
}
