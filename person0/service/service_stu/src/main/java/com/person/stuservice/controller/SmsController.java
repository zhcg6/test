package com.person.stuservice.controller;

import com.person.stuservice.common.Result;
import com.person.stuservice.entity.Sms;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/sms")
public class SmsController {
    @RequestMapping(value = "/sendCode",method = RequestMethod.POST)
    public void sms(@RequestBody Sms sms){
        Integer appid = 1400798997;
        String appkey = "4bf312e171ee1de7c4e3ace533472ecf";
        Integer templateId = 1715976;
        String smsSign = "乐学律己2公众号";

        try{
            String[] params ={ sms.getCode() };
            SmsSingleSender ssender  = new SmsSingleSender(appid,appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86",sms.getPhonenumber(),
                    templateId,params,smsSign,"","");
            System.out.println(result);
        }catch (HTTPException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @PostMapping("/getKey")
        public Result getKey(){
            String s = RandomStringUtils.randomNumeric(6);
            return Result.success(s);
        }

    @PostMapping("/getAccount")
        public Result getAccount(){
        String account = RandomStringUtils.randomNumeric(10);
        return Result.success(account);
    }

}
