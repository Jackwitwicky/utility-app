package com.gizahackathon.utilitiesapp.page

import kotlin.math.ceil

/**
 * Container for paginated data.
 * @param currentPage The first page is 0
 */
class Page<T>(val content: List<T>, val currentPage: Int, val totalItems: Long, itemsPerPage: Int) {

    companion object {
        /**
         * Default pagination size
         */
        const val DEFAULT_PAGE_SIZE = 20
    }

    /**
     * total number of pages. Always a value greater than or equal to 1
     */
    val totalPages =
        if (totalItems.toInt() == 0) 1 else ceil((totalItems.toDouble() / itemsPerPage.toDouble())).toInt()

    /**
     * The current page number. First page = 1
     */
    val pageNumber: Int get() = currentPage + 1

    /**
     * Whether this is the last page
     */
    val isLast = pageNumber == totalPages
}