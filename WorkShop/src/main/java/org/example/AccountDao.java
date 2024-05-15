package org.example;

import java.util.List;

public interface AccountDao {

    Contact addAccount(String id, String name, String surname, String phone, String email);
    Contact addAccount(String name, String surname, String phone, String email);
    Contact getAccount(String accountId);
    void setAmount(String accountId, String amount);

    void setPhone(String accountId, String phone);

    void setEmail(String accountId, String email);

    void deleteAccount(String accountId);

    List<Contact> getAllAccounts();
}
