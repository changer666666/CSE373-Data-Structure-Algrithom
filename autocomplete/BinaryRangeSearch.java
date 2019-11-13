package autocomplete;


public class BinaryRangeSearch implements Autocomplete {
    private Term[] terms;
    private Term[] prefixTerms = null;

    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate it).
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public BinaryRangeSearch(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException("terms is null!");
        }
        for (Term term : terms) {
            if (term == null) {
                throw new IllegalArgumentException("terms contains null!");
            }
        }
        this.terms = terms;
        mergeSortTerms(0, terms.length - 1);
    }

    private void mergeSortTerms(int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSortTerms(low, mid);
        mergeSortTerms(mid + 1, high);
        merge(low, mid, high);
    }

    private void merge(int low, int mid, int high) {
        Term[] temp = new Term[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (terms[i] == null || terms[j] == null) {
                throw new IllegalArgumentException("terms contains null!");
            }
            if (terms[i].compareTo(terms[j]) <= 0) {
                temp[k++] = terms[i++];
            } else {
                temp[k++] = terms[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = terms[i++];
        }
        while (j <= high) {
            temp[k++] = terms[j++];
        }
        for (i = 0; i < temp.length; i++) {
            terms[i + low] = temp[i];
        }
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("prefix is null!");
        }
        int start = binarySearchStart(prefix);
        int end = binarySearchEnd(prefix);
        prefixTerms = new Term[end - start + 1];
        int index = 0;
        for (int i = start; i <= end; i++) {
            prefixTerms[index++] = terms[i];
        }
        mergeSortTermsByWeight(0, prefixTerms.length - 1);

        return prefixTerms;
    }

    private void mergeSortTermsByWeight(int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSortTermsByWeight(start, mid);
        mergeSortTermsByWeight(mid + 1, end);
        mergeByWeight(start, mid, end);
    }

    private void mergeByWeight(int start, int mid, int end) {
        Term[] temp = new Term[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (prefixTerms[i].compareToByReverseWeightOrder(prefixTerms[j]) == -1) {
                temp[k++] = prefixTerms[i++];
            } else {
                temp[k++] = prefixTerms[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = prefixTerms[i++];
        }
        while (j <= end) {
            temp[k++] = prefixTerms[j++];
        }
        for (i = 0; i < temp.length; i++) {
            prefixTerms[i + start] = temp[i];
        }
    }

    private int binarySearchStart(String prefix) {
        int low = 0;
        int high = terms.length - 1;
        int mid;
        Term prefixTerm = new Term(prefix, 0);
        while (low <= high) {
            mid = (low + high) / 2;
            if (terms[mid].compareToByPrefixOrder(prefixTerm, prefix.length()) == -1) {
                low = mid + 1;
            } else {
                if (mid == 0) {
                    return mid;
                }
                if (terms[mid - 1].compareToByPrefixOrder(prefixTerm, prefix.length()) == -1) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    private int binarySearchEnd(String prefix) {
        int low = 0;
        int high = terms.length - 1;
        int mid;
        Term prefixTerm = new Term(prefix, 0);
        while (low <= high) {
            mid = (low + high) / 2;
            if (terms[mid].compareToByPrefixOrder(prefixTerm, prefix.length()) == 1) {
                high = mid - 1;
            } else {
                if (mid == high) {
                    return mid;
                }
                if (terms[mid + 1].compareToByPrefixOrder(prefixTerm, prefix.length()) == 1) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
