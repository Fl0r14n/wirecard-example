package com.wirecard.payment.serializer;

import com.wirecard.payment.resource.WirecardPaymentRequest;
import com.wirecard.payment.resource.WirecardPaymentResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.io.IOException;

public interface WirecardPaymentSerializer<S extends WirecardPaymentRequest, R extends WirecardPaymentResponse> {

    R from(String content) throws IOException;

    UrlEncodedFormEntity to(S request) throws IOException;
}
