package aoa.guessers;

import java.util.*;

public class LFGHelper {

    public static Map<Character, Integer> getFrequencyMap(List<String> words) {
        // TODO: Fill in this method.
        Map<Character, Integer> map = new HashMap<>();
        int count = 1;
        for (int word = 0; word < words.size(); word++) {
            String elem = words.get(word);
            for (int i = 0; i < elem.length(); i++) {
                char a = elem.charAt(i);
                if (map.containsKey(a)) {
                    int totalcount = map.get(a);
                    map.put(a, totalcount + 1);
                }
                if (!map.containsKey(a)) {
                    map.put(a, count);
                }
            }
        }
        return map;
    }

    public static Map<Character, Integer> sortMap(Map<Character, Integer> map) {
        TreeMap<Character, Integer> sorted = new TreeMap<>();
        sorted.putAll(map);
        List<Map.Entry<Character, Integer>> list = new LinkedList<Map.Entry<Character, Integer>>(sorted.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
    public static List keepOnlyWordsThatMatchPattern(List<String> wordlist, String pattern) {
        List<String> patternwords = new ArrayList<>();
        for (String word : wordlist) {
            if (word.length() == pattern.length()) for (int i = 0; i < word.length(); i++) {
                if (pattern.charAt(i) == '-') {
                }
                if (word.charAt(i) != pattern.charAt(i) && pattern.charAt(i) != '-') {
                    break;
                }
                if (i == word.length() - 1) {
                    patternwords.add(word);
                }
            }

        }
        return patternwords;
    }


}
