package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        // Если длина строки first - m, а second - n, то имеем такую сложность:
        // По памяти: O(m*n)
        // По времени: O(m*n)
        int[][] matrix = new int[first.length() + 1][second.length() + 1];
        int longestRow = 0;
        int longestColumn = 0;
        int longestLength = 0;

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    if (longestLength < matrix[i][j]) {
                        longestLength = matrix[i][j];
                        longestRow = i;
                        longestColumn = j;
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (; matrix[longestRow][longestColumn] != 0; longestRow--, longestColumn--) {
            result.append(first.charAt(longestRow - 1));
        }

        return result.reverse().toString();
    }
    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {

        // Сложность:
        // По памяти: O(n)
        // По времени O(n^2)
            char[][] matrix = readCharMatrixFromFile(inputName);
            return words.stream()
                    .filter(word -> wordExistsInMatrix(matrix, word))
                    .collect(Collectors.toSet());
        }

        // Метод для проверки наличия слова в матрице
        private static boolean wordExistsInMatrix(char[][] matrix, String word) {
            boolean isFound = false;
            for (int i = 0; i < matrix.length && !isFound; ++i) {
                for (int j = 0; j < matrix[i].length && !isFound; ++j) {
                    isFound = checkWordRecursive(matrix, i, j, word, 0, new ArrayList<>());
                }
            }
            return isFound;
        }
        // Рекурсивный метод дял поиска слов в мтарице
        private static boolean checkWordRecursive(char[][] matrix, int i, int j, String word, int pos, List<int[]> usedElements) {
            if (pos == word.length()) {
                return true;
            }
            boolean isFound = false;
            if (matrix[i][j] == word.charAt(pos)) {
                usedElements.add(new int[] {i, j});
                if (isFree(i - 1, j, usedElements)) {
                    isFound = checkWordRecursive(matrix, i - 1, j, word, pos + 1, usedElements);
                }
                // Проверка ячейки слева
                if (!isFound && isFree(i, j - 1, usedElements)) {
                    isFound = checkWordRecursive(matrix, i, j - 1, word, pos + 1, usedElements);
                }
                // Проверка ячейки снизу
                if (!isFound && isFree(i + 1, j, usedElements)) {
                    isFound = checkWordRecursive(matrix, i + 1, j, word, pos + 1, usedElements);
                }
                // Проверка ячейки справа
                if (!isFound && isFree(i, j + 1, usedElements)) {
                    isFound = checkWordRecursive(matrix, i, j + 1, word, pos + 1, usedElements);
                }
            }
            return isFound;
        }

        private static boolean isFree(int i, int j, List<int[]> usedElements) {
            return usedElements.stream()
                    .noneMatch(used -> used[0] == i && used[1] == j);
        }

        // Метод для считывания данных с файла
        private static char[][] readCharMatrixFromFile(String fileName) {
            List<String> rows = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(fileName), "UTF-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    rows.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            char[][] matrix = new char[rows.size() + 2][rows.get(0).split(" ").length + 2];
            for (int i = 0; i < rows.size(); ++i) {
                String[] charArr = rows.get(i).split(" ");
                for (int j = 0 ; j < matrix[i].length - 2; ++j) {
                    matrix[i + 1][j + 1] = charArr[j].charAt(0);
                }
            }
            return matrix;
        }
}
