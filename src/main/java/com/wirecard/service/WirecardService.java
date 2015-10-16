package com.wirecard.service;

import com.wirecard.resource.*;

public interface WirecardService {
    StorageInitResponse initDataStorage(StorageInitRequest params);

    StorageResponse readDataStorage(StorageRequest params);

    String initPayment(PaymentRequest params);
}
