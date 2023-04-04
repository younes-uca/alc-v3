package ma.learn.quiz.service.impl;


import java.util.Arrays;
import java.util.List;

public class AbstractService {

    List<String> malliciousCode = Arrays.asList(",", "AND", "OR", "WHERE","--",">","<","'");

    public String init(String className) {
        return "SELECT item FROM " + className + " item WHERE 1=1";
    }

    public String addCriteria(String key, Object value, String operator) {
        if(!containsMalicous(value)){
            if (value != null && !value.toString().isEmpty()) {
                if (value instanceof String && operator.equals("LIKE")) {
                    return " AND item." + key + " " + operator + " '%" + value.toString() + "%'";
                } else {
                    return " AND item." + key + " " + operator + " '" + value.toString() + "'";
                }
            }
        }
        return "";
    }

    public boolean containsMalicous(Object... params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (malliciousCode.contains(params[i].toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    public String addCriteria(String key, Object value) {
        return addCriteria(key, value, "=");
    }

    public String addCriteria(String key, Object valueMin, Object valueMax) {
        String query = addCriteria(key, valueMin, ">=");
        query += addCriteria(key, valueMax, "<=");
        return query;
    }

}