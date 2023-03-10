package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс реализует работу банковского сервиса по обслуживанию счетов пользователей
 * и переводов денег
 * @author Beznosov Viktor
 * @version 1.0
 */
public class BankService {
    /**
     * Репозиторий аккаунтов пользователей и их счетов
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет пользователя в репозиторий
     * @param user пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод удалчет пользователя из репозитория
     * @param passport номер паспорта
     * @return
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод принимает на вход номер паспорта и новый счет полльзователя и если находит пользователя, добавляет
     * ему еще один счет
     * @param passport номер паспорта
     * @param account нрмер счета
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccounts = getAccounts(user);
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Метод принимает на вход номер паспорта и позвращает пользоваетля, найденного в репозитории
     * по заданному номеру паспорта.
     * В противном случае возвращает null
     * @param passport номер паспорта
     * @return
     */
    public User findByPassport(String passport) {
        for (User user: users.keySet()) {
            if (passport.equals(user.getPassport())) {
                return user;
            }
        }

        return null;
    }

    /**
     * Метод принимает на вход номер паспорта пользователя и номер счета
     * и возвращает найденный счет пользователя с заданными паспортными данными
     * @param passport номер паспорта
     * @param requisite номер счета
     * @return
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = getAccounts(user);
            for (Account account: accounts) {
                if (requisite.equals(account.getRequisite())) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Метод принимает на вход паспортные данные и номер счета пользователя отправителя и получателя,
     * и размер перефодимых средств и совершает транзакцию средств от отправителя к получателя
     * Возвращает true в случае успешной транзакции и false в противном случае
     * @param srcPassport номер паспорта отправителя
     * @param srcRequisite номер счета отправителя
     * @param destPassport номер паспорта получателя
     * @param destRequisite номер счета получателя
     * @param amount сумма переводимых средств
     * @return
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account dstAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && dstAccount != null && srcAccount.getBalance() >= amount) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            dstAccount.setBalance(dstAccount.getBalance() + amount);
            return true;
        }

        return false;
    }

    /**
     * Метод принимает на вход пользователя и возвращает все его счета
     * @param user пользователь
     * @return
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}