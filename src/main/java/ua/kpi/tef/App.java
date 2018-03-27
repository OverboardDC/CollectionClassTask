package ua.kpi.tef;

import ua.kpi.tef.model.UserMeal;
import ua.kpi.tef.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 800),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 100),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 910)
        );
        System.out.println("Executed usually:");
        UserMealsUtil.getFilteredWithExceeded(mealList, LocalTime.of(9, 0), LocalTime.of(12, 0), 2000)
                .forEach(System.out::println);
        System.out.println("Executed by streams:");
        UserMealsUtil.getFilteredWithExceededByStreams(mealList, LocalTime.of(9, 0), LocalTime.of(12, 0), 2000)
                .forEach(System.out::println);
    }
}
