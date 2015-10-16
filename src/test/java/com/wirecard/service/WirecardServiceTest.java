package com.wirecard.service;

import com.example.WirecardSeamlessExampleApplication;
import com.wirecard.resource.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WirecardSeamlessExampleApplication.class)
@WebAppConfiguration
public class WirecardServiceTest {

    @Autowired
    WirecardService wirecardService;

    @Test
    public void init_data_storage_should_return_result() throws MalformedURLException {
        StorageInitRequest sir = new StorageInitRequest();
        {
            sir.setShopId("qmore");
            sir.setOrderIdent("12345");
            sir.setReturnUrl(new URL("http://localhost:80/wirecard/index.php/frontend/fallback_return.php"));
            sir.setLanguage(LanguageType.EN);
        }
        StorageInitResponse response = wirecardService.initDataStorage(sir);
        Assert.assertNotNull(response.getJavascriptUrl());
        Assert.assertNotNull(response.getStorageId());
    }

    @Test
    public void init_data_storage_for_iframe_should_return_result() throws MalformedURLException {
        StorageInitRequest sir = new StorageInitRequest();
        {
            sir.setIframe(true);

            sir.setShopId("qmore");
            sir.setOrderIdent("12345");
            sir.setReturnUrl(new URL("http://localhost:80/wirecard/index.php/frontend/fallback_return.php"));
            sir.setLanguage(LanguageType.EN);
            //sir.setIframeCssUrl(new URL("http://www.google.com/cse/style/look/v2/default.css"));
            //sir.setIframeCssUrl(new URL("http://localhost:8080/css/pci3_iframe.css"));
        }
        StorageInitResponse response = wirecardService.initDataStorage(sir);
        Assert.assertNotNull(response.getJavascriptUrl());
        Assert.assertNotNull(response.getStorageId());
    }

    @Test
    public void init_payment_shoud_return_response() throws MalformedURLException {
        PaymentRequest pr = new PaymentRequest(); {
            pr.setOrderIdent("BYRMSBS6KM");
            pr.setStorageId("df3c9a616aa45c346b80832d52785570");
            pr.setPaymentType(PaymentType.CCARD);
            pr.setConsumerIpAddress("193.17.194.226");
            pr.setConsumerUserAgent("Safari/7046A194A");
            pr.setConfirmUrl(new URL("https://ecom-acc.mammut.ch/store//confirm?orderId=12345"));

            pr.setPendingUrl(new URL("http://ecom-acc.mammut.ch/store/"));
            pr.setSuccessUrl(new URL("http://ecom-acc.mammut.ch/store/"));
            pr.setFailureURl(new URL("http://ecom-acc.mammut.ch/store/"));
            pr.setCancelUrl(new URL("http://ecom-acc.mammut.ch/store/"));
            pr.setServiceURL(new URL("http://ecom-acc.mammut.ch/store/"));

            pr.setOrderDescription("Test Order");
            pr.setCurrency("CHF");
            pr.setAmount("99.99");
            pr.setLanguage(LanguageType.EN);
            pr.setShopId("qmore");
        }
        String result = wirecardService.initPayment(pr);
        Assert.assertNotNull(result);
    }
}
