package com.yy.service;

import com.yy.pojo.Commodity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commodityService")
public interface CommodityService {
    public List<Commodity> selectByCom(Commodity commodity);
}
