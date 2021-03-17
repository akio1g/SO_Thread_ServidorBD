package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadServidor extends Thread {
	private int secICal;
	private int secFCal;
	private int secTra;
	private int id;
	private Semaphore semaforo;

	public ThreadServidor(int id, Semaphore semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		if ((id % 3) == 1) {
			secICal = 200;
			secFCal = 1000;
			secTra = 1000;
			calculo(secICal, secFCal);
			transacao(secTra);
			calculo(secICal, secFCal);
			transacao(secTra);
			fim();
		} else if ((id % 3) == 2) {
			secICal = 500;
			secFCal = 1500;
			secTra = 1500;
			calculo(secICal, secFCal);
			transacao(secTra);
			calculo(secICal, secFCal);
			transacao(secTra);
			fim();
		} else if ((id % 3) == 0) {
			secICal = 1000;
			secFCal = 2000;
			secTra = 1500;
			calculo(secICal, secFCal);
			transacao(secTra);
			calculo(secICal, secFCal);
			transacao(secTra);
			fim();
		}
	}

	private void fim() {
		System.out.println("Thread#"+id+" foi finalizada.");
	}

	private void transacao(int secTra) {
		try {
			semaforo.acquire();
			System.out.println("Thread#"+id+"fazendo transacao de BD...");
			try {
				sleep(secTra);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void calculo(int secICal, int secFCal) {
		Random r = new Random();
		int tempo = r.nextInt(secFCal);
		while (tempo < secICal)
			tempo = r.nextInt(secFCal);
		System.out.println("Thread#"+id+"realizando calculos...");
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
