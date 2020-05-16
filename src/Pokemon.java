import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Pokemon {
	private int dex = 0;
	private String name = "";
	private int baseHealth;
	private int health;
	private int baseSpecialDefense;
	private int sleepCounter;
	private int baseDefense;

	private int baseAttack;

	private int baseSpecialAttack;
	private String status = "";
	private int baseSpeed;

	private Move[] moves = new Move[4];

	private String type1 = "";
	private String type2 = "";

	// private String status;
//    private boolean hasStatus;
	private int attackIncrease;
	private int attackDecrease;
	private int specialAttackIncrease;
	private int specialAttackDecrease;
	private int defenseIncrease;
	private int defenseDecrease;
	private int specialDefenseIncrease;
	private int specialDefenseDecrease;
	private int speedIncrease;
	private int speedDecrease;

	public int convertMonName(String monName) {
		int moveNumber = -1;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Stats.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Stats.txt"));
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splittedCopy;
		splittedCopy = copy.split("\n");
		for (int i = 0; i < 719; i++) {
			String stats = splittedCopy[i];
			String[] s = stats.split(";");
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

	public Pokemon(int dexNumber) {
		dex = dexNumber;
		for (int i = 0; i < 4; i++) {
			moves[i] = new Move(1);
		}
		setStats(dex);
	}

	public Pokemon(String pokeName) {
		name = pokeName;
		int dexNumber = convertMonName(pokeName);
		dex = dexNumber;
		for (int i = 0; i < 4; i++) {
			moves[i] = new Move(1);
		}
		setStats(dex);
	}
//    public Pokemon( int dexParam,
//                    String nameParam,
//                    int healthParam,
////                    int specialDefenseParam,
//                    int defenseParam,
//                    int attackParam,
////                    int specialAttackParam,
//                    int speedParam,
//                    Move[] movesParam,
//                    String type1Param,
//                    String type2Param
////                    String statusParam,
////
////                    int attackModifierParam,
//////                    int specialAttackModifierParam,
////                    int defenseModifierParam,
//////                    int specialDefenseModifierParam,
////                    int speedModifierParam
//                    ){
//
//        dex=dexParam;
//        name=nameParam;
//        health=healthParam;
//        baseSpecialDefense=specialDefenseParam;
//        baseDefense=defenseParam;
//        baseAttack=attackParam;
////        specialAttack=specialAttackParam;
//        speed=speedParam;
//        moves=movesParam;
//        type1=type1Param;
//        type2=type2Param;
////        status=statusParam;
////        if(status.equalsIgnoreCase("NONE")){
////            hasStatus=false;
////        }
////        else{
////            hasStatus=true;
////        }
////        attackModifier=attackModifierParam;
//////        specialAttackModifier=specialAttackModifierParam;
////        defenseModifier=defenseModifierParam;
//////        specialDefenseModifier=specialDefenseModifierParam;
////        speedModifier=speedModifierParam;
////        statsApplied=true;
//    }

	public void setStats(int dexNumber) {

		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Stats.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Stats.txt"));
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] splittedCopy;
		splittedCopy = copy.split("\n");
		String stats = splittedCopy[dexNumber - 1];
		String[] s = stats.split(";");
		name = s[1];
		type1 = s[2];
		type2 = s[3];
		baseHealth = Integer.parseInt(s[4]);
		health = baseHealth;
		baseAttack = Integer.parseInt(s[5]);
		baseDefense = Integer.parseInt(s[6]);
		baseSpecialAttack = Integer.parseInt(s[7]);
		baseSpecialDefense = Integer.parseInt(s[8]);
		baseSpeed = Integer.parseInt(s[9]);
//        String content = Files.readString(Paths.get("Stats.txt"));
//        File f = new File("Stats.txt");
//        List<String> copy;
//        try {
//            copy=Files.readAllLines(new File("Stats.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        try {
////            Scanner reader= new Scanner(f);
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        }
//        String copy=reader.toString();

	}

	public int getDex() {
		return dex;
	}

	public int getHealth() {
		return health;
	}

	public String getID() {
		return name.replace(" ", "").replace("-", "").replace(".", "").toLowerCase();
	}

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

	public void setMoves(int[] givenMoves) {
		for (int i = 0; i < moves.length; i++) {
			moves[i] = new Move(givenMoves[i]);
		}
	}


	public void setMoves(Move[] givenMoves) {
		moves = givenMoves;
	}

	public Move[] getMoves() {
		return moves;
	}

	public String getName() {
		return name;
	}

	public String getType1() {
		return type1;
	}

	public String getType2() {
		return type2;
	}

	public void takeDamage(int damage) {

		health -= damage;
	}

	public void setAttackIncrease(int stages) {
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

	public void setSpecialAttackIncrease(int stages) {
		if(specialAttackIncrease==6){
			System.out.println(name + "'s special attack can't go any higher!");
		}
		else {
			int actualStages=0;
			while (specialAttackIncrease + 1 < 7 && stages > 0) {
				specialAttackIncrease++;
				stages--;
				actualStages++;
			}
//			specialAttackIncrease += stages;
			System.out.println(name + "'s special attack increased by " + actualStages + " stages!");
		}
//		specialAttackIncrease += stages;
//		System.out.println(name + "'s special attack increased by " + stages + " stages!");
	}

	public void setDefenseIncrease(int stages) {
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
//		defenseIncrease += stages;
//		System.out.println(name + "'s defense increased by " + stages + " stages!");
	}

	public void setSpecialDefenseIncrease(int stages) {
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

//		specialDefenseIncrease += stages;
//		System.out.println(name + "'s special defense increased by " + stages + " stage!");
	}

	public void setSpeedIncrease(int stages) {
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

	public void setAttackDecrease(int stages) {
		if(attackDecrease==6){
			System.out.println(name + "'s attack can't go any higher!");
		}
		else {
			int actualStages=0;
			while (attackDecrease + 1 < 7 && stages > 0) {
				attackDecrease++;
				stages--;
				actualStages++;
			}
//			attackDecrease += stages;
			System.out.println(name + "'s attack Decreased by " + actualStages + " stages!");
		}
//		attackDecrease += stages;
//		System.out.println(name + "'s attack decreased by " + stages + " stages...");

	}

	public void setSpecialAttackDecrease(int stages) {
		if(specialAttackDecrease==6){
			System.out.println(name + "'s special attack can't go any higher!");
		}
		else {
			int actualStages=0;
			while (specialAttackDecrease + 1 < 7 && stages > 0) {
				specialAttackDecrease++;
				stages--;
				actualStages++;
			}
//			specialAttackDecrease += stages;
			System.out.println(name + "'s special attack Decreased by " + actualStages + " stages!");
		}
//		specialAttackDecrease += stages;
//		System.out.println(name + "'s special attack decreased by " + stages + " stages...");
	}

	public void setDefenseDecrease(int stages) {
		if(defenseDecrease==6){
			System.out.println(name + "'s defense can't go any higher!");
		}
		else {
			int actualStages=0;
			while (defenseDecrease + 1 < 7 && stages > 0) {
				defenseDecrease++;
				stages--;
				actualStages++;
			}
//			defenseDecrease += stages;
			System.out.println(name + "'s defense Decreased by " + actualStages + " stages!");
		}
//		defenseDecrease += stages;
//		System.out.println(name + "'s defense decreased by " + stages + " stages...");
	}

	public void setSpecialDefenseDecrease(int stages) {
		if(specialDefenseDecrease==6){
			System.out.println(name + "'s special defense can't go any higher!");
		}
		else {
			int actualStages=0;
			while (specialDefenseDecrease + 1 < 7 && stages > 0) {
				specialDefenseDecrease++;
				stages--;
				actualStages++;
			}
//			specialDefenseDecrease += stages;
			System.out.println(name + "'s special defense Decreased by " + actualStages + " stages!");
		}
//		specialDefenseDecrease += stages;
//		System.out.println(name + "'s special defense decreased by " + stages + " stages...");
	}

	public void setSpeedDecrease(int stages) {
		if(speedDecrease==6){
			System.out.println(name + "'s speed can't go any higher!");
		}
		else {
			int actualStages=0;
			while (speedDecrease + 1 < 7 && stages > 0) {
				speedDecrease++;
				stages--;
				actualStages++;
			}
//			speedDecrease += stages;
			System.out.println(name + "'s speed Decreased by " + actualStages + " stages!");
		}
//		speedDecrease += stages;
//		System.out.println(name + "'s speed decreased by " + stages + " stages...");
	}
	public void resetBoosts(){
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
//    public void beParalyzed(Pokemon target)
//    {
//        if(target.getType1().equalsIgnoreCase("Electric") || target.getType2().equalsIgnoreCase("Electric"))
//        {
//            return;
//        }
//    }
//
//    public void bePoisoned(Pokemon target)
//    {
//        if(target.getType1().equalsIgnoreCase("Poison") || target.getType2().equalsIgnoreCase("Posion") || target.getType1().equalsIgnoreCase("Steel") || target.getType2().equalsIgnoreCase("Steel"))
//        {
//            return;
//        }
//        health = (int)(health - baseHealth * 1.0/8);
//    }
//
//    public void beBurned(Pokemon target)
//    {
//        if(target.getType1().equalsIgnoreCase("Fire") || target.getType2().equalsIgnoreCase("Fire"))
//        {
//            return;
//        }
//        health = (int)(health - baseHealth * 0.06);
//    }
//    public void beFrozen(Pokemon target)
//    {
//        if(target.getType1().equalsIgnoreCase("Ice") || target.getType2().equalsIgnoreCase("Ice"))
//        {
//            return;
//        }
//        int unfreeze = (int)(Math.random()*5);
//        if(unfreeze == 0)
//        {
//            return;
//        }
//        else
//        {
//            moves = null;
//        }
//    }
//
//    public void beFlinched(Pokemon target)
//    {
//        moves = null;
//        return;
//    }
//
//    public void beConfused(Pokemon target)
//    {
//        int unflinch = (int)(Math.random()*4);
//        if(unflinch == 0)
//        {
//            return;
//        }
//        int damage = (int)(Math.random()*2);
//        if(damage == 0)
//        {
//            calculator.calculateBasicDamage(this, this, new Move(1));
//            moves = null;
//        }
//    }
	// public String getStatus() {
//        return status;
//    }

//    public int getAttackModifier() {
//        return attackModifier;
//    }
//
//    public int getDefenseModifier() {
//        return defenseModifier;
//    }

//    public int getSpecialAttackModifier() {
//        return specialAttackModifier;
//    }
//
//    public boolean hasStatus() {
//        return hasStatus;
//    }
//
//    public int getSpecialDefenseModifier() {
//        return specialDefenseModifier;
//    }
//    public int getSpeedModifier() {
//        return speedModifier;
//
//    }
//
//    public boolean StatsApplied() {
//        return statsApplied;
//    }
//

//
//    }
//    dex=dexParam;
//    name=nameParam;
//    health=healthParam;
//    specialDefense=specialDefenseParam;
//    defense=defenseParam;
//    attack=attackParam;
//    specialAttack=specialAttackParam;
//    speed=speedParam;
//    moves=movesParam;
//    type1=type1Param;
//    type2=type2Param;
//    status=statusParam;
//        if(status.equalsIgnoreCase("NONE")){
//        hasStatus=false;
//    }
//        else{
//        hasStatus=true;
//    }
//    attackModifier=attackModifierParam;
//    specialAttackModifier=specialAttackModifierParam;
//    defenseModifier=defenseModifierParam;
//    specialDefenseModifier=specialDefenseModifierParam;
//    speedModifier=speedModifierParam;
//    statsApplied=true;

	// int dexParam,
//    String nameParam,
//    int healthParam,
//    int specialDefenseParam,
//    int defenseParam,
//    int attackParam,
//    int specialAttackParam,
//    int speedParam,
//    Move[] movesParam,
//    String type1Param,
//    String type2Param,
//    String statusParam,
//
//    int attackModifierParam,
//    int specialAttackModifierParam,
//    int defenseModifierParam,
//    int specialDefenseModifierParam,
//    int speedModifierParam
//    public Pokemon applyModifiers(Pokemon target){
//        return new Pokemon(target.getDex(),target.getName(),target.getHealth(),
////                target.getSpecialDefense()+(int)(target.getSpecialDefenseModifier()*0.5*target.getSpecialDefense()),
//                target.getDefense()+(int)(target.getDefenseModifier()*0.5*target.getDefense()),
//                target.getAttack()+(int)(target.getAttackModifier()*0.5*target.getAttack()),
////                target.getSpecialAttack()+(int)(target.getSpecialAttackModifier()*0.5*target.getSpecialAttack()),
//                target.getSpeed()+(int)(target.getSpeedModifier()*0.5*target.getSpeed()),
//                target.getMoves(),
//                target.getType1(),target.getType2(),
////                target.getStatus(),
//                target.getAttackModifier(),
////                target.getSpecialAttackModifier(),
//                target.getDefenseModifier(),
////                target.getSpecialDefenseModifier(),
//                target.getSpeedModifier()
//                   );
//    }
//
	public String toString() {
		String answer = null
//                "Status: "+status
//                +"\n"+

		;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBaseHealth() {
		return baseHealth;
	}
}