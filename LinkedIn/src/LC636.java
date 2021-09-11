import java.util.List;
import java.util.Stack;

public class LC636 {
    //end id 与 peek start id 匹配，出栈，计算时间
    //start 入栈

    class Log {
        int id;
        String label;
        int time;

        public Log(String log) {
            String[] ss = log.split(":");
            this.id = Integer.valueOf(ss[0]);
            this.label = ss[1];
            this.time = Integer.valueOf(ss[2]);
        }
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];

        Stack<Log> stack = new Stack<>();
        Stack<Integer> timeStack = new Stack<>();
        int lastTimeWindow = 0;

        for (String log : logs) {
           Log curLog = new Log(log);

            if (stack.isEmpty() || curLog.label.equals("start")) {
                stack.push(curLog);
                continue;
            }

            Log top = stack.pop();

            if (top.id != curLog.id)
                throw new IllegalArgumentException("Input is not valid");

            res[top.id] += curLog.time - top.time + 1;
            if (!stack.isEmpty()) { //still have main function running
                res[stack.peek().id] -= curLog.time - top.time + 1; //subtract in advance
            }
        }

        return res;
    }
}
