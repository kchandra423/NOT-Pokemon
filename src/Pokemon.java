public class Pokemon {


    private int dex;
    private String name;
    private int health;
    private int specialDefense;
    private int defense;
    private int attack;
    private int specialAttack;
    private int speed;
    private Move[] moves;
    private String type1;
    private String type2;
    public int testing = 0;
    //hello again kumar
    private String status;
    private boolean hasStatus;
    private int attackModifier;
    private int specialAttackModifier;
    private int defenseModifier;
    private int specialDefenseModifier;
    private int speedModifier;
private boolean statsApplied;

    public Pokemon(int dexNumber){
        dex=dexNumber;
        //setStats(dex);
    }
    public Pokemon( int dexParam,
             String nameParam,
             int healthParam,
             int specialDefenseParam,
      int defenseParam,
       int attackParam,
            int specialAttackParam,
            int speedParam,
    Move[] movesParam,
         String type1Param,
            String type2Param,
            String statusParam,

         int attackModifierParam,
      int specialAttackModifierParam,
         int defenseModifierParam,
        int specialDefenseModifierParam,
            int speedModifierParam){

        dex=dexParam;
        name=nameParam;
        health=healthParam;
        specialDefense=specialDefenseParam;
        defense=defenseParam;
        attack=attackParam;
        specialAttack=specialAttackParam;
        speed=speedParam;
        moves=movesParam;
        type1=type1Param;
        type2=type2Param;
        status=statusParam;
        if(status.equalsIgnoreCase("NONE")){
            hasStatus=false;
        }
        else{
            hasStatus=true;
        }
       attackModifier=attackModifierParam;
        specialAttackModifier=specialAttackModifierParam;
       defenseModifier=defenseModifierParam;
       specialDefenseModifier=specialDefenseModifierParam;
        speedModifier=speedModifierParam;
         statsApplied=true;
    }

    private void setStats(int dexNUmber){
        //code some way of using the dex Number to get the stats of the pokemon
    }

    public int getDex() {
        return dex;
    }

    public int getHealth() {
        return health;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getDefense() {
        return defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpeed() {
        return speed;
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
    public String getStatus() {
        return status;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenseModifier() {
        return defenseModifier;
    }

    public int getSpecialAttackModifier() {
        return specialAttackModifier;
    }

    public boolean hasStatus() {
        return hasStatus;
    }

    public int getSpecialDefenseModifier() {
        return specialDefenseModifier;
    }
    public int getSpeedModifier() {
        return speedModifier;

    }

    public boolean StatsApplied() {
        return statsApplied;
    }

    public void setAttackModifier(int stages) {
        attackModifier+=stages;
        statsApplied=false;
    }
    public void setSpecialAttackModifier(int stages) {
        specialAttackModifier+=stages;
        statsApplied=false;
    }
    public void setDefenseModifier(int stages) {
        defenseModifier+=stages;
        statsApplied=false;
    }
    public void setSpecialDefenseModifier(int stages) {
        specialDefenseModifier+=stages;
        statsApplied=false;
    }
    public void setSpeedModifier(int stages) {
        speedModifier+=stages;
        statsApplied=false;
    }
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





//    int dexParam,
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
    public Pokemon applyModifiers(Pokemon target){
        return new Pokemon(target.getDex(),target.getName(),target.getHealth(),
                target.getSpecialDefense()+(int)(target.getSpecialDefenseModifier()*0.5*target.getSpecialDefense()),
                target.getDefense()+(int)(target.getDefenseModifier()*0.5*target.getDefense()),
                target.getAttack()+(int)(target.getAttackModifier()*0.5*target.getAttack()),
                target.getSpecialAttack()+(int)(target.getSpecialAttackModifier()*0.5*target.getSpecialAttack()),
                target.getSpeed()+(int)(target.getSpeedModifier()*0.5*target.getSpeed()),
                target.getMoves(),
                target.getType1(),target.getType2(),target.getStatus(),target.getAttackModifier(),target.getSpecialAttackModifier(),
                target.getDefenseModifier(),target.getSpecialDefenseModifier(),target.getSpeedModifier());
    }

    public String toString(){
        String answer="\nDexNumber: "+dex
                +"\n"+
                "Name: "+name
                +"\n"+
                "Health: "+health
                +"\n"+
                "SpecialDefense: "+specialDefense
                +"\n"+
                "Defense: "+defense
                +"\n"+
                "Attack"+attack
                +"\n"+
                "Special Attack: "+specialAttack
                +"\n"+
        "Special Attack: "+specialAttack
                +"\n"+
                "Speed: "+speed
                +"\n"+

                "Types: "+type1 +"and"+type2
                +"\n"+
                "Status: "+status
                +"\n"+
        "Modifiers: "+attackModifier+" attack, "+defenseModifier+" defense, "+specialAttackModifier+" special attack, "+specialDefenseModifier+" special defense modifier, "+speedModifier+" speed modifier"
                +"\n";
        return answer;
    }
}
