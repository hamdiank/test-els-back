package com.els.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.els.entities.Employee;

public class Utils {
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;

	}

	public static String getValues(Employee e, String criteria) {

		switch (criteria) {
		case "firstName":
			return e.getFirstName();
		case "lastName":
			return e.getLastName();
		default:
			return e.getLastName();
		}
	}

}
