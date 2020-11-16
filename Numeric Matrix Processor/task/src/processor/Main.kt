package processor

import java.util.*
import kotlin.math.pow

fun main() {
    val scanner = Scanner(System.`in`)
    printMenu()
    
    var choice = scanner.nextInt()
    while (choice != 0) {
        when (choice) {
            1 -> add(scanner)
            2 -> scalarMul(scanner)
            3 -> multiply(scanner)
            4 -> transpose(scanner)
            5 -> determinant(scanner)
            6 -> inverse(scanner)
        }
        
        printMenu()
        choice = scanner.nextInt()
    }
    
}

fun printMenu() {
    print("\n1. Add matrices\n" +
            "2. Multiply matrix by a constant\n" +
            "3. Multiply matrices\n" +
            "4. Transpose matrix\n" +
            "5. Calculate a determinant\n" +
            "6. Inverse matrix\n" +
            "0. Exit\n" +
            "Your choice: ")
}

fun scalarMul(scanner: Scanner) {
    print("Enter size of matrix: ")
    val rows = scanner.nextInt()
    val columns = scanner.nextInt()
    var allInt = true
    
    println("Enter matrix:")
    val matrix = mutableListOf<List<Double>>()
    
    for (i in 1..rows) {
        val row = mutableListOf<Double>()
        for (j in 1..columns) {
            val number = scanner.nextDouble()
            row.add(number)
            if (!number.toString().endsWith(".0")) allInt = false
        }
        matrix.add(row)
    }
    
    print("Enter constant: ")
    val scalar = scanner.nextDouble()
    if (!scalar.toString().endsWith(".0")) allInt = false
    
    println("The result is:")
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            print("${if (allInt) (matrix[i][j] * scalar).toInt() else matrix[i][j] * scalar} ")
        }
        println()
    }
}

fun add(scanner: Scanner) {
    print("Enter size of first matrix: ")
    val rowsFirst = scanner.nextInt()
    val columnsFirst = scanner.nextInt()
    var allInt = true
    
    println("Enter first matrix:")
    val first = mutableListOf<List<Double>>()
    for (i in 1..rowsFirst) {
        val row = mutableListOf<Double>()
        for (j in 1..columnsFirst) {
            val number = scanner.nextDouble()
            row.add(number)
            if (!number.toString().endsWith(".0")) allInt = false
        }
        first.add(row)
    }
    
    print("Enter size of second matrix: ")
    val rowsSecond = scanner.nextInt()
    val columnsSecond = scanner.nextInt()
    
    println("Enter second matrix:")
    val second = mutableListOf<List<Double>>()
    for (i in 1..rowsSecond) {
        val row = mutableListOf<Double>()
        for (j in 1..columnsSecond) {
            val number = scanner.nextDouble()
            row.add(number)
            if (!number.toString().endsWith(".0")) allInt = false
        }
        second.add(row)
    }
    
    if (rowsFirst != rowsSecond || columnsFirst != columnsSecond) {
        println("The operation cannot be performed.")
        return
    }
    
    val result = mutableListOf<List<Double>>()
    for (i in 0 until rowsSecond) {
        val row = mutableListOf<Double>()
        for (j in 0 until columnsSecond) {
            row.add(first[i][j] + second[i][j])
        }
        result.add(row)
    }
    
    println("The result is:")
    for (i in 0 until rowsSecond) {
        for (j in 0 until columnsSecond) {
            print("${if (allInt) result[i][j].toInt() else result[i][j]} ")
        }
        println()
    }
}

fun multiply(scanner: Scanner) {
    print("Enter size of first matrix: ")
    val rowsFirst = scanner.nextInt()
    val columnsFirst = scanner.nextInt()
    var allInt = true
    
    println("Enter first matrix:")
    val first = mutableListOf<List<Double>>()
    for (i in 1..rowsFirst) {
        val row = mutableListOf<Double>()
        for (j in 1..columnsFirst) {
            val number = scanner.nextDouble()
            row.add(number)
            if (!number.toString().endsWith(".0")) allInt = false
        }
        first.add(row)
    }
    
    print("Enter size of second matrix: ")
    val rowsSecond = scanner.nextInt()
    val columnsSecond = scanner.nextInt()
    
    println("Enter second matrix:")
    val second = mutableListOf<List<Double>>()
    for (i in 1..rowsSecond) {
        val row = mutableListOf<Double>()
        for (j in 1..columnsSecond) {
            val number = scanner.nextDouble()
            row.add(number)
            if (!number.toString().endsWith(".0")) allInt = false
        }
        second.add(row)
    }
    
    if (columnsFirst != rowsSecond) {
        println("The operation cannot be performed.")
        return
    }
    
    println("The result is:")
    for (i in 0 until rowsFirst) {
        for (j in 0 until columnsSecond) {
            var sum = 0.0
            for (k in 0 until columnsFirst) sum += first[i][k] * second[k][j]
            print("${if (allInt) sum.toInt() else sum} ")
        }
        println()
    }
}

