package com.ljc.shop3.service;

import com.ljc.shop3.model.Banner;
import org.springframework.stereotype.Service;

public interface BannerService {
    Banner getByName(String name);
}