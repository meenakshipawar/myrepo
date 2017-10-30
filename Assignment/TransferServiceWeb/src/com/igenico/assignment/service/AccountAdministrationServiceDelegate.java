package com.igenico.assignment.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService (targetNamespace="http://service.assignment.igenico.com/", serviceName="AccountAdministrationServiceService", portName="AccountAdministrationServicePort")
public class AccountAdministrationServiceDelegate{

    com.igenico.assignment.service.AccountAdministrationService _accountAdministrationService = null;

    @WebMethod
    public boolean createAccount(@WebParam(name="account",targetNamespace="http://service.assignment.igenico.com/")com.igenico.assignment.model.Account account) throws com.igenico.assignment.model.TransferServiceException {
        return _accountAdministrationService.createAccount(account);
    }
    @WebMethod
    public boolean transferBalance (@WebParam(name="transferRequest",targetNamespace="http://service.assignment.igenico.com/")com.igenico.assignment.model.TransferRequest transferRequest) throws com.igenico.assignment.model.TransferServiceException {
        return _accountAdministrationService.transferBalance(transferRequest);
    }
    @WebMethod
    public com.igenico.assignment.model.Account getBalance (@WebParam(name="accountNumber",targetNamespace="http://service.assignment.igenico.com/")String accountNumber) throws com.igenico.assignment.model.TransferServiceException {
        return _accountAdministrationService.getBalance(accountNumber);
    }

    public AccountAdministrationServiceDelegate() {
        _accountAdministrationService = new com.igenico.assignment.service.AccountAdministrationService(); }

}