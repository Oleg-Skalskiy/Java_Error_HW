//Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
//        Фамилия Имя Отчество датарождения номертелефона пол
//
//        Форматы данных:
//        фамилия, имя, отчество - строки
//        датарождения - строка формата dd.mm.yyyy
//        номертелефона - целое беззнаковое число без форматирования
//        пол - символ латиницей f или m.
//
//        Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
//
//        Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.
//
//        Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида
//
//<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
//
//Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
//
//        Не забудьте закрыть соединение с файлом.
//
//        При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
//

import Data.InsufficientDataException;
import Gender.InsufficientGenderException;
import Phone.InsufficientPhoneException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InsufficientDataException, InsufficientPhoneException {
        try {
            System.out.println("Заполните данные (Например: ФИО, дата рождения, номер телефон (без +), пол (М или Ж))");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] array = input.split(" ");
            if (array.length != 6) {
                System.out.println("Ошибка ввода данных");
            }
            String name = array[0];
            String lastname = array[1];
            String surname = array[2];
            String birth = array[3];
            String phoneNumber = array[4];
            String gender = array[5];
            if (!birth.matches("\\d{2}.\\d{2}.\\d{4}")) {
                throw new InsufficientDataException("Вы ввели некорректную дату рождения!");
            }
            if (!phoneNumber.matches("\\d+")) {
                throw new InsufficientPhoneException("Вы ввели некорректный номер телефона!");
            }
            if (!gender.matches("[МЖ]")) {
                throw new InsufficientGenderException("Вы ввели некорректный пол!");
            }
            System.out.println("Имя: " + name);
            System.out.println("Отчество: " + lastname);
            System.out.println("Фамилия: " + surname);
            System.out.println("Дата Рождения: " + birth);
            System.out.println("Телефон: " + phoneNumber);
            System.out.println("Пол: " + gender);

            String info = name + lastname + surname + birth + phoneNumber + gender;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("RawData.txt"))) {
                writer.write(info);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (InsufficientGenderException e) {
            throw new RuntimeException(e);
        }
    }
}
