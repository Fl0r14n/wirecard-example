package com.wirecard.controller;

import com.wirecard.resource.request.InitStorageRequest;
import com.wirecard.resource.response.InitStorageResponse;
import com.wirecard.resource.type.LanguageType;
import com.wirecard.service.WirecardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;

@Controller
public class WirecardController {

    @Autowired
    private WirecardService wirecardService;

    @RequestMapping(value = "/wirecard", method = RequestMethod.GET)
    public ModelAndView wirecard(HttpServletRequest request) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("wirecard");

        InitStorageRequest sir = new InitStorageRequest();
        sir.setShopId("qmore");
        sir.setOrderIdent("12345");
        sir.setReturnUrl(new URL("http://localhost:80/wirecard/index.php/frontend/fallback_return.php"));
        sir.setLanguage(LanguageType.EN);
        sir.setIframe(true);

//        URL requestUrl = new URL(request.getRequestURL().toString());
//        URL cssUrl = new URL(requestUrl.getProtocol(), requestUrl.getHost(), requestUrl.getPort(), "/css/pci3_iframe_style2.css");
//        sir.setIframeCssUrl(cssUrl);
        //sir.setIframeCssUrl(new URL("http://www.google.com/cse/style/look/v2/default.css"));

        InitStorageResponse response = wirecardService.initDataStorage(sir);
        mav.addObject("storage", response);

        return mav;
    }

    //@RequestMapping(value = "/pay", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ModelAndView pay(@RequestBody String body) {
        System.out.println("PAY");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("pay");

        System.out.println(body);

//        InitPaymentRequest pr = new InitPaymentRequest() {
//
//        }
//        String response = wirecardService.initPayment(pr);

        return mav;
    }

    @RequestMapping(value = "/callback")
    public void callback(HttpServletRequest request) {
        System.out.println("Callback");
    }
}
