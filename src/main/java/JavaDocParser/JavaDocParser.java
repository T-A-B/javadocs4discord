package JavaDocParser;

import jdk.jfr.internal.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.junit.jupiter.api.extension.ParameterResolver;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class JavaDocParser {
    static HashMap<Integer, String> javadocRandomizerMap = new HashMap<>();
    static {
        populateMap();

    }
    public static void populateMap(){
        File dir = new File("DOCS/JDK8/docs/api/java");
        Collection<File> files = FileUtils.listFiles(
                dir,
                new RegexFileFilter("^((?!\\-).)*$"),
                DirectoryFileFilter.DIRECTORY
        );
        int x = 0;
        for(File f : files ) {
            System.out.println(f.getPath());

            if(f.getPath().contains(".html") && !f.getPath().contains("-")) {
                javadocRandomizerMap.put(x, f.getPath());
                x++;
            }

        }
        System.out.println("hello");;
    }
    public static int getRandom(){

        Random r = new Random(System.currentTimeMillis());
        return  r.nextInt(javadocRandomizerMap.size());

    }

    public static ArrayList<String> getJavaDocOfTheDayPost(){
        String fileName = javadocRandomizerMap.get(getRandom());
        System.out.println("FILENAME: " + fileName);
        ArrayList<String> lines = null;
        File f = new File(fileName);
        try {
         lines = (ArrayList<String>) Files.readAllLines(f.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean startRecording = false;
        ArrayList<String> linesWeCareAbout = new ArrayList<>();
      //  linesWeCareAbout.add("https://docs.oracle.com/javase/8/" + fileName.replace("DOCS\\JDK8\\", "").replace("\\", "/"));

        System.out.println("####" + fileName.replace("DOCS\\JDK8\\", ""));

        for(String s : lines) {
            if(s.equals("<!-- ======== START OF CLASS DATA ======== -->")){
                startRecording = true;
                continue;
            }
            if (s.contains("=\"summary\"")){
                System.out.println("wrwrqereqetqt");
                break;
            };
            if(startRecording){
                linesWeCareAbout.add(s.replaceAll("<[^>]*>", "").replaceAll("&.*;", "") + "\n");
            }
        }

    //    linesWeCareAbout.add("```");
        StringBuilder sbOutString = new StringBuilder().append("https://docs.oracle.com/javase/8/" + fileName.replace("DOCS\\JDK8\\", "").replace("\\", "/")).append("\n```");
        for(String s: linesWeCareAbout){
            sbOutString.append(s).append("\n");
        }
   //     linesWeCareAbout.add("```");
        String totalString = sbOutString.append("```").toString().replace("\n\n\n", "\n\n");

        ArrayList<String> outList = new ArrayList<>();
        ArrayList<String> parallelList = new ArrayList<>();
        StringBuilder sbLocal = new StringBuilder().append("https://docs.oracle.com/javase/8/" + fileName.replace("DOCS\\JDK8\\", "").replace("\\", "/")).append("```");
        if (totalString.length() < 2000) {
            outList.add(totalString);
            return outList;
        }
        else {
            int localSum = 0;



            parallelList.addAll(linesWeCareAbout);

            for(String s : linesWeCareAbout){
                localSum += s.length();
                if (localSum < 1996) {
                    sbLocal.append(s);
                }
                else {
                    localSum = 0;
                    outList.add(sbLocal.append("```").toString());
                    sbLocal = new StringBuilder().append("```");
                ;
                }
                parallelList.remove(s);
            }
        }

        if (!sbLocal.toString().equals( ""))
        outList.add(sbLocal.append("```").toString().replace("```\n```", "\n```"));
        return outList;
    }

    public static ArrayList<String> getSpecificJavaDocPost(String p_filename){
        String fileName = "";
        for(int i : javadocRandomizerMap.keySet()){
            if(javadocRandomizerMap.get(i).endsWith("\\"+ p_filename + ".html")){
                fileName = javadocRandomizerMap.get(i);
            }
        }



        System.out.println("FILENAME: " + fileName);
        ArrayList<String> lines = null;
        File f = new File(fileName);
        try {
            lines = (ArrayList<String>) Files.readAllLines(f.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean startRecording = false;
        ArrayList<String> linesWeCareAbout = new ArrayList<>();
        //  linesWeCareAbout.add("https://docs.oracle.com/javase/8/" + fileName.replace("DOCS\\JDK8\\", "").replace("\\", "/"));

        System.out.println("####" + fileName.replace("DOCS\\JDK8\\", ""));

        for(String s : lines) {
            if(s.equals("<!-- ======== START OF CLASS DATA ======== -->")){
                startRecording = true;
                continue;
            }
            if (s.contains("=\"summary\"")){
                System.out.println("wrwrqereqetqt");
                break;
            };
            if(startRecording){
                linesWeCareAbout.add(s.replaceAll("<[^>]*>", "").replaceAll("&.*;", "") + "\n");
            }
        }

        //    linesWeCareAbout.add("```");
        StringBuilder sbOutString = new StringBuilder().append("https://docs.oracle.com/javase/8/" + fileName.replace("DOCS\\JDK8\\", "").replace("\\", "/")).append("\n```");
        for(String s: linesWeCareAbout){
            sbOutString.append(s).append("\n");
        }
        //     linesWeCareAbout.add("```");
        String totalString = sbOutString.append("```").toString().replace("\n\n\n", "\n\n");

        ArrayList<String> outList = new ArrayList<>();
        ArrayList<String> parallelList = new ArrayList<>();
        StringBuilder sbLocal = new StringBuilder().append("https://docs.oracle.com/javase/8/" + fileName.replace("DOCS\\JDK8\\", "").replace("\\", "/")).append("\n```");
        if (totalString.length() < 2000) {
            outList.add(totalString);
            return outList;
        }
        else {
            int localSum = sbLocal.length();



            parallelList.addAll(linesWeCareAbout);

            for(String s : linesWeCareAbout){
                localSum += s.length();
                if (localSum < 1992) {
                    sbLocal.append(s);

                }
                else {
                    localSum = 0;
                    outList.add(sbLocal.append("```").toString());
                    sbLocal = new StringBuilder().append("```");
                    ;
                }
                parallelList.remove(s);
            }
        }

        if (!sbLocal.toString().equals( ""))
            outList.add(sbLocal.append("```").toString().replace("```\n```", "\n```"));
        return outList;
    }

    public static void main(String[] args){
        System.out.println(getJavaDocOfTheDayPost());
    }
}
