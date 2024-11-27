import React from 'react'
import {
    Pagination,
    PaginationContent,
    PaginationItem,
    PaginationLink,
    PaginationNext,
    PaginationPrevious,
} from "@/components/ui/pagination"

interface PaginatedListProps<T> {
    items: T[]
    itemsPerPage: number
    currentPage: number
    setCurrentPage: (page: number) => void
    renderItem: (item: T) => React.ReactNode
    noItemsMessage: string
}

export function PaginatedList<T>({
    items,
    itemsPerPage,
    currentPage,
    setCurrentPage,
    renderItem,
    noItemsMessage
}: PaginatedListProps<T>) {
    const totalPages = Math.ceil(items.length / itemsPerPage)
    const startIndex = (currentPage - 1) * itemsPerPage
    const paginatedItems = items.slice(startIndex, startIndex + itemsPerPage)

    return (
        <>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-8">
                {paginatedItems.map(renderItem)}
            </div>
            {items.length > itemsPerPage && (
                <Pagination>
                    <PaginationContent>
                        <PaginationItem>
                            <PaginationPrevious
                                href="#"
                                onClick={() => setCurrentPage(Math.max(currentPage - 1, 1))}
                                aria-disabled={currentPage === 1}
                            />
                        </PaginationItem>
                        {[...Array(totalPages)].map((_, i) => (
                            <PaginationItem key={i}>
                                <PaginationLink
                                    href="#"
                                    onClick={() => setCurrentPage(i + 1)}
                                    isActive={currentPage === i + 1}
                                >
                                    {i + 1}
                                </PaginationLink>
                            </PaginationItem>
                        ))}
                        <PaginationItem>
                            <PaginationNext
                                href="#"
                                onClick={() => setCurrentPage(Math.min(currentPage + 1, totalPages))}
                                aria-disabled={currentPage === totalPages}
                            />
                        </PaginationItem>
                    </PaginationContent>
                </Pagination>
            )}
            {items.length === 0 && (
                <p className="text-center text-muted-foreground mt-8">{noItemsMessage}</p>
            )}
        </>
    )
}
