package com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.service.impl;

import com.ssamal.starbucks_clone_api.v1.service.category.dto.vo.BannerRes.BannerInfo;
import com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.model.repository.EventRepository;
import com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.model.repository.RecommendRepository;
import com.ssamal.starbucks_clone_api.v1.service.evntsrcmnd.service.BannerService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final RecommendRepository recommendRepository;
    private final EventRepository eventRepository;

    @Override
    public List<BannerInfo> getBannerInfo() {
        List<BannerInfo> recommends = recommendRepository.findAllByBannerViewable(true)
            .stream().map(BannerInfo::of).toList();
        List<BannerInfo> events = eventRepository.findAllByBannerViewable(true)
            .stream().map(BannerInfo::of).toList();
        return Stream.concat(recommends.stream(), events.stream()).toList().stream()
            .sorted(Comparator.comparing(BannerInfo::getRegTime).reversed()).toList();
    }
}
