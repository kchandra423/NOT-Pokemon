
import java.util.Scanner;

public class Player {
    private Pokemon[] pokemon;
    private EaseOfUse ez = new EaseOfUse();
    private Calculator calculator= new Calculator();
    private Pokemon currentMon;
    private Player opposingPlayer;
    private Scanner kboard = new Scanner(System.in);
    public Player(Pokemon[] givenMons, Player other){
        pokemon=givenMons;
        opposingPlayer=other;
        currentMon=givenMons[0];
    }
    public Player(Pokemon[] givenMons, int[][] givenMoves, Player other){
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
    public Player(Pokemon[] givenMons, int[][] givenMoves){
        pokemon=givenMons;
        for (int i=0;i<givenMons.length;i++){
//            for(int k=0;k<4;i++){
                pokemon[i].setMoves(givenMoves[i]);
//            }
        }

        currentMon=givenMons[0];
    }
    public Player(Pokemon[] givenMons){
        pokemon=givenMons;
        currentMon=givenMons[0];

    }
    public Player(int[] givenMons,int[][] givenMoves,Player other){
        for (int i=0;i<givenMons.length;i++){
            pokemon[i]=new Pokemon(givenMons[i]);
        }
        for (int i=0;i<givenMons.length;i++){
//            for(int k=0;k<pokemon[i].getMoves().length;i++){
                pokemon[i].setMoves(givenMoves[i]);
//            }
        }
        opposingPlayer=other;
        currentMon=pokemon[0];
    }
//    public Player(Int[] givenMons){
//        pokemon=givenMons;
//  }
    public boolean isDefeated(){
        if(pokemon[0].getHealth()<=0&&pokemon[1].getHealth()<=0&&pokemon[2].getHealth()<=0&&
                pokemon[3].getHealth()<=0&&pokemon[4].getHealth()<=0&&pokemon[5].getHealth()<=0){
            return true;}

        else{
            return false;
        }
    }
    public void setCurrentMon(){
        currentMon=pokemon[0];
    }
    public void setOpposingPlayer(Player other){
        opposingPlayer=other;
        other.opposingPlayer = this;
    }
    public void fight(int indexOfMove){
    	if(currentMon.getMoves()[indexOfMove].getPP() == 0) {

    	}
    	else {
    		currentMon.getMoves()[indexOfMove].usePP();
    		// System.out.println("There's no PP left for this move!");
    	}
        if(currentMon.getHealth()<=0) {
        }
        else if(currentMon.getStatus().equalsIgnoreCase("brn"))
        {
            int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
            int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
            int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
            int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);
            ez.print(currentMon.getName() + " did " + damage /2 + " damage to " + opposingPlayer.currentMon.getName());
            System.out.println(calculator.howEffective(moveType, opposingType1, opposingType2));
            opposingPlayer.currentMon.takeDamage(damage/2);
            currentMon.takeDamage((int)(currentMon.getBaseHealth() * 0.06));
            System.out.println(currentMon.getName() + " was hurt from its burn");
        }
        else if(currentMon.getStatus().equalsIgnoreCase("par"))
        {
            System.out.println(currentMon.getName() + " has paralysis");
            if(Math.random() < 0.25) {
                System.out.println(currentMon.getName() + " was fully Paralyzed");
            }
            else
            {
                int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
                int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
                int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
                int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);
                ez.print(currentMon.getName() + " did " + damage + " damage to " + opposingPlayer.currentMon.getName());
                System.out.println(calculator.howEffective(moveType, opposingType1, opposingType2));
                opposingPlayer.currentMon.takeDamage(damage);
            }
        }
        else if(currentMon.getStatus().equalsIgnoreCase("frz"))
        {
            if(Math.random() < 0.2)
            {
                currentMon.setStatus("");
                int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
                int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
                int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
                int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);
                ez.print(currentMon.getName() + " did " + damage + " damage to " + opposingPlayer.currentMon.getName());
                System.out.println(calculator.howEffective(moveType, opposingType1, opposingType2));
                opposingPlayer.currentMon.takeDamage(damage);
            }
            else
            {
                System.out.println(currentMon.getName() + " was Frozen Solid");
            }
        }
        else if(currentMon.getStatus().equalsIgnoreCase("psn"))
        {
            int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
            int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
            int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
            int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);
            ez.print(currentMon.getName() + " did " + damage + " damage to " + opposingPlayer.currentMon.getName());
            System.out.println(calculator.howEffective(moveType, opposingType1, opposingType2));
            opposingPlayer.currentMon.takeDamage(damage);
            currentMon.takeDamage((int)(currentMon.getBaseHealth() * 1.0/8));
            System.out.println(currentMon.getName() + " was hurt from the poison");
        }
        else if(currentMon.getStatus().equalsIgnoreCase("slp"))
        {
            System.out.println(currentMon.getName() + " is fast asleep");
            if(Math.random() < 1.0/3)
            {
                currentMon.setStatus("");
            }
        }
        else {
            int moveType = calculator.getIntFromType(currentMon.getMoves()[indexOfMove].getType());
            int opposingType1 = calculator.getIntFromType(opposingPlayer.currentMon.getType1());
            int opposingType2 = calculator.getIntFromType(opposingPlayer.currentMon.getType2());
            int damage = calculator.calculateBasicDamage(currentMon, opposingPlayer.currentMon, currentMon.getMoves()[indexOfMove]);
            ez.print(currentMon.getName() + " did " + damage + " damage to " + opposingPlayer.currentMon.getName());
            System.out.println(calculator.howEffective(moveType, opposingType1, opposingType2));
            opposingPlayer.currentMon.takeDamage(damage);
        }
    }

    public void switchOut(Pokemon switchIn){
//        System.out.println("Choose which pokemon you want to switch in");
//        int y = indexOfMon(currentMon);
//        int x=y;
//
//
//        boolean z= true;
//        while(z) {
//            for (int i = 0; i < 6; i++) {
//                if (i == y) {
//                    System.out.println((i + 1) + ")" + pokemon[i].getName() + " (Already in play)");
//                }
//                else if(pokemon[i].getHealth()<=0){
//                    System.out.println((i + 1) + ")" + pokemon[i].getName() + " (Fainted)");
//                }
//                else {
//                    System.out.println((i + 1) + ")" + pokemon[i].getName());
//                }
//            }
//             x = kboard.nextInt() - 1;
//            if (x==y||x<0||pokemon[x].getHealth()<=0){
//                ez.print("That wasn't a valid input");
//            }
//            else{
//                z=false;
//            }
//        }
        currentMon=switchIn;
    }
    
    public void switchOut(int switchIn) {
    	if(pokemon[switchIn].getHealth() > 0 && pokemon[switchIn] != currentMon) {
    		currentMon = pokemon[switchIn];
    	}
    }
    
    public int indexOfMon(Pokemon mon){
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

    public Pokemon[] getPokemon() {
    	return pokemon;
    }
    
    public Pokemon getCurrentMon() {
    	return currentMon;
    }
    
//    @Override
    public String toString() {
        String answer="Player)\n" +
                "pokemon: " +pokemon[0].getName()+" " +pokemon[1].getName()+" "+pokemon[2].getName()+" " +pokemon[3].getName()+" " +pokemon[4].getName()+" "+ pokemon[5].getName()+

                "\ncurrentMon=" + currentMon.getName()
//                ", opposingPlayer=" + opposingPlayer +

                ;
        return answer;
    }
}
