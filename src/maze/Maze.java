package maze;

import java.awt.Point;
import java.util.*;

public class Maze {
	private LinkedList<Point> exits;
	
	public Point entrance;
	public byte[][] source;
	private PathFinder solver;
	
	public Maze(byte source[][]) {
		this.source = source;
		this.solver = new PathFinder(source);
		this.exits = new LinkedList<Point>();
		firstRun();
	}
	
	private void firstRun() {
		//Percorre o labirinto
		for (int i=0; i < source.length; i++) {
			for (int j=0; j < source[i].length; j++) {
				//Atualiza a entrada (s� pode haver uma)
				if (source[i][j] == 2) {
					if (entrance == null) entrance = new Point(i, j);
					//else ADCIONAR EXCESS�O, DUAS ENTRADAS
				}
				
<<<<<<< HEAD
				//Adiciona a sa�da � lista de sa�das conhecidas
=======
				//Adiciona a sa�da � lista de sa�das conhecidas
>>>>>>> ee3ee1c1cb5dc8a9d77ed184521926949a0dacbd
				else if (source[i][j] == 3) exits.add(new Point(i, j));
			}
		}
		
		//Caso entrada ou sa�da n�o encontrada
		//if (entrance == null) ADICIONAR EXCESS�O LABIRINTO SEM ENTRADA
		//if (exits.size() == 0) ADICIONAR EXCESS�O LABIRINTO SEM SA�DA
	}
	
	public ArrayList<LinkedList<Point>> dfs() {
		return solver.dfs(entrance, exits.size());
	}
	
	public ArrayList<LinkedList<Point>> bfs() {
		return solver.bfs(entrance, exits.size());
	}
	
	public ArrayList<LinkedList<Point>> AStar() {
<<<<<<< HEAD
		//Vetor dos caminhos at� cada sa�da
		ArrayList<LinkedList<Point>> paths = new ArrayList<LinkedList<Point>>(exits.size());
		
		//Encontra cada sa�da...
=======
		//Vetor dos caminhos at� cada sa�da
		ArrayList<LinkedList<Point>> paths = new ArrayList<LinkedList<Point>>(exits.size());
		
		//Encontra cada sa�da...
>>>>>>> ee3ee1c1cb5dc8a9d77ed184521926949a0dacbd
		for (Point exit : exits)
			paths.add(solver.AStar(source, entrance, exit));
		
		return paths;
	}
	
	public LinkedList<Point> showFootPrint() {
		return solver.showFootPrint();
	}
}
