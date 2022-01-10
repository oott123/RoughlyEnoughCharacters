package com.oott123.rechars.stubs;

import com.oott123.rechars.ReChars;
import com.oott123.rechars.helpers.MatchHelper;
import me.towdium.pinin.searchers.Searcher;
import me.towdium.pinin.searchers.TreeSearcher;
import net.minecraft.client.search.SuffixArray;

import java.util.List;

public class RecSuffixArray<T> extends SuffixArray<T> {
    private TreeSearcher<T> searcher = new TreeSearcher<>(Searcher.Logic.CONTAIN, MatchHelper.context);

    @Override
    public void add(T object, String text) {
        searcher.put(text, object);
    }

    @Override
    public void build() {
        // do nothing
    }

    @Override
    public List<T> findAll(String text) {
        var result = searcher.search(text);
        ReChars.LOGGER.debug(String.format("Searching %s using SuffixArray, matched: %d", text, result.size()));
        return result;
    }
}
