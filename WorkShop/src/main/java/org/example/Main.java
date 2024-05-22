package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        var accountDao = applicationContext.getBean(AccountDao.class);

        // Добавление нового аккаунта
        Contact newContact = accountDao.addAccount("John", "Doe", "+123456789", "john@example.com");
        System.out.println("Добавлен новый аккаунт: " + newContact);

        // Получение аккаунта по ID
        Contact retrievedContact = accountDao.getAccount(newContact.getId());
        System.out.println("Получен аккаунт по ID: " + retrievedContact);

        // Обновление телефонного номера
        accountDao.setPhone(retrievedContact.getId(), "+987654321");
        System.out.println("Телефонный номер обновлен");

        // Обновление email
        accountDao.setEmail(retrievedContact.getId(), "updated@example.com");
        System.out.println("Email обновлен");

        // Получение всех аккаунтов
        List<Contact> allContacts = accountDao.getAllAccounts();
        System.out.println("Все аккаунты: ");
        allContacts.forEach(System.out::println);

        // Удаление контакта по ID
        accountDao.deleteAccount(retrievedContact.getId());
        System.out.println("Контакт удален");

        // Получение всех аккаунтов после удаления
        List<Contact> allContactsAfterDeletion = accountDao.getAllAccounts();
        System.out.println("Все аккаунты после удаления: ");
        allContactsAfterDeletion.forEach(System.out::println);

        System.out.println("---------------------------");

        // Получение контактов из NetUtils и добавление их в базу данных
        NetUtils netUtils = new NetUtils();
        List<Contact> contacts = netUtils.getContacts();
        accountDao.addContact(contacts);

        System.out.println("Добавленные контакты:");
        System.out.println(contacts);

        allContactsAfterDeletion.forEach(System.out::println);
    }
}
