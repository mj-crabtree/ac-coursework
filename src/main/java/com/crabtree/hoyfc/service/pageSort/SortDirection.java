package com.crabtree.hoyfc.service.pageSort;

public enum SortDirection {
	ASC("ASC"), DESC("DESC");
	private String direction;
	SortDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
}