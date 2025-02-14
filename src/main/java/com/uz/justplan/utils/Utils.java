package com.uz.justplan.utils;

import com.uz.justplan.lookup.ReleaseIteration;
import com.uz.justplan.lookup.WorkingHourScope;
import com.uz.justplan.plan.Release;
import com.uz.justplan.plan.ReleaseWorkingDay;
import com.uz.justplan.resources.CompanyWorkingHour;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static void getcountriesdata() {
        String apiUrl = "https://restcountries.com/v3.1/all";

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine()); // Prints JSON response
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Object> getCountries() {
        // Fetch all countries with  2 char code, 3 char code, name, region
        // population, area, capital, tld, currency, nativeName, numericCode,
        // and officialName
        //...
        return new ArrayList<>(); // Replace with actual data

        // phone code, currency, language, and capital and weekends, holidays
        //...
    }

    public static void getPublicHolidays(String countryCode, int year) {
        String apiUrl = "https://date.nager.at/api/v3/PublicHolidays/" + year + "/" + countryCode;

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<DayOfWeek> getWeekendDays(String countryCode) {
        Map<String, List<DayOfWeek>> weekendMap = new HashMap<>();

        weekendMap.put("US", List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)); // USA
        weekendMap.put("AE", List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)); // UAE
        weekendMap.put("IN", List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)); // India
        weekendMap.put("SA", List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)); // Saudi Arabia

        return weekendMap.getOrDefault(countryCode, List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    }

    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        ReleaseIteration interval = ReleaseIteration.MONTHLY;
        List<DayOfWeek> weekends = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        List<LocalDate> holidays = Arrays.asList(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 25));

        //List<Release> scheduledDates = nextReleases(startDate, endDate, interval, weekends, holidays);
        //scheduledDates.forEach(System.out::println);
    }


    public static List<Release> nextReleases(LocalDate productStartDate,
                                             LocalDate productEndDate,//nullable
                                             ReleaseIteration interval, List<DayOfWeek> weekends,
                                             List<LocalDate> holidays,
                                             List<LocalDate> forcedWorkingDays,
                                             List<CompanyWorkingHour> companyWorkingHours,
                                             LocalDate lastReleaseEndDate,//nullable
                                             int lastRleaseVersion) {

        //Period step = Period.ofMonths(1);
        //        can be used with untill
        Assert.notNull(productStartDate, "productStartDate is required");
        Assert.notNull(weekends, "weekends is required");
        Assert.notNull(holidays, "holidays is required");
        Assert.notNull(interval, "interval is required");
        Assert.notNull(forcedWorkingDays, "forcedWorkingDays is required");
        Assert.notNull(companyWorkingHours, "companyWorkingHours is required");

        Map<WorkingHourScope, List<CompanyWorkingHour>> companyWorkingHourMap = companyWorkingHours
                .stream().collect(Collectors.groupingBy(CompanyWorkingHour::getScope));

        List<Release> releases = new ArrayList<>();
        int nextReleaseRequiredCount = 10;
        for (int i = 0; i < nextReleaseRequiredCount; i++) { // Generate 10 iterations

            LocalDate iterationStartDate = productStartDate.plusMonths(i * 1);
            if (lastReleaseEndDate != null && iterationStartDate.isBefore(lastReleaseEndDate)) {
                nextReleaseRequiredCount++;
                continue;
            }
            LocalDate iterationEndDate = productStartDate.plusMonths((i + 1) * 1).plusDays(-1);
            switch (interval) {
                case BI_MONTHLY:
                    iterationStartDate = productStartDate.plusMonths(i * 2);
                    iterationEndDate = iterationStartDate.plusMonths(2);
                case MONTHLY:
                    iterationStartDate = productStartDate.plusMonths(i * 1);
                    iterationEndDate = iterationStartDate.plusMonths(1);
                    break;
                case QUARTERLY:
                    iterationStartDate = productStartDate.plusMonths(i * 3);
                    iterationEndDate = iterationStartDate.plusMonths(3);
                    break;
                case ANNUAL:
                    iterationStartDate = productStartDate.plusYears(i * 1);
                    iterationEndDate = iterationStartDate.plusYears(1);
                    break;
                case SEMI_ANNUAL:
                    iterationStartDate = productStartDate.plusMonths(i * 6);
                    iterationEndDate = iterationStartDate.plusMonths(6);
                    break;
                case WEEKLY:
                    iterationStartDate = productStartDate.plusWeeks(i * 1);
                    iterationEndDate = iterationStartDate.plusWeeks(1);
                    break;
                case BI_WEEKLY:
                    iterationStartDate = productStartDate.plusWeeks(i * 2);
                    iterationEndDate = iterationStartDate.plusWeeks(2);
                    break;
                case TRI_WEEKLY:
                    iterationStartDate = productStartDate.plusWeeks(i * 3);
                    iterationEndDate = iterationStartDate.plusWeeks(3);
                    break;
                default:
                    break;
            }
            if (iterationStartDate != null) {
                while (!isWorkingDay(iterationStartDate, weekends, holidays, forcedWorkingDays)) {
                    iterationStartDate = iterationStartDate.plusDays(1);
                }
            }
            if (iterationEndDate != null) {
                iterationEndDate = iterationEndDate.plusDays(-1);
                while (!isWorkingDay(iterationEndDate, weekends, holidays, forcedWorkingDays)
                        || (productEndDate != null && iterationEndDate.isAfter(productEndDate))) {
                    iterationEndDate = iterationEndDate.plusDays(-1);
                }
            }

            if (productEndDate != null && (iterationStartDate.equals(productEndDate) || iterationStartDate.isAfter(productEndDate))) {
                break;
            }
            Release release = new Release();
            release.setStartDate(iterationStartDate);
            release.setEndDate(iterationEndDate);
            release.setActive(true);
            release.setName(iterationStartDate.getMonth().name() + "-" + iterationStartDate.getDayOfMonth()
                    + " to " + iterationEndDate.getMonth().name() + "-" + iterationEndDate.getDayOfMonth());
            release.setVersion(releases.size() + lastRleaseVersion + 1);
            //release.setProductId();

            // find working days and exclude holidays between start and end dates
            //LocalDate tempDate = iterationStartDate;
            List<ReleaseWorkingDay> workingDays = new ArrayList<>();
            iterationStartDate.datesUntil(iterationEndDate.plusDays(1))
                    .filter(expectedWorkingDay -> isWorkingDay(expectedWorkingDay, weekends, holidays, forcedWorkingDays))
                    .forEach(expectedWorkingDay -> {
                        ReleaseWorkingDay day = new ReleaseWorkingDay();
                        day.setWorkingDate(expectedWorkingDay);
                        day.setMinDate(getWorkingMinutes(expectedWorkingDay, companyWorkingHourMap));
                        workingDays.add(day);
                    });
            release.setWorkingDays(workingDays.size());
            release.setWorkingDaysList(workingDays);
            releases.add(release);
            // Print the next start date and iteration date for debugging purposes.
            // This will help you visualize the generated dates.
            // Note: This will not be visible in the final output.
            System.out.println("Next Start Date: " + iterationStartDate);
            System.out.println("Next Iteration Date: " + iterationEndDate);
            System.out.println("Working days: " + workingDays.size());
            if (productEndDate != null && iterationEndDate.equals(productEndDate)) {
                break;
            }

        }
        System.out.println("Releases: " + releases);
        return releases;
    }

    private static boolean isWorkingDay(LocalDate date, List<DayOfWeek> weekends,
                                        List<LocalDate> holidays,
                                        List<LocalDate> forcedWorkingDays) {
        if (forcedWorkingDays.contains(date)) {
            return true;
        }
        return !weekends.contains(date.getDayOfWeek()) && !holidays.contains(date);
    }

    private static int getWorkingMinutes(LocalDate date, Map<WorkingHourScope, List<CompanyWorkingHour>> workingMinutes) {

        int minutes = workingMinutes.get(WorkingHourScope.SPECIFIC_DATE).stream().filter(r -> r.getEventDate().equals(date))
                .findFirst().orElse(new CompanyWorkingHour()).getMinutes();
        if (minutes > 0) {
            return minutes;
        }
        minutes = workingMinutes.get(WorkingHourScope.DATE_RANGE).stream().filter(r ->
                        (r.getStartDate().isBefore(date) || r.getStartDate().equals(date))
                                && (r.getEndDate().isAfter(date) || r.getEndDate().equals(date)))
                .findFirst().orElse(new CompanyWorkingHour()).getMinutes();
        if (minutes > 0) {
            return minutes;
        }
        minutes = workingMinutes.get(WorkingHourScope.WEEK_DAY).stream().filter(r ->
                        r.getDayOfWeek().equals(date.getDayOfWeek()))
                .findFirst().orElse(new CompanyWorkingHour()).getMinutes();
        if (minutes > 0) {
            return minutes;
        }
        minutes = workingMinutes.get(WorkingHourScope.DEFAULT).get(0).getMinutes();
        return minutes;
    }

    public static String generateCode() {
        SecureRandom secureRandom = new SecureRandom();
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.format("%06d", otp);
    }
    public static LocalDate getLocalDateFromString(String dateStr, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(dateStr, formatter);
        } catch (Exception e) {
        }
        return null;
    }

    public static double getDoubleValue(String str) {
        if (Validation.isEmpty(str)) {
            return 0;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String getNameFromEmail(String email) {
        String namePart = email.split("@")[0].replaceAll("\\d", "")
                .replaceAll("[\\.\\-_]+", " ");
        return toTitleCase(namePart);
    }

    public static String toTitleCase(String input) {
        return Arrays.stream(input
                        .toLowerCase()
                        .split(" "))
                .map(word -> Character.toTitleCase(word.charAt(0))
                        + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
