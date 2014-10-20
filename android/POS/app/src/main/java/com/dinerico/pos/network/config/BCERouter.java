package com.dinerico.pos.network.config;

import com.dinerico.pos.model.Payment;

/**
 * Created by josephleon on 10/11/14.
 */
public interface BCERouter {

  Payment chargeElectronicMoney(Payment emPayment);
}
