package com.lcworld.service;

import java.util.List;

import com.lcworld.utils.Query;

public interface ICollectionService<T> {
	 List<T> queryFavorList(Query params);
}
