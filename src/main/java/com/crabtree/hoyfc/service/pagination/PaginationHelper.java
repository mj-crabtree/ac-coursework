package com.crabtree.hoyfc.service.pagination;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.baseEntity.BaseEntity;
import lombok.Data;

@Data
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
	private Integer toPosition;
	private DynamicArrayList<? extends BaseEntity> collection;

	public <T extends BaseEntity> PaginationHelper paginateCollection(DynamicArrayList<T> collection, Integer totalItems, Integer limit, Integer pageNumber) {
		PaginationHelper paginationHelper = new PaginationHelper();

		paginationHelper.setTotalItems(totalItems);
		paginationHelper.setTotalPages(((totalItems % limit) == 0) ? (totalItems / limit) : ((totalItems / limit) + 1));
		paginationHelper.setPageNumber(pageNumber);
		paginationHelper.setLimit(limit);
		paginationHelper.setPageOffset(limit);
		paginationHelper.setFromPosition(limit * (pageNumber - 1));


		Integer toPosition = 0;

		if (paginationHelper.getFromPosition() + paginationHelper.getLimit() >= paginationHelper.getTotalItems()) {
			paginationHelper.setToPosition(paginationHelper.getTotalItems());
		}
		else {
			paginationHelper.setToPosition(paginationHelper.getFromPosition() + paginationHelper.getLimit());
		}

		if (paginationHelper.getFromPosition() > paginationHelper.getToPosition()) {
			this.collection = null;
		}
		else {
			this.collection = collection.subList(paginationHelper.getFromPosition(), paginationHelper.getToPosition());
		}

		paginationHelper.setCollection(this.collection);
		determinePaginationAttributes(pageNumber, paginationHelper, paginationHelper.getTotalPages());

		return paginationHelper;
	}

	private void determinePaginationAttributes(Integer pageNumber, PaginationHelper paginationHelper, Integer totalPages) {
		if (pageNumber > 1 && pageNumber < totalPages) {

			// in-between the first and last pages
			paginationHelper.setIsFirstPage(false);
			paginationHelper.setIsLastPage(false);
			paginationHelper.setHasPreviousPage(true);
			paginationHelper.setHasNextPage(true);
			paginationHelper.setNextPage(pageNumber + 1);
			paginationHelper.setPreviousPage(pageNumber - 1);
		}
		else if (pageNumber == totalPages && pageNumber != 1) {

			// on the last page of data
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
			paginationHelper.setHasPreviousPage(false);
			paginationHelper.setNextPage(pageNumber + 1);
		}
		else if (pageNumber.equals(totalPages) && totalPages == 1) {

			// single page of data
			paginationHelper.setIsFirstPage(true);
			paginationHelper.setIsLastPage(true);
			paginationHelper.setHasNextPage(false);
			paginationHelper.setHasPreviousPage(false);
		}
	}

}