fun transpose(scanner: Scanner) {
    print("\n1. Main diagonal\n" +
            "2. Side diagonal\n" +
            "3. Vertical line\n" +
            "4. Horizontal line\n" +
            "Your choice: ")
    
    val choice = scanner.nextInt()
    
    print("Enter matrix size: ")
    val rows = scanner.nextInt()
    val columns = scanner.nextInt()
    
    var allInt = true
    
    println("Enter matrix:")
    val matrix = mutableListOf<MutableList<Double>>()
    
    for (i in 1..rows) {
        val row = mutableListOf<Double>()
        for (j in 1..columns) {
            val number = scanner.nextDouble()
            row.add(number)
            if (!number.toString().endsWith(".0")) allInt = false
        }
        matrix.add(row)
    }
    
    val result = when (choice) {
        1 -> mainDiagonalTranspose(matrix)
        2 -> sideDiagonalTranspose(matrix)
        3 -> verticalLineTranspose(matrix)
        4 -> horizontalLineTranspose(matrix)
        else -> mutableListOf()
    }
    
    println("The result is:")
    for (i in 0 until result.size) {
        for (j in 0 until result[0].size) {
            print("${if (allInt) result[i][j].toInt() else result[i][j]} ")
        }
        println()
    }
}

fun mainDiagonalTranspose(matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    val resultMatrix = mutableListOf<MutableList<Double>>()
    
    for (i in matrix[0].indices) {
        val row = mutableListOf<Double>()
        for (j in matrix.indices) {
            row.add(matrix[j][i])
        }
        resultMatrix.add(row)
    }
    
    return resultMatrix
}

fun sideDiagonalTranspose(matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    return horizontalLineTranspose(verticalLineTranspose(mainDiagonalTranspose(matrix)))
}

fun verticalLineTranspose(matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    for (j in 0 until matrix[0].size / 2) {
        for (i in matrix.indices) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[i][matrix[0].lastIndex - j]
            matrix[i][matrix[0].lastIndex - j] = temp
        }
    }
    
    return matrix
}

fun horizontalLineTranspose(matrix: MutableList<MutableList<Double>>): MutableList<MutableList<Double>> {
    for (j in matrix[0].indices) {
        for (i in 0 until matrix.size / 2) {
            val temp = matrix[i][j]
            matrix[i][j] = matrix[matrix.lastIndex - i][j]
            matrix[matrix.lastIndex - i][j] = temp
        }
    }
    
    return matrix
}

fun determinant(scanner: Scanner) {
    print("Enter matrix size: ")
    val rows = scanner.nextInt()
    val columns = scanner.nextInt()
    
    println("Enter matrix:")
    val matrix = mutableListOf<MutableList<Double>>()
    
    for (i in 1..rows) {
        val row = mutableListOf<Double>()
        for (j in 1..columns) {
            val number = scanner.nextDouble()
            row.add(number)
        }
        matrix.add(row)
    }
    
    if (rows != columns) {
        println("The operation cannot be performed.")
        return
    }
    
    val result = calculateDeterminant(matrix).toString().split(".")
    println("The result is:")
    println(if (result.size == 1 || result[1].all { it == '0' }) result[0] else "${result[0]}.${result[1]}")
}

fun calculateDeterminant(matrix: MutableList<MutableList<Double>>): Double {
    if (matrix.size == 1) return matrix[0][0]
    if (matrix.size == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
    
    var result = 0.0
    for (k in matrix.indices) {
        val cofactor = calculateCofactor(matrix, 0, k)
        result += cofactor * matrix[0][k]
    }
    
    return result
}

fun calculateCofactor(matrix: MutableList<MutableList<Double>>, rowToRemove: Int, columnToRemove: Int): Double {
    val minor = mutableListOf<MutableList<Double>>()
    for (i in matrix.indices) {
        if (i == rowToRemove) continue
        val row = mutableListOf<Double>()
        for (j in matrix.indices) {
            if (j == columnToRemove) continue
            row.add(matrix[i][j])
        }
        minor.add(row)
    }
    return calculateDeterminant(minor) *  (-1.0).pow(rowToRemove + columnToRemove + 2)
}

fun inverse(scanner: Scanner) {
    print("Enter matrix size: ")
    val rows = scanner.nextInt()
    val columns = scanner.nextInt()
    
    println("Enter matrix:")
    val matrix = mutableListOf<MutableList<Double>>()
    
    for (i in 1..rows) {
        val row = mutableListOf<Double>()
        for (j in 1..columns) {
            val number = scanner.nextDouble()
            row.add(number)
        }
        matrix.add(row)
    }
    
    if (rows != columns) {
        println("This matrix doesn't have an inverse.")
        return
    }
    
    val determinant = calculateDeterminant(matrix)
    if (determinant == 0.0) {
        println("This matrix doesn't have an inverse.")
        return
    }
    
    val cofactorMatrix = mutableListOf<MutableList<Double>>()
    for (i in matrix.indices) {
        val row = mutableListOf<Double>()
        for (j in matrix.indices) {
            val number = calculateCofactor(matrix, i, j)
            row.add(number / determinant)
        }
        cofactorMatrix.add(row)
    }
    
    val inverseMatrix = mainDiagonalTranspose(cofactorMatrix)
    
    println("The result is:")
    for (i in 0 until rows) {
        for (j in 0 until rows) {
            var number = inverseMatrix[i][j].toString()
            if (number == "-0.0") number = "0.0"
            val parts = number.split(".")
            if (parts[1].all { it == '0' }) number = parts[0]
            else if (parts[1].length > 2) number = "${parts[0]}.${parts[1].substring(0, 2)}"
            
            print("$number ")
        }
        println()
    }
}
