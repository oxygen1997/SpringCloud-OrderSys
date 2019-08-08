package com.czy.product.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name="product_category")
@Entity
@Data
public class ProductCategory {

     /** id */
     @Id
     @GeneratedValue
     private Integer categoryId;
     /** 类别名 */
     private String categoryName;
     /** 类别类型 */
     private Integer categoryType;
     /** 创建时间 */
     private Date createTime;
     /** 更新时间 */
     private Date updateTime;
}
