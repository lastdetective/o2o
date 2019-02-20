package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.AreaService;
import com.imooc.o2o.service.ShopCategoryService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页点击商店出现商店列表
 *
 * @author bruce liu
 * @since 2019/2/17
 */
@RestController
@RequestMapping(value = "frontend", method = RequestMethod.GET)
public class ShopListController {
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "listshopspageinfo")
    public Map<String, Object> listShopsPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 试着从请求地址中取 parentId;
        long parentShopCategoryId = HttpServletRequestUtil.getLong(request, "parentId");
        // 如果 parentId 不为空，则取该出该了别所有的
        List<ShopCategory> shopCategoryList = null;
        if (parentShopCategoryId != -1) {

            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory childShopCategory = new ShopCategory();

                childShopCategory.setShopCategoryId(parentShopCategoryId);
                shopCategoryCondition.setParent(childShopCategory);

                shopCategoryList =
                        shopCategoryService.getShopCategoryList(shopCategoryCondition);
                modelMap.put("shopCategoryList", shopCategoryList);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());

            }
        } else {
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);

            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        }
        modelMap.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

    @RequestMapping("listshops")
    private Map<String, Object> listShops(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        if (pageIndex != -1 && pageSize != -1) {
            // 试着获取第一级的店铺类别id
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            // 试着获取特定的二级类别id
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            // 试着获取模糊查询的名字
            String shopName = HttpServletRequestUtil.getString(request, "shopName");
            // 试着获取区域Id
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");

            // 获取组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);
            ShopExecution se = shopService.getShopList(shopCondition, pageIndex, pageSize);
            modelMap.put("success", true);
            modelMap.put("count", se.getCount());
            modelMap.put("shopList", se.getShopList());

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageIndex or pageSize");
        }
        return modelMap;

    }

    /**
     * 组装查询店铺列表的条件
     *
     * @param parentId
     * @param shopCategoryId
     * @param areaId
     * @param shopName
     * @return
     * @since
     */
    private Shop compactShopCondition4Search(long parentId,
                                             long shopCategoryId,
                                             int areaId,
                                             String shopName) {
        Shop shopCondtion = new Shop();
        if (parentId != -1) {
            ShopCategory child = new ShopCategory();
            ShopCategory parent = new ShopCategory();
            parent.setShopCategoryId(parentId);
            child.setParent(parent);
            shopCondtion.setShopCategory(child);
        }

        if (shopCategoryId != -1) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondtion.setShopCategory(shopCategory);
        }
        if (areaId != -1) {
            Area area = Area.builder().areaId(areaId).build();
            shopCondtion.setArea(area);
        }
        if (shopName != null && !"".equals(shopName) && !"".equals(shopName.trim())) {
            shopCondtion.setShopName(shopName);
        }
        shopCondtion.setEnableStatus(1);
        return shopCondtion;
    }

}
