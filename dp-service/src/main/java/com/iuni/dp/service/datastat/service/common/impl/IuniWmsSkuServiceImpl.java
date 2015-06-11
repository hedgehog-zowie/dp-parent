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

    private static final String[] goodsTypeOfU3 = new String[]{"1083694"};
    private static final String[] goodsTypeOfU2 = new String[]{"1083694"};
    private static final String[] goodsTypeOfPJ = new String[]{"1083696", "4910371", "4910373"};
    private static final String[] goodsTypeOfI1 = new String[]{"1083694"};

    private static final String[] waresOfU3 = new String[]{"5059485"};
    private static final String[] waresOfU2 = new String[]{"1083755"};
    private static final String[] waresOfi1 = new String[]{"5874069"};

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
