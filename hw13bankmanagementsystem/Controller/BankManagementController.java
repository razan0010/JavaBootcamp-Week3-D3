package com.example.hw13bankmanagementsystem.Controller;

import com.example.hw13bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.hw13bankmanagementsystem.Model.BankManagement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/bankManagement/")
public class BankManagementController {

    ArrayList<BankManagement> accounts = new ArrayList<>();

//    Get all the customers
    @GetMapping("get")
    public ArrayList<BankManagement> getAll(){
        return accounts;
    }


//    Add new customers
    @PostMapping("add")
    public ApiResponse addCustomer(@RequestBody BankManagement account){
        accounts.add(account);
        return new ApiResponse("The customer has been added", 200);
    }

//    Update customers
    @PutMapping("update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody BankManagement account){
        accounts.set(index, account);
        return new ApiResponse("The customer data has been updated", 200);
    }


//    Delete customers
    @DeleteMapping("delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        accounts.remove(index);
        return new ApiResponse("The customer data has been deleted", 200);
    }

//    Deposit money to customer
    // in postman in body will must add amount to deposit only in "balance" then send
    @PostMapping("deposit/{index}")
    public ApiResponse deposit( @PathVariable int index, @RequestBody BankManagement account){
        account.setID(accounts.get(index).getID());
        account.setUsername(accounts.get(index).getUsername());
        account.setBalance(account.getBalance() + accounts.get(index).getBalance());
        accounts.set(index,account);
        return new ApiResponse("The amount has been deposit successfully", 200);
    }

//    Withdraw money from customers
    // in postman in body will must add amount to withdraw only in "balance" then send
    @PostMapping("withdraw/{index}")
    public ApiResponse withdraw(@PathVariable int index, @RequestBody BankManagement account){
        account.setID(accounts.get(index).getID());
        account.setUsername(accounts.get(index).getUsername());

        if(accounts.get(index).getBalance() >0 && accounts.get(index).getBalance() >= account.getBalance())
        {
            account.setBalance(accounts.get(index).getBalance() - account.getBalance());
            accounts.set(index,account);
            return new ApiResponse("The amount has been withdrawn successfully", 200);

        } else return new ApiResponse("The balance is insufficient", 500);
}

}