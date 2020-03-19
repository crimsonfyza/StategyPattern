package sample.web.ui.domain;

import sample.web.ui.repository.AccountStrategy;

public class StudentAccount implements AccountStrategy {
    @Override
    public UserAccount determineRole(String username,int typeNumber,String phoneNumber, String Email) {
        UserAccount userAccount = new UserAccount(username, "Student", "StudentNumber", typeNumber, phoneNumber, Email);
        return userAccount;
    }
}