package ua.kpi.tef.util;

import ua.kpi.tef.model.UserMeal;
import ua.kpi.tef.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> datesOfMeal = new HashMap<>();
        ArrayList<UserMealWithExceed> userMealWithExceeds = new ArrayList<>();
        for (UserMeal meal : mealList) {
            datesOfMeal.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (a, b) -> a + b);
        }

        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        datesOfMeal.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return userMealWithExceeds;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByStreams(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> datesOfMeal = mealList.stream()
                .collect(Collectors.toMap(u -> u.getDateTime().toLocalDate(), UserMeal::getCalories, (u1, u2) -> u1 + u2));

        List<UserMealWithExceed> list = new ArrayList<>();
        mealList.stream().filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime))
                .filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime))
                .forEach(u -> list.add(new UserMealWithExceed(u.getDateTime(), u.getDescription(), u.getCalories(),
                        datesOfMeal.get(u.getDateTime().toLocalDate()) > caloriesPerDay)));
        return list;
    }
}
