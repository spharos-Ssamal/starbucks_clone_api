package com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.service;

import com.ssamal.starbucks_clone_api.v1.service.category.dto.vo.BannerRes.BannerInfo;
import java.util.List;

public interface BannerService {

    List<BannerInfo> getBannerInfo();

}
