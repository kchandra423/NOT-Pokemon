//
//  Author: Kumar Chandra
//  Revised: Kumar Chandra
//           5/17/20
//
//  Notes:
//       Calculates things(obviously). Can calculate damage, if a String is a valid move, who goes first, etc
//
//  Bugs:
//      unknown
//
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

//Nathaniel Thomas wrote the info for the arrays, and i just used it because thers no point in writing an
// entire aray of things for this besides to waste my time.
// I literally would have learned nothing if i filled all that out manually, which he had already did


	//checks if a move is valid
	public boolean isMove(String moveName){
		boolean answer = false;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Stats.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Text/Moves.txt"));//you should know what this does by now
			// so im not going to keep on commenting what this does because we use the exact same try catch every time
			// we use a txt file
			copy = new String(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] lines = copy.split("\n");//split by line

		String currentLine = "";
		for (int i = 0; i < lines.length; i++) {//iterates throught the whole txt file
			currentLine = lines[i];
			try{String name=currentLine.split("#")[5];//split by # instead of using TSV (tab seperated values)or
				// CSV(colon seperated values), we used hashtags
				//because the text file we use has colons and tabs that we want to keep sometiems


			if (moveName.equalsIgnoreCase(name)){//self explanatory
				answer=true;
			}}
				catch(Exception e){

				}
		}
		return answer;
	}
	public int getIntFromType(String type) {//every type has its respective int,
		// so given the name of a type we can get its int
		int answer = -1;

		for (int i = 0; i < typings.length; i++) {
			if (type.equalsIgnoreCase(typings[i])) {//self explanatory
				answer = i;
				break;
			}

		}
		return answer;
	}

	public double typeModifier(int attackType, int defendType1, int defendType2) {
		//calculates the damage modifier for a type against two other types(Type 2 can be null)
		double answer = 0;
		if (defendType2 == -1) {

			answer = typingArray[attackType][defendType1];
			return answer;
		} else {
			answer = typingArray[attackType][defendType1] * typingArray[attackType][defendType2];
			return answer;
		}

	}

	public boolean hasMove(Pokemon mon, Move move) {//checks if a pokemon knows a certain move
		boolean answer = false;
		String copy = "something went wrong; ";
		try {
//			copy = Files.readString(Paths.get("Stats.txt"));
			byte[] file = Files.readAllBytes(Paths.get("Text/Learnsets.txt"));
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

	public int calculateBasicDamage(Pokemon user, Pokemon target, Move move) {//calculates damage


		//FAQ!
		//-target refers to whether everything about the move targets the user, something like swords dance

		//-self refers to whether any secondary effects target the user ,
		// but NOT THE DAMAGE examples would be outrage or ancient power

		//-category there are three categories of moves, special, physical, and status.
		// status moves are non damging(swords dance)
		// special moves use a pokemons special attack and special defense stats (drain punch)
		// phsyical moves uses a pokemons attack and defense stats (focus blast)

		//-boosts are any stat modifieres a move has, negative or positive

		//-status effects are negative debuffs your pokemon has that effects things about how they play

		//-drain is a ratio of how much health you heal for each point of damage you do
		//for example if drain is 0.5, you heal back half the damage you do

		//-heal is also a ratio, but isnt affected by how much damage you do, but rather by the targets base health
		// for example if heal is 0.5, the target is healed of half of their base health


		if (move.getAccuracy() == 101) {//some moves cannot miss, in which case they are given an accuracy of 101

		} else if (Math.random() > (double) move.getAccuracy() / 100) {//self explanatory
			System.out.println(target.getName() + " evaded the attack!");
			return 0;

		}
		if (move.isTarget()) {//self explanatory
			target = user;
		}
		int damage = 0;
		int attack, defense, power, moveType, defenseType1, defenseType2, userType1, userType2;
		String status,boosts,category;
		double drain, heal;
		boolean everythingTargetsSelf;//not really being used right now
		boolean damaging;
		Pokemon boostsTarget;
		category=move.getCategory();//self explanatory
		moveType = getIntFromType(move.getType());//self explanatory
		if(move.isSelf()){
			boostsTarget=user;

		}
		else{
			boostsTarget=target;
		}

		if (category.equalsIgnoreCase("Physical")) {
			attack = user.getAttack();
			defense = target.getDefense();
			power = move.getPower();
			defenseType1 = getIntFromType(target.getType1());
			defenseType2 = getIntFromType(target.getType2());
			userType1 = getIntFromType(user.getType1());
			userType2 = getIntFromType(user.getType2());

			status=move.getStatus();
			boosts=move.getBoosts();
			drain=move.getDrain();
			heal=move.getHeal();

			 damaging=true;

		} else if (category.equalsIgnoreCase("Special")) {
			attack = user.getSpecialAttack();
			defense = target.getSpecialDefense();
			power = move.getPower();
			defenseType1 = getIntFromType(target.getType1());
			defenseType2 = getIntFromType(target.getType2());
			userType1 = getIntFromType(user.getType1());
			userType2 = getIntFromType(user.getType2());

			status=move.getStatus();
			boosts=move.getBoosts();
			drain=move.getDrain();
			heal=move.getHeal();
			damaging=true;
		}
		else {
			attack = user.getAttack();
			defense = target.getDefense();
			power = move.getPower();
			defenseType1 = getIntFromType(target.getType1());
			defenseType2 = getIntFromType(target.getType2());
			userType1 = getIntFromType(user.getType1());
			userType2 = getIntFromType(user.getType2());
			drain=move.getDrain();
			heal=move.getHeal();
			status=move.getStatus();
			boosts=move.getBoosts();

			damaging=false;



		}


		if (status.equalsIgnoreCase("par")//paralyzsis cant be given to electric types and
				// lowers your speed by half and gives you a 0.25 chance to be unable to move
				&& (target.getType1().equalsIgnoreCase("Electric") || target.getType2().equalsIgnoreCase("Electric"))) {

		} else if (status.equalsIgnoreCase("brn")// burn can't be given to fire types
				// lowers your attack stat by half, and makes you take 0.6% of your health each turn
				&& (target.getType1().equalsIgnoreCase("Fire") || target.getType2().equalsIgnoreCase("Fire"))) {

		} else if (status.equalsIgnoreCase("frz")//freeze cant be given to ice types. makes you unable to move
				&& (target.getType1().equalsIgnoreCase("Ice") || target.getType2().equalsIgnoreCase("Ice"))) {

		} else if (status.equalsIgnoreCase("psn")//posion cant be givenn to poison types and makes
				// you take 12% of you health each turn
				&& (target.getType1().equalsIgnoreCase("Poison") || target.getType2().equalsIgnoreCase("Poison"))) {

		} else if (!(status.equalsIgnoreCase("null"))) {//self explanatory
			if(move.getCategory().equalsIgnoreCase("status")){
				if ((boostsTarget.getStatus().equalsIgnoreCase("") || boostsTarget.getStatus().equalsIgnoreCase("null"))) {
					boostsTarget.setStatus(move.getStatus());
				}
			}
			else if (Math.random() <= (double) move.getChance() / 100
					&& (boostsTarget.getStatus().equalsIgnoreCase("") || boostsTarget.getStatus().equalsIgnoreCase("null"))) {
				boostsTarget.setStatus(move.getStatus());
			}
		}
		if (!(boosts.equalsIgnoreCase("null"))) {//self explanatory
			if (boosts.contains("atk")) {
				String attackChange = boosts.substring(boosts.indexOf("atk") + 5,
						//add 5 because the boosts are given as(fox example) atk: 2,
						// so you want the 5th character after the a in atk and want to end it at the comma
						boosts.indexOf(",", boosts.indexOf("atk")));
				int attackChangeNum = 0;
				try {
					attackChangeNum = Integer.parseInt(attackChange);
				} catch (Exception e) {
					System.out.println("There was a problem trying to parse the attack change to an integer");
					e.printStackTrace();
				}
				if (attackChangeNum < 0) {//can be negative or positive
					boostsTarget.setAttackDecrease(Math.abs(attackChangeNum));
				} else if (attackChangeNum > 0) {
					boostsTarget.setAttackIncrease(attackChangeNum);
				} else {
					System.out.println("Something went wrong while setting the attack stat changes");
				}
			}
			if (boosts.contains("def")) {//same as atk
				String defenseChange = boosts.substring(boosts.indexOf("def") + 5,
						boosts.indexOf(",", boosts.indexOf("def")));
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
			if (boosts.contains("spe")) {//same as others
				String speedChange = boosts.substring(boosts.indexOf("spe") + 5,
						boosts.indexOf(",", boosts.indexOf("spe")));
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
			if (boosts.contains("spd")) {//same as others you get the point

				String specialDefenseChange = boosts.substring(boosts.indexOf("spd") + 5,
						boosts.indexOf(",", boosts.indexOf("spd")));
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

			if (boosts.contains("spa")) {

				String specialAttackChange = boosts.substring(boosts.indexOf("spa") + 5,
						boosts.indexOf(",", boosts.indexOf("spa")));
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
//the formula for damage is given as floor
// (((2 * Level + 10) / 250) * (Attack[or special attack] / Defense[or special defense]) * Boosts + 2)
		//we simplified it for the moost part and just added a magic constant at the end
		//also since we dont have level's implemented, we just use the smogon level standard, which is 50
		damage = (int) ((((((2*100/5+2)*power*attack/defense))/50)+2)
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
			//(affection is another variable were not going to implement)
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
		user.heal((int)(damage*drain));
		System.out.println(user.getName()+" absorbed "+(int)(damage*drain)+" health from "+target.getName());
		target.heal((int)(target.getBaseHealth()*heal));
		System.out.println(target.getName()+" healed "+(int)(target.getBaseHealth()*heal)+" health.");
		if(damaging){
		return  (damage);//magic constants !
		}else{
			return 0;
		}


	}

	public int calculateWhoGoesFirst(Player p1, Player p2, Move p1Move, Move p2Move) {//calcultate who goes first between two moves
		int p1Speed = p1.getCurrentMon().getSpeed();
		int p2Speed = p2.getCurrentMon().getSpeed();
		//moves have priority(can be 0 or negative) if your move has higher priority,
		// it will always go first, even if you are faster
		int p1Priority = p1Move.getPriority();
		int p2Priority = p2Move.getPriority();
		if (p1.getCurrentMon().getStatus().equals("par")) {//paralysis reduces speed by 0.5
			p1Speed /= 2;
		}
		if (p2.getCurrentMon().getStatus().equals("par")) {
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

	public String howEffective(int moveTypeP, int defendType1P, int defendType2P) {//self explanatory
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


}
