package com.formation.ExampleDataBase;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);
        FruitJdbcDAO fruitJdbcDAO = new FruitJdbcDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Hello ! Quel est le nom du fruit à ajouter ?");
        String fruitName = scanner.nextLine();
        System.out.println("Date d'expiration ?");
        LocalDate localDate = LocalDate.parse(scanner.nextLine(), formatter);

        Fruit fruitToCreate = new Fruit(fruitName, localDate);
        Long idFruit = fruitJdbcDAO.create(fruitToCreate);
        System.out.println("Le fruit avec l'id " + idFruit + " a été ajouté");

        //create();

       // update(29l);

       // delete(29l);
        ConnectionManager.closeConnection();
    }
    private static void create(){
        FruitJdbcDAO fruitJdbcDAO = new FruitJdbcDAO();
        Fruit fruit = new Fruit("ananas", LocalDate.now());
        Long nroId =  fruitJdbcDAO.create(fruit);
        findById(nroId);

    }
    private static void update(Long nroID){
        System.out.println("****** information Actuelle*****");
        findById( nroID);

        FruitJdbcDAO fruitJdbcDAO = new FruitJdbcDAO();
        LocalDate modifiedDate = LocalDate.of(2021,07,16);
        Fruit fruit = new Fruit(nroID,"rrrrr",modifiedDate);
        fruitJdbcDAO.update(fruit);

        System.out.println("****** information Changée*****");
        findById(nroID);

        //ConnectionManager.closeConnection();
    }
    private static void delete(Long nroID){
        System.out.println("****** information Actuelle*****");
        findById( nroID);
        FruitJdbcDAO fruitJdbcDAO = new FruitJdbcDAO();
        fruitJdbcDAO.delete(nroID);
        System.out.println("****** information Supprimée*****");


        //ConnectionManager.closeConnection();
    }
    private static void findAll(){
        FruitJdbcDAO fruitJdbcDAO = new FruitJdbcDAO();
        List<Fruit> listfruit = fruitJdbcDAO.findAll();

        for (Fruit f: listfruit) {
            System.out.println("Fruit id :" + f.getId());
            System.out.println("Fruit name :" + f.getName());
            System.out.println("Fruit Date :" + f.getDate());
            System.out.println("......");
        }
        //ConnectionManager.closeConnection();
    }
    private static void findById(Long afloat){
        FruitJdbcDAO fruitJdbcDAO = new FruitJdbcDAO();
        Fruit fruit = fruitJdbcDAO.findById(afloat);
        System.out.println("Fruit id :" + fruit.getId());
        System.out.println("Fruit name :" + fruit.getName());
        System.out.println("Fruit Date :" + fruit.getDate());
     //   ConnectionManager.closeConnection();
    }
}
