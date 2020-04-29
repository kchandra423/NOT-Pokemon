//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//public class ReadFile {
///*
//© Khicken
// */
//
//
//    public class ReadWrite {
//        public String url;
//        File f;
//        String [] dexNums;
//        String[][] file;
//
//        public ReadWrite(String source) {
//            url = source;
//            f = new File(url);
//            dexNums = new String[802];
//            file = breakApart();
//            for(int i = 0; i < file[0].length; i++) {
//                dexNums[i] = file[0][i];
//            }
//        }
//
//        protected String[][] breakApart() {
//            try {
//                file = new String[22][802];
//                Scanner sc = new Scanner(f);
//                String[] currentSegments;
//                int dex = 0;
//                while(sc.hasNextLine()) {
//                    currentSegments = sc.nextLine().split(";");
//                    for(int segment = 0; segment < currentSegments.length; segment++) {
//                        file[segment][dex] = currentSegments[segment];
//                    }
//                    dex++;
//                }
//
//                sc.close();
//
//                return file;
//            } catch(FileNotFoundException ee) {
//                ee.printStackTrace();
//                return null;
//            }
//        }
//
//
//
//        public String getPokemonInfo(int segment, int index) {
//            return file[segment][index];
//        }
//
//        public String[] getAllDexNums() {
//            return dexNums;
//        }
//
//        public String[] getAllNames() {
//            String [] allNames = new String [802];
//
//            for (int i = 0; i < allNames.length; i ++) {
//                allNames[i] = file[1][i];
//            }
//
//            return allNames;
//        }
//    }
//
//}
