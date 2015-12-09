package com.wirecard.payment.controller;

import com.wirecard.payment.resource.request.InitPaymentRequest;
import com.wirecard.payment.resource.request.InitStorageRequest;
import com.wirecard.payment.resource.request.ReadStorageRequest;
import com.wirecard.payment.resource.response.ConfirmedPaymentResponse;
import com.wirecard.payment.resource.response.InitPaymentResponse;
import com.wirecard.payment.resource.response.InitStorageResponse;
import com.wirecard.payment.resource.response.ReadStorageResponse;
import com.wirecard.payment.resource.type.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wirecard.payment.service.WirecardPaymentService;

@Controller
public class WirecardPaymentController {

    private static final Logger L = LoggerFactory.getLogger(WirecardPaymentController.class);

    private static final String SHOP_ID = "qmore";
    private static final String ORDER_IDENT = "12345";
    @Autowired
    private WirecardPaymentService wirecardService;

    @RequestMapping(value = "/wirecard", method = RequestMethod.GET)
    public ModelAndView wirecard(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("wirecard");

        URL requestUrl = new URL(request.getRequestURL().toString());
        URL cssUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/css/pci3_iframe_style2.css");

        InitStorageRequest sir = new InitStorageRequest();
        {
            sir.setShopId(SHOP_ID);
            sir.setOrderIdent(ORDER_IDENT);
            sir.setReturnUrl(new URL(requestUrl.getProtocol(), requestUrl.getHost(), requestUrl.getPort(), "/fallback"));
            sir.setLanguage(Locale.GERMANY);
            sir.setIframe(true);
            sir.setIframeCssUrl(cssUrl);
        }

        InitStorageResponse response = wirecardService.initDataStorage(sir);
        mav.addObject("storage", response);
        return mav;
    }

    @RequestMapping(
            value = "/pay",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public InitPaymentResponse pay(HttpServletRequest request, @RequestBody ReadStorageResponse storage) throws IOException {
        //wirecard accepts only 80 or 443
        URL requestUrl = new URL(request.getRequestURL().toString());
        URL confirmUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/confirm?storageId=" + storage.getStorageId());
        URL pendingUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/pending");
        URL successUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/success");
        URL failureUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/failure");
        URL cancelUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/cancel");
        URL serviceUrl = new URL(requestUrl.getProtocol(), request.getServerName(), requestUrl.getProtocol().equals("http") ? 80 : 443, "/service");

        InitPaymentRequest pr = new InitPaymentRequest();
        {
            pr.setStorageId(storage.getStorageId());
            pr.setShopId(SHOP_ID);
            pr.setOrderIdent(ORDER_IDENT);
            pr.setPaymentType(PaymentType.CCARD);

            //user
            pr.setConsumerIpAddress(request.getRemoteAddr());
            pr.setConsumerUserAgent(request.getHeader("User-Agent"));

            //callbacks
            pr.setConfirmUrl(confirmUrl);
            pr.setPendingUrl(pendingUrl);
            pr.setSuccessUrl(successUrl);
            pr.setFailureURl(failureUrl);
            pr.setCancelUrl(cancelUrl);
            pr.setServiceURL(serviceUrl);

            //order
            pr.setOrderDescription("Test Order");
            pr.setOrderNumber(12345L);
            pr.setOrderReference("orderReference");
            pr.setCurrency(Currency.getInstance(Locale.GERMANY));
            pr.setAmount(new BigDecimal("99.99"));
            pr.setLanguage(Locale.GERMANY);
        }
        InitPaymentResponse response = wirecardService.initPayment(pr);
        return response;
    }

    @RequestMapping(
            value = "/storage",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReadStorageResponse storage(@RequestBody ReadStorageResponse storage) throws IOException {
        //Test method for read storage
        ReadStorageRequest rsr = new ReadStorageRequest();
        {
            rsr.setStorageId(storage.getStorageId());
            rsr.setShopId(SHOP_ID);
        }
        return wirecardService.readDataStorage(rsr);
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public void confirm(@RequestBody String body) throws IOException {
        L.info("Confirm");
        L.info(body);
        ConfirmedPaymentResponse response = wirecardService.readPaymentConfirmation(body);
        L.info(response.toString());
    }

    @RequestMapping(value = "/pending")
    public ModelAndView pending() throws IOException {
        L.info("Pending");
        return new ModelAndView("pending");
    }

    @RequestMapping(value = "/success")
    public ModelAndView success() throws IOException {
        L.info("Success");
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/failure")
    public ModelAndView failure() throws IOException {
        L.info("Failure");
        return new ModelAndView("failure");
    }

    @RequestMapping(value = "/cancel")
    public ModelAndView cancel() throws IOException {
        L.info("Cancel");
        return new ModelAndView("cancel");
    }

    @RequestMapping(value = "/service")
    public ModelAndView service() throws IOException {
        L.info("Service");
        return new ModelAndView("service");
    }

    @RequestMapping(value = "/fallback")
    public void fallback() {
        //Dummy callback for wirecard
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/wirecard";
    }
}
