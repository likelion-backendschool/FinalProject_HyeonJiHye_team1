package com.ll.exam.final__mutbooks_v3.app.product.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductForm {
    @NotBlank
    private String subject;
    @NotNull
    private int price;
    @NotNull
    private Long postKeywordId;
    @NotBlank
    private String productTagContents;
}
