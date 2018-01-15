package com.lcworld.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService<T> {
	void exportExcel(DualHashBidiMap titleMapping, List<T> objectList,
			HttpServletResponse response);

	List<T> importExcel(Class<T> t, DualHashBidiMap titleMapping,
			MultipartFile file);
}
