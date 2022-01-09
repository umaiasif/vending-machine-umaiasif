/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codealong.vendingmachine;

import com.codealong.vendingmachine.dao.VendingMachineDao;
import com.codealong.vendingmachine.ui.UserIO;
import com.codealong.vendingmachine.ui.UserIOConsoleImpl;
import com.codealong.vendingmachine.ui.VendingMachineView;
import com.codealong.vendingmachine.service.ServiceLayer;
import com.codealong.vendingmachine.service.ServiceLayerImpl;
import com.codealong.vendingmachine.controller.VendingMachineController;
import com.codealong.vendingmachine.dao.VendingMachineDaoFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 *
 * @author umair
 */
public class App {
    public static void main(String[] args){
  //     VendingMachineDao dao = new VendingMachineDaoFileImpl();
  //      ServiceLayer service = new ServiceLayerImpl(dao);
  //      UserIO io = new UserIOConsoleImpl();
  //      VendingMachineView view = new VendingMachineView(io);
  //      VendingMachineController controller = new VendingMachineController(service, view);
  //      controller.run();
       
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationcontext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
