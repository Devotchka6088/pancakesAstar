package pancakesAStar;

public class Nodo implements Comparable<Nodo>{
    private String estado;
    private Nodo padre;
    private int distanciaRecorrida;
    private int heuristica;
    private int noRegreso;

    public Nodo(String estado, Nodo padre, int distanciaRecorrida, int heuristica, int noRegreso) {
        this.estado = estado;
        this.padre = padre;
        this.distanciaRecorrida = distanciaRecorrida;
        this.heuristica = heuristica;
        this.noRegreso = noRegreso;
    }

    public String getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public int getDistancia() {
        return distanciaRecorrida;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public int getNoRegreso() {
        return noRegreso;
    }

    public int compareTo(Nodo o) {
        int f1 = this.distanciaRecorrida + this.heuristica;
        int f2 = o.distanciaRecorrida + o.heuristica;
        return Integer.compare(f1, f2);
    }
}
