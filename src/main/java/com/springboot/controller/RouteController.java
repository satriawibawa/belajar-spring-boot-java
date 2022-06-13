package com.springboot.controller;

import com.springboot.model.OTPModel;
import com.springboot.service.OTPService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
public class RouteController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/otp")
    public ResponseEntity<Object> requestOTP(@RequestBody OTPModel otpModel) {
        return new ResponseEntity<>(otpService.otp(otpModel.getMsisdn()), HttpStatus.OK);
    }

    @Controller
    public class CustomErrorController extends AbstractErrorController {

        public CustomErrorController(final ErrorAttributes errorAttributes) {
            super(errorAttributes, Collections.emptyList());
        }

        @RequestMapping("/error")
        @ResponseBody
        public String handleError(Model model, HttpServletRequest request) {
            Map<String, Object> errorAttributes = this.getErrorAttributes(request, false);
            JSONObject jsonString = new JSONObject();
            try {
                jsonString.put("status", errorAttributes.get("status").toString());
                jsonString.put("reason", errorAttributes.get("error").toString());
                jsonString.put("Create", new JSONObject().put("Name", "Satria Wibawa"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return jsonString.toString();
        }

        @Override
        public String getErrorPath() {
            return "/error";
        }
    }
}
