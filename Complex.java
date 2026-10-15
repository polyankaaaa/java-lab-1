public class Complex {

    private double deistvitelnaya;
    private double mnimaya;

    public Complex(double deistvitelnaya, double mnimaya) {
        this.deistvitelnaya = deistvitelnaya;
        this.mnimaya = mnimaya;
    }

    public Complex slozhit(Complex drugoeChislo) {

        double novayaDeistvitelnaya;
        double novayaMnimaya;

        novayaDeistvitelnaya =
                deistvitelnaya + drugoeChislo.deistvitelnaya;

        novayaMnimaya =
                mnimaya + drugoeChislo.mnimaya;

        Complex rezultat =
                new Complex(novayaDeistvitelnaya, novayaMnimaya);

        return rezultat;
    }

    public Complex vychest(Complex drugoeChislo) {

        double novayaDeistvitelnaya;
        double novayaMnimaya;

        novayaDeistvitelnaya =
                deistvitelnaya - drugoeChislo.deistvitelnaya;

        novayaMnimaya =
                mnimaya - drugoeChislo.mnimaya;

        Complex rezultat =
                new Complex(novayaDeistvitelnaya, novayaMnimaya);

        return rezultat;
    }

    public Complex umnozhit(Complex drugoeChislo) {
        double novayaDeistvitelnaya;
        double novayaMnimaya;
        novayaDeistvitelnaya =
                deistvitelnaya * drugoeChislo.deistvitelnaya
                - mnimaya * drugoeChislo.mnimaya;
        novayaMnimaya =
                deistvitelnaya * drugoeChislo.mnimaya
                + mnimaya * drugoeChislo.deistvitelnaya;

        Complex rezultat =
                new Complex(novayaDeistvitelnaya, novayaMnimaya);

        return rezultat;
    }

    public Complex razdelit(Complex drugoeChislo) {

        double znamenatel;

        znamenatel =
                drugoeChislo.deistvitelnaya * drugoeChislo.deistvitelnaya
                + drugoeChislo.mnimaya * drugoeChislo.mnimaya;

        if (znamenatel == 0) {
            throw new ArithmeticException(
                    "Нельзя делить на нулевое комплексное число"
            );
        }

        double novayaDeistvitelnaya;
        double novayaMnimaya;

        novayaDeistvitelnaya =
                (deistvitelnaya * drugoeChislo.deistvitelnaya
                + mnimaya * drugoeChislo.mnimaya)
                / znamenatel;

        novayaMnimaya =
                (mnimaya * drugoeChislo.deistvitelnaya
                - deistvitelnaya * drugoeChislo.mnimaya)
                / znamenatel;
        Complex rezultat =
                new Complex(novayaDeistvitelnaya, novayaMnimaya);
        return rezultat;
    }
    public boolean etoNol() {

        if (deistvitelnaya == 0 && mnimaya == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {

        if (mnimaya > 0) {
            return deistvitelnaya + " + " + mnimaya + "i";
        }

        if (mnimaya < 0) {
            return deistvitelnaya + " - " + (-mnimaya) + "i";
        }

        return deistvitelnaya + "";
    }
}