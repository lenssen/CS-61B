import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> map = new HashMap<>();
        char al;
        int i = 1;
        for(al = 'a'; al <= 'z'; al ++){
            map.put(al, i);
            i++;
        }
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer elem:nums){
            map.put(elem, elem * elem);
        }
        return map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < words.size(); i++) {
            int count = 1;
                String word = words.get(i);
                if (!map.containsKey(word)) {
                    for (int j = i + 1; j < words.size(); j++) {
                        String nextword = words.get(j);
                        if (word.equals(words.get(j))) {
                            count += 1;
                            map.put(word, count);
                        }
                    }
                    map.put(word, count);
                }
            }
        return map;
    }
}
