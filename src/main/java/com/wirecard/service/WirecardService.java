package com.wirecard.service;

import com.wirecard.resource.request.InitPaymentRequest;
import com.wirecard.resource.request.InitStorageRequest;
import com.wirecard.resource.request.ReadStorageRequest;
import com.wirecard.resource.response.ConfirmedPaymentResponse;
import com.wirecard.resource.response.InitPaymentResponse;
import com.wirecard.resource.response.InitStorageResponse;
import com.wirecard.resource.response.ReadStorageResponse;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

public interface WirecardService {
    InitStorageResponse initDataStorage(InitStorageRequest params) throws IOException;

    ReadStorageResponse readDataStorage(ReadStorageRequest params) throws IOException;

    InitPaymentResponse initPayment(InitPaymentRequest params) throws IOException;
    
    ConfirmedPaymentResponse readPaymentConfirmation(HttpServletRequest request) throws IOException;
}
