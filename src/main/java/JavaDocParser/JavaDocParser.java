package JavaDocParser;

import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaDocParser {
    public static ArrayList<String> getJavaDocOfTheDayPost(String p_filename){
        ArrayList<String> lines = null;
        File f = new File("DOCS/JDK8/docs/api/java/util/concurrent/ConcurrentHashMap.html");
        try {
         lines = (ArrayList<String>) Files.readAllLines(f.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean startRecording = false;
        ArrayList<String> linesWeCareAbout = new ArrayList<>();
        linesWeCareAbout.add("```");
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

        linesWeCareAbout.add("```");
        StringBuilder sbOutString = new StringBuilder();
        for(String s: linesWeCareAbout){
            sbOutString.append(s).append("\n");
        }

        String totalString = sbOutString.toString();

        ArrayList<String> outList = new ArrayList<>();
        ArrayList<String> parallelList = new ArrayList<>();
        StringBuilder sbLocal = new StringBuilder();
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

        if (sbLocal.toString() != "")
        outList.add(sbLocal.toString());
        return outList;
    }

    public static void main(String[] args){
        System.out.println(getJavaDocOfTheDayPost(""));
    }
}
