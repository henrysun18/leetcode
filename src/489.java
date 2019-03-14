import java.util.HashSet;
import java.util.Set;

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();

    // Clean the current cell.
    public void clean();
}

class RobotRoomCleaner {
	public void cleanRoom(Robot robot) {
		// every node's neighbours should be fully traversed

		//ITERATIVE??
		////////// clean current cell and push it to stack
		////////// traverse cell neighbours
		/////////// when all neighbours are cleaned or blocked, put current into set of visited points
        /*Set<Point> cleaned = new HashSet<>();
        
        LinkedList<Point> stack = new LinkedList<>();
        
        Direction currDir = Direction.UP;
        Point curr = new Point(0,0);
        clean();
        cleaned.add(curr);
        stack.push(curr);
        
        while (!stack.isEmpty()) {
            // traverse all neighbours
            Point above = new Point(curr.x-1, curr.y);
            Point below = new Point(curr.x+1, curr.y);
            Point 
            if (move(Direction.UP, curr, )) {
                //successfully moved up
            }
        }*/

		//RECURSIVE
		// to traverse every neighbour in dfs fashion,
		// we need a helper function that calls itself
		// this helper will go forward then come back, then go left then come back, then go right then come back
		// every time it calls move(), it will call itself to do the dfs
		Set<String> cleaned = new HashSet<>();

		//directions: 0 for up, 1 for left, 2 for down, 3 for right

		dfs(robot, 0, 0, 0, cleaned);
	}

	private void dfs(Robot robot, int x, int y, int robotOrientation, Set<String> cleaned) {
		robot.clean();
		cleaned.add(x + ":" + y);

		for (int i = 0; i < 4; i++) {
			//dfs forward, then turn left and dfs (3 times), then turn right, move forward, then 180 to backtrack
			int newX = x, newY = y;
			switch(robotOrientation) {
				case 0:
					newY++; //up is +Y, right is +X
					break;
				case 1:
					newX--;
					break;
				case 2:
					newY--;
					break;
				default:
					newX++;
			}
			String key = newX + ":" + newY;
			if (!cleaned.contains(key)) {
				//try to move forward and dfs, then come back
				if (robot.move()) {
					dfs(robot, newX, newY, robotOrientation, cleaned);
				}
			}

			// turn left to repeat
			robotOrientation = (robotOrientation+1) % 4;
			robot.turnLeft(); //forgot to add this line !!
		}

		//currently, robot is facing same direction as when it entered the function
		//go back one step
		robot.turnLeft();
		robot.turnLeft();
		robot.move();
		robot.turnLeft();
		robot.turnLeft();
	}
}

// can simply hash a colon-separated string!
/*class Point {
    public int x;
    public int y;
    
    public boolean equals(Object obj) {
        Point that = (Point) obj;
        return x == that.x && y == that.y;
    }
    
    public int hashCode() {
        return 37 * x + y;
    }
}*/