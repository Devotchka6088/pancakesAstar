package pancakesAStar;

import java.util.HashSet;
import java.util.LinkedList;

public class Main {
	
	static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	static String ordenPrin = "";

	public static void main(String[] args) {
		
		int nDiscos = 10;
		
		String ordenRan = crearDiscos(nDiscos);
		
		ordenRan = shuffle();
		
		busquedaAestrella(ordenRan);

	}
	
	private static String crearDiscos(int nDiscos) {
		for(int i=0;i<=nDiscos-1;i++) {
			ordenPrin+=letras[i];
		}
		System.out.println("Inicio     > "+ordenPrin);
		return ordenPrin;
	}
	
	private static String shuffle() {

		char[] pos = ordenPrin.toCharArray();
		String orden = "";
		for(int i=0;i<ordenPrin.length();i++) {
			int ran = (int)(Math.random()*ordenPrin.length());
			if(pos[ran]!=0) {
				orden+=pos[ran];
				pos[ran]=0;
			}else {
				i--;
			}
		}
		System.out.println("Aleatorio  > "+orden);
		return orden;
	}
	
	private static void busquedaAestrella(String ordenRan) {
	    LinkedList<Nodo> colaPrioridad = new LinkedList<>();
	    HashSet<String> visitados = new HashSet<>();

	    Nodo nodoInicial = new Nodo(ordenRan, null, 0, heuristica(ordenRan), -1);
	    colaPrioridad.add(nodoInicial);

	    while (!colaPrioridad.isEmpty()) {
	        Nodo nodoActual = colaPrioridad.remove();

	        if (veriSol(nodoActual.getEstado())) {
	            System.out.println("\nSolucion encontrada");
	            System.out.println("Nodos visitados > " + (visitados.size() + 1));
	            break;
	        }

	        visitados.add(nodoActual.getEstado());

	        char[] pos = nodoActual.getEstado().toCharArray();

	        for (int i=1;i<pos.length;i++) {
	            if (nodoActual.getNoRegreso()!=i-1) {
	                String nuevoEstado = "";
	                for (int j=i;j>=0;j--) {
	                    nuevoEstado+=pos[j];
	                }
	                for (int j=i+1;j<pos.length;j++) {
	                    nuevoEstado+=pos[j];
	                }
	                if (!visitados.contains(nuevoEstado)) {
	                    Nodo nuevoNodo = new Nodo(nuevoEstado, nodoActual, nodoActual.getDistancia()+1, heuristica(nuevoEstado), i);
	                    colaPrioridad.add(nuevoNodo);
	                }
	            }
	        }
	    }
	}

	public static int heuristica(String orden) {
	    char[] pos = orden.toCharArray();
	    int distancia = 0;
	    int discosMalPosicionados = 0;
	    for (int i = 0; i < pos.length; i++) {
	        distancia += Math.abs(letras[i] - pos[i]);
	        if (pos[i] != letras[i]) {
	            discosMalPosicionados++;
	        }
	    }
	    return distancia + discosMalPosicionados;
	}

	private static boolean veriSol(String ordenRan) {
		return ordenPrin.equals(ordenRan);
	}
}