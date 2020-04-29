
import java.util.Scanner;

public class Player {
    BasicPokemon[] pokemon;
    EaseOfUse ez = new EaseOfUse();
    Calculator calculator= new Calculator();
    BasicPokemon currentMon;
    Player opposingPlayer;
    Scanner kboard = new Scanner(System.in);
    public Player(BasicPokemon[] givenMons,Player other){
        pokemon=givenMons;
        opposingPlayer=other;
        currentMon=givenMons[0];
    }
    public Player(BasicPokemon[] givenMons,int[][] givenMoves,Player other){
        pokemon=givenMons;
        BasicMove[] hi=new BasicMove[4];
        for (int i=0;i<6;i++){
            for(int k=0;k<4;k++){
                pokemon[i].moves[k]=new BasicMove(givenMoves[i][k]);

            }



        }
        opposingPlayer=other;
        currentMon=givenMons[0];
    }
    public Player(BasicPokemon[] givenMons,int[][] givenMoves){
        pokemon=givenMons;
        for (int i=0;i<givenMons.length;i++){
//            for(int k=0;k<4;i++){
                pokemon[i].setMoves(givenMoves[i]);
//            }
        }

        currentMon=givenMons[0];
    }
    public Player(BasicPokemon[] givenMons){
        pokemon=givenMons;
        currentMon=givenMons[0];

    }
    public Player(int[] givenMons,int[][] givenMoves,Player other){
        for (int i=0;i<givenMons.length;i++){
            pokemon[i]=new BasicPokemon(givenMons[i]);
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
    public void setOpposingPlayer(Player other){
        opposingPlayer=other;
    }
    public void fight(int indexOfMove){
        if(currentMon.getHealth()<=0){}
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

    public void switchOut(){
        System.out.println("Choose which pokemon you want to switch in");
        int y = indexOfMon(currentMon);
        int x=y;


        boolean z= true;
        while(z) {
            for (int i = 0; i < 6; i++) {
                if (i == y) {
                    System.out.println((i + 1) + ")" + pokemon[i].getName() + " (Already in play)");
                }
                else if(pokemon[i].getHealth()<=0){
                    System.out.println((i + 1) + ")" + pokemon[i].getName() + " (Fainted)");
                }
                else {
                    System.out.println((i + 1) + ")" + pokemon[i].getName());
                }
            }
             x = kboard.nextInt() - 1;
            if (x==y||x<0||pokemon[x].getHealth()<=0){
                ez.print("That wasn't a valid input");
            }
            else{
                z=false;
            }
        }
        currentMon=pokemon[x];
    }
    public int indexOfMon(BasicPokemon mon){
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
