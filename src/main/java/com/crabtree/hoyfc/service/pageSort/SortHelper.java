package com.crabtree.hoyfc.service.pageSort;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class SortHelper {
	private String sortColumn;
	private SortDirection sortDirection;

	public SortHelper() {
		this.sortColumn = "id";
		this.sortDirection = SortDirection.DESC;
	}

	public Boolean isAscending() {
		return this.sortDirection == SortDirection.ASC;
	}

	public Boolean isDescending() {
		return this.sortDirection == SortDirection.DESC;
	}
}