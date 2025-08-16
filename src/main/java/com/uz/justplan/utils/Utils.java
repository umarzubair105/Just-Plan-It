package com.uz.justplan.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uz.justplan.lookup.ReleaseIteration;
import com.uz.justplan.lookup.WorkingHourScope;
import com.uz.justplan.plan.Release;
import com.uz.justplan.plan.ReleaseWorkingDay;
import com.uz.justplan.resources.CompanyWorkingHour;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static void getcountriesdata() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://restcountries.com/v3.1/all?fields=name,cca2,cca3,region,flags,subregion,currencies,languages,idd";

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            Map[] countries = mapper.readValue(response.getBody(), Map[].class);
            String sql = "insert into `country` (id, name, countryCode,countryCode3, phoneCode, region," +
                    "subRegion,flag,currency,language,active) values ";
            String values = " (%s,'%s','%s','%s','%s','%s','%s','%s','%s','%s',1), ";
            int i = 1;
            for (Map c : countries) {
                //{flags={png=https://flagcdn.com/w320/tg.png,
                // svg=https://flagcdn.com/tg.svg, alt=The flag of Togo is composed of five equal horizontal bands of green alternating with yellow. A red square bearing a five-pointed white star is superimposed in the canton.},
                // name={common=Togo, official=Togolese Republic, nativeName={fra={official=République togolaise, common=Togo}}},
                // cca2=TG, cca3=TGO, currencies={XOF={name=West African CFA franc, symbol=Fr}},
                // region=Africa, subregion=Western Africa, languages={fra=French}}
                //"idd": {  "root": "+2","suffixes": [
                //                "38"]        },
                Map name = (Map) c.get("name");
                String cca2 = (String) c.get("cca2");
                String cca3 = (String) c.get("cca3");
                Map idd = (Map) c.get("idd");
                String phoneCode = idd == null ? "" : idd.toString();
                String region = (String) c.get("region");
                String subregion = (String) c.get("subregion");
                Map flags = (Map) c.get("flags");
                Map currencies = (Map) c.get("currencies");
                String currency = currencies == null ? "" : currencies.toString();
                Map languages = (Map) c.get("languages");
                String language = languages == null ? "" : languages.toString();
                String line = String.format(values, i++,
                        name == null ? "" : (String) name.get("common"),
                        cca2, cca3, phoneCode, region,
                        subregion,
                        flags == null ? "" : (String) flags.get("png"),
                        currency.replaceAll("'", "''"),
                        language.replaceAll("'", "''"));

                //System.out.println(c);
                //System.out.println(line);
                sql += line;
            }
            System.out.println(sql);

            System.out.println("Status: " + response.getStatusCode());
            System.out.println("Response: ");
            //System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        //getcountriesdata();
        try {
            Map<Integer, String> m = new HashMap<>();
            m.put(1, "aa");
            m.put(100000000, "aa");
            System.out.println("iterationStartDate SEMI_ANNUAL:" + m.get(1));
            System.out.println("iterationStartDate SEMI_ANNUAL:" + m.get(10));
            System.out.println("iterationStartDate SEMI_ANNUAL:" + m.get(100000000));
            //System.out.println("iterationStartDate WEEKLY:"+  getLastDateOfWeek(iterationStartDate, weekends));
            //System.out.println("iterationStartDate BI_WEEKLY:"+  getLastDateOfWeek(iterationStartDate.plusWeeks(1), weekends));
            //System.out.println("iterationStartDate TRI_WEEKLY:"+  getLastDateOfWeek(iterationStartDate.plusWeeks(2), weekends));
            //System.out.println(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        ReleaseIteration interval = ReleaseIteration.MONTHLY;
        List<DayOfWeek> weekends = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
        List<LocalDate> holidays = Arrays.asList(LocalDate.of(2024, 1, 1),
                LocalDate.of(2024, 12, 25));
*/
        //List<Release> scheduledDates = nextReleases(startDate, endDate, interval, weekends, holidays);
        //scheduledDates.forEach(System.out::println);
    }

    private static LocalDate getLastDateOfMonth(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        LocalDate lastDay = yearMonth.atEndOfMonth();
        System.out.println("Last day of month: " + lastDay); // Output: 2025-05-31
        return lastDay;
    }

    private static LocalDate getLastDateOfQuarter(LocalDate date) {
        int month = date.getMonthValue();
        int year = date.getYear();
        int lastMonthOfQuarter;
        if (month <= 3) {
            lastMonthOfQuarter = 3; // Q1
        } else if (month <= 6) {
            lastMonthOfQuarter = 6; // Q2
        } else if (month <= 9) {
            lastMonthOfQuarter = 9; // Q3
        } else {
            lastMonthOfQuarter = 12; // Q4
        }
        YearMonth endMonth = YearMonth.of(year, lastMonthOfQuarter);
        LocalDate lastDay = endMonth.atEndOfMonth();
        System.out.println("Last day of quarter: " + lastDay); // Output: 2025-05-31
        return lastDay;
    }

    private static LocalDate getLastDateOfSemiYear(LocalDate date) {
        int month = date.getMonthValue();
        int year = date.getYear();
        int lastMonthOfQuarter;
        if (month <= 6) {
            lastMonthOfQuarter = 6; // Q2
        } else {
            lastMonthOfQuarter = 12; // Q4
        }
        YearMonth endMonth = YearMonth.of(year, lastMonthOfQuarter);
        LocalDate lastDay = endMonth.atEndOfMonth();
        System.out.println("Last day of semi year: " + lastDay); // Output: 2025-05-31
        return lastDay;
    }

    private static LocalDate getLastDateOfYear(LocalDate date) {
        int year = date.getYear(); // or specify a year like 2025
        LocalDate lastDay = LocalDate.of(year, 12, 31);
        System.out.println("Last day of year: " + lastDay); // Output: 2025-05-31
        return lastDay;
    }

    private static LocalDate getLastDateOfWeek(LocalDate date, Set<DayOfWeek> weekends) {
        DayOfWeek weekend = weekends.stream().findFirst().orElse(DayOfWeek.SUNDAY);
        LocalDate endOfWeek = date.with(TemporalAdjusters.nextOrSame(weekend));
        System.out.println("End of Week: " + endOfWeek);
        return endOfWeek;
    }

    /*    private static LocalDate getStartDateOfWeek(LocalDate date, Set<DayOfWeek> weekends) {
            LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            System.out.println("Start of Week: " + startOfWeek);
            return startOfWeek;
        }*/
    public static List<Release> nextReleases(LocalDate productStartDate,
                                             LocalDate productEndDate,//nullable
                                             ReleaseIteration interval, Set<DayOfWeek> weekends,
                                             Set<LocalDate> holidays,
                                             Set<LocalDate> forcedWorkingDays,
                                             Set<CompanyWorkingHour> companyWorkingHours,
                                             LocalDate lastReleaseEndDate,//nullable
                                             int lastReleaseVersion) {

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
        //int nextReleaseRequiredCount = 1;// Generate so many iterations
        //for (int i = 0; i < nextReleaseRequiredCount; i++) {

        LocalDate iterationStartDate = lastReleaseEndDate == null ? productStartDate : lastReleaseEndDate.plusDays(1);
        while (!isWorkingDay(iterationStartDate, weekends, holidays, forcedWorkingDays)) {
            iterationStartDate = iterationStartDate.plusDays(1);
        }
        System.out.println("iterationStartDate:" + iterationStartDate);
        LocalDate iterationEndDate = null;
        switch (interval) {
            case BI_MONTHLY:
                iterationEndDate = getLastDateOfMonth(iterationStartDate.plusMonths(1));
                break;
            case MONTHLY:
                iterationEndDate = getLastDateOfMonth(iterationStartDate);
                break;
            case QUARTERLY:
                iterationEndDate = getLastDateOfQuarter(iterationStartDate);
                break;
            case ANNUAL:
                iterationEndDate = getLastDateOfYear(iterationStartDate);
                break;
            case SEMI_ANNUAL:
                iterationEndDate = getLastDateOfSemiYear(iterationStartDate);
                break;
            case WEEKLY:
                iterationEndDate = getLastDateOfWeek(iterationStartDate, weekends);
                break;
            case BI_WEEKLY:
                iterationEndDate = getLastDateOfWeek(iterationStartDate.plusWeeks(1), weekends);
                break;
            case TRI_WEEKLY:
                iterationEndDate = getLastDateOfWeek(iterationStartDate.plusWeeks(2), weekends);
                break;
            default:
                break;
        }
        if (iterationEndDate != null) {
            iterationEndDate = iterationEndDate.plusDays(-1);
            while (!isWorkingDay(iterationEndDate, weekends, holidays, forcedWorkingDays)
                    || (productEndDate != null && iterationEndDate.isAfter(productEndDate))) {
                iterationEndDate = iterationEndDate.plusDays(-1);
            }
        }

        if (productEndDate != null && (iterationStartDate.equals(productEndDate) || iterationStartDate.isAfter(productEndDate))) {
            Assert.isTrue(false, "Release can not be started after Product end date.");
        }
        Release release = new Release();
        release.setStartDate(iterationStartDate);
        release.setEndDate(iterationEndDate);
        release.setActive(true);
        release.setName(toTitleCase(iterationStartDate.getMonth().name() + "-" + iterationStartDate.getDayOfMonth()
                + " to " + iterationEndDate.getMonth().name() + "-" + iterationEndDate.getDayOfMonth()));
        release.setVersion(releases.size() + lastReleaseVersion + 1);
        //release.setProductId();

        // find working days and exclude holidays between start and end dates
        //LocalDate tempDate = iterationStartDate;
        List<ReleaseWorkingDay> workingDays = new ArrayList<>();
        iterationStartDate.datesUntil(iterationEndDate.plusDays(1))
                .filter(expectedWorkingDay -> isWorkingDay(expectedWorkingDay, weekends, holidays, forcedWorkingDays))
                .forEach(expectedWorkingDay -> {
                    ReleaseWorkingDay day = new ReleaseWorkingDay();
                    day.setWorkingDate(expectedWorkingDay);
                    day.setMinutes(getWorkingMinutes(expectedWorkingDay, companyWorkingHourMap));
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
        //if (productEndDate != null && iterationEndDate.equals(productEndDate)) {
        //  break;
        //}

        //}
        System.out.println("Releases: " + releases);
        return releases;
    }

    public static List<Release> nextReleasesDepr(LocalDate productStartDate,
                                                 LocalDate productEndDate,//nullable
                                                 ReleaseIteration interval, Set<DayOfWeek> weekends,
                                                 Set<LocalDate> holidays,
                                                 Set<LocalDate> forcedWorkingDays,
                                                 Set<CompanyWorkingHour> companyWorkingHours,
                                                 LocalDate lastReleaseEndDate,//nullable
                                                 int lastReleaseVersion) {

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
        int nextReleaseRequiredCount = 1;// Generate so many iterations
        for (int i = 0; i < nextReleaseRequiredCount; i++) {

            LocalDate iterationStartDate = productStartDate.plusMonths(i * 1);
            System.out.println("iterationStartDate:" + iterationStartDate);
            System.out.println("lastReleaseEndDate:" + lastReleaseEndDate);
            if (lastReleaseEndDate != null && iterationStartDate.isBefore(lastReleaseEndDate)) {
                nextReleaseRequiredCount++;
                continue;
            }
            LocalDate iterationEndDate = productStartDate.plusMonths((i + 1) * 1).plusDays(-1);
            switch (interval) {
                case BI_MONTHLY:
                    iterationStartDate = productStartDate.plusMonths(i * 2);
                    iterationEndDate = iterationStartDate.plusMonths(2);
                    break;
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
            release.setName(toTitleCase(iterationStartDate.getMonth().name() + "-" + iterationStartDate.getDayOfMonth()
                    + " to " + iterationEndDate.getMonth().name() + "-" + iterationEndDate.getDayOfMonth()));
            release.setVersion(releases.size() + lastReleaseVersion + 1);
            //release.setProductId();

            // find working days and exclude holidays between start and end dates
            //LocalDate tempDate = iterationStartDate;
            List<ReleaseWorkingDay> workingDays = new ArrayList<>();
            iterationStartDate.datesUntil(iterationEndDate.plusDays(1))
                    .filter(expectedWorkingDay -> isWorkingDay(expectedWorkingDay, weekends, holidays, forcedWorkingDays))
                    .forEach(expectedWorkingDay -> {
                        ReleaseWorkingDay day = new ReleaseWorkingDay();
                        day.setWorkingDate(expectedWorkingDay);
                        day.setMinutes(getWorkingMinutes(expectedWorkingDay, companyWorkingHourMap));
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

    private static boolean isWorkingDay(LocalDate date, Set<DayOfWeek> weekends,
                                        Set<LocalDate> holidays,
                                        Set<LocalDate> forcedWorkingDays) {
        if (forcedWorkingDays.contains(date)) {
            return true;
        }
        return !weekends.contains(date.getDayOfWeek()) && !holidays.contains(date);
    }

    private static int getWorkingMinutes(LocalDate date, Map<WorkingHourScope, List<CompanyWorkingHour>> workingMinutes) {

        int minutes = workingMinutes.getOrDefault(WorkingHourScope.SPECIFIC_DATE, new ArrayList<>()).stream()
                .filter(r -> r.getEventDate().equals(date))
                .findFirst().orElse(new CompanyWorkingHour()).getMinutes();
        if (minutes > 0) {
            return minutes;
        }
        minutes = workingMinutes.getOrDefault(WorkingHourScope.DATE_RANGE, new ArrayList<>()).stream().filter(r ->
                        !date.isBefore(r.getStartDate()) && !date.isAfter(r.getEndDate()))
                .findFirst().orElse(new CompanyWorkingHour()).getMinutes();
        if (minutes > 0) {
            return minutes;
        }
        minutes = workingMinutes.getOrDefault(WorkingHourScope.WEEK_DAY, new ArrayList<>()).stream().filter(r ->
                        r.getDayOfWeek().equals(date.getDayOfWeek()))
                .findFirst().orElse(new CompanyWorkingHour()).getMinutes();
        if (minutes > 0) {
            return minutes;
        }
        minutes = workingMinutes.getOrDefault(WorkingHourScope.DEFAULT, new ArrayList<>()).get(0).getMinutes();
        return minutes;
    }

    public static boolean isWithinOrEqualToStartEndDates(LocalDate date, LocalDate startDate,
                                                         LocalDate endDate) {
        return date.isEqual(startDate) || date.isEqual(endDate)
                || (date.isAfter(startDate) && date.isBefore(endDate));
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
