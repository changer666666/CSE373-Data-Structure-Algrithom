package autocomplete;

public class Term implements Comparable<Term> {
    String query;
    long weight;

    /**
     * Initializes a term with the given query string and weight.
     * @throws IllegalArgumentException if query is null or weight is negative
     */
    public Term(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException("query is null or weight is negative!");
        }
        this.query = query;
        this.weight = weight;
    }

    /**
     * Compares the two terms in lexicographic order by query.
     * @throws NullPointerException if the specified object is null
     */
    public int compareTo(Term that) {
        if (that == null) {
            throw new NullPointerException("The specified object is null!");
        }
        if (this.query.length() == 0 && that.query.length() == 0) {
            return 0;
        }
        if (this.query.length() == 0) {
            return -1;
        }
        if (that.query.length() == 0) {
            return 1;
        }

        int index = 0;
        while (this.query.length() > index && that.query.length() > index) {
            if (this.query.charAt(index) < that.query.charAt(index)) {
                return -1;
            } else if (this.query.charAt(index) > that.query.charAt(index)) {
                return 1;
            } else {
                index++;
            }
        }
        if (this.query.length() == index && that.query.length() == index) {
            return 0;
        }
        if (this.query.length() == index) {
            return -1;
        } else {
            return 1;
        }
    }

    /** Compares to another term, in descending order by weight. */
    public int compareToByReverseWeightOrder(Term that) {
        if (that == null) {
            throw new NullPointerException("The specified object is null!");
        }
        return Long.compare(that.weight, this.weight);
    }

    /**
     * Compares to another term in lexicographic order, but using only the first r characters
     * of each query. If r is greater than the length of any term's query, compares using the
     * term's full query.
     * @throws IllegalArgumentException if r < 0
     */
    public int compareToByPrefixOrder(Term that, int r) {
        if (that == null || r < 0) {
            throw new IllegalArgumentException("The specified object is null or r < 0!");
        }
        if (r > this.query.length() || r > that.query.length()) {
            return this.compareTo(that);
        }
        Term thisNewTerm = new Term(this.query.substring(0, r), this.weight);
        Term thatNewTerm = new Term(that.query.substring(0, r), that.weight);
        return thisNewTerm.compareTo(thatNewTerm);
    }

    /** Returns this term's query. */
    public String query() {
        return this.query;
    }

    /**
     * Returns the first r characters of this query.
     * If r is greater than the length of the query, returns the entire query.
     * @throws IllegalArgumentException if r < 0
     */
    public String queryPrefix(int r) {
        if (r < 0) {
            throw new NullPointerException("r < 0!");
        }
        return this.query.substring(0, r);
    }

    /** Returns this term's weight. */
    public long weight() {
        return this.weight;
    }
}
