package mt.edu.um

class SingletonSolver {

  val allElements = Set(1, 2, 3, 4, 5, 6, 7, 8, 9)

  def solve(sudoku: Sudoku): Sudoku = {
    def hypothesis(r: Int, c: Int): Set[Int] = {
      // Get row, column and block. Block index is calculated using division.
      allElements.diff(sudoku.row(r) ++ sudoku.column(c) ++ sudoku.block((r / 3) * 3 + (c / 3)))
    }

    def allHypothesis(): List[(Int, Int, Set[Int])] = {
      def getAllRow(rowCount: Int, grid: List[List[Int]]): List[(Int, Int, Set[Int])] = {
        def getAllCol(colCount: Int, row: List[Int]): List[(Int, Int, Set[Int])] = row match {
          case List() => Nil
          case x :: xs =>
            // Ignoring elements that are already filled.
            if(x != 0) getAllCol(colCount + 1, xs)
            else (rowCount, colCount, hypothesis(rowCount, colCount)) :: getAllCol(colCount + 1, xs)
        }
        if (grid.isEmpty) Nil else getAllCol(0, grid.head) ::: getAllRow(rowCount + 1, grid.tail)
      }
      getAllRow(0, sudoku.grid)
    }

    def complete(): Boolean = {
      def check(grid: List[List[Int]]): Boolean = grid match {
        case List() => true
        case x :: xs =>
          if (x.contains(0)) false
          else check(xs)
      }
      check(sudoku.grid)
    }

    def step(): Sudoku = {
      def changeSudoku(value: (Int, Int, Set[Int]), grid: List[List[Int]]): List[List[Int]] = {
        // Update the row and update that row with the new element in the set at the column given
        grid.updated(value._1, grid(value._1).updated(value._2, value._3.head))
      }
      // From allHypothesis get the first one that has the set that contains only 1 element
      new Sudoku(changeSudoku(allHypothesis().filter(_._3.size == 1).head, sudoku.grid))
    }
    if (complete()) sudoku else solve(step())
  }
}
