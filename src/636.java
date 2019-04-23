import java.util.List;
import java.util.Stack;

class ExclusiveTimeOfFunctions {
	public int[] exclusiveTime(int n, List<String> logs) {
		//wtf i just noticed logs are stored in TIMESTAMP ORDER!!!
		//makes this so much easier
		//cuz now i can just use a stack to record the actual execution time for each function
		//if functions are already sorted by id as well, then it's even easier
		//otherwise, we'll just use a simple array of size n that we'll return anyways
		int[] res = new int[n];
		Stack<int[]> stack = new Stack<>(); //item[0] is the id, item[1] is how long it has executed thus far
		int lastEventTime = 0;

		for (String log : logs) {
			if (stack.isEmpty()) {
				//must be a start event
				stack.push(new int[]{getId(log), 0});
				lastEventTime = getTime(log);
				continue;
			}

			// if (getId(log) != stack.peek()[0]) {
			if (isStart(log)) { //cuz apparently 2 functions with same id can be started at the same time
				//new start event, add this log's event time - lastEventTime to the top function on stack
				stack.peek()[1] += getTime(log) - lastEventTime;
				stack.push(new int[]{getId(log), 0});
				lastEventTime = getTime(log);
			} else {
				//concluding the event on top of the stack, note that end time includes the time itself
				stack.peek()[1] += getTime(log)+1 - lastEventTime;
				int[] function = stack.pop(); //function[0] is the id, [1] is the total execution time
				res[function[0]] += function[1]; //cuz apparently theres a test case where function id's aren't unique
				lastEventTime = getTime(log)+1; //lastEventTime starts at 7 if we have 0:end:6
			}
		}

		return res;
	}

	private int getId(String log) {
		return Integer.parseInt(log.split(":")[0]);
	}
	private boolean isStart(String log) {
		return log.split(":")[1].equals("start");
	}
	private int getTime(String log) {
		return Integer.parseInt(log.split(":")[2]);
	}
}
