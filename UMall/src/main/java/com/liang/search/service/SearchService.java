package com.liang.search.service;


import com.liang.common.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String keyword, int page, int size, String sort, int priceGt, int priceLte);
}
