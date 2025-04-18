package cw2407;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2407 {
    public static void main(String[] args) {
        while(true) {
            List<String> movies = new ArrayList<>();
            Scanner scn1 = new Scanner(System.in);
            System.out.print("Введите количество фильмов (0 - выход): ");
            int amountMovies = scn1.nextInt();
            if(amountMovies <= 0) break;
            Scanner scn2 = new Scanner(System.in);
            System.out.print("Введите, какое количество фильмов выводить на одной странице: ");
            int moviesPerPage = scn2.nextInt();
            if(moviesPerPage <= 0) break;

            for (int i = 0; i < amountMovies; i++) {
                movies.add("Movie #" + (i + 1));
            }
            int delta = amountMovies % moviesPerPage > 0 ? 1 : 0;
            for (int i = 0; i < amountMovies / moviesPerPage + delta; i++) {
                System.out.printf("Page %d\n",(i + 1));
            }

            Scanner scn = new Scanner(System.in);
            System.out.print("Введите номер страницы: ");
            int page = scn.nextInt();
            if(page > 0 && page <= amountMovies/moviesPerPage+delta) {
                int start = moviesPerPage * (page - 1);
                int limit = Math.min(amountMovies - start, moviesPerPage);
                movies.stream().skip(start).limit(limit).forEach(System.out::println);
            }else{
                System.out.println("Введена неверная страница");
            }
        }
    }
}
