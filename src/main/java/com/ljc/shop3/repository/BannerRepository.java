package com.ljc.shop3.repository;

import com.ljc.shop3.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {
    Banner findOneById(Long id);

    Banner findOneByName(String name);
}
