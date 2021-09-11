import java.util.HashSet;
import java.util.Set;

interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();
}

//Goal test : no cell can go, every cell has visited
//tansition model : 4 directions, up, right, down ,left clock - right hand rule, going up until can not go, then turn right(or left)
//两个数捏一块，唯一的hashcode a * 31 + b
public class LC489 {
    int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //up, right, down, left
    Set<Integer> visited = new HashSet<>();
    public void cleanRoom(Robot robot) {
        this.dfs(robot, 0, 0, 0);
    }

    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    private void dfs(Robot robot, int row, int col, int arrow) {
        robot.clean();
        visited.add(row * 201 + col);

        for (int i = 0; i < 4; i++) {
            int nextArrow = (arrow + i) % 4;
            int nextRow = row + DIRECTIONS[nextArrow][0];
            int nextCol = col + DIRECTIONS[nextArrow][1];

            if (!visited.contains(nextRow * 201 + nextCol) && robot.move()) {
                dfs(robot, nextRow, nextCol, nextArrow);
                goBack(robot); //back tack, go back, also same arrow
            }
            robot.turnRight(); //match next arrow direction
        }
    }
}
