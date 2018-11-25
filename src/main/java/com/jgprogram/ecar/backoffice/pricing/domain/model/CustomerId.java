package com.jgprogram.ecar.backoffice.pricing.domain.model;

import com.jgprogram.common.domain.model.AbstractId;
import com.jgprogram.common.exception.BusinessLogicException;

public class CustomerId extends AbstractId {

    public CustomerId(String customerId) {
        super(customerId);
    }

    @Override
    protected void validateId(String id) throws BusinessLogicException {
        //Not required
    }
}
