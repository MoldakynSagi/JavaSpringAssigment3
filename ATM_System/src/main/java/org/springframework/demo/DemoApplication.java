package org.springframework.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.ResultSet;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("MyConfig.java");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Connection connection = context.getBean("dbc", Connection.class);

        ResultSet resultSet = connection.getUsers();

        ChangePIN changePIN = context.getBean("pin", ChangePIN.class);
        CheckBalance checkBalance = context.getBean("balance", CheckBalance.class);
        TopUp topUp = context.getBean("top", TopUp.class);
        Withdraw withdraw = context.getBean("withdraw", Withdraw.class);
        ATM atm = new ATM(01);

        System.out.println("Please add the card num: ");
        Scanner sc = new Scanner(System.in);
        int in = sc.nextInt();

        System.out.println("Please add the pass: ");
        int we = sc.nextInt();
        try {
            while (resultSet.next()){
                //System.out.println(resultSet.getInt("cardnum" ));
                if(resultSet.getInt("pass" ) == we){
                    System.out.println("Please choose the action");
                    atm.printInfo();
                    int p = sc.nextInt();
                    if(p == 1){
                        checkBalance.makeService(in);
                    }else if(p == 2){
                        topUp.makeService(in);
                    }else if(p == 3){
                        withdraw.makeService(in);
                    }else if(p == 4){
                        changePIN.makeService(in);
                    }
                    else if(p == 0){
                        break;
                    }
                }

            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        context.close();
    }

}
        
