package pl.akademiaspecjalistowit.ecommerce.api.controller;

import org.springframework.http.ResponseEntity;
import pl.akademiaspecjalistowit.api.AccountApi;
import pl.akademiaspecjalistowit.model.AddFundsRequest;
class AccountController implements AccountApi {
    @Override
    public ResponseEntity<Void> topUpAccount(AddFundsRequest addFundsRequest) {
        return AccountApi.super.topUpAccount(addFundsRequest);
    }
}
