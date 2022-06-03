package com.revature.SoSoulGood.Services;

import com.revature.SoSoulGood.Dao.MenuDao;
import com.revature.SoSoulGood.Exceptions.ResourcePersistanceException;
import com.revature.SoSoulGood.Models.Menu;
import com.revature.SoSoulGood.Models.CreditCard;
import com.revature.SoSoulGood.Util.Logger;

import java.util.List;

public class MenuServices {


        private MenuDao menuDao;
        private Logger logger = Logger.getLogger();
    public MenuServices(MenuDao menuDao) {
            this.menuDao=menuDao;
        }
        public List<Menu> readAll(){

            List<Menu> menu = menuDao.findAll();
            return menu;
        }


        public Menu readById(String id) throws ResourcePersistanceException {

            Menu menu = menuDao.findById(id);
            return menu;
        }

        public Menu update(Menu updatedMenu) {
            if (!menuDao.update(updatedMenu)) {
                return null;
            }

            return updatedMenu;
        }

        public boolean delete(String username) {
            return menuDao.delete(username);
        }

        public boolean validateIdNotUsed(String username) {
            return menuDao.checkEmail(username);
        }

        public Menu create(Menu newMenu) {
            System.out.println("Menu trying to be registered: " + newMenu);
            logger.info("Menu trying to be registered: ");

            System.out.println("before issue?");
            Menu persistedMenu = menuDao.create(newMenu);
            System.out.println("before issue????");

            if (persistedMenu == null) {
                throw new ResourcePersistanceException("Menu was not persisted to the database upon registration");
            }
//        logger.info("Menu has been persisted: " + newCustomer);
            return persistedMenu;
        }
    }


