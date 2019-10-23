package lesson1;

import kotlin.NotImplementedError;
import kotlin.Pair;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) throws Exception {
       List<Pair<Integer, String>> timeDate = new ArrayList<>();
        List<Integer> minute = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(inputName));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("AM")) {
                String stringI = line.replaceAll(" AM", "");
                String[] split = stringI.split(":");
                int hour = Integer.parseInt(split[0]);
                int minutes = Integer.parseInt(split[1]);
                int seconds = Integer.parseInt(split[2]);
                if (hour == 12) hour = 0;
                int secondsTime = (hour * 60 + minutes) * 60 + seconds;
                timeDate.add(new Pair<>(secondsTime, line));
            } else if (line.contains("PM")) {
                String stringI = line.replaceAll(" PM", "");
                String[] split = stringI.split(":");
                int hourI = Integer.parseInt(split[0]);
                int minutes = Integer.parseInt(split[1]);
                int seconds = Integer.parseInt(split[2]);
                if (hourI == 12) {
                    int secondsTime = ((hourI) * 60 + minutes) * 60 + seconds;
                    timeDate.add(new Pair<>(secondsTime, line));
                } else {
                    int secondsTime = ((hourI + 12) * 60 + minutes) * 60 + seconds;
                    timeDate.add(new Pair<>(secondsTime, line));
                }
            }
        }
        for (Pair<Integer, String> pair : timeDate) {
            Integer key = pair.getFirst();
            String value = pair.getSecond();
            minute.add(key);
        }
        minute.sort(Comparator.naturalOrder());

        FileOutputStream writer = new FileOutputStream(outputName);
        for (Integer integer : minute) {
            for (Pair<Integer, String> integerStringPair : timeDate) {
                if (integer == integerStringPair.getFirst()) {
                    writer.write(integerStringPair.getSecond().getBytes());
                    writer.write("\n".getBytes());
                }
            }
        }

        writer.close();
}

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) throws Exception {
        ArrayList<Pair<String, Integer>> streetAndNumberList = new ArrayList<>();
        //List<String> personList;
        List<List> personList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        Path pathFile = Paths.get(inputName);
        long numberLines = Files.lines(pathFile).count();

        //try (BufferedReader br = new BufferedReader(new FileReader(new File(inputName)))) {
       // try (BufferedReader br = new BufferedReader(new InputStreamReader(inputName), "UTF8")) {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(inputName), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                //String [] streetAndNumber = line.replaceAll("\\s-\\s[а-яА-Я]+\\s[1-9]+", "").split(" ");
                    //String person[] = new String[3];
                    List<String> person = new ArrayList<String>();
                    List<Integer> personi = new ArrayList<Integer>();
                    List<List> personl = new ArrayList<List>();
                    String dirAndNumber = line.replaceAll("[а-яА-Я]+\\s[а-яА-Я]+\\s+-\\s", "").trim();
                    String direction = dirAndNumber.split(" ")[0];
                    Integer numberHouse = Integer.parseInt(dirAndNumber.split(" ")[1]);
                    String namesPerson = line.replaceAll("\\s-\\s[а-яА-Я]+\\s[1-9]+", "");
                    List <String> namePersonList = new ArrayList<String>();
                    namePersonList.add(namesPerson);
                    person.add(direction);
                    personi.add(numberHouse);
                    personl.add(namePersonList);
                    List<List> listfinal = new ArrayList<List>();
                    listfinal.add(person);
                listfinal.add(personi);
                listfinal.add(personl);
                personList.add(listfinal);



                    //personList = Arrays.asList(person));
                    //personList.forEach(System.out::println);
                    //streetAndNumberList.add(new Pair(streetAndNumber[0], streetAndNumber[1]));
                    //personList.add(new Pair(person[0], person[1]));
                    //map.put(streetAndNumber[1], streetAndNumber[0]);
                    FileOutputStream writer = new FileOutputStream(outputName);
                    writer.write(line.getBytes());
                    writer.flush();
                    writer.close();

       /* Comparator<Pair<String, String>> comparatorA = comparing(Pair<String, String>::getFirst);
        Comparator <Pair<String, String>> comparatorB = comparatorA.thenComparing((Pair::getSecond));
        personList.forEach(System.out::println);*/

            }
        //}
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader(inputName));
        String line;
        while ((line = r.readLine()) != null) {
            //String streetAndNumber = line.replaceAll(".", "");
            System.out.println(line);

        }
        FileOutputStream writer = new FileOutputStream(outputName);
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}

