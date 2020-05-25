//
//  Author: Kumar Chandra
//  Revised: Kumar Chandra
//           5/10/20
//
//  Notes:
//       The way this class works is that it has 6 pokemon,
//       and a currentMon, which is the pokemon it currently has in place
//       It can also fight, or switch out, where it changes its currentMon.
//       It also has access to the opposing player, to attack them
//
//  Bugs:
//      unknown
//
public class Player {
	private Battle battle;
	private EaseOfUse ez = new EaseOfUse();
    private Calculator calculator;//self explanatory
    private Pokemon[] pokemon;//has 6 pokemon
    private Pokemon currentMon;// your current pokemon that is in play
    private Player opposingPlayer;// the opponent
    public Player(Battle battle, Calculator calc, Pokemon[] givenMons, Player other){//sets pokemon and opponent
        this.battle = battle;
    	calculator = calc;
    	pokemon=givenMons;
        opposingPlayer=other;
        currentMon=givenMons[0];
    }
    public Player(Battle battle, Calculator calc, Pokemon[] givenMons, int[][] givenMoves, Player other){//sets pokemon and moves
    	this.battle = battle;
    	calculator = calc;
    	pokemon=givenMons;
        Move[] hi=new Move[4];
        for (int i=0;i<6;i++){
            for(int k=0;k<4;k++){
                pokemon[i].getMoves()[k]=new Move(givenMoves[i][k]);

            }



        }
        opposingPlayer=other;
        currentMon=givenMons[0];
    }
    public Player(Battle battle, Calculator calc, Pokemon[] givenMons, int[][] givenMoves){//sets mons and moves but not opponent
        this.battle = battle;
    	calculator = calc;
    	pokemon=givenMons;
        for (int i=0;i<givenMons.length;i++){

                pokemon[i].setMoves(givenMoves[i]);

        }

        currentMon=givenMons[0];
    }
    public Player(Battle battle, Calculator calc, Pokemon[] givenMons){//just sets mons
        this.battle = battle;
    	calculator = calc;
    	pokemon=givenMons;
        currentMon=givenMons[0];

    }
    public Player(Battle battle, Calculator calc, int[] givenMons,int[][] givenMoves,Player other) {//sets mons and moves, but as an int array that it converts into pokemon
        this.battle = battle;
    	calculator = calc;
    	for (int i = 0; i < givenMons.length; i++) {
            pokemon[i] = new Pokemon(givenMons[i]);
        }
        for (int i = 0; i < givenMons.length; i++) {

            pokemon[i].setMoves(givenMoves[i]);

        }
        opposingPlayer = other;
        currentMon = pokemon[0];
    }
    public boolean isDefeated(){//self explanatory
        if(pokemon[0].getHealth()<=0&&pokemon[1].getHealth()<=0&&pokemon[2].getHealth()<=0&&
                pokemon[3].getHealth()<=0&&pokemon[4].getHealth()<=0&&pokemon[5].getHealth()<=0){
            return true;}

        else{
            return false;
        }
    }
    public void setCurrentMon(){//self explanatory
        currentMon=pokemon[0];
    }
    public void setOpposingPlayer(Player other){//self explanatory
        opposingPlayer=other;
        other.opposingPlayer = this;
    }
    public void fight(int indexOfMove){
    	if(currentMon.getHealth()<=0) {
        	return;
        }
    	if(currentMon.getMoves()[indexOfMove].getPP() == 0) {
    		// System.out.println("There's no PP left for this move!");
    		return;
    	}
    	else {
    		currentMon.getMoves()[indexOfMove].usePP();
    	}
        if(currentMon.getStatus().equalsIgnoreCase("brn"))
        {
            int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
            int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
            int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
            int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);


            int damageDone = 0;
            Ratio multiHit = currentMon.getMoves()[indexOfMove].getMultiHit();
            if(multiHit.getSecond() > 1) {
            	int totalDamageDone = 0;
            	int numHits = (int)(multiHit.getFirst()+Math.random()*(multiHit.getSecond()-multiHit.getFirst()+1));
	            for(int i = 0; i < numHits; i++) {
	            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
	            	totalDamageDone += damageDone;
	                battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
	            }
	            battle.log("A total of " + totalDamageDone + " damage was dealt!");
	            damageDone = totalDamageDone;
            }
            else {
            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
            	battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
            }
            double drain=currentMon.getMoves()[indexOfMove].getDrain();
            int damageAbsorbed = currentMon.heal((int)(damageDone*drain));
            if(drain > 0) {
                battle.log(currentMon.getName()+" absorbed "+damageAbsorbed+" health from "+opposingPlayer.currentMon.getName());
            }
            double recoil=currentMon.getMoves()[indexOfMove].getRecoil();
            int recoilDamage = currentMon.takeDamage((int)(damageDone*recoil));
            if(recoil > 0) {
                battle.log(currentMon.getName()+" took " + recoilDamage + " as recoil.");
            }
            String howEffective = calculator.howEffective(moveType, opposingType1, opposingType2);
            if(!howEffective.isEmpty()) {
                battle.log(howEffective);
            }
            currentMon.takeDamage((int)(currentMon.getBaseHealth() * 0.06));
            battle.log(currentMon.getName() + " was hurt from its burn");
        }
        else if(currentMon.getStatus().equalsIgnoreCase("par"))
        {
        	battle.log(currentMon.getName() + " has paralysis");
            if(Math.random() < 0.25) {
            	battle.log(currentMon.getName() + " was fully Paralyzed");
            }
            else
            {
                int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
                int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
                int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
                int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);

                int damageDone = 0;
                Ratio multiHit = currentMon.getMoves()[indexOfMove].getMultiHit();
                if(multiHit.getSecond() > 1) {
                	int totalDamageDone = 0;
                	int numHits = (int)(multiHit.getFirst()+Math.random()*(multiHit.getSecond()-multiHit.getFirst()+1));
    	            for(int i = 0; i < numHits; i++) {
    	            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
    	            	totalDamageDone += damageDone;
    	                battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
    	            }
    	            battle.log("A total of " + totalDamageDone + " damage was dealt!");
    	            damageDone = totalDamageDone;
                }
                else {
                	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
                	battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
                }
                double drain=currentMon.getMoves()[indexOfMove].getDrain();
                int damageAbsorbed = currentMon.heal((int)(damageDone*drain));
                if(drain > 0) {
                    battle.log(currentMon.getName()+" absorbed "+damageAbsorbed+" health from "+opposingPlayer.currentMon.getName());
                }
                double recoil=currentMon.getMoves()[indexOfMove].getRecoil();
                int recoilDamage = currentMon.takeDamage((int)(damageDone*recoil));
                if(recoil > 0) {
                    battle.log(currentMon.getName()+" took " + recoilDamage + " as recoil.");
                }
                String howEffective = calculator.howEffective(moveType, opposingType1, opposingType2);
                if(!howEffective.isEmpty()) {
                    battle.log(howEffective);
                }


            }
        }
        else if(currentMon.getStatus().equalsIgnoreCase("frz"))
        {
            if(Math.random() < 0.2)
            {
                battle.log(currentMon.getName()+" thawed out!");
                currentMon.setStatus("");
                int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
                int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
                int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
                int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);

                int damageDone = 0;
                Ratio multiHit = currentMon.getMoves()[indexOfMove].getMultiHit();
                if(multiHit.getSecond() > 1) {
                	int totalDamageDone = 0;
                	int numHits = (int)(multiHit.getFirst()+Math.random()*(multiHit.getSecond()-multiHit.getFirst()+1));
    	            for(int i = 0; i < numHits; i++) {
    	            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
    	            	totalDamageDone += damageDone;
    	                battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
    	            }
    	            battle.log("A total of " + totalDamageDone + " damage was dealt!");
    	            damageDone = totalDamageDone;
                }
                else {
                	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
                	battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
                }
                double drain=currentMon.getMoves()[indexOfMove].getDrain();
                int damageAbsorbed = currentMon.heal((int)(damageDone*drain));
                if(drain > 0) {
                    battle.log(currentMon.getName()+" absorbed "+damageAbsorbed+" health from "+opposingPlayer.currentMon.getName());
                }
                double recoil=currentMon.getMoves()[indexOfMove].getRecoil();
                int recoilDamage = currentMon.takeDamage((int)(damageDone*recoil));
                if(recoil > 0) {
                    battle.log(currentMon.getName()+" took " + recoilDamage + " as recoil.");
                }
                String howEffective = calculator.howEffective(moveType, opposingType1, opposingType2);
                if(!howEffective.isEmpty()) {
                    battle.log(howEffective);
                }

            }
            else
            {
                battle.log(currentMon.getName() + " was Frozen Solid");
            }
        }
        else if(currentMon.getStatus().equalsIgnoreCase("psn"))
        {
            int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
            int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
            int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
            int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);
