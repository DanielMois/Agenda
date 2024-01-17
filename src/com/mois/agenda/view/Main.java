package com.mois.agenda.view;

import com.mois.agenda.dao.ContactDAO;
import com.mois.agenda.model.Contact;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    static int op;

    public static void main(String[] args) throws SQLException {

        LocalDate currentDate = LocalDate.now();
        ContactDAO contactDAO = new ContactDAO();
        Scanner scanner = new Scanner(System.in);

        while (op != 5) {

            System.out.println(
                    System.lineSeparator() + "|1: CREATE                                        |" +
                    System.lineSeparator() + "|2: READ                                          |" +
                    System.lineSeparator() + "|3: UPDATE                                        |" +
                    System.lineSeparator() + "|4: DELETE                                        |" +
                    System.lineSeparator() + "|5: EXIT                                          |" +
                    System.lineSeparator() + "--------------------------------------------------"
            );

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:

                    Contact contactCreate = new Contact();

                    System.out.println("Digite o nome do novo contato: ");
                    contactCreate.setName(scanner.nextLine());
                    System.out.println("Digite a idade do novo contato: ");
                    contactCreate.setAge(scanner.nextInt());

                    contactCreate.setRegisterDate(java.sql.Date.valueOf(currentDate));

                    contactDAO.save(contactCreate);
                    System.out.println("Contato salvo com sucesso."
                    + System.lineSeparator());
                    break;

                case 2:

                    System.out.println(System.lineSeparator()
                    + "Segue a listagem dos contatos: ");
                    for (Contact contactRead : contactDAO.getContacts()) {
                        System.out.println("Contato: " + contactRead.getName());
                    }
                    System.out.println(System.lineSeparator());
                    break;

                case 3:

                    Contact contactUpdate = new Contact();
                    System.out.println("Digite o nome atualizado do contato: ");
                    contactUpdate.setName(scanner.nextLine());
                    System.out.println("Digite a idade atualizada do contato: ");
                    contactUpdate.setAge(scanner.nextInt());
                    contactUpdate.setRegisterDate(java.sql.Date.valueOf(currentDate));
                    System.out.println("PASSE O IDENTIFICADOR DO CONTATO: ");
                    contactUpdate.setId(scanner.nextInt());

                    contactDAO.update(contactUpdate);
                    System.out.println("Contato alterado com sucesso."
                            + System.lineSeparator());
                    break;

                case 4:
                    System.out.println("PASSE O IDENTIFICADOR DO CONTATO: ");
                    int id = scanner.nextInt();
                    System.out.println("Você tem certeza que deseja eliminar o contado de id " + id + "? S/N");
                    Scanner confScanner = new Scanner(System.in);

                    String confirmation = confScanner.nextLine();

                    if (confirmation.equalsIgnoreCase("S")) {
                        contactDAO.deleteById(id);
                        System.out.println("Elemento excluído do banco." + System.lineSeparator());
                    } else if (confirmation.equalsIgnoreCase("N")) {
                        System.out.println("Sua operação foi cancelada." + System.lineSeparator());
                    }
                    break;

                case 5:
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;

            }
        }
    }
}
