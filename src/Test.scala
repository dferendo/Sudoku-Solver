/**
  * Created by dylan on 25/11/2016.
  */
import mt.edu.um.{SingletonSolver, Sudoku}

object Test {
  def main(args: Array[String]): Unit = {
    val ex1 = new Sudoku(List(
      List(0, 0, 5, 0, 0, 6, 3, 0, 0),
      List(0, 0, 0, 0, 0, 0, 4, 0, 0),
      List(9, 8, 0, 7, 4, 0, 0, 0, 5),
      List(1, 0, 0, 0, 7, 0, 9, 0, 0),
      List(0, 0, 9, 5, 0, 1, 6, 0, 0),
      List(0, 0, 8, 0, 2, 0, 0, 0, 7),
      List(6, 0, 0, 0, 1, 8, 0, 9, 3),
      List(0, 0, 1, 0, 0, 0, 0, 0, 0),
      List(0, 0, 4, 2, 0, 0, 5, 0, 0)))
    // Row
    assert(ex1.row(0) == Set(5, 6, 3))
    assert(ex1.row(1) == Set(4))
    assert(ex1.row(2) == Set(5, 9, 7, 8, 4))
    // Column
    assert(ex1.column(8) == Set(5, 7, 3))
    assert(ex1.column(0) == Set(9, 1, 6))
    assert(ex1.column(1) == Set(8))
    assert(ex1.column(2) == Set(5, 1, 9, 8, 4))
    // Block
    assert(ex1.block(0) == Set(5, 9, 8))
    assert(ex1.block(1) == Set(6, 7, 4))
    assert(ex1.block(3) == Set(1, 9, 8))
    assert(ex1.block(8) == Set(9, 3, 5))

    print(ex1.toString())
    print("\n")

    val singleton = new SingletonSolver()

    // Test
    // assert(hypothesis(0, 0) == Set(2, 7, 4))
    // assert(hypothesis(0, 1) == Set(1, 2, 4, 7))
    // assert(hypothesis(2, 2) == Set(2, 3, 6))
    // assert(hypothesis(0, 3) == Set(1, 8, 9))
    // assert(allHypothesis()(0) == (0, 0, Set(2, 7, 4)))
    // assert(allHypothesis()(1) == (0, 1, Set(1, 2, 7, 4)))
    // assert(allHypothesis()(2) == (0, 3, Set(1, 9, 8)))

    print(singleton.solve(ex1).toString())
    print("\n")

    val ex2 = new Sudoku(List(
      List(0, 4, 0, 7, 0, 2, 8, 0, 0),
      List(0, 0, 0, 0, 0, 0, 2, 0, 9),
      List(9, 2, 8, 0, 4, 0, 0, 0, 0),
      List(0, 0, 3, 8, 0, 7, 0, 1, 0),
      List(0, 0, 0, 0, 2, 0, 0, 0, 0),
      List(0, 7, 0, 9, 0, 1, 4, 0, 0),
      List(0, 0, 0, 0, 5, 0, 1, 9, 3),
      List(5, 0, 6, 0, 0, 0, 0, 0, 0),
      List(0, 0, 9, 1, 0, 4, 0, 8, 5)))
    val singletonSolver = new SingletonSolver()
    print(singletonSolver.solve(ex2).toString())
  }
}
