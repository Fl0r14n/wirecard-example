package com.wirecard.payment.service;

import com.wirecard.payment.resource.request.InitPaymentRequest;
import com.wirecard.payment.resource.request.InitStorageRequest;
import com.wirecard.payment.resource.request.ReadStorageRequest;
import com.wirecard.payment.resource.response.ConfirmedPaymentResponse;
import com.wirecard.payment.resource.response.InitPaymentResponse;
import com.wirecard.payment.resource.response.InitStorageResponse;
import com.wirecard.payment.resource.response.ReadStorageResponse;

import java.io.IOException;

public interface WirecardPaymentService {

    InitStorageResponse initDataStorage(InitStorageRequest params) throws IOException;

    ReadStorageResponse readDataStorage(ReadStorageRequest params) throws IOException;

    InitPaymentResponse initPayment(InitPaymentRequest params) throws IOException;

    ConfirmedPaymentResponse readPaymentConfirmation(String paymentConfirmation) throws IOException;
}
