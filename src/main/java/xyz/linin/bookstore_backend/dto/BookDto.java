package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class BookDto {

    @ApiModelProperty("id")
    private Integer id;

    @NotBlank
    @ApiModelProperty("ISBN")
    private String isbn;

    @NotBlank
    @ApiModelProperty("书名")
    private String name;

    @NotBlank
    @ApiModelProperty("类型")
    private String type;

    @NotBlank
    @ApiModelProperty("作者")
    private String author;

    @NotBlank
    @ApiModelProperty("价格")
    private Double price;

    @NotBlank
    @ApiModelProperty("描述")
    private String description;

    @NotBlank
    @ApiModelProperty("库存")
    private Integer inventory;

    @NotBlank
    @ApiModelProperty("图片")
    private String image;
}
