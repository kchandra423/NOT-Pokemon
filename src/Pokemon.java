//
//  Author: Kumar Chandra
//  Revised: Kumar Chandra
//           5/3/20
//
//  Notes:
//      This class creates Pokemon, which have 4 moves, and numerous different stats.
//      This class stores info about a pokemon, and keeps track of any status conditions or modifiers it may have
//
//  Bugs:
//    none
//
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Pokemon {
	private int dex = 0;//self explanatory
	private String name = "";//self explanatory
	private int baseHealth;//self explanatory
	private int health;//current health
	private int baseSpecialDefense;//self explanatory
	private int sleepCounter;//not implemented yet
	private int baseDefense;//self explanatory
	private int baseAttack;//self explanatory
	private int baseSpecialAttack;//self explanatory
	private String status = "";//self explanatory
	private int baseSpeed;//self explanatory
	private Move[] moves = new Move[4];//each pokemon has 4 moves
	private String type1 = "";//self explanatory
	private String type2 = "";//self explanatory
	//****All your stat modifiers, which are essentially just buffs and debuffs******
	private int attackIncrease;//self explanatory
	private int attackDecrease;//self explanatory
	private int specialAttackIncrease;//self explanatory
	private int specialAttackDecrease;//self explanatory
	private int defenseIncrease;//self explanatory
	private int defenseDecrease;//self explanatory
	private int specialDefenseIncrease;//self explanatory
	private int specialDefenseDecrease;//self explanatory
	private int speedIncrease;//self explanatory
	private int speedDecrease;//self explanatory

	public int convertMonName(String monName) {//takes a name of a pokemon and gets its dex number
		int moveNumber = -1;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Text/Stats.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Text/Stats.txt"));//he turns the stats.txt file into a string
			// (funniest thing ive ever seen)
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splittedCopy;
		splittedCopy = copy.split("\n");//splits it by line
		for (int i = 0; i < 802; i++) {
			String stats = splittedCopy[i];
			String[] s = stats.split(";");//splits by semicolon,
			// just look at the stats.txt file and youll understand
			try {
				if (s[1].equalsIgnoreCase(monName)) {
					moveNumber = Integer.parseInt(s[0]);
					break;
				}
			} catch (Exception e) {

			}
		}
		return moveNumber;
	}

	public Pokemon(int dexNumber) {//instantiates with a number
		dex = dexNumber;
		for (int i = 0; i < 4; i++) {
			moves[i] = new Move(1);
		}
		setStats(dex);
	}

	public Pokemon(String pokeName) {//instantiates with a name
		name = pokeName;
		int dexNumber = convertMonName(pokeName);
		dex = dexNumber;
		for (int i = 0; i < 4; i++) {
			moves[i] = new Move(1);
		}
		setStats(dex);
	}

	//uses the stats file to get the stats of a pokemon
	public void setStats(int dexNumber) {

		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Text/Stats.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Text/Stats.txt"));
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splittedCopy;
		splittedCopy = copy.split("\n");//splits by line
		String stats = splittedCopy[dexNumber - 1];
		String[] s = stats.split(";");//splits y ;
		name = s[1];//self explanatory
		type1 = s[2];//self explanatory
		type2 = s[3];//self explanatory
		baseHealth = Integer.parseInt(s[4])*2+110;
		health = baseHealth;//self explanatory
		baseAttack = Integer.parseInt(s[5])*2+5;
		baseDefense = Integer.parseInt(s[6])*2+5;
		baseSpecialAttack = Integer.parseInt(s[7])*2+5;//self explanatory
		baseSpecialDefense = Integer.parseInt(s[8])*2+5;
		baseSpeed = Integer.parseInt(s[9])*2+5;


	}

	public int getDex() {
		return dex;
	}//self explanatory

	public int getHealth() {
		return health;
	}//self explanatory

	public String getID() {//some of the txt files use a pokemons id, which is just its name without spaces or capitalizon or special symbols
		return name.replace(" ", "").replace("-", "").replace(".", "").toLowerCase();
	}






	//**************************************
	//**************************************
	//***********IMPORTANT*******************
	//**************************************
	//***************************************
	// the way stat modifiers work is that its calculated by getting
	//your base stat, and using modifiers to calculate whatever it is currently
	// it uses this formulate base stat * (2+statincrease)/(2)
	// or base stat* (2)/(2+stat decrease)
	// a stat increase and decrease cancel each other out
	// you can't just do base stat * (2+statincrease)/(2+stat decrease) or else your numbers will be wrong
	// all of the getStat methods use this are basically the same
	// also stat increases and decreases cant go above 6





	public int getSpecialDefense() {
		if (specialDefenseDecrease == 0 && specialDefenseIncrease > 0) {// no stat decrease, their is a stat increase
			return (int) (baseSpecialDefense * ((2.0 + specialDefenseIncrease) / 2.0));
		} else if (specialDefenseDecrease > 0 && specialDefenseIncrease == 0) {// no stat increase, their is a stat
			// decrease
			return (int) (baseSpecialDefense * (2.0 / (2.0 + specialDefenseDecrease)));
		} else if (specialDefenseIncrease > specialDefenseDecrease && specialDefenseDecrease != 0) {// stat increase and
			// stat decrease,
			// but stat increase
			// is higher.
			while (specialDefenseDecrease > 0) {
				specialDefenseDecrease--;
				specialDefenseIncrease--;
			}
			return (int) (baseSpecialDefense * ((2.0 + specialDefenseIncrease) / 2.0));
		} else if (specialDefenseDecrease > specialDefenseIncrease && specialDefenseIncrease != 0) {// stat increase and
			// decrease, but
			// stat decrease is
			// higher
			while (specialDefenseIncrease > 0) {
				specialDefenseDecrease--;
				specialDefenseIncrease--;
			}
			return (int) (baseSpecialDefense * (2.0 / (2.0 + specialDefenseDecrease)));

		} else if (specialDefenseDecrease == 0 && specialDefenseIncrease == 0) {// no stat changes
			return baseSpecialDefense;
		} else {// this shouldn't happen
			while (true) {
				System.out.println("what");
			}
		}
	}

	public int getDefense() {
		if (defenseDecrease == 0 && defenseIncrease > 0) {// no stat decrease, their is a stat increase
			return (int) (baseDefense * ((2.0 + defenseIncrease) / 2.0));
		} else if (defenseDecrease > 0 && defenseIncrease == 0) {// no stat increase, their is a stat decrease
			return (int) (baseDefense * (2.0 / (2.0 + defenseDecrease)));
		} else if (defenseIncrease > defenseDecrease && defenseDecrease != 0) {// stat increase and stat decrease, but
			// stat increase is higher.
			while (defenseDecrease > 0) {
				defenseDecrease--;
				defenseIncrease--;
			}
			return (int) (baseDefense * ((2.0 + defenseIncrease) / 2.0));
		} else if (defenseDecrease > defenseIncrease && defenseIncrease != 0) {// stat increase and decrease, but stat
			// decrease is higher
			while (defenseIncrease > 0) {
				defenseDecrease--;
				defenseIncrease--;
			}
			return (int) (baseDefense * (2.0 / (2.0 + defenseDecrease)));

		} else if (defenseDecrease == 0 && defenseIncrease == 0) {// no stat changes
			return baseDefense;
		} else {// this shouldn't happen

			while (true) {
				System.out.println("what");
			}
		}
	}

	public int getAttack() {
		if (attackDecrease == 0 && attackIncrease > 0) {// no stat decrease, their is a stat increase
			return (int) (baseAttack * ((2.0 + attackIncrease) / 2.0));
		} else if (attackDecrease > 0 && attackIncrease == 0) {// no stat increase, their is a stat decrease
			return (int) (baseAttack * (2.0 / (2.0 + attackDecrease)));
		} else if (attackIncrease > attackDecrease && attackDecrease != 0) {// stat increase and stat decrease, but stat
			// increase is higher.
			while (attackDecrease > 0) {
				attackDecrease--;
				attackIncrease--;
			}
			return (int) (baseAttack * ((2.0 + attackIncrease) / 2.0));
		} else if (attackDecrease > attackIncrease && attackIncrease != 0) {// stat increase and decrease, but stat
			// decrease is higher
			while (attackIncrease > 0) {
				attackDecrease--;
				attackIncrease--;
			}
			return (int) (baseAttack * (2.0 / (2.0 + attackDecrease)));

		} else if (attackDecrease == 0 && attackIncrease == 0) {// no stat changes
			return baseAttack;
		} else {// this shouldn't happen
			while (true) {
				System.out.println("what");
			}
		}
	}

	public int getSpecialAttack() {
		if (specialAttackDecrease == 0 && specialAttackIncrease > 0) {// no stat decrease, their is a stat increase
			return (int) (baseSpecialAttack * ((2.0 + specialAttackIncrease) / 2.0));
		} else if (specialAttackDecrease > 0 && specialAttackIncrease == 0) {// no stat increase, their is a stat
			// decrease
			return (int) (baseSpecialAttack * (2.0 / (2.0 + specialAttackDecrease)));
		} else if (specialAttackIncrease > specialAttackDecrease && specialAttackDecrease != 0) {// stat increase and
			// stat decrease,
			// but stat increase
			// is higher.
			while (specialAttackDecrease > 0) {
				specialAttackDecrease--;
				specialAttackIncrease--;
			}
			return (int) (baseSpecialAttack * ((2.0 + specialAttackIncrease) / 2.0));
		} else if (specialAttackDecrease > specialAttackIncrease && specialAttackIncrease != 0) {// stat increase and
			// decrease, but
			// stat decrease is
			// higher
			while (specialAttackIncrease > 0) {
				specialAttackDecrease--;
				specialAttackIncrease--;
			}
			return (int) (baseSpecialAttack * (2.0 / (2.0 + specialAttackDecrease)));

		} else if (specialAttackDecrease == 0 && specialAttackIncrease == 0) {// no stat changes
			return baseSpecialAttack;
		} else {// this shouldn't happen
			while (true) {
				System.out.println("what");
			}
		}
	}

	public int getSpeed() {
		if (speedDecrease == 0 && speedIncrease > 0) {// no stat decrease, their is a stat increase
			return (int) (baseSpeed * ((2.0 + speedIncrease) / 2.0));
		} else if (speedDecrease > 0 && speedIncrease == 0) {// no stat increase, their is a stat decrease
			return (int) (baseSpeed * (2.0 / (2.0 + speedDecrease)));
		} else if (speedIncrease > speedDecrease && speedDecrease != 0) {// stat increase and stat decrease, but stat
			// increase is higher.
			while (speedDecrease > 0) {
				speedDecrease--;
				speedIncrease--;
			}
			return (int) (baseSpeed * ((2.0 + speedIncrease) / 2.0));
		} else if (speedDecrease > speedIncrease && speedIncrease != 0) {// stat increase and decrease, but stat
			// decrease is higher
			while (speedIncrease > 0) {
				speedDecrease--;
				speedIncrease--;
			}
			return (int) (baseSpeed * (2.0 / (2.0 + speedDecrease)));

		} else if (speedDecrease == 0 && speedIncrease == 0) {// no stat changes
			return baseSpeed;
		} else {// this shouldn't happen
			while (true) {
				System.out.println("what");
			}
		}
	}









	//****************************************














	public void setMoves(int[] givenMoves) {//sets your moves, converts given integers into moves
		for (int i = 0; i < moves.length; i++) {
			moves[i] = new Move(givenMoves[i]);
		}
	}


	public void setMoves(Move[] givenMoves) {//sets your moves with moves
		moves = givenMoves;
	}

	public Move[] getMoves() {//self explanatory
		return moves;
	}

	public String getName() {//self explanatory
		return name;
	}

	public String getType1() {//self explanatory
		return type1;
	}

	public String getType2() {//self explanatory
		return type2;
	}

	public void takeDamage(int damage) {//self explanatory

		health -= damage;
		if(health < 0) {
			health = 0;
		}
	}
	
	public void heal(int heal) {//self explanatory

		health += heal;
		if(health > baseHealth) {
			health = baseHealth;
		}
	}

	public void setAttackIncrease(int stages) {//self explanatory
		if(attackIncrease==6){
			System.out.println(name + "'s attack can't go any higher!");
		}
		else {
			int actualStages=0;
			while (attackIncrease + 1 < 7 && stages > 0) {
				attackIncrease++;
				stages--;
				actualStages++;
			}
//			attackIncrease += stages;
			System.out.println(name + "'s attack increased by " + actualStages + " stages!");
		}
	}

	public void setSpecialAttackIncrease(int stages) {//self explanatory
		if (specialAttackIncrease == 6) {
			System.out.println(name + "'s special attack can't go any higher!");
		} else {
			int actualStages = 0;
			while (specialAttackIncrease + 1 < 7 && stages > 0) {
				specialAttackIncrease++;
				stages--;
				actualStages++;
			}
//			specialAttackIncrease += stages;
			System.out.println(name + "'s special attack increased by " + actualStages + " stages!");
		}
	}

	public void setDefenseIncrease(int stages) {//self explanatory
		if(defenseIncrease==6){
			System.out.println(name + "'s defense can't go any higher!");
		}
		else {
			int actualStages=0;
			while (defenseIncrease + 1 < 7 && stages > 0) {
				defenseIncrease++;
				stages--;
				actualStages++;
			}
//			defenseIncrease += stages;
			System.out.println(name + "'s defense increased by " + actualStages + " stages!");
		}

	}

	public void setSpecialDefenseIncrease(int stages) {//self explanatory
		if(specialDefenseIncrease==6){
			System.out.println(name + "'s special defense can't go any higher!");
		}
		else {
			int actualStages=0;
			while (specialDefenseIncrease + 1 < 7 && stages > 0) {
				specialDefenseIncrease++;
				stages--;
				actualStages++;
			}
//			specialDefenseIncrease += stages;
			System.out.println(name + "'s special defense increased by " + actualStages + " stages!");
		}

}

	public void setSpeedIncrease(int stages) {//self explanatory
		if(speedIncrease==6){
			System.out.println(name + "'s speed can't go any higher!");
		}
		else {
			int actualStages=0;
			while (speedIncrease + 1 < 7 && stages > 0) {
				speedIncrease++;
				stages--;
				actualStages++;
			}
//			speedIncrease += stages;
			System.out.println(name + "'s speed increased by " + actualStages + " stages!");
		}
//		speedIncrease += stages;
//		System.out.println(name + "'s speed increased by " + stages + " stages!");

	}

	public void setAttackDecrease(int stages) {//self explanatory
		if(attackDecrease==6){
			System.out.println(name + "'s attack can't go any lower!");
		}
		else {
			int actualStages=0;
			while (attackDecrease + 1 < 7 && stages > 0) {
				attackDecrease++;
				stages--;
				actualStages++;
			}
//			attackDecrease += stages;
			System.out.println(name + "'s attack Decreased by " + actualStages + " stages...");
		}


	}

	public void setSpecialAttackDecrease(int stages) {//self explanatory
		if(specialAttackDecrease==6){
			System.out.println(name + "'s special attack can't go any lower!");
		}
		else {
			int actualStages=0;
			while (specialAttackDecrease + 1 < 7 && stages > 0) {
				specialAttackDecrease++;
				stages--;
				actualStages++;
			}
//			specialAttackDecrease += stages;
			System.out.println(name + "'s special attack Decreased by " + actualStages + " stages...");
		}

	}

	public void setDefenseDecrease(int stages) {//self explanatory
		if(defenseDecrease==6){
			System.out.println(name + "'s defense can't go any lower!");
		}
		else {
			int actualStages=0;
			while (defenseDecrease + 1 < 7 && stages > 0) {
				defenseDecrease++;
				stages--;
				actualStages++;
			}
//			defenseDecrease += stages;
			System.out.println(name + "'s defense Decreased by " + actualStages + " stages...");
		}

	}

	public void setSpecialDefenseDecrease(int stages) {//self explanatory
		if(specialDefenseDecrease==6){
			System.out.println(name + "'s special defense can't go any lower!");
		}
		else {
			int actualStages=0;
			while (specialDefenseDecrease + 1 < 7 && stages > 0) {
				specialDefenseDecrease++;
				stages--;
				actualStages++;
			}
//			specialDefenseDecrease += stages;
			System.out.println(name + "'s special defense Decreased by " + actualStages + " stages...");
		}

	}

	public void setSpeedDecrease(int stages) {//self explanatory
		if(speedDecrease==6){
			System.out.println(name + "'s speed can't go any lower!");
		}
		else {
			int actualStages=0;
			while (speedDecrease + 1 < 7 && stages > 0) {
				speedDecrease++;
				stages--;
				actualStages++;
			}
//			speedDecrease += stages;
			System.out.println(name + "'s speed Decreased by " + actualStages + " stages...");
		}
	}
	public void resetBoosts(){//self explanatory
		specialAttackDecrease=0;
		specialAttackIncrease=0;
		attackIncrease=0;
		attackDecrease=0;
		defenseDecrease=0;
		defenseIncrease=0;
		specialDefenseDecrease=0;
		specialDefenseIncrease=0;
		speedDecrease=0;
		speedDecrease=0;
	}

	public String toString() {//self explanatory
		String answer = null;
		try {
			answer = "\n" + "Name: " + name
					+ "\n" + "Health: " + health + " / " + baseHealth
					+ "\n" + "Defense: " + baseDefense
					+ "\n" + "Attack: " + baseAttack
					+ "\n" + "Special Attack: " + baseSpecialAttack
					+ "\n" + "Special Defense: " + baseSpecialDefense
					+ "\n" + "Speed: " + baseSpeed
					+ "\n" + "Modifiers: " + (attackIncrease - attackDecrease) + " attack, "
					+ (defenseIncrease - defenseDecrease) + " defense, "
					+ (specialAttackIncrease - specialAttackDecrease) + " special attack, "
					+ (specialDefenseIncrease - specialDefenseDecrease) + " special defense, "
					+ (speedIncrease - speedDecrease) + " speed"
					+ "\n" + "Status: " + status
					+ "\n" + "Types: " + type1 + " and " + type2 + "\n";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	public String getStatus() {//self explanatory
		return status;
	}

	public void setStatus(String status) {//self explanatory
		this.status = status;
	}

	public int getBaseHealth() {//self explanatory
		return baseHealth;
	}
}