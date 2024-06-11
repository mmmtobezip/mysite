package com.poscodx.mysite.service;

import org.springframework.stereotype.Service;
import com.poscodx.mysite.repository.SiteRepository;
import com.poscodx.mysite.vo.SiteVo;

@Service
public class SiteService {
  private SiteRepository siteRepository;

  public SiteService(SiteRepository siteRepository) {
    this.siteRepository = siteRepository;
  }

  public SiteVo getSite() {
    // return siteRepository.find(); 만들어야해
    return null;
  }

  public void updateSite(SiteVo vo) {
    // siteRepository.update(vo);
  }
}
