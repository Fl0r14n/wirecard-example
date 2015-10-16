package com.wirecard.service;

import com.wirecard.resource.PaymentRequest;
import com.wirecard.resource.StorageInitRequest;
import com.wirecard.resource.StorageInitResponse;
import com.wirecard.resource.StorageResponse;

public interface WirecardService {
    StorageInitResponse initDataStorage(StorageInitRequest params);

    StorageResponse readDataStorage(String storageId);

    String initPayment(PaymentRequest params);
}
