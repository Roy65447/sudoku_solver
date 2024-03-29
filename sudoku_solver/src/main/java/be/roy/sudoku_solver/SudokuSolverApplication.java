package be.roy.sudoku_solver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SudokuSolverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuSolverApplication.class, args);
	}

}
