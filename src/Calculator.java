import java.util.concurrent.ThreadLocalRandom;

public class Calculator {
    String typings[] = {"Normal", "Fighting", "Flying", "Poison", "Ground", "Rock", "Bug", "Ghost",
            "Steel", "Fire", "Water", "Grass", "Electric", "Psychic", "Ice", "Dragon", "Dark", "Fairy"};

    double normal[] = {1, 1, 1, 1, 1, .5, 1, 0, .5, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    double fighting[] = {2, 1, .5, .5, 1, 2, .5, 0, 2, 1, 1, 1, 1, .5, 2, 1, 2, .5};
    double flying[] = {1, 2, 1, 1, 1, .5, 2, 1, .5, 1, 1, 2, .5, 1, 1, 1, 1, 1};
    double poison[] = {1, 1, 1, .5, .5, .5, 1, .5, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2};
    double ground[] = {1, 1, 0, 2, 1, 2, .5, 1, 2, 2, 1, .5, 2, 1, 1, 1, 1, 1};
    double rock[] = {1, .5, 2, 1, .5, 1, 2, 1, .5, 2, 1, 1, 1, 1, 2, 1, 1, 2, .5};
    double bug[] = {1, .5, .5, .5, 1, 1, 1, .5, .5, .5, 1, 2, 1, 2, 1, 1, 2, .5};
    double ghost[] = {0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, 1};
    double steel[] = {1, 1, 1, 1, 1, 2, 1, 1, .5, .5, .5, 1, .5, 1, 2, 1, 1, 2};
    double fire[] = {1, 1, 1, 1, 1, .5, 2, 1, 2, .5, .5, 2, 1, 1, 2, .5, 1, 1};
    double water[] = {1, 1, 1, 1, 2, 2, 1, 1, 1, 2, .5, .5, 1, 1, 1, .5, 1, 1};
    double grass[] = {1, 1, .5, .5, 2, 2, .5, 1, .5, .5, 2, .5, 1, 1, 1, .5, 1, 1};
    double electric[] = {1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 2, .5, .5, 1, 1, .5, 1, 1};
    double psychic[] = {1, 2, 1, 2, 1, 1, 1, 1, .5, 1, 1, 1, 1, .5, 1, 1, 0, 1};
    double ice[] = {1, 1, 2, 1, 2, 1, 1, 1, .5, .5, .5, 2, 1, 1, .5, 2, 1, 1};
    double dragon[] = {1, 1, 1, 1, 1, 1, 1, 1, .5, 1, 1, 1, 1, 1, 1, 2, 1, 0};
    double dark[] = {1, .5, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, .5, .5};
    double fairy[] = {1, 2, 1, .5, 1, 1, 1, 1, .5, .5, 1, 1, 1, 1, 1, 2, 2, 1};
    double typingArray[][] = {normal, fighting, flying, poison, ground, rock, bug, ghost, steel, fire,
            water, grass, electric, psychic, ice, dragon, dark, fairy};
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
            double answer=0;
            if(defendType2==-1) {

                answer = typingArray[attackType][defendType1];
                return answer;
            }
            else {
                answer = typingArray[attackType][defendType1]*typingArray[attackType][defendType2];
                return answer;
            }

        }
//    public int calculateDamage(Pokemon user, Pokemon target, int movNumber){
//        //code way to calculate damage
//
//    }
        public int calculateBasicDamage(BasicPokemon user, BasicPokemon target,BasicMove move){
        //code way to calculate damage
        int damage=0;
        int attack,defense,power,moveType,defenseType1,defenseType2,userType1,userType2;
    if(move.category.equalsIgnoreCase("Physical")) {
         attack = user.getAttack();
                 defense = target.getDefense();
                 power = move.getBasePower();
                 moveType = getIntFromType(move.getType());
                defenseType1 = getIntFromType(target.getType1());
        defenseType2 = getIntFromType(target.getType2());
                userType1 = getIntFromType(user.getType1());
                userType2 = getIntFromType(user.getType2());

    }
    else if(move.category.equalsIgnoreCase("Special")){
         attack = user.getSpecialAttack();
                 defense = target.getSpecialDefense();
                 power = move.getBasePower();
                 moveType = getIntFromType(move.getType());
                defenseType1 = getIntFromType(target.getType1());
                 defenseType2 = getIntFromType(target.getType2());
                userType1 = getIntFromType(user.getType1());
                 userType2 = getIntFromType(user.getType2());
    }
    else{
        return 0;
    }

            damage=(int)((((200/5+2)*power*attack/defense)/50+2)*typeModifier(moveType,defenseType1,defenseType2)* ThreadLocalRandom.current().nextDouble(.85,1.01)
            );


        if(userType1==moveType||userType2==moveType){
            damage=(int)(damage*1.5);
        }
        if(Math.random()<0.04){
            damage*=2;
        }
        return (int)(damage/4.61538461538*3);
                }
    public int calculateWhoGoesFirst(Player p1, Player p2, BasicMove p1Move,BasicMove p2Move){
        int p1Speed=p1.currentMon.getSpeed();
        int p2Speed=p2.currentMon.getSpeed();
        int p1Priority=p1Move.priority;
        int p2Priority=p2Move.priority;
        if(p1.currentMon.status.equals("para"))
        {
            p1Speed /= 2;
        }
        if(p2.currentMon.status.equals("para"))
        {
            p2Speed /= 2;
        }
        if(p1Priority>p2Priority){
            return 1;
        }
        else if(p2Priority>p1Priority){
            return 2;
        }
        if(p1Speed>p2Speed){
            return 1;
        }
        else if(p2Speed>p1Speed){
            return 2;
        }
        else{
            if(Math.random()>=0.5){
                return 1;
            }
            else{
                return 2;
            }
        }

    }

                public String howEffective(int moveTypeP, int defendType1P, int defendType2P){
                    double x=typeModifier(moveTypeP,defendType1P,defendType2P);

                    if(x==0){
                        return "It had no effect...";
                    }
                    else if(x==2.0){
                        return "It's super effective!";
                    }
                    else if(x==4.0){
                        return "It's super duper effective!!";
                    }
                    else if(x==0.5){
                        return "It's not very effective...";
                    }
                    else if(x==0.25){
                        return "It had very little effect...";
                    }
                    else{
                        return "";
                    }
                }
//    public int calculateStatus (Pokemon user, Pokemon target, int movNumber){
//        //code way to calculate status
//    }

                }
