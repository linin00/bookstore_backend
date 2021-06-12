package xyz.linin.bookstore_backend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
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

    @ApiModelProperty("价格")
    private Integer price;

    @NotBlank
    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("库存")
    private Integer inventory;

    @NotBlank
    @ApiModelProperty("图片")
    private String image;
}
