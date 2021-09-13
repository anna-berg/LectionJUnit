package DateTime;

import java.time.*;
import java.time.temporal.ChronoUnit;
/*
* стандарты времени:
* GMT - среднее солнечное время нулевого мередиана (старый стандарт)
*       вычесляется астрономически по полож. Земли относительно других обьектов.
* UTC - новый стандарт времени , расчитывается по атомным часам.
*
* */
public class DateTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        long toEpochMilli = now.toInstant().toEpochMilli();// получаем всемирное время, без смещения . переводим в милисек. по ЮниксТайм
        ZonedDateTime plusOneDay = now.plus(1L, ChronoUnit.DAYS);
        ZonedDateTime nullData = now.truncatedTo(ChronoUnit.DAYS); //обнулить дату до дня
        int dayOfMonth = now.getDayOfMonth();

        System.out.println(LocalDateTime.now(Clock.systemUTC()));
        System.out.println(LocalDateTime.now(ZoneOffset.UTC)); //время и дата без зоны
        System.out.println(LocalTime.now());// просто время
    }
}
