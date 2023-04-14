package aoa.guessers;

import aoa.utils.FileUtils;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.xmlresolver.catalog.entry.Entry;

import java.util.*;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    public Map patterncharnumber(String pattern) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < pattern.length(); i++) {
            char alpha = pattern.charAt(i);

            if (map.containsKey(alpha)) {
                int totalcount = map.get(alpha);
                map.put(alpha, totalcount + 1);
            }
            if (!map.containsKey(alpha)) {
                map.put(alpha, count);
            }
        }
        return map;
    }


    public List guessnotinpattern(String pattern, List<Character> guesses) {
        List notinpattern = new ArrayList();
        for (Character letter : guesses) {
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) == letter) {
                    break;
                }
                if ((i == pattern.length() - 1 && pattern.charAt(i) == '-') || (i == pattern.length() - 1 && pattern.charAt(i) != letter)) {
                    notinpattern.add(letter);
                }
                if (pattern.charAt(i) != '-' && pattern.charAt(i) != letter) {
                }
            }
        }
        return notinpattern;
    }

//    public List guessinpatternmismatch(String pattern, List<String> words) {
//        List<String> returnwords = new ArrayList<>();
//        for (String word : words) {
//            int count = 0;
//            Map<Character, Integer> lettercount = new HashMap<>();
//            for (int i = 0; i < word.length(); i++) {
//                if (letterinpattern(pattern, word.charAt(i))) {
//                    lettercount.put(word.charAt(i), count + 1);
//                }
//            }
//        }
//        return words;
//    }

//    public List keepOnlyWordsThatMatchPattern(List<String> wordlist, String pattern) {
//        List<String> patternwords = new ArrayList<>();
//        for (String word : wordlist) {
//            if (word.length() == pattern.length()) for (int i = 0; i < word.length(); i++) {
//                if (pattern.charAt(i) == '-') {
//                }
//                if (word.charAt(i) != pattern.charAt(i) && pattern.charAt(i) != '-') {
//                    break;
//                }
//                if (i == word.length() - 1) {
//                    patternwords.add(word);
//                }
//            }
//
//        }
//        return patternwords;
//    }

    public List updatedwordslist(List<Character> letternotinpattern) {
        List<String> newlist = new ArrayList<>();
        for (String word : words) {
            for (Character letter : letternotinpattern) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        newlist.add(word);
                    }
                }
            }
        }
        return newlist;
    }


    public List guessnotinword(List<String> letternotinword, List<Character> letters) {

        List<String> correctguessnotinword = new ArrayList<>();
        for (Character letter : letters) {
            for (String word : letternotinword) {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        break;
                    }
                    if (i == word.length() - 1 && word.charAt(i) != letter) {
                        correctguessnotinword.add(word);
                    }
                }
            }
        }
        return correctguessnotinword;
    }


    public List letterpatternlist(List<Character> guesses, String pattern) {
        List<Character> patternlist = new ArrayList<>();
        for (Character letter : guesses) {
            if (letterinpattern(pattern, letter)) {
                patternlist.add(letter);
            }
        }
        return patternlist;
    }

    public boolean letterinpattern(String pattern, char letter) {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == letter) {
                return true;
            }
        }
        return false;
    }


//    public Map<Character, Integer> getFrequencyMap(List<String> upatedlist) {
//        // TODO: Fill in this method.
//        Map<Character, Integer> map = new HashMap<>();
//        int count = 1;
//        for (int word = 0; word < upatedlist.size(); word++) {
//            String elem = upatedlist.get(word);
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
//
//    public Map<Character, Integer> sortMap(Map<Character, Integer> map) {
//        TreeMap<Character, Integer> sorted = new TreeMap<>();
//        sorted.putAll(map);
//        List<Map.Entry<Character, Integer>> list = new LinkedList<Map.Entry<Character, Integer>>(sorted.entrySet());
//
//        // Sort the list
//        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
//            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
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

    public List removewords(List<String> words, List<String> updatedlist) {
        List<String> newlist = new ArrayList<>();
        for (String word : updatedlist) {
            if (!words.contains(word)) {
                newlist.add(word);
            }
        }
        return newlist;
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */ public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        List letternotinpattern = guessnotinpattern(pattern, guesses);
        List newlist = updatedwordslist(letternotinpattern);
        List<String> updatedlist = new ArrayList<>();
        for (String word : words) {
            if (!newlist.contains(word)) {
                updatedlist.add(word);
            }
        }
        List<Character> guessinpattern = letterpatternlist(guesses, pattern);
        List<String> matchingwords = guessnotinword(updatedlist, guessinpattern);
        List<String> newupdatedlist = new ArrayList<>();
        for (String wors : updatedlist) {
            if (!matchingwords.contains(wors)) {
                newupdatedlist.add(wors);
            }
        }
        List<String> newwordlist = new ArrayList<>();
        Map<Character, Integer> patternmap = patterncharnumber(pattern);
        for (String word : newupdatedlist) {
            Map<Character, Integer> wordmap = patterncharnumber(word);
            for (Map.Entry<Character, Integer> mapElement : wordmap.entrySet()) {
                Character key = mapElement.getKey();
                if (patternmap.containsKey(key)) {
                    if (patternmap.get(key) != wordmap.get(key)) {
                        newwordlist.add(word);
                    }
                }
            }
        }
        List<String> removewords = removewords(newwordlist, newupdatedlist);
        List<String> newwords = LFGHelper.keepOnlyWordsThatMatchPattern(removewords, pattern);
        Map<Character, Integer> map = LFGHelper.getFrequencyMap(newwords);
        Map<Character, Integer> sortmap = LFGHelper.sortMap(map);
        for (Map.Entry<Character, Integer> entry : sortmap.entrySet()) {
            char letter = entry.getKey();
            if (!letterinpattern(pattern, letter)) {
                // Print the key with max value
                return entry.getKey();
            }
        }
        return '?';
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("----", List.of('e')));
    }
}
