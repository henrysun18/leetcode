class TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
		//we can use a hashmap to store tasks & num left for each
		//then cycle through all the tasks until we go back to the first task
		//then wait until we can begin the cycle again

		//actually, if input was [a, a, a, a, b, b, c], 1, then the solution should be a,b,a,b,a,c,a instead of a,b,c,a,b,a,null,a
		//so we should actually find the task with the most remaining to be scheduled asap?
		//basically a greedy algorithm that finds the earliest next event, with the most number of tasks remaining
		//on every time unit, find all tasks that can be run immediately, and pick the one with the most remaining!
		//or we just line up the tasks, sorted by num remaining, then move them back up to n times behind the front of the line
		//but which data structure can help us accomplish that with good performance

		if (tasks == null || tasks.length == 0) return 0;

		//since letters only range A-Z, let's have auxiliary array to count the occurrences
		int[] numTasks = new int[26];
		int[] nextAvailableTaskTime = new int[26];
		for (char c : tasks) {
			numTasks[c - 'A']++;
		}

		//go through the array and find the task with the most tasks remaining and can be run the earliest

		int currTime = 0;
		int finished = 0;
		while (true) {
			//go through alphabet and find the char that can be run the earliest and has most tasks remaining
			int nextTask = 0;
			int nextTaskTime = Integer.MAX_VALUE;

			for (int i = 0; i < 26; i++) {
				if (numTasks[i] == 0) continue;

				boolean canExecuteAsEarlyAsNextTask = nextAvailableTaskTime[i] == nextTaskTime;
				boolean canExecuteEarlier = nextAvailableTaskTime[i] < nextTaskTime;
				boolean canExecuteNow = nextAvailableTaskTime[i] <= currTime;
				if (canExecuteNow) {
					//update next task in 2 cases:
					//1. current next task CANNOT execute now
					//2. current next task can also execute now, but i has more tasks remaining
					//DON'T FORGET TO CONSIDER CASE 1!!!!!
					if (nextAvailableTaskTime[nextTask] > currTime || numTasks[i] > numTasks[nextTask]) {
						nextTask = i;
					}
					nextTaskTime = currTime; //always do this, otherwise nextTaskTime might stay at MAX_VALUE
				} else if (canExecuteEarlier) {
					nextTask = i;
					nextTaskTime = nextAvailableTaskTime[i];
				} else if (canExecuteAsEarlyAsNextTask) {
					if (numTasks[i] > numTasks[nextTask]) {
						nextTask = i;
					}
				}
			}
			//if no task can go immediately, we need to set currTime to nextTaskTime to address the idle intervals
			if (nextTaskTime > currTime) {
				currTime = nextTaskTime;
			}

			//best task chosen, now execute and update state
			numTasks[nextTask]--; //execute
			currTime++; //increment time
			nextAvailableTaskTime[nextTask] = currTime + n; //time after executing and cooldown
			nextTaskTime = nextAvailableTaskTime[nextTask];
			finished++;

			if (finished == tasks.length) {
				return currTime;
			}
		}



        /*second attempt, looks correct but logic is convoluted af and i accidentally looped thgouh ALL tasks instead of just 0 to 26 lol too tired
        int finished = 0;
        int currTime = 0;
        int timeOfNextTask = 0;
        while (true) {
            //find the best task to run, it should have the earliest possible start time and most num tasks left
            int nextTask = tasks[0] - 'A';
            for (char c : tasks) {
                int task = (int) (c - 'A');
                if (numTasks[task] == 0) continue;

                //if task can be run immediately and it has the most numTasks
                if (nextAvailableTaskTime[task] <= currTime) {
                    //System.out.println(nextAvailableTaskTime[task] + " task " + task + " <= " + currTime);
                    if (nextAvailableTaskTime[nextTask] > currTime || numTasks[task] > numTasks[nextTask]) {
                        nextTask = task;
                        timeOfNextTask = nextAvailableTaskTime[task];
                    }
                } else if (nextAvailableTaskTime[task] < timeOfNextTask || nextAvailableTaskTime[task] == timeOfNextTask && numTasks[task] > numTasks[nextTask]) {
                    //else if task is the earliest possible next task
                    nextTask = task;
                    timeOfNextTask = nextAvailableTaskTime[task];
                }
            }

            if (nextAvailableTaskTime[nextTask] > currTime) {
                currTime = nextAvailableTaskTime[nextTask];
            }

            //execute the task and update state
            numTasks[nextTask]--;
            currTime++;
            nextAvailableTaskTime[nextTask] = currTime + n; //execute AND cooldown included

            timeOfNextTask = nextAvailableTaskTime[nextTask];
            finished++;

            //System.out.println(nextTask + " was run, afterwards the time is: " + currTime);
            if (finished == tasks.length) {
                return currTime;
            }
        }
        */




        /*first attempt, wrong since i didnt consider edge case
        //first populate map
        Map<Character, Integer> numLeft = new HashMap<>();
        for (char c : tasks) {
            numLeft.put(c, numLeft.getOrDefault(c, 0) + 1);
        }

        //now start going through the tasks
        int finished = 0;
        int intervals = 0;
        while (finished < tasks.length) {
            int count = 0;
            for (char key : numLeft.keySet()) {
                if (numLeft.get(key) > 0) {
                    numLeft.put(key, numLeft.get(key)-1);
                    count++;
                    finished++;
                }
            }

            //break early condition, edge case
            if (finished == tasks.length) {
                return intervals + count; //don't need to wait for next cycle by incrementing max of n and count!
            }

            intervals += Math.max(n+1, count);
        }

        return intervals;
        */
	}
}