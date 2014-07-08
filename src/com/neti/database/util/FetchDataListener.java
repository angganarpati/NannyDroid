package com.neti.database.util;

import java.util.List;

import com.neti.model.Ranking;

public interface FetchDataListener {
    public void onFetchComplete(List<Ranking> data);
    public void onFetchFailure(String msg);
}
