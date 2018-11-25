package com.jgprogram.ecar.backoffice.pricing.domain.model.charging;

import com.jgprogram.common.domain.model.AbstractId;
import com.jgprogram.common.exception.BusinessLogicException;

public class ChargingPricingId extends AbstractId {

    public ChargingPricingId(String id) {
        super(id);
    }

    @Override
    protected void validateId(String id) throws BusinessLogicException {
        // NOT REQUIRED
    }
}
