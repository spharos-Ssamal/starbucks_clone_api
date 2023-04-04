package com.ssamal.starbucks_clone_api.v1.user.options.service.impl;

import com.ssamal.starbucks_clone_api.v1.user.options.model.repository.SeasonRespository;
import com.ssamal.starbucks_clone_api.v1.user.options.dto.vo.SeasonRes.GetSeasonInfoRes;
import com.ssamal.starbucks_clone_api.v1.user.options.dto.vo.SeasonRes.SeasonInfo;
import com.ssamal.starbucks_clone_api.v1.user.options.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRespository seasonRespository;

    @Override
    public GetSeasonInfoRes getSeasonInfo() {
        return new GetSeasonInfoRes(
            seasonRespository.findAll().stream().map(SeasonInfo::of).toList());
    }
}
