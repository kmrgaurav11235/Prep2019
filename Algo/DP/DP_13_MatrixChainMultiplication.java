/*
https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/Dynamic/chainMatrixMult.htm

- Given a sequence of matrices, find the most efficient way to multiply these matrices together. The 
    problem is not actually to perform the multiplications, but merely to decide in which order to 
    perform the multiplications.
- We have many options to multiply a chain of matrices because matrix multiplication is associative. 
    In other words, no matter how we parenthesize the product, the result will be the same. For example, 
    if we had four matrices A, B, C, and D, we would have:
    (ABC)D = (AB)(CD) = A(BCD) = ....
- However, the order in which we parenthesize the product affects the number of simple arithmetic 
    operations needed to compute the product, or the efficiency. For example, suppose A is a 10 × 30 
    matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
    * (AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
    * A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
    Clearly the first parenthesization requires less number of operations.
- Given an array p[] which represents the chain of matrices such that the i-th matrix Ai is of dimension 
    p[i-1] x p[i]. We need to write a function matrixChainOrder() that should return the minimum number 
    of multiplications needed to multiply the chain.

    1) 
    Input: p[] = {40, 20, 30, 10, 30}   
    Output: 26000  
    There are 4 matrices of dimensions 40x20, 20x30, 30x10 and 10x30. The minimum number of multiplications 
    are obtained by putting parenthesis in following way:
    (A(BC))D --> 20*30*10 + 40*20*10 + 40*10*30

    2)
    Input: p[] = {10, 20, 30, 40, 30} 
    Output: 30000 
    There are 4 matrices of dimensions 10x20, 20x30, 30x40 and 40x30. The minimum number of multiplications 
    are obtained by putting parenthesis in following way:
    ((AB)C)D --> 10*20*30 + 10*30*40 + 10*40*30

    3)
    Input: p[] = {10, 20, 30}  
    Output: 6000  
    There are only two matrices of dimensions 10x20 and 20x30. So there is only one way to multiply the 
    matrices, cost of which is 10*20*30

- Solution:
    Step 1) The structure of an optimal parenthesization: For convenience, let us adopt the notation A[i..j], 
        where i < j, for the matrix that results from evaluating the product A[i]A[i+1]...A[j]. If the problem 
        is nontrivial, i.e., i < j, then to parenthesize the product A[i]A[i+1]...A[j], we must split the product 
        between A[k] and A[k+1] for some integer k in the range i <= k < j. 
        * That is, for some value of k, we first compute the matrices A[i..k] and A[k+1..j] and then multiply 
            them together to produce the final product A[i..j].
        * The cost of parenthesizing this way is the cost of computing the matrix A[i..k], plus the cost of 
            computing A[k+1..j], plus the cost of multiplying them together.
        * The optimal substructure of this problem is as follows: Suppose that to optimally parenthesize 
            A[i]A[i+1]..A[j], we split the product between A[k] and A[k+1]. Then, the way we parenthesize the 
            "prefix" subchain A[i]A[i+1]..A[k] within this optimal parenthesization of A[i]A[i+1]..A[j] must be 
            an optimal parenthesization of A[i]A[i+1]..A[k]. Same is true for the "suffix".
    Step 2) Recursive solution: Let m[i,j] be the minimum number of scalar multiplications needed to compute 
        the matrix A[i..j]. We can define m[i,j] recursively as follows. 
        * If i == j, the problem is trivial. The chain consists of just one matrix, so that no scalar multiplications 
            are necessary to compute the product.
        * When i < j, we use the optimal substructure property from Step 1. To optimally parenthesize, we split 
            the product A[i]A[i+1]..A[j] between A[k] and A[k+1], where i <= k < j. Then, m[i,j] equals the minimum 
            cost for computing the subproducts A[i..k] and A[k+1..j], plus the cost of multiplying these two matrices 
            together. Recalling that each matrix A[i] has dimension p[i-1] x p[i], we see that computing the matrix 
            product A[i..k]A[k+1..j] takes p[i-1] * p[k] * p[j] scalar multiplications. 
        * Thus, we obtain:
                    0 --- if i == j,
        m[i,j] = 
                    Min ( m[i,k] + m[k+1,j] + p[i-1] * p[k] * p[j] ) for all k, where i <= k < j --- if i < j
    Step 3) Using Dynamic Programming: We have relatively few distinct subproblems -- one subproblem for each 
        choice of i and j satisfying 0 <= i <= j < n. As we have this property of overlapping subproblems, we 
        can compute the optimal cost by using a tabular, bottom-up approach.
        * Input: Sequence p[] = {p[0], p[1], p[n - 1]} where the matrix A[i] has dimensions p[i-1] x p[i] for 
            i = (0,1,2...n - 1). 
        * The procedure uses an auxiliary table m[][] for storing the costs.
        * And another auxiliary tabl s[][] that records which index of k achieved the optimal cost in computing 
            m[i][j].
        * The algorithm should fill in the table min a manner that corresponds to solving the parenthesization 
            problem on matrix chains of increasing length.
        * matrixChainOrder (p[], n)
            let m[0,n-1][0,n-1] and s[0,n-1][0,n] be new tables
            // For simplicity of the program, one extra row and one extra column are allocated in m[][]. 
            // 0th row and 0th column of m[][] are not used
            for i = 1 to n - 1
                // cost is zero when multiplying one matrix
                m[i][i] = 0
            for l = 2 to n - 1
                // l is the chain length
                for i = 1 to n - l
                    j = i + l - 1
                    m[i][j] = Infinity
                    for k = i to j - 1
                        q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j]
                        if q < m[i][j]
                            m[i][j] = q
                            s[i][j] = k
            return m and s
*/
public class DP_13_MatrixChainMultiplication {
    
}