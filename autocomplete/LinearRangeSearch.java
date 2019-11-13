package autocomplete;

import java.util.ArrayList;
import java.util.Iterator;

public class LinearRangeSearch implements Autocomplete {
    private ArrayList<Term> arrayList = new ArrayList<>();

    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly store and mutate it).
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public LinearRangeSearch(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException("terms is null or contains null!");
        }
        for (Term term : terms) {
            if (term == null) {
                throw new IllegalArgumentException("terms is null or contains null!");
            }
            arrayList.add(term);
        }
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public Term[] allMatches(String prefix) {
        ArrayList<Term> matchTerms = new ArrayList<>();
        Iterator i = arrayList.iterator();
        while (i.hasNext()) {
            Term next = (Term) i.next();
            if (next.query.length() >= prefix.length()) {
                if (next.query.substring(0, prefix.length()).equals(prefix)) {
                    matchTerms.add(next);
                }
            }
            i.remove();
        }
        matchTerms.sort(TermComparators.byReverseWeightOrder());
        Term[] termArray = new Term[0];
        return matchTerms.toArray(termArray);
    }
}

