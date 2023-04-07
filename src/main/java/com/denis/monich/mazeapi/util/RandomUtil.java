package com.denis.monich.mazeapi.util;

public final class RandomUtil {

  public RandomUtil() {
  }

  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }

}
