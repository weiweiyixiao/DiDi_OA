import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class wordAnagram {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static Trie trieDictionary = new Trie();

    public static void main(String[] args) throws Exception {
        System.out.println("Testing 1 - Send Http GET request");
        buildTriefromDictionary();
        String test1 = "nnogreen";
        String test2 = "grene";
        System.out.println(findWord(test1));
        System.out.println(findWord(test2));
    }

    // HTTP GET request
    private static void buildTriefromDictionary() throws Exception {

        String url = "https://raw.githubusercontent.com/lad/words/master/words";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            trieDictionary.insert(inputLine.toLowerCase().replaceAll("[^a-z]", ""));
        }
        in.close();
    }

    private static List<String> findWord(String s) {
        List<String> input = inputPermutation.permute(s);
        //System.out.println(input);
        List<String> result = new ArrayList<>();
        for (String str : input) {
            if(trieDictionary.search(str)) {
                result.add(str);
            }
        }
        return result;
    }
}
