package com.letscode.services.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomizerUtil {
	
	public int randomizador(int range) {
		Random random = new Random();
		int valor = random.nextInt(range);
		return valor;
	}

}
