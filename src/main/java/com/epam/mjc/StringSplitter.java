package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        try{
            String[] splitted = source.split(delimiters.iterator().next());
            StringBuilder builder = new StringBuilder();
            for (String s : splitted) {
                builder.append(s);
            }
            StringTokenizer tokenizer = new StringTokenizer(source,builder.toString());
            List<String> result = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                result.add(tokenizer.nextToken());
            }
            return result;
        } catch(UnsupportedOperationException e){
            throw new UnsupportedOperationException("You should implement this method.");
        }

    }
}
