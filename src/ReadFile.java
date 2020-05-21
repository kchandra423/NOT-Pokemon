//
//  Author: Kumar Chandra
//  Revised: Kumar Chandra
//           5/10/20
//
//  Notes:
//      This class is exclsuively used to create our text files from the Showdown text files
//  Bugs:
//    none
//
//import sun.rmi.transport.proxy.RMIMasterSocketFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class ReadFile {
    //17
//Num, accuracy, power, category, desc, name, pp , priority, flags, chance, self, boosts, crit ratio, drain, heal,status,type
    public static void main(String[] args) {
        ReadFile read = new ReadFile();
        String copy = "something went wrong; ";
        try {
//			copy = Files.readString(Paths.get("Stats.txt"));
            byte[] file = Files.readAllBytes(Paths.get("Text/ShowdownStats.txt"));
            copy = new String(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(
//            read.orderMoves(
                (read.getSpecifiedPokemon(copy)));
    }

    public String formatShowDownMovesets(String s) {
        //takes a string of the showdown movesets txt file and returns a string with
        // each move neccesary stats seperated by # and each move seperated by a \n
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

//
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
                } else if (currentLine.contains("\theal: ")) {

                    currentChunksLines[14] = currentLine.substring
                            (currentLine.indexOf("heal: ") + 6, currentLine.indexOf("],") + 1)
                            + "#";
                } else if (currentLine.contains("\ttarget: \"self\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] = "true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                } else if (currentLine.contains("\ttarget: \"adjacentAllyOrSelf\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] = "true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                } else if (currentLine.contains("\ttarget: \"allyTeam\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] = "true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                } else if (currentLine.contains("\ttarget: \"adjacentAlly\"")) {
                    System.out.println("hjjjhhjj");
                    currentChunksLines[16] = "true#";

//                            currentLine.substring
//                            (currentLine.indexOf("target: ") + 8, currentLine.indexOf("\""))
//                            + "#";
                } else if (currentLine.contains("\tself: {")) {

                    currentChunksLines[10] = "true"
                            + "#";
                } else if (currentLine.contains("\tchance: ")) {

                    currentChunksLines[9] = currentLine.substring
                            (currentLine.indexOf("chance: ") + 8, currentLine.indexOf(","))
                            + "#";
                } else if (currentLine.contains("\tid: ")) {

                    currentChunksLines[18] = currentLine.substring
                            (currentLine.indexOf("id: ") + 4, currentLine.indexOf(","))
                            + "\n";
                } else if (currentLine.indexOf("\tboosts: ") != -1) {
                    currentChunksLines[11] = "";
                    for (int z = 1; !lines[k + z].equalsIgnoreCase("NOTLINEEND"); z++) {
                        currentChunksLines[11] += lines[k + z];
                    }
                    currentChunksLines[11] += "#";

                }


            }
            for (int a = 0; a < currentChunksLines.length; a++) {
                masterString += currentChunksLines[a];
            }
        }
        return masterString;
    }
    public String getMegas(String s) {
        //takes a string of the showdown movesets txt file and returns a string with
        // each move neccesary stats seperated by # and each move seperated by a \n
        String masterString = "";
        String[] chunks;
        chunks = s.split("ENDLINE");
        String[] currentChunksLines = new String[10];
        String[] lines;
        String currentLine = "";
        int numberOfMegas=0;
        for (int i = 0; i < chunks.length; i++) {

            String chunk = chunks[i];
            lines = chunk.split("\n");
            if(lines[1].contains("mega")) {
                Arrays.fill(currentChunksLines, "null;");
                numberOfMegas++;
                for (int k = 0; k < lines.length; k++) {
                    currentLine = lines[k];
                    if (currentLine.contains("num: ")) {
                        currentChunksLines[0] = "1000"+numberOfMegas
                                + ";";
                    }
                    if (currentLine.contains("species: ")) {
                        currentChunksLines[1] = currentLine.substring
                                (currentLine.indexOf("species: ") + 9, currentLine.indexOf(",") + 1)
                                + ";";
                    }
                    if (currentLine.contains("types: ")) {
                        String x=currentLine.substring(currentLine.indexOf("types: ") + 7, currentLine.indexOf("],") );

                        x.replace("\"","");
                        x.replace(" ","");
                        String[] types=x.split(",");
                        currentChunksLines[2]=types[0]+";";
                        try{
                            currentChunksLines[3]=types[1]+";";
                        }catch (Exception e){
                            currentChunksLines[3]="NA;";
                        }

                    }
                    if (currentLine.contains("baseStats: ")) {
                        String x=currentLine.substring(currentLine.indexOf("baseStats: ") + 11, currentLine.indexOf("},") + 1);
                        x.replace("}","");
                        x.replace(" ","");
                        String newString="";
                       for(int z=0;z<x.length();z++){
                           if((Character.isDigit(x.charAt(z)))||x.charAt(z)==','){
                               newString+=x.charAt(z);
                           }
                       }

                        String[] stats=newString.split(",");
                        currentChunksLines[4]=stats[0]+";";
                        currentChunksLines[5]=stats[1]+";";
                        currentChunksLines[6]=stats[2]+";";
                        currentChunksLines[7]=stats[3]+";";
                        currentChunksLines[8]=stats[4]+";";
                        currentChunksLines[9]=stats[5]+";"+"\n";



                    }
                }
                for (int a = 0; a < currentChunksLines.length; a++) {
                    masterString += currentChunksLines[a];
                }
            }
        }
        masterString=masterString.replace("]","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace("\"","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace(" ","");
        masterString=masterString.replace(",","");
        return masterString;

    }
    public String getArceus(String s) {
        //takes a string of the showdown movesets txt file and returns a string with
        // each move neccesary stats seperated by # and each move seperated by a \n
        String masterString = "";
        String[] chunks;
        chunks = s.split("ENDLINE");
        String[] currentChunksLines = new String[10];
        String[] lines;
        String currentLine = "";
        int numberOfMegas=49;
        for (int i = 0; i < chunks.length; i++) {

            String chunk = chunks[i];
            lines = chunk.split("\n");
            if(lines[1].contains("arceus")) {
                Arrays.fill(currentChunksLines, "null;");
                numberOfMegas++;
                for (int k = 0; k < lines.length; k++) {
                    currentLine = lines[k];
                    if (currentLine.contains("num: ")) {
                        currentChunksLines[0] = "1000"+numberOfMegas
                                + ";";
                    }
                    if (currentLine.contains("species: ")) {
                        currentChunksLines[1] = currentLine.substring
                                (currentLine.indexOf("species: ") + 9, currentLine.indexOf(",") + 1)
                                + ";";
                    }
                    if (currentLine.contains("types: ")) {
                        String x=currentLine.substring(currentLine.indexOf("types: ") + 7, currentLine.indexOf("],") );

                        x.replace("\"","");
                        x.replace(" ","");
                        String[] types=x.split(",");
                        currentChunksLines[2]=types[0]+";";
                        try{
                            currentChunksLines[3]=types[1]+";";
                        }catch (Exception e){
                            currentChunksLines[3]="NA;";
                        }

                    }
                    if (currentLine.contains("baseStats: ")) {
                        String x=currentLine.substring(currentLine.indexOf("baseStats: ") + 11, currentLine.indexOf("},") + 1);
                        x.replace("}","");
                        x.replace(" ","");
                        String newString="";
                        for(int z=0;z<x.length();z++){
                            if((Character.isDigit(x.charAt(z)))||x.charAt(z)==','){
                                newString+=x.charAt(z);
                            }
                        }

                        String[] stats=newString.split(",");
                        currentChunksLines[4]=stats[0]+";";
                        currentChunksLines[5]=stats[1]+";";
                        currentChunksLines[6]=stats[2]+";";
                        currentChunksLines[7]=stats[3]+";";
                        currentChunksLines[8]=stats[4]+";";
                        currentChunksLines[9]=stats[5]+";"+"\n";



                    }
                }
                for (int a = 0; a < currentChunksLines.length; a++) {
                    masterString += currentChunksLines[a];
                }
            }
        }
        masterString=masterString.replace("]","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace("\"","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace(" ","");
        masterString=masterString.replace(",","");
        return masterString;

    }
    public String getSilvally(String s) {
        //takes a string of the showdown movesets txt file and returns a string with
        // each move neccesary stats seperated by # and each move seperated by a \n
        String masterString = "";
        String[] chunks;
        chunks = s.split("ENDLINE");
        String[] currentChunksLines = new String[10];
        String[] lines;
        String currentLine = "";
        int numberOfMegas=67;
        for (int i = 0; i < chunks.length; i++) {

            String chunk = chunks[i];
            lines = chunk.split("\n");
            if(lines[1].contains("silvally")) {
                Arrays.fill(currentChunksLines, "null;");
                numberOfMegas++;
                for (int k = 0; k < lines.length; k++) {
                    currentLine = lines[k];
                    if (currentLine.contains("num: ")) {
                        currentChunksLines[0] = "1000"+numberOfMegas
                                + ";";
                    }
                    if (currentLine.contains("species: ")) {
                        currentChunksLines[1] = currentLine.substring
                                (currentLine.indexOf("species: ") + 9, currentLine.indexOf(",") + 1)
                                + ";";
                    }
                    if (currentLine.contains("types: ")) {
                        String x=currentLine.substring(currentLine.indexOf("types: ") + 7, currentLine.indexOf("],") );

                        x.replace("\"","");
                        x.replace(" ","");
                        String[] types=x.split(",");
                        currentChunksLines[2]=types[0]+";";
                        try{
                            currentChunksLines[3]=types[1]+";";
                        }catch (Exception e){
                            currentChunksLines[3]="NA;";
                        }

                    }
                    if (currentLine.contains("baseStats: ")) {
                        String x=currentLine.substring(currentLine.indexOf("baseStats: ") + 11, currentLine.indexOf("},") + 1);
                        x.replace("}","");
                        x.replace(" ","");
                        String newString="";
                        for(int z=0;z<x.length();z++){
                            if((Character.isDigit(x.charAt(z)))||x.charAt(z)==','){
                                newString+=x.charAt(z);
                            }
                        }

                        String[] stats=newString.split(",");
                        currentChunksLines[4]=stats[0]+";";
                        currentChunksLines[5]=stats[1]+";";
                        currentChunksLines[6]=stats[2]+";";
                        currentChunksLines[7]=stats[3]+";";
                        currentChunksLines[8]=stats[4]+";";
                        currentChunksLines[9]=stats[5]+";"+"\n";



                    }
                }
                for (int a = 0; a < currentChunksLines.length; a++) {
                    masterString += currentChunksLines[a];
                }
            }
        }
        masterString=masterString.replace("]","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace("\"","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace(" ","");
        masterString=masterString.replace(",","");
        return masterString;

    }
    public String getSpecifiedPokemon(String s) {
        //takes a string of the showdown movesets txt file and returns a string with
        // each move neccesary stats seperated by # and each move seperated by a \n
        String masterString = "";
        String[] chunks;
        chunks = s.split("ENDLINE");
        String[] currentChunksLines = new String[10];
        String[] lines;
        String currentLine = "";
        int numberOfMegas=89;
        for (int i = 0; i < chunks.length; i++) {

            String chunk = chunks[i];
            lines = chunk.split("\n");
            if(lines[1].contains("primal")) {
                Arrays.fill(currentChunksLines, "null;");
                numberOfMegas++;
                for (int k = 0; k < lines.length; k++) {
                    currentLine = lines[k];
                    if (currentLine.contains("num: ")) {
                        currentChunksLines[0] = "1000"+numberOfMegas
                                + ";";
                    }
                    if (currentLine.contains("species: ")) {
                        currentChunksLines[1] = currentLine.substring
                                (currentLine.indexOf("species: ") + 9, currentLine.indexOf(",") + 1)
                                + ";";
                    }
                    if (currentLine.contains("types: ")) {
                        String x=currentLine.substring(currentLine.indexOf("types: ") + 7, currentLine.indexOf("],") );

                        x.replace("\"","");
                        x.replace(" ","");
                        String[] types=x.split(",");
                        currentChunksLines[2]=types[0]+";";
                        try{
                            currentChunksLines[3]=types[1]+";";
                        }catch (Exception e){
                            currentChunksLines[3]="NA;";
                        }

                    }
                    if (currentLine.contains("baseStats: ")) {
                        String x=currentLine.substring(currentLine.indexOf("baseStats: ") + 11, currentLine.indexOf("},") + 1);
                        x.replace("}","");
                        x.replace(" ","");
                        String newString="";
                        for(int z=0;z<x.length();z++){
                            if((Character.isDigit(x.charAt(z)))||x.charAt(z)==','){
                                newString+=x.charAt(z);
                            }
                        }

                        String[] stats=newString.split(",");
                        currentChunksLines[4]=stats[0]+";";
                        currentChunksLines[5]=stats[1]+";";
                        currentChunksLines[6]=stats[2]+";";
                        currentChunksLines[7]=stats[3]+";";
                        currentChunksLines[8]=stats[4]+";";
                        currentChunksLines[9]=stats[5]+";"+"\n";



                    }
                }
                for (int a = 0; a < currentChunksLines.length; a++) {
                    masterString += currentChunksLines[a];
                }
            }
        }
        masterString=masterString.replace("]","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace("\"","");
        masterString=masterString.replace("[","");
        masterString=masterString.replace(" ","");
        masterString=masterString.replace(",","");
        return masterString;

    }
//  0    1        2      3          4     5    6    7         8      9       10     11       12         13    14     15   16     17     18
//Num, accuracy, power, category, desc, name, pp , priority, flags, chance, self, boosts, crit ratio, drain, heal,status,target, type, id
    //self= boosts target self
    // target= everything targets self
//public String addBack(String s) {
////    //takes a string of the showdown movesets txt file and returns a string with
////    // each move neccesary stats seperated by # and each move seperated by a \n
////    String masterString = "";
////    String[] lines;
////    lines = s.split("ENDLINE");
////    String[] currentChunksLines = new String[10];
////
////    String currentLine = "";
////
////
////    return masterString;
////
////}
    public String orderMoves(String s) {//orders a txt files move from first to last
        String masterString = "";
        String[] neu = new String[719];
        String[] old = s.split("\n");
        int num = 0;

        for (int i = 0; i < old.length; i++) {
            String firstPartOfOLd = old[i].split("#")[0];
            try {
                num = Integer.parseInt(firstPartOfOLd);
            } catch (Exception e) {

            }
            neu[num - 1] = old[i];
        }

        for (int i = 0; i < 719; i++) {

            masterString += neu[i] + "\n";
        }
        return masterString;
    }

    public String formatShowDownLearnSets(String s) {
        String masterString = "";
        String[] chunks;
        chunks = s.split("\t}},\n");
        String[] lines;

        String currentLine = "";
        for (int i = 0; i < chunks.length; i++) {
            lines = chunks[i].split("\n");
//    System.out.println(chunks[i]);
            for (int k = 0; k < lines.length; k++) {
                currentLine = lines[k];


                masterString += currentLine.substring(0, currentLine.indexOf(":")) + "#";
            }
            masterString += "\n";
        }


        return masterString;
    }
}