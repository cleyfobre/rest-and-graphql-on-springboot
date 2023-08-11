package com.demo.springgraphql2.service;

import com.demo.springgraphql2.dto.TargetDto;
import com.demo.springgraphql2.entity.Target;

public interface TargetService {

    Target getTarget(Long id);
    TargetDto.Partner getTargets(TargetDto.PartnerInfoRequest request);

}
