package mt.edu.um

/**
  * Created by dylan on 25/11/2016.
  */
class Sudoku(val grid: List[List[Int]]) {

  def row (row: Int): Set[Int] = {
    grid(row).toSet.diff(Set(0))
  }

  def column(column: Int): Set[Int] = {
    def getCol(grid: List[List[Int]]) : Set[Int] = grid match {
      case List() => Set()
      case x :: xs =>
        // 0 is ignored
        if(x(column) == 0) getCol(xs)
        else Set(x(column)) ++ getCol(xs)
    }
    getCol(grid)
  }

  def block(block: Int): Set[Int] = {
    def getBlock(grid: List[List[Int]], counter: Int): Set[Int] = {
      def getColsForRow(row: List[Int], start: Int, counter: Int): Set[Int] = {
        // Every block has 3 columns
        if (counter == 3) Set()
        else Set(row(start)) ++ getColsForRow(row, start + 1, counter + 1)
      }
      // Every block has 3 rows
      if (counter == 3) Set()
      // Get the location of the block using division and modulus.
      else getColsForRow(grid((block / 3) * 3 + counter), (block % 3) * 3, 0) ++ getBlock(grid, counter + 1)
    }
    // Remove 0 from block
    getBlock(grid, 0).diff(Set(0))
  }

  override def toString() = {
    def printRow(row: List[Int]): String = row match {
      case List() => ""
      case x :: xs =>
        if(x == 0) "_ " + printRow(xs)
        else x + " " + printRow(xs)
    }
    def printGrid(grid: List[List[Int]]): String = grid match {
      case List() => ""
      case x :: xs => printRow(x) + "\n" + printGrid(xs)
    }
    printGrid(grid)
  }
}
