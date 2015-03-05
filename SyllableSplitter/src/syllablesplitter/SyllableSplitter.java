package syllablesplitter;

import edu.northwestern.at.morphadorner.corpuslinguistics.hyphenator.AmericanHyphenator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SyllableSplitter {

    public static void main(String[] args) throws IOException {
        //If help is required, print help message:
        if(args.length>0){
            System.out.println("\n\t- This application splits a word into syllables.");
            System.out.println("\t- To start it, run:\n");
            System.out.println("\t\tjava -jar SyllableSplitter.jar\n");
            System.out.println("\t- To close it, input a blank line.");
            System.out.println("\t- Input:\n");
            System.out.println("\t\t<word>\n");
            System.out.println("\t- Output:\n");
            System.out.println("\t\t<syllables>");
            return;
        }
        
        //Create reader of stdin:
        InputStream is = System.in;
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));
        
        //Create the hyphenator:
        AmericanHyphenator a = new AmericanHyphenator();
        
        //While not terminated, read a word:
        boolean flag = false;
        while (!flag) {
            String word = bis.readLine().trim();

            if (word.length() == 0) {
                return;
            }

            try {
                //Generate syllables:
                String syllables = a.hyphenate(word);

                //Output syllables:
                System.out.println(syllables);
            } catch (Exception e) {
                System.out.println("1");
            }
        }
    }
}
