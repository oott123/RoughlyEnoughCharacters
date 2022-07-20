package com.oott123.rechars.stubs;

import com.oott123.rechars.helpers.MatchHelper;
import me.towdium.pinin.searchers.Searcher;
import me.towdium.pinin.searchers.TreeSearcher;
import mezz.jei.core.search.suffixtree.GeneralizedSuffixTree;

import java.util.Set;

@SuppressWarnings("unused")
public class FakeTree<T> extends GeneralizedSuffixTree<T> {

    TreeSearcher<T> tree = new TreeSearcher<>(Searcher.Logic.CONTAIN, MatchHelper.context);

    @Override
    public void getSearchResults(String word, Set<T> results) {
        results.addAll(tree.search(word));
    }

    @Override
    public void put(String key, T value) {
        tree.put(key, value);
    }

    @Override
    public void getAllElements(Set<T> results) {
        results.addAll(tree.search(""));
    }

}
