package view;

import java.util.concurrent.Semaphore;

import controller.ThreadServidor;

public class Principal {

	public static void main(String[] args) {
		int fila = 1;
		Semaphore semaforo = new Semaphore(fila);
		for(int id = 1;id<= 21;id++) {
			Thread threads = new ThreadServidor(id,semaforo);
			threads.start();
		}
	}

}
