package com.isladellago.usermanager.service.client;

import com.isladellago.usermanager.domain.dto.CreateTokenReq;
import com.isladellago.usermanager.domain.dto.CreateTokenRes;
import com.isladellago.usermanager.domain.dto.ValidateTokenReq;
import com.isladellago.usermanager.domain.dto.ValidateTokenRes;
import com.isladellago.usermanager.util.PathUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "securityManagerClient", url = "${security.manager.root.url}")
public interface SecurityManagerClient {

    @RequestMapping(method = RequestMethod.POST, value = PathUtils.SecurityManagerClient.CREATE_TOKEN_PATH)
    CreateTokenRes createToken(@RequestBody CreateTokenReq createTokenBody);

    @RequestMapping(method = RequestMethod.POST, value = PathUtils.SecurityManagerClient.VALIDATE_TOKEN_PATH)
    ValidateTokenRes validateToken(@RequestBody ValidateTokenReq validateTokenBody);
}
