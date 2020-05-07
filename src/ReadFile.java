import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {
    //17
//Num, accuracy, power, category, desc, name, pp , priority, flags, chance, self, boosts, crit ratio, drain, heal,status,type
    public String formatShowDownMovesets(String s) {
        String masterString = "";
        String[] chunks;
        chunks = s.split("ENDLINE");
        String[] lines;
        String[] currentChunksLines = new String[19];
        String currentLine = "";
        for (int i = 0; i < chunks.length; i++) {
            String chunk = chunks[i];
            lines = chunk.split("\n");
            for (int y = 0; y < currentChunksLines.length; y++) {
                currentChunksLines[y] = "null#";
            }
            for (int k = 0; k < lines.length; k++) {
                currentLine = lines[k];


                if (currentLine.contains("num: ")) {

                    currentChunksLines[0] = currentLine.substring
                            (currentLine.indexOf("num: ") + 5, currentLine.indexOf(","))
                            + "#";


                } else if (currentLine.contains("accuracy: ")) {

                    currentChunksLines[1] = currentLine.substring
                            (currentLine.indexOf("accuracy: ") + 10, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("basePower: ")) {

                    currentChunksLines[2] = currentLine.substring
                            (currentLine.indexOf("basePower: ") + 11, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("category: ")) {

                    currentChunksLines[3] = currentLine.substring
                            (currentLine.indexOf("category: ") + 10, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("desc: ")) {

                    currentChunksLines[4] = currentLine.substring
                            (currentLine.indexOf("desc: ") + 6, currentLine.indexOf("\",") + 1)
                            + "#";
                } else if (currentLine.contains("name: ")) {

                    currentChunksLines[5] = currentLine.substring
                            (currentLine.indexOf("name: ") + 6, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("pp: ")) {

                    currentChunksLines[6] = currentLine.substring
                            (currentLine.indexOf("pp: ") + 4, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("priority: ")) {

                    currentChunksLines[7] = currentLine.substring
                            (currentLine.indexOf("priority: ") + 10, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("flags: ")) {

                    currentChunksLines[8] = currentLine.substring
                            (currentLine.indexOf("flags: ") + 7, currentLine.indexOf("},") + 1)
                            + "#";
                } else if (currentLine.contains("\ttype: ")) {

                    currentChunksLines[17] = currentLine.substring
                            (currentLine.indexOf("type: ") + 6, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("\tcritRatio: ")) {

                    currentChunksLines[12] = currentLine.substring
                            (currentLine.indexOf("critRatio: ") + 11, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("\tdrain: ")) {

                    currentChunksLines[13] = currentLine.substring
                            (currentLine.indexOf("drain: ") + 7, currentLine.indexOf("],") + 1)
                            + "#";
                } else if (currentLine.contains("\tstatus: ")) {

                    currentChunksLines[15] = currentLine.substring
                            (currentLine.indexOf("status: ") + 8, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("\theal ")) {

                    currentChunksLines[14] = currentLine.substring
                            (currentLine.indexOf("heal: ") + 6, currentLine.indexOf("],") + 1)
                            + "#";
                } else if (currentLine.contains("\ttarget: \"self\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] ="true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                }
                else if (currentLine.contains("\ttarget: \"adjacentAllyOrSelf\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] ="true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                }
                else if (currentLine.contains("\ttarget: \"allyTeam\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] ="true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                }
                else if (currentLine.contains("\ttarget: \"adjacentAlly\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] ="true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                }
                else if (currentLine.contains("\tself: {")) {

                    currentChunksLines[10] = "true"
                            + "#";
                } else if (currentLine.contains("\tchance: ")) {

                    currentChunksLines[9] = currentLine.substring
                            (currentLine.indexOf("chance: ") + 8, currentLine.indexOf(","))
                            + "#";
                }
            else if (currentLine.contains("\tid: ")) {

                currentChunksLines[18] = currentLine.substring
                        (currentLine.indexOf("id: ") + 4, currentLine.indexOf(","))
                        + "\n";
            }
                else if (currentLine.indexOf("\tboosts: ") != -1) {
                    currentChunksLines[11] = "";
                    for (int z = 1; !lines[k + z].equalsIgnoreCase("NOTLINEEND"); z++) {
                        currentChunksLines[11] += lines[k + z];
                    }
                    currentChunksLines[11] += "#";
//                    String Icantthinkofaname="";
//                    while(!(Icantthinkofaname.equalsIgnoreCase("NOTLINEEND")) {
//                            Icantthinkofaname=lines
//                        currentChunksLines[11] =
//                    }
                }
//            else{
//                System.out.println("what");
//            }

            }
            for (int a = 0; a < currentChunksLines.length; a++) {
                masterString += currentChunksLines[a];
            }
        }
        return masterString;
    }
//  0    1        2      3          4     5    6    7         8      9       10     11       12         13    14     15   16     17     18
//Num, accuracy, power, category, desc, name, pp , priority, flags, chance, self, boosts, crit ratio, drain, heal,status,target, type, id
    //self= boosts target self
    // target= everything targets self
//
//            if(currentLine.contains("status: "))
//            {
//                currentChunk+=currentLine;
//            }
//           if(currentLine.contains("chance: "))
//            {
//                currentChunk+=currentLine;
//            }
//            if(currentLine.contains("boosts: "))
//            {
//                currentChunk+=currentLine;
//            }
//            if(currentLine.contains("self:")||currentLine.contains("self\""))
//            {
//                currentChunk+=currentLine;
//            }

    public String orderMoves(String s) {
        String masterString = "";
        String [] neu= new String [719];
        String [] old =s.split("\n");
        int num=0;
        for(int i =0; i<old.length;i++){
            String firstPartOfOLd=old[i].split("#")[0];
            try{
                num = Integer.parseInt(firstPartOfOLd);
            }
            catch (Exception e){

            }
            neu[num-1]=old[i];
        }
//    String[] hi = new String[719];
//    String[] otherString = s.split("\n");
//    String[] more = new String[701];
//    String[] makeitstop = new String[701];
//    int num;
//    for (int i = 0; i < 701; i++) {
//        makeitstop = otherString[i].split("#");
//        more = makeitstop;
//        num = Integer.parseInt(more[0]);
//        hi[num - 1] = otherString[i];
//    }
        for (int i = 0; i < 719; i++) {

            masterString += neu[i] + "\n";
        }
        return masterString;
    }
    public String formatShowDownLearnSets(String s) {
        String masterString = "";
        String[] chunks;
        chunks = s.split("}},");
        String[] lines;

        String currentLine = "";



        return masterString;
    }
//        masterString.replace("num: ", "");
//        masterString.replace(",accuracy: ", "#");
//        masterString.replace(",basePower ", "#");
//        masterString.replace(",category", "#");
//        masterString.replace(",desc: ", "#");
//        masterString.replace(",name: ", "#");
//        masterString.replace(",pp: ", "#");
//        masterString.replace(",priority: ", "#");
//        masterString.replace(",flags: ", "#");
//        masterString.replace(",type: ", "#");
//        masterString.replace("\"", "");
//        masterString.replace("\'", "");
//        masterString.replace(",\n", "\n");
//        String[] line = s.split("\n");
//        for (int i = 0; i < line.length; i++) {
//
//            if(line[i].contains("num: "))
//            {
//              masterString+=line[i];
//            }
//            else if(line[i].contains("accuracy: "))
//            {
//                masterString+=line[i];
//            }
//
//            else if(line[i].contains("basePower: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("category: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("desc: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("name: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("pp: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("priority: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("flags: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("type: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("status: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("chance: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("boosts: "))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("self:")||line[i].contains("self\""))
//            {
//                masterString+=line[i];
//            }
//            else if(line[i].contains("ENDLINE"))
//            {
//                masterString+="\n";
//            }
//
//        }

//
//
//    }

}
//                "Moves: "+moves[0].getMoveName()+" "+moves[1].getMoveName()+" "+moves[2].getMoveName()+" "+moves[3].getMoveName()
//                +"\n"+