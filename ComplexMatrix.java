public class ComplexMatrix {

    private int stroki;
    private int stolbcy;
    private Complex[][] matrica;
    public ComplexMatrix(int stroki, int stolbcy) {
        this.stroki = stroki;
        this.stolbcy = stolbcy;
        matrica = new Complex[stroki][stolbcy];
        for (int i = 0; i < stroki; i++) {
            for (int j = 0; j < stolbcy; j++) {
                matrica[i][j] = new Complex(0, 0);
            }
        }
    }

    public int getStroki() {
        return stroki;
    }
    public int getStolbcy() {
        return stolbcy;
    }
    public void setElement(int stroka, int stolbec, Complex chislo) {
        matrica[stroka][stolbec] = chislo;
    }
    public Complex getElement(int stroka, int stolbec) {
        return matrica[stroka][stolbec];
    }

    public ComplexMatrix slozhit(ComplexMatrix drugayaMatrica) {

        if (stroki != drugayaMatrica.stroki ||
                stolbcy != drugayaMatrica.stolbcy) {

            throw new IllegalArgumentException(
                    "Размеры матриц должны совпадать"
            );
        }

        ComplexMatrix rezultat =
                new ComplexMatrix(stroki, stolbcy);

        for (int i = 0; i < stroki; i++) {

            for (int j = 0; j < stolbcy; j++) {

                Complex summa;

                summa = matrica[i][j].slozhit(
                        drugayaMatrica.matrica[i][j]
                );

                rezultat.matrica[i][j] = summa;
            }
        }
        return rezultat;
    }
    public ComplexMatrix umnozhit(ComplexMatrix drugayaMatrica) {

        if (stolbcy != drugayaMatrica.stroki) {

            throw new IllegalArgumentException(
                    "Такие матрицы нельзя перемножить"
            );
        }

        ComplexMatrix rezultat =
                new ComplexMatrix(
                        stroki,
                        drugayaMatrica.stolbcy
                );

        for (int i = 0; i < stroki; i++) {

            for (int j = 0; j < drugayaMatrica.stolbcy; j++) {

                Complex summa = new Complex(0, 0);

                for (int k = 0; k < stolbcy; k++) {

                    Complex proizvedenie;

                    proizvedenie =
                            matrica[i][k].umnozhit(
                                    drugayaMatrica.matrica[k][j]
                            );

                    summa = summa.slozhit(proizvedenie);
                }

                rezultat.matrica[i][j] = summa;
            }
        }
        return rezultat;
    }

    public ComplexMatrix transponirovat() {
        ComplexMatrix rezultat =
                new ComplexMatrix(stolbcy, stroki);
        for (int i = 0; i < stroki; i++) {
            for (int j = 0; j < stolbcy; j++) {
                rezultat.matrica[j][i] =
                        matrica[i][j];
            }
        }

        return rezultat;
    }

    private ComplexMatrix poluchitMinor(
            int ubratStroku,
            int ubratStolbec) {
        ComplexMatrix minor =
                new ComplexMatrix(
                        stroki - 1,
                        stolbcy - 1
                );

        int novayaStroka = 0;
        for (int i = 0; i < stroki; i++) {
            if (i != ubratStroku) {
                int novyiStolbec = 0;
                for (int j = 0; j < stolbcy; j++) {
                    if (j != ubratStolbec) {
                        minor.matrica[novayaStroka][novyiStolbec] =
                                matrica[i][j];

                        novyiStolbec = novyiStolbec + 1;
                    }
                }

                novayaStroka = novayaStroka + 1;
            }
        }

        return minor;
    }

    public Complex opredelitel() {

        if (stroki != stolbcy) {

            throw new IllegalArgumentException(
                    "Определитель можно вычислить только для квадратной матрицы"
            );
        }

        if (stroki == 1) {
            return matrica[0][0];
        }

        if (stroki == 2) {

            Complex pervoeProizvedenie;
            Complex vtoroeProizvedenie;
            Complex rezultat;

            pervoeProizvedenie =
                    matrica[0][0].umnozhit(
                            matrica[1][1]
                    );

            vtoroeProizvedenie =
                    matrica[0][1].umnozhit(
                            matrica[1][0]
                    );

            rezultat =
                    pervoeProizvedenie.vychest(
                            vtoroeProizvedenie
                    );

            return rezultat;
        }

        Complex rezultat =
                new Complex(0, 0);

        for (int j = 0; j < stolbcy; j++) {

            ComplexMatrix minor;
            Complex opredelitelMinora;
            Complex slagaemoe;

            minor = poluchitMinor(0, j);

            opredelitelMinora =
                    minor.opredelitel();

            slagaemoe =
                    matrica[0][j].umnozhit(
                            opredelitelMinora
                    );

            if (j % 2 == 0) {

                rezultat =
                        rezultat.slozhit(slagaemoe);

            } else {

                rezultat =
                        rezultat.vychest(slagaemoe);
            }
        }

        return rezultat;
    }

    public ComplexMatrix obratnaya() {

        if (stroki != stolbcy) {

            throw new IllegalArgumentException(
                    "Для нахождения обратной матрицы матрица должна быть квадратной"
            );
        }

        Complex det;

        det = opredelitel();

        if (det.etoNol()) {

            throw new ArithmeticException(
                    "Обратной матрицы не существует"
            );
        }

        if (stroki == 1) {

            ComplexMatrix rezultat =
                    new ComplexMatrix(1, 1);

            Complex edinica =
                    new Complex(1, 0);

            Complex chislo =
                    edinica.razdelit(matrica[0][0]);

            rezultat.matrica[0][0] = chislo;

            return rezultat;
        }

        ComplexMatrix dopolneniya =
                new ComplexMatrix(stroki, stolbcy);

        for (int i = 0; i < stroki; i++) {

            for (int j = 0; j < stolbcy; j++) {

                ComplexMatrix minor;
                Complex detMinora;

                minor = poluchitMinor(i, j);

                detMinora =
                        minor.opredelitel();

                if ((i + j) % 2 != 0) {

                    Complex minusOdin =
                            new Complex(-1, 0);

                    detMinora =
                            detMinora.umnozhit(minusOdin);
                }

                dopolneniya.matrica[i][j] =
                        detMinora;
            }
        }

        ComplexMatrix transponirovannaya;

        transponirovannaya =
                dopolneniya.transponirovat();

        ComplexMatrix rezultat =
                new ComplexMatrix(stroki, stolbcy);

        for (int i = 0; i < stroki; i++) {

            for (int j = 0; j < stolbcy; j++) {

                Complex chislo;

                chislo =
                        transponirovannaya.matrica[i][j]
                                .razdelit(det);

                rezultat.matrica[i][j] =
                        chislo;
            }
        }

        return rezultat;
    }

    public ComplexMatrix razdelit(
            ComplexMatrix drugayaMatrica) {

        ComplexMatrix obratnayaMatrica;

        obratnayaMatrica =
                drugayaMatrica.obratnaya();

        ComplexMatrix rezultat;

        rezultat =
                this.umnozhit(obratnayaMatrica);

        return rezultat;
    }

    public String toString() {

        String tekst = "";

        for (int i = 0; i < stroki; i++) {

            for (int j = 0; j < stolbcy; j++) {

                tekst =
                        tekst + matrica[i][j] + "    ";
            }

            tekst = tekst + "\n";
        }

        return tekst;
    }
}