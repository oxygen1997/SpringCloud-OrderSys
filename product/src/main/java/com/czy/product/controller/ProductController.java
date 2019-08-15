package com.czy.product.controller;

import com.czy.product.pojo.DecreaseStockInput;
import com.czy.product.pojo.ProductCategory;
import com.czy.product.pojo.ProductInfo;
import com.czy.product.service.CategoryService;
import com.czy.product.service.ProductService;
import com.czy.product.util.ResultVOUtil;
import com.czy.product.vo.ProductInfoVO;
import com.czy.product.vo.ProductVO;
import com.czy.product.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

     @Autowired
     CategoryService categoryService;

     @Autowired
     ProductService productService;

     /**
      * 1.查询所有在架的商品
      * 2.获取类目type列表
      * 3.查询类目
      * 4.构造数据
      */

     @GetMapping("/list")
     public ResultVO<ProductInfo> list() {
          // 1.查询所有在架商品
          List<ProductInfo> onlineProductInfoList = productService.findByProductUpAll();

          //2. 获取类目type列表
//          jdk8 stream流写法
//          List<Integer> categoryTypeList = onlineSell.stream()
//                  .map(ProductInfo::getCategoryType)
//                  .collect(Collectors.toList());
          List<Integer> categoryTypeList = new ArrayList<>();
          for (ProductInfo productInfo:onlineProductInfoList) {
               categoryTypeList.add(productInfo.getCategoryType());
          }
          //3. 从数据库查询类目
          List<ProductCategory> categoryList = categoryService.findByCategoryIdIn(categoryTypeList);

          //4. 构造数据
          List<ProductVO> productVOList = new ArrayList<>();
          for (ProductCategory productCategory : categoryList) {
               //ProductVO为Json对象中的data
               ProductVO productVO = new ProductVO();
               productVO.setCategoryName(productCategory.getCategoryName());
               productVO.setCategoryType(productCategory.getCategoryType());

               List<ProductInfoVO> productInfoVOList = new ArrayList<>();
               for (ProductInfo productInfo : onlineProductInfoList) {
                    //ProductInfoVO为foods
                    if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                         ProductInfoVO productInfoVO = new ProductInfoVO();
                         BeanUtils.copyProperties(productInfo, productInfoVO);
                         productInfoVOList.add(productInfoVO);
                    }
               }
               productVO.setProductInfoVOList(productInfoVOList);
               productVOList.add(productVO);
          }
          return ResultVOUtil.success(productVOList);
     }

     @PostMapping("/getForOrderList")
     public List<ProductInfo> getList(@RequestBody List<String> productIdList){
          List<ProductInfo> list = productService.findList(productIdList);
          return list;
     }
     @PostMapping("/decreaseProductStock")
     public void decreaseProductStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList){
          productService.decreaseProductStock(decreaseStockInputList);
     }

}
