import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static ArrayList<Integer> color;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> topsort;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<String> names = new ArrayList<>(n);
        color = new ArrayList<>(Collections.nCopies(26, 0));
        graph = new ArrayList<>();
        topsort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            names.add(line);
        }

        boolean flag = initGr(names);
        for (int i = 0; i < 26; i++) {
            if (color.get(i) == 0) flag |= dfs(i);
        }
        Collections.reverse(topsort);
        if (flag == true) {
            System.out.print("Impossible");
        } else {
            for (int x : topsort) {
                System.out.print((char) (x + 'a'));
                //System.out.print(" ");
            }
        }
    }

    static boolean check(String a, String b) {
        int n = Math.min(a.length(), b.length());
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                flag = false;
                graph.get(a.charAt(i) - 'a').add(b.charAt(i) - 'a');
                break;
            }
        }
        return flag && (a.length() > b.length());
    }

    static boolean initGr(ArrayList<String> names) {
        for (int i = 0; i < 26; i++) graph.add(new ArrayList<>());
        int n = names.size();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String a = names.get(i);
                String b = names.get(j);
                flag |= check(a, b);
            }
        }
        return flag;
    }

    static boolean dfs(int v) {
        color.set(v, 1);
        for (int u : graph.get(v)) {
            if (color.get(u) == 1 || color.get(u) == 0 && dfs(u)) return true;
        }
        color.set(v, 2);
        topsort.add(v);
        return false;
    }
}
