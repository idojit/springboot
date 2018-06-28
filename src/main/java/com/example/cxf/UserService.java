package com.example.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface UserService {

	@WebMethod
    String getName(@WebParam(name = "userId") String userId);
    @WebMethod
    User getUser(String userId);
    
}
