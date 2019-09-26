import java.io.*;
import java.util.*;

class inputPermutation {
    public static List<String> permute(String s) {
        List<String> results = new ArrayList<>();
        char[] input = s.toCharArray();
        Arrays.sort(input);
        boolean[] visited = new boolean[input.length];
        StringBuilder sb = new StringBuilder();
        dfs(input, visited, sb, results);
        return results;
    }

    private static void dfs(char[] input, boolean[] visited, StringBuilder sb, List<String> results) {
        //base
        if (sb.length() == input.length) {
            results.add(sb.toString());
            return;
        }
        //general
        for (int i = 0; i < input.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i > 0 && input[i] == input[i - 1] && !visited[i] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            sb.append(input[i]);
            dfs(input, visited, sb, results);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
