package com.poc.BookStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCatXrefDto {

    private Integer bookId;

    private Integer categoryId;

}
