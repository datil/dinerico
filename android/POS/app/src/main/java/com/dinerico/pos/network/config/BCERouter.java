package com.dinerico.pos.network.config;

import com.dinerico.pos.model.EMCharge;
import com.dinerico.pos.model.EMChargeReceipt;

/**
 * Created by josephleon on 10/11/14.
 */
public interface BCERouter {

  EMChargeReceipt chargeElectronicMoney(EMCharge emCharge);
}
