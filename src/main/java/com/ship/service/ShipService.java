package com.ship.service;


import com.ship.entity.Ship;

import com.ship.exceptions.ShipValidateException;
import com.ship.helpers.ShipHelper;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
//@Slf4j
public class ShipService {
    private final SessionFactory sessionFactory;
    private final ShipHelper shipHelper;

    @Autowired
    public ShipService(SessionFactory sessionFactory, ShipHelper shipHelper) {
        this.sessionFactory = sessionFactory;
        this.shipHelper = shipHelper;
    }



    public Ship getById(Integer id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ship result = session.get(Ship.class, id);
        //log.info("IN findById - ship: {} found by id: {}", result, id);
        session.flush();
        session.close();

        return result;
    }

    public void add(Ship ship){
        try {
            shipHelper.smthNotforAddMethod(ship);
            ship.setRating(shipHelper.countRating(ship));
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            //log.info("IN add - ship: {}", ship);
            if (ship.getId() != null)
                session.replicate(ship, ReplicationMode.OVERWRITE);
            session.save(ship);
            session.flush();
            session.close();
        }catch (ShipValidateException e){
            e.printStackTrace();
        }
    }

    public List<Ship> getAll(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Ship> result = session.createCriteria(Ship.class).list();
        //log.info("IN findById - ship: {} found by id: {}", result, id);
        session.flush();
        session.close();
        return result;
    }

    public void updateById(Integer id, Ship newDataShip){
        try {
            shipHelper.smthNotforAddMethod(newDataShip);
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Ship result = session.get(Ship.class, id);
            result = shipHelper.updateByFields(result, newDataShip);
            session.update(result);
            session.flush();
            session.close();

        }catch (ShipValidateException e){
            e.printStackTrace();
        }
    }



    public Ship deleteById(Integer id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Ship result = session.get(Ship.class, id);
        //log.info("IN findById - ship: {} found by id: {}", result, id);
        session.remove(result);
        session.flush();
        session.close();
        return result;
    }

    public List<Ship> getCountBy(Map<String, String> allParam) {
        String hql;
        List<Ship> resultList = new ArrayList<>();

        try {
            hql = shipHelper.createHqlQuery(allParam);
        }catch (NullPointerException e){
            System.out.println("invalid data");
            return resultList;
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        resultList = query.list();
        session.flush();
        session.close();
        return resultList;

    }






}
