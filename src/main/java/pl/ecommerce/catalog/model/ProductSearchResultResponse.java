package pl.ecommerce.catalog.model;

import pl.ecommerce.product.model.dto.ProductResponse;

import java.util.List;

public record ProductSearchResultResponse(List<ProductResponse> content,
                                          Integer totalPages,
                                          Long totalElements,
                                          Integer size,
                                          Integer number) {
}
