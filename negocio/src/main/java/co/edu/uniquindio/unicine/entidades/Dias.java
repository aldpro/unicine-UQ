package co.edu.uniquindio.unicine.entidades;

public enum Dias {

    LUNES(0), MARTES(1), MIERCOLES(2), JUEVES(3), VIERNES(4), SABADO(5), DOMINGO(6);

    private int numTipo;

    Dias(int numTipo) {
        this.numTipo = numTipo;
    }

    public int getNumTipo() {
        return numTipo;
    }

    public void setNumTipo(int numTipo) {
        this.numTipo = numTipo;
    }

    public Dias getDias(int index) {

        switch(index) {

            case 0: return Dias.LUNES;

            case 1: return Dias.MARTES;

            case 2: return Dias.MIERCOLES;

            case 3: return Dias.JUEVES;

            case 4: return Dias.VIERNES;

            case 5: return Dias.SABADO;

            case 6: return Dias.DOMINGO;


            default:

                return null;

        }

    }
}
