import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class Calculator {
	String typings[] = { "Normal", "Fighting", "Flying", "Poison", "Ground", "Rock", "Bug", "Ghost", "Steel", "Fire",
			"Water", "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy" };

	double normal[] = { 1, 1, 1, 1, 1, .5, 1, 0, .5, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	double fighting[] = { 2, 1, .5, .5, 1, 2, .5, 0, 2, 1, 1, 1, 1, .5, 2, 1, 2, .5 };
	double flying[] = { 1, 2, 1, 1, 1, .5, 2, 1, .5, 1, 1, 2, .5, 1, 1, 1, 1, 1 };
	double poison[] = { 1, 1, 1, .5, .5, .5, 1, .5, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2 };
	double ground[] = { 1, 1, 0, 2, 1, 2, .5, 1, 2, 2, 1, .5, 2, 1, 1, 1, 1, 1 };
	double rock[] = { 1, .5, 2, 1, .5, 1, 2, 1, .5, 2, 1, 1, 1, 1, 2, 1, 1, 2, .5 };
	double bug[] = { 1, .5, .5, .5, 1, 1, 1, .5, .5, .5, 1, 2, 1, 2, 1, 1, 2, .5 };
	double ghost[] = { 0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, 1 };
	double steel[] = { 1, 1, 1, 1, 1, 2, 1, 1, .5, .5, .5, 1, .5, 1, 2, 1, 1, 2 };
	double fire[] = { 1, 1, 1, 1, 1, .5, 2, 1, 2, .5, .5, 2, 1, 1, 2, .5, 1, 1 };
	double water[] = { 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, .5, .5, 1, 1, 1, .5, 1, 1 };
	double grass[] = { 1, 1, .5, .5, 2, 2, .5, 1, .5, .5, 2, .5, 1, 1, 1, .5, 1, 1 };
	double electric[] = { 1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 2, .5, .5, 1, 1, .5, 1, 1 };
	double psychic[] = { 1, 2, 1, 2, 1, 1, 1, 1, .5, 1, 1, 1, 1, .5, 1, 1, 0, 1 };
	double ice[] = { 1, 1, 2, 1, 2, 1, 1, 1, .5, .5, .5, 2, 1, 1, .5, 2, 1, 1 };
	double dragon[] = { 1, 1, 1, 1, 1, 1, 1, 1, .5, 1, 1, 1, 1, 1, 1, 2, 1, 0 };
	double dark[] = { 1, .5, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, .5 };
	double fairy[] = { 1, 2, 1, .5, 1, 1, 1, 1, .5, .5, 1, 1, 1, 1, 1, 2, 2, 1 };
	double typingArray[][] = { normal, fighting, flying, poison, ground, rock, bug, ghost, steel, fire, water, grass,
			electric, psychic, ice, dragon, dark, fairy };

//nathan eel did that
	public int getIntFromType(String type) {
		int answer = -1;

		for (int i = 0; i < typings.length; i++) {
			if (type.equalsIgnoreCase(typings[i])) {
				answer = i;
				break;
			}

		}
		return answer;
	}

	public double typeModifier(int attackType, int defendType1, int defendType2) {
		double answer = 0;
		if (defendType2 == -1) {

			answer = typingArray[attackType][defendType1];
			return answer;
		} else {
			answer = typingArray[attackType][defendType1] * typingArray[attackType][defendType2];
			return answer;
		}

	}

//    public int calculateDamage(Pokemon user, Pokemon target, int movNumber){
//        //code way to calculate damage
//
//    }
	public boolean hasMove(Pokemon mon, Move move) {
		boolean answer = false;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Stats.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Learnsets.txt"));
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] lines = copy.split("\n");
		String currentLine = "";
		for (int i = 0; i < lines.length; i++) {
			currentLine = lines[i];
			if (currentLine.contains(mon.getName().replace(" ", "").replace("-", "").replace(".", "").toLowerCase())) {
				if (currentLine.contains(move.getId())) {
					answer = true;
				}
			}
		}
		return answer;
	}

	public int calculateBasicDamage(Pokemon user, Pokemon target, Move move) {
		// code way to calculate damage
		if (move.getAccuracy() == 101) {

		} else if (Math.random() > (double) move.getAccuracy() / 100) {
			System.out.println(target.getName() + " evaded the attack!");
			return 0;

		}
		if (move.isTarget()) {
			target = user;
		}
		int damage = 0;
		int attack, defense, power, moveType, defenseType1, defenseType2, userType1, userType2;
		Pokemon boostsTarget;
		if (move.getCategory().equalsIgnoreCase("Physical")) {
			attack = user.getAttack();
			defense = target.getDefense();
			power = move.getPower();
			moveType = getIntFromType(move.getType());
			defenseType1 = getIntFromType(target.getType1());
			defenseType2 = getIntFromType(target.getType2());
			userType1 = getIntFromType(user.getType1());
			userType2 = getIntFromType(user.getType2());

		} else if (move.getCategory().equalsIgnoreCase("Special")) {
			attack = user.getSpecialAttack();
			defense = target.getSpecialDefense();
			power = move.getPower();
			moveType = getIntFromType(move.getType());
			defenseType1 = getIntFromType(target.getType1());
			defenseType2 = getIntFromType(target.getType2());
			userType1 = getIntFromType(user.getType1());
			userType2 = getIntFromType(user.getType2());
		} else {
			if (move.isSelf()) {
				boostsTarget = user;
			} else {
				boostsTarget = target;
			}
			if (!(move.getBoosts().equalsIgnoreCase("null"))) {
				if (move.getBoosts().contains("atk")) {
					String attackChange = move.getBoosts().substring(move.getBoosts().indexOf("atk") + 5,
							move.getBoosts().indexOf(",", move.getBoosts().indexOf("atk")));
					int attackChangeNum = 0;
					System.out.println(attackChange);
					try {
						attackChangeNum = Integer.parseInt(attackChange);
					} catch (Exception e) {
						System.out.println("There was a problem trying to parse the attack change to an integer");
						e.printStackTrace();
					}
					if (attackChangeNum < 0) {
						boostsTarget.setAttackDecrease(Math.abs(attackChangeNum));
					} else if (attackChangeNum > 0) {
						boostsTarget.setAttackIncrease(attackChangeNum);
					} else {
						System.out.println("Something went wrong while setting the attack stat changes");
					}
				}
				if (move.getBoosts().contains("def")) {
					String defenseChange = move.getBoosts().substring(move.getBoosts().indexOf("def") + 5,
							move.getBoosts().indexOf(",", move.getBoosts().indexOf("def")));
					int defenseChangeNum = 0;
					try {
						defenseChangeNum = Integer.parseInt(defenseChange);
					} catch (Exception e) {
						System.out.println("There was a problem trying to parse the defense change to an integer");
						e.printStackTrace();
					}
					if (defenseChangeNum < 0) {
						boostsTarget.setDefenseDecrease(Math.abs(defenseChangeNum));
					} else if (defenseChangeNum > 0) {
						boostsTarget.setDefenseIncrease(defenseChangeNum);
					} else {
						System.out.println("Something went wrong while setting the defense stat changes");
					}
				}
				if (move.getBoosts().contains("spe")) {
					String speedChange = move.getBoosts().substring(move.getBoosts().indexOf("spe") + 5,
							move.getBoosts().indexOf(",", move.getBoosts().indexOf("spe")));
					int speedChangeNum = 0;
					try {
						speedChangeNum = Integer.parseInt(speedChange);
					} catch (Exception e) {
						System.out.println("There was a problem trying to parse the speed change to an integer");
						e.printStackTrace();
					}
					if (speedChangeNum < 0) {
						boostsTarget.setSpeedDecrease(Math.abs(speedChangeNum));
					} else if (speedChangeNum > 0) {
						boostsTarget.setSpeedIncrease(speedChangeNum);
					} else {
						System.out.println("Something went wrong while setting the speed stat changes");
					}
				}
				if (move.getBoosts().contains("spd")) {

					String specialDefenseChange = move.getBoosts().substring(move.getBoosts().indexOf("spd") + 5,
							move.getBoosts().indexOf(",", move.getBoosts().indexOf("spd")));
					int specialDefenseChangeNum = 0;
					try {
						specialDefenseChangeNum = Integer.parseInt(specialDefenseChange);
					} catch (Exception e) {
						System.out.println("There was a problem trying to parse the specialDefense change to an integer");
						e.printStackTrace();
					}
					if (specialDefenseChangeNum < 0) {
						boostsTarget.setSpecialDefenseDecrease(Math.abs(specialDefenseChangeNum));
					} else if (specialDefenseChangeNum > 0) {
						boostsTarget.setSpecialDefenseIncrease(specialDefenseChangeNum);
					} else {
						System.out.println("Something went wrong while setting the specialDefense stat changes");
					}
				}

				if (move.getBoosts().contains("spa")) {

					String specialAttackChange = move.getBoosts().substring(move.getBoosts().indexOf("spa") + 5,
							move.getBoosts().indexOf(",", move.getBoosts().indexOf("spa")));
					int specialAttackChangeNum = 0;
					try {
						specialAttackChangeNum = Integer.parseInt(specialAttackChange);
					} catch (Exception e) {
						System.out.println("There was a problem trying to parse the specialAttack change to an integer");
						e.printStackTrace();
					}
					if (specialAttackChangeNum < 0) {
						boostsTarget.setSpecialAttackDecrease(Math.abs(specialAttackChangeNum));
					} else if (specialAttackChangeNum > 0) {
						boostsTarget.setSpecialAttackIncrease(specialAttackChangeNum);
					} else {
						System.out.println("Something went wrong while setting the specialAttack stat changes");
					}
				}
				if (move.getStatus().equalsIgnoreCase("par") && (target.getType1().equalsIgnoreCase("Electric")
						|| target.getType2().equalsIgnoreCase("Electric"))) {

				} else if (move.getStatus().equalsIgnoreCase("brn")
						&& (target.getType1().equalsIgnoreCase("Fire") || target.getType2().equalsIgnoreCase("Fire"))) {

				} else if (move.getStatus().equalsIgnoreCase("frz")
						&& (target.getType1().equalsIgnoreCase("Ice") || target.getType2().equalsIgnoreCase("Ice"))) {

				} else if (move.getStatus().equalsIgnoreCase("psn") && (target.getType1().equalsIgnoreCase("Poison")
						|| target.getType2().equalsIgnoreCase("Poison"))) {

				} else if (!(move.getStatus().equalsIgnoreCase("null"))) {
					if (Math.random() <= (double) move.getChance() / 100 && (boostsTarget.getStatus().equalsIgnoreCase("")
							|| boostsTarget.getStatus().equalsIgnoreCase("null"))) {
						boostsTarget.setStatus(move.getStatus());
					}
				}

			}
			return 0;
		}
		if (move.isSelf()) {
			boostsTarget = user;
		} else {
			boostsTarget = target;
		}

		if (move.getStatus().equalsIgnoreCase("par")
				&& (target.getType1().equalsIgnoreCase("Electric") || target.getType2().equalsIgnoreCase("Electric"))) {

		} else if (move.getStatus().equalsIgnoreCase("brn")
				&& (target.getType1().equalsIgnoreCase("Fire") || target.getType2().equalsIgnoreCase("Fire"))) {

		} else if (move.getStatus().equalsIgnoreCase("frz")
				&& (target.getType1().equalsIgnoreCase("Ice") || target.getType2().equalsIgnoreCase("Ice"))) {

		} else if (move.getStatus().equalsIgnoreCase("psn")
				&& (target.getType1().equalsIgnoreCase("Poison") || target.getType2().equalsIgnoreCase("Poison"))) {

		} else if (!(move.getStatus().equalsIgnoreCase("null"))) {
			if (Math.random() <= (double) move.getChance() / 100
					&& (boostsTarget.getStatus().equalsIgnoreCase("") || boostsTarget.getStatus().equalsIgnoreCase("null"))) {
				boostsTarget.setStatus(move.getStatus());
			}
		}
		if (!(move.getBoosts().equalsIgnoreCase("null"))) {
			if (move.getBoosts().contains("atk")) {
				String attackChange = move.getBoosts().substring(move.getBoosts().indexOf("atk") + 5,
						move.getBoosts().indexOf(",", move.getBoosts().indexOf("atk")));
				int attackChangeNum = 0;
				try {
					attackChangeNum = Integer.parseInt(attackChange);
				} catch (Exception e) {
					System.out.println("There was a problem trying to parse the attack change to an integer");
					e.printStackTrace();
				}
				if (attackChangeNum < 0) {
					boostsTarget.setAttackDecrease(Math.abs(attackChangeNum));
				} else if (attackChangeNum > 0) {
					boostsTarget.setAttackIncrease(attackChangeNum);
				} else {
					System.out.println("Something went wrong while setting the attack stat changes");
				}
			}
			if (move.getBoosts().contains("def")) {
				String defenseChange = move.getBoosts().substring(move.getBoosts().indexOf("def") + 5,
						move.getBoosts().indexOf(",", move.getBoosts().indexOf("def")));
				int defenseChangeNum = 0;
				try {
					defenseChangeNum = Integer.parseInt(defenseChange);
				} catch (Exception e) {
					System.out.println("There was a problem trying to parse the defense change to an integer");
					e.printStackTrace();
				}
				if (defenseChangeNum < 0) {
					boostsTarget.setDefenseDecrease(Math.abs(defenseChangeNum));
				} else if (defenseChangeNum > 0) {
					boostsTarget.setDefenseIncrease(defenseChangeNum);
				} else {
					System.out.println("Something went wrong while setting the defense stat changes");
				}
			}
			if (move.getBoosts().contains("spe")) {
				String speedChange = move.getBoosts().substring(move.getBoosts().indexOf("spe") + 5,
						move.getBoosts().indexOf(",", move.getBoosts().indexOf("spe")));
				int speedChangeNum = 0;
				try {
					speedChangeNum = Integer.parseInt(speedChange);
				} catch (Exception e) {
					System.out.println("There was a problem trying to parse the speed change to an integer");
					e.printStackTrace();
				}
				if (speedChangeNum < 0) {
					boostsTarget.setSpeedDecrease(Math.abs(speedChangeNum));
				} else if (speedChangeNum > 0) {
					boostsTarget.setSpeedIncrease(speedChangeNum);
				} else {
					System.out.println("Something went wrong while setting the speed stat changes");
				}
			}
			if (move.getBoosts().contains("spd")) {

				String specialDefenseChange = move.getBoosts().substring(move.getBoosts().indexOf("spd") + 5,
						move.getBoosts().indexOf(",", move.getBoosts().indexOf("spd")));
				int specialDefenseChangeNum = 0;
				try {
					specialDefenseChangeNum = Integer.parseInt(specialDefenseChange);
				} catch (Exception e) {
					System.out.println("There was a problem trying to parse the specialDefense change to an integer");
					e.printStackTrace();
				}
				if (specialDefenseChangeNum < 0) {
					boostsTarget.setSpecialDefenseDecrease(Math.abs(specialDefenseChangeNum));
				} else if (specialDefenseChangeNum > 0) {
					boostsTarget.setSpecialDefenseIncrease(specialDefenseChangeNum);
				} else {
					System.out.println("Something went wrong while setting the specialDefense stat changes");
				}
			}

			if (move.getBoosts().contains("spa")) {

				String specialAttackChange = move.getBoosts().substring(move.getBoosts().indexOf("spa") + 5,
						move.getBoosts().indexOf(",", move.getBoosts().indexOf("spa")));
				int specialAttackChangeNum = 0;
				try {
					specialAttackChangeNum = Integer.parseInt(specialAttackChange);
				} catch (Exception e) {
					System.out.println("There was a problem trying to parse the specialAttack change to an integer");
					e.printStackTrace();
				}
				if (specialAttackChangeNum < 0) {
					boostsTarget.setSpecialAttackDecrease(Math.abs(specialAttackChangeNum));
				} else if (specialAttackChangeNum > 0) {
					boostsTarget.setSpecialAttackIncrease(specialAttackChangeNum);
				} else {
					System.out.println("Something went wrong while setting the specialAttack stat changes");
				}
			}

		}

		damage = (int) (((((((200 / 5) + 2) * power * attack) / defense) / 50) + 2)
				* typeModifier(moveType, defenseType1, defenseType2)
				* ThreadLocalRandom.current().nextDouble(.85, 1.01));

		if (userType1 == moveType || userType2 == moveType) {
			damage = (int) (damage * 1.5);
		}

		if (Math.random() < 0.083 && move.getCritical() == 0) {
			damage *= 1.5;
			if (user.getStatus().equalsIgnoreCase("brn")) {
				damage *= 2;
			}
			System.out.println(user.getName() + " scored a critical hit!");

		} else if (Math.random() < 0.25 && move.getCritical() == 1) {
			damage *= 1.5;
			if (user.getStatus().equalsIgnoreCase("brn")) {
				damage *= 2;
			}
			System.out.println(user.getName() + " scored a critical hit!");

		} else if (Math.random() <= 1 && move.getCritical() == 2) {// only because we assume they have max affection
			damage *= 1.5;
			if (user.getStatus().equalsIgnoreCase("brn")) {
				damage *= 2;
			}

			System.out.println(user.getName() + " scored a critical hit!");
		} else if (move.getCritical() >= 3) {
			damage *= 1.5;
			if (user.getStatus().equalsIgnoreCase("brn")) {
				damage *= 2;
			}
			System.out.println(user.getName() + " scored a critical hit!");

		}
		return (int) (damage / 4.61538461538 * 3);
	}

	public int calculateWhoGoesFirst(Player p1, Player p2, Move p1Move, Move p2Move) {
		int p1Speed = p1.currentMon.getSpeed();
		int p2Speed = p2.currentMon.getSpeed();
		int p1Priority = p1Move.getPriority();
		int p2Priority = p2Move.getPriority();
		if (p1.currentMon.getStatus().equals("para")) {
			p1Speed /= 2;
		}
		if (p2.currentMon.getStatus().equals("para")) {
			p2Speed /= 2;
		}
		if (p1Priority > p2Priority) {
			return 1;
		} else if (p2Priority > p1Priority) {
			return 2;
		}
		if (p1Speed > p2Speed) {
			return 1;
		} else if (p2Speed > p1Speed) {
			return 2;
		} else {
			if (Math.random() >= 0.5) {
				return 1;
			} else {
				return 2;
			}
		}

	}

	public String howEffective(int moveTypeP, int defendType1P, int defendType2P) {
		double x = typeModifier(moveTypeP, defendType1P, defendType2P);

		if (x == 0) {
			return "It had no effect...";
		} else if (x == 2.0) {
			return "It's super effective!";
		} else if (x == 4.0) {
			return "It's super duper effective!!";
		} else if (x == 0.5) {
			return "It's not very effective...";
		} else if (x == 0.25) {
			return "It had very little effect...";
		} else {
			return "";
		}
	}
//    public int calculateStatus (Pokemon user, Pokemon target, int movNumber){
//        //code way to calculate status
//    }

}
