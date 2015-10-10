package com.iuni.dp.service.datastat.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iuni.dp.persist.datastat.common.dao.IuniWmsSkuDao;
import com.iuni.dp.persist.datastat.common.model.IuniWmsSku;
import com.iuni.dp.service.datastat.service.common.IuniWmsSkuService;

@Service("iuniWmsSkuService")
public class IuniWmsSkuServiceImpl implements IuniWmsSkuService {

    private static final String[] goodsTypeOfU3 = new String[]{"手机"};
    private static final String[] goodsTypeOfU2 = new String[]{"手机"};
    private static final String[] goodsTypeOfPJ = new String[]{"配件", "玩偶", "配饰", "服装"};
    private static final String[] goodsTypeOfI1 = new String[]{"手机"};
    private static final String[] goodsTypeOfN1 = new String[]{"手机"};
    private static final String[] goodsTypeOfFW = new String[]{"碎屏险", "延长保"};

    private static final String[] waresOfU3 = new String[]{"000000017"};
    private static final String[] waresOfU2 = new String[]{"000000001"};
    private static final String[] waresOfi1 = new String[]{"000000021"};
    private static final String[] waresOfN1 = new String[]{"000000028"};

    @Autowired
    private IuniWmsSkuDao iuniWmsSkuDao;

    public static Map<String, Object> paramsConvert(String goodsType, Map<String, Object> params) {
        String[] goodsTypeList = null;
        String[] phoneList = null;
        if ("i1".equals(goodsType)) {
            goodsTypeList = goodsTypeOfI1;
            phoneList = waresOfi1;
        } else if ("U3".equals(goodsType)) {
            goodsTypeList = goodsTypeOfU3;
            phoneList = waresOfU3;
        } else if ("U2".equals(goodsType)) {
            goodsTypeList = goodsTypeOfU2;
            phoneList = waresOfU2;
        } else if ("PJ".equals(goodsType)) {
            goodsTypeList = goodsTypeOfPJ;
        } else if ("FW".equals(goodsType)) {
            goodsTypeList = goodsTypeOfFW;
        } else if ("N1".equals(goodsType)) {
            goodsTypeList = goodsTypeOfN1;
            phoneList = waresOfN1;
        }
        params.put("goodsTypeList", goodsTypeList);
        params.put("phoneList", phoneList);
        return params;
    }

    @Override
    public List<IuniWmsSku> queryIuniWmsSku(String goodsType) {
        List<IuniWmsSku> list = new ArrayList<IuniWmsSku>();
        Map<String, Object> params = new HashMap<String, Object>();
        paramsConvert(goodsType, params);

        List<Map<String, Object>> tempList = iuniWmsSkuDao.selectIuniWmsSku(params);
        for (Map<String, Object> e : tempList) {
            IuniWmsSku sku = new IuniWmsSku();
            sku.setId((String) e.get("skuId"));
            sku.setSkuName((String) e.get("skuName"));
            list.add(sku);
        }
        return list;
    }

}
