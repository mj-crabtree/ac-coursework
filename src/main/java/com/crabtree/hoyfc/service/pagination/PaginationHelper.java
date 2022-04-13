package com.crabtree.hoyfc.service.pagination;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.baseEntity.BaseEntity;

public class PaginationHelper {

	private Integer totalItems;
	private Integer totalPages;
	private Integer limit;
	private Integer pageNumber;
	private Integer pageOffset;
	private Boolean isFirstPage;
	private Boolean isLastPage;
	private Integer nextPage;
	private Integer previousPage;
	private Boolean hasNextPage;
	private Boolean hasPreviousPage;
	private Integer fromPosition;
	private DynamicArrayList<? extends BaseEntity> collection;

	public <T extends BaseEntity> PaginationHelper paginationHelper(DynamicArrayList<T> collection, Integer totalItems, Integer limit, Integer pageNumber) {
		PaginationHelper paginationHelper = new PaginationHelper();

		paginationHelper.setTotalItems(totalItems);
		paginationHelper.setTotalPages(((totalItems % limit) == 0) ? (totalItems / limit) : ((totalItems / limit) + 1));
		paginationHelper.setPageNumber(pageNumber);
		paginationHelper.setLimit(limit);
		paginationHelper.setPageOffset(limit);
		paginationHelper.setFromPosition(limit * (pageNumber - 1));


		Integer toPosition = 0;

		if (paginationHelper.getFromPosition() + paginationHelper.getLimit() >= paginationHelper.getTotalItems()) {
			toPosition = paginationHelper.getTotalItems();
		}
		else {
			toPosition = paginationHelper.getFromPosition() + paginationHelper.getLimit();
		}

		if (paginationHelper.getFromPosition() > toPosition) {
			this.collection = null;
		}
		else {
			this.collection = collection.subList(paginationHelper.getFromPosition(), toPosition);
		}

		paginationHelper.setCollection(this.collection);

		determinePaginationAttributes(pageNumber, paginationHelper, paginationHelper.getTotalPages());

		return paginationHelper;
	}

	private void determinePaginationAttributes(Integer pageNumber, PaginationHelper paginationHelper, Integer totalPages) {
		if (pageNumber > 1 && pageNumber < totalPages) {

			// in-between the first and last pages
			paginationHelper.setIsFirstPage(false);
			paginationHelper.setHasPreviousPage(true);
			paginationHelper.setNextPage(pageNumber + 1);
			paginationHelper.setPreviousPage(pageNumber - 1);
		}
		else if (pageNumber.equals(totalPages) && pageNumber != 1) {

			// on the last page of data
			isLastPage = true;
			paginationHelper.setIsLastPage(true);
			paginationHelper.setIsFirstPage(false);
			paginationHelper.setHasNextPage(false);
			paginationHelper.setHasPreviousPage(true);
			paginationHelper.setPreviousPage(pageNumber - 1);
		}
		else if (pageNumber == 1 && !pageNumber.equals(totalPages)) {

			// on the first page of data
			paginationHelper.setIsLastPage(false);
			paginationHelper.setHasNextPage(true);
			paginationHelper.setIsFirstPage(true);
			paginationHelper.setNextPage(pageNumber + 1);
		}
		else if (pageNumber.equals(totalPages) && totalPages == 1) {

			// only one page of data
			paginationHelper.setIsFirstPage(true);
			paginationHelper.setIsLastPage(true);
			paginationHelper.setHasNextPage(false);
			paginationHelper.setHasPreviousPage(false);
		}
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(Integer previousPage) {
		this.previousPage = previousPage;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public Boolean getHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(Boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public Integer getFromPosition() {
		return fromPosition;
	}

	public void setFromPosition(Integer fromPosition) {
		this.fromPosition = fromPosition;
	}

	public DynamicArrayList<? extends BaseEntity> getCollection() {
		return collection;
	}

	public void setCollection(DynamicArrayList<? extends BaseEntity> collection) {
		this.collection = collection;
	}

	public Boolean getIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(Boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public Boolean getIsFirstPage() {
		return isFirstPage;
	}

	public void setIsFirstPage(Boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
}