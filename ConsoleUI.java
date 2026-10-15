import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner;
    private ComplexMatrix posledniiRezultat;
    public ConsoleUI() {
        scanner = new Scanner(System.in);
        posledniiRezultat = null;
    }

    public void zapustit() {

        boolean rabotaet = true;

        System.out.println("Работа с матрицами комплексных чисел");

        while (rabotaet) {
            pokazatMenu();
            System.out.print("Выберите операцию: ");
            int vybor = scanner.nextInt();

            try {

                switch (vybor) {

                    case 1:
                        slozhenie();
                        break;

                    case 2:
                        umnozhenie();
                        break;

                    case 3:
                        delenie();
                        break;

                    case 4:
                        transponirovanie();
                        break;

                    case 5:
                        opredelitel();
                        break;

                    case 6:
                        pokazatPosledniiRezultat();
                        break;

                    case 0:
                        rabotaet = false;
                        break;

                    default:
                        System.out.println("Такой операции нет");
                }

            } catch (IllegalArgumentException e) {

                System.out.println("Ошибка: " + e.getMessage());

            } catch (ArithmeticException e) {

                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        System.out.println("Программа завершена");
    }

    private void pokazatMenu() {

        System.out.println();
        System.out.println("1 - Сложить матрицы");
        System.out.println("2 - Умножить матрицы");
        System.out.println("3 - Разделить матрицы");
        System.out.println("4 - Транспонировать матрицу");
        System.out.println("5 - Найти определитель");
        System.out.println("6 - Показать последний результат");
        System.out.println("0 - Выход");
        System.out.println();
    }

    private ComplexMatrix sozdatMatricu() {

        System.out.print("Количество строк: ");
        int stroki = scanner.nextInt();

        System.out.print("Количество столбцов: ");
        int stolbcy = scanner.nextInt();

        if (stroki <= 0 || stolbcy <= 0) {

            throw new IllegalArgumentException(
                    "Размеры матрицы должны быть больше нуля"
            );
        }

        ComplexMatrix matrica =
                new ComplexMatrix(stroki, stolbcy);
        for (int i = 0; i < stroki; i++) {
            for (int j = 0; j < stolbcy; j++) {

                System.out.println(
                        "Элемент [" + (i + 1) + "][" + (j + 1) + "]"
                );

                System.out.print("Действительная часть: ");
                double deistvitelnaya =
                        scanner.nextDouble();

                System.out.print("Мнимая часть: ");
                double mnimaya =
                        scanner.nextDouble();

                Complex chislo =
                        new Complex(
                                deistvitelnaya,
                                mnimaya
                        );

                matrica.setElement(
                        i,
                        j,
                        chislo
                );
            }
        }

        System.out.println("Матрица:");
        System.out.println(matrica);

        return matrica;
    }

    private void slozhenie() {
        System.out.println("Введите первую матрицу:");

        ComplexMatrix pervayaMatrica =
                sozdatMatricu();

        System.out.println("Введите вторую матрицу:");

        ComplexMatrix vtorayaMatrica =
                sozdatMatricu();
        ComplexMatrix rezultat;

        rezultat =
                pervayaMatrica.slozhit(
                        vtorayaMatrica
                );

        posledniiRezultat = rezultat;

        System.out.println("Результат:");
        System.out.println(rezultat);
    }

    private void umnozhenie() {

        System.out.println("Введите первую матрицу:");

        ComplexMatrix pervayaMatrica =
                sozdatMatricu();

        System.out.println("Введите вторую матрицу:");

        ComplexMatrix vtorayaMatrica =
                sozdatMatricu();

        ComplexMatrix rezultat;

        rezultat =
                pervayaMatrica.umnozhit(
                        vtorayaMatrica
                );

        posledniiRezultat = rezultat;
        System.out.println("Результат:");
        System.out.println(rezultat);
    }

    private void delenie() {

        System.out.println("Введите первую матрицу:");

        ComplexMatrix pervayaMatrica =
                sozdatMatricu();

        System.out.println("Введите вторую матрицу:");

        ComplexMatrix vtorayaMatrica =
                sozdatMatricu();

        ComplexMatrix rezultat;

        rezultat =
                pervayaMatrica.razdelit(
                        vtorayaMatrica
                );

        posledniiRezultat = rezultat;
        System.out.println("Результат:");
        System.out.println(rezultat);
    }

    private void transponirovanie() {

        System.out.println("Введите матрицу:");

        ComplexMatrix matrica =
                sozdatMatricu();

        ComplexMatrix rezultat;

        rezultat =
                matrica.transponirovat();

        posledniiRezultat = rezultat;

        System.out.println("Результат:");
        System.out.println(rezultat);
    }

    private void opredelitel() {

        System.out.println("Введите матрицу:");

        ComplexMatrix matrica =
                sozdatMatricu();

        Complex rezultat;

        rezultat =
                matrica.opredelitel();

        System.out.println("Определитель:");
        System.out.println(rezultat);
    }

    private void pokazatPosledniiRezultat() {
        if (posledniiRezultat == null) {
            System.out.println(
                    "Сохранённого результата пока нет"
            );

        } else {

            System.out.println("Последний результат:");
            System.out.println(posledniiRezultat);
        }
    }
}