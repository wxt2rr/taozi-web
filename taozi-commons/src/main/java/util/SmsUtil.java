package util;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SmsUtil {
    private String product;
    private String domain;
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    private String sendSms(List<String> phoneNumbers, String templateCode, Map<String, String> templateParam) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",accessKeyId,accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",product,domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        // https://help.aliyun.com/document_detail/55284.html?spm=5176.10629532.106.1.7c441cbe1TVKT9
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(StringUtils.join(phoneNumbers, ','));
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        // 可选:模板中的变量替换JSON串
        if (templateParam != null && !templateParam.isEmpty()) {
            request.setTemplateParam(JSONObject.toJSONString(templateParam));
        }

        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse.getCode();
    }

    public String sendSms(String phoneNumber, String templateCode, Map<String, String> templateParam) {
        String code = null;
        try {
            code = sendSms(Collections.singletonList(phoneNumber), templateCode, templateParam);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return code;
    }

    public String sendSms(String[] phoneNumbers, String templateCode, Map<String, String> templateParam) {
        String code = null;
        try {
            code = sendSms(Arrays.asList(phoneNumbers), templateCode, templateParam);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return code;
    }
}
