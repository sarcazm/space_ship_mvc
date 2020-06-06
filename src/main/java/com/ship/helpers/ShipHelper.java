package com.ship.helpers;



import com.ship.entity.Ship;
import com.ship.entity.ShipType;
import com.ship.exceptions.ShipValidateException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class ShipHelper {

    public ShipHelper() {
    }

    public Double countRating(Ship ship){
        double k = 1.0;
        long year = 3019;
        if (!ship.getIsUsed())k = 0.5;
        Double result = (80 * ship.getSpeed() * k) / (year - ship.getProdDate() + 1);
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();

    }

    private boolean isString(String str){
        if (str.equals("name") || str.equals("planet") || str.equals("ship_type")) return true;
        return false;
    }

    public String createHqlQuery(Map<String, String> allParam) throws NullPointerException{

        Set<String> params = new HashSet<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String, String> entry : allParam.entrySet()){
            if (entry.getValue().equals(""))
                continue;
            params.add(entry.getKey());
            values.add(entry.getValue());
        }
        if (params.isEmpty())throw new NullPointerException();
        int i = 0;
        String result = "";
        StringBuffer hqlBuffer = new StringBuffer("from Ship sh where ");
        StringBuffer addBuffer = new StringBuffer("");
        //эти бульки нужны для and и для того, чтобы можно было не соблюдать порядок необходимых характеристик в URL
        boolean andInAfterBefore = false;
        boolean andInMinMaxSpeed = false;
        boolean andInMinMaxCrewSize = false;
        boolean andInMinMaxRating = false;
        for (String s : params){
            //minRating and maxRating
            if (s.equals("minRating")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("rating > " + values.get(i));
                i++;
                andInMinMaxRating = true;
                continue;
            }
            if (s.equals("maxRating")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("rating < " + values.get(i));
                i++;
                andInMinMaxRating = true;
                continue;
            }
            //minCrewSize and maxCrewSize
            if (s.equals("minCrewSize")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("crew_size > " + values.get(i));
                i++;
                andInMinMaxCrewSize = true;
                continue;
            }
            if (s.equals("maxCrewSize")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("crew_size < " + values.get(i));
                i++;
                andInMinMaxCrewSize = true;
                continue;
            }
            //min and max speed
            if (s.equals("minSpeed")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("speed > " + values.get(i));
                i++;
                andInMinMaxSpeed = true;
                continue;
            }
            if (s.equals("maxSpeed")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("speed < " + values.get(i));
                andInMinMaxSpeed = true;
                i++;
                continue;
            }
            //after and before date
            if (s.equals("after")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("prod_date > " + values.get(i));
                i++;
                andInAfterBefore = true;
                continue;
            }
            if (s.equals("before")){
                if (andInMinMaxSpeed || andInAfterBefore || andInMinMaxCrewSize || andInMinMaxRating)
                    addBuffer.append(" and ");
                addBuffer.append("prod_date < " + values.get(i));
                andInAfterBefore = true;
                i++;
                continue;
            }
            if (isString(s))
                hqlBuffer.append("sh.").append(s).append(" = '").append(values.get(i)).append("'");
            else
                hqlBuffer.append("sh.").append(s).append(" = ").append(values.get(i));
            i++;
            if (i < values.size())
                hqlBuffer.append(" and ");
        }
        //если последний пробел в строке, значит после where ничего не стоит и не нужно append AND
        if (hqlBuffer.lastIndexOf(" ") == hqlBuffer.length() - 1)
            result = hqlBuffer.append(addBuffer).toString();
        else
            result = hqlBuffer.append(" and ").append(addBuffer).toString();
        System.out.println("result = " + result);
        return result;
    }

    public String smthNotforUpdateMethod(Ship ship){

        String not = "";
        if (ship.getName() != null){
            if (ship.getName().isEmpty())return "Invalid: the name is empty";
            if (ship.getName().length() > 50)return "Invalid: the number of characters in the name is > 50";
        }
        if (ship.getPlanet() != null){
            if (ship.getPlanet().isEmpty())return "Invalid: the planet is empty";
            if (ship.getPlanet().length() > 50)return "Invalid: the number of characters in the planet is > 50";
        }
        if (ship.getShip_type() != null){
            if (!(ship.getShip_type() == ShipType.COMBAT || ship.getShip_type() == ShipType.TRANSPORT))return "invalid type";
        }
        if (ship.getProdDate() != null){
            if (ship.getProdDate() < 0 )return "invalid date";
        }
        if (ship.getSpeed() != null){
            if (ship.getSpeed() > 0.99 || ship.getSpeed() < 0.01)return "Invalid: the value of speed is incorrect";
        }
        if (ship.getCrewSize() != null){
            if (ship.getCrewSize() > 9999 || ship.getCrewSize() < 1)return "Invalid: the quantity passengers is incorrect";
        }
        return not;
    }

    public void smthNotforAddMethod(Ship ship) throws ShipValidateException{
        if (ship.getName() == null || ship.getName().isEmpty())
            throw new ShipValidateException("invalid name");
        if (ship.getPlanet() == null || ship.getPlanet().isEmpty())
            throw new ShipValidateException("invalid planet");
        if (ship.getName().length() > 50)
            throw new ShipValidateException("Invalid: the number of characters in the name is > 50");
        if (ship.getPlanet().length() > 50)
            throw new ShipValidateException("Invalid: the number of characters in the planet is > 50");
        if (ship.getShip_type() == null)
            throw new ShipValidateException("not specified type");
        if (!(ship.getShip_type() == ShipType.COMBAT || ship.getShip_type() == ShipType.TRANSPORT))
            throw new ShipValidateException("invalid type");
        if (ship.getProdDate() == null)
            throw new ShipValidateException("not specified date");
        if (ship.getProdDate() < 0 )
            throw new ShipValidateException("invalid date");
        //тут дообавить обработку определенных периодов годов
        if (ship.getSpeed() == null)
            throw new ShipValidateException("not specified  speed");
        if (ship.getSpeed() > 0.99 || ship.getSpeed() < 0.01)
            throw new ShipValidateException("Invalid: the value of speed is incorrect");
        if (ship.getCrewSize() == null)
            throw new ShipValidateException("not specified quantity passengers");
        if (ship.getCrewSize() > 9999 || ship.getCrewSize() < 1)
            throw new ShipValidateException("Invalid: the quantity passengers is incorrect");
    }

    public Ship updateByFields(Ship result, Ship newDataShip){

        if (newDataShip.getName() != null)
            result.setName(newDataShip.getName());
        if (newDataShip.getPlanet() != null)
            result.setPlanet(newDataShip.getPlanet());
        if (newDataShip.getShip_type() != null)
            result.setShip_type(newDataShip.getShip_type());
        if (newDataShip.getProdDate() != null)
            result.setProdDate(newDataShip.getProdDate());
        if (newDataShip.getName() != null)
            result.setName(newDataShip.getName());
        if (newDataShip.getIsUsed() != null)
            result.setIsUsed(newDataShip.getIsUsed());
        if (newDataShip.getSpeed() != null)
            result.setSpeed(newDataShip.getSpeed());
        if (newDataShip.getCrewSize() != null)
            result.setCrewSize(newDataShip.getCrewSize());
        result.setRating(countRating(result));

        return result;
    }
}
