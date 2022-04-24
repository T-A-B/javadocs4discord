package JavaDocParser;

import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaDocParser {
    public static String getJavaDocOfTheDayPost(String p_filename){
        ArrayList<String> lines = null;
        File f = new File("DOCS/JDK8/docs/api/java/io/BufferedOutputStream.html");
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
                linesWeCareAbout.add(s.replaceAll("<[^>]*>", ""));
            }
        }

        linesWeCareAbout.add("```");
        StringBuilder sbOutString = new StringBuilder();
        for(String s: linesWeCareAbout){
            sbOutString.append(s).append("\n");
        }

        return sbOutString.toString();
    }

    public static void main(String[] args){
        System.out.println(getJavaDocOfTheDayPost(""));
    }
}
