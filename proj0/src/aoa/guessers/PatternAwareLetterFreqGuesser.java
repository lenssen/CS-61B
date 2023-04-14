package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;
import net.sf.saxon.expr.Component;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.xmlresolver.catalog.entry.Entry;

import java.util.*;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }


//    public List keepOnlyWordsThatMatchPattern(List wordlist, String pattern) {
//        List<String> patternwords = new ArrayList<>();
//        for (String word : words) {
//            if (word.length() == pattern.length())
//                for (int i = 0; i < word.length(); i++) {
//                    if (pattern.charAt(i) == '-') {
//                    }
//                    if (word.charAt(i) != pattern.charAt(i) && pattern.charAt(i) != '-') {
//                        break;
//                    }
//                    if (i == word.length() - 1) {
//                        patternwords.add(word);
//                    }
//                }
//
//        }
//        return patternwords;
//    }

//    public Map<Character, Integer> getFrequencyMap(List updatedList) {
//        // TODO: Fill in this method.
//        Map<Character, Integer> map = new HashMap<>();
//        int count = 1;
//        for (int word = 0; word < updatedList.size(); word++) {
//            String elem = (String) updatedList.get(word);
//            for (int i = 0; i < elem.length(); i++) {
//                char a = elem.charAt(i);
//                if (map.containsKey(a)) {
//                    int totalcount = map.get(a);
//                    map.put(a, totalcount + 1);
//                }
//                if (!map.containsKey(a)) {
//                    map.put(a, count);
//                }
//            }
//        }
//        return map;
//    }

//    public Map<Character, Integer> sortMap(Map<Character, Integer> map){
//        TreeMap<Character, Integer> sorted = new TreeMap<>();
//        sorted.putAll(map);
//        List<Map.Entry<Character, Integer> > list =
//                new LinkedList<Map.Entry<Character, Integer> >(sorted.entrySet());
//
//        // Sort the list
//        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
//            public int compare(Map.Entry<Character, Integer> o1,
//                               Map.Entry<Character, Integer> o2)
//            {
//                return (o2.getValue()).compareTo(o1.getValue());
//            }
//        });
//
//        // put data from sorted list to hashmap
//        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
//        for (Map.Entry<Character, Integer> aa : list) {
//            temp.put(aa.getKey(), aa.getValue());
//        }
//        return temp;
//    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List updated = LFGHelper.keepOnlyWordsThatMatchPattern(words, pattern);
        Map<Character, Integer> map = LFGHelper.getFrequencyMap(updated);
        Map<Character, Integer> sortedmap = LFGHelper.sortMap(map);
        // Iterate through HashMap
        for (Map.Entry<Character, Integer> entry :
                sortedmap.entrySet()) {
            if (!guesses.contains(entry.getKey())) {
                // Print the key with max value
                return entry.getKey();
            }
        }
        return '?';
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}