//            battle.log(currentMon.getName() + " did " + damage + " damage to " + opposingPlayer.currentMon.getName());
            int damageDone = 0;
            Ratio multiHit = currentMon.getMoves()[indexOfMove].getMultiHit();
            if(multiHit.getSecond() > 1) {
            	int totalDamageDone = 0;
            	int numHits = (int)(multiHit.getFirst()+Math.random()*(multiHit.getSecond()-multiHit.getFirst()+1));
	            for(int i = 0; i < numHits; i++) {
	            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
	            	totalDamageDone += damageDone;
	                battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
	            }
	            battle.log("A total of " + totalDamageDone + " damage was dealt!");
	            damageDone = totalDamageDone;
            }
            else {
            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
            	battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
            }
            double drain=currentMon.getMoves()[indexOfMove].getDrain();
            int damageAbsorbed = currentMon.heal((int)(damageDone*drain));
            if(drain > 0) {
                battle.log(currentMon.getName()+" absorbed "+damageAbsorbed+" health from "+opposingPlayer.currentMon.getName());
            }
            String howEffective = calculator.howEffective(moveType, opposingType1, opposingType2);
            if(!howEffective.isEmpty()) {
                battle.log(howEffective);
            }
            double recoil=currentMon.getMoves()[indexOfMove].getRecoil();
            int recoilDamage = currentMon.takeDamage((int)(damageDone*recoil));
            if(recoil > 0) {
                battle.log(currentMon.getName()+" took " + recoilDamage + " as recoil.");
            }
            currentMon.takeDamage((int)(currentMon.getBaseHealth() * 1.0/8));
            battle.log(currentMon.getName() + " was hurt from the poison");
        }
        else if(currentMon.getStatus().equalsIgnoreCase("slp"))
        {
        	battle.log(currentMon.getName() + " is fast asleep");
            if(Math.random() < 1.0/3)
            {
                currentMon.setStatus("");
                battle.log(currentMon.getName()+" started to wake up");
            }
        }
        else {

            int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
            int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
            int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
            int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);
            int damageDone = 0;
            Ratio multiHit = currentMon.getMoves()[indexOfMove].getMultiHit();
            if(multiHit.getSecond() > 1) {
            	int totalDamageDone = 0;
            	int numHits = (int)(multiHit.getFirst()+Math.random()*(multiHit.getSecond()-multiHit.getFirst()+1));
	            for(int i = 0; i < numHits; i++) {
	            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
	            	totalDamageDone += damageDone;
	                battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
	            }
	            battle.log("A total of " + totalDamageDone + " damage was dealt!");
	            damageDone = totalDamageDone;
            }
            else {
            	damageDone=opposingPlayer.currentMon.takeDamage(damage/2);
            	battle.log(currentMon.getName() + " did " + damageDone + " damage to " + opposingPlayer.currentMon.getName());
            }
            double drain=currentMon.getMoves()[indexOfMove].getDrain();
            int damageAbsorbed = currentMon.heal((int)(damageDone*drain));
            if(drain > 0) {
                battle.log(currentMon.getName()+" absorbed "+damageAbsorbed+" health from "+opposingPlayer.currentMon.getName());
            }
            double recoil=currentMon.getMoves()[indexOfMove].getRecoil();
            int recoilDamage = currentMon.takeDamage((int)(damageDone*recoil));
            if(recoil > 0) {
                battle.log(currentMon.getName()+" took " + recoilDamage + " as recoil.");
            }
            String howEffective = calculator.howEffective(moveType, opposingType1, opposingType2);
            if(!howEffective.isEmpty()) {
                battle.log(howEffective);
            }
        }
    }

    public void switchOut(Pokemon switchIn){//self explanatory
       if(switchIn.getHealth()>0) {
    	   battle.log(currentMon.getName() + " was switched out for " + switchIn.getName());
           currentMon = switchIn;
       }
    }
    
    public void switchOut(int switchIn) {//self explanatory, but it turns the int into a number, where the int is the
        // index of the pokemon in the pokemon array
    	if(pokemon[switchIn].getHealth() > 0 && pokemon[switchIn] != currentMon) {
    		currentMon = pokemon[switchIn];
    	}
    }
    
    public int indexOfMon(Pokemon mon){//finds the index of a pokemon in your array of pokemon
        int answer=-1;
        for(int i=0;i<pokemon.length;i++)
        {
            if (pokemon[i]==mon){
                answer=i;
                break;
            }
        }
        return answer;
    }

    public Pokemon[] getPokemon() {//self explanatory
    	return pokemon;
    }
    
    public Pokemon getCurrentMon() {//self explanatory
    	return currentMon;
    }
    

    public String toString() {//self explanatory
        String answer="Player)\n" +
                "pokemon: " +pokemon[0].getName()+" " +pokemon[1].getName()+" "+pokemon[2].getName()+" " +pokemon[3].getName()+" " +pokemon[4].getName()+" "+ pokemon[5].getName()+

                "\ncurrentMon=" + currentMon.getName()


                ;
        return answer;
    }
}
