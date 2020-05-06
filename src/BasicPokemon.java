import java.nio.file.Paths;

import java.nio.file.Files;

import java.io.*;

public class BasicPokemon {
    Calculator calculator = new Calculator();
    private int dex = 0;
    private String name = "";
     int baseHealth;
    private int health;
    private int baseSpecialDefense;
    int sleepCounter;
    private int baseDefense;

    private int baseAttack;

    private int baseSpecialAttack;
    public String status = "";
    private int baseSpeed;

    BasicMove[] moves = new BasicMove[4];

    private String type1 = "";
    private String type2 = "";

    //    private String status;
//    private boolean hasStatus;
    int attackIncrease;
    int attackDecrease;
    int specialAttackIncrease;
    int specialAttackDecrease;
    int defenseIncrease;
    int defenseDecrease;
    int specialDefenseIncrease;
    int specialDefenseDecrease;
    int speedIncrease;
    int speedDecrease;

    public int convertMmonName(String monName) {
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

    public BasicPokemon(int dexNumber) {
        dex = dexNumber;
        for (int i = 0; i < 4; i++) {
            moves[i] = new BasicMove(1);
        }
        setStats(dex);
    }

    public BasicPokemon(String pokeName) {
        name = pokeName;
        int dexNumber = convertMmonName(pokeName);
        dex = dexNumber;
        for (int i = 0; i < 4; i++) {
            moves[i] = new BasicMove(1);
        }
        setStats(dex);
    }
//    public BasicPokemon( int dexParam,
//                    String nameParam,
//                    int healthParam,
////                    int specialDefenseParam,
//                    int defenseParam,
//                    int attackParam,
////                    int specialAttackParam,
//                    int speedParam,
//                    BasicMove[] movesParam,
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

    public void setStats(int dexNUmber) {

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
        String stats = splittedCopy[dexNUmber - 1];
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
            return(int) (baseDefense * ((2.0 + defenseIncrease) / 2.0));
        } else if (defenseDecrease > 0 && defenseIncrease == 0) {// no stat increase, their is a stat decrease
            return (int)(baseDefense * (2.0 / (2.0 + defenseDecrease)));
        } else if (defenseIncrease > defenseDecrease && defenseDecrease != 0) {// stat increase and stat decrease, but
            // stat increase is higher.
            while (defenseDecrease > 0) {
                defenseDecrease--;
                defenseIncrease--;
            }
            return(int) (baseDefense * ((2.0 + defenseIncrease) / 2.0));
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
            moves[i] = new BasicMove(givenMoves[i]);
        }
    }

    public void setMoves(BasicMove[] givenMoves) {
        moves = givenMoves;
    }

    public BasicMove[] getMoves() {
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
        attackIncrease += stages;
        System.out.println(name +"'s attack increased by "+stages+" stages!");

    }

    public void setSpecialAttackIncrease(int stages) {
        specialAttackIncrease += stages;
        System.out.println(name +"'s special attack increased by "+stages+" stages!");
    }

    public void setDefenseIncrease(int stages) {

        defenseIncrease += stages;
        System.out.println(name +"'s defense increased by "+stages+" stages!");
    }

    public void setSpecialDefenseIncrease(int stages) {
        specialDefenseIncrease += stages;
        System.out.println(name +"'s special defense increased by "+stages+" stage!");
    }

    public void setSpeedIncrease(int stages) {

        speedIncrease += stages;
        System.out.println(name +"'s speed increased by "+stages+" stages!");

    }

    public void setAttackDecrease(int stages) {
        attackDecrease += stages;
        System.out.println(name +"'s attack decreased by "+stages+" stages...");

    }

    public void setSpecialAttackDecrease(int stages) {
        specialAttackDecrease += stages;
        System.out.println(name +"'s special attack decreased by "+stages+" stages...");
    }

    public void setDefenseDecrease(int stages) {
        defenseDecrease += stages;
        System.out.println(name +"'s defense decreased by "+stages+" stages...");
    }

    public void setSpecialDefenseDecrease(int stages) {
        specialDefenseDecrease += stages;
        System.out.println(name +"'s special defense decreased by "+stages+" stages...");
    }

    public void setSpeedDecrease(int stages) {

        speedDecrease += stages;
        System.out.println(name +"'s speed decreased by "+stages+" stages...");
    }
//    public void beParalyzed(BasicPokemon target)
//    {
//        if(target.getType1().equalsIgnoreCase("Electric") || target.getType2().equalsIgnoreCase("Electric"))
//        {
//            return;
//        }
//    }
//
//    public void bePoisoned(BasicPokemon target)
//    {
//        if(target.getType1().equalsIgnoreCase("Poison") || target.getType2().equalsIgnoreCase("Posion") || target.getType1().equalsIgnoreCase("Steel") || target.getType2().equalsIgnoreCase("Steel"))
//        {
//            return;
//        }
//        health = (int)(health - baseHealth * 1.0/8);
//    }
//
//    public void beBurned(BasicPokemon target)
//    {
//        if(target.getType1().equalsIgnoreCase("Fire") || target.getType2().equalsIgnoreCase("Fire"))
//        {
//            return;
//        }
//        health = (int)(health - baseHealth * 0.06);
//    }
//    public void beFrozen(BasicPokemon target)
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
//    public void beFlinched(BasicPokemon target)
//    {
//        moves = null;
//        return;
//    }
//
//    public void beConfused(BasicPokemon target)
//    {
//        int unflinch = (int)(Math.random()*4);
//        if(unflinch == 0)
//        {
//            return;
//        }
//        int damage = (int)(Math.random()*2);
//        if(damage == 0)
//        {
//            calculator.calculateBasicDamage(this, this, new BasicMove(1));
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
//    public BasicPokemon applyModifiers(BasicPokemon target){
//        return new BasicPokemon(target.getDex(),target.getName(),target.getHealth(),
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
            answer = "\nDexNumber: " + dex + "\n" + "Name: " + name + "\n" + "Health: " + health + "\n" +

                    "Defense: " + getDefense() + "\n" + "Attack: " + getAttack() + "\n" + "Special Attack: "
                    + getSpecialAttack() + "\n" + "Special Defense: " + getSpecialDefense() + "\n" + "Speed: "
                    + getSpeed() + "\n" + "Modifiers: " + (attackIncrease - attackDecrease) + " attack, "
                    + (defenseIncrease - defenseDecrease) + " defense, "
                    + (specialAttackIncrease - specialAttackDecrease) + " special attack, "
                    + (specialDefenseIncrease - specialDefenseDecrease) + " special defense modifier, "
                    + (speedIncrease - speedDecrease) + " speed modifier" + "\n" + "Types: " + type1 + " and " + type2
                    + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}