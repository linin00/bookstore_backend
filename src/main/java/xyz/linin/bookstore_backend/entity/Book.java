package xyz.linin.bookstore_backend.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {
    @Id
    @ApiModelProperty("id")
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @ApiModelProperty("ISBN")
    private String isbn;

    @Column(nullable = false)
    @ApiModelProperty("书名")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty("类型")
    private String type;

    @Column(nullable = false)
    @ApiModelProperty("作者")
    private String author;

    @Column(nullable = false)
    @ApiModelProperty("价格")
    private Integer price;

    @Column(nullable = false)
    @ApiModelProperty("描述")
    private String description;

    @Column(nullable = false)
    @ApiModelProperty("库存")
    private Integer inventory;

    @Column(nullable = false)
    @ApiModelProperty("图片")
    private String image;
}
