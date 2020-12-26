package com.ljc.shop3.service;


import com.ljc.shop3.model.Banner;
import com.ljc.shop3.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerRepository bannerRepository;

    public Banner getByName(String name) {

        //数据表如何创建
        //如何查询数据库

        return bannerRepository.findOneByName(name);

    }
}