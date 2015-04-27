package adjectiveinflector;

import edu.northwestern.at.morphadorner.corpuslinguistics.hyphenator.EnglishHyphenator;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class EnglishAdjectiveInflector {
    
    private final EnglishHyphenator a;
    private final String separator;

    public EnglishAdjectiveInflector() {
        this.a = new EnglishHyphenator();
        this.separator = Character.toString(Character.toChars(173)[0]);
    }
    
    public String inflect(String adj, String mode) throws UnsupportedEncodingException{
        //Get number of syllables:
        String hyphenized = this.a.hyphenate(adj);
        StringTokenizer st = new StringTokenizer(hyphenized, this.separator);
        int syllables = st.countTokens();

        //Inflect adjective:
        String inflected_adjective = null;
        switch (mode) {
            case "comparative":
                inflected_adjective = this.toComparative(adj, syllables);
                break;
            case "superlative":
                inflected_adjective = this.toSuperlative(adj, syllables);
                break;
            default:
                inflected_adjective = adj;
                break;
        }
        
        //Return inflected adjective:
        return inflected_adjective;
    }

    private String toComparative(String adj, int syllables) {
        //Create result:
        String result = null;
        
        //Create inflection:
        if (syllables==1){
            if (adj.charAt(adj.length()-1)=='e'){
                result = adj + "r";
            }else if(adj.charAt(adj.length()-1)=='y'){
                result = adj.substring(0, adj.length()-1) + "ier";
            }else{
                result = adj + "er";
            }
        }else if (syllables==2){
            if (adj.charAt(adj.length()-1)=='y'){
                result = adj.substring(0, adj.length()-1) + "ier";
            }else{
                result = "more " + adj;
            }
        }else{
            result = "more " + adj;
        }
        
        //Return result:
        return result;
    }

    private String toSuperlative(String adj, int syllables) {
        //Create result:
        String result = null;
        
        //Create inflection:
        if (syllables==1){
            if (adj.charAt(adj.length()-1)=='e'){
                result = adj + "st";
            }else if(adj.charAt(adj.length()-1)=='y'){
                result = adj.substring(0, adj.length()-1) + "iest";
            }else{
                result = adj + "est";
            }
        }else if (syllables==2){
            if (adj.charAt(adj.length()-1)=='y'){
                result = adj.substring(0, adj.length()-1) + "iest";
            }else{
                result = "most " + adj;
            }
        }else{
            result = "most " + adj;
        }
        
        //Return result:
        return result;
    }
    
}
