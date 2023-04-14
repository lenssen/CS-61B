package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /**
     * Returns a map from a given letter to its frequency across all words.
     * This task is similar to something you did in hw0b!
     */
    public Map<Character, Integer> getFrequencyMap() {
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

    /**
     * Returns the most common letter in WORDS that has not yet been guessed
     * (and therefore isn't present in GUESSES).
     */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> map = LFGHelper.getFrequencyMap(words);
        TreeMap<Character, Integer> sorted = new TreeMap<>(Collections.reverseOrder());
        sorted.putAll(map);
        List<Map.Entry<Character, Integer>> list =
                new LinkedList<>((sorted.entrySet()));
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        for ( int i = list.size() -1 ; i > 0; i-- ) {
            char a = list.get(i).getKey();
            if(!guesses.contains(a)){
            return a;
        }}
        return 'a';
    }


    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
