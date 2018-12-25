package com.imooc.o2o.web.shopadmin;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/4.
 */
@RestController
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping(value = "/addproductcategorys")
    private Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,
                                                    HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession(false).getAttribute("currentShop");
        for (ProductCategory pc : productCategoryList) {
            pc.setShopId(currentShop.getShopId());
        }
        if (productCategoryList != null && productCategoryList.size() > 0 && currentShop != null) {
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategoryList(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());

            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请至少输入一个商品类别");
        }
        return modelMap;
    }


    @PostMapping(value = "/removeproductcategory")
    private Map<String, Object> removeProductCategory(@RequestBody Map<String, Long> param, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        Long productCategoryId = param.get("tempProductCategoryId");


        String aaa = request.getParameter("tempProductCategoryId");
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution productCategoryExecution =
                        productCategoryService.deleteProductCategory(currentShop.getShopId(), productCategoryId);
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    resultMap.put("success", true);
                } else {
                    resultMap.put("success", false);
                    resultMap.put("errMsg", productCategoryExecution.getStateInfo());
                }
            } catch (RuntimeException e) {
                resultMap.put("success", false);
                resultMap.put("errMsg", e.toString());
            }
        } else {
            resultMap.put("success", false);
            resultMap.put("errMsg", "请至少选择一个商品类别");
        }
        return resultMap;
    }

    @RequestMapping(value = "listproductcategorys")
    public Map<String, Object> getProductCategoryList(HttpServletRequest request) {
        Shop currentShop = (Shop) request.getSession(false).getAttribute("currentShop");
        Map<String, Object> dataMap = new HashMap<>();
        List<ProductCategory> productCategoryList = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            dataMap.put("success", true);
            dataMap.put("productCategoryList", productCategoryList);
        } else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            dataMap.put("success", false);
            dataMap.put("errorCode", ps.getState());
            dataMap.put("errorMsg", ps.getStateInfo());
        }
        return dataMap;
    }

}
